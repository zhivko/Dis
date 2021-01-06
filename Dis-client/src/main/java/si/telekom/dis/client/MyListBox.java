package si.telekom.dis.client;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MyListBox extends Composite {
	private Label caption = new Label();
	private ListBox listBox = new ListBox();

	public MyListBox(String captionTxt, int widthInPixels) {
		this(captionTxt, false);
		this.listBox.setWidth(String.valueOf(widthInPixels) + "px");
	}

	public MyListBox(String captionTxt) {
		this(captionTxt, false);
	}
	
	public void addItem(String value, String txt)
	{
		listBox.addItem(value, txt);
	}


	public void addChangeHandler(ChangeHandler handler) {
		listBox.addChangeHandler(handler);
	}

	public MyListBox(String captionTxt, boolean noAddButton) {
		Panel p = new VerticalPanel();

		HorizontalPanel hp1 = new HorizontalPanel();
		caption.setText(captionTxt);

		HorizontalPanel space = new HorizontalPanel();
		space.setHeight("5px");

		hp1.add(listBox);
		if (!noAddButton) {
			Button addButton = new Button("dodaj");
			addButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					MyListBox.this.enterPressed();
				}
			});
			hp1.add(addButton);
		}
		p.add(hp1);
		p.add(caption);
		p.add(space);

		caption.setStyleName("MyTxtBox.label");

		listBox.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					MyListBox.this.enterPressed();
				}
			}
		});

		initWidget(p);
	}

	public void valueChanged() {
	}

	public String getValue() {
		if (this.listBox.getSelectedIndex() != -1)
			return this.listBox.getSelectedItemText();
		return null;
	}

	public String getItemValue() {
		return this.listBox.getSelectedValue();
	}
	
	public ListBox getListBox() {
		return this.listBox;
	}

	public void setIsEditable(boolean b) {
		this.listBox.setEnabled(b);
	}

	public void enterPressed() {
		// TODO Auto-generated method stub

	}

	public void addItem(String value) {
		this.listBox.addItem(value);
	}

	public void setSelectedIndex(int i) {
		this.listBox.setSelectedIndex(i);
	}

	public int getSelectedIndex() {
		return this.listBox.getSelectedIndex();
	}

}
