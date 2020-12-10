
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOOPdf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOOPdf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="template" type="{http://erender.telekom.si/}Template" minOccurs="0" form="qualified"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="stringByRef" type="{http://templates.mobitel.com}StringByRef" minOccurs="0" form="qualified"/>
 *         &lt;element name="hashMapWrapper" type="{http://templates.mobitel.com/}hashMapWrapper" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOOPdf", propOrder = {
    "template",
    "id",
    "stringByRef",
    "hashMapWrapper"
})
@XmlRootElement(name = "getOOPdf")
public class GetOOPdf {

    protected Template template;
    @XmlElement(namespace = "")
    protected Integer id;
    protected StringByRef stringByRef;
    protected HashMapWrapper hashMapWrapper;

    /**
     * Gets the value of the template property.
     * 
     * @return
     *     possible object is
     *     {@link Template }
     *     
     */
    public Template getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     * 
     * @param value
     *     allowed object is
     *     {@link Template }
     *     
     */
    public void setTemplate(Template value) {
        this.template = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

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
     * Gets the value of the hashMapWrapper property.
     * 
     * @return
     *     possible object is
     *     {@link HashMapWrapper }
     *     
     */
    public HashMapWrapper getHashMapWrapper() {
        return hashMapWrapper;
    }

    /**
     * Sets the value of the hashMapWrapper property.
     * 
     * @param value
     *     allowed object is
     *     {@link HashMapWrapper }
     *     
     */
    public void setHashMapWrapper(HashMapWrapper value) {
        this.hashMapWrapper = value;
    }

}
