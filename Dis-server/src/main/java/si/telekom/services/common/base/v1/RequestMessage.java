
package si.telekom.services.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.services.common.customer.v1.CreateCustomerRequestMsg;
import si.telekom.services.common.customer.v1.CreditCheckRequestMsg;
import si.telekom.services.common.customer.v1.FindCustomerRequestMsg;
import si.telekom.services.common.customer.v1.GetCustomerRequestMsg;
import si.telekom.services.common.customer.v1.UpdateCustomerRequestMsg;


/**
 * <p>Java class for RequestMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestMessage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}Message">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://telekom.si/services/common/base/v1}RequestMessageHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestMessage", propOrder = {
    "header"
})
@XmlSeeAlso({
    GetSystemInfoRequestMsg.class,
    CreditCheckRequestMsg.class,
    CreateCustomerRequestMsg.class,
    UpdateCustomerRequestMsg.class,
    FindCustomerRequestMsg.class,
    GetCustomerRequestMsg.class
})
public class RequestMessage
    extends Message
{

    protected RequestMessageHeader header;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link RequestMessageHeader }
     *     
     */
    public RequestMessageHeader getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestMessageHeader }
     *     
     */
    public void setHeader(RequestMessageHeader value) {
        this.header = value;
    }

}
