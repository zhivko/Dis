
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for syncAttsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="syncAttsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://templates.mobitel.com/}arrayList" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "syncAttsResponse", propOrder = {
    "returns"
})
@XmlRootElement(name = "syncAttsResponse")
public class SyncAttsResponse {

    @XmlElement(name = "return", namespace = "")
    protected List<si.telekom.dis.server.jaxwsClient.pdfGenerator.ArrayList> returns;

    /**
     * Gets the value of the returns property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returns property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link si.telekom.dis.server.jaxwsClient.pdfGenerator.ArrayList }
     * 
     * 
     */
    public List<si.telekom.dis.server.jaxwsClient.pdfGenerator.ArrayList> getReturns() {
        if (returns == null) {
            returns = new java.util.ArrayList<si.telekom.dis.server.jaxwsClient.pdfGenerator.ArrayList>();
        }
        return this.returns;
    }

}
