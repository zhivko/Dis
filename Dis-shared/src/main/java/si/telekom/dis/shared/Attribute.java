package si.telekom.dis.shared;

/**
 * @author klemen
 *
 */
public class Attribute implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable, HasIdName  {

	/**
	 * @author klemen
	 *
	 * possible types of user interface control
	 */
	public enum types {
	//@formatter:off
			TEXTBOX("textbox"),
			TEXTAREA("textarea"),
			CHECKBOX("checkBox"),
			DROPDOWN("dropdown"),
			DATETIME("datetime"),
			DATE("date"),
			CALENDAR("calendar"),
			TIME("time");
		//@formatter:on

			public String type;
			

			public String toString() {
				return this.type;
			}

			private types(String action) {
				this.type = action;
			}
		}
	
	
	
	public Attribute(String dcmtAttName) {
		this.dcmtAttName = dcmtAttName;
	}

	public Attribute() {
	}	
	
	public String dcmtAttName;
	public String dcmtType;

	public String label;
	
	/**
	 * type represents type for user interface it could be: textbox, textarea, checkBox, dropdown, datetime, date, time  
	 */
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String tabId;
	public int row;
	public int col;

	public String defaultValue;
	public boolean defaultValueIsConstant;
	public boolean defaultValueIsDql;
	public boolean defaultValueIsSql;
	public boolean defaultValueIsCalculatedOnServer;

	/**
	 * attribute can depend on another attribute in that case definnition would be [att_name, column_no from suggestbox attribute]
	 */
	public String dependsOn;
	
	public boolean isRepeating;
	public boolean isMandatory;
	public boolean isLimitedToValueList;
	public boolean isReadOnly;

	public String jdbcValueListDefinition;
	public String dqlValueListDefinition;
	public String commaSeparatedValueListDefinition;
	
	public int dropDownCol=0;
	/**
	 * restQuery in form of ...
	 */
	public String restQuery;

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.dcmtAttName;
	}

	@Override
	public String getParameter() {
		// TODO Auto-generated method stub
		return this.label;
	}

	@Override
	public void setId(String id_) {
		// TODO Auto-generated method stub
		this.dcmtAttName = id_;
	}

	@Override
	public void setName(String name_) {
		this.label = name_;		
	}
	
}
