
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhoneNumberBlockResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneNumberBlockResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="blockName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorOwnerNrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operatorUserNrn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceswitch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneNumberBlockResource", propOrder = {
    "blockName",
    "type",
    "operatorOwnerNrn",
    "operatorUserNrn",
    "sourceswitch"
})
public class PhoneNumberBlockResource
    extends Resource
{

    protected String blockName;
    protected String type;
    protected String operatorOwnerNrn;
    protected String operatorUserNrn;
    protected String sourceswitch;

    /**
     * Gets the value of the blockName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * Sets the value of the blockName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlockName(String value) {
        this.blockName = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the operatorOwnerNrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorOwnerNrn() {
        return operatorOwnerNrn;
    }

    /**
     * Sets the value of the operatorOwnerNrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorOwnerNrn(String value) {
        this.operatorOwnerNrn = value;
    }

    /**
     * Gets the value of the operatorUserNrn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatorUserNrn() {
        return operatorUserNrn;
    }

    /**
     * Sets the value of the operatorUserNrn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatorUserNrn(String value) {
        this.operatorUserNrn = value;
    }

    /**
     * Gets the value of the sourceswitch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceswitch() {
        return sourceswitch;
    }

    /**
     * Sets the value of the sourceswitch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceswitch(String value) {
        this.sourceswitch = value;
    }

}
