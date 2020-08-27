
package si.telekom.schemas.common.party.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.customer.v1.Customer;
import si.telekom.schemas.common.dealer.v1.Dealer;
import si.telekom.schemas.common.dealer.v1.PointOfSale;


/**
 * The part played by a party in a given context with any characteristics, such as expected pattern of behavior, attributes, and/or associations that it entails.
 * PartyRole is an abstract concept that should be used in places where the business refers to a Party playing a Role
 * 
 * 
 * <p>Java class for PartyRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartyRole">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="partyRoleType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="party" type="{http://telekom.si/schemas/common/party/v1}Party" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyRole", propOrder = {
    "partyRoleType",
    "party"
})
@XmlSeeAlso({
    Customer.class,
    PointOfSale.class,
    Dealer.class
})
public class PartyRole
    extends Entity
{

    protected CatalogValue partyRoleType;
    protected Party party;

    /**
     * Gets the value of the partyRoleType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPartyRoleType() {
        return partyRoleType;
    }

    /**
     * Sets the value of the partyRoleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPartyRoleType(CatalogValue value) {
        this.partyRoleType = value;
    }

    /**
     * Gets the value of the party property.
     * 
     * @return
     *     possible object is
     *     {@link Party }
     *     
     */
    public Party getParty() {
        return party;
    }

    /**
     * Sets the value of the party property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party }
     *     
     */
    public void setParty(Party value) {
        this.party = value;
    }

}
