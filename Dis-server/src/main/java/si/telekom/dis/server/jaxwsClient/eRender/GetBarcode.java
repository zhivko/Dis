
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for getBarcode complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBarcode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iTypePar" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *         &lt;element name="sCapturePar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="sCompanyPar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="sLocationPar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *         &lt;element name="dateTimePar" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0" form="qualified"/>
 *         &lt;element name="nQuantityPar" type="{http://www.w3.org/2001/XMLSchema}int" form="qualified"/>
 *         &lt;element name="sourceSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBarcode", propOrder = {
    "iTypePar",
    "sCapturePar",
    "sCompanyPar",
    "sLocationPar",
    "dateTimePar",
    "nQuantityPar",
    "sourceSystem"
})
@XmlRootElement(name = "getBarcode")
public class GetBarcode {

    protected int iTypePar;
    protected String sCapturePar;
    protected String sCompanyPar;
    protected String sLocationPar;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateTimePar;
    protected int nQuantityPar;
    protected String sourceSystem;

    /**
     * Gets the value of the iTypePar property.
     * 
     */
    public int getITypePar() {
        return iTypePar;
    }

    /**
     * Sets the value of the iTypePar property.
     * 
     */
    public void setITypePar(int value) {
        this.iTypePar = value;
    }

    /**
     * Gets the value of the sCapturePar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCapturePar() {
        return sCapturePar;
    }

    /**
     * Sets the value of the sCapturePar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCapturePar(String value) {
        this.sCapturePar = value;
    }

    /**
     * Gets the value of the sCompanyPar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCompanyPar() {
        return sCompanyPar;
    }

    /**
     * Sets the value of the sCompanyPar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCompanyPar(String value) {
        this.sCompanyPar = value;
    }

    /**
     * Gets the value of the sLocationPar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSLocationPar() {
        return sLocationPar;
    }

    /**
     * Sets the value of the sLocationPar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSLocationPar(String value) {
        this.sLocationPar = value;
    }

    /**
     * Gets the value of the dateTimePar property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateTimePar() {
        return dateTimePar;
    }

    /**
     * Sets the value of the dateTimePar property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateTimePar(XMLGregorianCalendar value) {
        this.dateTimePar = value;
    }

    /**
     * Gets the value of the nQuantityPar property.
     * 
     */
    public int getNQuantityPar() {
        return nQuantityPar;
    }

    /**
     * Sets the value of the nQuantityPar property.
     * 
     */
    public void setNQuantityPar(int value) {
        this.nQuantityPar = value;
    }

    /**
     * Gets the value of the sourceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

}
