package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
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
