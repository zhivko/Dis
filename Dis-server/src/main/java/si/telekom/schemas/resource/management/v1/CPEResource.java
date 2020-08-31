
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CPEResource complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CPEResource">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/resource/management/v1}Resource">
 *       &lt;sequence>
 *         &lt;element name="serialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="friendlyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tr69Serial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macInet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macVoip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macIptv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="foreignName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sapCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="digital" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="maxPhoneNumbers" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="hasPort" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="supportIxrave" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="trunkConf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="defaultOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hdmProductClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prodOfferingId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="groupCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accessDevice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestBaseDevice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupForeignName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relatedResourceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="POS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CPEResource", propOrder = {
    "serialNum",
    "mac",
    "cpeId",
    "summary",
    "friendlyName",
    "macLabel",
    "tr69Serial",
    "macInet",
    "macVoip",
    "macIptv",
    "code",
    "name",
    "foreignName",
    "sapCode",
    "docUrl",
    "digital",
    "maxPhoneNumbers",
    "hasPort",
    "supportIxrave",
    "trunkConf",
    "defaultOwner",
    "hdmProductClass",
    "prodOfferingId",
    "groupCode",
    "groupName",
    "accessDevice",
    "requestBaseDevice",
    "groupForeignName",
    "relatedResourceId",
    "pos"
})
public class CPEResource
    extends Resource
{

    protected String serialNum;
    protected String mac;
    protected String cpeId;
    protected String summary;
    protected String friendlyName;
    protected String macLabel;
    protected String tr69Serial;
    protected String macInet;
    protected String macVoip;
    protected String macIptv;
    protected String code;
    protected String name;
    protected String foreignName;
    protected String sapCode;
    protected String docUrl;
    protected Long digital;
    protected Long maxPhoneNumbers;
    protected Long hasPort;
    protected Long supportIxrave;
    protected String trunkConf;
    protected String defaultOwner;
    protected String hdmProductClass;
    protected Long prodOfferingId;
    protected String groupCode;
    protected String groupName;
    protected String accessDevice;
    protected String requestBaseDevice;
    protected String groupForeignName;
    protected Long relatedResourceId;
    @XmlElement(name = "POS")
    protected String pos;

    /**
     * Gets the value of the serialNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Sets the value of the serialNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNum(String value) {
        this.serialNum = value;
    }

    /**
     * Gets the value of the mac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMac() {
        return mac;
    }

    /**
     * Sets the value of the mac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMac(String value) {
        this.mac = value;
    }

    /**
     * Gets the value of the cpeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpeId() {
        return cpeId;
    }

    /**
     * Sets the value of the cpeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpeId(String value) {
        this.cpeId = value;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
    }

    /**
     * Gets the value of the friendlyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFriendlyName() {
        return friendlyName;
    }

    /**
     * Sets the value of the friendlyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFriendlyName(String value) {
        this.friendlyName = value;
    }

    /**
     * Gets the value of the macLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacLabel() {
        return macLabel;
    }

    /**
     * Sets the value of the macLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacLabel(String value) {
        this.macLabel = value;
    }

    /**
     * Gets the value of the tr69Serial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTr69Serial() {
        return tr69Serial;
    }

    /**
     * Sets the value of the tr69Serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTr69Serial(String value) {
        this.tr69Serial = value;
    }

    /**
     * Gets the value of the macInet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacInet() {
        return macInet;
    }

    /**
     * Sets the value of the macInet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacInet(String value) {
        this.macInet = value;
    }

    /**
     * Gets the value of the macVoip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacVoip() {
        return macVoip;
    }

    /**
     * Sets the value of the macVoip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacVoip(String value) {
        this.macVoip = value;
    }

    /**
     * Gets the value of the macIptv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacIptv() {
        return macIptv;
    }

    /**
     * Sets the value of the macIptv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacIptv(String value) {
        this.macIptv = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

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
     * Gets the value of the foreignName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeignName() {
        return foreignName;
    }

    /**
     * Sets the value of the foreignName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeignName(String value) {
        this.foreignName = value;
    }

    /**
     * Gets the value of the sapCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapCode() {
        return sapCode;
    }

    /**
     * Sets the value of the sapCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapCode(String value) {
        this.sapCode = value;
    }

    /**
     * Gets the value of the docUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocUrl() {
        return docUrl;
    }

    /**
     * Sets the value of the docUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocUrl(String value) {
        this.docUrl = value;
    }

    /**
     * Gets the value of the digital property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDigital() {
        return digital;
    }

    /**
     * Sets the value of the digital property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDigital(Long value) {
        this.digital = value;
    }

    /**
     * Gets the value of the maxPhoneNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaxPhoneNumbers() {
        return maxPhoneNumbers;
    }

    /**
     * Sets the value of the maxPhoneNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaxPhoneNumbers(Long value) {
        this.maxPhoneNumbers = value;
    }

    /**
     * Gets the value of the hasPort property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHasPort() {
        return hasPort;
    }

    /**
     * Sets the value of the hasPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHasPort(Long value) {
        this.hasPort = value;
    }

    /**
     * Gets the value of the supportIxrave property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSupportIxrave() {
        return supportIxrave;
    }

    /**
     * Sets the value of the supportIxrave property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSupportIxrave(Long value) {
        this.supportIxrave = value;
    }

    /**
     * Gets the value of the trunkConf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrunkConf() {
        return trunkConf;
    }

    /**
     * Sets the value of the trunkConf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrunkConf(String value) {
        this.trunkConf = value;
    }

    /**
     * Gets the value of the defaultOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultOwner() {
        return defaultOwner;
    }

    /**
     * Sets the value of the defaultOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultOwner(String value) {
        this.defaultOwner = value;
    }

    /**
     * Gets the value of the hdmProductClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHdmProductClass() {
        return hdmProductClass;
    }

    /**
     * Sets the value of the hdmProductClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHdmProductClass(String value) {
        this.hdmProductClass = value;
    }

    /**
     * Gets the value of the prodOfferingId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProdOfferingId() {
        return prodOfferingId;
    }

    /**
     * Sets the value of the prodOfferingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProdOfferingId(Long value) {
        this.prodOfferingId = value;
    }

    /**
     * Gets the value of the groupCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * Sets the value of the groupCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupCode(String value) {
        this.groupCode = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the accessDevice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessDevice() {
        return accessDevice;
    }

    /**
     * Sets the value of the accessDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessDevice(String value) {
        this.accessDevice = value;
    }

    /**
     * Gets the value of the requestBaseDevice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestBaseDevice() {
        return requestBaseDevice;
    }

    /**
     * Sets the value of the requestBaseDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestBaseDevice(String value) {
        this.requestBaseDevice = value;
    }

    /**
     * Gets the value of the groupForeignName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupForeignName() {
        return groupForeignName;
    }

    /**
     * Sets the value of the groupForeignName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupForeignName(String value) {
        this.groupForeignName = value;
    }

    /**
     * Gets the value of the relatedResourceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRelatedResourceId() {
        return relatedResourceId;
    }

    /**
     * Sets the value of the relatedResourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRelatedResourceId(Long value) {
        this.relatedResourceId = value;
    }

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOS() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOS(String value) {
        this.pos = value;
    }

}
