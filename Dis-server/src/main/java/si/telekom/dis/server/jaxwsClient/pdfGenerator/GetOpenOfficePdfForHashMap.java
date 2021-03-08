
package si.telekom.dis.server.jaxwsClient.pdfGenerator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getOpenOfficePdfForHashMap complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getOpenOfficePdfForHashMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="templateTypeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0" form="qualified"/>
 *         &lt;element name="paramValues" type="{http://templates.mobitel.com/}hashMapWrapper" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOpenOfficePdfForHashMap", propOrder = {
    "templateTypeId",
    "paramValues"
})
@XmlRootElement(name = "getOpenOfficePdfForHashMap")
public class GetOpenOfficePdfForHashMap {

    protected Integer templateTypeId;
    protected HashMapWrapper paramValues;

    /**
     * Gets the value of the templateTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTemplateTypeId() {
        return templateTypeId;
    }

    /**
     * Sets the value of the templateTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTemplateTypeId(Integer value) {
        this.templateTypeId = value;
    }

    /**
     * Gets the value of the paramValues property.
     * 
     * @return
     *     possible object is
     *     {@link HashMapWrapper }
     *     
     */
    public HashMapWrapper getParamValues() {
        return paramValues;
    }

    /**
     * Sets the value of the paramValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link HashMapWrapper }
     *     
     */
    public void setParamValues(HashMapWrapper value) {
        this.paramValues = value;
    }

}
