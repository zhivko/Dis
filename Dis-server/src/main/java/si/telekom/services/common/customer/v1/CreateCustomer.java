
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
 *         &lt;element name="createCustomerRequestMsg" type="{http://telekom.si/services/common/customer/v1}CreateCustomerRequestMsg"/>
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
    "createCustomerRequestMsg"
})
@XmlRootElement(name = "createCustomer")
public class CreateCustomer {

    @XmlElement(required = true)
    protected CreateCustomerRequestMsg createCustomerRequestMsg;

    /**
     * Gets the value of the createCustomerRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link CreateCustomerRequestMsg }
     *     
     */
    public CreateCustomerRequestMsg getCreateCustomerRequestMsg() {
        return createCustomerRequestMsg;
    }

    /**
     * Sets the value of the createCustomerRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreateCustomerRequestMsg }
     *     
     */
    public void setCreateCustomerRequestMsg(CreateCustomerRequestMsg value) {
        this.createCustomerRequestMsg = value;
    }

}
