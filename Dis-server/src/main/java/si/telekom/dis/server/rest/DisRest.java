package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.binary.Base64;

import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.shared.Profile;

// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/application.wadl?detail=true
// http://localhost:8080/Dis-server/rest/disRest/importDocument



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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getSampleNewDocumentArg")
	// public String newDocument(@QueryParam("loginName") String loginName,
	// @QueryParam("passwordEncrypted") String passwordEncrypted,
	// @QueryParam("profileId") String profileId, @BeanParam HashMap<String,
	// ArrayList<String>> attributes, @BeanParam HashMap<String,
	// ArrayList<String>> roles,
	// @QueryParam("templateObjectNameOrFolder") String
	// templateObjectNameOrFolder) {
	public NewDocumentArg getSampleNewDocumentArg(@Context SecurityContext sc) {
		NewDocumentArg ndArg = new NewDocumentArg();

		ndArg.setProfileId("test");

		HashMap<String, String[]> attributes = new HashMap<String, String[]>();
		String[] vals = { "v1", "v2", "v3" };
		attributes.put("a1", vals);
		ndArg.setAttribute(attributes);

		HashMap<String, String[]> roles = new HashMap<String, String[]>();
		String[] valsRoles = { "r1", "r2", "r3" };
		roles.put("r1", valsRoles);
		ndArg.setRoles(roles);

		ndArg.setTemplateObjectNameOrFolder("");

		return ndArg;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/newDocument")
	// public String newDocument(@QueryParam("loginName") String loginName,
	// @QueryParam("passwordEncrypted") String passwordEncrypted,
	// @QueryParam("profileId") String profileId, @BeanParam HashMap<String,
	// ArrayList<String>> attributes, @BeanParam HashMap<String,
	// ArrayList<String>> roles,
	// @QueryParam("templateObjectNameOrFolder") String
	// templateObjectNameOrFolder) {
	public String newDocument(@Context SecurityContext sc, NewDocumentArg newDocumentArg) {
		try {

			Map<String, List<String>> attributes_ = new HashMap<String, List<String>>();
			for (String attName : newDocumentArg.getAttributes().keySet()) {
				ArrayList<String> values = new ArrayList<String>();
				for (String value : newDocumentArg.getAttributes().get(attName)) {
					values.add(value);
				}
				attributes_.put(attName, values);
			}

			Map<String, List<String>> roles_ = new HashMap<String, List<String>>();
			for (String roleName : newDocumentArg.getRoles().keySet()) {
				ArrayList<String> values = new ArrayList<String>();
				for (String value : newDocumentArg.getRoles().get(roleName)) {
					values.add(value);
				}
				roles_.put(roleName, values);
			}

			User user = (User) sc.getUserPrincipal();

// @formatter:off
			return ExplorerServiceImpl.getInstance().newDocument(
					user.getId(), 
					Base64.encodeBase64String(user.getPassword().getBytes()),
					newDocumentArg.getProfileId(), 
					attributes_, 
					roles_, 
					newDocumentArg.getTemplateObjectNameOrFolder()
			);
// @formatter:on
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
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
	public String importDocument(@Context SecurityContext sc, ImportDocumentArg importDocumentArg) {
		try {

			Map<String, List<String>> attributes_ = new HashMap<String, List<String>>();
			for (String attName : importDocumentArg.getAttributes().keySet()) {
				ArrayList<String> values = new ArrayList<String>();
				for (String value : importDocumentArg.getAttributes().get(attName)) {
					values.add(value);
				}
				attributes_.put(attName, values);
			}

			Map<String, List<String>> roles_ = new HashMap<String, List<String>>();
			for (String roleName : importDocumentArg.getRoles().keySet()) {
				ArrayList<String> values = new ArrayList<String>();
				for (String value : importDocumentArg.getRoles().get(roleName)) {
					values.add(value);
				}
				roles_.put(roleName, values);
			}

			User user = (User) sc.getUserPrincipal();

// @formatter:off
			return ExplorerServiceImpl.getInstance().importDocument(
					user.getId(), 
					Base64.encodeBase64String(user.getPassword().getBytes()),
					importDocumentArg.getFolderId(), 
					importDocumentArg.getProfileId(),
					importDocumentArg.getStateId(), 
					attributes_, 
					roles_, 
					importDocumentArg.getContentBase64().getBytes(),
					importDocumentArg.getFormat());
// @formatter:on

		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getProfilesForClassSign")
	public List<String> getProfilesForClassSign(@Context SecurityContext sc,
			@QueryParam("classSign") String classSign, @QueryParam("wizzardType") String wizardType) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			User user = (User) sc.getUserPrincipal();
			
			List<Profile> profiles = AdminServiceImpl.getInstance().getProfilesForClassSign(
					
					user.getId(), 
					Base64.encodeBase64String(user.getPassword().getBytes()),

					classSign, wizardType);
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

}
