
package si.telekom.schemas.product.usage.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for Counter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Counter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Usage">
 *       &lt;sequence>
 *         &lt;element name="valueType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *         &lt;element name="accumulatorSpecification" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Counter", propOrder = {
    "valueType",
    "accumulatorSpecification"
})
@XmlSeeAlso({
    LogicalCounter.class,
    AllowanceCounter.class,
    InstalmentLoanCounter.class,
    UsageProfileCounter.class,
    CreditLimitCounter.class
})
public class Counter
    extends Usage
{

    @XmlElement(required = true)
    protected CatalogValue valueType;
    protected CatalogValue accumulatorSpecification;

    /**
     * Gets the value of the valueType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getValueType() {
        return valueType;
    }

    /**
     * Sets the value of the valueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setValueType(CatalogValue value) {
        this.valueType = value;
    }

    /**
     * Gets the value of the accumulatorSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getAccumulatorSpecification() {
        return accumulatorSpecification;
    }

    /**
     * Sets the value of the accumulatorSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setAccumulatorSpecification(CatalogValue value) {
        this.accumulatorSpecification = value;
    }

}
