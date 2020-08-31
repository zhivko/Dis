
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
 *         &lt;element name="findCatalogRequestMsg" type="{http://telekom.si/services/common/base/v1}FindCatalogRequestMsg"/>
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
    "findCatalogRequestMsg"
})
@XmlRootElement(name = "findCatalog")
public class FindCatalog {

    @XmlElement(required = true)
    protected FindCatalogRequestMsg findCatalogRequestMsg;

    /**
     * Gets the value of the findCatalogRequestMsg property.
     * 
     * @return
     *     possible object is
     *     {@link FindCatalogRequestMsg }
     *     
     */
    public FindCatalogRequestMsg getFindCatalogRequestMsg() {
        return findCatalogRequestMsg;
    }

    /**
     * Sets the value of the findCatalogRequestMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindCatalogRequestMsg }
     *     
     */
    public void setFindCatalogRequestMsg(FindCatalogRequestMsg value) {
        this.findCatalogRequestMsg = value;
    }

}
