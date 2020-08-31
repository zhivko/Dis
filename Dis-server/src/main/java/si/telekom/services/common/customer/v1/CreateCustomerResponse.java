
package si.telekom.services.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="createCustomerResponseMsg" type="{http://telekom.si/services/common/customer/v1}CreateCustomerResponseMsg"/>
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
    "createCustomerResponseMsg"
})
@XmlRootElement(name = "createCustomerResponse")
public class CreateCustomerResponse {

    @XmlElement(required = true)
    protected CreateCustomerResponseMsg createCustomerResponseMsg;

    /**
     * Gets the value of the createCustomerResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link CreateCustomerResponseMsg }
     *     
     */
    public CreateCustomerResponseMsg getCreateCustomerResponseMsg() {
        return createCustomerResponseMsg;
    }

    /**
     * Sets the value of the createCustomerResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateCustomerResponseMsg }
     *     
     */
    public void setCreateCustomerResponseMsg(CreateCustomerResponseMsg value) {
        this.createCustomerResponseMsg = value;
    }

}
