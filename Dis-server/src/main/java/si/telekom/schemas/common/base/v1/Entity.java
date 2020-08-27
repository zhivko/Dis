
package si.telekom.schemas.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.businessinteraction.v1.BusinessInteraction;
import si.telekom.schemas.common.businessinteraction.v1.BusinessInteractionItem;
import si.telekom.schemas.common.businessinteraction.v1.BusinessInteractionRole;
import si.telekom.schemas.common.businessinteraction.v1.TopicGroup;
import si.telekom.schemas.common.customer.v1.CustomerAccount;
import si.telekom.schemas.common.dealer.v1.Contract;
import si.telekom.schemas.common.dealer.v1.Representative;
import si.telekom.schemas.common.dealer.v1.SalesChannel;
import si.telekom.schemas.common.dealer.v1.SalesOrganizationalUnit;
import si.telekom.schemas.common.dealer.v1.SalesType;
import si.telekom.schemas.common.dealer.v1.WorkingHours;
import si.telekom.schemas.common.location.v1.Address;
import si.telekom.schemas.common.location.v1.AddressStructured;
import si.telekom.schemas.common.location.v1.Continent;
import si.telekom.schemas.common.location.v1.Country;
import si.telekom.schemas.common.location.v1.LocalAddress;
import si.telekom.schemas.common.location.v1.Muncipality;
import si.telekom.schemas.common.location.v1.Place;
import si.telekom.schemas.common.location.v1.PostOffice;
import si.telekom.schemas.common.location.v1.Settlement;
import si.telekom.schemas.common.location.v1.Street;
import si.telekom.schemas.common.party.v1.BankAccount;
import si.telekom.schemas.common.party.v1.Party;
import si.telekom.schemas.common.party.v1.PartyContact;
import si.telekom.schemas.common.party.v1.PartyIdentification;
import si.telekom.schemas.common.party.v1.PartyRelation;
import si.telekom.schemas.common.party.v1.PartyRole;
import si.telekom.schemas.common.party.v1.PartyRoleType;
import si.telekom.schemas.common.party.v1.User;
import si.telekom.schemas.product.fulfillment.v1.AssignedProductOffering;
import si.telekom.schemas.product.fulfillment.v1.Order;
import si.telekom.schemas.product.fulfillment.v1.OrderItem;
import si.telekom.schemas.product.fulfillment.v1.Product;
import si.telekom.schemas.product.fulfillment.v1.ProductPrice;
import si.telekom.schemas.product.fulfillment.v1.ProductState;
import si.telekom.schemas.product.lifecycle.v1.PLMEntity;
import si.telekom.schemas.product.usage.v1.Usage;
import si.telekom.schemas.resource.management.v1.Resource;
import si.telekom.schemas.resource.management.v1.ResourceRelation;


/**
 * <p>Java class for Entity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Entity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validity" type="{http://telekom.si/schemas/common/base/v1}TimePeriod" minOccurs="0"/>
 *         &lt;element name="characteristicCollection" type="{http://telekom.si/schemas/common/base/v1}CharacteristicCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idCorrelation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idPrior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="updateAction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity", propOrder = {
    "id",
    "validity",
    "characteristicCollection"
})
@XmlSeeAlso({
    CustomerAccount.class,
    OrderItem.class,
    Order.class,
    AssignedProductOffering.class,
    ProductPrice.class,
    Product.class,
    si.telekom.schemas.product.fulfillment.v1.Relation.class,
    ProductState.class,
    BusinessInteractionRole.class,
    si.telekom.schemas.common.businessinteraction.v1.Relation.class,
    TopicGroup.class,
    BusinessInteractionItem.class,
    BusinessInteraction.class,
    Document.class,
    Comment.class,
    Characteristic.class,
    Content.class,
    Party.class,
    PartyRoleType.class,
    PartyRelation.class,
    BankAccount.class,
    PartyIdentification.class,
    PartyContact.class,
    Address.class,
    LocalAddress.class,
    PostOffice.class,
    AddressStructured.class,
    Settlement.class,
    Muncipality.class,
    Continent.class,
    Street.class,
    Country.class,
    Place.class,
    SalesOrganizationalUnit.class,
    PartyRole.class,
    WorkingHours.class,
    SalesChannel.class,
    User.class,
    Representative.class,
    Contract.class,
    SalesType.class,
    PLMEntity.class,
    ResourceRelation.class,
    Resource.class,
    Usage.class
})
public class Entity {

    protected String id;
    protected TimePeriod validity;
    protected CharacteristicCollection characteristicCollection;
    @XmlAttribute(name = "action")
    protected String action;
    @XmlAttribute(name = "idCorrelation")
    protected String idCorrelation;
    @XmlAttribute(name = "idPrior")
    protected String idPrior;
    @XmlAttribute(name = "updateAction")
    protected String updateAction;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

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
     * Gets the value of the characteristicCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CharacteristicCollection }
     *     
     */
    public CharacteristicCollection getCharacteristicCollection() {
        return characteristicCollection;
    }

    /**
     * Sets the value of the characteristicCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacteristicCollection }
     *     
     */
    public void setCharacteristicCollection(CharacteristicCollection value) {
        this.characteristicCollection = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the idCorrelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCorrelation() {
        return idCorrelation;
    }

    /**
     * Sets the value of the idCorrelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCorrelation(String value) {
        this.idCorrelation = value;
    }

    /**
     * Gets the value of the idPrior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPrior() {
        return idPrior;
    }

    /**
     * Sets the value of the idPrior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPrior(String value) {
        this.idPrior = value;
    }

    /**
     * Gets the value of the updateAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateAction() {
        return updateAction;
    }

    /**
     * Sets the value of the updateAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateAction(String value) {
        this.updateAction = value;
    }

}
