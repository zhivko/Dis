
package si.telekom.schemas.common.party.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Represents an  individual, organization or organization unit.
 * Party is an abstract concept that should be used in places where the business says something can be an organization , organization unit or an individual
 * 
 * 
 * <p>Java class for Party complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Party">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="subType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="taxCountry" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="taxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="taxPayer" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="taxRegion" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="taxFactor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identificationCollection" type="{http://telekom.si/schemas/common/party/v1}PartyIdentificationCollection" minOccurs="0"/>
 *         &lt;element name="sector" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placeOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankAccountCollection" type="{http://telekom.si/schemas/common/party/v1}BankAccountCollection" minOccurs="0"/>
 *         &lt;element name="partyRelationCollection" type="{http://telekom.si/schemas/common/party/v1}PartyRelationCollection" minOccurs="0"/>
 *         &lt;element name="addressCollection" type="{http://telekom.si/schemas/common/party/v1}PartyAddressCollection" minOccurs="0"/>
 *         &lt;element name="partyRoleTypeCollection" type="{http://telekom.si/schemas/common/party/v1}PartyRoleTypeCollection" minOccurs="0"/>
 *         &lt;element name="contactCollection" type="{http://telekom.si/schemas/common/party/v1}PartyContactCollection" minOccurs="0"/>
 *         &lt;element name="paymentAccountCollection" type="{http://telekom.si/schemas/common/party/v1}PaymentAccountCollection" minOccurs="0"/>
 *         &lt;element name="distributionChannelCollection" type="{http://telekom.si/schemas/common/party/v1}DistributionChannelCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party", propOrder = {
    "type",
    "subType",
    "taxCountry",
    "taxNo",
    "taxCode",
    "taxPayer",
    "taxRegion",
    "taxFactor",
    "identificationCollection",
    "sector",
    "dueDate",
    "birthDate",
    "placeOfBirth",
    "note",
    "bankAccountCollection",
    "partyRelationCollection",
    "addressCollection",
    "partyRoleTypeCollection",
    "contactCollection",
    "paymentAccountCollection",
    "distributionChannelCollection"
})
@XmlSeeAlso({
    Individual.class,
    Organization.class
})
public class Party
    extends Entity
{

    protected CatalogValue type;
    protected CatalogValue subType;
    protected CatalogValue taxCountry;
    protected String taxNo;
    protected String taxCode;
    protected Integer taxPayer;
    protected CatalogValue taxRegion;
    protected String taxFactor;
    protected PartyIdentificationCollection identificationCollection;
    protected CatalogValue sector;
    protected CatalogValue dueDate;
    protected String birthDate;
    protected String placeOfBirth;
    protected String note;
    protected BankAccountCollection bankAccountCollection;
    protected PartyRelationCollection partyRelationCollection;
    protected PartyAddressCollection addressCollection;
    protected PartyRoleTypeCollection partyRoleTypeCollection;
    protected PartyContactCollection contactCollection;
    protected PaymentAccountCollection paymentAccountCollection;
    protected DistributionChannelCollection distributionChannelCollection;

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
     * Gets the value of the subType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getSubType() {
        return subType;
    }

    /**
     * Sets the value of the subType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setSubType(CatalogValue value) {
        this.subType = value;
    }

    /**
     * Gets the value of the taxCountry property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getTaxCountry() {
        return taxCountry;
    }

    /**
     * Sets the value of the taxCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setTaxCountry(CatalogValue value) {
        this.taxCountry = value;
    }

    /**
     * Gets the value of the taxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxNo() {
        return taxNo;
    }

    /**
     * Sets the value of the taxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxNo(String value) {
        this.taxNo = value;
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
     * Gets the value of the taxPayer property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTaxPayer() {
        return taxPayer;
    }

    /**
     * Sets the value of the taxPayer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTaxPayer(Integer value) {
        this.taxPayer = value;
    }

    /**
     * Gets the value of the taxRegion property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getTaxRegion() {
        return taxRegion;
    }

    /**
     * Sets the value of the taxRegion property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setTaxRegion(CatalogValue value) {
        this.taxRegion = value;
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

    /**
     * Gets the value of the identificationCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyIdentificationCollection }
     *     
     */
    public PartyIdentificationCollection getIdentificationCollection() {
        return identificationCollection;
    }

    /**
     * Sets the value of the identificationCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyIdentificationCollection }
     *     
     */
    public void setIdentificationCollection(PartyIdentificationCollection value) {
        this.identificationCollection = value;
    }

    /**
     * Gets the value of the sector property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getSector() {
        return sector;
    }

    /**
     * Sets the value of the sector property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setSector(CatalogValue value) {
        this.sector = value;
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
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the placeOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the value of the placeOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceOfBirth(String value) {
        this.placeOfBirth = value;
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
     * Gets the value of the bankAccountCollection property.
     * 
     * @return
     *     possible object is
     *     {@link BankAccountCollection }
     *     
     */
    public BankAccountCollection getBankAccountCollection() {
        return bankAccountCollection;
    }

    /**
     * Sets the value of the bankAccountCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAccountCollection }
     *     
     */
    public void setBankAccountCollection(BankAccountCollection value) {
        this.bankAccountCollection = value;
    }

    /**
     * Gets the value of the partyRelationCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyRelationCollection }
     *     
     */
    public PartyRelationCollection getPartyRelationCollection() {
        return partyRelationCollection;
    }

    /**
     * Sets the value of the partyRelationCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyRelationCollection }
     *     
     */
    public void setPartyRelationCollection(PartyRelationCollection value) {
        this.partyRelationCollection = value;
    }

    /**
     * Gets the value of the addressCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyAddressCollection }
     *     
     */
    public PartyAddressCollection getAddressCollection() {
        return addressCollection;
    }

    /**
     * Sets the value of the addressCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyAddressCollection }
     *     
     */
    public void setAddressCollection(PartyAddressCollection value) {
        this.addressCollection = value;
    }

    /**
     * Gets the value of the partyRoleTypeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyRoleTypeCollection }
     *     
     */
    public PartyRoleTypeCollection getPartyRoleTypeCollection() {
        return partyRoleTypeCollection;
    }

    /**
     * Sets the value of the partyRoleTypeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyRoleTypeCollection }
     *     
     */
    public void setPartyRoleTypeCollection(PartyRoleTypeCollection value) {
        this.partyRoleTypeCollection = value;
    }

    /**
     * Gets the value of the contactCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyContactCollection }
     *     
     */
    public PartyContactCollection getContactCollection() {
        return contactCollection;
    }

    /**
     * Sets the value of the contactCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyContactCollection }
     *     
     */
    public void setContactCollection(PartyContactCollection value) {
        this.contactCollection = value;
    }

    /**
     * Gets the value of the paymentAccountCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentAccountCollection }
     *     
     */
    public PaymentAccountCollection getPaymentAccountCollection() {
        return paymentAccountCollection;
    }

    /**
     * Sets the value of the paymentAccountCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentAccountCollection }
     *     
     */
    public void setPaymentAccountCollection(PaymentAccountCollection value) {
        this.paymentAccountCollection = value;
    }

    /**
     * Gets the value of the distributionChannelCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DistributionChannelCollection }
     *     
     */
    public DistributionChannelCollection getDistributionChannelCollection() {
        return distributionChannelCollection;
    }

    /**
     * Sets the value of the distributionChannelCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributionChannelCollection }
     *     
     */
    public void setDistributionChannelCollection(DistributionChannelCollection value) {
        this.distributionChannelCollection = value;
    }

}
