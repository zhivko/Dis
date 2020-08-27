
package si.telekom.schemas.product.usage.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LogicalCounter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LogicalCounter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/usage/v1}Counter">
 *       &lt;sequence>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="componentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueCollection" type="{http://telekom.si/schemas/product/usage/v1}LogicalCounterValueCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogicalCounter", propOrder = {
    "productId",
    "componentId",
    "valueCollection"
})
public class LogicalCounter
    extends Counter
{

    @XmlElement(required = true)
    protected String productId;
    protected String componentId;
    protected LogicalCounterValueCollection valueCollection;

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
    }

    /**
     * Gets the value of the componentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComponentId() {
        return componentId;
    }

    /**
     * Sets the value of the componentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComponentId(String value) {
        this.componentId = value;
    }

    /**
     * Gets the value of the valueCollection property.
     * 
     * @return
     *     possible object is
     *     {@link LogicalCounterValueCollection }
     *     
     */
    public LogicalCounterValueCollection getValueCollection() {
        return valueCollection;
    }

    /**
     * Sets the value of the valueCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogicalCounterValueCollection }
     *     
     */
    public void setValueCollection(LogicalCounterValueCollection value) {
        this.valueCollection = value;
    }

}
