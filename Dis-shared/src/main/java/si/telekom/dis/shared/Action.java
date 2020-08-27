package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.List;

import si.telekom.dis.shared.ExtendedPermit.extPermit;

public class Action implements HasIdName, com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	public String id;
	public String name;
	public Permit.permit permit;
	public List<ExtendedPermit.extPermit> extPermits;

	public Action() {
	}

	public Action(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Action(String id, String name, Permit.permit p) {
		super();
		this.id = id;
		this.name = name;
		this.permit = p;
		this.extPermits = new ArrayList<ExtendedPermit.extPermit>();
	}

	public Action(String id, String name, Permit.permit p, List<ExtendedPermit.extPermit> extPermits) {
		this(id, name, p);
		this.extPermits = extPermits;
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

	@Override
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
		return "action";
	}

	@Override
	public String toString() {
		return "id=" + this.id + ";" + "name=" + this.name;
	}

	@Override
	public boolean equals(Object obj) {
		Action act1 = (Action) obj;
		return this.id.equals(act1.id);
	}

}
