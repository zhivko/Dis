
package si.telekom.schemas.common.location.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Structured representation of settlement
 * 
 * <p>Java class for Settlement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Settlement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postOffice" type="{http://telekom.si/schemas/common/location/v1}PostOffice" minOccurs="0"/>
 *         &lt;element name="muncipality" type="{http://telekom.si/schemas/common/location/v1}Muncipality" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Settlement", propOrder = {
    "name",
    "postOffice",
    "muncipality"
})
public class Settlement
    extends Entity
{

    protected String name;
    protected PostOffice postOffice;
    protected Muncipality muncipality;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the postOffice property.
     * 
     * @return
     *     possible object is
     *     {@link PostOffice }
     *     
     */
    public PostOffice getPostOffice() {
        return postOffice;
    }

    /**
     * Sets the value of the postOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link PostOffice }
     *     
     */
    public void setPostOffice(PostOffice value) {
        this.postOffice = value;
    }

    /**
     * Gets the value of the muncipality property.
     * 
     * @return
     *     possible object is
     *     {@link Muncipality }
     *     
     */
    public Muncipality getMuncipality() {
        return muncipality;
    }

    /**
     * Sets the value of the muncipality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Muncipality }
     *     
     */
    public void setMuncipality(Muncipality value) {
        this.muncipality = value;
    }

}
