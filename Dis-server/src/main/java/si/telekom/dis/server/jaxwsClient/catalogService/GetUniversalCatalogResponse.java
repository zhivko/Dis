
package si.telekom.dis.server.jaxwsClient.catalogService;

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
 *         &lt;element name="getUniversalCatalogResponseMsg" type="{http://telekom.si/services/common/base/v1}GetUniversalCatalogResponseMsg"/>
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
    "getUniversalCatalogResponseMsg"
})
@XmlRootElement(name = "getUniversalCatalogResponse")
public class GetUniversalCatalogResponse {

    @XmlElement(required = true)
    protected GetUniversalCatalogResponseMsg getUniversalCatalogResponseMsg;

    /**
     * Gets the value of the getUniversalCatalogResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetUniversalCatalogResponseMsg }
     *     
     */
    public GetUniversalCatalogResponseMsg getGetUniversalCatalogResponseMsg() {
        return getUniversalCatalogResponseMsg;
    }

    /**
     * Sets the value of the getUniversalCatalogResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUniversalCatalogResponseMsg }
     *     
     */
    public void setGetUniversalCatalogResponseMsg(GetUniversalCatalogResponseMsg value) {
        this.getUniversalCatalogResponseMsg = value;
    }

}
