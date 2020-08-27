package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.view.client.ProvidesKey;

public class Document implements com.google.gwt.user.client.rpc.IsSerializable,  java.io.Serializable, Comparable<Document>
{
	public String r_object_id;
	public String i_chronicle_id;
	public String object_name;
	public String title;
	public String subject;
	public Date r_creation_date;
	public Date r_modify_date;
	public String creator;
	public String modifier;
	public String owner;
	public String lockOwner;
	public String lockMachine;
	public String r_version_label;
	
	public boolean isHighlighted;

	public String state_id;
	
	public List<Role> roles;
	public List<String> roleMembers;   //documentum users or groups matching 1-1 to roles
	public String type_name;
	public String format;

	public List<String> details;
	public boolean isClassified = false;
	public boolean isFolder=false;
	
  /**
   * The key provider that provides the unique ID of a document.
   */
  public static final ProvidesKey<Document> KEY_PROVIDER = new ProvidesKey<Document>() {
    @Override
    public Object getKey(Document item) {
      return item == null ? null : item.r_object_id;
    }
  };	
	
	public Document() {
		
	}
	
	
	public Document(String r_object_id, String object_name, String type_) {
		super();
		this.r_object_id = r_object_id;
		this.object_name = object_name;
		this.type_name = type_;
		roles = new ArrayList<Role>();
		roleMembers = new ArrayList<String>();
	}


	public String getDisplayName() {
		// TODO Auto-generated method stub
		return this.object_name;
	}


	@Override
	public int compareTo(Document o) {
		// TODO Auto-generated method stub
		return this.object_name.compareTo(o.object_name);
	}


}
