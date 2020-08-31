
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * <p>Java class for SalesOrganizationalUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOrganizationalUnit">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="posId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="posName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sapStorageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="managingOrganizationalUnit" type="{http://telekom.si/schemas/common/dealer/v1}SalesOrganizationalUnit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOrganizationalUnit", propOrder = {
    "name",
    "posId",
    "posName",
    "sapStorageId",
    "managingOrganizationalUnit"
})
public class SalesOrganizationalUnit
    extends Entity
{

    protected String name;
    protected String posId;
    protected String posName;
    protected String sapStorageId;
    protected SalesOrganizationalUnit managingOrganizationalUnit;

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
     * Gets the value of the posId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosId() {
        return posId;
    }

    /**
     * Sets the value of the posId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosId(String value) {
        this.posId = value;
    }

    /**
     * Gets the value of the posName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosName() {
        return posName;
    }

    /**
     * Sets the value of the posName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosName(String value) {
        this.posName = value;
    }

    /**
     * Gets the value of the sapStorageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapStorageId() {
        return sapStorageId;
    }

    /**
     * Sets the value of the sapStorageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapStorageId(String value) {
        this.sapStorageId = value;
    }

    /**
     * Gets the value of the managingOrganizationalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrganizationalUnit }
     *     
     */
    public SalesOrganizationalUnit getManagingOrganizationalUnit() {
        return managingOrganizationalUnit;
    }

    /**
     * Sets the value of the managingOrganizationalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrganizationalUnit }
     *     
     */
    public void setManagingOrganizationalUnit(SalesOrganizationalUnit value) {
        this.managingOrganizationalUnit = value;
    }

}
