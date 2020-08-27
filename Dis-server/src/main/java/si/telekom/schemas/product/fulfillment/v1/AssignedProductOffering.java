
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.product.lifecycle.v1.ProductOffering;


/**
 * <p>Java class for AssignedProductOffering complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedProductOffering">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="offering" type="{http://telekom.si/schemas/product/lifecycle/v1}ProductOffering" minOccurs="0"/>
 *         &lt;element name="priceCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductPriceCollection" minOccurs="0"/>
 *         &lt;element name="version" type="{http://telekom.si/schemas/product/fulfillment/v1}VersionInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedProductOffering", propOrder = {
    "offering",
    "priceCollection",
    "version"
})
public class AssignedProductOffering
    extends Entity
{

    protected ProductOffering offering;
    protected ProductPriceCollection priceCollection;
    protected VersionInfo version;

    /**
     * Gets the value of the offering property.
     * 
     * @return
     *     possible object is
     *     {@link ProductOffering }
     *     
     */
    public ProductOffering getOffering() {
        return offering;
    }

    /**
     * Sets the value of the offering property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductOffering }
     *     
     */
    public void setOffering(ProductOffering value) {
        this.offering = value;
    }

    /**
     * Gets the value of the priceCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ProductPriceCollection }
     *     
     */
    public ProductPriceCollection getPriceCollection() {
        return priceCollection;
    }

    /**
     * Sets the value of the priceCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductPriceCollection }
     *     
     */
    public void setPriceCollection(ProductPriceCollection value) {
        this.priceCollection = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link VersionInfo }
     *     
     */
    public VersionInfo getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionInfo }
     *     
     */
    public void setVersion(VersionInfo value) {
        this.version = value;
    }

}
