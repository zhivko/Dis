
package si.telekom.services.common.base.v1;

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
    "serviceException"
})
public class ServiceExceptionCollection {

    @XmlElement(required = true)
    protected List<ServiceException> serviceException;

    /**
     * Gets the value of the serviceException property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceException property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceException().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceException }
     * 
     * 
     */
    public List<ServiceException> getServiceException() {
        if (serviceException == null) {
            serviceException = new ArrayList<ServiceException>();
        }
        return this.serviceException;
    }

}
