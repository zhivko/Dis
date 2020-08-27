
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnaryOperatorEnumRestriction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnaryOperatorEnumRestriction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NOT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnaryOperatorEnumRestriction")
@XmlEnum
public enum UnaryOperatorEnumRestriction {

    NOT;

    public String value() {
        return name();
    }

    public static UnaryOperatorEnumRestriction fromValue(String v) {
        return valueOf(v);
    }

}
