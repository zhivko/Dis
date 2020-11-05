package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Named;
import javax.ws.rs.BeanParam;
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
	@Consumes( MediaType.APPLICATION_JSON )
	@Produces( MediaType.APPLICATION_JSON )
	@Path("/newDocument")
//	public String newDocument(@QueryParam("loginName") String loginName, @QueryParam("passwordEncrypted") String passwordEncrypted,
//			@QueryParam("profileId") String profileId, @BeanParam HashMap<String, ArrayList<String>> attributes, @BeanParam HashMap<String, ArrayList<String>> roles,
//			@QueryParam("templateObjectNameOrFolder") String templateObjectNameOrFolder) {
public String newDocument(NewDocumentArg newDocumentArg) {
		try {

//			HashMap<String, List<String>> vals = new HashMap<String, List<String>>();
//			for (AttValue attValue : attributes.attValueList) {
//				vals.put(attValue.attName, attValue.values);
//			}
//
//			HashMap<String, List<String>> rolesUsers = new HashMap<String, List<String>>();
//			for (RoleValue roleValue : roles.roleValueList) {
//				rolesUsers.put(roleValue.roleName, roleValue.values);
//			}
			
//			Map<String, List<String>> attributes_ = new HashMap<String, List<String>>();
//			for (String attName :  attributes.keySet()) {
//				ArrayList<String> values = new ArrayList<String>();
//				for (String value : attributes.get(attName)) {
//					values.add(value);
//				}
//				attributes_.put(attName, values);
//			}
//
//			
//			Map<String, List<String>> roles_ = new HashMap<String, List<String>>();
//			for (String roleName :  attributes.keySet()) {
//				ArrayList<String> values = new ArrayList<String>();
//				for (String value : roles.get(roleName)) {
//					values.add(value);
//				}
//				roles_.put(roleName, values);
//			}


			return ExplorerServiceImpl.getInstance().newDocument(newDocumentArg.getLoginName(), newDocumentArg.getPasswordEncrypted(), newDocumentArg.getProfileId(), newDocumentArg.getAttributes(), newDocumentArg.getRoles(), newDocumentArg.getTemplateObjectNameOrFolder());
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/importDocument")
	public String importDocument(@QueryParam("loginName") String loginName, @QueryParam("encryptedPassword") String encryptedPassword,
			@QueryParam("folderRObjectId") String folderRObjectId, @QueryParam("profileId") String profileId, @BeanParam AttValueList attributes,
			@BeanParam RoleValueList roles, @QueryParam("base64content") String base64Content, @QueryParam("format") String format) {
		try {
			HashMap<String, List<String>> vals_ = new HashMap<String, List<String>>();
			for (AttValue attValue : attributes.attValueList) {
				vals_.put(attValue.attName, attValue.values);
			}

			HashMap<String, List<String>> rolesUsers_ = new HashMap<String, List<String>>();
			for (RoleValue roleValue : roles.roleValueList) {
				rolesUsers_.put(roleValue.roleName, roleValue.values);
			}
			byte[] base64Content_ = base64Content.getBytes();
			return ExplorerServiceImpl.getInstance().importDocument(loginName, encryptedPassword, folderRObjectId, profileId, vals_, rolesUsers_,
					base64Content_, format);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getProfilesForClassSign")
	public List<String> getProfilesForClassSign(@QueryParam("loginName") String loginName, @QueryParam("passwordEncrypted") String passwordEncrypted,
			@QueryParam("classSign") String classSign, @QueryParam("wizzardType") String wizardType) {
		ArrayList<String> ret = new ArrayList<String>();
		try {
			List<Profile> profiles = AdminServiceImpl.getInstance().getProfilesForClassSign(loginName, passwordEncrypted, classSign, wizardType);
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
