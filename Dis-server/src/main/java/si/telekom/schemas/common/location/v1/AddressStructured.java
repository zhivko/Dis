
package si.telekom.schemas.common.location.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Detailed structured representation of physical location address
 * 
 * <p>Java class for AddressStructured complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressStructured">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="street" type="{http://telekom.si/schemas/common/location/v1}Street" minOccurs="0"/>
 *         &lt;element name="streetNr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="streetNrSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localAddress" type="{http://telekom.si/schemas/common/location/v1}LocalAddress" minOccurs="0"/>
 *         &lt;element name="geographicLocation" type="{http://telekom.si/schemas/common/location/v1}GeographicLocation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressStructured", propOrder = {
    "street",
    "streetNr",
    "streetNrSuffix",
    "localAddress",
    "geographicLocation"
})
public class AddressStructured
    extends Entity
{

    protected Street street;
    protected Integer streetNr;
    protected String streetNrSuffix;
    protected LocalAddress localAddress;
    protected GeographicLocation geographicLocation;

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link Street }
     *     
     */
    public Street getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link Street }
     *     
     */
    public void setStreet(Street value) {
        this.street = value;
    }

    /**
     * Gets the value of the streetNr property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStreetNr() {
        return streetNr;
    }

    /**
     * Sets the value of the streetNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStreetNr(Integer value) {
        this.streetNr = value;
    }

    /**
     * Gets the value of the streetNrSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetNrSuffix() {
        return streetNrSuffix;
    }

    /**
     * Sets the value of the streetNrSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNrSuffix(String value) {
        this.streetNrSuffix = value;
    }

    /**
     * Gets the value of the localAddress property.
     * 
     * @return
     *     possible object is
     *     {@link LocalAddress }
     *     
     */
    public LocalAddress getLocalAddress() {
        return localAddress;
    }

    /**
     * Sets the value of the localAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalAddress }
     *     
     */
    public void setLocalAddress(LocalAddress value) {
        this.localAddress = value;
    }

    /**
     * Gets the value of the geographicLocation property.
     * 
     * @return
     *     possible object is
     *     {@link GeographicLocation }
     *     
     */
    public GeographicLocation getGeographicLocation() {
        return geographicLocation;
    }

    /**
     * Sets the value of the geographicLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicLocation }
     *     
     */
    public void setGeographicLocation(GeographicLocation value) {
        this.geographicLocation = value;
    }

}
