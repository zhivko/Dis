
package si.telekom.schemas.common.party.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.schemas.common.party.v1 package. 
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

    private final static QName _Individual_QNAME = new QName("http://telekom.si/schemas/common/party/v1", "individual");
    private final static QName _Organization_QNAME = new QName("http://telekom.si/schemas/common/party/v1", "organization");
    private final static QName _User_QNAME = new QName("http://telekom.si/schemas/common/party/v1", "user");
    private final static QName _Party_QNAME = new QName("http://telekom.si/schemas/common/party/v1", "party");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.schemas.common.party.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Individual }
     * 
     */
    public Individual createIndividual() {
        return new Individual();
    }

    /**
     * Create an instance of {@link Organization }
     * 
     */
    public Organization createOrganization() {
        return new Organization();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Party }
     * 
     */
    public Party createParty() {
        return new Party();
    }

    /**
     * Create an instance of {@link PartyRelationCollection }
     * 
     */
    public PartyRelationCollection createPartyRelationCollection() {
        return new PartyRelationCollection();
    }

    /**
     * Create an instance of {@link RelatedUserCollection }
     * 
     */
    public RelatedUserCollection createRelatedUserCollection() {
        return new RelatedUserCollection();
    }

    /**
     * Create an instance of {@link DistributionChannel }
     * 
     */
    public DistributionChannel createDistributionChannel() {
        return new DistributionChannel();
    }

    /**
     * Create an instance of {@link PartyContactCollection }
     * 
     */
    public PartyContactCollection createPartyContactCollection() {
        return new PartyContactCollection();
    }

    /**
     * Create an instance of {@link UserDetail }
     * 
     */
    public UserDetail createUserDetail() {
        return new UserDetail();
    }

    /**
     * Create an instance of {@link PaymentAccountCollection }
     * 
     */
    public PaymentAccountCollection createPaymentAccountCollection() {
        return new PaymentAccountCollection();
    }

    /**
     * Create an instance of {@link PartyRoleType }
     * 
     */
    public PartyRoleType createPartyRoleType() {
        return new PartyRoleType();
    }

    /**
     * Create an instance of {@link UserGroupCollection }
     * 
     */
    public UserGroupCollection createUserGroupCollection() {
        return new UserGroupCollection();
    }

    /**
     * Create an instance of {@link DistributionChannelCollection }
     * 
     */
    public DistributionChannelCollection createDistributionChannelCollection() {
        return new DistributionChannelCollection();
    }

    /**
     * Create an instance of {@link PartyAddressCollection }
     * 
     */
    public PartyAddressCollection createPartyAddressCollection() {
        return new PartyAddressCollection();
    }

    /**
     * Create an instance of {@link PartyRoleCollection }
     * 
     */
    public PartyRoleCollection createPartyRoleCollection() {
        return new PartyRoleCollection();
    }

    /**
     * Create an instance of {@link PartyRole }
     * 
     */
    public PartyRole createPartyRole() {
        return new PartyRole();
    }

    /**
     * Create an instance of {@link PartyRoleTypeCollection }
     * 
     */
    public PartyRoleTypeCollection createPartyRoleTypeCollection() {
        return new PartyRoleTypeCollection();
    }

    /**
     * Create an instance of {@link PartyRelation }
     * 
     */
    public PartyRelation createPartyRelation() {
        return new PartyRelation();
    }

    /**
     * Create an instance of {@link PaymentAccount }
     * 
     */
    public PaymentAccount createPaymentAccount() {
        return new PaymentAccount();
    }

    /**
     * Create an instance of {@link BankAccount }
     * 
     */
    public BankAccount createBankAccount() {
        return new BankAccount();
    }

    /**
     * Create an instance of {@link PartyIdentificationCollection }
     * 
     */
    public PartyIdentificationCollection createPartyIdentificationCollection() {
        return new PartyIdentificationCollection();
    }

    /**
     * Create an instance of {@link PartyAddress }
     * 
     */
    public PartyAddress createPartyAddress() {
        return new PartyAddress();
    }

    /**
     * Create an instance of {@link PartyIdentification }
     * 
     */
    public PartyIdentification createPartyIdentification() {
        return new PartyIdentification();
    }

    /**
     * Create an instance of {@link BankAccountCollection }
     * 
     */
    public BankAccountCollection createBankAccountCollection() {
        return new BankAccountCollection();
    }

    /**
     * Create an instance of {@link RelatedUser }
     * 
     */
    public RelatedUser createRelatedUser() {
        return new RelatedUser();
    }

    /**
     * Create an instance of {@link PartyContact }
     * 
     */
    public PartyContact createPartyContact() {
        return new PartyContact();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Individual }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/party/v1", name = "individual")
    public JAXBElement<Individual> createIndividual(Individual value) {
        return new JAXBElement<Individual>(_Individual_QNAME, Individual.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/party/v1", name = "organization")
    public JAXBElement<Organization> createOrganization(Organization value) {
        return new JAXBElement<Organization>(_Organization_QNAME, Organization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/party/v1", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Party }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/party/v1", name = "party")
    public JAXBElement<Party> createParty(Party value) {
        return new JAXBElement<Party>(_Party_QNAME, Party.class, null, value);
    }

}
