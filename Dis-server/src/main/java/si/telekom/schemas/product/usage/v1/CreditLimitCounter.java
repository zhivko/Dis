
package si.telekom.schemas.product.usage.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.TaxedAmount;


/**
 * <p>Java class for CreditLimitCounter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditLimitCounter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Counter">
 *       &lt;sequence>
 *         &lt;element name="isDefault" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="resetValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="resetAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="balanceAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resetDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="isOverspendAllowed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditLimitCounter", propOrder = {
    "isDefault",
    "resetValue",
    "resetAmount",
    "balance",
    "balanceAmount",
    "period",
    "resetDate",
    "isOverspendAllowed"
})
public class CreditLimitCounter
    extends Counter
{

    protected Integer isDefault;
    protected BigDecimal resetValue;
    protected TaxedAmount resetAmount;
    protected BigDecimal balance;
    protected TaxedAmount balanceAmount;
    protected String period;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar resetDate;
    protected Integer isOverspendAllowed;

    /**
     * Gets the value of the isDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsDefault(Integer value) {
        this.isDefault = value;
    }

    /**
     * Gets the value of the resetValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getResetValue() {
        return resetValue;
    }

    /**
     * Sets the value of the resetValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setResetValue(BigDecimal value) {
        this.resetValue = value;
    }

    /**
     * Gets the value of the resetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getResetAmount() {
        return resetAmount;
    }

    /**
     * Sets the value of the resetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setResetAmount(TaxedAmount value) {
        this.resetAmount = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

    /**
     * Gets the value of the balanceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * Sets the value of the balanceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setBalanceAmount(TaxedAmount value) {
        this.balanceAmount = value;
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

    /**
     * Gets the value of the resetDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResetDate() {
        return resetDate;
    }

    /**
     * Sets the value of the resetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResetDate(XMLGregorianCalendar value) {
        this.resetDate = value;
    }

    /**
     * Gets the value of the isOverspendAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsOverspendAllowed() {
        return isOverspendAllowed;
    }

    /**
     * Sets the value of the isOverspendAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsOverspendAllowed(Integer value) {
        this.isOverspendAllowed = value;
    }

}
