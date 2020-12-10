
package si.telekom.dis.server.jaxwsClient.eRender;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for grant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="grant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rObjectIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *         &lt;element name="accessorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="basicPermit" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *         &lt;element name="extPermit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="osName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="domain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="docBase" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grant", propOrder = {
    "rObjectIds",
    "accessorName",
    "basicPermit",
    "extPermit",
    "osName",
    "password",
    "domain",
    "docBase"
})
@XmlRootElement(name = "grant")
public class Grant {

    @XmlElement(nillable = true)
    protected List<String> rObjectIds;
    protected String accessorName;
    protected int basicPermit;
    protected String extPermit;
    protected String osName;
    protected String password;
    protected String domain;
    protected String docBase;

    /**
     * Gets the value of the rObjectIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rObjectIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRObjectIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRObjectIds() {
        if (rObjectIds == null) {
            rObjectIds = new ArrayList<String>();
        }
        return this.rObjectIds;
    }

    /**
     * Gets the value of the accessorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessorName() {
        return accessorName;
    }

    /**
     * Sets the value of the accessorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessorName(String value) {
        this.accessorName = value;
    }

    /**
     * Gets the value of the basicPermit property.
     * 
     */
    public int getBasicPermit() {
        return basicPermit;
    }

    /**
     * Sets the value of the basicPermit property.
     * 
     */
    public void setBasicPermit(int value) {
        this.basicPermit = value;
    }

    /**
     * Gets the value of the extPermit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtPermit() {
        return extPermit;
    }

    /**
     * Sets the value of the extPermit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtPermit(String value) {
        this.extPermit = value;
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
     * Gets the value of the docBase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocBase() {
        return docBase;
    }

    /**
     * Sets the value of the docBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocBase(String value) {
        this.docBase = value;
    }

}
