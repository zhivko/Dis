
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.party.v1.User;


/**
 * A BusinessInteraction is an arrangement, contract, or communication between one or more Business Participants.  A BusinessInteraction may consist of one or more BusinessInteractionItems.  
 * 
 * <p>Java class for BusinessInteraction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteraction">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="originApplication" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="user" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="channel" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="referenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issue" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="origin" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="interactingRoleCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionRoleCollection" minOccurs="0"/>
 *         &lt;element name="contact" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionRole" minOccurs="0"/>
 *         &lt;element name="businessInteractionItemCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionItemCollection" minOccurs="0"/>
 *         &lt;element name="relationCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}RelationCollection" minOccurs="0"/>
 *         &lt;element name="resolvingInfo" type="{http://telekom.si/schemas/common/businessinteraction/v1}ResolvingInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteraction", propOrder = {
    "originApplication",
    "user",
    "channel",
    "referenceId",
    "issue",
    "origin",
    "startDate",
    "endDate",
    "note",
    "subject",
    "status",
    "priority",
    "interactingRoleCollection",
    "contact",
    "businessInteractionItemCollection",
    "relationCollection",
    "resolvingInfo"
})
@XmlSeeAlso({
    BusinessInteractionFax.class,
    BusinessInteractionMail.class,
    BusinessInteractionPhone.class,
    BusinessInteractionSocialMedia.class,
    BusinessInteractionPortal.class,
    BusinessInteractionIncident.class,
    BusinessInteractionEmail.class,
    BusinessInteractionAlarm.class
})
public class BusinessInteraction
    extends Entity
{

    protected CatalogValue originApplication;
    protected User user;
    protected CatalogValue channel;
    protected String referenceId;
    protected CatalogValue issue;
    protected CatalogValue origin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    protected String note;
    protected String subject;
    protected CatalogValue status;
    protected CatalogValue priority;
    protected BusinessInteractionRoleCollection interactingRoleCollection;
    protected BusinessInteractionRole contact;
    protected BusinessInteractionItemCollection businessInteractionItemCollection;
    protected RelationCollection relationCollection;
    protected ResolvingInfo resolvingInfo;

    /**
     * Gets the value of the originApplication property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getOriginApplication() {
        return originApplication;
    }

    /**
     * Sets the value of the originApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setOriginApplication(CatalogValue value) {
        this.originApplication = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setUser(User value) {
        this.user = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setChannel(CatalogValue value) {
        this.channel = value;
    }

    /**
     * Gets the value of the referenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Sets the value of the referenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceId(String value) {
        this.referenceId = value;
    }

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setIssue(CatalogValue value) {
        this.issue = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setOrigin(CatalogValue value) {
        this.origin = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
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
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
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
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPriority(CatalogValue value) {
        this.priority = value;
    }

    /**
     * Gets the value of the interactingRoleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessInteractionRoleCollection }
     *     
     */
    public BusinessInteractionRoleCollection getInteractingRoleCollection() {
        return interactingRoleCollection;
    }

    /**
     * Sets the value of the interactingRoleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessInteractionRoleCollection }
     *     
     */
    public void setInteractingRoleCollection(BusinessInteractionRoleCollection value) {
        this.interactingRoleCollection = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessInteractionRole }
     *     
     */
    public BusinessInteractionRole getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessInteractionRole }
     *     
     */
    public void setContact(BusinessInteractionRole value) {
        this.contact = value;
    }

    /**
     * Gets the value of the businessInteractionItemCollection property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessInteractionItemCollection }
     *     
     */
    public BusinessInteractionItemCollection getBusinessInteractionItemCollection() {
        return businessInteractionItemCollection;
    }

    /**
     * Sets the value of the businessInteractionItemCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessInteractionItemCollection }
     *     
     */
    public void setBusinessInteractionItemCollection(BusinessInteractionItemCollection value) {
        this.businessInteractionItemCollection = value;
    }

    /**
     * Gets the value of the relationCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RelationCollection }
     *     
     */
    public RelationCollection getRelationCollection() {
        return relationCollection;
    }

    /**
     * Sets the value of the relationCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationCollection }
     *     
     */
    public void setRelationCollection(RelationCollection value) {
        this.relationCollection = value;
    }

    /**
     * Gets the value of the resolvingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ResolvingInfo }
     *     
     */
    public ResolvingInfo getResolvingInfo() {
        return resolvingInfo;
    }

    /**
     * Sets the value of the resolvingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResolvingInfo }
     *     
     */
    public void setResolvingInfo(ResolvingInfo value) {
        this.resolvingInfo = value;
    }

}
