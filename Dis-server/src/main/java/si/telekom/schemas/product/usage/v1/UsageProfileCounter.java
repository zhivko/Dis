
package si.telekom.schemas.product.usage.v1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for UsageProfileCounter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UsageProfileCounter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Counter">
 *       &lt;sequence>
 *         &lt;element name="currentValue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="period" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
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
@XmlType(name = "UsageProfileCounter", propOrder = {
    "currentValue",
    "period",
    "type",
    "resetEventCollection"
})
public class UsageProfileCounter
    extends Counter
{

    @XmlElement(required = true)
    protected BigDecimal currentValue;
    @XmlElement(required = true)
    protected String period;
    @XmlElement(required = true)
    protected CatalogValue type;
    protected UsageProfileCounter.ResetEventCollection resetEventCollection;

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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setType(CatalogValue value) {
        this.type = value;
    }

    /**
     * Gets the value of the resetEventCollection property.
     * 
     * @return
     *     possible object is
     *     {@link UsageProfileCounter.ResetEventCollection }
     *     
     */
    public UsageProfileCounter.ResetEventCollection getResetEventCollection() {
        return resetEventCollection;
    }

    /**
     * Sets the value of the resetEventCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageProfileCounter.ResetEventCollection }
     *     
     */
    public void setResetEventCollection(UsageProfileCounter.ResetEventCollection value) {
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

}
