
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RelatedPhoneNumberResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelatedPhoneNumberResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/product/fulfillment/v1}RelatedResource">
 *       &lt;sequence>
 *         &lt;element name="portability" type="{http://telekom.si/schemas/product/fulfillment/v1}Portability" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedPhoneNumberResource", propOrder = {
    "portability"
})
public class RelatedPhoneNumberResource
    extends RelatedResource
{

    protected Portability portability;

    /**
     * Gets the value of the portability property.
     * 
     * @return
     *     possible object is
     *     {@link Portability }
     *     
     */
    public Portability getPortability() {
        return portability;
    }

    /**
     * Sets the value of the portability property.
     * 
     * @param value
     *     allowed object is
     *     {@link Portability }
     *     
     */
    public void setPortability(Portability value) {
        this.portability = value;
    }

}
