
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
 *         &lt;element name="getUniversalCatalogRequestMsg" type="{http://telekom.si/services/common/base/v1}GetUniversalCatalogRequestMsg"/>
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
    "getUniversalCatalogRequestMsg"
})
@XmlRootElement(name = "getUniversalCatalog")
public class GetUniversalCatalog {

    @XmlElement(required = true)
    protected GetUniversalCatalogRequestMsg getUniversalCatalogRequestMsg;

    /**
     * Gets the value of the getUniversalCatalogRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link GetUniversalCatalogRequestMsg }
     *     
     */
    public GetUniversalCatalogRequestMsg getGetUniversalCatalogRequestMsg() {
        return getUniversalCatalogRequestMsg;
    }

    /**
     * Sets the value of the getUniversalCatalogRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link GetUniversalCatalogRequestMsg }
     *     
     */
    public void setGetUniversalCatalogRequestMsg(GetUniversalCatalogRequestMsg value) {
        this.getUniversalCatalogRequestMsg = value;
    }

}
