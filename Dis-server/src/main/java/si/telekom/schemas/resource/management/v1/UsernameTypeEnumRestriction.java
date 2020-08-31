
package si.telekom.schemas.resource.management.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UsernameTypeEnumRestriction.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UsernameTypeEnumRestriction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Basic user account"/>
 *     &lt;enumeration value="BB Account"/>
 *     &lt;enumeration value="Aditional phone number"/>
 *     &lt;enumeration value="Virtual mail server"/>
 *     &lt;enumeration value="VPN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UsernameTypeEnumRestriction")
@XmlEnum
public enum UsernameTypeEnumRestriction {

    @XmlEnumValue("Basic user account")
    BASIC_USER_ACCOUNT("Basic user account"),
    @XmlEnumValue("BB Account")
    BB_ACCOUNT("BB Account"),
    @XmlEnumValue("Aditional phone number")
    ADITIONAL_PHONE_NUMBER("Aditional phone number"),
    @XmlEnumValue("Virtual mail server")
    VIRTUAL_MAIL_SERVER("Virtual mail server"),
    VPN("VPN");
    private final String value;

    UsernameTypeEnumRestriction(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UsernameTypeEnumRestriction fromValue(String v) {
        for (UsernameTypeEnumRestriction c: UsernameTypeEnumRestriction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
