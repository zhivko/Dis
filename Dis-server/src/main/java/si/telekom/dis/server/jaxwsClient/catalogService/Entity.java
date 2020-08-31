
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Entity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Entity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validity" type="{http://telekom.si/schemas/common/base/v1}TimePeriod" minOccurs="0"/>
 *         &lt;element name="characteristicCollection" type="{http://telekom.si/schemas/common/base/v1}CharacteristicCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idCorrelation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="idPrior" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="updateAction" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity", namespace = "http://telekom.si/schemas/common/base/v1", propOrder = {
    "id",
    "validity",
    "characteristicCollection"
})
@XmlSeeAlso({
    Characteristic.class
})
public class Entity {

    protected String id;
    protected TimePeriod validity;
    protected CharacteristicCollection characteristicCollection;
    @XmlAttribute(name = "action")
    protected String action;
    @XmlAttribute(name = "idCorrelation")
    protected String idCorrelation;
    @XmlAttribute(name = "idPrior")
    protected String idPrior;
    @XmlAttribute(name = "updateAction")
    protected String updateAction;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the validity property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriod }
     *     
     */
    public TimePeriod getValidity() {
        return validity;
    }

    /**
     * Sets the value of the validity property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriod }
     *     
     */
    public void setValidity(TimePeriod value) {
        this.validity = value;
    }

    /**
     * Gets the value of the characteristicCollection property.
     * 
     * @return
     *     possible object is
     *     {@link CharacteristicCollection }
     *     
     */
    public CharacteristicCollection getCharacteristicCollection() {
        return characteristicCollection;
    }

    /**
     * Sets the value of the characteristicCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacteristicCollection }
     *     
     */
    public void setCharacteristicCollection(CharacteristicCollection value) {
        this.characteristicCollection = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the idCorrelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCorrelation() {
        return idCorrelation;
    }

    /**
     * Sets the value of the idCorrelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCorrelation(String value) {
        this.idCorrelation = value;
    }

    /**
     * Gets the value of the idPrior property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPrior() {
        return idPrior;
    }

    /**
     * Sets the value of the idPrior property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPrior(String value) {
        this.idPrior = value;
    }

    /**
     * Gets the value of the updateAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateAction() {
        return updateAction;
    }

    /**
     * Sets the value of the updateAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateAction(String value) {
        this.updateAction = value;
    }

}
