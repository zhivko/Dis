
package si.telekom.services.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.customer.v1.CreditCheckParameterCollection;
import si.telekom.services.common.base.v1.ResponseMessage;


/**
 * <p>Java class for CreditCheckResponseMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCheckResponseMsg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}ResponseMessage">
 *       &lt;sequence>
 *         &lt;element name="partyId" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="responseResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responseParameterCollection" type="{http://telekom.si/schemas/common/customer/v1}CreditCheckParameterCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCheckResponseMsg", propOrder = {
    "partyId",
    "responseResult",
    "responseParameterCollection"
})
public class CreditCheckResponseMsg
    extends ResponseMessage
{

    protected Object partyId;
    protected String responseResult;
    protected CreditCheckParameterCollection responseParameterCollection;

    /**
     * Gets the value of the partyId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPartyId() {
        return partyId;
    }

    /**
     * Sets the value of the partyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPartyId(Object value) {
        this.partyId = value;
    }

    /**
     * Gets the value of the responseResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseResult() {
        return responseResult;
    }

    /**
     * Sets the value of the responseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseResult(String value) {
        this.responseResult = value;
    }

    /**
     * Gets the value of the responseParameterCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCheckParameterCollection }
     *     
     */
    public CreditCheckParameterCollection getResponseParameterCollection() {
        return responseParameterCollection;
    }

    /**
     * Sets the value of the responseParameterCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCheckParameterCollection }
     *     
     */
    public void setResponseParameterCollection(CreditCheckParameterCollection value) {
        this.responseParameterCollection = value;
    }

}
