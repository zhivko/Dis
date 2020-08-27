package si.telekom.dis.shared;

import java.util.List;
import java.util.Map;

public class ProfileAttributesAndValues  implements com.google.gwt.user.client.rpc.IsSerializable,  java.io.Serializable{
	public Profile profile;
	public List<Attribute> attributes;
	public Map<String, List<String>> values;
	public Map<String, List<String>> rolesAndUsers;
}
