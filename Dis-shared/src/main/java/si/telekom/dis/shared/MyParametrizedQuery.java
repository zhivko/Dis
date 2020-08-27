package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.List;

public class MyParametrizedQuery implements java.io.Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	/**
	 * 
	 */
	public String name;
	public String dql;
	public List<Attribute> formAttributes;
	public List<String> dqlParts;
	public List<String> labels;
	public List<String> arguments;
	public List<String> groups;
	public List<String> orderBys;
	public List<String> orderByDirections;
	

	public MyParametrizedQuery() {
		formAttributes = new ArrayList<Attribute>();
		dqlParts = new ArrayList<String>();
		labels = new ArrayList<String>();
		arguments = new ArrayList<String>();
		groups = new ArrayList<String>();
		orderBys = new ArrayList<String>();
		orderByDirections = new ArrayList<String>();
	}

	public MyParametrizedQuery(String name, String dql) {
		this();
		this.name = name;
		this.dql = dql;
	}

}
