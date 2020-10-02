package si.telekom.dis.server.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ext.ParamConverter;

public class AttValueList implements javax.ws.rs.ext.ParamConverterProvider {
	public List<AttValue> attValueList;

	public AttValueList() {
		this.attValueList = new ArrayList<AttValue>();
	}

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		if (rawType.equals(AttValueList.class)) {
			return (ParamConverter<T>) new MyParamConverter();
		}
		return null;
	}

	
	private class MyParamConverter implements ParamConverter<AttValueList>{
	  /**
	   * @param AttValueList string should be separated with $ and # and ¤ form attName1$attvalue1¤attvalue2¤attvalue3¤...#attName1$attvalue1¤attvalue2¤attvalue3¤...
	   * @return
	   */
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
}
