
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * <p>Java class for Isp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Isp">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/dealer/v1}Dealer">
 *       &lt;sequence>
 *         &lt;element name="ispName" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Isp", propOrder = {
    "ispName"
})
public class Isp
    extends Dealer
{

    @XmlElement(required = true)
    protected CatalogValue ispName;

    /**
     * Gets the value of the ispName property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getIspName() {
        return ispName;
    }

    /**
     * Sets the value of the ispName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setIspName(CatalogValue value) {
        this.ispName = value;
    }

}
