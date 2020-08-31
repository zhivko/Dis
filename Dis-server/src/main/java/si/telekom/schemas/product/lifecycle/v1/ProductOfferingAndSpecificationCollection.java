
package si.telekom.schemas.product.lifecycle.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductOfferingAndSpecificationCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductOfferingAndSpecificationCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productOfferingCollection" type="{http://telekom.si/schemas/product/lifecycle/v1}ProductOfferingCollection" minOccurs="0"/>
 *         &lt;element name="productSpecificationCollection" type="{http://telekom.si/schemas/product/lifecycle/v1}ProductSpecificationCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductOfferingAndSpecificationCollection", propOrder = {
    "productOfferingCollection",
    "productSpecificationCollection"
})
public class ProductOfferingAndSpecificationCollection {

    protected ProductOfferingCollection productOfferingCollection;
    protected ProductSpecificationCollection productSpecificationCollection;

    /**
     * Gets the value of the productOfferingCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductOfferingCollection }
     *     
     */
    public ProductOfferingCollection getProductOfferingCollection() {
        return productOfferingCollection;
    }

    /**
     * Sets the value of the productOfferingCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductOfferingCollection }
     *     
     */
    public void setProductOfferingCollection(ProductOfferingCollection value) {
        this.productOfferingCollection = value;
    }

    /**
     * Gets the value of the productSpecificationCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSpecificationCollection }
     *     
     */
    public ProductSpecificationCollection getProductSpecificationCollection() {
        return productSpecificationCollection;
    }

    /**
     * Sets the value of the productSpecificationCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSpecificationCollection }
     *     
     */
    public void setProductSpecificationCollection(ProductSpecificationCollection value) {
        this.productSpecificationCollection = value;
    }

}
