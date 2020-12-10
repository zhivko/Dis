
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpenOfficePdfResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpenOfficePdfResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stringByRef" type="{http://templates.mobitel.com}StringByRef" minOccurs="0" form="qualified"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpenOfficePdfResponse", propOrder = {
    "stringByRef",
    "result"
})
@XmlRootElement(name = "getOpenOfficePdfResponse")
public class GetOpenOfficePdfResponse {

    protected StringByRef stringByRef;
    @XmlElementRef(name = "result", namespace = "http://templates.mobitel.com/", type = JAXBElement.class)
    protected JAXBElement<byte[]> result;

    /**
     * Gets the value of the stringByRef property.
     * 
     * @return
     *     possible object is
     *     {@link StringByRef }
     *     
     */
    public StringByRef getStringByRef() {
        return stringByRef;
    }

    /**
     * Sets the value of the stringByRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringByRef }
     *     
     */
    public void setStringByRef(StringByRef value) {
        this.stringByRef = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setResult(JAXBElement<byte[]> value) {
        this.result = value;
    }

}
