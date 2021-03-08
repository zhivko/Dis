
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for format complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="format">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dctmFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dosExtension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "format", propOrder = {
    "dctmFormat",
    "dosExtension"
})
public class Format {

    @XmlElement(namespace = "")
    protected String dctmFormat;
    @XmlElement(namespace = "")
    protected String dosExtension;

    /**
     * Gets the value of the dctmFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDctmFormat() {
        return dctmFormat;
    }

    /**
     * Sets the value of the dctmFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDctmFormat(String value) {
        this.dctmFormat = value;
    }

    /**
     * Gets the value of the dosExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosExtension() {
        return dosExtension;
    }

    /**
     * Sets the value of the dosExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosExtension(String value) {
        this.dosExtension = value;
    }

}
