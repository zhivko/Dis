
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for runSmartList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="runSmartList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smartlistRObjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="docbase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="pageIndex" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *         &lt;element name="pageMaxRowCount" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *         &lt;element name="osName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "runSmartList", propOrder = {
    "smartlistRObjectId",
    "docbase",
    "pageIndex",
    "pageMaxRowCount",
    "osName",
    "domain",
    "password"
})
@XmlRootElement(name = "runSmartList")
public class RunSmartList {

    protected String smartlistRObjectId;
    protected String docbase;
    protected int pageIndex;
    protected int pageMaxRowCount;
    protected String osName;
    protected String domain;
    protected String password;

    /**
     * Gets the value of the smartlistRObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmartlistRObjectId() {
        return smartlistRObjectId;
    }

    /**
     * Sets the value of the smartlistRObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmartlistRObjectId(String value) {
        this.smartlistRObjectId = value;
    }

    /**
     * Gets the value of the docbase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocbase() {
        return docbase;
    }

    /**
     * Sets the value of the docbase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocbase(String value) {
        this.docbase = value;
    }

    /**
     * Gets the value of the pageIndex property.
     * 
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * Sets the value of the pageIndex property.
     * 
     */
    public void setPageIndex(int value) {
        this.pageIndex = value;
    }

    /**
     * Gets the value of the pageMaxRowCount property.
     * 
     */
    public int getPageMaxRowCount() {
        return pageMaxRowCount;
    }

    /**
     * Sets the value of the pageMaxRowCount property.
     * 
     */
    public void setPageMaxRowCount(int value) {
        this.pageMaxRowCount = value;
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

}
