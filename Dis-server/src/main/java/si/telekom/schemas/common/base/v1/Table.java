
package si.telekom.schemas.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Table complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Table">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="header" type="{http://telekom.si/schemas/common/base/v1}TableRow" minOccurs="0"/>
 *         &lt;element name="dataType" type="{http://telekom.si/schemas/common/base/v1}TableRow" minOccurs="0"/>
 *         &lt;element name="rowCollection" type="{http://telekom.si/schemas/common/base/v1}TableRowCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Table", propOrder = {
    "name",
    "header",
    "dataType",
    "rowCollection"
})
public class Table {

    protected String name;
    protected TableRow header;
    protected TableRow dataType;
    protected TableRowCollection rowCollection;

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
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link TableRow }
     *     
     */
    public TableRow getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableRow }
     *     
     */
    public void setHeader(TableRow value) {
        this.header = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link TableRow }
     *     
     */
    public TableRow getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableRow }
     *     
     */
    public void setDataType(TableRow value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the rowCollection property.
     * 
     * @return
     *     possible object is
     *     {@link TableRowCollection }
     *     
     */
    public TableRowCollection getRowCollection() {
        return rowCollection;
    }

    /**
     * Sets the value of the rowCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableRowCollection }
     *     
     */
    public void setRowCollection(TableRowCollection value) {
        this.rowCollection = value;
    }

}
