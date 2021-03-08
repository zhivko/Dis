
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getFormatsFromDosExtension complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFormatsFromDosExtension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dosExtension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="osName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="pasword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="docbaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFormatsFromDosExtension", propOrder = {
    "dosExtension",
    "osName",
    "domain",
    "pasword",
    "docbaseName"
})
@XmlRootElement(name = "getFormatsFromDosExtension")
public class GetFormatsFromDosExtension {

    protected String dosExtension;
    protected String osName;
    protected String domain;
    protected String pasword;
    protected String docbaseName;

    /**
     * Gets the value of the dosExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosExtension() {
        return dosExtension;
    }

    /**
     * Sets the value of the dosExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosExtension(String value) {
        this.dosExtension = value;
    }

    /**
     * Gets the value of the osName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsName() {
        return osName;
    }

    /**
     * Sets the value of the osName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsName(String value) {
        this.osName = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the pasword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasword() {
        return pasword;
    }

    /**
     * Sets the value of the pasword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasword(String value) {
        this.pasword = value;
    }

    /**
     * Gets the value of the docbaseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocbaseName() {
        return docbaseName;
    }

    /**
     * Sets the value of the docbaseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocbaseName(String value) {
        this.docbaseName = value;
    }

}
