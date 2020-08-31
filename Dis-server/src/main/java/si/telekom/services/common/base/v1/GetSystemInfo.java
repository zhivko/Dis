
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
 *         &lt;element name="getSystemInfoRequestMsg" type="{http://telekom.si/services/common/base/v1}GetSystemInfoRequestMsg"/>
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
    "getSystemInfoRequestMsg"
})
@XmlRootElement(name = "getSystemInfo")
public class GetSystemInfo {

    @XmlElement(required = true)
    protected GetSystemInfoRequestMsg getSystemInfoRequestMsg;

    /**
     * Gets the value of the getSystemInfoRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetSystemInfoRequestMsg }
     *     
     */
    public GetSystemInfoRequestMsg getGetSystemInfoRequestMsg() {
        return getSystemInfoRequestMsg;
    }

    /**
     * Sets the value of the getSystemInfoRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetSystemInfoRequestMsg }
     *     
     */
    public void setGetSystemInfoRequestMsg(GetSystemInfoRequestMsg value) {
        this.getSystemInfoRequestMsg = value;
    }

}
