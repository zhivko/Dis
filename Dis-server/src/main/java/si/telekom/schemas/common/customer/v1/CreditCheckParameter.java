
package si.telekom.schemas.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Vstopni oz. izstopni parameter creditCheck metode
 * 
 * <p>Java class for CreditCheckParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCheckParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="parameterCodeCollection" type="{http://telekom.si/schemas/common/customer/v1}CreditCheckParameterCodeCollection" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCheckParameter", propOrder = {
    "parameterCodeCollection",
    "amount"
})
public class CreditCheckParameter {

    protected CreditCheckParameterCodeCollection parameterCodeCollection;
    protected Integer amount;

    /**
     * Gets the value of the parameterCodeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCheckParameterCodeCollection }
     *     
     */
    public CreditCheckParameterCodeCollection getParameterCodeCollection() {
        return parameterCodeCollection;
    }

    /**
     * Sets the value of the parameterCodeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCheckParameterCodeCollection }
     *     
     */
    public void setParameterCodeCollection(CreditCheckParameterCodeCollection value) {
        this.parameterCodeCollection = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAmount(Integer value) {
        this.amount = value;
    }

}
