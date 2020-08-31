
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharacteristicWithCatalogCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharacteristicWithCatalogCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="characteristicWithCatalog" type="{http://telekom.si/schemas/common/base/v1}CharacteristicWithCatalog" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharacteristicWithCatalogCollection", namespace = "http://telekom.si/schemas/common/base/v1", propOrder = {
    "characteristicWithCatalogs"
})
public class CharacteristicWithCatalogCollection {

    @XmlElement(name = "characteristicWithCatalog", required = true)
    protected List<CharacteristicWithCatalog> characteristicWithCatalogs;

    /**
     * Gets the value of the characteristicWithCatalogs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the characteristicWithCatalogs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharacteristicWithCatalogs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacteristicWithCatalog }
     * 
     * 
     */
    public List<CharacteristicWithCatalog> getCharacteristicWithCatalogs() {
        if (characteristicWithCatalogs == null) {
            characteristicWithCatalogs = new ArrayList<CharacteristicWithCatalog>();
        }
        return this.characteristicWithCatalogs;
    }

}
