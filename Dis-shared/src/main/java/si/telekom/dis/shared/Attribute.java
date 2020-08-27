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
	public String type;
	
	public String tabId;
	public int row;
	public int col;

	public String defaultValue;
	public boolean defaultValueIsConstant;
	public boolean defaultValueIsDql;
	public boolean defaultValueIsSql;
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
	
	public String getCommaSeparatedValueListDefinition() {
		return commaSeparatedValueListDefinition;
	}

	public void setCommaSeparatedValueListDefinition(String commaSeparatedValueListDefinition) {
		this.commaSeparatedValueListDefinition = commaSeparatedValueListDefinition;
	}
	
	

	public String getDcmtAttName() {
		return dcmtAttName;
	}
	public void setDcmtAttName(String dcmtAttName) {
		this.dcmtAttName = dcmtAttName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTabId() {
		return tabId;
	}
	public void setTabId(String tabId) {
		this.tabId = tabId;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public boolean isRepeating() {
		return isRepeating;
	}
	public void setRepeating(boolean isRepeating) {
		this.isRepeating = isRepeating;
	}
	public boolean isMandatory() {
		return isMandatory;
	}
	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	public boolean isLimitedToValueList() {
		return isLimitedToValueList;
	}
	public void setLimitedToValueList(boolean isLimitedToValueList) {
		this.isLimitedToValueList = isLimitedToValueList;
	}
	public String getOdbcValueListDefinition() {
		return jdbcValueListDefinition;
	}
	public void setOdbcValueListDefinition(String odbcValueListDefinition) {
		this.jdbcValueListDefinition = odbcValueListDefinition;
	}
	public String getDqlValueListDefinition() {
		return dqlValueListDefinition;
	}
	public void setDqlValueListDefinition(String dqlValueListDefinition) {
		this.dqlValueListDefinition = dqlValueListDefinition;
	}
	public String getDcmtType() {
		return dcmtType;
	}
	public void setDcmtType(String dcmtType) {
		this.dcmtType = dcmtType;
	}

	public void setIsReadOnly(Boolean value) {
		// TODO Auto-generated method stub
		this.isReadOnly = value;
	}

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
