package si.telekom.dis.shared;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Profile implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	public Profile() {
	}

	public String id;
	public String name;
	public String objType;
	public boolean isDefaultForObjectType;
	
	public String namePolicyPrefix;
	public String namePolicySuffix;
	public String namePolicyCounterStart;
	public String namePolicyCounterPlaces;
	public int namePolicyBarcodeType;
	
	public ArrayList<Template> templates;
	public ArrayList<TemplateFolder> templateFolderPaths;
	
	public ArrayList<Role> roles;
	public ArrayList<State> states;
	public ArrayList<Tab> tabs;
	
	public ArrayList<Attribute> detailAttributes;
	
	public List<AttributeRoleStateWizards> attributeRolesStatesWizards;
	public Map<String, Map<String, List<String>>> roleStateActions;
	
	public Date modifyDateUTC;
	
	@Override
	public String toString()
	{
		return this.id;
	}
	
}
