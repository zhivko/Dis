
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.base.v1.InvolvmentRoleCollection;
import si.telekom.schemas.common.base.v1.TimePeriod;


/**
 * <p>Java class for Resource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Resource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="resourceIdentifier" type="{http://telekom.si/schemas/resource/management/v1}ResourceIdentifier" minOccurs="0"/>
 *         &lt;element name="externalRefId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preResCustomerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nativeManaged" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="reservationPeriod" type="{http://telekom.si/schemas/common/base/v1}TimePeriod" minOccurs="0"/>
 *         &lt;element name="status" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="involvmentRoleCollection" type="{http://telekom.si/schemas/common/base/v1}InvolvmentRoleCollection" minOccurs="0"/>
 *         &lt;element name="resourceRelationCollection" type="{http://telekom.si/schemas/resource/management/v1}ResourceRelationCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Resource", propOrder = {
    "resourceIdentifier",
    "externalRefId",
    "reservationId",
    "preResCustomerId",
    "customerId",
    "nativeManaged",
    "reservationPeriod",
    "status",
    "involvmentRoleCollection",
    "resourceRelationCollection"
})
@XmlSeeAlso({
    ConnectionSiteResource.class,
    UsernameResource.class,
    APNResource.class,
    EmailResource.class,
    SIMResource.class,
    IDVodaResource.class,
    IptvIdResource.class,
    CPEResource.class,
    CirpacAccoutCodeResource.class,
    PhoneNumberResource.class,
    VoucherResource.class,
    WSIdentResource.class,
    PhoneNumberBlockResource.class,
    IPResource.class,
    GenericIdentResource.class
})
public class Resource
    extends Entity
{

    protected ResourceIdentifier resourceIdentifier;
    protected String externalRefId;
    protected String reservationId;
    protected String preResCustomerId;
    protected String customerId;
    protected Boolean nativeManaged;
    protected TimePeriod reservationPeriod;
    protected CatalogValue status;
    protected InvolvmentRoleCollection involvmentRoleCollection;
    protected ResourceRelationCollection resourceRelationCollection;

    /**
     * Gets the value of the resourceIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceIdentifier }
     *     
     */
    public ResourceIdentifier getResourceIdentifier() {
        return resourceIdentifier;
    }

    /**
     * Sets the value of the resourceIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceIdentifier }
     *     
     */
    public void setResourceIdentifier(ResourceIdentifier value) {
        this.resourceIdentifier = value;
    }

    /**
     * Gets the value of the externalRefId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalRefId() {
        return externalRefId;
    }

    /**
     * Sets the value of the externalRefId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalRefId(String value) {
        this.externalRefId = value;
    }

    /**
     * Gets the value of the reservationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationId(String value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the preResCustomerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreResCustomerId() {
        return preResCustomerId;
    }

    /**
     * Sets the value of the preResCustomerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreResCustomerId(String value) {
        this.preResCustomerId = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the nativeManaged property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNativeManaged() {
        return nativeManaged;
    }

    /**
     * Sets the value of the nativeManaged property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNativeManaged(Boolean value) {
        this.nativeManaged = value;
    }

    /**
     * Gets the value of the reservationPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriod }
     *     
     */
    public TimePeriod getReservationPeriod() {
        return reservationPeriod;
    }

    /**
     * Sets the value of the reservationPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriod }
     *     
     */
    public void setReservationPeriod(TimePeriod value) {
        this.reservationPeriod = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setStatus(CatalogValue value) {
        this.status = value;
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
     * Gets the value of the resourceRelationCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceRelationCollection }
     *     
     */
    public ResourceRelationCollection getResourceRelationCollection() {
        return resourceRelationCollection;
    }

    /**
     * Sets the value of the resourceRelationCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceRelationCollection }
     *     
     */
    public void setResourceRelationCollection(ResourceRelationCollection value) {
        this.resourceRelationCollection = value;
    }

}
