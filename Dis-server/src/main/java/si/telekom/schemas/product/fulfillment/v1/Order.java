
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.CommentCollection;
import si.telekom.schemas.common.base.v1.DocumentCollection;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.base.v1.ParameterCollection;
import si.telekom.schemas.common.businessinteraction.v1.BusinessInteractionCollection;
import si.telekom.schemas.common.customer.v1.CustomerAccountCollection;
import si.telekom.schemas.common.customer.v1.CustomerCollection;
import si.telekom.schemas.common.dealer.v1.Dealer;
import si.telekom.schemas.common.location.v1.PlaceCollection;
import si.telekom.schemas.common.party.v1.User;
import si.telekom.schemas.resource.management.v1.ResourceCollection;


/**
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="relatedPlaceCollection" type="{http://telekom.si/schemas/common/location/v1}PlaceCollection" minOccurs="0"/>
 *         &lt;element name="resourceCollection" type="{http://telekom.si/schemas/resource/management/v1}ResourceCollection" minOccurs="0"/>
 *         &lt;element name="customerCollection" type="{http://telekom.si/schemas/common/customer/v1}CustomerCollection" minOccurs="0"/>
 *         &lt;element name="customerAccountCollection" type="{http://telekom.si/schemas/common/customer/v1}CustomerAccountCollection" minOccurs="0"/>
 *         &lt;element name="orderItemCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}OrderItemCollection" minOccurs="0"/>
 *         &lt;element name="status" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="businessInteractionCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteractionCollection" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soldAt" type="{http://telekom.si/schemas/common/dealer/v1}Dealer" minOccurs="0"/>
 *         &lt;element name="soldBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="enteredAt" type="{http://telekom.si/schemas/common/dealer/v1}Dealer" minOccurs="0"/>
 *         &lt;element name="enteredBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="documentCollection" type="{http://telekom.si/schemas/common/base/v1}DocumentCollection" minOccurs="0"/>
 *         &lt;element name="commentCollection" type="{http://telekom.si/schemas/common/base/v1}CommentCollection" minOccurs="0"/>
 *         &lt;element name="orderContactCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}OrderContactCollection" minOccurs="0"/>
 *         &lt;element name="orderOptionsCollection" type="{http://telekom.si/schemas/common/base/v1}ParameterCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="orderAction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "relatedPlaceCollection",
    "resourceCollection",
    "customerCollection",
    "customerAccountCollection",
    "orderItemCollection",
    "status",
    "businessInteractionCollection",
    "customerId",
    "soldAt",
    "soldBy",
    "enteredAt",
    "enteredBy",
    "documentCollection",
    "commentCollection",
    "orderContactCollection",
    "orderOptionsCollection"
})
public class Order
    extends Entity
{

    protected PlaceCollection relatedPlaceCollection;
    protected ResourceCollection resourceCollection;
    protected CustomerCollection customerCollection;
    protected CustomerAccountCollection customerAccountCollection;
    protected OrderItemCollection orderItemCollection;
    protected CatalogValue status;
    protected BusinessInteractionCollection businessInteractionCollection;
    protected String customerId;
    protected Dealer soldAt;
    protected User soldBy;
    protected Dealer enteredAt;
    protected User enteredBy;
    protected DocumentCollection documentCollection;
    protected CommentCollection commentCollection;
    protected OrderContactCollection orderContactCollection;
    protected ParameterCollection orderOptionsCollection;
    @XmlAttribute(name = "orderAction")
    protected String orderAction;

    /**
     * Gets the value of the relatedPlaceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceCollection }
     *     
     */
    public PlaceCollection getRelatedPlaceCollection() {
        return relatedPlaceCollection;
    }

    /**
     * Sets the value of the relatedPlaceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceCollection }
     *     
     */
    public void setRelatedPlaceCollection(PlaceCollection value) {
        this.relatedPlaceCollection = value;
    }

    /**
     * Gets the value of the resourceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ResourceCollection }
     *     
     */
    public ResourceCollection getResourceCollection() {
        return resourceCollection;
    }

    /**
     * Sets the value of the resourceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResourceCollection }
     *     
     */
    public void setResourceCollection(ResourceCollection value) {
        this.resourceCollection = value;
    }

    /**
     * Gets the value of the customerCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerCollection }
     *     
     */
    public CustomerCollection getCustomerCollection() {
        return customerCollection;
    }

    /**
     * Sets the value of the customerCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerCollection }
     *     
     */
    public void setCustomerCollection(CustomerCollection value) {
        this.customerCollection = value;
    }

    /**
     * Gets the value of the customerAccountCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerAccountCollection }
     *     
     */
    public CustomerAccountCollection getCustomerAccountCollection() {
        return customerAccountCollection;
    }

    /**
     * Sets the value of the customerAccountCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerAccountCollection }
     *     
     */
    public void setCustomerAccountCollection(CustomerAccountCollection value) {
        this.customerAccountCollection = value;
    }

    /**
     * Gets the value of the orderItemCollection property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemCollection }
     *     
     */
    public OrderItemCollection getOrderItemCollection() {
        return orderItemCollection;
    }

    /**
     * Sets the value of the orderItemCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemCollection }
     *     
     */
    public void setOrderItemCollection(OrderItemCollection value) {
        this.orderItemCollection = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setStatus(CatalogValue value) {
        this.status = value;
    }

    /**
     * Gets the value of the businessInteractionCollection property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessInteractionCollection }
     *     
     */
    public BusinessInteractionCollection getBusinessInteractionCollection() {
        return businessInteractionCollection;
    }

    /**
     * Sets the value of the businessInteractionCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessInteractionCollection }
     *     
     */
    public void setBusinessInteractionCollection(BusinessInteractionCollection value) {
        this.businessInteractionCollection = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the soldAt property.
     * 
     * @return
     *     possible object is
     *     {@link Dealer }
     *     
     */
    public Dealer getSoldAt() {
        return soldAt;
    }

    /**
     * Sets the value of the soldAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dealer }
     *     
     */
    public void setSoldAt(Dealer value) {
        this.soldAt = value;
    }

    /**
     * Gets the value of the soldBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getSoldBy() {
        return soldBy;
    }

    /**
     * Sets the value of the soldBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setSoldBy(User value) {
        this.soldBy = value;
    }

    /**
     * Gets the value of the enteredAt property.
     * 
     * @return
     *     possible object is
     *     {@link Dealer }
     *     
     */
    public Dealer getEnteredAt() {
        return enteredAt;
    }

    /**
     * Sets the value of the enteredAt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Dealer }
     *     
     */
    public void setEnteredAt(Dealer value) {
        this.enteredAt = value;
    }

    /**
     * Gets the value of the enteredBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getEnteredBy() {
        return enteredBy;
    }

    /**
     * Sets the value of the enteredBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setEnteredBy(User value) {
        this.enteredBy = value;
    }

    /**
     * Gets the value of the documentCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentCollection }
     *     
     */
    public DocumentCollection getDocumentCollection() {
        return documentCollection;
    }

    /**
     * Sets the value of the documentCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentCollection }
     *     
     */
    public void setDocumentCollection(DocumentCollection value) {
        this.documentCollection = value;
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

    /**
     * Gets the value of the orderContactCollection property.
     * 
     * @return
     *     possible object is
     *     {@link OrderContactCollection }
     *     
     */
    public OrderContactCollection getOrderContactCollection() {
        return orderContactCollection;
    }

    /**
     * Sets the value of the orderContactCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderContactCollection }
     *     
     */
    public void setOrderContactCollection(OrderContactCollection value) {
        this.orderContactCollection = value;
    }

    /**
     * Gets the value of the orderOptionsCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterCollection }
     *     
     */
    public ParameterCollection getOrderOptionsCollection() {
        return orderOptionsCollection;
    }

    /**
     * Sets the value of the orderOptionsCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterCollection }
     *     
     */
    public void setOrderOptionsCollection(ParameterCollection value) {
        this.orderOptionsCollection = value;
    }

    /**
     * Gets the value of the orderAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderAction() {
        return orderAction;
    }

    /**
     * Sets the value of the orderAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderAction(String value) {
        this.orderAction = value;
    }

}
