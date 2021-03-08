
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for overlayPdfResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overlayPdfResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pdfWithOverlay" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overlayPdfResponse", propOrder = {
    "pdfWithOverlay"
})
@XmlRootElement(name = "overlayPdfResponse")
public class OverlayPdfResponse {

    @XmlElementRef(name = "pdfWithOverlay", namespace = "http://erender.telekom.si/", type = JAXBElement.class)
    protected JAXBElement<byte[]> pdfWithOverlay;

    /**
     * Gets the value of the pdfWithOverlay property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getPdfWithOverlay() {
        return pdfWithOverlay;
    }

    /**
     * Sets the value of the pdfWithOverlay property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setPdfWithOverlay(JAXBElement<byte[]> value) {
        this.pdfWithOverlay = value;
    }

}
