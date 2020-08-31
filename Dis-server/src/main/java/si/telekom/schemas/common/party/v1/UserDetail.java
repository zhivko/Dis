
package si.telekom.schemas.common.party.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.dealer.v1.DealerUser;


/**
 * Represents user with detail info (with groups, superiors, subordinates,..)
 * 
 * <p>Java class for UserDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/party/v1}User">
 *       &lt;sequence>
 *         &lt;element name="userGroupCollection" type="{http://telekom.si/schemas/common/party/v1}UserGroupCollection" minOccurs="0"/>
 *         &lt;element name="relatedUserCollection" type="{http://telekom.si/schemas/common/party/v1}RelatedUserCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserDetail", propOrder = {
    "userGroupCollection",
    "relatedUserCollection"
})
@XmlSeeAlso({
    DealerUser.class
})
public class UserDetail
    extends User
{

    protected UserGroupCollection userGroupCollection;
    protected RelatedUserCollection relatedUserCollection;

    /**
     * Gets the value of the userGroupCollection property.
     * 
     * @return
     *     possible object is
     *     {@link UserGroupCollection }
     *     
     */
    public UserGroupCollection getUserGroupCollection() {
        return userGroupCollection;
    }

    /**
     * Sets the value of the userGroupCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserGroupCollection }
     *     
     */
    public void setUserGroupCollection(UserGroupCollection value) {
        this.userGroupCollection = value;
    }

    /**
     * Gets the value of the relatedUserCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedUserCollection }
     *     
     */
    public RelatedUserCollection getRelatedUserCollection() {
        return relatedUserCollection;
    }

    /**
     * Sets the value of the relatedUserCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedUserCollection }
     *     
     */
    public void setRelatedUserCollection(RelatedUserCollection value) {
        this.relatedUserCollection = value;
    }

}
