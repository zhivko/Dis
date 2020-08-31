
package si.telekom.services.common.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
    "resultEntity"
})
public class ResultFilter {

    @XmlElement(required = true)
    protected List<ResultEntity> resultEntity;

    /**
     * Gets the value of the resultEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultEntity }
     * 
     * 
     */
    public List<ResultEntity> getResultEntity() {
        if (resultEntity == null) {
            resultEntity = new ArrayList<ResultEntity>();
        }
        return this.resultEntity;
    }

}
