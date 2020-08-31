
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Relation between business interactions
 * 
 * <p>Java class for Relation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Relation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="relationType" type="{http://telekom.si/schemas/common/base/v1}CatalogValue"/>
 *         &lt;element name="relatedInteractionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Relation", propOrder = {
    "relationType",
    "relatedInteractionId"
})
public class Relation
    extends Entity
{

    @XmlElement(required = true)
    protected CatalogValue relationType;
    protected String relatedInteractionId;

    /**
     * Gets the value of the relationType property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getRelationType() {
        return relationType;
    }

    /**
     * Sets the value of the relationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setRelationType(CatalogValue value) {
        this.relationType = value;
    }

    /**
     * Gets the value of the relatedInteractionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedInteractionId() {
        return relatedInteractionId;
    }

    /**
     * Sets the value of the relatedInteractionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedInteractionId(String value) {
        this.relatedInteractionId = value;
    }

}
