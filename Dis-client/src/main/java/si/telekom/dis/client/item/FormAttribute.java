package si.telekom.dis.client.item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import si.telekom.dis.client.MultiValueSelectBox;
import si.telekom.dis.client.MyDateTime;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class FormAttribute extends Composite implements HasValueChangeHandlers<List<String>>, HasKeyUpHandlers {
	public Attribute att;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	TextBox tb = null;
	MultiValueSelectBox sb = null;
	TextArea ta = null;
	CheckBox cb = null;
	MyDateTime dp = null;
	DateBox dateBox = null;
	// DateTimePicker dateTimePicker = null;
	MyDateTime myDateTime = null;
	Label lb;

	public ListBox values;

	VerticalPanel vp;
	// private String defaultWidth = "80%";

	public FormAttribute(Attribute a_) {
		this.att = a_;

		vp = new VerticalPanel();

		String attType = att.getType();
		String textboxType = Attribute.types.TEXTBOX.type;

		values = new ListBox();
		values.setVisibleItemCount(5);
		values.setWidth("100%");
		if (att.isReadOnly) {
			values.setEnabled(false);
		}

		values.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DELETE)
					values.removeItem(values.getSelectedIndex());
				FormAttribute.this.valueDeleted(values.getSelectedIndex());
			}
		});
		
		GWT.log("name: " + att.dcmtAttName + " " + att.type);

		if (att.getType().equals(Attribute.types.TEXTBOX.type)) {
			tb = new TextBox();
			tb.setWidth("100%");

			if (this.att.isReadOnly)
				tb.setEnabled(false);

			tb.addKeyUpHandler(new KeyUpHandler() {
				@Override
				public void onKeyUp(KeyUpEvent event) {
					if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
						values.addItem(tb.getText());
						if (att.isRepeating())
							tb.setText("");
					}
				}
			});

			tb.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					logger.info("Firing tb date change event");
					ArrayList<String> al = new ArrayList<String>();
					String val = tb.getValue();
					al.add(tb.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});
			vp.add(tb);
			vp.setCellVerticalAlignment(tb, HasVerticalAlignment.ALIGN_BOTTOM);
			vp.setCellHeight(tb, "100%");
		} else if (att.getType().equals(Attribute.types.TEXTAREA.type)) {
			ta = new TextArea();
			ta.setWidth("100%");
			ta.setHeight("5em");

			// ta.setWidth(defaultWidth);
			if (this.att.isReadOnly)
				ta.setEnabled(false);

			ta.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					logger.info("Firing ta date change event");
					ArrayList<String> al = new ArrayList<String>();
					al.add(ta.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});

			vp.add(ta);
			vp.setCellVerticalAlignment(ta, HasVerticalAlignment.ALIGN_BOTTOM);
			vp.setCellHeight(ta, "100%");
		} else if (att.getType().equals(Attribute.types.CHECKBOX.type)) {
			cb = new CheckBox();
			// cb.setWidth(defaultWidth);
			// cb.setText(att.getLabel());
			if (this.att.isReadOnly)
				cb.setEnabled(false);

			cb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
				@Override
				public void onValueChange(ValueChangeEvent<Boolean> event) {
					logger.info("Firing cb date change event");
					ArrayList<String> al = new ArrayList<String>();
					al.add(cb.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});

			vp.add(cb);
			vp.setCellVerticalAlignment(cb, HasVerticalAlignment.ALIGN_BOTTOM);
			vp.setCellHeight(cb, "100%");
		} else if (att.getType().equals(Attribute.types.DROPDOWN.type)) {
			// HorizontalPanel hp = new HorizontalPanel();
			// tb = new TextBox();
			// ArrayList<String> data = new ArrayList<String>();

			sb = new MultiValueSelectBox(this.att, values);
			// sb.setWidth(defaultWidth);
			sb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
				@Override
				public void onValueChange(ValueChangeEvent<List<String>> event) {
					// cycle through dependent attributes
					ArrayList<String> al = new ArrayList<String>();
					al.add(sb.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});

			if (this.att.isReadOnly) {
				sb.setEnabled(false);
			}
			
			vp.add(sb);
			vp.setCellWidth(sb, "100%");
			vp.setCellVerticalAlignment(sb, HasVerticalAlignment.ALIGN_BOTTOM);
		} else if (att.getType().equals(Attribute.types.CALENDAR.type)) {
			dp = new MyDateTime();
			dp.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					logger.info("Firing dp date change event");
					ArrayList<String> al = new ArrayList<String>();
					al.add(dp.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});
			vp.add(dp);
			vp.setCellHeight(dp, "100%");
			vp.setCellWidth(dp, "100%");
			vp.setCellVerticalAlignment(dp, HasVerticalAlignment.ALIGN_MIDDLE);
		} else if (att.getType().equals(Attribute.types.DATETIME.type)) {
			myDateTime = new MyDateTime();
			myDateTime.setEnabled(!att.isReadOnly);
			myDateTime.addValueChangeHandler(new ValueChangeHandler<String>() {
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					logger.info("Firing DateTimeBox date change event");
					ArrayList<String> al = new ArrayList<String>();
					al.add(myDateTime.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});
			vp.add(myDateTime);
		} else if (att.getType().equals(Attribute.types.DATE.type)) {
			dateBox = new DateBox();
			DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
			dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
			// dateBox.setWidth(defaultWidth);
			dateBox.setEnabled(!att.isReadOnly);
			dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					logger.info("Firing datebox date change event");
					ArrayList<String> al = new ArrayList<String>();
					al.add(dateBox.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});
			vp.add(dateBox);
			vp.setCellVerticalAlignment(dateBox, HasVerticalAlignment.ALIGN_BOTTOM);
			vp.setCellHeight(dateBox, "100%");
		} else if (att.getType().equals(Attribute.types.TIME.type)) {
			dateBox = new DateBox();
			DateTimeFormat dateFormat = DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT);
			dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
			// dateBox.setWidth(defaultWidth);
			dateBox.setEnabled(!att.isReadOnly);
			dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
				@Override
				public void onValueChange(ValueChangeEvent<Date> event) {
					logger.info("Firing datebox date change event");
					ArrayList<String> al = new ArrayList<String>();
					al.add(dateBox.getValue().toString());
					ValueChangeEvent.fire(FormAttribute.this, al);
				}
			});
			vp.add(dateBox);
			vp.setCellVerticalAlignment(dateBox, HasVerticalAlignment.ALIGN_BOTTOM);
			vp.setCellHeight(dateBox, "100%");
		}

		if (att.isRepeating()) {
			values.addDoubleClickHandler(new DoubleClickHandler() {
				@Override
				public void onDoubleClick(DoubleClickEvent event) {
					tb.setValue(values.getSelectedItemText());
				}
			});
			
			vp.add(values);
		}

		SimplePanel space = new SimplePanel();
		space.setWidth("10px");
		space.setHeight("10px");

		lb = new Label();
		lb.setText(att.getLabel());
		if (att.isMandatory)
			lb.setStyleName("font-weight:bold;");
		vp.add(lb);
		vp.setCellVerticalAlignment(lb, HasVerticalAlignment.ALIGN_TOP);

		vp.add(space);

		initWidget(vp);
	}

	public void valueDeleted(int selectedIndex) {
		// TODO Auto-generated method stub
	}

	public String getValue() {
		// TODO Auto-generated method stub
		if (att.getType().equals(Attribute.types.TEXTBOX.type))
			return (tb.getValue() == null ? "" : tb.getValue());
		else if (att.getType().equals(Attribute.types.TEXTAREA.type))
			return (ta.getValue() == null ? "" : ta.getValue());
		else if (att.getType().equals(Attribute.types.CHECKBOX.type))
			return cb.getValue().toString();
		else if (att.getType().equals(Attribute.types.DROPDOWN.type))
			if (sb.getValue().size() > 0)
				return sb.getValue().get(0);
			else
				return "";
		else if (att.getType().equals(Attribute.types.CALENDAR.type))
			// milliseconds since January 1, 1970, 00:00:00 GMT
			return (dp.getValue() == null ? "" : String.valueOf(dp.getValue()));
		else if (att.getType().equals(Attribute.types.DATETIME.type))
			// milliseconds since January 1, 1970, 00:00:00 GMT
			return (myDateTime.getValueDate() == null ? "" : String.valueOf(myDateTime.getValueDate().getTime()));
		else if (att.getType().equals(Attribute.types.DATE.type))
			// milliseconds since January 1, 1970, 00:00:00 GMT
			return (dateBox.getValue() == null ? "" : String.valueOf(dateBox.getValue().getTime()));
		else if (att.getType().equals(Attribute.types.TIME.type))
			// milliseconds since January 1, 1970, 00:00:00 GMT
			return (dateBox.getValue() == null ? "" : String.valueOf(dateBox.getValue().getTime()));

		return "";
	}

	public List<String> getValues() {
		// TODO Auto-generated method stub

		ArrayList<String> values = new ArrayList<String>();
		if (att.isRepeating())
			for (int i = 0; i < this.values.getItemCount(); i++) {
				String val = this.values.getValue(i);
				values.add(val);
			}
		else
			values.add(getValue());

		return values;
	}

	public List<String> getValues(boolean onlyValues) {
		List<String> values = this.getValues();
		if (onlyValues) {
			ArrayList<String> al = new ArrayList<String>();
			for (String val : values) {
				al.add((split(val, "|")[0]));
			}
			values = al;
		}
		return values;
	}

	public void setValue(List<String> values) {
		// TODO Auto-generated method stub
		if (!att.isRepeating()) {
			if (values.size() > 0) {
				if (values.get(0) != null) {
					if (att.getType().equals(Attribute.types.TEXTBOX.type)) {
						tb.setValue(values.get(0));
					} else if (att.getType().equals(Attribute.types.TEXTAREA.type)) {
						ta.setValue(values.get(0));
					} else if (att.getType().equals(Attribute.types.CHECKBOX.type)) {
						cb.setValue(Boolean.valueOf(values.get(0)));
					} else if (att.getType().equals(Attribute.types.DROPDOWN.type)) {
						sb.setValue(values);
					} else if (att.getType().equals(Attribute.types.CALENDAR.type)) {
						// milliseconds since January 1, 1970, 00:00:00 GMT
						Date date = new Date(Long.valueOf(values.get(0)));
						dp.setValue(date);
					} else if (att.getType().equals(Attribute.types.DATETIME.type)) {
						// milliseconds since January 1, 1970, 00:00:00 GMT
						myDateTime.setValue(new Date(Long.valueOf(values.get(0))));
					} else if (att.getType().equals(Attribute.types.DATE.type)) {
						// milliseconds since January 1, 1970, 00:00:00 GMT
						dateBox.setValue(new Date(Long.valueOf(values.get(0))));
					} else if (att.getType().equals(Attribute.types.TIME.type)) {
						// milliseconds since January 1, 1970, 00:00:00 GMT
						dateBox.setValue(new Date(Long.valueOf(values.get(0))));
					}
				}
			}
		} else {
			if (values != null && values.size() > 0) {
				if (att.getType().equals(Attribute.types.DROPDOWN.type)) {
					sb.setValue(values);
				} else {
					for (String value : values) {
						this.values.addItem(value);
					}
				}
			}
		}

	}

	public Attribute getAtribute() {
		return att;
	}

	public void mandatoryNeeded() {
		lb.getElement().getStyle().setColor("red");
	}

	public void mandatoryOk() {
		lb.getElement().getStyle().setColor("");
	}

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<List<String>> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}

	public String getFirstMultiselectRow() {
		if (sb != null) {
			return sb.getValue().get(0);
		}
		return null;
	}

	public String getDateTime() {
		String ret = DateTimeFormat.getFormat("dd.MM.yyyy HH:mm:ss").format(myDateTime.getValueDate());
		return ret;
	}

	@Override
	public void setWidth(String width) {
		super.setWidth(width);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		HandlerRegistration hr = null;
		if (tb != null)
			hr = tb.addKeyUpHandler(handler);
		if (ta != null)
			hr = ta.addKeyUpHandler(handler);
		if (myDateTime != null) {
			hr = myDateTime.addKeyUpHandler(handler);
		}

		return hr;
	}

	public void setFocus() {
		if (tb != null)
			tb.setFocus(true);
		if (ta != null)
			ta.setFocus(true);
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

	public static final native String ltrim(String s) /*-{
		return s.trim();
	}-*/;

}
