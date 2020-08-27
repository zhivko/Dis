package si.telekom.dis.shared;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author klemen
 *
 */
public class DcmtAttribute implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable  {

	public String attr_name;
	
	
	/**
									0 ... boolean <br>
									1 ... integer <br>
									2 ... string  <br>
									3 ... id      <br>
									4 ... date    <br>
	 */
	public String domain_type;
	public boolean attr_repeating;
	public boolean read_only;
	public int domain_length;
	public String label_text;
	public String comment_text;
	
	public Kind kind; 
	
	public enum Kind implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable 
	{
		INTERNAL,
		SYSTEM,
		CUSTOM
	}
	
	public DcmtAttribute()
	{
	}
	
	public DcmtAttribute(String attr_name_, String domain_type_, boolean attr_repeating_, boolean read_only_, int domain_length_, String label_text_,
			String comment_text_) {
		this.attr_name = attr_name_;
		this.domain_type = domain_type_;
		this.attr_repeating = attr_repeating_;
		this.read_only = read_only_;
		this.domain_length = domain_length_;
		this.label_text = label_text_;
		this.comment_text = comment_text_;
		
		if(attr_name.startsWith("i_") || attr_name.startsWith("a_"))
			kind = Kind.INTERNAL;
		else if(attr_name.startsWith("r_"))
			kind = Kind.SYSTEM;
		else 
			kind = Kind.CUSTOM;
		
	}
}
