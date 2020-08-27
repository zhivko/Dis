
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatorEnumRestriction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OperatorEnumRestriction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EQUAL"/>
 *     &lt;enumeration value="NOTEQ"/>
 *     &lt;enumeration value="LESS"/>
 *     &lt;enumeration value="MORE"/>
 *     &lt;enumeration value="MOREEQ"/>
 *     &lt;enumeration value="LESSEQ"/>
 *     &lt;enumeration value="CONTAINS"/>
 *     &lt;enumeration value="REGEX"/>
 *     &lt;enumeration value="LIKE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperatorEnumRestriction")
@XmlEnum
public enum OperatorEnumRestriction {

    EQUAL,
    NOTEQ,
    LESS,
    MORE,
    MOREEQ,
    LESSEQ,
    CONTAINS,
    REGEX,
    LIKE;

    public String value() {
        return name();
    }

    public static OperatorEnumRestriction fromValue(String v) {
        return valueOf(v);
    }

}
