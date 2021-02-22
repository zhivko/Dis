package si.telekom.dis.shared;

import java.util.List;

public class AttributeValue implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable  {

	private String name;
	private List<String> values;
	
	public AttributeValue()
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	
	
}
