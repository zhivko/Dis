
package si.telekom.schemas.product.fulfillment.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import si.telekom.schemas.common.base.v1.Entity;
import si.telekom.schemas.common.base.v1.InvolvmentRoleCollection;
import si.telekom.schemas.common.base.v1.Quantity;
import si.telekom.schemas.product.lifecycle.v1.ProductSpecification;


/**
 * <p>Java class for Product complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Product">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="activatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://telekom.si/schemas/product/fulfillment/v1}VersionInfo" minOccurs="0"/>
 *         &lt;element name="treeVersionTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="specification" type="{http://telekom.si/schemas/product/lifecycle/v1}ProductSpecification" minOccurs="0"/>
 *         &lt;element name="assignedOffering" type="{http://telekom.si/schemas/product/fulfillment/v1}AssignedProductOffering" minOccurs="0"/>
 *         &lt;element name="accessId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://telekom.si/schemas/common/base/v1}Quantity" minOccurs="0"/>
 *         &lt;element name="enteredOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="enteredByPointOfSaleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enteredByUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soldByPointOfSaleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soldByUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provisionendOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lastModifiedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="state" type="{http://telekom.si/schemas/product/fulfillment/v1}ProductState" minOccurs="0"/>
 *         &lt;element name="involvmentRoleCollection" type="{http://telekom.si/schemas/common/base/v1}InvolvmentRoleCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="instanceVersion" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Product", propOrder = {
    "activatedOn",
    "name",
    "version",
    "treeVersionTimestamp",
    "specification",
    "assignedOffering",
    "accessId",
    "serviceId",
    "quantity",
    "enteredOn",
    "enteredByPointOfSaleId",
    "enteredByUsername",
    "soldByPointOfSaleId",
    "soldByUsername",
    "provisionendOn",
    "lastModifiedOn",
    "state",
    "involvmentRoleCollection"
})
@XmlSeeAlso({
    ProductDetail.class
})
public class Product
    extends Entity
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activatedOn;
    protected String name;
    protected VersionInfo version;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar treeVersionTimestamp;
    protected ProductSpecification specification;
    protected AssignedProductOffering assignedOffering;
    protected String accessId;
    protected String serviceId;
    protected Quantity quantity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar enteredOn;
    protected String enteredByPointOfSaleId;
    protected String enteredByUsername;
    protected String soldByPointOfSaleId;
    protected String soldByUsername;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar provisionendOn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedOn;
    protected ProductState state;
    protected InvolvmentRoleCollection involvmentRoleCollection;
    @XmlAttribute(name = "instanceVersion")
    protected Integer instanceVersion;

    /**
     * Gets the value of the activatedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivatedOn() {
        return activatedOn;
    }

    /**
     * Sets the value of the activatedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivatedOn(XMLGregorianCalendar value) {
        this.activatedOn = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link VersionInfo }
     *     
     */
    public VersionInfo getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionInfo }
     *     
     */
    public void setVersion(VersionInfo value) {
        this.version = value;
    }

    /**
     * Gets the value of the treeVersionTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTreeVersionTimestamp() {
        return treeVersionTimestamp;
    }

    /**
     * Sets the value of the treeVersionTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTreeVersionTimestamp(XMLGregorianCalendar value) {
        this.treeVersionTimestamp = value;
    }

    /**
     * Gets the value of the specification property.
     * 
     * @return
     *     possible object is
     *     {@link ProductSpecification }
     *     
     */
    public ProductSpecification getSpecification() {
        return specification;
    }

    /**
     * Sets the value of the specification property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductSpecification }
     *     
     */
    public void setSpecification(ProductSpecification value) {
        this.specification = value;
    }

    /**
     * Gets the value of the assignedOffering property.
     * 
     * @return
     *     possible object is
     *     {@link AssignedProductOffering }
     *     
     */
    public AssignedProductOffering getAssignedOffering() {
        return assignedOffering;
    }

    /**
     * Sets the value of the assignedOffering property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssignedProductOffering }
     *     
     */
    public void setAssignedOffering(AssignedProductOffering value) {
        this.assignedOffering = value;
    }

    /**
     * Gets the value of the accessId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessId() {
        return accessId;
    }

    /**
     * Sets the value of the accessId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessId(String value) {
        this.accessId = value;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Quantity }
     *     
     */
    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Quantity }
     *     
     */
    public void setQuantity(Quantity value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the enteredOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEnteredOn() {
        return enteredOn;
    }

    /**
     * Sets the value of the enteredOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEnteredOn(XMLGregorianCalendar value) {
        this.enteredOn = value;
    }

    /**
     * Gets the value of the enteredByPointOfSaleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnteredByPointOfSaleId() {
        return enteredByPointOfSaleId;
    }

    /**
     * Sets the value of the enteredByPointOfSaleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnteredByPointOfSaleId(String value) {
        this.enteredByPointOfSaleId = value;
    }

    /**
     * Gets the value of the enteredByUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnteredByUsername() {
        return enteredByUsername;
    }

    /**
     * Sets the value of the enteredByUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnteredByUsername(String value) {
        this.enteredByUsername = value;
    }

    /**
     * Gets the value of the soldByPointOfSaleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldByPointOfSaleId() {
        return soldByPointOfSaleId;
    }

    /**
     * Sets the value of the soldByPointOfSaleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldByPointOfSaleId(String value) {
        this.soldByPointOfSaleId = value;
    }

    /**
     * Gets the value of the soldByUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoldByUsername() {
        return soldByUsername;
    }

    /**
     * Sets the value of the soldByUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoldByUsername(String value) {
        this.soldByUsername = value;
    }

    /**
     * Gets the value of the provisionendOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProvisionendOn() {
        return provisionendOn;
    }

    /**
     * Sets the value of the provisionendOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProvisionendOn(XMLGregorianCalendar value) {
        this.provisionendOn = value;
    }

    /**
     * Gets the value of the lastModifiedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedOn() {
        return lastModifiedOn;
    }

    /**
     * Sets the value of the lastModifiedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedOn(XMLGregorianCalendar value) {
        this.lastModifiedOn = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link ProductState }
     *     
     */
    public ProductState getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductState }
     *     
     */
    public void setState(ProductState value) {
        this.state = value;
    }

    /**
     * Gets the value of the involvmentRoleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link InvolvmentRoleCollection }
     *     
     */
    public InvolvmentRoleCollection getInvolvmentRoleCollection() {
        return involvmentRoleCollection;
    }

    /**
     * Sets the value of the involvmentRoleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvolvmentRoleCollection }
     *     
     */
    public void setInvolvmentRoleCollection(InvolvmentRoleCollection value) {
        this.involvmentRoleCollection = value;
    }

    /**
     * Gets the value of the instanceVersion property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInstanceVersion() {
        return instanceVersion;
    }

    /**
     * Sets the value of the instanceVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInstanceVersion(Integer value) {
        this.instanceVersion = value;
    }

}
