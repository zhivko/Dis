
package si.telekom.schemas.product.fulfillment.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductStateCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductStateCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productState" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductState" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductStateCollection", propOrder = {
    "productState"
})
public class ProductStateCollection {

    @XmlElement(required = true)
    protected List<ProductState> productState;

    /**
     * Gets the value of the productState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductState }
     * 
     * 
     */
    public List<ProductState> getProductState() {
        if (productState == null) {
            productState = new ArrayList<ProductState>();
        }
        return this.productState;
    }

}
