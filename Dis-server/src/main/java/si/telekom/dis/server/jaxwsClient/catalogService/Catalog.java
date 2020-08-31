
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Catalog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Catalog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catalogValueCollection" type="{http://telekom.si/schemas/common/base/v1}CatalogValueCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Catalog", namespace = "http://telekom.si/schemas/common/base/v1", propOrder = {
    "name",
    "catalogValueCollection"
})
public class Catalog {

    protected String name;
    protected CatalogValueCollection catalogValueCollection;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the catalogValueCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValueCollection }
     *     
     */
    public CatalogValueCollection getCatalogValueCollection() {
        return catalogValueCollection;
    }

    /**
     * Sets the value of the catalogValueCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValueCollection }
     *     
     */
    public void setCatalogValueCollection(CatalogValueCollection value) {
        this.catalogValueCollection = value;
    }

}
