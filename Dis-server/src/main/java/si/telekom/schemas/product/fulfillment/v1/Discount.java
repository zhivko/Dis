
package si.telekom.schemas.product.fulfillment.v1;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for Discount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Discount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/fulfillment/v1}ProductPrice">
 *       &lt;sequence>
 *         &lt;element name="impactSourceOffering" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="discountAmountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="appliedOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Discount", propOrder = {
    "impactSourceOffering",
    "discountAmountType",
    "discountValue",
    "appliedOrder"
})
public class Discount
    extends ProductPrice
{

    protected CatalogValue impactSourceOffering;
    protected String discountAmountType;
    protected BigDecimal discountValue;
    protected Integer appliedOrder;

    /**
     * Gets the value of the impactSourceOffering property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getImpactSourceOffering() {
        return impactSourceOffering;
    }

    /**
     * Sets the value of the impactSourceOffering property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setImpactSourceOffering(CatalogValue value) {
        this.impactSourceOffering = value;
    }

    /**
     * Gets the value of the discountAmountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountAmountType() {
        return discountAmountType;
    }

    /**
     * Sets the value of the discountAmountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountAmountType(String value) {
        this.discountAmountType = value;
    }

    /**
     * Gets the value of the discountValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    /**
     * Sets the value of the discountValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountValue(BigDecimal value) {
        this.discountValue = value;
    }

    /**
     * Gets the value of the appliedOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAppliedOrder() {
        return appliedOrder;
    }

    /**
     * Sets the value of the appliedOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAppliedOrder(Integer value) {
        this.appliedOrder = value;
    }

}
