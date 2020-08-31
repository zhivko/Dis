
package si.telekom.services.common.base.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
    "filter",
    "condition"
})
public class Filter {

    protected List<Filter> filter;
    protected List<Condition> condition;
    @XmlAttribute(name = "unaryOperator")
    protected UnaryOperatorEnumRestriction unaryOperator;
    @XmlAttribute(name = "logicalOperator")
    protected LogicalOperatorEnumRestriction logicalOperator;

    /**
     * Gets the value of the filter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Filter }
     * 
     * 
     */
    public List<Filter> getFilter() {
        if (filter == null) {
            filter = new ArrayList<Filter>();
        }
        return this.filter;
    }

    /**
     * Gets the value of the condition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Condition }
     * 
     * 
     */
    public List<Condition> getCondition() {
        if (condition == null) {
            condition = new ArrayList<Condition>();
        }
        return this.condition;
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
