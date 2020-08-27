
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RelatedConnectionSiteResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelatedConnectionSiteResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/fulfillment/v1}RelatedResource">
 *       &lt;sequence>
 *         &lt;element name="feasibilityRequestNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedConnectionSiteResource", propOrder = {
    "feasibilityRequestNumber"
})
public class RelatedConnectionSiteResource
    extends RelatedResource
{

    protected String feasibilityRequestNumber;

    /**
     * Gets the value of the feasibilityRequestNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeasibilityRequestNumber() {
        return feasibilityRequestNumber;
    }

    /**
     * Sets the value of the feasibilityRequestNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeasibilityRequestNumber(String value) {
        this.feasibilityRequestNumber = value;
    }

}
