
package si.telekom.schemas.product.fulfillment.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.CommentCollection;


/**
 * <p>Java class for FulfilmentGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FulfilmentGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="type" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *         &lt;element name="typeIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="processStatus" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *         &lt;element name="orderItemIdCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="orderItemId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="processFault" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *         &lt;element name="statusHistory" type="{http://telekom.si/schemas/product/fulfillment/v1}FulfilmentGroupStatusCollection" minOccurs="0"/>
 *         &lt;element name="fulfilmentGroupCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}FulfilmentGroupCollection" minOccurs="0"/>
 *         &lt;element name="commentCollection" type="{http://telekom.si/schemas/common/base/v1}CommentCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FulfilmentGroup", propOrder = {
    "id",
    "type",
    "typeIdentifier",
    "typeDescription",
    "level",
    "processStatus",
    "orderItemIdCollection",
    "processFault",
    "status",
    "statusHistory",
    "fulfilmentGroupCollection",
    "commentCollection"
})
public class FulfilmentGroup {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected CatalogValue type;
    protected String typeIdentifier;
    protected String typeDescription;
    @XmlElement(required = true)
    protected String level;
    @XmlElement(required = true)
    protected CatalogValue processStatus;
    protected FulfilmentGroup.OrderItemIdCollection orderItemIdCollection;
    protected String processFault;
    @XmlElement(required = true)
    protected CatalogValue status;
    protected FulfilmentGroupStatusCollection statusHistory;
    protected FulfilmentGroupCollection fulfilmentGroupCollection;
    protected CommentCollection commentCollection;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setType(CatalogValue value) {
        this.type = value;
    }

    /**
     * Gets the value of the typeIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeIdentifier() {
        return typeIdentifier;
    }

    /**
     * Sets the value of the typeIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeIdentifier(String value) {
        this.typeIdentifier = value;
    }

    /**
     * Gets the value of the typeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeDescription() {
        return typeDescription;
    }

    /**
     * Sets the value of the typeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeDescription(String value) {
        this.typeDescription = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the processStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getProcessStatus() {
        return processStatus;
    }

    /**
     * Sets the value of the processStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setProcessStatus(CatalogValue value) {
        this.processStatus = value;
    }

    /**
     * Gets the value of the orderItemIdCollection property.
     * 
     * @return
     *     possible object is
     *     {@link FulfilmentGroup.OrderItemIdCollection }
     *     
     */
    public FulfilmentGroup.OrderItemIdCollection getOrderItemIdCollection() {
        return orderItemIdCollection;
    }

    /**
     * Sets the value of the orderItemIdCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link FulfilmentGroup.OrderItemIdCollection }
     *     
     */
    public void setOrderItemIdCollection(FulfilmentGroup.OrderItemIdCollection value) {
        this.orderItemIdCollection = value;
    }

    /**
     * Gets the value of the processFault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessFault() {
        return processFault;
    }

    /**
     * Sets the value of the processFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessFault(String value) {
        this.processFault = value;
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
     * Gets the value of the statusHistory property.
     * 
     * @return
     *     possible object is
     *     {@link FulfilmentGroupStatusCollection }
     *     
     */
    public FulfilmentGroupStatusCollection getStatusHistory() {
        return statusHistory;
    }

    /**
     * Sets the value of the statusHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link FulfilmentGroupStatusCollection }
     *     
     */
    public void setStatusHistory(FulfilmentGroupStatusCollection value) {
        this.statusHistory = value;
    }

    /**
     * Gets the value of the fulfilmentGroupCollection property.
     * 
     * @return
     *     possible object is
     *     {@link FulfilmentGroupCollection }
     *     
     */
    public FulfilmentGroupCollection getFulfilmentGroupCollection() {
        return fulfilmentGroupCollection;
    }

    /**
     * Sets the value of the fulfilmentGroupCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link FulfilmentGroupCollection }
     *     
     */
    public void setFulfilmentGroupCollection(FulfilmentGroupCollection value) {
        this.fulfilmentGroupCollection = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="orderItemId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orderItemId"
    })
    public static class OrderItemIdCollection {

        @XmlElement(required = true)
        protected List<String> orderItemId;

        /**
         * Gets the value of the orderItemId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the orderItemId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getOrderItemId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getOrderItemId() {
            if (orderItemId == null) {
                orderItemId = new ArrayList<String>();
            }
            return this.orderItemId;
        }

    }

}
