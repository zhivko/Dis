
package si.telekom.schemas.product.fulfillment.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignedProductOfferingCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedProductOfferingCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assigendProductOffering" type="{http://telekom.si/schemas/product/fulfillment/v1}AssignedProductOffering" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedProductOfferingCollection", propOrder = {
    "assigendProductOffering"
})
public class AssignedProductOfferingCollection {

    @XmlElement(required = true)
    protected List<AssignedProductOffering> assigendProductOffering;

    /**
     * Gets the value of the assigendProductOffering property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assigendProductOffering property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssigendProductOffering().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssignedProductOffering }
     * 
     * 
     */
    public List<AssignedProductOffering> getAssigendProductOffering() {
        if (assigendProductOffering == null) {
            assigendProductOffering = new ArrayList<AssignedProductOffering>();
        }
        return this.assigendProductOffering;
    }

}
