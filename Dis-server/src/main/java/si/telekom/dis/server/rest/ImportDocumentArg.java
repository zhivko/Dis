package si.telekom.dis.server.rest;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
@JsonInclude(Include.ALWAYS)
public class ImportDocumentArg {

	private String profileId;
	private String folderId;
	private Map<String, String[]> attributes;
	private Map<String, String[]> rolesUsers;
	private String contentBase64;
	private String format;
	private String stateId;
	
	public ImportDocumentArg()
	{
		
	}
	
	@XmlElement(value = "profileId")
	public String getProfileId() {
		return profileId;
	}
	
	
	@XmlElement(value = "stateId")
	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}	

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	
	@XmlElement(value="folderId")
	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
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
		return rolesUsers;
	}

	public void setRoles(Map<String, String[]> rolesUsers) {
		this.rolesUsers = rolesUsers;
	}

	@XmlElement(value="contentBase64")
	public String getContentBase64() {
		return contentBase64;
	}

	public void setContentBase64(String contentBase64) {
		this.contentBase64 = contentBase64;
	}

	@XmlElement(value="format")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
