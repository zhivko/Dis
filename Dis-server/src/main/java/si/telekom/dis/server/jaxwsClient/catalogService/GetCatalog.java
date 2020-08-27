
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
 *         &lt;element name="getCatalogRequestMsg" type="{http://telekom.si/services/common/base/v1}GetCatalogRequestMsg"/>
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
    "getCatalogRequestMsg"
})
@XmlRootElement(name = "getCatalog")
public class GetCatalog {

    @XmlElement(required = true)
    protected GetCatalogRequestMsg getCatalogRequestMsg;

    /**
     * Gets the value of the getCatalogRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetCatalogRequestMsg }
     *     
     */
    public GetCatalogRequestMsg getGetCatalogRequestMsg() {
        return getCatalogRequestMsg;
    }

    /**
     * Sets the value of the getCatalogRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCatalogRequestMsg }
     *     
     */
    public void setGetCatalogRequestMsg(GetCatalogRequestMsg value) {
        this.getCatalogRequestMsg = value;
    }

}
