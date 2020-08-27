
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IPSubRangeResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IPSubRangeResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}IPResource">
 *       &lt;sequence>
 *         &lt;element name="rangeStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangeStartHex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangeEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangeEndHex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cidr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="regRangeStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentSubRangeStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPSubRangeResource", propOrder = {
    "rangeStart",
    "rangeStartHex",
    "rangeEnd",
    "rangeEndHex",
    "cidr",
    "regRangeStart",
    "parentSubRangeStart",
    "description"
})
public class IPSubRangeResource
    extends IPResource
{

    protected String rangeStart;
    protected String rangeStartHex;
    protected String rangeEnd;
    protected String rangeEndHex;
    protected String cidr;
    protected String regRangeStart;
    protected String parentSubRangeStart;
    protected String description;

    /**
     * Gets the value of the rangeStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangeStart() {
        return rangeStart;
    }

    /**
     * Sets the value of the rangeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangeStart(String value) {
        this.rangeStart = value;
    }

    /**
     * Gets the value of the rangeStartHex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangeStartHex() {
        return rangeStartHex;
    }

    /**
     * Sets the value of the rangeStartHex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangeStartHex(String value) {
        this.rangeStartHex = value;
    }

    /**
     * Gets the value of the rangeEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangeEnd() {
        return rangeEnd;
    }

    /**
     * Sets the value of the rangeEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangeEnd(String value) {
        this.rangeEnd = value;
    }

    /**
     * Gets the value of the rangeEndHex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRangeEndHex() {
        return rangeEndHex;
    }

    /**
     * Sets the value of the rangeEndHex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRangeEndHex(String value) {
        this.rangeEndHex = value;
    }

    /**
     * Gets the value of the cidr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidr() {
        return cidr;
    }

    /**
     * Sets the value of the cidr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidr(String value) {
        this.cidr = value;
    }

    /**
     * Gets the value of the regRangeStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegRangeStart() {
        return regRangeStart;
    }

    /**
     * Sets the value of the regRangeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegRangeStart(String value) {
        this.regRangeStart = value;
    }

    /**
     * Gets the value of the parentSubRangeStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentSubRangeStart() {
        return parentSubRangeStart;
    }

    /**
     * Sets the value of the parentSubRangeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentSubRangeStart(String value) {
        this.parentSubRangeStart = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
