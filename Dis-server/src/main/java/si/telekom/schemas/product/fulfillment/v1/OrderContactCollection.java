
package si.telekom.schemas.product.fulfillment.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderContactCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderContactCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderContact" type="{http://telekom.si/schemas/product/fulfillment/v1}OrderContact" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderContactCollection", propOrder = {
    "orderContact"
})
public class OrderContactCollection {

    @XmlElement(required = true)
    protected List<OrderContact> orderContact;

    /**
     * Gets the value of the orderContact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderContact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderContact }
     * 
     * 
     */
    public List<OrderContact> getOrderContact() {
        if (orderContact == null) {
            orderContact = new ArrayList<OrderContact>();
        }
        return this.orderContact;
    }

}
