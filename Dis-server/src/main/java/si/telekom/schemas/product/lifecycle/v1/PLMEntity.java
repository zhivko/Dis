
package si.telekom.schemas.product.lifecycle.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.Entity;


/**
 * Ta tip predstavlja PLM entiteto. K bazni entiteti Entity dodaja atribute, ki jih vsebujejo vse PLM entitete (npr. version).
 * 
 * <p>Java class for PLMEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PLMEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}Entity">
 *       &lt;sequence>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minorVersionFrom" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="minorVersionTo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PLMEntity", propOrder = {
    "version",
    "deleted",
    "minorVersionFrom",
    "minorVersionTo"
})
@XmlSeeAlso({
    ProductSpecification.class,
    ProductOffering.class,
    ProductOfferingPrice.class
})
public class PLMEntity
    extends Entity
{

    protected Long version;
    protected Integer deleted;
    protected Long minorVersionFrom;
    protected Long minorVersionTo;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVersion(Long value) {
        this.version = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeleted(Integer value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the minorVersionFrom property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinorVersionFrom() {
        return minorVersionFrom;
    }

    /**
     * Sets the value of the minorVersionFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinorVersionFrom(Long value) {
        this.minorVersionFrom = value;
    }

    /**
     * Gets the value of the minorVersionTo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMinorVersionTo() {
        return minorVersionTo;
    }

    /**
     * Sets the value of the minorVersionTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMinorVersionTo(Long value) {
        this.minorVersionTo = value;
    }

}
