package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.List;

public class State implements HasIdName, com.google.gwt.user.client.rpc.IsSerializable,  java.io.Serializable {
	
	private String id;
	private String name;
	public List<StandardAction> standardActions;
	
	
	public State() {

	}

	public State(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		standardActions = new ArrayList<StandardAction>();
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
	public String getType() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
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

}
