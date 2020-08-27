
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.base.v1.Money;
import si.telekom.schemas.common.base.v1.Tax;
import si.telekom.schemas.common.base.v1.TaxedAmount;


/**
 * <p>Java class for ProductPrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductPrice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="priceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="discriminatorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="priceAmountId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="priceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="discriminator" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="priceType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="priceCategory" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="chargeRecurrence" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="productSpecification" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="chargeUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeUnits" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="grossAmount" type="{http://telekom.si/schemas/common/base/v1}Money" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="tax" type="{http://telekom.si/schemas/common/base/v1}Tax" minOccurs="0"/>
 *         &lt;element name="suggestedRetailGrossAmount" type="{http://telekom.si/schemas/common/base/v1}Money" minOccurs="0"/>
 *         &lt;element name="suggestedRetailAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="discountedAmount" type="{http://telekom.si/schemas/common/base/v1}TaxedAmount" minOccurs="0"/>
 *         &lt;element name="discountCollection" type="{http://telekom.si/schemas/product/fulfillment/v1}DiscountCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductPrice", propOrder = {
    "priceId",
    "discriminatorId",
    "priceAmountId",
    "priceName",
    "price",
    "discriminator",
    "priceType",
    "priceCategory",
    "chargeRecurrence",
    "productSpecification",
    "chargeUnit",
    "chargeUnits",
    "grossAmount",
    "amount",
    "tax",
    "suggestedRetailGrossAmount",
    "suggestedRetailAmount",
    "discountedAmount",
    "discountCollection"
})
@XmlSeeAlso({
    Discount.class
})
public class ProductPrice
    extends Entity
{

    protected Long priceId;
    protected Long discriminatorId;
    protected Long priceAmountId;
    protected String priceName;
    protected CatalogValue price;
    protected CatalogValue discriminator;
    protected CatalogValue priceType;
    protected CatalogValue priceCategory;
    protected CatalogValue chargeRecurrence;
    protected CatalogValue productSpecification;
    protected String chargeUnit;
    protected Long chargeUnits;
    protected Money grossAmount;
    protected TaxedAmount amount;
    protected Tax tax;
    protected Money suggestedRetailGrossAmount;
    protected TaxedAmount suggestedRetailAmount;
    protected TaxedAmount discountedAmount;
    protected DiscountCollection discountCollection;

    /**
     * Gets the value of the priceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPriceId() {
        return priceId;
    }

    /**
     * Sets the value of the priceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPriceId(Long value) {
        this.priceId = value;
    }

    /**
     * Gets the value of the discriminatorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDiscriminatorId() {
        return discriminatorId;
    }

    /**
     * Sets the value of the discriminatorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDiscriminatorId(Long value) {
        this.discriminatorId = value;
    }

    /**
     * Gets the value of the priceAmountId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPriceAmountId() {
        return priceAmountId;
    }

    /**
     * Sets the value of the priceAmountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPriceAmountId(Long value) {
        this.priceAmountId = value;
    }

    /**
     * Gets the value of the priceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriceName() {
        return priceName;
    }

    /**
     * Sets the value of the priceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriceName(String value) {
        this.priceName = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPrice(CatalogValue value) {
        this.price = value;
    }

    /**
     * Gets the value of the discriminator property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getDiscriminator() {
        return discriminator;
    }

    /**
     * Sets the value of the discriminator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setDiscriminator(CatalogValue value) {
        this.discriminator = value;
    }

    /**
     * Gets the value of the priceType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPriceType() {
        return priceType;
    }

    /**
     * Sets the value of the priceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPriceType(CatalogValue value) {
        this.priceType = value;
    }

    /**
     * Gets the value of the priceCategory property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getPriceCategory() {
        return priceCategory;
    }

    /**
     * Sets the value of the priceCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setPriceCategory(CatalogValue value) {
        this.priceCategory = value;
    }

    /**
     * Gets the value of the chargeRecurrence property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getChargeRecurrence() {
        return chargeRecurrence;
    }

    /**
     * Sets the value of the chargeRecurrence property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setChargeRecurrence(CatalogValue value) {
        this.chargeRecurrence = value;
    }

    /**
     * Gets the value of the productSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getProductSpecification() {
        return productSpecification;
    }

    /**
     * Sets the value of the productSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setProductSpecification(CatalogValue value) {
        this.productSpecification = value;
    }

    /**
     * Gets the value of the chargeUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeUnit() {
        return chargeUnit;
    }

    /**
     * Sets the value of the chargeUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeUnit(String value) {
        this.chargeUnit = value;
    }

    /**
     * Gets the value of the chargeUnits property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getChargeUnits() {
        return chargeUnits;
    }

    /**
     * Sets the value of the chargeUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setChargeUnits(Long value) {
        this.chargeUnits = value;
    }

    /**
     * Gets the value of the grossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getGrossAmount() {
        return grossAmount;
    }

    /**
     * Sets the value of the grossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setGrossAmount(Money value) {
        this.grossAmount = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setAmount(TaxedAmount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the tax property.
     * 
     * @return
     *     possible object is
     *     {@link Tax }
     *     
     */
    public Tax getTax() {
        return tax;
    }

    /**
     * Sets the value of the tax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tax }
     *     
     */
    public void setTax(Tax value) {
        this.tax = value;
    }

    /**
     * Gets the value of the suggestedRetailGrossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Money }
     *     
     */
    public Money getSuggestedRetailGrossAmount() {
        return suggestedRetailGrossAmount;
    }

    /**
     * Sets the value of the suggestedRetailGrossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Money }
     *     
     */
    public void setSuggestedRetailGrossAmount(Money value) {
        this.suggestedRetailGrossAmount = value;
    }

    /**
     * Gets the value of the suggestedRetailAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getSuggestedRetailAmount() {
        return suggestedRetailAmount;
    }

    /**
     * Sets the value of the suggestedRetailAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setSuggestedRetailAmount(TaxedAmount value) {
        this.suggestedRetailAmount = value;
    }

    /**
     * Gets the value of the discountedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxedAmount }
     *     
     */
    public TaxedAmount getDiscountedAmount() {
        return discountedAmount;
    }

    /**
     * Sets the value of the discountedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxedAmount }
     *     
     */
    public void setDiscountedAmount(TaxedAmount value) {
        this.discountedAmount = value;
    }

    /**
     * Gets the value of the discountCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountCollection }
     *     
     */
    public DiscountCollection getDiscountCollection() {
        return discountCollection;
    }

    /**
     * Sets the value of the discountCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountCollection }
     *     
     */
    public void setDiscountCollection(DiscountCollection value) {
        this.discountCollection = value;
    }

}
