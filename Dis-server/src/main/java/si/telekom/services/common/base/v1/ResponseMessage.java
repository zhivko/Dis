
package si.telekom.services.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.services.common.customer.v1.CreateCustomerResponseMsg;
import si.telekom.services.common.customer.v1.CreditCheckResponseMsg;
import si.telekom.services.common.customer.v1.FindCustomerResponseMsg;
import si.telekom.services.common.customer.v1.GetCustomerResponseMsg;
import si.telekom.services.common.customer.v1.UpdateCustomerResponseMsg;


/**
 * <p>Java class for ResponseMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseMessage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}Message">
 *       &lt;sequence>
 *         &lt;element name="responseMessageHeader" type="{http://telekom.si/services/common/base/v1}ResponseMessageHeader" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseMessage", propOrder = {
    "responseMessageHeader"
})
@XmlSeeAlso({
    GetSystemInfoResponseMsg.class,
    GetCustomerResponseMsg.class,
    CreateCustomerResponseMsg.class,
    UpdateCustomerResponseMsg.class,
    CreditCheckResponseMsg.class,
    FindCustomerResponseMsg.class
})
public class ResponseMessage
    extends Message
{

    protected ResponseMessageHeader responseMessageHeader;

    /**
     * Gets the value of the responseMessageHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseMessageHeader }
     *     
     */
    public ResponseMessageHeader getResponseMessageHeader() {
        return responseMessageHeader;
    }

    /**
     * Sets the value of the responseMessageHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseMessageHeader }
     *     
     */
    public void setResponseMessageHeader(ResponseMessageHeader value) {
        this.responseMessageHeader = value;
    }

}
