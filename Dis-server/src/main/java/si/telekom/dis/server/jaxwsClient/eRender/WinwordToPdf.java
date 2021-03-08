
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for winwordToPdf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="winwordToPdf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="winwordContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "winwordToPdf", propOrder = {
    "winwordContent"
})
@XmlRootElement(name = "winwordToPdf")
public class WinwordToPdf {

    @XmlElementRef(name = "winwordContent", namespace = "http://erender.telekom.si/", type = JAXBElement.class)
    protected JAXBElement<byte[]> winwordContent;

    /**
     * Gets the value of the winwordContent property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getWinwordContent() {
        return winwordContent;
    }

    /**
     * Sets the value of the winwordContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setWinwordContent(JAXBElement<byte[]> value) {
        this.winwordContent = value;
    }

}
