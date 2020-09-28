package si.telekom.dis.server.rest;

import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.shared.Profile;

// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/application.wadl
@Named
@Path("/disRest")
public class DisRest {

	public DisRest() {

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
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/newDocument")
	public String newDocument(@QueryParam("loginName") String loginName, @QueryParam("password") String password,
			@QueryParam("profileId") String profileId, @QueryParam("attributes") Map<String, List<String>> attributes, Map<String, List<String>> rolesUsers,
			String templateObjectNameOrFolder) {
		try {
			return ExplorerServiceImpl.getInstance().newDocument(loginName, password, profileId, attributes, rolesUsers, templateObjectNameOrFolder);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/importDocument")
	public String importDocument(@QueryParam("loginName") String loginName, @QueryParam("password") String password,
			@QueryParam("folderRObjectId") String folderRObjectId, @QueryParam("profileId") String profileId,
			@QueryParam("attributes") Map<String, List<String>> attributes, @QueryParam("rolesUsers") Map<String, List<String>> rolesUsers,
			@QueryParam("base64Content") byte[] base64Content, @QueryParam("format") String format) {
		// return ExplorerServiceImpl.getInstance().importDocument(loginName,
		// password, folderRObjectId, profileId, attributes, rolesUsers,
		// base64Content,
		// format);
		try {
			return ExplorerServiceImpl.getInstance().importDocument(loginName, password, folderRObjectId, profileId, attributes, rolesUsers, base64Content,
					format);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}

	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getProfilesForClassSign")
	public List<Profile> getProfilesForClassSign(@QueryParam("loginName") String loginName, @QueryParam("passwordEncrypted") String passwordEncrypted,
			@QueryParam("classSign") String classSign, @QueryParam("wizardType") String wizardType) {
		List<Profile> profiles = AdminServiceImpl.getInstance().getProfilesForClassSign(loginName, passwordEncrypted, classSign, wizardType);
		return profiles;
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

}
