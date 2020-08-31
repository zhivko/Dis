
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FindCatalogResponseMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FindCatalogResponseMsg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}ResponseMessage">
 *       &lt;sequence>
 *         &lt;element name="catalog" type="{http://telekom.si/schemas/common/base/v1}Catalog" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FindCatalogResponseMsg", propOrder = {
    "catalogs"
})
public class FindCatalogResponseMsg
    extends ResponseMessage
{

    @XmlElement(name = "catalog")
    protected List<Catalog> catalogs;

    /**
     * Gets the value of the catalogs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the catalogs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatalogs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Catalog }
     * 
     * 
     */
    public List<Catalog> getCatalogs() {
        if (catalogs == null) {
            catalogs = new ArrayList<Catalog>();
        }
        return this.catalogs;
    }

}
