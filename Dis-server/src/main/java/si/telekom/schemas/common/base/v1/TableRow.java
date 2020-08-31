
package si.telekom.schemas.common.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TableRow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TableRow">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="column" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="tableCollection" type="{http://telekom.si/schemas/common/base/v1}TableCollection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableRow", propOrder = {
    "column",
    "tableCollection"
})
@XmlSeeAlso({
    CatalogTableRow.class
})
public class TableRow {

    @XmlElement(required = true)
    protected List<String> column;
    protected TableCollection tableCollection;

    /**
     * Gets the value of the column property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the column property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColumn().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getColumn() {
        if (column == null) {
            column = new ArrayList<String>();
        }
        return this.column;
    }

    /**
     * Gets the value of the tableCollection property.
     * 
     * @return
     *     possible object is
     *     {@link TableCollection }
     *     
     */
    public TableCollection getTableCollection() {
        return tableCollection;
    }

    /**
     * Sets the value of the tableCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link TableCollection }
     *     
     */
    public void setTableCollection(TableCollection value) {
        this.tableCollection = value;
    }

}
