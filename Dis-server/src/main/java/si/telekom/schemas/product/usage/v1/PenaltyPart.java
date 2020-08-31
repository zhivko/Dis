
package si.telekom.schemas.product.usage.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.TaxedAmount;


/**
 * <p>Java class for PenaltyPart complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PenaltyPart">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount"/>
 *         &lt;element name="chargeAmountDiscriminator" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PenaltyPart", propOrder = {
    "amount",
    "chargeAmountDiscriminator"
})
public class PenaltyPart {

    @XmlElement(required = true)
    protected TaxedAmount amount;
    @XmlElement(required = true)
    protected CatalogValue chargeAmountDiscriminator;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setAmount(TaxedAmount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the chargeAmountDiscriminator property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getChargeAmountDiscriminator() {
        return chargeAmountDiscriminator;
    }

    /**
     * Sets the value of the chargeAmountDiscriminator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setChargeAmountDiscriminator(CatalogValue value) {
        this.chargeAmountDiscriminator = value;
    }

}
