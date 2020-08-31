
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.party.v1.User;


/**
 * <p>Java class for ResolvingInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResolvingInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="assignedTo" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="assignedSalesOrganizationalUnitId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="solvedBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="solvedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResolvingInfo", propOrder = {
    "assignedTo",
    "assignedSalesOrganizationalUnitId",
    "solvedBy",
    "dueDate",
    "solvedDate"
})
public class ResolvingInfo {

    protected User assignedTo;
    protected Long assignedSalesOrganizationalUnitId;
    protected User solvedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar solvedDate;

    /**
     * Gets the value of the assignedTo property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getAssignedTo() {
        return assignedTo;
    }

    /**
     * Sets the value of the assignedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setAssignedTo(User value) {
        this.assignedTo = value;
    }

    /**
     * Gets the value of the assignedSalesOrganizationalUnitId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAssignedSalesOrganizationalUnitId() {
        return assignedSalesOrganizationalUnitId;
    }

    /**
     * Sets the value of the assignedSalesOrganizationalUnitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAssignedSalesOrganizationalUnitId(Long value) {
        this.assignedSalesOrganizationalUnitId = value;
    }

    /**
     * Gets the value of the solvedBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getSolvedBy() {
        return solvedBy;
    }

    /**
     * Sets the value of the solvedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setSolvedBy(User value) {
        this.solvedBy = value;
    }

    /**
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the solvedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSolvedDate() {
        return solvedDate;
    }

    /**
     * Sets the value of the solvedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSolvedDate(XMLGregorianCalendar value) {
        this.solvedDate = value;
    }

}
