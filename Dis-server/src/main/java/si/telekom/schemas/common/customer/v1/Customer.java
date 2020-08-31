
package si.telekom.schemas.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.party.v1.PartyRole;


/**
 * A person or organization that buys or has bought or otherwise obtained Products, Resources and/or Services from the enterprise
 * 
 * <p>Java class for Customer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Customer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/party/v1}PartyRole">
 *       &lt;sequence>
 *         &lt;element name="creditRisk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditRiskAgency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Customer", propOrder = {
    "creditRisk",
    "creditRiskAgency",
    "dueDate"
})
@XmlSeeAlso({
    CustomerDetail.class
})
public class Customer
    extends PartyRole
{

    protected String creditRisk;
    protected String creditRiskAgency;
    protected CatalogValue dueDate;

    /**
     * Gets the value of the creditRisk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditRisk() {
        return creditRisk;
    }

    /**
     * Sets the value of the creditRisk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditRisk(String value) {
        this.creditRisk = value;
    }

    /**
     * Gets the value of the creditRiskAgency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditRiskAgency() {
        return creditRiskAgency;
    }

    /**
     * Sets the value of the creditRiskAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditRiskAgency(String value) {
        this.creditRiskAgency = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setDueDate(CatalogValue value) {
        this.dueDate = value;
    }

}
