
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DealerUserSalesOU complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DealerUserSalesOU">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesOrganizationalUnit" type="{http://telekom.si/schemas/common/dealer/v1}SalesOrganizationalUnit"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lastActive" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DealerUserSalesOU", propOrder = {
    "salesOrganizationalUnit",
    "priority",
    "lastActive"
})
public class DealerUserSalesOU {

    @XmlElement(required = true)
    protected SalesOrganizationalUnit salesOrganizationalUnit;
    protected Integer priority;
    protected Integer lastActive;

    /**
     * Gets the value of the salesOrganizationalUnit property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrganizationalUnit }
     *     
     */
    public SalesOrganizationalUnit getSalesOrganizationalUnit() {
        return salesOrganizationalUnit;
    }

    /**
     * Sets the value of the salesOrganizationalUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrganizationalUnit }
     *     
     */
    public void setSalesOrganizationalUnit(SalesOrganizationalUnit value) {
        this.salesOrganizationalUnit = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPriority(Integer value) {
        this.priority = value;
    }

    /**
     * Gets the value of the lastActive property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLastActive() {
        return lastActive;
    }

    /**
     * Sets the value of the lastActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLastActive(Integer value) {
        this.lastActive = value;
    }

}
