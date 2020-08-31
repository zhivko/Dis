
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Group of collection of topic items
 * 
 * <p>Java class for TopicGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TopicGroup">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="topicCollection" type="{http://telekom.si/schemas/common/businessinteraction/v1}TopicCollection"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="classification" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopicGroup", propOrder = {
    "topicCollection",
    "description",
    "classification"
})
public class TopicGroup
    extends Entity
{

    @XmlElement(required = true)
    protected TopicCollection topicCollection;
    protected String description;
    protected CatalogValue classification;

    /**
     * Gets the value of the topicCollection property.
     * 
     * @return
     *     possible object is
     *     {@link TopicCollection }
     *     
     */
    public TopicCollection getTopicCollection() {
        return topicCollection;
    }

    /**
     * Sets the value of the topicCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link TopicCollection }
     *     
     */
    public void setTopicCollection(TopicCollection value) {
        this.topicCollection = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getClassification() {
        return classification;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setClassification(CatalogValue value) {
        this.classification = value;
    }

}
