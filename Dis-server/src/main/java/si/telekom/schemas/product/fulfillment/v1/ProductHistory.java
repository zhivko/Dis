
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CharacteristicCollection;


/**
 * <p>Java class for ProductHistory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductHistory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="composedProductCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductCollection" minOccurs="0"/>
 *         &lt;element name="relatedProductCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductCollection" minOccurs="0"/>
 *         &lt;element name="assignedProductOfferingCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}AssignedProductOfferingCollection" minOccurs="0"/>
 *         &lt;element name="relatedResourceCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}RelatedResourceCollection" minOccurs="0"/>
 *         &lt;element name="productStateCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductStateCollection" minOccurs="0"/>
 *         &lt;element name="characteristicCollection" type="{http://telekom.si/schemas/common/base/v1}CharacteristicCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductHistory", propOrder = {
    "composedProductCollection",
    "relatedProductCollection",
    "assignedProductOfferingCollection",
    "relatedResourceCollection",
    "productStateCollection",
    "characteristicCollection"
})
public class ProductHistory {

    protected ProductCollection composedProductCollection;
    protected ProductCollection relatedProductCollection;
    protected AssignedProductOfferingCollection assignedProductOfferingCollection;
    protected RelatedResourceCollection relatedResourceCollection;
    protected ProductStateCollection productStateCollection;
    protected CharacteristicCollection characteristicCollection;

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
     * Gets the value of the relatedProductCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCollection }
     *     
     */
    public ProductCollection getRelatedProductCollection() {
        return relatedProductCollection;
    }

    /**
     * Sets the value of the relatedProductCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCollection }
     *     
     */
    public void setRelatedProductCollection(ProductCollection value) {
        this.relatedProductCollection = value;
    }

    /**
     * Gets the value of the assignedProductOfferingCollection property.
     * 
     * @return
     *     possible object is
     *     {@link AssignedProductOfferingCollection }
     *     
     */
    public AssignedProductOfferingCollection getAssignedProductOfferingCollection() {
        return assignedProductOfferingCollection;
    }

    /**
     * Sets the value of the assignedProductOfferingCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssignedProductOfferingCollection }
     *     
     */
    public void setAssignedProductOfferingCollection(AssignedProductOfferingCollection value) {
        this.assignedProductOfferingCollection = value;
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
     * Gets the value of the characteristicCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CharacteristicCollection }
     *     
     */
    public CharacteristicCollection getCharacteristicCollection() {
        return characteristicCollection;
    }

    /**
     * Sets the value of the characteristicCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacteristicCollection }
     *     
     */
    public void setCharacteristicCollection(CharacteristicCollection value) {
        this.characteristicCollection = value;
    }

}
