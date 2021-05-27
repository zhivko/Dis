package si.telekom.dis.server.jobs;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;

import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;

public class MoveMobFormTemplateToEffective implements Runnable {

	public void run() {

		// prevent running on development machine
		try (final DatagramSocket socket = new DatagramSocket()) {
			socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			String ip = socket.getLocalAddress().getHostAddress();
			if (ip.equals("10.115.4.149")) {
				Logger.getLogger(this.getClass()).warn("MoveMobFormTemplateToEffective exited because code runs on development machine.");
				return;
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		IDfSession userSession = null;
		IDfCollection coll = null;
		Thread.currentThread().setName("job_MoveMobFormTemplateToEffective");
		try {
			userSession = AdminServiceImpl.getAdminSession();
//@formatter:off
			String dql = "select r_object_id from mob_form_template where not mob_valid_from is nulldate and " +
									 "DATEDIFF(day,mob_valid_from, date(today)) > 0 and " +
									 "any r_version_label in ('draft') and " +
									 "r_object_id not in (select r_object_id where any r_version_label in ('effective'))" + 
									 "group by r_object_id enable (return_range 1 30 'r_object_id')";
//@formatter:on
			Logger.getLogger(this.getClass()).info("Dql: " + dql);

			IDfQuery q = new DfQuery(dql);

			coll = q.execute(userSession, DfQuery.READ_QUERY);
			while (coll.next()) {
				IDfPersistentObject persObj = userSession.getObject(coll.getId("r_object_id"));
				Logger.getLogger(this.getClass()).info("About to move from draft to effective, barcode: " + persObj.getString("object_name")
						+ " r_object_id: " + coll.getId("r_object_id").toString());
				
				Object[] profileAndRolesOfUserAndState = ExplorerServiceImpl.getInstance().getProfileAndUserRolesAndState(persObj,
						userSession.getLoginInfo().getUser(), userSession);

				userSession.beginTrans();
				ExplorerServiceImpl.getInstance().moveToState(userSession, coll.getId("r_object_id").getId(), "effective", profileAndRolesOfUserAndState);
				userSession.commitTrans();
			}
			if(userSession.isTransactionActive())
				userSession.abortTrans();
			
		} catch (Throwable ex) {
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(MoveMobFormTemplateToEffective.class).error(ex.getMessage());
			Logger.getLogger(MoveMobFormTemplateToEffective.class).error(stackTrace);

		} finally {
			if (coll != null) {
				try {
					coll.close();
				} catch (Exception ex) {
					Logger.getLogger(MoveMobFormTemplateToEffective.class).error(ex.getMessage());
				}
			}
			if (userSession != null && userSession.isConnected())
				userSession.getSessionManager().release(userSession);
		}

	}

	public static void main(String[] args) {
		Thread t = new Thread(new MoveMobFormTemplateToEffective());
		t.setName("MoveMobFormTemplateToEffective");
		t.run();
	}

}
