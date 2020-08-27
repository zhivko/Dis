
package si.telekom.schemas.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharacteristicWithCatalog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharacteristicWithCatalog">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Characteristic">
 *       &lt;sequence>
 *         &lt;element name="catalogValueCollection" type="{http://telekom.si/schemas/common/base/v1}CatalogValueCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacteristicWithCatalog", propOrder = {
    "catalogValueCollection"
})
public class CharacteristicWithCatalog
    extends Characteristic
{

    protected CatalogValueCollection catalogValueCollection;

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
