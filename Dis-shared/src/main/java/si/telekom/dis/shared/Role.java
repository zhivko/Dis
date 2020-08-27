package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.List;

public class Role implements HasIdName, com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	public String id;
	public String name;
	public List<UserGroup> defaultUserGroups;

	public Role() {
		super();
		defaultUserGroups = new ArrayList<UserGroup>();
	}

	public Role(String id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getParameter() {
		// TODO Auto-generated method stub
		return name;
	}

	public void setId(String id_) {
		// TODO Auto-generated method stub
		this.id = id_;
	}

	@Override
	public void setName(String name_) {
		// TODO Auto-generated method stub
		this.name = name_;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}

}
