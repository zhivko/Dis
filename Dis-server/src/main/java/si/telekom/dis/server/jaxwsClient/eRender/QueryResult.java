
package si.telekom.dis.server.jaxwsClient.eRender;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for queryResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="queryResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://templates.mobitel.com/}myHashMapArray" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="maxPages" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="resultsPerPage" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryResult", propOrder = {
    "results"
})
public class QueryResult {

    @XmlElement(name = "result", namespace = "")
    protected List<MyHashMapArray> results;
    @XmlAttribute(name = "maxPages", required = true)
    protected int maxPages;
    @XmlAttribute(name = "resultsPerPage", required = true)
    protected int resultsPerPage;

    /**
     * Gets the value of the results property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the results property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MyHashMapArray }
     * 
     * 
     */
    public List<MyHashMapArray> getResults() {
        if (results == null) {
            results = new ArrayList<MyHashMapArray>();
        }
        return this.results;
    }

    /**
     * Gets the value of the maxPages property.
     * 
     */
    public int getMaxPages() {
        return maxPages;
    }

    /**
     * Sets the value of the maxPages property.
     * 
     */
    public void setMaxPages(int value) {
        this.maxPages = value;
    }

    /**
     * Gets the value of the resultsPerPage property.
     * 
     */
    public int getResultsPerPage() {
        return resultsPerPage;
    }

    /**
     * Sets the value of the resultsPerPage property.
     * 
     */
    public void setResultsPerPage(int value) {
        this.resultsPerPage = value;
    }

}
