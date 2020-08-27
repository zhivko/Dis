
package si.telekom.schemas.product.usage.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LogicalCounterValueCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LogicalCounterValueCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logicalCounterValue" type="{http://telekom.si/schemas/product/usage/v1}LogicalCounterValue" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LogicalCounterValueCollection", propOrder = {
    "logicalCounterValue"
})
public class LogicalCounterValueCollection {

    @XmlElement(required = true)
    protected List<LogicalCounterValue> logicalCounterValue;

    /**
     * Gets the value of the logicalCounterValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the logicalCounterValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogicalCounterValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LogicalCounterValue }
     * 
     * 
     */
    public List<LogicalCounterValue> getLogicalCounterValue() {
        if (logicalCounterValue == null) {
            logicalCounterValue = new ArrayList<LogicalCounterValue>();
        }
        return this.logicalCounterValue;
    }

}
