package si.telekom.dis.server.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<List<String>> dqlLookup(@BeanParam String loginName, @BeanParam String passwordEncrypted,
			@BeanParam String dql) throws Exception {
		// return ExplorerServiceImpl.getInstance().dqlLookup(loginName,
		// passwordEncrypted, dql);
		try {
			return ExplorerServiceImpl.getInstance().dqlLookup(loginName, passwordEncrypted, dql);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes({ "text/xml", "application/json" })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/newDocument")
	public String newDocument(@BeanParam String loginName, @BeanParam String password,
			@BeanParam String profileId, @BeanParam AttValueList attributes, @BeanParam RoleValueList roles,
			String templateObjectNameOrFolder) {
		try {

			HashMap<String, List<String>> vals = new HashMap<String, List<String>>();
			for (AttValue attValue : attributes.attValueList) {
				vals.put(attValue.attName, attValue.values);
			}

			HashMap<String, List<String>> rolesUsers = new HashMap<String, List<String>>();
			for (RoleValue roleValue : roles.roleValueList) {
				rolesUsers.put(roleValue.roleName, roleValue.values);
			}

			return ExplorerServiceImpl.getInstance().newDocument(loginName, password, profileId, vals, rolesUsers, templateObjectNameOrFolder);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET

	@Consumes(MediaType.TEXT_HTML)

	@Produces({ MediaType.APPLICATION_JSON })

	@Path("/importDocument")
	public String importDocument(@BeanParam String loginName, @BeanParam String encryptedPassword,
			@BeanParam String folderRObjectId, @BeanParam String profileId, @BeanParam AttValueList attributes,
			@BeanParam RoleValueList roles, @BeanParam String base64Content, @BeanParam String format) {
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
			return ExplorerServiceImpl.getInstance().importDocument(loginName, encryptedPassword, folderRObjectId, profileId, vals_, rolesUsers_, base64Content_,
					format);
		} catch (Exception ex) {
			throw new WebApplicationException(ex.getMessage());
		}
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/getProfilesForClassSign")
	public List<Profile> getProfilesForClassSign(@BeanParam String loginName, @BeanParam String passwordEncrypted,
			@BeanParam String classSign,@BeanParam String wizardType) {
		List<Profile> profiles = AdminServiceImpl.getInstance().getProfilesForClassSign(loginName, passwordEncrypted, classSign, wizardType);
		return profiles;
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/promote")
	public Void promote(@BeanParam String loginName, @BeanParam String passwordEncrypted,
			@QueryParam("r_object_id") String r_object_id) throws Exception {
		return ExplorerServiceImpl.getInstance().promote(loginName, passwordEncrypted, r_object_id);
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/demote")
	public Void demote(@BeanParam String loginName, @BeanParam String passwordEncrypted,
			@BeanParam String r_object_id) throws Exception {
		return ExplorerServiceImpl.getInstance().demote(loginName, passwordEncrypted, r_object_id);
	}

}
