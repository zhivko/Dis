package si.telekom.dis.server.rest;

import java.util.List;
import java.util.Map;


public class NewDocumentArg {

	private String loginName;
	private String passwordEncrypted;
	private String profileId;
	private Map<String, List<String>> attributes;
	private Map<String, List<String>> roles;
	private String templateObjectNameOrFolder;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPasswordEncrypted() {
		return passwordEncrypted;
	}

	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public Map<String, List<String>> getAttributes() {
		return attributes;
	}

	public void setAttribute(Map<String, List<String>> attribute) {
		this.attributes = attribute;
	}

	public Map<String, List<String>> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, List<String>> roles) {
		this.roles = roles;
	}

	public String getTemplateObjectNameOrFolder() {
		return templateObjectNameOrFolder;
	}

	public void setTemplateObjectNameOrFolder(String templateObjectNameOrFolder) {
		this.templateObjectNameOrFolder = templateObjectNameOrFolder;
	}
}
