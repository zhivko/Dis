
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.CommentCollection;
import si.telekom.schemas.common.base.v1.TimePeriod;
import si.telekom.schemas.common.party.v1.PartyContactCollection;


/**
 * <p>Java class for ProductOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductOrderItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/fulfillment/v1}ProductDetail">
 *       &lt;sequence>
 *         &lt;element name="orderItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemStatus" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="assignedOfferingIdPrior" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ActionCollection" minOccurs="0"/>
 *         &lt;element name="recordStatus" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="distributionType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="scheduledOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="appointmentPeriod" type="{http://telekom.si/schemas/common/base/v1}TimePeriod" minOccurs="0"/>
 *         &lt;element name="desiredOn" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partyContactCollection" type="{http://telekom.si/schemas/common/party/v1}PartyContactCollection" minOccurs="0"/>
 *         &lt;element name="provisionendState" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="provisioningType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="actionOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paymentType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="paymentState" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="commentCollection" type="{http://telekom.si/schemas/common/base/v1}CommentCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductOrderItem", propOrder = {
    "orderItemId",
    "orderItemStatus",
    "assignedOfferingIdPrior",
    "actionCollection",
    "recordStatus",
    "distributionType",
    "scheduledOn",
    "appointmentPeriod",
    "desiredOn",
    "reservationId",
    "partyContactCollection",
    "provisionendState",
    "provisioningType",
    "actionOn",
    "paymentType",
    "paymentState",
    "commentCollection"
})
public class ProductOrderItem
    extends ProductDetail
{

    protected String orderItemId;
    protected CatalogValue orderItemStatus;
    protected String assignedOfferingIdPrior;
    protected ActionCollection actionCollection;
    protected CatalogValue recordStatus;
    protected CatalogValue distributionType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar scheduledOn;
    protected TimePeriod appointmentPeriod;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar desiredOn;
    protected String reservationId;
    protected PartyContactCollection partyContactCollection;
    protected CatalogValue provisionendState;
    protected CatalogValue provisioningType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar actionOn;
    protected CatalogValue paymentType;
    protected CatalogValue paymentState;
    protected CommentCollection commentCollection;

    /**
     * Gets the value of the orderItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemId() {
        return orderItemId;
    }

    /**
     * Sets the value of the orderItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemId(String value) {
        this.orderItemId = value;
    }

    /**
     * Gets the value of the orderItemStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getOrderItemStatus() {
        return orderItemStatus;
    }

    /**
     * Sets the value of the orderItemStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setOrderItemStatus(CatalogValue value) {
        this.orderItemStatus = value;
    }

    /**
     * Gets the value of the assignedOfferingIdPrior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedOfferingIdPrior() {
        return assignedOfferingIdPrior;
    }

    /**
     * Sets the value of the assignedOfferingIdPrior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedOfferingIdPrior(String value) {
        this.assignedOfferingIdPrior = value;
    }

    /**
     * Gets the value of the actionCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ActionCollection }
     *     
     */
    public ActionCollection getActionCollection() {
        return actionCollection;
    }

    /**
     * Sets the value of the actionCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionCollection }
     *     
     */
    public void setActionCollection(ActionCollection value) {
        this.actionCollection = value;
    }

    /**
     * Gets the value of the recordStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getRecordStatus() {
        return recordStatus;
    }

    /**
     * Sets the value of the recordStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setRecordStatus(CatalogValue value) {
        this.recordStatus = value;
    }

    /**
     * Gets the value of the distributionType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getDistributionType() {
        return distributionType;
    }

    /**
     * Sets the value of the distributionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setDistributionType(CatalogValue value) {
        this.distributionType = value;
    }

    /**
     * Gets the value of the scheduledOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScheduledOn() {
        return scheduledOn;
    }

    /**
     * Sets the value of the scheduledOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScheduledOn(XMLGregorianCalendar value) {
        this.scheduledOn = value;
    }

    /**
     * Gets the value of the appointmentPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriod }
     *     
     */
    public TimePeriod getAppointmentPeriod() {
        return appointmentPeriod;
    }

    /**
     * Sets the value of the appointmentPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriod }
     *     
     */
    public void setAppointmentPeriod(TimePeriod value) {
        this.appointmentPeriod = value;
    }

    /**
     * Gets the value of the desiredOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDesiredOn() {
        return desiredOn;
    }

    /**
     * Sets the value of the desiredOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDesiredOn(XMLGregorianCalendar value) {
        this.desiredOn = value;
    }

    /**
     * Gets the value of the reservationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationId(String value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the partyContactCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PartyContactCollection }
     *     
     */
    public PartyContactCollection getPartyContactCollection() {
        return partyContactCollection;
    }

    /**
     * Sets the value of the partyContactCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyContactCollection }
     *     
     */
    public void setPartyContactCollection(PartyContactCollection value) {
        this.partyContactCollection = value;
    }

    /**
     * Gets the value of the provisionendState property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getProvisionendState() {
        return provisionendState;
    }

    /**
     * Sets the value of the provisionendState property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setProvisionendState(CatalogValue value) {
        this.provisionendState = value;
    }

    /**
     * Gets the value of the provisioningType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getProvisioningType() {
        return provisioningType;
    }

    /**
     * Sets the value of the provisioningType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setProvisioningType(CatalogValue value) {
        this.provisioningType = value;
    }

    /**
     * Gets the value of the actionOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActionOn() {
        return actionOn;
    }

    /**
     * Sets the value of the actionOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActionOn(XMLGregorianCalendar value) {
        this.actionOn = value;
    }

    /**
     * Gets the value of the paymentType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPaymentType() {
        return paymentType;
    }

    /**
     * Sets the value of the paymentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPaymentType(CatalogValue value) {
        this.paymentType = value;
    }

    /**
     * Gets the value of the paymentState property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPaymentState() {
        return paymentState;
    }

    /**
     * Sets the value of the paymentState property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPaymentState(CatalogValue value) {
        this.paymentState = value;
    }

    /**
     * Gets the value of the commentCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CommentCollection }
     *     
     */
    public CommentCollection getCommentCollection() {
        return commentCollection;
    }

    /**
     * Sets the value of the commentCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommentCollection }
     *     
     */
    public void setCommentCollection(CommentCollection value) {
        this.commentCollection = value;
    }

}
