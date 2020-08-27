
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
 *         &lt;element name="getCatalogResponseMsg" type="{http://telekom.si/services/common/base/v1}GetCatalogResponseMsg"/>
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
    "getCatalogResponseMsg"
})
@XmlRootElement(name = "getCatalogResponse")
public class GetCatalogResponse {

    @XmlElement(required = true)
    protected GetCatalogResponseMsg getCatalogResponseMsg;

    /**
     * Gets the value of the getCatalogResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetCatalogResponseMsg }
     *     
     */
    public GetCatalogResponseMsg getGetCatalogResponseMsg() {
        return getCatalogResponseMsg;
    }

    /**
     * Sets the value of the getCatalogResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCatalogResponseMsg }
     *     
     */
    public void setGetCatalogResponseMsg(GetCatalogResponseMsg value) {
        this.getCatalogResponseMsg = value;
    }

}
