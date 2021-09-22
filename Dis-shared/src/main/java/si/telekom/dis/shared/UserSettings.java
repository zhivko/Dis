package si.telekom.dis.shared;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


//@JacksonXmlRootElement(localName = "userSettings")
@XmlRootElement(name="userSettings")
public class UserSettings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserSettings() {
	}

	// @JacksonXmlElementWrapper(localName = "explorer")
	// @JacksonXmlProperty(localName = "explorerReturnResultCount")
	
	@XmlElement(name="explorerReturnResultCount")
	public int explorerReturnResultCount;

	// @JacksonXmlElementWrapper(localName = "search")
	// @JacksonXmlProperty(localName = "searchReturnResultCount")
	@XmlElement(name="searchReturnResultCount")
	public int searchReturnResultCount;
	
	@XmlElement(name="auditTrailPerPageCount")
	public int auditTrailPerPageCount;

	@XmlElement(name="useColaboraOnlineForEdit")
	public boolean useColaboraOnlineForEdit;
	
}
