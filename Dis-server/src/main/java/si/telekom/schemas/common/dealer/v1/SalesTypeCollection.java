
package si.telekom.schemas.common.dealer.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesTypeCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesTypeCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesType" type="{http://telekom.si/schemas/common/dealer/v1}SalesType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesTypeCollection", propOrder = {
    "salesType"
})
public class SalesTypeCollection {

    protected List<SalesType> salesType;

    /**
     * Gets the value of the salesType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesType }
     * 
     * 
     */
    public List<SalesType> getSalesType() {
        if (salesType == null) {
            salesType = new ArrayList<SalesType>();
        }
        return this.salesType;
    }

}
