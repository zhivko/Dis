
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Document;


/**
 * Mail type of business interaction
 * 
 * <p>Java class for BusinessInteractionMail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionMail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteraction">
 *       &lt;sequence>
 *         &lt;element name="document" type="{http://telekom.si/schemas/common/base/v1}Document" minOccurs="0"/>
 *         &lt;element name="documentTypeDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionMail", propOrder = {
    "document",
    "documentTypeDescription"
})
public class BusinessInteractionMail
    extends BusinessInteraction
{

    protected Document document;
    protected String documentTypeDescription;

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    public void setDocument(Document value) {
        this.document = value;
    }

    /**
     * Gets the value of the documentTypeDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentTypeDescription() {
        return documentTypeDescription;
    }

    /**
     * Sets the value of the documentTypeDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentTypeDescription(String value) {
        this.documentTypeDescription = value;
    }

}
