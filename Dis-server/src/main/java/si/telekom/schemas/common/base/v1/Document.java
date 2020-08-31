
package si.telekom.schemas.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.businessinteraction.v1.Attachment;
import si.telekom.schemas.common.party.v1.User;
import si.telekom.schemas.product.fulfillment.v1.OrderDocument;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="barcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentContent" type="{http://telekom.si/schemas/common/base/v1}Content" minOccurs="0"/>
 *         &lt;element name="type" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="createdOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *         &lt;element name="modifiedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="modifiedBy" type="{http://telekom.si/schemas/common/party/v1}User" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "barcode",
    "title",
    "documentContent",
    "type",
    "createdOn",
    "createdBy",
    "modifiedOn",
    "modifiedBy"
})
@XmlSeeAlso({
    OrderDocument.class,
    Attachment.class
})
public class Document
    extends Entity
{

    protected String barcode;
    protected String title;
    protected Content documentContent;
    protected CatalogValue type;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdOn;
    protected User createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifiedOn;
    protected User modifiedBy;

    /**
     * Gets the value of the barcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Sets the value of the barcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarcode(String value) {
        this.barcode = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the documentContent property.
     * 
     * @return
     *     possible object is
     *     {@link Content }
     *     
     */
    public Content getDocumentContent() {
        return documentContent;
    }

    /**
     * Sets the value of the documentContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Content }
     *     
     */
    public void setDocumentContent(Content value) {
        this.documentContent = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setType(CatalogValue value) {
        this.type = value;
    }

    /**
     * Gets the value of the createdOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the value of the createdOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedOn(XMLGregorianCalendar value) {
        this.createdOn = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setCreatedBy(User value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the modifiedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedOn() {
        return modifiedOn;
    }

    /**
     * Sets the value of the modifiedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedOn(XMLGregorianCalendar value) {
        this.modifiedOn = value;
    }

    /**
     * Gets the value of the modifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getModifiedBy() {
        return modifiedBy;
    }

    /**
     * Sets the value of the modifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setModifiedBy(User value) {
        this.modifiedBy = value;
    }

}
