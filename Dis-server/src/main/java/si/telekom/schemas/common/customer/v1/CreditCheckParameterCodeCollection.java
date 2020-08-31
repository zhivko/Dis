
package si.telekom.schemas.common.customer.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Lista kod pogojev creditCheck metode, operacija med njimi je zmeraj IN
 * 
 * <p>Java class for CreditCheckParameterCodeCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCheckParameterCodeCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameterCode" type="{http://telekom.si/schemas/common/customer/v1}CreditCheckParameterCode" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCheckParameterCodeCollection", propOrder = {
    "parameterCode"
})
public class CreditCheckParameterCodeCollection {

    @XmlElement(required = true)
    protected List<CreditCheckParameterCode> parameterCode;

    /**
     * Gets the value of the parameterCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameterCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameterCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditCheckParameterCode }
     * 
     * 
     */
    public List<CreditCheckParameterCode> getParameterCode() {
        if (parameterCode == null) {
            parameterCode = new ArrayList<CreditCheckParameterCode>();
        }
        return this.parameterCode;
    }

}
