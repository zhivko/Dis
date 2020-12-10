
package si.telekom.dis.server.jaxwsClient.eRender;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generatePdfAndRaiseMinorVersion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generatePdfAndRaiseMinorVersion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://erender.telekom.com}barcodes" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://erender.telekom.com}inetids" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://templates.mobitel.com}osName" minOccurs="0"/>
 *         &lt;element ref="{http://templates.mobitel.com}domain" minOccurs="0"/>
 *         &lt;element ref="{http://templates.mobitel.com}password" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generatePdfAndRaiseMinorVersion", propOrder = {
    "barcodes",
    "inetids",
    "osName",
    "domain",
    "password"
})
@XmlRootElement(name = "generatePdfAndRaiseMinorVersion")
public class GeneratePdfAndRaiseMinorVersion {

    @XmlElement(namespace = "http://erender.telekom.com")
    protected List<String> barcodes;
    @XmlElement(namespace = "http://erender.telekom.com")
    protected List<String> inetids;
    @XmlElement(namespace = "http://templates.mobitel.com")
    protected String osName;
    @XmlElement(namespace = "http://templates.mobitel.com")
    protected String domain;
    @XmlElement(namespace = "http://templates.mobitel.com")
    protected String password;

    /**
     * Gets the value of the barcodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the barcodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBarcodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBarcodes() {
        if (barcodes == null) {
            barcodes = new ArrayList<String>();
        }
        return this.barcodes;
    }

    /**
     * Gets the value of the inetids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inetids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInetids().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInetids() {
        if (inetids == null) {
            inetids = new ArrayList<String>();
        }
        return this.inetids;
    }

    /**
     * Gets the value of the osName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOsName() {
        return osName;
    }

    /**
     * Sets the value of the osName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOsName(String value) {
        this.osName = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

}
