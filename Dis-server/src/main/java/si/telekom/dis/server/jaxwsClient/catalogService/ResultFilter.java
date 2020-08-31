
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResultFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultEntity" type="{http://telekom.si/services/common/base/v1}ResultEntity" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultFilter", propOrder = {
    "resultEntities"
})
@XmlRootElement(name = "resultFilter")
public class ResultFilter {

    @XmlElement(name = "resultEntity", required = true)
    protected List<ResultEntity> resultEntities;

    /**
     * Gets the value of the resultEntities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultEntities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultEntity }
     * 
     * 
     */
    public List<ResultEntity> getResultEntities() {
        if (resultEntities == null) {
            resultEntities = new ArrayList<ResultEntity>();
        }
        return this.resultEntities;
    }

}
