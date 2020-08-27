
package si.telekom.schemas.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Detailed information about Customer
 * 
 * <p>Java class for CustomerDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/customer/v1}Customer">
 *       &lt;sequence>
 *         &lt;element name="customerAccountCollection" type="{http://telekom.si/schemas/common/customer/v1}CustomerAccountCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerDetail", propOrder = {
    "customerAccountCollection"
})
public class CustomerDetail
    extends Customer
{

    protected CustomerAccountCollection customerAccountCollection;

    /**
     * Gets the value of the customerAccountCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerAccountCollection }
     *     
     */
    public CustomerAccountCollection getCustomerAccountCollection() {
        return customerAccountCollection;
    }

    /**
     * Sets the value of the customerAccountCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerAccountCollection }
     *     
     */
    public void setCustomerAccountCollection(CustomerAccountCollection value) {
        this.customerAccountCollection = value;
    }

}
