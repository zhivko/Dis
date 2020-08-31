
package si.telekom.schemas.common.businessinteraction.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * Collection of topics that were communicated in single business interaction item
 * 
 * <p>Java class for TopicCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TopicCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="topic" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TopicCollection", propOrder = {
    "topic"
})
public class TopicCollection {

    @XmlElement(required = true)
    protected List<CatalogValue> topic;

    /**
     * Gets the value of the topic property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the topic property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTopic().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogValue }
     * 
     * 
     */
    public List<CatalogValue> getTopic() {
        if (topic == null) {
            topic = new ArrayList<CatalogValue>();
        }
        return this.topic;
    }

}
