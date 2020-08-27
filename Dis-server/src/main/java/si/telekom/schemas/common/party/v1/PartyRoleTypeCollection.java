
package si.telekom.schemas.common.party.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Collection of party role types for party
 * 
 * <p>Java class for PartyRoleTypeCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartyRoleTypeCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="partyRoleType" type="{http://telekom.si/schemas/common/party/v1}PartyRoleType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyRoleTypeCollection", propOrder = {
    "partyRoleType"
})
public class PartyRoleTypeCollection {

    protected List<PartyRoleType> partyRoleType;

    /**
     * Gets the value of the partyRoleType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partyRoleType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartyRoleType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyRoleType }
     * 
     * 
     */
    public List<PartyRoleType> getPartyRoleType() {
        if (partyRoleType == null) {
            partyRoleType = new ArrayList<PartyRoleType>();
        }
        return this.partyRoleType;
    }

}
