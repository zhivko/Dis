
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDVodaResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDVodaResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="idVoda" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intendedUse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationOfUse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDVodaResource", propOrder = {
    "idVoda",
    "category",
    "intendedUse",
    "locationOfUse"
})
public class IDVodaResource
    extends Resource
{

    protected String idVoda;
    protected String category;
    protected String intendedUse;
    protected String locationOfUse;

    /**
     * Gets the value of the idVoda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdVoda() {
        return idVoda;
    }

    /**
     * Sets the value of the idVoda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdVoda(String value) {
        this.idVoda = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the intendedUse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntendedUse() {
        return intendedUse;
    }

    /**
     * Sets the value of the intendedUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntendedUse(String value) {
        this.intendedUse = value;
    }

    /**
     * Gets the value of the locationOfUse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationOfUse() {
        return locationOfUse;
    }

    /**
     * Sets the value of the locationOfUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationOfUse(String value) {
        this.locationOfUse = value;
    }

}
