
package si.telekom.schemas.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtendedCatalogValue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtendedCatalogValue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}CatalogValue">
 *       &lt;sequence>
 *         &lt;element name="parameterCollection" type="{http://telekom.si/schemas/common/base/v1}ParameterCollection" minOccurs="0"/>
 *         &lt;element name="attributeCollection" type="{http://telekom.si/schemas/common/base/v1}CatalogValueCollection" minOccurs="0"/>
 *         &lt;element name="valueCollection" type="{http://telekom.si/schemas/common/base/v1}CatalogValueCollection" minOccurs="0"/>
 *         &lt;element name="catalogValueCollection" type="{http://telekom.si/schemas/common/base/v1}CatalogValueCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedCatalogValue", propOrder = {
    "parameterCollection",
    "attributeCollection",
    "valueCollection",
    "catalogValueCollection"
})
public class ExtendedCatalogValue
    extends CatalogValue
{

    protected ParameterCollection parameterCollection;
    protected CatalogValueCollection attributeCollection;
    protected CatalogValueCollection valueCollection;
    protected CatalogValueCollection catalogValueCollection;

    /**
     * Gets the value of the parameterCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ParameterCollection }
     *     
     */
    public ParameterCollection getParameterCollection() {
        return parameterCollection;
    }

    /**
     * Sets the value of the parameterCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterCollection }
     *     
     */
    public void setParameterCollection(ParameterCollection value) {
        this.parameterCollection = value;
    }

    /**
     * Gets the value of the attributeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValueCollection }
     *     
     */
    public CatalogValueCollection getAttributeCollection() {
        return attributeCollection;
    }

    /**
     * Sets the value of the attributeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValueCollection }
     *     
     */
    public void setAttributeCollection(CatalogValueCollection value) {
        this.attributeCollection = value;
    }

    /**
     * Gets the value of the valueCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValueCollection }
     *     
     */
    public CatalogValueCollection getValueCollection() {
        return valueCollection;
    }

    /**
     * Sets the value of the valueCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValueCollection }
     *     
     */
    public void setValueCollection(CatalogValueCollection value) {
        this.valueCollection = value;
    }

    /**
     * Gets the value of the catalogValueCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValueCollection }
     *     
     */
    public CatalogValueCollection getCatalogValueCollection() {
        return catalogValueCollection;
    }

    /**
     * Sets the value of the catalogValueCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValueCollection }
     *     
     */
    public void setCatalogValueCollection(CatalogValueCollection value) {
        this.catalogValueCollection = value;
    }

}
