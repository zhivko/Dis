
package si.telekom.schemas.common.dealer.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesChannelCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesChannelCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesChannel" type="{http://telekom.si/schemas/common/dealer/v1}SalesChannel" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesChannelCollection", propOrder = {
    "salesChannel"
})
public class SalesChannelCollection {

    protected List<SalesChannel> salesChannel;

    /**
     * Gets the value of the salesChannel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesChannel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesChannel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesChannel }
     * 
     * 
     */
    public List<SalesChannel> getSalesChannel() {
        if (salesChannel == null) {
            salesChannel = new ArrayList<SalesChannel>();
        }
        return this.salesChannel;
    }

}
