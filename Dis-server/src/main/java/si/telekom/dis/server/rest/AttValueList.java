package si.telekom.dis.server.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.ext.ParamConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class AttValueList {
	@QueryParam("attValueList")
	public List<AttValue> attValueList;

	public AttValueList() {
		this.attValueList = new ArrayList<AttValue>();
	}

	/*
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.equals(AttValueList.class)) {
			return (ParamConverter<T>) new MyParamConverter();
		}
		return null;
	}

	

	  @Override
	  public AttValueList fromString(String s){
	  	AttValueList attValueList = new AttValueList();
	  	
	  	String attNameWithValues[] = s.split("#");
	  	for (String attNameWithValue : attNameWithValues) {
				String keyValues[] = attNameWithValue.split("$");
				String attName = keyValues[0];
				String[] values =  keyValues[1].split("¤");
				List<String> valuesList = Arrays.asList(values);
		  	attValueList.attValueList.add(new AttValue(attName, valuesList));
			}
	  	return attValueList;
	  }

	  @Override
	  public String toString(AttValueList obj){
	    String ret="";
	    for (AttValue attValue : obj.attValueList) {
				ret = ret + attValue.attName + "$";
				
				for (String value : attValue.values) {
					ret = ret + value + "¤";
				}
				
				if(attValue.values.size()>0)
					ret = ret.substring(0,ret.length()-1);
			}
	    return ret;
	  }
	}	
	
	*/
}
