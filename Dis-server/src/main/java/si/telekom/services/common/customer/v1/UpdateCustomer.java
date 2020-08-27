
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
 *         &lt;element name="updateCustomerRequestMsg" type="{http://telekom.si/services/common/customer/v1}UpdateCustomerRequestMsg"/>
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
    "updateCustomerRequestMsg"
})
@XmlRootElement(name = "updateCustomer")
public class UpdateCustomer {

    @XmlElement(required = true)
    protected UpdateCustomerRequestMsg updateCustomerRequestMsg;

    /**
     * Gets the value of the updateCustomerRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateCustomerRequestMsg }
     *     
     */
    public UpdateCustomerRequestMsg getUpdateCustomerRequestMsg() {
        return updateCustomerRequestMsg;
    }

    /**
     * Sets the value of the updateCustomerRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateCustomerRequestMsg }
     *     
     */
    public void setUpdateCustomerRequestMsg(UpdateCustomerRequestMsg value) {
        this.updateCustomerRequestMsg = value;
    }

}
