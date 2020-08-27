
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvolvmentRoleCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvolvmentRoleCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="involvmentRole" type="{http://telekom.si/schemas/common/base/v1}InvolvmentRole" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvolvmentRoleCollection", namespace = "http://telekom.si/schemas/common/base/v1", propOrder = {
    "involvmentRoles"
})
public class InvolvmentRoleCollection {

    @XmlElement(name = "involvmentRole")
    protected List<InvolvmentRole> involvmentRoles;

    /**
     * Gets the value of the involvmentRoles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the involvmentRoles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvolvmentRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvolvmentRole }
     * 
     * 
     */
    public List<InvolvmentRole> getInvolvmentRoles() {
        if (involvmentRoles == null) {
            involvmentRoles = new ArrayList<InvolvmentRole>();
        }
        return this.involvmentRoles;
    }

}
