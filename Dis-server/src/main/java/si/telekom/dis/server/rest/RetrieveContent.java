package si.telekom.dis.server.rest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;

import si.telekom.dis.server.AdminServiceImpl;

// http://localhost:8888/rest/retrieveContent?objectType=mob_outgoing_invoice_s&classSignId=%20&count=42239930

@Path("retrieve")
public class RetrieveContent {

	static IDfSession objSess;
	static {
		try {
			objSess = AdminServiceImpl.getAdminSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// CustomerSearch cs = new CustomerSearch();
		// List<String> list = cs.search("ŽIVKOVI");
		// for (String line : list) {
		// System.out.println(line);
		// }

	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	public String search(@Context Request request, @QueryParam("objectType") String objType,
			@QueryParam("classSignId") String classSignId, @QueryParam("mobSource") String mob_source,
			@QueryParam("count") int count) {

		String path;
		if (SystemUtils.IS_OS_LINUX)
			path = "c:/temp";
		else
			path = "/app/render/content";
		String fileName = null;
		Logger.getLogger(this.getClass()).info("retrieving content.223423..");
		try {

			IDfQuery query = new DfQuery();

			if (objType.endsWith("_s"))
				objType = objType.substring(0, objType.length() - 2);
			long t1 = System.currentTimeMillis();
//			String dqlObjByQual = "select r_object_id from " + objType + " where mob_classification_id = '"
//					+ classSignId + "' and owner_name='" + ownerName + "' ENABLE(RETURN_TOP 1)";

			String dqlObjByQual = "select r_object_id from " + objType + " where mob_classification_id='" + classSignId
					+ "' and mob_source='" + mob_source + "' ENABLE(RETURN_TOP 1)";

			Logger.getLogger(this.getClass()).info(dqlObjByQual);

			query.setDQL(dqlObjByQual);
			String classSignReplaced = classSignId.replace("(", "");
			classSignReplaced = classSignReplaced.replace(")", "");

			IDfCollection coll = query.execute(objSess, IDfQuery.DF_READ_QUERY);
			if (coll.next()) {
				IDfSysObject sysObj = (IDfSysObject) objSess.getObject(new DfId(coll.getString("r_object_id")));
				String cntFormatted = String.format(Locale.ROOT, "%012d", count);
				if (sysObj.getFormat() != null) {
					fileName = path + "/" + cntFormatted + "#" + objType + "#" + sysObj.getOwnerName() + "#"
							+ classSignReplaced + "#" + sysObj.getId("r_object_id") + "."
							+ sysObj.getFormat().getDOSExtension();
				} else {
					fileName = path + "/" + cntFormatted + "#" + objType + "#" + sysObj.getOwnerName() + "#"
							+ classSignReplaced + "#" + sysObj.getId("r_object_id") + ".unknown";
				}
				if (sysObj.getContentSize() > 0)
					sysObj.getFile(fileName);
				long t2 = System.currentTimeMillis();
				String durationStr1 = String.format(Locale.ROOT, "%.2f", ((t2 - t1) / 1000.0f));
				Logger.getLogger(this.getClass()).info("Took " + durationStr1 + "s for " + fileName);
			} else {
				Logger.getLogger(this.getClass()).info("no result");
			}
			coll.close();
		} catch (DfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileName;
	}

	@GET
	@Path("getByDql")
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	public String getByDql(@Context Request request, @QueryParam("dql") String dql) {
		String ret = "";
		String method = request.getMethod();
		if (method.equals("GET")) {

			String path;
			if (!SystemUtils.IS_OS_LINUX)
				path = "c:\\temp\\content";
			else
				path = "/app/render/content";
			String fileName = null;
			Logger.getLogger(this.getClass()).info("retrieving content...");
			try {

				IDfQuery query = new DfQuery();
				long t1 = System.currentTimeMillis();
				Logger.getLogger(this.getClass()).info(dql);
				query.setDQL(dql);

				IDfCollection coll = query.execute(objSess, IDfQuery.DF_READ_QUERY);
				if (coll.next()) {
					IDfSysObject sysObj = (IDfSysObject) objSess.getObject(new DfId(coll.getString("r_object_id")));
					String name = "";
					for (int j = 0; j < coll.getAttrCount(); j++) {
						name = name + sysObj.getValue(coll.getAttr(j).getName()).asString() + "#";
					}
					
					String mob_source = sysObj.getValue("mob_source").toString();
					if (mob_source == null || mob_source.contentEquals(" ") || mob_source.contentEquals("NULL"))
						path = path + File.separator + sysObj.getValue("owner_name");
					else
						path = path + File.separator + sysObj.getValue("mob_source");
					
					File fold = new File(path);
					fold.mkdirs();
					
					
					if (name.length() > 2)
						name = name.substring(0, name.length() - 1);

					name = name.replace("/", "_");

					if (sysObj.getFormat() != null) {
						fileName = path + File.separator + name + "." + sysObj.getFormat().getDOSExtension();
					} else {
						fileName = path + File.separator + name + ".unknown";
					}
					if (sysObj.getContentSize() > 0)
						sysObj.getFile(fileName);	
					else {
						PrintWriter writer;
						try {
							writer = new PrintWriter(fileName, "UTF-8");
							writer.close();
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					long t2 = System.currentTimeMillis();
					String durationStr1 = String.format(Locale.ROOT, "%.2f", ((t2 - t1) / 1000.0f));
					Logger.getLogger(this.getClass()).info("Took " + durationStr1 + "s for " + fileName);
				} else {
					Logger.getLogger(this.getClass()).info("no result");
				}
				coll.close();

				fileName = fileName.replace("\\", "/");
				fileName = fileName.replace(" ", "%20");
				fileName = fileName.replace("#", "%23");

				ret = "file:///" + fileName;
				Logger.getLogger(this.getClass()).info(ret);
			} catch (DfException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// objSess.getSessionManager().release(objSess);
			}
		}
		return ret;
	}
}