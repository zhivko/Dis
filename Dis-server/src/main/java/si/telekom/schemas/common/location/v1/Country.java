
package si.telekom.schemas.common.location.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Structured representatio of country
 * 
 * <p>Java class for Country complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Country">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isoCode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="iso2Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="iso3Code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameEnglish" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameOfficial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="continent" type="{http://telekom.si/schemas/common/location/v1}Continent" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Country", propOrder = {
    "name",
    "isoCode",
    "iso2Code",
    "iso3Code",
    "nameEnglish",
    "nameOfficial",
    "continent"
})
public class Country
    extends Entity
{

    protected String name;
    protected Long isoCode;
    protected String iso2Code;
    protected String iso3Code;
    protected String nameEnglish;
    protected String nameOfficial;
    protected Continent continent;

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
     * Gets the value of the isoCode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIsoCode() {
        return isoCode;
    }

    /**
     * Sets the value of the isoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIsoCode(Long value) {
        this.isoCode = value;
    }

    /**
     * Gets the value of the iso2Code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIso2Code() {
        return iso2Code;
    }

    /**
     * Sets the value of the iso2Code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIso2Code(String value) {
        this.iso2Code = value;
    }

    /**
     * Gets the value of the iso3Code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIso3Code() {
        return iso3Code;
    }

    /**
     * Sets the value of the iso3Code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIso3Code(String value) {
        this.iso3Code = value;
    }

    /**
     * Gets the value of the nameEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameEnglish() {
        return nameEnglish;
    }

    /**
     * Sets the value of the nameEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameEnglish(String value) {
        this.nameEnglish = value;
    }

    /**
     * Gets the value of the nameOfficial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameOfficial() {
        return nameOfficial;
    }

    /**
     * Sets the value of the nameOfficial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameOfficial(String value) {
        this.nameOfficial = value;
    }

    /**
     * Gets the value of the continent property.
     * 
     * @return
     *     possible object is
     *     {@link Continent }
     *     
     */
    public Continent getContinent() {
        return continent;
    }

    /**
     * Sets the value of the continent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Continent }
     *     
     */
    public void setContinent(Continent value) {
        this.continent = value;
    }

}
