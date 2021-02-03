//https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup-1.5
package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.binary.Base64;

import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.server.rest.api.DocumentsApiService;
import si.telekom.dis.server.rest.api.NotFoundException;
import si.telekom.dis.shared.Profile;

// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/application.wadl?detail=true
// http://localhost:8080/Dis-server/rest/disRest/importDocument
// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/importDocument
// mvn clean package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true

// http://localhost:8080/Dis-server/api/v1/dis-dev/application.wadl
// http://localhost:8080/Dis-server/rest/application.wadl
public class DisRest extends DocumentsApiService {
	public DisRest() {
		super();
	}

	public DisRest(ServletConfig servletContext) {
		super();
		// TODO Auto-generated constructor stub
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
		String rObjectId = ExplorerServiceImpl.getInstance().newDocument(
				user.getId(), 
				Base64.encodeBase64String(user.getPassword().getBytes()),
				newDocumentRequest.getProfileId(), 
				attributes_, 
				roles_, 
				newDocumentRequest.getTemplateObjectRObjectId());
//@formatter:on
			Document doc = new Document();
			doc.setrObjectId(rObjectId);
			return Response.ok(doc).build();
		} catch (Exception ex) {
			return Response.status(500, ex.getMessage()).build();
		}

	}

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

	@Override
	public Response demoteDocument(String rObjectId, String xTransactionId, SecurityContext securityContext) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getContent(String rObjectId, String format, String xTransactionId, SecurityContext securityContext) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDocuments(String xTransactionId, String rObjectId, String dql, SecurityContext securityContext) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response importDocument(String xTransactionId, ImportDocumentRequest importDocumentRequest, SecurityContext securityContext)
			throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response promoteDocument(String rObjectId, String xTransactionId, SecurityContext securityContext) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
