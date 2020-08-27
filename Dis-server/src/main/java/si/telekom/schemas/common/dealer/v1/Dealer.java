
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.party.v1.PartyRole;


/**
 * <p>Java class for Dealer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dealer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/party/v1}PartyRole">
 *       &lt;sequence>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="directory" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="warningRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="class" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="representativeCollection" type="{http://telekom.si/schemas/common/dealer/v1}RepresentativeCollection" minOccurs="0"/>
 *         &lt;element name="contractCollection" type="{http://telekom.si/schemas/common/dealer/v1}ContractCollection" minOccurs="0"/>
 *         &lt;element name="pointOfSaleCollection" type="{http://telekom.si/schemas/common/dealer/v1}PointOfSaleCollection" minOccurs="0"/>
 *         &lt;element name="dealerRoleCollection" type="{http://telekom.si/schemas/common/dealer/v1}DealerRoleCollection" minOccurs="0"/>
 *         &lt;element name="salesTypeCollection" type="{http://telekom.si/schemas/common/dealer/v1}SalesTypeCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dealer", propOrder = {
    "note",
    "directory",
    "warningRemark",
    "clazz",
    "representativeCollection",
    "contractCollection",
    "pointOfSaleCollection",
    "dealerRoleCollection",
    "salesTypeCollection"
})
@XmlSeeAlso({
    Isp.class
})
public class Dealer
    extends PartyRole
{

    protected String note;
    protected Integer directory;
    protected String warningRemark;
    @XmlElement(name = "class")
    protected String clazz;
    protected RepresentativeCollection representativeCollection;
    protected ContractCollection contractCollection;
    protected PointOfSaleCollection pointOfSaleCollection;
    protected DealerRoleCollection dealerRoleCollection;
    protected SalesTypeCollection salesTypeCollection;

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
     * Gets the value of the directory property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDirectory(Integer value) {
        this.directory = value;
    }

    /**
     * Gets the value of the warningRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarningRemark() {
        return warningRemark;
    }

    /**
     * Sets the value of the warningRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarningRemark(String value) {
        this.warningRemark = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the representativeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link RepresentativeCollection }
     *     
     */
    public RepresentativeCollection getRepresentativeCollection() {
        return representativeCollection;
    }

    /**
     * Sets the value of the representativeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepresentativeCollection }
     *     
     */
    public void setRepresentativeCollection(RepresentativeCollection value) {
        this.representativeCollection = value;
    }

    /**
     * Gets the value of the contractCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ContractCollection }
     *     
     */
    public ContractCollection getContractCollection() {
        return contractCollection;
    }

    /**
     * Sets the value of the contractCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractCollection }
     *     
     */
    public void setContractCollection(ContractCollection value) {
        this.contractCollection = value;
    }

    /**
     * Gets the value of the pointOfSaleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link PointOfSaleCollection }
     *     
     */
    public PointOfSaleCollection getPointOfSaleCollection() {
        return pointOfSaleCollection;
    }

    /**
     * Sets the value of the pointOfSaleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link PointOfSaleCollection }
     *     
     */
    public void setPointOfSaleCollection(PointOfSaleCollection value) {
        this.pointOfSaleCollection = value;
    }

    /**
     * Gets the value of the dealerRoleCollection property.
     * 
     * @return
     *     possible object is
     *     {@link DealerRoleCollection }
     *     
     */
    public DealerRoleCollection getDealerRoleCollection() {
        return dealerRoleCollection;
    }

    /**
     * Sets the value of the dealerRoleCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link DealerRoleCollection }
     *     
     */
    public void setDealerRoleCollection(DealerRoleCollection value) {
        this.dealerRoleCollection = value;
    }

    /**
     * Gets the value of the salesTypeCollection property.
     * 
     * @return
     *     possible object is
     *     {@link SalesTypeCollection }
     *     
     */
    public SalesTypeCollection getSalesTypeCollection() {
        return salesTypeCollection;
    }

    /**
     * Sets the value of the salesTypeCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesTypeCollection }
     *     
     */
    public void setSalesTypeCollection(SalesTypeCollection value) {
        this.salesTypeCollection = value;
    }

}
