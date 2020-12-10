package si.telekom.dis.server.jobs;

import org.apache.log4j.Logger;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfId;

import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;

public class MoveMobFormTemplateToEffective implements Runnable {

	public void run() {
		IDfSession sess = null;
		IDfCollection coll = null;
		try {
			sess = AdminServiceImpl.getAdminSession();

			String dql = "select r_object_id from mob_form_template where not mob_valid_from is nulldate and "
					+ "DATEDIFF(day,\"mob_valid_from\", date(today)) > 0 and any r_version_label in ('draft') group by r_object_id enable (return_range 1 30 'r_object_id')";
			IDfQuery q = new DfQuery(dql);

			coll = q.execute(sess, DfQuery.READ_QUERY);
			while (coll.next()) {
				IDfPersistentObject persObj = sess.getObject(coll.getId("r_object_id"));
				Logger.getLogger(this.getClass()).info(
						"move from draft to effective, barcode: " + persObj.getString("object_name") + " r_object_id: " + coll.getId("r_object_id").toString());
				ExplorerServiceImpl.getInstance().promote(sess, coll.getId("r_object_id").toString());
			}

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
			if (sess != null && sess.isConnected())
				sess.getSessionManager().release(sess);
		}

	}

	public static void main(String[] args) {
		Thread t = new Thread(new MoveMobFormTemplateToEffective());
		t.setName("MoveMobFormTemplateToEffective");
		t.run();
	}

}
