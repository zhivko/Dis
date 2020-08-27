
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Item/Entity that was involved or was purpose of Business Interaction
 * 
 * <p>Java class for BusinessInteractionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="businessInteractionRoleCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionRoleCollection" minOccurs="0"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="topicGroupCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}TopicGroupCollection" minOccurs="0"/>
 *         &lt;element name="attachmentCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}AttachmentCollection" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionItem", propOrder = {
    "businessInteractionRoleCollection",
    "subject",
    "topicGroupCollection",
    "attachmentCollection",
    "note"
})
public class BusinessInteractionItem
    extends Entity
{

    protected BusinessInteractionRoleCollection businessInteractionRoleCollection;
    protected String subject;
    protected TopicGroupCollection topicGroupCollection;
    protected AttachmentCollection attachmentCollection;
    protected String note;

    /**
     * Gets the value of the businessInteractionRoleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessInteractionRoleCollection }
     *     
     */
    public BusinessInteractionRoleCollection getBusinessInteractionRoleCollection() {
        return businessInteractionRoleCollection;
    }

    /**
     * Sets the value of the businessInteractionRoleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessInteractionRoleCollection }
     *     
     */
    public void setBusinessInteractionRoleCollection(BusinessInteractionRoleCollection value) {
        this.businessInteractionRoleCollection = value;
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
     * Gets the value of the topicGroupCollection property.
     * 
     * @return
     *     possible object is
     *     {@link TopicGroupCollection }
     *     
     */
    public TopicGroupCollection getTopicGroupCollection() {
        return topicGroupCollection;
    }

    /**
     * Sets the value of the topicGroupCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopicGroupCollection }
     *     
     */
    public void setTopicGroupCollection(TopicGroupCollection value) {
        this.topicGroupCollection = value;
    }

    /**
     * Gets the value of the attachmentCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AttachmentCollection }
     *     
     */
    public AttachmentCollection getAttachmentCollection() {
        return attachmentCollection;
    }

    /**
     * Sets the value of the attachmentCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttachmentCollection }
     *     
     */
    public void setAttachmentCollection(AttachmentCollection value) {
        this.attachmentCollection = value;
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

}
