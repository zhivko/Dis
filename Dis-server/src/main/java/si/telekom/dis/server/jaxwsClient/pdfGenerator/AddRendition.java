
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addRendition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addRendition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rObjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="osName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="docbaseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0" form="qualified"/>
 *         &lt;element name="documentumFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addRendition", propOrder = {
    "rObjectId",
    "osName",
    "domain",
    "password",
    "docbaseName",
    "data",
    "documentumFormat"
})
@XmlRootElement(name = "addRendition")
public class AddRendition {

    protected String rObjectId;
    protected String osName;
    protected String domain;
    protected String password;
    protected String docbaseName;
    @XmlElementRef(name = "data", namespace = "http://templates.mobitel.com/", type = JAXBElement.class)
    protected JAXBElement<byte[]> data;
    protected String documentumFormat;

    /**
     * Gets the value of the rObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRObjectId() {
        return rObjectId;
    }

    /**
     * Sets the value of the rObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRObjectId(String value) {
        this.rObjectId = value;
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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setData(JAXBElement<byte[]> value) {
        this.data = value;
    }

    /**
     * Gets the value of the documentumFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentumFormat() {
        return documentumFormat;
    }

    /**
     * Sets the value of the documentumFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentumFormat(String value) {
        this.documentumFormat = value;
    }

}
