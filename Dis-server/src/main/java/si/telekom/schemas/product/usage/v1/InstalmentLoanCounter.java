
package si.telekom.schemas.product.usage.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.TaxedAmount;


/**
 * <p>Java class for InstalmentLoanCounter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstalmentLoanCounter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Counter">
 *       &lt;sequence>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="total" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="totalNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="paidAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="paid" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="paidNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="firstAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="first" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="lastAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="last" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstalmentLoanCounter", propOrder = {
    "totalAmount",
    "total",
    "totalNumber",
    "paidAmount",
    "paid",
    "paidNumber",
    "firstAmount",
    "first",
    "lastAmount",
    "last"
})
public class InstalmentLoanCounter
    extends Counter
{

    @XmlElement(required = true)
    protected BigDecimal totalAmount;
    protected TaxedAmount total;
    protected int totalNumber;
    @XmlElement(required = true)
    protected BigDecimal paidAmount;
    protected TaxedAmount paid;
    protected int paidNumber;
    protected BigDecimal firstAmount;
    protected TaxedAmount first;
    protected BigDecimal lastAmount;
    protected TaxedAmount last;

    /**
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

    /**
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setTotal(TaxedAmount value) {
        this.total = value;
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
     * Gets the value of the paidAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    /**
     * Sets the value of the paidAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaidAmount(BigDecimal value) {
        this.paidAmount = value;
    }

    /**
     * Gets the value of the paid property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getPaid() {
        return paid;
    }

    /**
     * Sets the value of the paid property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setPaid(TaxedAmount value) {
        this.paid = value;
    }

    /**
     * Gets the value of the paidNumber property.
     * 
     */
    public int getPaidNumber() {
        return paidNumber;
    }

    /**
     * Sets the value of the paidNumber property.
     * 
     */
    public void setPaidNumber(int value) {
        this.paidNumber = value;
    }

    /**
     * Gets the value of the firstAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFirstAmount() {
        return firstAmount;
    }

    /**
     * Sets the value of the firstAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFirstAmount(BigDecimal value) {
        this.firstAmount = value;
    }

    /**
     * Gets the value of the first property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getFirst() {
        return first;
    }

    /**
     * Sets the value of the first property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setFirst(TaxedAmount value) {
        this.first = value;
    }

    /**
     * Gets the value of the lastAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLastAmount() {
        return lastAmount;
    }

    /**
     * Sets the value of the lastAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLastAmount(BigDecimal value) {
        this.lastAmount = value;
    }

    /**
     * Gets the value of the last property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getLast() {
        return last;
    }

    /**
     * Sets the value of the last property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setLast(TaxedAmount value) {
        this.last = value;
    }

}
