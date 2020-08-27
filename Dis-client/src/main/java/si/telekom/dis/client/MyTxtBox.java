package si.telekom.dis.client;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyTxtBox extends Composite {
	private Label caption = new Label();
	private TextBox textBox = new TextBox();
	
	public MyTxtBox(String captionTxt, int widthInPixels) {
		this(captionTxt);
		this.textBox.setWidth(String.valueOf(widthInPixels) + "px");
	}

	public MyTxtBox(String captionTxt) {
		Panel p = new VerticalPanel();
		caption.setText(captionTxt);

		HorizontalPanel space = new HorizontalPanel();
		space.setHeight("10px");

		p.add(textBox);
		p.add(caption);
		p.add(space);

		caption.setStyleName("MyTxtBox.label");

		textBox.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				MyTxtBox.this.valueChanged();
			}
		});
		
		textBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
				{
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
		return this.textBox.getText();
	}

	public void setValue(String val) {
		this.textBox.setText(val);
	}

	public TextBox getTextBox() {
		return this.textBox;
	}

	public void setIsEditable(boolean b) {
		this.textBox.setEnabled(b);
	}
	
	public void setTextBoxWidth(String width)
	{
		textBox.setWidth(width);
	}

	public void setName(String string) {
		textBox.setName(string);
		
	}

}
