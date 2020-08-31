
package si.telekom.schemas.common.businessinteraction.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BusinessInteractionCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessInteraction" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteraction" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionCollection", propOrder = {
    "businessInteraction"
})
public class BusinessInteractionCollection {

    @XmlElement(required = true)
    protected List<BusinessInteraction> businessInteraction;

    /**
     * Gets the value of the businessInteraction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessInteraction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessInteraction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessInteraction }
     * 
     * 
     */
    public List<BusinessInteraction> getBusinessInteraction() {
        if (businessInteraction == null) {
            businessInteraction = new ArrayList<BusinessInteraction>();
        }
        return this.businessInteraction;
    }

}
