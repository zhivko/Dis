
package si.telekom.schemas.common.party.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of distibution channels
 * 
 * <p>Java class for DistributionChannelCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DistributionChannelCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="distributionChannel" type="{http://telekom.si/schemas/common/party/v1}DistributionChannel" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributionChannelCollection", propOrder = {
    "distributionChannel"
})
public class DistributionChannelCollection {

    @XmlElement(required = true)
    protected List<DistributionChannel> distributionChannel;

    /**
     * Gets the value of the distributionChannel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the distributionChannel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDistributionChannel().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DistributionChannel }
     * 
     * 
     */
    public List<DistributionChannel> getDistributionChannel() {
        if (distributionChannel == null) {
            distributionChannel = new ArrayList<DistributionChannel>();
        }
        return this.distributionChannel;
    }

}
