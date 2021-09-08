package si.telekom.dis.client;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyCheckBox extends Composite implements MyHasValue<String> {
	private Label caption = new Label();
	private CheckBox checkBox = new CheckBox();

	public MyCheckBox(String captionTxt, int widthInPixels) {
		this(captionTxt);
		this.checkBox.setWidth(String.valueOf(widthInPixels) + "px");
	}

	public MyCheckBox(String captionTxt) {
		Panel p = new VerticalPanel();
		caption.setText(captionTxt);

		HorizontalPanel space = new HorizontalPanel();
		space.setHeight("10px");

		p.add(checkBox);
		p.add(caption);
		p.add(space);

		caption.setStyleName("MyTxtBox.label");

		checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				// TODO Auto-generated method stub
				MyCheckBox.this.valueChanged();
			}
		});

		checkBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					enterPressed();
				}
			}
		});

		initWidget(p);
	}

	public void valueChanged() {
	}

	public void enterPressed() {
		// TODO Auto-generated method stub

	}

	public String getValue() {
		boolean val = this.checkBox.getValue();
		return (val ? "true" : "false");
	}

	public void setValue(String val) {
		this.checkBox.setValue(val.equals("true"));
	}

	public CheckBox getCheckBox() {
		return this.checkBox;
	}

	public void setIsEditable(boolean b) {
		this.checkBox.setEnabled(b);
	}

	public void setTextBoxWidth(String width) {
		checkBox.setWidth(width);
	}

	public void setName(String string) {
		checkBox.setName(string);

	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		checkBox.setEnabled(false);
	}

}
