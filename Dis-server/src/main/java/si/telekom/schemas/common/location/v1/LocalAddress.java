
package si.telekom.schemas.common.location.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Address within a property in an urban area. It may refer to a building, a building cluster, or a floor of a multistory building
 * 
 * <p>Java class for LocalAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocalAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="formattedLocalAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subUnitType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subUnitNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="buildingName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="privateStreetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "LocalAddress", propOrder = {
    "formattedLocalAddress",
    "name",
    "subUnitType",
    "subUnitNr",
    "level",
    "buildingName",
    "privateStreetName",
    "description"
})
public class LocalAddress
    extends Entity
{

    protected String formattedLocalAddress;
    protected String name;
    protected String subUnitType;
    protected String subUnitNr;
    protected String level;
    protected String buildingName;
    protected String privateStreetName;
    protected String description;

    /**
     * Gets the value of the formattedLocalAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormattedLocalAddress() {
        return formattedLocalAddress;
    }

    /**
     * Sets the value of the formattedLocalAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormattedLocalAddress(String value) {
        this.formattedLocalAddress = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the subUnitType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubUnitType() {
        return subUnitType;
    }

    /**
     * Sets the value of the subUnitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubUnitType(String value) {
        this.subUnitType = value;
    }

    /**
     * Gets the value of the subUnitNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubUnitNr() {
        return subUnitNr;
    }

    /**
     * Sets the value of the subUnitNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubUnitNr(String value) {
        this.subUnitNr = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the buildingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * Sets the value of the buildingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingName(String value) {
        this.buildingName = value;
    }

    /**
     * Gets the value of the privateStreetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrivateStreetName() {
        return privateStreetName;
    }

    /**
     * Sets the value of the privateStreetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrivateStreetName(String value) {
        this.privateStreetName = value;
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
