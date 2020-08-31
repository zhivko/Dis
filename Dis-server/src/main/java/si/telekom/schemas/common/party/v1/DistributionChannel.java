
package si.telekom.schemas.common.party.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Distribution channel
 * 
 * <p>Java class for DistributionChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DistributionChannel">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/party/v1}BankAccount">
 *       &lt;sequence>
 *         &lt;element name="postMail" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="eMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerAccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DistributionChannel", propOrder = {
    "postMail",
    "eMail",
    "customerAccountId"
})
public class DistributionChannel
    extends BankAccount
{

    protected int postMail;
    protected String eMail;
    protected String customerAccountId;

    /**
     * Gets the value of the postMail property.
     * 
     */
    public int getPostMail() {
        return postMail;
    }

    /**
     * Sets the value of the postMail property.
     * 
     */
    public void setPostMail(int value) {
        this.postMail = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

    /**
     * Gets the value of the customerAccountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerAccountId() {
        return customerAccountId;
    }

    /**
     * Sets the value of the customerAccountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerAccountId(String value) {
        this.customerAccountId = value;
    }

}
