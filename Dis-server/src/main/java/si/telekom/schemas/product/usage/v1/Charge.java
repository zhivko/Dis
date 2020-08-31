
package si.telekom.schemas.product.usage.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Money;
import si.telekom.schemas.common.base.v1.TaxedAmount;


/**
 * <p>Java class for Charge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Charge">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Usage">
 *       &lt;sequence>
 *         &lt;element name="priceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="discriminatorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="priceAmountId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://telekom.si/schemas/common/base/v1}Money" minOccurs="0"/>
 *         &lt;element name="chargeAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Charge", propOrder = {
    "priceId",
    "discriminatorId",
    "priceAmountId",
    "amount",
    "chargeAmount"
})
public class Charge
    extends Usage
{

    protected Long priceId;
    protected Long discriminatorId;
    protected Long priceAmountId;
    protected Money amount;
    protected TaxedAmount chargeAmount;

    /**
     * Gets the value of the priceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPriceId() {
        return priceId;
    }

    /**
     * Sets the value of the priceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPriceId(Long value) {
        this.priceId = value;
    }

    /**
     * Gets the value of the discriminatorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDiscriminatorId() {
        return discriminatorId;
    }

    /**
     * Sets the value of the discriminatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDiscriminatorId(Long value) {
        this.discriminatorId = value;
    }

    /**
     * Gets the value of the priceAmountId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPriceAmountId() {
        return priceAmountId;
    }

    /**
     * Sets the value of the priceAmountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPriceAmountId(Long value) {
        this.priceAmountId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setAmount(Money value) {
        this.amount = value;
    }

    /**
     * Gets the value of the chargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getChargeAmount() {
        return chargeAmount;
    }

    /**
     * Sets the value of the chargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setChargeAmount(TaxedAmount value) {
        this.chargeAmount = value;
    }

}
