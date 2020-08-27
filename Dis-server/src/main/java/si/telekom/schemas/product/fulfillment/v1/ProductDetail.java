
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.DocumentCollection;
import si.telekom.schemas.common.dealer.v1.Dealer;
import si.telekom.schemas.common.party.v1.User;
import si.telekom.schemas.product.usage.v1.UsageCollection;


/**
 * <p>Java class for ProductDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/fulfillment/v1}Product">
 *       &lt;sequence>
 *         &lt;element name="idPriorParent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageCollection" type="{http://telekom.si/schemas/product/usage/v1}UsageCollection" minOccurs="0"/>
 *         &lt;element name="composedProductCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductCollection" minOccurs="0"/>
 *         &lt;element name="compositionRelationType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="relatedProductCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}RelatedProductCollection" minOccurs="0"/>
 *         &lt;element name="inverselyRelatedProductCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}RelatedProductCollection" minOccurs="0"/>
 *         &lt;element name="relatedResourceCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}RelatedResourceCollection" minOccurs="0"/>
 *         &lt;element name="relatedDocumentCollection" type="{http://telekom.si/schemas/common/base/v1}DocumentCollection" minOccurs="0"/>
 *         &lt;element name="productStateCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductStateCollection" minOccurs="0"/>
 *         &lt;element name="soldAt" type="{http://telekom.si/schemas/common/dealer/v1}Dealer" minOccurs="0"/>
 *         &lt;element name="soldBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="enteredAt" type="{http://telekom.si/schemas/common/dealer/v1}Dealer" minOccurs="0"/>
 *         &lt;element name="enteredBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="provisionendBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="lastChangeBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="lastChangeByOrderItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductDetail", propOrder = {
    "idPriorParent",
    "usageCollection",
    "composedProductCollection",
    "compositionRelationType",
    "relatedProductCollection",
    "inverselyRelatedProductCollection",
    "relatedResourceCollection",
    "relatedDocumentCollection",
    "productStateCollection",
    "soldAt",
    "soldBy",
    "enteredAt",
    "enteredBy",
    "provisionendBy",
    "lastChangeBy",
    "lastChangeByOrderItemId",
    "note"
})
@XmlSeeAlso({
    ProductOrderItem.class
})
public class ProductDetail
    extends Product
{

    protected String idPriorParent;
    protected UsageCollection usageCollection;
    protected ProductCollection composedProductCollection;
    protected CatalogValue compositionRelationType;
    protected RelatedProductCollection relatedProductCollection;
    protected RelatedProductCollection inverselyRelatedProductCollection;
    protected RelatedResourceCollection relatedResourceCollection;
    protected DocumentCollection relatedDocumentCollection;
    protected ProductStateCollection productStateCollection;
    protected Dealer soldAt;
    protected User soldBy;
    protected Dealer enteredAt;
    protected User enteredBy;
    protected User provisionendBy;
    protected User lastChangeBy;
    protected String lastChangeByOrderItemId;
    protected String note;

    /**
     * Gets the value of the idPriorParent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPriorParent() {
        return idPriorParent;
    }

    /**
     * Sets the value of the idPriorParent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPriorParent(String value) {
        this.idPriorParent = value;
    }

    /**
     * Gets the value of the usageCollection property.
     * 
     * @return
     *     possible object is
     *     {@link UsageCollection }
     *     
     */
    public UsageCollection getUsageCollection() {
        return usageCollection;
    }

    /**
     * Sets the value of the usageCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsageCollection }
     *     
     */
    public void setUsageCollection(UsageCollection value) {
        this.usageCollection = value;
    }

    /**
     * Gets the value of the composedProductCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCollection }
     *     
     */
    public ProductCollection getComposedProductCollection() {
        return composedProductCollection;
    }

    /**
     * Sets the value of the composedProductCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCollection }
     *     
     */
    public void setComposedProductCollection(ProductCollection value) {
        this.composedProductCollection = value;
    }

    /**
     * Gets the value of the compositionRelationType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getCompositionRelationType() {
        return compositionRelationType;
    }

    /**
     * Sets the value of the compositionRelationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setCompositionRelationType(CatalogValue value) {
        this.compositionRelationType = value;
    }

    /**
     * Gets the value of the relatedProductCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedProductCollection }
     *     
     */
    public RelatedProductCollection getRelatedProductCollection() {
        return relatedProductCollection;
    }

    /**
     * Sets the value of the relatedProductCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedProductCollection }
     *     
     */
    public void setRelatedProductCollection(RelatedProductCollection value) {
        this.relatedProductCollection = value;
    }

    /**
     * Gets the value of the inverselyRelatedProductCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedProductCollection }
     *     
     */
    public RelatedProductCollection getInverselyRelatedProductCollection() {
        return inverselyRelatedProductCollection;
    }

    /**
     * Sets the value of the inverselyRelatedProductCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedProductCollection }
     *     
     */
    public void setInverselyRelatedProductCollection(RelatedProductCollection value) {
        this.inverselyRelatedProductCollection = value;
    }

    /**
     * Gets the value of the relatedResourceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RelatedResourceCollection }
     *     
     */
    public RelatedResourceCollection getRelatedResourceCollection() {
        return relatedResourceCollection;
    }

    /**
     * Sets the value of the relatedResourceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelatedResourceCollection }
     *     
     */
    public void setRelatedResourceCollection(RelatedResourceCollection value) {
        this.relatedResourceCollection = value;
    }

    /**
     * Gets the value of the relatedDocumentCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentCollection }
     *     
     */
    public DocumentCollection getRelatedDocumentCollection() {
        return relatedDocumentCollection;
    }

    /**
     * Sets the value of the relatedDocumentCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentCollection }
     *     
     */
    public void setRelatedDocumentCollection(DocumentCollection value) {
        this.relatedDocumentCollection = value;
    }

    /**
     * Gets the value of the productStateCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductStateCollection }
     *     
     */
    public ProductStateCollection getProductStateCollection() {
        return productStateCollection;
    }

    /**
     * Sets the value of the productStateCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductStateCollection }
     *     
     */
    public void setProductStateCollection(ProductStateCollection value) {
        this.productStateCollection = value;
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
     * Gets the value of the provisionendBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getProvisionendBy() {
        return provisionendBy;
    }

    /**
     * Sets the value of the provisionendBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setProvisionendBy(User value) {
        this.provisionendBy = value;
    }

    /**
     * Gets the value of the lastChangeBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getLastChangeBy() {
        return lastChangeBy;
    }

    /**
     * Sets the value of the lastChangeBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setLastChangeBy(User value) {
        this.lastChangeBy = value;
    }

    /**
     * Gets the value of the lastChangeByOrderItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastChangeByOrderItemId() {
        return lastChangeByOrderItemId;
    }

    /**
     * Sets the value of the lastChangeByOrderItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastChangeByOrderItemId(String value) {
        this.lastChangeByOrderItemId = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

}
