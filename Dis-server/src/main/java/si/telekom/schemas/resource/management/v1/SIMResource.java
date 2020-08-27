
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for SIMResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIMResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="IMSI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="puk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usage" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="type" type="{http://telekom.si/schemas/resource/management/v1}SIMType" minOccurs="0"/>
 *         &lt;element name="pointOfSaleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HLRId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="serialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIMResource", propOrder = {
    "imsi",
    "pin1",
    "pin2",
    "puk1",
    "puk2",
    "ki",
    "tKey",
    "usage",
    "type",
    "pointOfSaleId",
    "hlrId",
    "serialNum"
})
public class SIMResource
    extends Resource
{

    @XmlElement(name = "IMSI")
    protected String imsi;
    protected String pin1;
    protected String pin2;
    protected String puk1;
    protected String puk2;
    @XmlElement(name = "KI")
    protected String ki;
    protected String tKey;
    protected CatalogValue usage;
    protected SIMType type;
    protected String pointOfSaleId;
    @XmlElement(name = "HLRId")
    protected Long hlrId;
    protected String serialNum;

    /**
     * Gets the value of the imsi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIMSI() {
        return imsi;
    }

    /**
     * Sets the value of the imsi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIMSI(String value) {
        this.imsi = value;
    }

    /**
     * Gets the value of the pin1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin1() {
        return pin1;
    }

    /**
     * Sets the value of the pin1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin1(String value) {
        this.pin1 = value;
    }

    /**
     * Gets the value of the pin2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin2() {
        return pin2;
    }

    /**
     * Sets the value of the pin2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin2(String value) {
        this.pin2 = value;
    }

    /**
     * Gets the value of the puk1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk1() {
        return puk1;
    }

    /**
     * Sets the value of the puk1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk1(String value) {
        this.puk1 = value;
    }

    /**
     * Gets the value of the puk2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuk2() {
        return puk2;
    }

    /**
     * Sets the value of the puk2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuk2(String value) {
        this.puk2 = value;
    }

    /**
     * Gets the value of the ki property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKI() {
        return ki;
    }

    /**
     * Sets the value of the ki property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKI(String value) {
        this.ki = value;
    }

    /**
     * Gets the value of the tKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTKey() {
        return tKey;
    }

    /**
     * Sets the value of the tKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTKey(String value) {
        this.tKey = value;
    }

    /**
     * Gets the value of the usage property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getUsage() {
        return usage;
    }

    /**
     * Sets the value of the usage property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setUsage(CatalogValue value) {
        this.usage = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link SIMType }
     *     
     */
    public SIMType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link SIMType }
     *     
     */
    public void setType(SIMType value) {
        this.type = value;
    }

    /**
     * Gets the value of the pointOfSaleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointOfSaleId() {
        return pointOfSaleId;
    }

    /**
     * Sets the value of the pointOfSaleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointOfSaleId(String value) {
        this.pointOfSaleId = value;
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
     * Gets the value of the serialNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Sets the value of the serialNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNum(String value) {
        this.serialNum = value;
    }

}
