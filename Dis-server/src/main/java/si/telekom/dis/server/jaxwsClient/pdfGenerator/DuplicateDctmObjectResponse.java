
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for duplicateDctmObjectResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="duplicateDctmObjectResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="newBarcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "duplicateDctmObjectResponse", propOrder = {
    "newBarcode"
})
@XmlRootElement(name = "duplicateDctmObjectResponse")
public class DuplicateDctmObjectResponse {

    protected String newBarcode;

    /**
     * Gets the value of the newBarcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewBarcode() {
        return newBarcode;
    }

    /**
     * Sets the value of the newBarcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewBarcode(String value) {
        this.newBarcode = value;
    }

}
