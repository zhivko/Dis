package si.telekom.dis.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class RoleValueList {
	@QueryParam("roleValueList")
	public List<RoleValue> roleValueList;

	public RoleValueList() {
		roleValueList = new ArrayList<RoleValue>();
	}

	
	/*
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.equals(RoleValueList.class)) {
			return (ParamConverter<T>) new MyParamConverter();
		}
		return null;
	}


		@Override
		public RoleValueList fromString(String s) {
			RoleValueList roleValueList = new RoleValueList();

			String attNameWithValues[] = s.split("#");
			for (String attNameWithValue : attNameWithValues) {
				String keyValues[] = attNameWithValue.split("$");
				String roleName = keyValues[0];
				String[] values = keyValues[1].split("¤");
				List<String> usersGroupsList = Arrays.asList(values);
				roleValueList.roleValueList.add(new RoleValue(roleName, usersGroupsList));
			}
			return roleValueList;
		}

		@Override
		public String toString(RoleValueList obj) {
			String ret = "";
			for (RoleValue attValue : obj.roleValueList) {
				ret = ret + attValue.roleName + "$";

				for (String value : attValue.values) {
					ret = ret + value + "¤";
				}

				if (attValue.values.size() > 0)
					ret = ret.substring(0, ret.length() - 1);
			}
			return ret;
		}
	}
	*/

}
