
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
 *         &lt;element name="getCustomerResponseMsg" type="{http://telekom.si/services/common/customer/v1}GetCustomerResponseMsg"/>
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
    "getCustomerResponseMsg"
})
@XmlRootElement(name = "getCustomerResponse")
public class GetCustomerResponse {

    @XmlElement(required = true)
    protected GetCustomerResponseMsg getCustomerResponseMsg;

    /**
     * Gets the value of the getCustomerResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetCustomerResponseMsg }
     *     
     */
    public GetCustomerResponseMsg getGetCustomerResponseMsg() {
        return getCustomerResponseMsg;
    }

    /**
     * Sets the value of the getCustomerResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCustomerResponseMsg }
     *     
     */
    public void setGetCustomerResponseMsg(GetCustomerResponseMsg value) {
        this.getCustomerResponseMsg = value;
    }

}
