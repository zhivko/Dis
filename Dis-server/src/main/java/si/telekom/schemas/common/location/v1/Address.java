
package si.telekom.schemas.common.location.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.party.v1.PartyAddress;


/**
 * Representation of physical location address
 * 
 * <p>Java class for Address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Address">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="formattedAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseNr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseNrAddOn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postOffice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="settlement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="municipality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
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
@XmlType(name = "Address", propOrder = {
    "formattedAddress",
    "streetName",
    "houseNr",
    "houseNrAddOn",
    "postCode",
    "postOffice",
    "settlement",
    "municipality",
    "country",
    "localAddress",
    "geographicLocation"
})
@XmlSeeAlso({
    PartyAddress.class
})
public class Address
    extends Entity
{

    protected String formattedAddress;
    protected String streetName;
    protected String houseNr;
    protected String houseNrAddOn;
    protected String postCode;
    protected String postOffice;
    protected String settlement;
    protected String municipality;
    protected CatalogValue country;
    protected LocalAddress localAddress;
    protected GeographicLocation geographicLocation;

    /**
     * Gets the value of the formattedAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * Sets the value of the formattedAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormattedAddress(String value) {
        this.formattedAddress = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the houseNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNr() {
        return houseNr;
    }

    /**
     * Sets the value of the houseNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNr(String value) {
        this.houseNr = value;
    }

    /**
     * Gets the value of the houseNrAddOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNrAddOn() {
        return houseNrAddOn;
    }

    /**
     * Sets the value of the houseNrAddOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNrAddOn(String value) {
        this.houseNrAddOn = value;
    }

    /**
     * Gets the value of the postCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the value of the postCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    /**
     * Gets the value of the postOffice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostOffice() {
        return postOffice;
    }

    /**
     * Sets the value of the postOffice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostOffice(String value) {
        this.postOffice = value;
    }

    /**
     * Gets the value of the settlement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlement() {
        return settlement;
    }

    /**
     * Sets the value of the settlement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlement(String value) {
        this.settlement = value;
    }

    /**
     * Gets the value of the municipality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * Sets the value of the municipality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipality(String value) {
        this.municipality = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setCountry(CatalogValue value) {
        this.country = value;
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
