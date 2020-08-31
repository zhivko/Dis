
package si.telekom.schemas.common.party.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Represents party role type that individual or organization is playing (e.g. customer, supplier, employee)
 * 
 * <p>Java class for PartyRoleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartyRoleType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="partyRoleType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyRoleType", propOrder = {
    "partyRoleType"
})
public class PartyRoleType
    extends Entity
{

    @XmlElement(required = true)
    protected CatalogValue partyRoleType;

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

}
