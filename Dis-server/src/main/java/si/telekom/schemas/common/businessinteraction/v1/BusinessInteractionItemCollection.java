
package si.telekom.schemas.common.businessinteraction.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of items involved in Business Interaction
 * 
 * <p>Java class for BusinessInteractionItemCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionItemCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessInteractionItem" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionItem" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionItemCollection", propOrder = {
    "businessInteractionItem"
})
public class BusinessInteractionItemCollection {

    @XmlElement(required = true)
    protected List<BusinessInteractionItem> businessInteractionItem;

    /**
     * Gets the value of the businessInteractionItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessInteractionItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessInteractionItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessInteractionItem }
     * 
     * 
     */
    public List<BusinessInteractionItem> getBusinessInteractionItem() {
        if (businessInteractionItem == null) {
            businessInteractionItem = new ArrayList<BusinessInteractionItem>();
        }
        return this.businessInteractionItem;
    }

}
