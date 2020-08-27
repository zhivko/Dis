
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Alarm type of business interaction
 * 
 * <p>Java class for BusinessInteractionAlarm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionAlarm">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteraction">
 *       &lt;sequence>
 *         &lt;element name="alarmDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="alarmNumber" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="alarmType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="averageValue" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contactRedirectionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="currentValue" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="limitValue" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="redirectionMark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarkDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successStatusId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionAlarm", propOrder = {
    "alarmDate",
    "alarmNumber",
    "alarmType",
    "averageValue",
    "contactRedirectionId",
    "currentValue",
    "duration",
    "limitValue",
    "redirectionMark",
    "remark",
    "remarkDetail",
    "successStatusId"
})
public class BusinessInteractionAlarm
    extends BusinessInteraction
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar alarmDate;
    protected Long alarmNumber;
    protected String alarmType;
    protected Long averageValue;
    protected Long contactRedirectionId;
    protected Long currentValue;
    protected Long duration;
    protected Long limitValue;
    protected String redirectionMark;
    protected String remark;
    protected String remarkDetail;
    protected Long successStatusId;

    /**
     * Gets the value of the alarmDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAlarmDate() {
        return alarmDate;
    }

    /**
     * Sets the value of the alarmDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAlarmDate(XMLGregorianCalendar value) {
        this.alarmDate = value;
    }

    /**
     * Gets the value of the alarmNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAlarmNumber() {
        return alarmNumber;
    }

    /**
     * Sets the value of the alarmNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAlarmNumber(Long value) {
        this.alarmNumber = value;
    }

    /**
     * Gets the value of the alarmType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlarmType() {
        return alarmType;
    }

    /**
     * Sets the value of the alarmType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlarmType(String value) {
        this.alarmType = value;
    }

    /**
     * Gets the value of the averageValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAverageValue() {
        return averageValue;
    }

    /**
     * Sets the value of the averageValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAverageValue(Long value) {
        this.averageValue = value;
    }

    /**
     * Gets the value of the contactRedirectionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContactRedirectionId() {
        return contactRedirectionId;
    }

    /**
     * Sets the value of the contactRedirectionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContactRedirectionId(Long value) {
        this.contactRedirectionId = value;
    }

    /**
     * Gets the value of the currentValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets the value of the currentValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCurrentValue(Long value) {
        this.currentValue = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDuration(Long value) {
        this.duration = value;
    }

    /**
     * Gets the value of the limitValue property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLimitValue() {
        return limitValue;
    }

    /**
     * Sets the value of the limitValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLimitValue(Long value) {
        this.limitValue = value;
    }

    /**
     * Gets the value of the redirectionMark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedirectionMark() {
        return redirectionMark;
    }

    /**
     * Sets the value of the redirectionMark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedirectionMark(String value) {
        this.redirectionMark = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the remarkDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarkDetail() {
        return remarkDetail;
    }

    /**
     * Sets the value of the remarkDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarkDetail(String value) {
        this.remarkDetail = value;
    }

    /**
     * Gets the value of the successStatusId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSuccessStatusId() {
        return successStatusId;
    }

    /**
     * Sets the value of the successStatusId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSuccessStatusId(Long value) {
        this.successStatusId = value;
    }

}
