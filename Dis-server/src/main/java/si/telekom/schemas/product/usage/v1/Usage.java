
package si.telekom.schemas.product.usage.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.base.v1.InvolvmentRoleCollection;


/**
 * <p>Java class for Usage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Usage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
@XmlType(name = "Usage", propOrder = {
    "date",
    "involvmentRoleCollection"
})
@XmlSeeAlso({
    ContractPenalty.class,
    Charge.class,
    Counter.class
})
public class Usage
    extends Entity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected InvolvmentRoleCollection involvmentRoleCollection;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
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
