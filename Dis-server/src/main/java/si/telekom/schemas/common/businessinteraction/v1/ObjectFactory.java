
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.schemas.common.businessinteraction.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BusinessInteraction_QNAME = new QName("http://telekom.si/schemas/common/businessinteraction/v1", "businessInteraction");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.schemas.common.businessinteraction.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BusinessInteraction }
     * 
     */
    public BusinessInteraction createBusinessInteraction() {
        return new BusinessInteraction();
    }

    /**
     * Create an instance of {@link RelationCollection }
     * 
     */
    public RelationCollection createRelationCollection() {
        return new RelationCollection();
    }

    /**
     * Create an instance of {@link BusinessInteractionRoleCollection }
     * 
     */
    public BusinessInteractionRoleCollection createBusinessInteractionRoleCollection() {
        return new BusinessInteractionRoleCollection();
    }

    /**
     * Create an instance of {@link BusinessInteractionPartyRole }
     * 
     */
    public BusinessInteractionPartyRole createBusinessInteractionPartyRole() {
        return new BusinessInteractionPartyRole();
    }

    /**
     * Create an instance of {@link BusinessInteractionFax }
     * 
     */
    public BusinessInteractionFax createBusinessInteractionFax() {
        return new BusinessInteractionFax();
    }

    /**
     * Create an instance of {@link BusinessInteractionMail }
     * 
     */
    public BusinessInteractionMail createBusinessInteractionMail() {
        return new BusinessInteractionMail();
    }

    /**
     * Create an instance of {@link BusinessInteractionPhone }
     * 
     */
    public BusinessInteractionPhone createBusinessInteractionPhone() {
        return new BusinessInteractionPhone();
    }

    /**
     * Create an instance of {@link BusinessInteractionSocialMedia }
     * 
     */
    public BusinessInteractionSocialMedia createBusinessInteractionSocialMedia() {
        return new BusinessInteractionSocialMedia();
    }

    /**
     * Create an instance of {@link Attachment }
     * 
     */
    public Attachment createAttachment() {
        return new Attachment();
    }

    /**
     * Create an instance of {@link ResolvingInfo }
     * 
     */
    public ResolvingInfo createResolvingInfo() {
        return new ResolvingInfo();
    }

    /**
     * Create an instance of {@link BusinessInteractionRole }
     * 
     */
    public BusinessInteractionRole createBusinessInteractionRole() {
        return new BusinessInteractionRole();
    }

    /**
     * Create an instance of {@link AttachmentCollection }
     * 
     */
    public AttachmentCollection createAttachmentCollection() {
        return new AttachmentCollection();
    }

    /**
     * Create an instance of {@link BusinessInteractionPortal }
     * 
     */
    public BusinessInteractionPortal createBusinessInteractionPortal() {
        return new BusinessInteractionPortal();
    }

    /**
     * Create an instance of {@link BusinessInteractionIncident }
     * 
     */
    public BusinessInteractionIncident createBusinessInteractionIncident() {
        return new BusinessInteractionIncident();
    }

    /**
     * Create an instance of {@link Relation }
     * 
     */
    public Relation createRelation() {
        return new Relation();
    }

    /**
     * Create an instance of {@link BusinessInteractionItemCollection }
     * 
     */
    public BusinessInteractionItemCollection createBusinessInteractionItemCollection() {
        return new BusinessInteractionItemCollection();
    }

    /**
     * Create an instance of {@link TopicGroup }
     * 
     */
    public TopicGroup createTopicGroup() {
        return new TopicGroup();
    }

    /**
     * Create an instance of {@link BusinessInteractionCollection }
     * 
     */
    public BusinessInteractionCollection createBusinessInteractionCollection() {
        return new BusinessInteractionCollection();
    }

    /**
     * Create an instance of {@link BusinessInteractionEmail }
     * 
     */
    public BusinessInteractionEmail createBusinessInteractionEmail() {
        return new BusinessInteractionEmail();
    }

    /**
     * Create an instance of {@link TopicGroupCollection }
     * 
     */
    public TopicGroupCollection createTopicGroupCollection() {
        return new TopicGroupCollection();
    }

    /**
     * Create an instance of {@link BusinessInteractionItem }
     * 
     */
    public BusinessInteractionItem createBusinessInteractionItem() {
        return new BusinessInteractionItem();
    }

    /**
     * Create an instance of {@link BusinessInteractionAlarm }
     * 
     */
    public BusinessInteractionAlarm createBusinessInteractionAlarm() {
        return new BusinessInteractionAlarm();
    }

    /**
     * Create an instance of {@link TopicCollection }
     * 
     */
    public TopicCollection createTopicCollection() {
        return new TopicCollection();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessInteraction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/businessinteraction/v1", name = "businessInteraction")
    public JAXBElement<BusinessInteraction> createBusinessInteraction(BusinessInteraction value) {
        return new JAXBElement<BusinessInteraction>(_BusinessInteraction_QNAME, BusinessInteraction.class, null, value);
    }

}
