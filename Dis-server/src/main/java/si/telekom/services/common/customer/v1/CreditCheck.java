
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
 *         &lt;element name="creditCheckRequestMsg" type="{http://telekom.si/services/common/customer/v1}CreditCheckRequestMsg"/>
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
    "creditCheckRequestMsg"
})
@XmlRootElement(name = "creditCheck")
public class CreditCheck {

    @XmlElement(required = true)
    protected CreditCheckRequestMsg creditCheckRequestMsg;

    /**
     * Gets the value of the creditCheckRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCheckRequestMsg }
     *     
     */
    public CreditCheckRequestMsg getCreditCheckRequestMsg() {
        return creditCheckRequestMsg;
    }

    /**
     * Sets the value of the creditCheckRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCheckRequestMsg }
     *     
     */
    public void setCreditCheckRequestMsg(CreditCheckRequestMsg value) {
        this.creditCheckRequestMsg = value;
    }

}
