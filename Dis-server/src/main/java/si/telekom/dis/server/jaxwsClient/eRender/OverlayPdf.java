
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for overlayPdf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="overlayPdf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pdfContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0" form="qualified"/>
 *         &lt;element name="watermark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="fontSize" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *         &lt;element name="opacity" type="{http://www.w3.org/2001/XMLSchema}float" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "overlayPdf", propOrder = {
    "pdfContent",
    "watermark",
    "fontSize",
    "opacity"
})
@XmlRootElement(name = "overlayPdf")
public class OverlayPdf {

    @XmlElementRef(name = "pdfContent", namespace = "http://erender.telekom.si/", type = JAXBElement.class)
    protected JAXBElement<byte[]> pdfContent;
    protected String watermark;
    protected int fontSize;
    protected float opacity;

    /**
     * Gets the value of the pdfContent property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getPdfContent() {
        return pdfContent;
    }

    /**
     * Sets the value of the pdfContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setPdfContent(JAXBElement<byte[]> value) {
        this.pdfContent = value;
    }

    /**
     * Gets the value of the watermark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWatermark() {
        return watermark;
    }

    /**
     * Sets the value of the watermark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWatermark(String value) {
        this.watermark = value;
    }

    /**
     * Gets the value of the fontSize property.
     * 
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Sets the value of the fontSize property.
     * 
     */
    public void setFontSize(int value) {
        this.fontSize = value;
    }

    /**
     * Gets the value of the opacity property.
     * 
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * Sets the value of the opacity property.
     * 
     */
    public void setOpacity(float value) {
        this.opacity = value;
    }

}
