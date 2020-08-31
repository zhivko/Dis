
package si.telekom.schemas.product.fulfillment.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FulfilmentGroupCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FulfilmentGroupCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fulfilmentGroup" type="{http://telekom.si/schemas/product/fulfillment/v1}FulfilmentGroup" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FulfilmentGroupCollection", propOrder = {
    "fulfilmentGroup"
})
public class FulfilmentGroupCollection {

    @XmlElement(required = true)
    protected List<FulfilmentGroup> fulfilmentGroup;

    /**
     * Gets the value of the fulfilmentGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fulfilmentGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFulfilmentGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FulfilmentGroup }
     * 
     * 
     */
    public List<FulfilmentGroup> getFulfilmentGroup() {
        if (fulfilmentGroup == null) {
            fulfilmentGroup = new ArrayList<FulfilmentGroup>();
        }
        return this.fulfilmentGroup;
    }

}
