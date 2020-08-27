
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Portal type of business interaction
 * 
 * <p>Java class for BusinessInteractionPortal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionPortal">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteraction">
 *       &lt;sequence>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portalSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionPortal", propOrder = {
    "content",
    "portalSubject"
})
public class BusinessInteractionPortal
    extends BusinessInteraction
{

    protected String content;
    protected String portalSubject;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the portalSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalSubject() {
        return portalSubject;
    }

    /**
     * Sets the value of the portalSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalSubject(String value) {
        this.portalSubject = value;
    }

}
