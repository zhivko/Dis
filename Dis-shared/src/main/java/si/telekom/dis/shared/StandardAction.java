package si.telekom.dis.shared;

public class StandardAction implements HasIdName, com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public StandardAction() {
	}
	
	public enum types implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	//@formatter:off
			LINK_TO_FOLDER("link_to_folder"),
			UNLINK_FROM_FOLDER("unlink_from_folder"),
			MOVE_ALL_FOLDER_LINKS("move_all_folder_links"),
			START_WORKFLOW("start_workflow"),
			REPLACE_VERSION_LABEL("replace_version_label"),
			ADD_VERSION_LABEL("add_version_label"),
			SET_ATTRIBUTE("set_attribute"),
			CLEAR_ATTRIBUTES("clear_attributes"),
			APPLY_CAS_RETENTION("apply_cas_retention");
		//@formatter:on

			public String type;
			
			public String toString() {
				return this.type;
			}

			private types(String type_) {
				this.type = type_;
			}
		}
	
	public String kind;
	public String parameter;
	


	public StandardAction(String kind_) {
		super();
		this.kind = kind_;
	}

	public StandardAction(String kind_, String parameter_) {
		super();
		this.kind = kind_;
		this.parameter = parameter_;
	}
	
	
	public String getKind() {
		// TODO Auto-generated method stub
		return kind;
	}

	@Override
	public String getParameter() {
		// TODO Auto-generated method stub
		return parameter;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "standardAction";
	}
	
	@Override
	public String toString()
	{
		return "kind=" + this.kind + ";" + "parameter=" + this.parameter;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "unknown";
	}

	@Override
	public void setId(String id_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String name_) {
		// TODO Auto-generated method stub
		
	}

}
