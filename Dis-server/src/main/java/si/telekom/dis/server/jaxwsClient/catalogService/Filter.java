
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Filter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Filter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}filter" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://telekom.si/services/common/base/v1}condition" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="unaryOperator" type="{http://telekom.si/services/common/base/v1}UnaryOperatorEnumRestriction" />
 *       &lt;attribute name="logicalOperator" type="{http://telekom.si/services/common/base/v1}LogicalOperatorEnumRestriction" default="AND" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Filter", propOrder = {
    "filters",
    "conditions"
})
@XmlRootElement(name = "filter")
public class Filter {

    @XmlElement(name = "filter")
    protected List<Filter> filters;
    @XmlElement(name = "condition")
    protected List<Condition> conditions;
    @XmlAttribute(name = "unaryOperator")
    protected UnaryOperatorEnumRestriction unaryOperator;
    @XmlAttribute(name = "logicalOperator")
    protected LogicalOperatorEnumRestriction logicalOperator;

    /**
     * Gets the value of the filters property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filters property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilters().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Filter }
     * 
     * 
     */
    public List<Filter> getFilters() {
        if (filters == null) {
            filters = new ArrayList<Filter>();
        }
        return this.filters;
    }

    /**
     * Gets the value of the conditions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Condition }
     * 
     * 
     */
    public List<Condition> getConditions() {
        if (conditions == null) {
            conditions = new ArrayList<Condition>();
        }
        return this.conditions;
    }

    /**
     * Gets the value of the unaryOperator property.
     * 
     * @return
     *     possible object is
     *     {@link UnaryOperatorEnumRestriction }
     *     
     */
    public UnaryOperatorEnumRestriction getUnaryOperator() {
        return unaryOperator;
    }

    /**
     * Sets the value of the unaryOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnaryOperatorEnumRestriction }
     *     
     */
    public void setUnaryOperator(UnaryOperatorEnumRestriction value) {
        this.unaryOperator = value;
    }

    /**
     * Gets the value of the logicalOperator property.
     * 
     * @return
     *     possible object is
     *     {@link LogicalOperatorEnumRestriction }
     *     
     */
    public LogicalOperatorEnumRestriction getLogicalOperator() {
        if (logicalOperator == null) {
            return LogicalOperatorEnumRestriction.AND;
        } else {
            return logicalOperator;
        }
    }

    /**
     * Sets the value of the logicalOperator property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogicalOperatorEnumRestriction }
     *     
     */
    public void setLogicalOperator(LogicalOperatorEnumRestriction value) {
        this.logicalOperator = value;
    }

}
