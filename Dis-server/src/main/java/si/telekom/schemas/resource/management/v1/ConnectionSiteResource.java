
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConnectionSiteResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectionSiteResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="rmConnectionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accessId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pathName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pathCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="midA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="midZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="microlocationA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="microlocationZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectionSiteResource", propOrder = {
    "rmConnectionId",
    "accessId",
    "pathName",
    "pathCategory",
    "midA",
    "midZ",
    "microlocationA",
    "microlocationZ"
})
public class ConnectionSiteResource
    extends Resource
{

    protected String rmConnectionId;
    protected String accessId;
    protected String pathName;
    protected String pathCategory;
    protected String midA;
    protected String midZ;
    protected String microlocationA;
    protected String microlocationZ;

    /**
     * Gets the value of the rmConnectionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRmConnectionId() {
        return rmConnectionId;
    }

    /**
     * Sets the value of the rmConnectionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRmConnectionId(String value) {
        this.rmConnectionId = value;
    }

    /**
     * Gets the value of the accessId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessId() {
        return accessId;
    }

    /**
     * Sets the value of the accessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessId(String value) {
        this.accessId = value;
    }

    /**
     * Gets the value of the pathName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * Sets the value of the pathName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathName(String value) {
        this.pathName = value;
    }

    /**
     * Gets the value of the pathCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPathCategory() {
        return pathCategory;
    }

    /**
     * Sets the value of the pathCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPathCategory(String value) {
        this.pathCategory = value;
    }

    /**
     * Gets the value of the midA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMidA() {
        return midA;
    }

    /**
     * Sets the value of the midA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMidA(String value) {
        this.midA = value;
    }

    /**
     * Gets the value of the midZ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMidZ() {
        return midZ;
    }

    /**
     * Sets the value of the midZ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMidZ(String value) {
        this.midZ = value;
    }

    /**
     * Gets the value of the microlocationA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicrolocationA() {
        return microlocationA;
    }

    /**
     * Sets the value of the microlocationA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicrolocationA(String value) {
        this.microlocationA = value;
    }

    /**
     * Gets the value of the microlocationZ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMicrolocationZ() {
        return microlocationZ;
    }

    /**
     * Sets the value of the microlocationZ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMicrolocationZ(String value) {
        this.microlocationZ = value;
    }

}
