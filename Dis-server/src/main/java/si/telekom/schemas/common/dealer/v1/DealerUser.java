
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.InvolvmentRoleCollection;
import si.telekom.schemas.common.party.v1.UserDetail;


/**
 * <p>Java class for DealerUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DealerUser">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/party/v1}UserDetail">
 *       &lt;sequence>
 *         &lt;element name="sapId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCollection" type="{http://telekom.si/schemas/common/dealer/v1}DealerUserRoleCollection" minOccurs="0"/>
 *         &lt;element name="involvmentRoleCollection" type="{http://telekom.si/schemas/common/base/v1}InvolvmentRoleCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DealerUser", propOrder = {
    "sapId",
    "priority",
    "roleCollection",
    "involvmentRoleCollection"
})
public class DealerUser
    extends UserDetail
{

    protected String sapId;
    protected String priority;
    protected DealerUserRoleCollection roleCollection;
    protected InvolvmentRoleCollection involvmentRoleCollection;

    /**
     * Gets the value of the sapId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapId() {
        return sapId;
    }

    /**
     * Sets the value of the sapId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapId(String value) {
        this.sapId = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the roleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DealerUserRoleCollection }
     *     
     */
    public DealerUserRoleCollection getRoleCollection() {
        return roleCollection;
    }

    /**
     * Sets the value of the roleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DealerUserRoleCollection }
     *     
     */
    public void setRoleCollection(DealerUserRoleCollection value) {
        this.roleCollection = value;
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

}
