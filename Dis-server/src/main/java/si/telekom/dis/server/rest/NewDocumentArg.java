package si.telekom.dis.server.rest;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
@JsonInclude(Include.ALWAYS)
public class NewDocumentArg {

	private String profileId;
	private Map<String, String[]> attributes;
	private Map<String, String[]> roles;
	private String templateObjectNameOrFolder;

	public NewDocumentArg()
	{
		
	}
	
	@XmlElement(value = "profileId")
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@XmlElement(value="attributes")
	public Map<String, String[]> getAttributes() {
		return attributes;
	}

	public void setAttribute(Map<String, String[]> attribute) {
		this.attributes = attribute;
	}

	@XmlElement(value="rolesUsers")
	public Map<String, String[]> getRoles() {
		return roles;
	}

	public void setRoles(Map<String, String[]> roles) {
		this.roles = roles;
	}

	public String getTemplateObjectNameOrFolder() {
		return templateObjectNameOrFolder;
	}

	public void setTemplateObjectNameOrFolder(String templateObjectNameOrFolder) {
		this.templateObjectNameOrFolder = templateObjectNameOrFolder;
	}
}
