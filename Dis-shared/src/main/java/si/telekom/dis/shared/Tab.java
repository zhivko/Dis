package si.telekom.dis.shared;

public class Tab implements HasIdName, com.google.gwt.user.client.rpc.IsSerializable,  java.io.Serializable {
	public Integer row;
	public Integer col;
	
	public Tab() {

	}

	public Tab(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private String id;
	private String name;

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
