
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
 *         &lt;element name="findCatalogResponseMsg" type="{http://telekom.si/services/common/base/v1}FindCatalogResponseMsg"/>
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
    "findCatalogResponseMsg"
})
@XmlRootElement(name = "findCatalogResponse")
public class FindCatalogResponse {

    @XmlElement(required = true)
    protected FindCatalogResponseMsg findCatalogResponseMsg;

    /**
     * Gets the value of the findCatalogResponseMsg property.
     * 
     * @return
     *     possible object is
     *     {@link FindCatalogResponseMsg }
     *     
     */
    public FindCatalogResponseMsg getFindCatalogResponseMsg() {
        return findCatalogResponseMsg;
    }

    /**
     * Sets the value of the findCatalogResponseMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link FindCatalogResponseMsg }
     *     
     */
    public void setFindCatalogResponseMsg(FindCatalogResponseMsg value) {
        this.findCatalogResponseMsg = value;
    }

}
