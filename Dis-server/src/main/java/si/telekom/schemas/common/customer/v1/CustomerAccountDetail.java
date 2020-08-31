
package si.telekom.schemas.common.customer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.product.fulfillment.v1.ProductCollection;
import si.telekom.schemas.product.usage.v1.UsageCollection;


/**
 * Detailed information about CustomerAccount
 * 
 * <p>Java class for CustomerAccountDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerAccountDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/customer/v1}CustomerAccount">
 *       &lt;sequence>
 *         &lt;element name="usageCollection" type="{http://telekom.si/schemas/product/usage/v1}UsageCollection" minOccurs="0"/>
 *         &lt;element name="productCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerAccountDetail", propOrder = {
    "usageCollection",
    "productCollection"
})
public class CustomerAccountDetail
    extends CustomerAccount
{

    protected UsageCollection usageCollection;
    protected ProductCollection productCollection;

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
     * Gets the value of the productCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCollection }
     *     
     */
    public ProductCollection getProductCollection() {
        return productCollection;
    }

    /**
     * Sets the value of the productCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCollection }
     *     
     */
    public void setProductCollection(ProductCollection value) {
        this.productCollection = value;
    }

}
