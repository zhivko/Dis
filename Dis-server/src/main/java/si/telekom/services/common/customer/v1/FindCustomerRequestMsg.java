
package si.telekom.services.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.TimePeriod;
import si.telekom.services.common.base.v1.RequestMessage;
import si.telekom.services.common.base.v1.SearchFilter;


/**
 * <p>Java class for FindCustomerRequestMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FindCustomerRequestMsg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}RequestMessage">
 *       &lt;sequence>
 *         &lt;element name="validity" type="{http://telekom.si/schemas/common/base/v1}TimePeriod" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="taxCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="streetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *             &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="searchParameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="searchFor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}searchFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FindCustomerRequestMsg", propOrder = {
    "validity",
    "taxCode",
    "name",
    "streetName",
    "postCode",
    "searchParameter",
    "searchFor",
    "searchFilter"
})
public class FindCustomerRequestMsg
    extends RequestMessage
{

    protected TimePeriod validity;
    protected String taxCode;
    protected String name;
    protected String streetName;
    protected String postCode;
    protected String searchParameter;
    protected String searchFor;
    @XmlElement(namespace = "http://telekom.si/services/common/base/v1")
    protected SearchFilter searchFilter;

    /**
     * Gets the value of the validity property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriod }
     *     
     */
    public TimePeriod getValidity() {
        return validity;
    }

    /**
     * Sets the value of the validity property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriod }
     *     
     */
    public void setValidity(TimePeriod value) {
        this.validity = value;
    }

    /**
     * Gets the value of the taxCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * Sets the value of the taxCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxCode(String value) {
        this.taxCode = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

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
     * Gets the value of the searchFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SearchFilter }
     *     
     */
    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    /**
     * Sets the value of the searchFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchFilter }
     *     
     */
    public void setSearchFilter(SearchFilter value) {
        this.searchFilter = value;
    }

}
