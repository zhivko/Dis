
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
 *         &lt;element name="findCustomerResponseMsg" type="{http://telekom.si/services/common/customer/v1}FindCustomerResponseMsg"/>
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
    "findCustomerResponseMsg"
})
@XmlRootElement(name = "findCustomerResponse")
public class FindCustomerResponse {

    @XmlElement(required = true)
    protected FindCustomerResponseMsg findCustomerResponseMsg;

    /**
     * Gets the value of the findCustomerResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link FindCustomerResponseMsg }
     *     
     */
    public FindCustomerResponseMsg getFindCustomerResponseMsg() {
        return findCustomerResponseMsg;
    }

    /**
     * Sets the value of the findCustomerResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindCustomerResponseMsg }
     *     
     */
    public void setFindCustomerResponseMsg(FindCustomerResponseMsg value) {
        this.findCustomerResponseMsg = value;
    }

}
