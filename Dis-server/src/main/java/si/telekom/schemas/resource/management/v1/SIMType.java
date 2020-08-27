
package si.telekom.schemas.resource.management.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for SIMType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SIMType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capability" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="sizeCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="size" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="memory" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="featureCollection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="feature" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="serialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SIMType", propOrder = {
    "capability",
    "sizeCollection",
    "memory",
    "featureCollection",
    "serialNum"
})
public class SIMType {

    protected CatalogValue capability;
    protected SIMType.SizeCollection sizeCollection;
    protected Integer memory;
    protected SIMType.FeatureCollection featureCollection;
    protected String serialNum;

    /**
     * Gets the value of the capability property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getCapability() {
        return capability;
    }

    /**
     * Sets the value of the capability property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setCapability(CatalogValue value) {
        this.capability = value;
    }

    /**
     * Gets the value of the sizeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link SIMType.SizeCollection }
     *     
     */
    public SIMType.SizeCollection getSizeCollection() {
        return sizeCollection;
    }

    /**
     * Sets the value of the sizeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SIMType.SizeCollection }
     *     
     */
    public void setSizeCollection(SIMType.SizeCollection value) {
        this.sizeCollection = value;
    }

    /**
     * Gets the value of the memory property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMemory() {
        return memory;
    }

    /**
     * Sets the value of the memory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMemory(Integer value) {
        this.memory = value;
    }

    /**
     * Gets the value of the featureCollection property.
     * 
     * @return
     *     possible object is
     *     {@link SIMType.FeatureCollection }
     *     
     */
    public SIMType.FeatureCollection getFeatureCollection() {
        return featureCollection;
    }

    /**
     * Sets the value of the featureCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SIMType.FeatureCollection }
     *     
     */
    public void setFeatureCollection(SIMType.FeatureCollection value) {
        this.featureCollection = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="feature" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "feature"
    })
    public static class FeatureCollection {

        @XmlElement(required = true)
        protected List<CatalogValue> feature;

        /**
         * Gets the value of the feature property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the feature property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFeature().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CatalogValue }
         * 
         * 
         */
        public List<CatalogValue> getFeature() {
            if (feature == null) {
                feature = new ArrayList<CatalogValue>();
            }
            return this.feature;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="size" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "size"
    })
    public static class SizeCollection {

        @XmlElement(required = true)
        protected List<CatalogValue> size;

        /**
         * Gets the value of the size property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the size property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSize().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CatalogValue }
         * 
         * 
         */
        public List<CatalogValue> getSize() {
            if (size == null) {
                size = new ArrayList<CatalogValue>();
            }
            return this.size;
        }

    }

}
