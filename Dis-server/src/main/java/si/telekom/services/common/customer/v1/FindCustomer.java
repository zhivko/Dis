
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
 *         &lt;element name="findCustomerRequestMsg" type="{http://telekom.si/services/common/customer/v1}FindCustomerRequestMsg"/>
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
    "findCustomerRequestMsg"
})
@XmlRootElement(name = "findCustomer")
public class FindCustomer {

    @XmlElement(required = true)
    protected FindCustomerRequestMsg findCustomerRequestMsg;

    /**
     * Gets the value of the findCustomerRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link FindCustomerRequestMsg }
     *     
     */
    public FindCustomerRequestMsg getFindCustomerRequestMsg() {
        return findCustomerRequestMsg;
    }

    /**
     * Sets the value of the findCustomerRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindCustomerRequestMsg }
     *     
     */
    public void setFindCustomerRequestMsg(FindCustomerRequestMsg value) {
        this.findCustomerRequestMsg = value;
    }

}
