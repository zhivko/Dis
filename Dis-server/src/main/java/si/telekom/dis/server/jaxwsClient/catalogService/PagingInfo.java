
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PagingInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PagingInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}pagingFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PagingInfo", propOrder = {
    "count",
    "pagingFilter"
})
public class PagingInfo {

    protected Long count;
    protected PagingFilter pagingFilter;

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCount(Long value) {
        this.count = value;
    }

    /**
     * Gets the value of the pagingFilter property.
     * 
     * @return
     *     possible object is
     *     {@link PagingFilter }
     *     
     */
    public PagingFilter getPagingFilter() {
        return pagingFilter;
    }

    /**
     * Sets the value of the pagingFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link PagingFilter }
     *     
     */
    public void setPagingFilter(PagingFilter value) {
        this.pagingFilter = value;
    }

}
