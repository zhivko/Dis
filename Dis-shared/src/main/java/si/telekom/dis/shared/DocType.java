package si.telekom.dis.shared;

import java.util.HashMap;
import java.util.Map;

public class DocType implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable, Comparable<DocType> {
	public String id;
	public String name;
	public String superName;
	public Map<String, DcmtAttribute> attributes;
	public Map<String, Profile> profiles;

	public DocType() {
	}

	public DocType(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.superName = "";
		this.attributes = new HashMap<String, DcmtAttribute>();
		this.profiles = new HashMap<String, Profile>();
	}

	public void addAttribute(DcmtAttribute a) {
		this.attributes.put(a.attr_name, a);
	}

	@Override
	public int compareTo(DocType o) {
		// TODO Auto-generated method stub
		return (this.id.compareTo(o.id));
	}
	
	@Override
	public String toString()
	{
		return this.id;
	}
	

}
