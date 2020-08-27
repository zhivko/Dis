
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.Document;


/**
 * Attachement of interaction item
 * 
 * <p>Java class for Attachment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Attachment">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Document">
 *       &lt;sequence>
 *         &lt;element name="attachemntTypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DCMTConfirmationtimes" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="releaseTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="responseStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attachment", propOrder = {
    "attachemntTypeId",
    "dcmtConfirmationtimes",
    "note",
    "releaseTimestamp",
    "responseStatusCode"
})
public class Attachment
    extends Document
{

    protected Long attachemntTypeId;
    @XmlElement(name = "DCMTConfirmationtimes")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dcmtConfirmationtimes;
    protected String note;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar releaseTimestamp;
    protected String responseStatusCode;

    /**
     * Gets the value of the attachemntTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAttachemntTypeId() {
        return attachemntTypeId;
    }

    /**
     * Sets the value of the attachemntTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAttachemntTypeId(Long value) {
        this.attachemntTypeId = value;
    }

    /**
     * Gets the value of the dcmtConfirmationtimes property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDCMTConfirmationtimes() {
        return dcmtConfirmationtimes;
    }

    /**
     * Sets the value of the dcmtConfirmationtimes property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDCMTConfirmationtimes(XMLGregorianCalendar value) {
        this.dcmtConfirmationtimes = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the releaseTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReleaseTimestamp() {
        return releaseTimestamp;
    }

    /**
     * Sets the value of the releaseTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReleaseTimestamp(XMLGregorianCalendar value) {
        this.releaseTimestamp = value;
    }

    /**
     * Gets the value of the responseStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseStatusCode() {
        return responseStatusCode;
    }

    /**
     * Sets the value of the responseStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseStatusCode(String value) {
        this.responseStatusCode = value;
    }

}
