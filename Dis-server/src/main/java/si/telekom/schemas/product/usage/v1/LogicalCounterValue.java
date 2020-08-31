
package si.telekom.schemas.product.usage.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LogicalCounterValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LogicalCounterValue">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="periodOffset" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isTransferred" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogicalCounterValue", propOrder = {
    "value",
    "periodOffset",
    "isTransferred"
})
public class LogicalCounterValue {

    @XmlElement(required = true)
    protected BigDecimal value;
    protected int periodOffset;
    protected boolean isTransferred;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the periodOffset property.
     * 
     */
    public int getPeriodOffset() {
        return periodOffset;
    }

    /**
     * Sets the value of the periodOffset property.
     * 
     */
    public void setPeriodOffset(int value) {
        this.periodOffset = value;
    }

    /**
     * Gets the value of the isTransferred property.
     * 
     */
    public boolean isIsTransferred() {
        return isTransferred;
    }

    /**
     * Sets the value of the isTransferred property.
     * 
     */
    public void setIsTransferred(boolean value) {
        this.isTransferred = value;
    }

}
