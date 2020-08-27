
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
 *         &lt;element name="getCustomerRequestMsg" type="{http://telekom.si/services/common/customer/v1}GetCustomerRequestMsg"/>
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
    "getCustomerRequestMsg"
})
@XmlRootElement(name = "getCustomer")
public class GetCustomer {

    @XmlElement(required = true)
    protected GetCustomerRequestMsg getCustomerRequestMsg;

    /**
     * Gets the value of the getCustomerRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetCustomerRequestMsg }
     *     
     */
    public GetCustomerRequestMsg getGetCustomerRequestMsg() {
        return getCustomerRequestMsg;
    }

    /**
     * Sets the value of the getCustomerRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCustomerRequestMsg }
     *     
     */
    public void setGetCustomerRequestMsg(GetCustomerRequestMsg value) {
        this.getCustomerRequestMsg = value;
    }

}
