
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for IPResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IPResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="ipType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IPResource", propOrder = {
    "ipType"
})
@XmlSeeAlso({
    IPSubRangeResource.class,
    IPAddressResource.class,
    IPRegionalRangeResource.class
})
public class IPResource
    extends Resource
{

    protected CatalogValue ipType;

    /**
     * Gets the value of the ipType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getIpType() {
        return ipType;
    }

    /**
     * Sets the value of the ipType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setIpType(CatalogValue value) {
        this.ipType = value;
    }

}
