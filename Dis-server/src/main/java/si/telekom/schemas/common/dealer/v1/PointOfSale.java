
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.InvolvmentRoleCollection;
import si.telekom.schemas.common.party.v1.PartyRole;


/**
 * <p>Java class for PointOfSale complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PointOfSale">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/party/v1}PartyRole">
 *       &lt;sequence>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="directory" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointOfSaleType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="sapStorageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workingHoursCollection" type="{http://telekom.si/schemas/common/dealer/v1}WorkingHoursCollection" minOccurs="0"/>
 *         &lt;element name="salesChannelCollection" type="{http://telekom.si/schemas/common/dealer/v1}SalesChannelCollection" minOccurs="0"/>
 *         &lt;element name="salesTypeCollection" type="{http://telekom.si/schemas/common/dealer/v1}SalesTypeCollection" minOccurs="0"/>
 *         &lt;element name="representativeCollection" type="{http://telekom.si/schemas/common/dealer/v1}RepresentativeCollection" minOccurs="0"/>
 *         &lt;element name="userCollection" type="{http://telekom.si/schemas/common/dealer/v1}DealerUserCollection" minOccurs="0"/>
 *         &lt;element name="contract" type="{http://telekom.si/schemas/common/dealer/v1}Contract" minOccurs="0"/>
 *         &lt;element name="involvmentRoleCollection" type="{http://telekom.si/schemas/common/base/v1}InvolvmentRoleCollection" minOccurs="0"/>
 *         &lt;element name="rootPLMCategory" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="rootPLMCategoryFavorite" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isPhisicalyPresent" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="profitCenterId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PointOfSale", propOrder = {
    "note",
    "directory",
    "priority",
    "pointOfSaleType",
    "sapStorageId",
    "workingHoursCollection",
    "salesChannelCollection",
    "salesTypeCollection",
    "representativeCollection",
    "userCollection",
    "contract",
    "involvmentRoleCollection",
    "rootPLMCategory",
    "rootPLMCategoryFavorite",
    "isPhisicalyPresent",
    "profitCenterId"
})
public class PointOfSale
    extends PartyRole
{

    protected String note;
    protected Integer directory;
    protected String priority;
    protected CatalogValue pointOfSaleType;
    protected String sapStorageId;
    protected WorkingHoursCollection workingHoursCollection;
    protected SalesChannelCollection salesChannelCollection;
    protected SalesTypeCollection salesTypeCollection;
    protected RepresentativeCollection representativeCollection;
    protected DealerUserCollection userCollection;
    protected Contract contract;
    protected InvolvmentRoleCollection involvmentRoleCollection;
    protected Long rootPLMCategory;
    protected Long rootPLMCategoryFavorite;
    protected Integer isPhisicalyPresent;
    protected String profitCenterId;

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
     * Gets the value of the directory property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectory(Integer value) {
        this.directory = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the pointOfSaleType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPointOfSaleType() {
        return pointOfSaleType;
    }

    /**
     * Sets the value of the pointOfSaleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPointOfSaleType(CatalogValue value) {
        this.pointOfSaleType = value;
    }

    /**
     * Gets the value of the sapStorageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapStorageId() {
        return sapStorageId;
    }

    /**
     * Sets the value of the sapStorageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapStorageId(String value) {
        this.sapStorageId = value;
    }

    /**
     * Gets the value of the workingHoursCollection property.
     * 
     * @return
     *     possible object is
     *     {@link WorkingHoursCollection }
     *     
     */
    public WorkingHoursCollection getWorkingHoursCollection() {
        return workingHoursCollection;
    }

    /**
     * Sets the value of the workingHoursCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkingHoursCollection }
     *     
     */
    public void setWorkingHoursCollection(WorkingHoursCollection value) {
        this.workingHoursCollection = value;
    }

    /**
     * Gets the value of the salesChannelCollection property.
     * 
     * @return
     *     possible object is
     *     {@link SalesChannelCollection }
     *     
     */
    public SalesChannelCollection getSalesChannelCollection() {
        return salesChannelCollection;
    }

    /**
     * Sets the value of the salesChannelCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesChannelCollection }
     *     
     */
    public void setSalesChannelCollection(SalesChannelCollection value) {
        this.salesChannelCollection = value;
    }

    /**
     * Gets the value of the salesTypeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link SalesTypeCollection }
     *     
     */
    public SalesTypeCollection getSalesTypeCollection() {
        return salesTypeCollection;
    }

    /**
     * Sets the value of the salesTypeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesTypeCollection }
     *     
     */
    public void setSalesTypeCollection(SalesTypeCollection value) {
        this.salesTypeCollection = value;
    }

    /**
     * Gets the value of the representativeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RepresentativeCollection }
     *     
     */
    public RepresentativeCollection getRepresentativeCollection() {
        return representativeCollection;
    }

    /**
     * Sets the value of the representativeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepresentativeCollection }
     *     
     */
    public void setRepresentativeCollection(RepresentativeCollection value) {
        this.representativeCollection = value;
    }

    /**
     * Gets the value of the userCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DealerUserCollection }
     *     
     */
    public DealerUserCollection getUserCollection() {
        return userCollection;
    }

    /**
     * Sets the value of the userCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DealerUserCollection }
     *     
     */
    public void setUserCollection(DealerUserCollection value) {
        this.userCollection = value;
    }

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContract(Contract value) {
        this.contract = value;
    }

    /**
     * Gets the value of the involvmentRoleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link InvolvmentRoleCollection }
     *     
     */
    public InvolvmentRoleCollection getInvolvmentRoleCollection() {
        return involvmentRoleCollection;
    }

    /**
     * Sets the value of the involvmentRoleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvolvmentRoleCollection }
     *     
     */
    public void setInvolvmentRoleCollection(InvolvmentRoleCollection value) {
        this.involvmentRoleCollection = value;
    }

    /**
     * Gets the value of the rootPLMCategory property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRootPLMCategory() {
        return rootPLMCategory;
    }

    /**
     * Sets the value of the rootPLMCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRootPLMCategory(Long value) {
        this.rootPLMCategory = value;
    }

    /**
     * Gets the value of the rootPLMCategoryFavorite property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRootPLMCategoryFavorite() {
        return rootPLMCategoryFavorite;
    }

    /**
     * Sets the value of the rootPLMCategoryFavorite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRootPLMCategoryFavorite(Long value) {
        this.rootPLMCategoryFavorite = value;
    }

    /**
     * Gets the value of the isPhisicalyPresent property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIsPhisicalyPresent() {
        return isPhisicalyPresent;
    }

    /**
     * Sets the value of the isPhisicalyPresent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIsPhisicalyPresent(Integer value) {
        this.isPhisicalyPresent = value;
    }

    /**
     * Gets the value of the profitCenterId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfitCenterId() {
        return profitCenterId;
    }

    /**
     * Sets the value of the profitCenterId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfitCenterId(String value) {
        this.profitCenterId = value;
    }

}
