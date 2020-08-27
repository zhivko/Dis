package si.telekom.dis.shared;

public class Template implements com.google.gwt.user.client.rpc.IsSerializable,  java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String object_name;
	
	public Template() {
	}
	
	public Template(String object_name_) {
		this.object_name = object_name_;
	}

}
