
package si.telekom.services.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.customer.v1.CreditCheckParameter;
import si.telekom.services.common.base.v1.RequestMessage;


/**
 * <p>Java class for CreditCheckRequestMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCheckRequestMsg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}RequestMessage">
 *       &lt;sequence>
 *         &lt;element name="searchParameter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="searchFor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="requestParameter" type="{http://telekom.si/schemas/common/customer/v1}CreditCheckParameter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCheckRequestMsg", propOrder = {
    "searchParameter",
    "searchFor",
    "sourceId",
    "productTypeId",
    "productNo",
    "requestParameter"
})
public class CreditCheckRequestMsg
    extends RequestMessage
{

    protected String searchParameter;
    protected String searchFor;
    protected String sourceId;
    protected String productTypeId;
    @XmlElement(defaultValue = "1")
    protected Integer productNo;
    protected CreditCheckParameter requestParameter;

    /**
     * Gets the value of the searchParameter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchParameter() {
        return searchParameter;
    }

    /**
     * Sets the value of the searchParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchParameter(String value) {
        this.searchParameter = value;
    }

    /**
     * Gets the value of the searchFor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchFor() {
        return searchFor;
    }

    /**
     * Sets the value of the searchFor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchFor(String value) {
        this.searchFor = value;
    }

    /**
     * Gets the value of the sourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * Sets the value of the sourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceId(String value) {
        this.sourceId = value;
    }

    /**
     * Gets the value of the productTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeId() {
        return productTypeId;
    }

    /**
     * Sets the value of the productTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeId(String value) {
        this.productTypeId = value;
    }

    /**
     * Gets the value of the productNo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProductNo() {
        return productNo;
    }

    /**
     * Sets the value of the productNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProductNo(Integer value) {
        this.productNo = value;
    }

    /**
     * Gets the value of the requestParameter property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCheckParameter }
     *     
     */
    public CreditCheckParameter getRequestParameter() {
        return requestParameter;
    }

    /**
     * Sets the value of the requestParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCheckParameter }
     *     
     */
    public void setRequestParameter(CreditCheckParameter value) {
        this.requestParameter = value;
    }

}
