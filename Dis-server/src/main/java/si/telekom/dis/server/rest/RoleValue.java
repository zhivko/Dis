package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ext.ParamConverter;

public class RoleValue {
		public String roleName;
		public List<String> values;
		
		public RoleValue()
		{
			
		}
		
		public RoleValue(String name)
		{
			this.roleName = name;
			this.values = new ArrayList<String>();	
		}

		public RoleValue(String name, List<String> values)
		{
			this.roleName = name;
			this.values = values;	
		}
		
}
