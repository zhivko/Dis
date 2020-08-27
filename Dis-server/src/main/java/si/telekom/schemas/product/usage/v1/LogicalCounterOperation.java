
package si.telekom.schemas.product.usage.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LogicalCounterOperation.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LogicalCounterOperation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="READ"/>
 *     &lt;enumeration value="SET"/>
 *     &lt;enumeration value="RESET"/>
 *     &lt;enumeration value="ADD"/>
 *     &lt;enumeration value="SUBTRACT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LogicalCounterOperation")
@XmlEnum
public enum LogicalCounterOperation {

    READ,
    SET,
    RESET,
    ADD,
    SUBTRACT;

    public String value() {
        return name();
    }

    public static LogicalCounterOperation fromValue(String v) {
        return valueOf(v);
    }

}
