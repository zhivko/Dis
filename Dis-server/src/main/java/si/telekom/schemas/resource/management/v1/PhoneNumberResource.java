
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for PhoneNumberResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneNumberResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="nationalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="switchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portedToswitchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="HLRId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="operatorUserNrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorOwnerNrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="internationalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="class" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="blockName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dealerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneNumberResource", propOrder = {
    "nationalNumber",
    "prefix",
    "switchId",
    "portedToswitchId",
    "areaCode",
    "category",
    "hlrId",
    "operatorUserNrn",
    "operatorOwnerNrn",
    "internationalNumber",
    "clazz",
    "blockName",
    "dealerId"
})
public class PhoneNumberResource
    extends Resource
{

    protected String nationalNumber;
    protected String prefix;
    protected String switchId;
    protected String portedToswitchId;
    protected String areaCode;
    protected CatalogValue category;
    @XmlElement(name = "HLRId")
    protected Long hlrId;
    protected String operatorUserNrn;
    protected String operatorOwnerNrn;
    protected String internationalNumber;
    @XmlElement(name = "class")
    protected CatalogValue clazz;
    protected String blockName;
    protected String dealerId;

    /**
     * Gets the value of the nationalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalNumber() {
        return nationalNumber;
    }

    /**
     * Sets the value of the nationalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalNumber(String value) {
        this.nationalNumber = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the switchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwitchId() {
        return switchId;
    }

    /**
     * Sets the value of the switchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwitchId(String value) {
        this.switchId = value;
    }

    /**
     * Gets the value of the portedToswitchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortedToswitchId() {
        return portedToswitchId;
    }

    /**
     * Sets the value of the portedToswitchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortedToswitchId(String value) {
        this.portedToswitchId = value;
    }

    /**
     * Gets the value of the areaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * Sets the value of the areaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setCategory(CatalogValue value) {
        this.category = value;
    }

    /**
     * Gets the value of the hlrId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHLRId() {
        return hlrId;
    }

    /**
     * Sets the value of the hlrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHLRId(Long value) {
        this.hlrId = value;
    }

    /**
     * Gets the value of the operatorUserNrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorUserNrn() {
        return operatorUserNrn;
    }

    /**
     * Sets the value of the operatorUserNrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorUserNrn(String value) {
        this.operatorUserNrn = value;
    }

    /**
     * Gets the value of the operatorOwnerNrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorOwnerNrn() {
        return operatorOwnerNrn;
    }

    /**
     * Sets the value of the operatorOwnerNrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorOwnerNrn(String value) {
        this.operatorOwnerNrn = value;
    }

    /**
     * Gets the value of the internationalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalNumber() {
        return internationalNumber;
    }

    /**
     * Sets the value of the internationalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalNumber(String value) {
        this.internationalNumber = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setClazz(CatalogValue value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the blockName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * Sets the value of the blockName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockName(String value) {
        this.blockName = value;
    }

    /**
     * Gets the value of the dealerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealerId() {
        return dealerId;
    }

    /**
     * Sets the value of the dealerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealerId(String value) {
        this.dealerId = value;
    }

}
