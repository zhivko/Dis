package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.List;

public class MyParametrizedQuery implements java.io.Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	public String name;
	public String dql;
	public List<Attribute> formAttributes;
	public List<String> labels;
	public List<String> arguments;
	public List<UserGroup> usersGroups;
	public List<String> orderBys;
	public List<String> orderByDirections;
	
	public String filterClass;

	public MyParametrizedQuery() {
		formAttributes = new ArrayList<Attribute>();
		labels = new ArrayList<String>();
		arguments = new ArrayList<String>();
		usersGroups = new ArrayList<UserGroup>();
		orderBys = new ArrayList<String>();
		orderByDirections = new ArrayList<String>();
		filterClass = null;
	}

	public MyParametrizedQuery(String name, String dql) {
		this();
		this.name = name;
		this.dql = dql;
	}

}
