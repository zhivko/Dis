
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
 *         &lt;element name="creditCheckResponseMsg" type="{http://telekom.si/services/common/customer/v1}CreditCheckResponseMsg"/>
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
    "creditCheckResponseMsg"
})
@XmlRootElement(name = "creditCheckResponse")
public class CreditCheckResponse {

    @XmlElement(required = true)
    protected CreditCheckResponseMsg creditCheckResponseMsg;

    /**
     * Gets the value of the creditCheckResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCheckResponseMsg }
     *     
     */
    public CreditCheckResponseMsg getCreditCheckResponseMsg() {
        return creditCheckResponseMsg;
    }

    /**
     * Sets the value of the creditCheckResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCheckResponseMsg }
     *     
     */
    public void setCreditCheckResponseMsg(CreditCheckResponseMsg value) {
        this.creditCheckResponseMsg = value;
    }

}
