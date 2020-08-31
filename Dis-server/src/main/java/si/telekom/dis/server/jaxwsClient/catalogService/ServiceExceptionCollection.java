
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceExceptionCollection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceExceptionCollection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceException" type="{http://telekom.si/services/common/base/v1}ServiceException" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceExceptionCollection", propOrder = {
    "serviceExceptions"
})
public class ServiceExceptionCollection {

    @XmlElement(name = "serviceException", required = true)
    protected List<CatalogServiceException> serviceExceptions;

    /**
     * Gets the value of the serviceExceptions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceExceptions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceExceptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogServiceException }
     * 
     * 
     */
    public List<CatalogServiceException> getServiceExceptions() {
        if (serviceExceptions == null) {
            serviceExceptions = new ArrayList<CatalogServiceException>();
        }
        return this.serviceExceptions;
    }

}
