
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dctmAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dctmAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="possibleValues" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="possibleValuesDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="objectType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="attributeName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="readOnly" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="repeating" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="typeBoolean" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="typeString" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="typeInteger" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="typeDouble" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="typeId" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="typeTime" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="mandatory" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="limittedByPossibleValues" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dctmAttribute", propOrder = {
    "possibleValues",
    "possibleValuesDescriptions"
})
public class DctmAttribute {

    @XmlElement(namespace = "")
    protected List<String> possibleValues;
    @XmlElement(name = "possibleValuesDescription", namespace = "")
    protected List<String> possibleValuesDescriptions;
    @XmlAttribute(name = "objectType")
    protected String objectType;
    @XmlAttribute(name = "attributeName")
    protected String attributeName;
    @XmlAttribute(name = "readOnly", required = true)
    protected boolean readOnly;
    @XmlAttribute(name = "repeating", required = true)
    protected boolean repeating;
    @XmlAttribute(name = "typeBoolean", required = true)
    protected boolean typeBoolean;
    @XmlAttribute(name = "typeString", required = true)
    protected boolean typeString;
    @XmlAttribute(name = "typeInteger", required = true)
    protected boolean typeInteger;
    @XmlAttribute(name = "typeDouble", required = true)
    protected boolean typeDouble;
    @XmlAttribute(name = "typeId", required = true)
    protected boolean typeId;
    @XmlAttribute(name = "typeTime", required = true)
    protected boolean typeTime;
    @XmlAttribute(name = "mandatory", required = true)
    protected boolean mandatory;
    @XmlAttribute(name = "limittedByPossibleValues", required = true)
    protected boolean limittedByPossibleValues;

    /**
     * Gets the value of the possibleValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the possibleValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPossibleValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPossibleValues() {
        if (possibleValues == null) {
            possibleValues = new ArrayList<String>();
        }
        return this.possibleValues;
    }

    /**
     * Gets the value of the possibleValuesDescriptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the possibleValuesDescriptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPossibleValuesDescriptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPossibleValuesDescriptions() {
        if (possibleValuesDescriptions == null) {
            possibleValuesDescriptions = new ArrayList<String>();
        }
        return this.possibleValuesDescriptions;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectType(String value) {
        this.objectType = value;
    }

    /**
     * Gets the value of the attributeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * Sets the value of the attributeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    /**
     * Gets the value of the readOnly property.
     * 
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Sets the value of the readOnly property.
     * 
     */
    public void setReadOnly(boolean value) {
        this.readOnly = value;
    }

    /**
     * Gets the value of the repeating property.
     * 
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * Sets the value of the repeating property.
     * 
     */
    public void setRepeating(boolean value) {
        this.repeating = value;
    }

    /**
     * Gets the value of the typeBoolean property.
     * 
     */
    public boolean isTypeBoolean() {
        return typeBoolean;
    }

    /**
     * Sets the value of the typeBoolean property.
     * 
     */
    public void setTypeBoolean(boolean value) {
        this.typeBoolean = value;
    }

    /**
     * Gets the value of the typeString property.
     * 
     */
    public boolean isTypeString() {
        return typeString;
    }

    /**
     * Sets the value of the typeString property.
     * 
     */
    public void setTypeString(boolean value) {
        this.typeString = value;
    }

    /**
     * Gets the value of the typeInteger property.
     * 
     */
    public boolean isTypeInteger() {
        return typeInteger;
    }

    /**
     * Sets the value of the typeInteger property.
     * 
     */
    public void setTypeInteger(boolean value) {
        this.typeInteger = value;
    }

    /**
     * Gets the value of the typeDouble property.
     * 
     */
    public boolean isTypeDouble() {
        return typeDouble;
    }

    /**
     * Sets the value of the typeDouble property.
     * 
     */
    public void setTypeDouble(boolean value) {
        this.typeDouble = value;
    }

    /**
     * Gets the value of the typeId property.
     * 
     */
    public boolean isTypeId() {
        return typeId;
    }

    /**
     * Sets the value of the typeId property.
     * 
     */
    public void setTypeId(boolean value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the typeTime property.
     * 
     */
    public boolean isTypeTime() {
        return typeTime;
    }

    /**
     * Sets the value of the typeTime property.
     * 
     */
    public void setTypeTime(boolean value) {
        this.typeTime = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     */
    public void setMandatory(boolean value) {
        this.mandatory = value;
    }

    /**
     * Gets the value of the limittedByPossibleValues property.
     * 
     */
    public boolean isLimittedByPossibleValues() {
        return limittedByPossibleValues;
    }

    /**
     * Sets the value of the limittedByPossibleValues property.
     * 
     */
    public void setLimittedByPossibleValues(boolean value) {
        this.limittedByPossibleValues = value;
    }

}
