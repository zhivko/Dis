
package si.telekom.services.common.base.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseMessageHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseMessageHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceInfoCollection" type="{http://telekom.si/services/common/base/v1}ServiceInfoCollection" minOccurs="0"/>
 *         &lt;element name="pagingInfo" type="{http://telekom.si/services/common/base/v1}PagingInfo" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseMessageHeader", propOrder = {
    "serviceInfoCollection",
    "pagingInfo",
    "transactionId"
})
public class ResponseMessageHeader {

    protected ServiceInfoCollection serviceInfoCollection;
    protected PagingInfo pagingInfo;
    protected String transactionId;

    /**
     * Gets the value of the serviceInfoCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceInfoCollection }
     *     
     */
    public ServiceInfoCollection getServiceInfoCollection() {
        return serviceInfoCollection;
    }

    /**
     * Sets the value of the serviceInfoCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceInfoCollection }
     *     
     */
    public void setServiceInfoCollection(ServiceInfoCollection value) {
        this.serviceInfoCollection = value;
    }

    /**
     * Gets the value of the pagingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PagingInfo }
     *     
     */
    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    /**
     * Sets the value of the pagingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PagingInfo }
     *     
     */
    public void setPagingInfo(PagingInfo value) {
        this.pagingInfo = value;
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

}
