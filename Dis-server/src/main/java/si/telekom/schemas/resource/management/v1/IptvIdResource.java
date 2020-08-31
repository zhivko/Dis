
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IptvIdResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IptvIdResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="iptvId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IptvIdResource", propOrder = {
    "iptvId"
})
public class IptvIdResource
    extends Resource
{

    protected String iptvId;

    /**
     * Gets the value of the iptvId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIptvId() {
        return iptvId;
    }

    /**
     * Sets the value of the iptvId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIptvId(String value) {
        this.iptvId = value;
    }

}
