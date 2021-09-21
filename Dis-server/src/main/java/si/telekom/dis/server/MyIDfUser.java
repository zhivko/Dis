package si.telekom.dis.server;

import com.documentum.fc.client.IDfUser;
import com.documentum.fc.common.DfException;

public class MyIDfUser implements java.io.Serializable {
	String userName;
	String description;
	String loginName;
	
	
	public static MyIDfUser convertFromIDFUser(IDfUser user) throws DfException
	{
		MyIDfUser us = new MyIDfUser();
		us.description = user.getDescription();
		us.userName = user.getUserName();
		us.loginName = user.getUserLoginName();
		return us;
	}

}
