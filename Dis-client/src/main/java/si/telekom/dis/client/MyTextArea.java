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

public class MyTextArea extends Composite {
	private Label caption = new Label();
	private TextArea textArea = new TextArea();

	public MyTextArea(String captionTxt, int widthInPixels) {
		this(captionTxt);
		this.textArea.setWidth(String.valueOf(widthInPixels) + "px");
	}

	public MyTextArea(String captionTxt) {
		Panel p = new VerticalPanel();
		caption.setText(captionTxt);

		HorizontalPanel space = new HorizontalPanel();
		space.setHeight("10px");

		p.add(textArea);
		p.add(caption);
		p.add(space);

		caption.setStyleName("MyTxtBox.label");

		textArea.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					MyTextArea.this.enterPressed();
				}
			}
		});

		textArea.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				MyTextArea.this.valueChanged();
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
		return this.textArea.getText();
	}

	public void setValue(String val) {
		this.textArea.setText(val);
	}

	public TextArea getTextBox() {
		return this.textArea;
	}

	public void setIsEditable(boolean b) {
		this.textArea.setEnabled(b);
	}
	
	public void setTextAreaWidth(String width)
	{
		textArea.setWidth(width);
	}
	public void setTextAreaHeight(String height)
	{
		textArea.setHeight(height);
	}
	
	public void setName(String string) {
		textArea.setName(string);
		
	}

}
