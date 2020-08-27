
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetSystemInfoResponseMsg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSystemInfoResponseMsg">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/services/common/base/v1}ResponseMessage">
 *       &lt;sequence>
 *         &lt;element name="systemInfo" type="{http://telekom.si/services/common/base/v1}SystemInfo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSystemInfoResponseMsg", propOrder = {
    "systemInfos"
})
public class GetSystemInfoResponseMsg
    extends ResponseMessage
{

    @XmlElement(name = "systemInfo")
    protected List<SystemInfo> systemInfos;

    /**
     * Gets the value of the systemInfos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the systemInfos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSystemInfos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SystemInfo }
     * 
     * 
     */
    public List<SystemInfo> getSystemInfos() {
        if (systemInfos == null) {
            systemInfos = new ArrayList<SystemInfo>();
        }
        return this.systemInfos;
    }

}
