
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.InvolvmentRole;


/**
 * <p>Java class for DealerInvolvmentRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DealerInvolvmentRole">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}InvolvmentRole">
 *       &lt;sequence>
 *         &lt;element name="dealer" type="{http://telekom.si/schemas/common/dealer/v1}Dealer"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DealerInvolvmentRole", propOrder = {
    "dealer"
})
public class DealerInvolvmentRole
    extends InvolvmentRole
{

    @XmlElement(required = true)
    protected Dealer dealer;

    /**
     * Gets the value of the dealer property.
     * 
     * @return
     *     possible object is
     *     {@link Dealer }
     *     
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Sets the value of the dealer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dealer }
     *     
     */
    public void setDealer(Dealer value) {
        this.dealer = value;
    }

}
