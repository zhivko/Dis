
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.party.v1.PartyRole;


/**
 * PartyRoleEntity involved in interaction (extension of BusinessInteractionRole)
 * 
 * <p>Java class for BusinessInteractionPartyRole complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionPartyRole">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionRole">
 *       &lt;sequence>
 *         &lt;element name="interactionPartyRole" type="{http://telekom.si/schemas/common/party/v1}PartyRole" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionPartyRole", propOrder = {
    "interactionPartyRole"
})
public class BusinessInteractionPartyRole
    extends BusinessInteractionRole
{

    protected PartyRole interactionPartyRole;

    /**
     * Gets the value of the interactionPartyRole property.
     * 
     * @return
     *     possible object is
     *     {@link PartyRole }
     *     
     */
    public PartyRole getInteractionPartyRole() {
        return interactionPartyRole;
    }

    /**
     * Sets the value of the interactionPartyRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyRole }
     *     
     */
    public void setInteractionPartyRole(PartyRole value) {
        this.interactionPartyRole = value;
    }

}
