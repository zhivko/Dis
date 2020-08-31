
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}filter" minOccurs="0"/>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}resultFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchFilter", propOrder = {
    "filter",
    "resultFilter"
})
@XmlRootElement(name = "searchFilter")
public class SearchFilter {

    protected Filter filter;
    protected ResultFilter resultFilter;

    /**
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link Filter }
     *     
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Filter }
     *     
     */
    public void setFilter(Filter value) {
        this.filter = value;
    }

    /**
     * Gets the value of the resultFilter property.
     * 
     * @return
     *     possible object is
     *     {@link ResultFilter }
     *     
     */
    public ResultFilter getResultFilter() {
        return resultFilter;
    }

    /**
     * Sets the value of the resultFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultFilter }
     *     
     */
    public void setResultFilter(ResultFilter value) {
        this.resultFilter = value;
    }

}
