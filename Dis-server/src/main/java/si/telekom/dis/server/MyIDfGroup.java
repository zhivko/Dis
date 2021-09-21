package si.telekom.dis.server;

import java.util.ArrayList;

import com.documentum.fc.client.IDfGroup;
import com.documentum.fc.common.DfException;

public class MyIDfGroup implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<String> users = new ArrayList<String>();
	ArrayList<String> groups = new ArrayList<String>();
	
	
	public static MyIDfGroup convertFromIDFGroup(IDfGroup group) throws DfException
	{
		MyIDfGroup gr = new MyIDfGroup();
		for (int i=0; i<group.getAllUsersNamesCount(); i++) {
			String userName = group.getAllUsersNames(i);
			MyIDfUser user = AdminServiceImpl.allUsers.get(userName);
			gr.users.add(userName);
		}
		for (int i=0; i<group.getGroupsNamesCount(); i++) {
			gr.groups.add(group.getGroupsNames(i));
		}
		return gr;
	}
	
	
	public int getUsersNamesCount()
	{
		return users.size();
	}
	
	public String getUsersNames(int which)
	{
		return users.get(which);
	}
	
}
