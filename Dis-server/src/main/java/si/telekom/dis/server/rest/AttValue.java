package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class AttValue {
		public String attName;
		public List<String> values;
		
		public AttValue()
		{
			
		}
		
		public AttValue(String name)
		{
			this.attName = name;
			this.values = new ArrayList<String>();	
		}

		public AttValue(String name, List<String> values)
		{
			this.attName = name;
			this.values = values;	
		}
		
}
