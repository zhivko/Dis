
package si.telekom.schemas.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogTableRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogTableRow">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/base/v1}TableRow">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://telekom.si/schemas/common/base/v1}Table" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogTableRow", propOrder = {
    "value"
})
public class CatalogTableRow
    extends TableRow
{

    protected Table value;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link Table }
     *     
     */
    public Table getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link Table }
     *     
     */
    public void setValue(Table value) {
        this.value = value;
    }

}
