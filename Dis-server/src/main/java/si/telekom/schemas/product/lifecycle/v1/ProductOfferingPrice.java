
package si.telekom.schemas.product.lifecycle.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProductOfferingPrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductOfferingPrice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/lifecycle/v1}PLMEntity">
 *       &lt;sequence>
 *         &lt;element name="productOfferingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productOfferingCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductOfferingPrice", propOrder = {
    "productOfferingId",
    "productOfferingCode",
    "priceId",
    "priceCode"
})
public class ProductOfferingPrice
    extends PLMEntity
{

    protected String productOfferingId;
    protected String productOfferingCode;
    protected String priceId;
    protected String priceCode;

    /**
     * Gets the value of the productOfferingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOfferingId() {
        return productOfferingId;
    }

    /**
     * Sets the value of the productOfferingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOfferingId(String value) {
        this.productOfferingId = value;
    }

    /**
     * Gets the value of the productOfferingCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOfferingCode() {
        return productOfferingCode;
    }

    /**
     * Sets the value of the productOfferingCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOfferingCode(String value) {
        this.productOfferingCode = value;
    }

    /**
     * Gets the value of the priceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceId() {
        return priceId;
    }

    /**
     * Sets the value of the priceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceId(String value) {
        this.priceId = value;
    }

    /**
     * Gets the value of the priceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceCode() {
        return priceCode;
    }

    /**
     * Sets the value of the priceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceCode(String value) {
        this.priceCode = value;
    }

}
