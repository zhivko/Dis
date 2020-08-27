package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// class that holds combination of SAME attributes configuration
// and specifies wizards that use it
// and specify roles/states that use it 
public class AttributeRoleStateWizards implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {

	public List<String> wizards;
	/**
	 * String id of State, List of role ids
	 */
	public Map<String, List<String>> stateRole;
	public List<Attribute> attributes;

	public AttributeRoleStateWizards() {
		attributes = new ArrayList<Attribute>();
		wizards = new ArrayList<String>();
		stateRole = new HashMap<String, List<String>>();
	}

}
