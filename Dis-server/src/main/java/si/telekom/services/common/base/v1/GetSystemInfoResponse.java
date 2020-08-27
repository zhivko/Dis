
package si.telekom.services.common.base.v1;

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
 *         &lt;element name="getSystemInfoResponseMsg" type="{http://telekom.si/services/common/base/v1}GetSystemInfoResponseMsg"/>
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
    "getSystemInfoResponseMsg"
})
@XmlRootElement(name = "getSystemInfoResponse")
public class GetSystemInfoResponse {

    @XmlElement(required = true)
    protected GetSystemInfoResponseMsg getSystemInfoResponseMsg;

    /**
     * Gets the value of the getSystemInfoResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetSystemInfoResponseMsg }
     *     
     */
    public GetSystemInfoResponseMsg getGetSystemInfoResponseMsg() {
        return getSystemInfoResponseMsg;
    }

    /**
     * Sets the value of the getSystemInfoResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSystemInfoResponseMsg }
     *     
     */
    public void setGetSystemInfoResponseMsg(GetSystemInfoResponseMsg value) {
        this.getSystemInfoResponseMsg = value;
    }

}
