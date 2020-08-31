
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * <p>Java class for OrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="productOrderItem" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductOrderItem" minOccurs="0"/>
 *         &lt;element name="updateReason" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderItem", propOrder = {
    "productOrderItem",
    "updateReason"
})
public class OrderItem
    extends Entity
{

    protected ProductOrderItem productOrderItem;
    protected CatalogValue updateReason;

    /**
     * Gets the value of the productOrderItem property.
     * 
     * @return
     *     possible object is
     *     {@link ProductOrderItem }
     *     
     */
    public ProductOrderItem getProductOrderItem() {
        return productOrderItem;
    }

    /**
     * Sets the value of the productOrderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductOrderItem }
     *     
     */
    public void setProductOrderItem(ProductOrderItem value) {
        this.productOrderItem = value;
    }

    /**
     * Gets the value of the updateReason property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getUpdateReason() {
        return updateReason;
    }

    /**
     * Sets the value of the updateReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setUpdateReason(CatalogValue value) {
        this.updateReason = value;
    }

}
