
package si.telekom.schemas.product.usage.v1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Tax;


/**
 * <p>Java class for AllowanceCounter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AllowanceCounter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Counter">
 *       &lt;sequence>
 *         &lt;element name="resetValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="resetDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="prorated" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="limitValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="transferLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="isGrossAmount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tax" type="{http://telekom.si/schemas/common/base/v1}Tax" minOccurs="0"/>
 *         &lt;element name="tresholdCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="treshold" type="{http://www.w3.org/2001/XMLSchema}decimal" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="resetEventCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resetEvent" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllowanceCounter", propOrder = {
    "resetValue",
    "resetDate",
    "currentValue",
    "prorated",
    "limitValue",
    "transferLimit",
    "isGrossAmount",
    "tax",
    "tresholdCollection",
    "resetEventCollection"
})
public class AllowanceCounter
    extends Counter
{

    protected BigDecimal resetValue;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resetDate;
    protected BigDecimal currentValue;
    protected CatalogValue prorated;
    protected BigDecimal limitValue;
    protected BigDecimal transferLimit;
    protected Integer isGrossAmount;
    protected Tax tax;
    protected AllowanceCounter.TresholdCollection tresholdCollection;
    protected AllowanceCounter.ResetEventCollection resetEventCollection;

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
     * Gets the value of the currentValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the value of the currentValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentValue(BigDecimal value) {
        this.currentValue = value;
    }

    /**
     * Gets the value of the prorated property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getProrated() {
        return prorated;
    }

    /**
     * Sets the value of the prorated property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setProrated(CatalogValue value) {
        this.prorated = value;
    }

    /**
     * Gets the value of the limitValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLimitValue() {
        return limitValue;
    }

    /**
     * Sets the value of the limitValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLimitValue(BigDecimal value) {
        this.limitValue = value;
    }

    /**
     * Gets the value of the transferLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTransferLimit() {
        return transferLimit;
    }

    /**
     * Sets the value of the transferLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTransferLimit(BigDecimal value) {
        this.transferLimit = value;
    }

    /**
     * Gets the value of the isGrossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsGrossAmount() {
        return isGrossAmount;
    }

    /**
     * Sets the value of the isGrossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsGrossAmount(Integer value) {
        this.isGrossAmount = value;
    }

    /**
     * Gets the value of the tax property.
     * 
     * @return
     *     possible object is
     *     {@link Tax }
     *     
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * Sets the value of the tax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tax }
     *     
     */
    public void setTax(Tax value) {
        this.tax = value;
    }

    /**
     * Gets the value of the tresholdCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AllowanceCounter.TresholdCollection }
     *     
     */
    public AllowanceCounter.TresholdCollection getTresholdCollection() {
        return tresholdCollection;
    }

    /**
     * Sets the value of the tresholdCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowanceCounter.TresholdCollection }
     *     
     */
    public void setTresholdCollection(AllowanceCounter.TresholdCollection value) {
        this.tresholdCollection = value;
    }

    /**
     * Gets the value of the resetEventCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AllowanceCounter.ResetEventCollection }
     *     
     */
    public AllowanceCounter.ResetEventCollection getResetEventCollection() {
        return resetEventCollection;
    }

    /**
     * Sets the value of the resetEventCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowanceCounter.ResetEventCollection }
     *     
     */
    public void setResetEventCollection(AllowanceCounter.ResetEventCollection value) {
        this.resetEventCollection = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="resetEvent" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "resetEvent"
    })
    public static class ResetEventCollection {

        @XmlElement(required = true)
        protected List<CatalogValue> resetEvent;

        /**
         * Gets the value of the resetEvent property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the resetEvent property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getResetEvent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CatalogValue }
         * 
         * 
         */
        public List<CatalogValue> getResetEvent() {
            if (resetEvent == null) {
                resetEvent = new ArrayList<CatalogValue>();
            }
            return this.resetEvent;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="treshold" type="{http://www.w3.org/2001/XMLSchema}decimal" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "treshold"
    })
    public static class TresholdCollection {

        @XmlElement(required = true)
        protected List<BigDecimal> treshold;

        /**
         * Gets the value of the treshold property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the treshold property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTreshold().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BigDecimal }
         * 
         * 
         */
        public List<BigDecimal> getTreshold() {
            if (treshold == null) {
                treshold = new ArrayList<BigDecimal>();
            }
            return this.treshold;
        }

    }

}
