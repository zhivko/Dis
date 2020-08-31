
package si.telekom.schemas.common.dealer.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for DealerRoleCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DealerRoleCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dealerRole" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DealerRoleCollection", propOrder = {
    "dealerRole"
})
public class DealerRoleCollection {

    @XmlElement(required = true)
    protected List<CatalogValue> dealerRole;

    /**
     * Gets the value of the dealerRole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dealerRole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDealerRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogValue }
     * 
     * 
     */
    public List<CatalogValue> getDealerRole() {
        if (dealerRole == null) {
            dealerRole = new ArrayList<CatalogValue>();
        }
        return this.dealerRole;
    }

}
