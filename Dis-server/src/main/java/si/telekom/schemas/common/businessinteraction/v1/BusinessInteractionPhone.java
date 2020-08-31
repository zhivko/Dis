
package si.telekom.schemas.common.businessinteraction.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import si.telekom.schemas.common.base.v1.CatalogValue;


/**
 * Phone type of business interaction
 * 
 * <p>Java class for BusinessInteractionPhone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessInteractionPhone">
 *   &lt;complexContent>
 *     &lt;extension base="{http://telekom.si/schemas/common/businessinteraction/v1}BusinessInteraction">
 *       &lt;sequence>
 *         &lt;element name="redirection" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *         &lt;element name="redirectionMark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="skillGroupNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="skillId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="solidusUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successStatus" type="{http://telekom.si/schemas/common/base/v1}CatalogValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessInteractionPhone", propOrder = {
    "redirection",
    "redirectionMark",
    "duration",
    "skillGroupNum",
    "skillId",
    "solidusUser",
    "successStatus"
})
public class BusinessInteractionPhone
    extends BusinessInteraction
{

    protected CatalogValue redirection;
    protected String redirectionMark;
    protected Long duration;
    protected String skillGroupNum;
    protected String skillId;
    protected String solidusUser;
    protected CatalogValue successStatus;

    /**
     * Gets the value of the redirection property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getRedirection() {
        return redirection;
    }

    /**
     * Sets the value of the redirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setRedirection(CatalogValue value) {
        this.redirection = value;
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
     * Gets the value of the skillGroupNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkillGroupNum() {
        return skillGroupNum;
    }

    /**
     * Sets the value of the skillGroupNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkillGroupNum(String value) {
        this.skillGroupNum = value;
    }

    /**
     * Gets the value of the skillId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkillId() {
        return skillId;
    }

    /**
     * Sets the value of the skillId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkillId(String value) {
        this.skillId = value;
    }

    /**
     * Gets the value of the solidusUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolidusUser() {
        return solidusUser;
    }

    /**
     * Sets the value of the solidusUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolidusUser(String value) {
        this.solidusUser = value;
    }

    /**
     * Gets the value of the successStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogValue }
     *     
     */
    public CatalogValue getSuccessStatus() {
        return successStatus;
    }

    /**
     * Sets the value of the successStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogValue }
     *     
     */
    public void setSuccessStatus(CatalogValue value) {
        this.successStatus = value;
    }

}
