package si.telekom.dis.server.reports;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;

import si.telekom.dis.server.WsServer;

public class FilterSapObjects implements IIncludeInReport {

	public FilterSapObjects() {

	}

	@Override
	public boolean shouldInclude(IDfSysObject sysObj, IDfSession userSession) throws Exception {
		// TODO Auto-generated method stub
		IDfQuery query = new DfQuery();
		boolean ret = false;

		String dql = "select time_stamp, audited_obj_id, event_name, attribute_list, attribute_list_old from dm_audittrail_Mobitel_all where (audited_obj_id in (select r_object_id from dm_document(all) where i_chronicle_id in "
				+ " (select i_chronicle_id from dm_document where r_object_id = '" + sysObj.getObjectId().getId()
				+ "'))) and event_name='dm_save' order by r_object_id";

		query.setDQL(dql);
		Logger.getLogger(this.getClass()).info("Started dql query: " + dql);
		long milis1 = System.currentTimeMillis();
		IDfCollection collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);
		long milis2 = System.currentTimeMillis();
		Logger.getLogger(this.getClass()).info("Ended in: " + (int) ((milis2 - milis1) / 1000) + "s");

		while (collection.next()) {
			String attribute_list[] = collection.getString("attribute_list").split(",");
			String attribute_list_old[] = collection.getString("attribute_list_old").split(",");

			String attName = "mobs_invoice_type_finance";

			HashMap<String, String> hmAttOld = new HashMap<String, String>();
			for (String attWithValue : attribute_list_old) {
				String[] vals = attWithValue.split("=");
				if (vals.length == 2)
					hmAttOld.put(vals[0], vals[1]);
			}

			HashMap<String, String> hmAtt = new HashMap<String, String>();
			for (String attWithValue : attribute_list) {
				String[] vals = attWithValue.split("=");
				if (vals.length == 2)
					hmAtt.put(vals[0], vals[1]);
			}

			String value = hmAtt.get("mobs_invoice_type_finance");
			String oldValue = hmAttOld.get("mobs_invoice_type_finance");

			if (value != null && oldValue != null && !value.equals("") && !oldValue.equals("") && !value.equals(oldValue)) {
				String msg = "Dokument: r_object_id: " + collection.getId("audited_obj_id").toString() + " barcode: <strong>" + sysObj.getObjectName()
						+ "</strong>, timestamp: " + collection.getTime("time_stamp").toString() + " attribute " + attName + " from: <strong>" + oldValue
						+ "</strong> to: <strong>" + value + "</strong>";
				Logger.getLogger(this.getClass()).info(msg);
				WsServer.log(userSession.getLoginInfo().getUser(), msg);
				ret = true;
				break;
			}
		}
		collection.close();

		return ret;
	}

}
