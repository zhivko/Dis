
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
 *         &lt;element name="updateCustomerResponseMsg" type="{http://telekom.si/services/common/customer/v1}UpdateCustomerResponseMsg"/>
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
    "updateCustomerResponseMsg"
})
@XmlRootElement(name = "updateCustomerResponse")
public class UpdateCustomerResponse {

    @XmlElement(required = true)
    protected UpdateCustomerResponseMsg updateCustomerResponseMsg;

    /**
     * Gets the value of the updateCustomerResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateCustomerResponseMsg }
     *     
     */
    public UpdateCustomerResponseMsg getUpdateCustomerResponseMsg() {
        return updateCustomerResponseMsg;
    }

    /**
     * Sets the value of the updateCustomerResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateCustomerResponseMsg }
     *     
     */
    public void setUpdateCustomerResponseMsg(UpdateCustomerResponseMsg value) {
        this.updateCustomerResponseMsg = value;
    }

}
