
package si.telekom.dis.server.jaxwsClient.eRender;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mergeContentOfItems complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mergeContentOfItems">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://erender.telekom.si/}MergeItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mergeContentOfItems", propOrder = {
    "arg0S"
})
@XmlRootElement(name = "mergeContentOfItems")
public class MergeContentOfItems {

    @XmlElement(name = "arg0", namespace = "")
    protected List<MergeItem> arg0S;

    /**
     * Gets the value of the arg0S property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg0S property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg0s().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MergeItem }
     * 
     * 
     */
    public List<MergeItem> getArg0s() {
        if (arg0S == null) {
            arg0S = new ArrayList<MergeItem>();
        }
        return this.arg0S;
    }

}
