
package si.telekom.schemas.product.usage.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InstalmentLoanCounterCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InstalmentLoanCounterCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="instalmentLoanCounter" type="{http://telekom.si/schemas/product/usage/v1}InstalmentLoanCounter" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstalmentLoanCounterCollection", propOrder = {
    "instalmentLoanCounter"
})
public class InstalmentLoanCounterCollection {

    @XmlElement(required = true)
    protected List<InstalmentLoanCounter> instalmentLoanCounter;

    /**
     * Gets the value of the instalmentLoanCounter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instalmentLoanCounter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstalmentLoanCounter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstalmentLoanCounter }
     * 
     * 
     */
    public List<InstalmentLoanCounter> getInstalmentLoanCounter() {
        if (instalmentLoanCounter == null) {
            instalmentLoanCounter = new ArrayList<InstalmentLoanCounter>();
        }
        return this.instalmentLoanCounter;
    }

}
