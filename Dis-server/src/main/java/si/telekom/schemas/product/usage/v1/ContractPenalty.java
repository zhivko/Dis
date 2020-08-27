
package si.telekom.schemas.product.usage.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.TaxedAmount;


/**
 * <p>Java class for ContractPenalty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContractPenalty">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Usage">
 *       &lt;sequence>
 *         &lt;element name="penalty" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="penaltyAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="penaltyBaseAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="penaltyParts" type="{http://telekom.si/schemas/product/usage/v1}PenaltyPartCollection" minOccurs="0"/>
 *         &lt;element name="totalNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remainingNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContractPenalty", propOrder = {
    "penalty",
    "penaltyAmount",
    "penaltyBaseAmount",
    "penaltyParts",
    "totalNumber",
    "remainingNumber",
    "period"
})
public class ContractPenalty
    extends Usage
{

    protected BigDecimal penalty;
    protected TaxedAmount penaltyAmount;
    protected TaxedAmount penaltyBaseAmount;
    protected PenaltyPartCollection penaltyParts;
    protected int totalNumber;
    protected int remainingNumber;
    @XmlElement(required = true)
    protected String period;

    /**
     * Gets the value of the penalty property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPenalty() {
        return penalty;
    }

    /**
     * Sets the value of the penalty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPenalty(BigDecimal value) {
        this.penalty = value;
    }

    /**
     * Gets the value of the penaltyAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getPenaltyAmount() {
        return penaltyAmount;
    }

    /**
     * Sets the value of the penaltyAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setPenaltyAmount(TaxedAmount value) {
        this.penaltyAmount = value;
    }

    /**
     * Gets the value of the penaltyBaseAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getPenaltyBaseAmount() {
        return penaltyBaseAmount;
    }

    /**
     * Sets the value of the penaltyBaseAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setPenaltyBaseAmount(TaxedAmount value) {
        this.penaltyBaseAmount = value;
    }

    /**
     * Gets the value of the penaltyParts property.
     * 
     * @return
     *     possible object is
     *     {@link PenaltyPartCollection }
     *     
     */
    public PenaltyPartCollection getPenaltyParts() {
        return penaltyParts;
    }

    /**
     * Sets the value of the penaltyParts property.
     * 
     * @param value
     *     allowed object is
     *     {@link PenaltyPartCollection }
     *     
     */
    public void setPenaltyParts(PenaltyPartCollection value) {
        this.penaltyParts = value;
    }

    /**
     * Gets the value of the totalNumber property.
     * 
     */
    public int getTotalNumber() {
        return totalNumber;
    }

    /**
     * Sets the value of the totalNumber property.
     * 
     */
    public void setTotalNumber(int value) {
        this.totalNumber = value;
    }

    /**
     * Gets the value of the remainingNumber property.
     * 
     */
    public int getRemainingNumber() {
        return remainingNumber;
    }

    /**
     * Sets the value of the remainingNumber property.
     * 
     */
    public void setRemainingNumber(int value) {
        this.remainingNumber = value;
    }

    /**
     * Gets the value of the period property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the value of the period property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriod(String value) {
        this.period = value;
    }

}
