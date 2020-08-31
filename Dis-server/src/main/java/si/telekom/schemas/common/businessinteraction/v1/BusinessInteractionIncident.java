
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Incident type of business interaction
 * 
 * <p>Java class for BusinessInteractionIncident complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionIncident">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteraction">
 *       &lt;sequence>
 *         &lt;element name="incidentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="incidentStatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionIncident", propOrder = {
    "incidentId",
    "incidentStatusId"
})
public class BusinessInteractionIncident
    extends BusinessInteraction
{

    protected Long incidentId;
    protected Long incidentStatusId;

    /**
     * Gets the value of the incidentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIncidentId() {
        return incidentId;
    }

    /**
     * Sets the value of the incidentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIncidentId(Long value) {
        this.incidentId = value;
    }

    /**
     * Gets the value of the incidentStatusId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIncidentStatusId() {
        return incidentStatusId;
    }

    /**
     * Sets the value of the incidentStatusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIncidentStatusId(Long value) {
        this.incidentStatusId = value;
    }

}
