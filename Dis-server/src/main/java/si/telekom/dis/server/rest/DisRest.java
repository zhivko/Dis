//https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup-1.5
package si.telekom.dis.server.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;

import io.swagger.annotations.Api;
import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.server.WsServer;
import si.telekom.dis.server.rest.api.DocumentsApiService;
import si.telekom.dis.server.rest.api.NotFoundException;


// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/application.wadl?detail=true
// http://localhost:8080/Dis-server/rest/disRest/importDocument
// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/importDocument
// mvn clean package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true

// https://localhost:8445/Dis-server/api/swagger/getListingYaml
// https://localhost:8445/Dis-server/api/swagger/getListingJson
// https://localhost:8445/Dis-server/api/application.wadl
@Api
public class DisRest extends DocumentsApiService {
	public DisRest() {
		super();
	}

	public DisRest(ServletConfig servletContext) {
		super();
	}

	/**
	 * dql must be in form select r_object_id, ... from [documentum type] where
	 * [where condition]
	 */
	@Override
	public Response getDocuments(String xTransactionId, String dql, SecurityContext securityContext) throws NotFoundException {

		IDfSession userSession = null;
		IDfCollection collection = null;

		try {
			User user = (User) securityContext.getUserPrincipal();
			String loginName = user.getId();
			String password = Base64.encodeBase64String(user.getPassword().getBytes());

			DocumentsResponse docResp = new DocumentsResponse();

			Logger.getLogger(this.getClass()).info("getDocuments for " + loginName + " for: " + dql);
			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfQuery query = new DfQuery();
			query.setDQL(dql);
			Logger.getLogger(this.getClass()).info("\tStarted dql query: " + dql);
			WsServer.log(loginName, "Started dql query...");
			long milis1 = System.currentTimeMillis();
			collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);

			while (collection.next()) {
				String r_object_id = collection.getString("r_object_id");
				IDfPersistentObject persObj = userSession.getObjectByQualification("dm_sysobject where r_object_id='" + r_object_id + "'");
				if (persObj != null) {
					si.telekom.dis.shared.Document doc = ExplorerServiceImpl.getInstance().docFromSysObject((IDfSysObject) persObj, loginName, userSession);
					Document doc1 = new Document();
					doc1.setrObjectId(doc.r_object_id);
					doc1.setObjectName(doc.object_name);
					doc1.setReleaseNumber(doc.releaseNo);

					ArrayList<Attribute> alAtts = new ArrayList<Attribute>();
					for (int i = 0; i < collection.getAttrCount(); i++) {
						String attName = collection.getAttr(i).getName();
						if (!attName.equals("r_object_id")) {
							Attribute att = new Attribute();
							if (collection.getAttr(i).isRepeating()) {
								ArrayList<String> values = new ArrayList<String>();
								for (int j = 0; j < collection.getValueCount(attName); j++) {
									values.add(collection.getRepeatingValue(attName, j).asString());
								}
								att.setName(attName);
								att.setValues(values);
								alAtts.add(att);
							} else {
								ArrayList<String> values = new ArrayList<String>();
								values.add(collection.getValue(attName).asString());
								att.setName(attName);
								att.setValues(values);
							}
							alAtts.add(att);
						}
					}
					doc1.setAttributes(alAtts);
					docResp.addDocumentsItem(doc1);
				}
			}
			return Response.ok(docResp).build();
		} catch (Throwable ex) {
			return Response.status(500, ex.getMessage()).build();
		} finally {
			if (collection != null)
				try {
					collection.close();
				} catch (DfException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (userSession != null)
				if (userSession.isConnected())
					userSession.getSessionManager().release(userSession);
		}
	}

	@Override
	public Response createDocument(String xTransactionId, NewDocumentRequest newDocumentRequest, SecurityContext securityContext)
			throws NotFoundException {

		try {
			User user = (User) securityContext.getUserPrincipal();

			Map<String, List<String>> attributes_ = new HashMap<String, List<String>>();
			for (Attribute att : newDocumentRequest.getAttributes()) {
				attributes_.put(att.getName(), att.getValues());
			}

			Map<String, List<String>> roles_ = new HashMap<String, List<String>>();
			for (RoleUsers role : newDocumentRequest.getRolesUsers()) {
				roles_.put(role.getRoleName(), role.getValues());
			}

//@formatter:off
		si.telekom.dis.shared.Document doc = ExplorerServiceImpl.getInstance().newDocument(
				user.getId(), 
				Base64.encodeBase64String(user.getPassword().getBytes()),
				newDocumentRequest.getProfileId(), 
				attributes_, 
				roles_, 
				newDocumentRequest.getTemplateObjectRObjectId());
//@formatter:on

			Document doc1 = convertDocument(doc);
			return Response.ok(doc1).build();

		} catch (Throwable ex) {
			return Response.status(500, ex.getMessage()).build();
		} finally {
		}
	}

	private Document convertDocument(si.telekom.dis.shared.Document doc) {
		Document doc1 = new Document();
		doc1.setrObjectId(doc.r_object_id);
		doc1.setObjectName(doc.object_name);
		doc1.setReleaseNumber(doc.releaseNo);

		return doc1;
	}

	@Override
	public Response importDocument(String xTransactionId, ImportDocumentRequest importDocumentRequest, SecurityContext securityContext)
			throws NotFoundException {
		try {
			User user = (User) securityContext.getUserPrincipal();

			Map<String, List<String>> attributes_ = new HashMap<String, List<String>>();
			for (Attribute att : importDocumentRequest.getAttributes()) {
				attributes_.put(att.getName(), att.getValues());
			}

			Map<String, List<String>> roles_ = new HashMap<String, List<String>>();
			for (RoleUsers role : importDocumentRequest.getRolesUsers()) {
				roles_.put(role.getRoleName(), role.getValues());
			}

//@formatter:off
		si.telekom.dis.shared.Document doc = ExplorerServiceImpl.getInstance().importDocument(
				user.getId(), 
				Base64.encodeBase64String(user.getPassword().getBytes()),
				"",
				importDocumentRequest.getProfileId(), 
				importDocumentRequest.getStateId(), 
				attributes_, 
				roles_, 
				importDocumentRequest.getContentBase64().getBytes(), 
				importDocumentRequest.getFormat());
//@formatter:on
			Document doc1 = convertDocument(doc);
			return Response.ok(doc1).build();

		} catch (Throwable ex) {
			return Response.status(500, ex.getMessage()).build();
		} finally {
		}
	}

	@Override
	public Response demoteDocument(String rObjectId, String xTransactionId, SecurityContext securityContext) throws NotFoundException {
		try {
			User user = (User) securityContext.getUserPrincipal();
			String loginName = user.getId();
			String password = Base64.encodeBase64String(user.getPassword().getBytes());

			si.telekom.dis.shared.Document doc = ExplorerServiceImpl.getInstance().demote(loginName, password, rObjectId);
			Document doc1 = convertDocument(doc);
			
			return Response.ok(doc1).build();
		} catch (Throwable ex) {
			return Response.status(500, ex.getMessage()).build();
		} finally {
		}
	}

	@Override
	public Response getContent(String rObjectId, String format, String xTransactionId, SecurityContext securityContext) throws NotFoundException {
		IDfSession userSession = null;

		try {
			User user = (User) securityContext.getUserPrincipal();
			String loginName = user.getId();
			String password = Base64.encodeBase64String(user.getPassword().getBytes());

			userSession = AdminServiceImpl.getSession(loginName, password);
			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject persObj = userSession.getObject(new DfId(rObjectId));
			IDfSysObject sysObj = (IDfSysObject) persObj;
			if (format.equals(""))
				format = sysObj.getContentType();
			IDfFormat formatObj = userSession.getFormat(format);

			String mimeType = formatObj.getMIMEType().toString();
			String dosExtension = formatObj.getDOSExtension();

			ByteArrayInputStream bacontentStreamIs = sysObj.getContentEx(format, 0);

			try (ByteArrayOutputStream binaryOutput = new ByteArrayOutputStream()) {
				try (ObjectOutputStream objectStream = new ObjectOutputStream(binaryOutput)) {
					objectStream.writeObject(bacontentStreamIs);
				}
				byte[] content = Base64.encodeBase64(binaryOutput.toByteArray());

				return Response.ok(content).build();
			}

		} catch (Exception ex) {
			return Response.status(500, ex.getMessage()).build();
		} finally {
			if (userSession != null)
				if (userSession.isConnected())
					userSession.getSessionManager().release(userSession);
		}
	}

	@Override
	public Response promoteDocument(String rObjectId, String xTransactionId, SecurityContext securityContext) throws NotFoundException {
		try {
			User user = (User) securityContext.getUserPrincipal();
			String loginName = user.getId();
			String password = Base64.encodeBase64String(user.getPassword().getBytes());

			si.telekom.dis.shared.Document doc = ExplorerServiceImpl.getInstance().promote(loginName, password, rObjectId);
			Document doc1 = convertDocument(doc);
			
			return Response.ok(doc1).build();
		} catch (Throwable ex) {
			return Response.status(500, ex.getMessage()).build();
		} finally {
		}
	}

/*
 * 
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/dqlLookup")
	public List<List<String>> dqlLookup(@QueryParam("loginName") String loginName, @QueryParam("passwordEncrypted") String passwordEncrypted,
			@QueryParam("dql") String dql) throws Exception {
		// return ExplorerServiceImpl.getInstance().dqlLookup(loginName,
		// passwordEncrypted, dql);
		try {
			return ExplorerServiceImpl.getInstance().dqlLookup(loginName, passwordEncrypted, dql);
		} catch (Exception ex) {
			throw new WebApplicationException(ex);
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getSampleNewDocumentArg")
	// public String newDocument(@QueryParam("loginName") String loginName,
	// @QueryParam("passwordEncrypted") String passwordEncrypted,
	// @QueryParam("profileId") String profileId, @BeanParam HashMap<String,
	// ArrayList<String>> attributes, @BeanParam HashMap<String,
	// ArrayList<String>> roles,
	// @QueryParam("templateObjectNameOrFolder") String
	// templateObjectNameOrFolder) {
	public NewDocumentRequest getSampleNewDocumentArg(@Context SecurityContext sc) {
		NewDocumentRequest ndReq = new NewDocumentRequest();

		ndReq.setProfileId("test");

		List<Attribute> attributes = new ArrayList<Attribute>();
		Attribute att1 = new Attribute();
		att1.setName("title");
		att1.addValuesItem("moj title");
		attributes.add(att1);
		ndReq.setAttributes(attributes);

		List<RoleUsers> roleUsers = new ArrayList<RoleUsers>();
		RoleUsers rolUser1 = new RoleUsers();
		rolUser1.setRoleName("coordinator");
		rolUser1.addValuesItem("zivkovick");
		rolUser1.addValuesItem("kovacevicr");
		roleUsers.add(rolUser1);
		RoleUsers rolUser2 = new RoleUsers();
		rolUser2.setRoleName("user");
		rolUser2.addValuesItem("dm_world");
		roleUsers.add(rolUser2);

		ndReq.setRolesUsers(roleUsers);

		ndReq.setTemplateObjectRObjectId("0900000191cfbdf8");

		return ndReq;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/importDocument")
	// public String newDocument(@QueryParam("loginName") String loginName,
	// @QueryParam("passwordEncrypted") String passwordEncrypted,
	// @QueryParam("profileId") String profileId, @BeanParam HashMap<String,
	// ArrayList<String>> attributes, @BeanParam HashMap<String,
	// ArrayList<String>> roles,
	// @QueryParam("templateObjectNameOrFolder") String
	// templateObjectNameOrFolder) {
	public String importDocument(@Context SecurityContext sc, ImportDocumentRequest importDocumentReq) {
		try {
			User user = (User) sc.getUserPrincipal();

			Map<String, List<String>> attributes_ = new HashMap<String, List<String>>();
			for (Attribute att : importDocumentReq.getAttributes()) {
				attributes_.put(att.getName(), att.getValues());
			}

			Map<String, List<String>> roles_ = new HashMap<String, List<String>>();
			for (RoleUsers role : importDocumentReq.getRolesUsers()) {
				roles_.put(role.getRoleName(), role.getValues());
			}

// @formatter:off
			return ExplorerServiceImpl.getInstance().importDocument(
					user.getId(), 
					user.getPassword(), 
					importDocumentReq.getProfileId(),
					null,
					importDocumentReq.getStateId(), 
					attributes_, 
					roles_, 
					importDocumentReq.getContentBase64().getBytes(),
					importDocumentReq.getFormat());
// @formatter:on
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getProfilesForClassSign")
	public List<String> getProfilesForClassSign(@Context SecurityContext sc, @QueryParam("classSign") String classSign,
			@QueryParam("wizzardType") String wizardType) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			User user = (User) sc.getUserPrincipal();
			//@formatter:on
			List<Profile> profiles = AdminServiceImpl.getInstance().getProfilesForClassSign(user.getId(),
					Base64.encodeBase64String(user.getPassword().getBytes()), classSign, wizardType);
//@formatter:off
			
			for (Profile profile : profiles) {
				ret.add(profile.id);
			}
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
		return ret;
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/promote")
	public Void promote(@QueryParam("loginName") String loginName, @QueryParam("passwordEncrypted") String passwordEncrypted,
			@QueryParam("r_object_id") String r_object_id) throws Exception {
		return ExplorerServiceImpl.getInstance().promote(loginName, passwordEncrypted, r_object_id);
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/demote")
	public Void demote(@QueryParam("loginName") String loginName, @QueryParam("passwordEncrypted") String passwordEncrypted,
			@QueryParam("r_object_id") String r_object_id) throws Exception {
		return ExplorerServiceImpl.getInstance().demote(loginName, passwordEncrypted, r_object_id);
	}
 * 	
 */
	
	
	
}
