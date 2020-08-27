
package si.telekom.services.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.services.common.base.v1.RequestMessage;
import si.telekom.services.common.base.v1.ResultFilter;


/**
 * <p>Java class for GetCustomerRequestMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetCustomerRequestMsg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}RequestMessage">
 *       &lt;sequence>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}resultFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCustomerRequestMsg", propOrder = {
    "customerId",
    "resultFilter"
})
public class GetCustomerRequestMsg
    extends RequestMessage
{

    protected long customerId;
    @XmlElement(namespace = "http://telekom.si/services/common/base/v1")
    protected ResultFilter resultFilter;

    /**
     * Gets the value of the customerId property.
     * 
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     */
    public void setCustomerId(long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the resultFilter property.
     * 
     * @return
     *     possible object is
     *     {@link ResultFilter }
     *     
     */
    public ResultFilter getResultFilter() {
        return resultFilter;
    }

    /**
     * Sets the value of the resultFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultFilter }
     *     
     */
    public void setResultFilter(ResultFilter value) {
        this.resultFilter = value;
    }

}
