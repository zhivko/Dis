
package si.telekom.schemas.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.base.v1.InvolvmentRole;
import si.telekom.schemas.common.party.v1.DistributionChannel;
import si.telekom.schemas.common.party.v1.PartyAddress;
import si.telekom.schemas.common.party.v1.PartyContactCollection;
import si.telekom.schemas.common.party.v1.PaymentAccount;


/**
 * An arrangement that a Customer has with an enterprise that provides Products to the Customer.
 * 
 * <p>Java class for CustomerAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerAccount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="involvmentRole" type="{http://telekom.si/schemas/common/base/v1}InvolvmentRole" minOccurs="0"/>
 *         &lt;element name="type" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="address" type="{http://telekom.si/schemas/common/party/v1}PartyAddress" minOccurs="0"/>
 *         &lt;element name="partyContactCollection" type="{http://telekom.si/schemas/common/party/v1}PartyContactCollection" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="distributionChannel" type="{http://telekom.si/schemas/common/party/v1}DistributionChannel" minOccurs="0"/>
 *         &lt;element name="paymentAccount" type="{http://telekom.si/schemas/common/party/v1}PaymentAccount" minOccurs="0"/>
 *         &lt;element name="invoiceLayout" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalProperty1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalProperty2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxFactor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerAccount", propOrder = {
    "involvmentRole",
    "type",
    "address",
    "partyContactCollection",
    "dueDate",
    "distributionChannel",
    "paymentAccount",
    "invoiceLayout",
    "note",
    "additionalProperty1",
    "additionalProperty2",
    "taxFactor"
})
@XmlSeeAlso({
    CustomerAccountDetail.class
})
public class CustomerAccount
    extends Entity
{

    protected InvolvmentRole involvmentRole;
    protected CatalogValue type;
    protected PartyAddress address;
    protected PartyContactCollection partyContactCollection;
    protected CatalogValue dueDate;
    protected DistributionChannel distributionChannel;
    protected PaymentAccount paymentAccount;
    protected CatalogValue invoiceLayout;
    protected String note;
    protected String additionalProperty1;
    protected String additionalProperty2;
    protected String taxFactor;

    /**
     * Gets the value of the involvmentRole property.
     * 
     * @return
     *     possible object is
     *     {@link InvolvmentRole }
     *     
     */
    public InvolvmentRole getInvolvmentRole() {
        return involvmentRole;
    }

    /**
     * Sets the value of the involvmentRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvolvmentRole }
     *     
     */
    public void setInvolvmentRole(InvolvmentRole value) {
        this.involvmentRole = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setType(CatalogValue value) {
        this.type = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link PartyAddress }
     *     
     */
    public PartyAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyAddress }
     *     
     */
    public void setAddress(PartyAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the partyContactCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyContactCollection }
     *     
     */
    public PartyContactCollection getPartyContactCollection() {
        return partyContactCollection;
    }

    /**
     * Sets the value of the partyContactCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyContactCollection }
     *     
     */
    public void setPartyContactCollection(PartyContactCollection value) {
        this.partyContactCollection = value;
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

    /**
     * Gets the value of the distributionChannel property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionChannel }
     *     
     */
    public DistributionChannel getDistributionChannel() {
        return distributionChannel;
    }

    /**
     * Sets the value of the distributionChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionChannel }
     *     
     */
    public void setDistributionChannel(DistributionChannel value) {
        this.distributionChannel = value;
    }

    /**
     * Gets the value of the paymentAccount property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentAccount }
     *     
     */
    public PaymentAccount getPaymentAccount() {
        return paymentAccount;
    }

    /**
     * Sets the value of the paymentAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentAccount }
     *     
     */
    public void setPaymentAccount(PaymentAccount value) {
        this.paymentAccount = value;
    }

    /**
     * Gets the value of the invoiceLayout property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getInvoiceLayout() {
        return invoiceLayout;
    }

    /**
     * Sets the value of the invoiceLayout property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setInvoiceLayout(CatalogValue value) {
        this.invoiceLayout = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the additionalProperty1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalProperty1() {
        return additionalProperty1;
    }

    /**
     * Sets the value of the additionalProperty1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalProperty1(String value) {
        this.additionalProperty1 = value;
    }

    /**
     * Gets the value of the additionalProperty2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalProperty2() {
        return additionalProperty2;
    }

    /**
     * Sets the value of the additionalProperty2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalProperty2(String value) {
        this.additionalProperty2 = value;
    }

    /**
     * Gets the value of the taxFactor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxFactor() {
        return taxFactor;
    }

    /**
     * Sets the value of the taxFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxFactor(String value) {
        this.taxFactor = value;
    }

}
