package si.telekom.dis.client.item;

import java.util.logging.Logger;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.MyValueChangeHandler;
import si.telekom.dis.client.action.NewProfile;
import si.telekom.dis.shared.HasIdName;

public class State extends IsSelected {
	private TextBox id;
	private TextBox name;
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private VerticalContainer parentVertCont;
	private VerticalPanel vp;
	public HasIdName hasIdName;

	public State(si.telekom.dis.shared.State statItem, VerticalContainer parent) {
		this();
		this.item = statItem;

		id.setText(item.getId());
		name.setText(item.getParameter());
		this.parentVertCont = parent;
		hasIdName = new si.telekom.dis.shared.State();
		hasIdName.setId(id.getText());
		hasIdName.setName(name.getText());
	}	
	
	public State(String id1, String name1, VerticalContainer parent) {
		this();
		this.item = new si.telekom.dis.shared.State(id1, name1);

		id.setText(id1);
		name.setText(name1);
		this.parentVertCont = parent;
		hasIdName = new si.telekom.dis.shared.State();
		hasIdName.setId(id.getText());
		hasIdName.setName(name.getText());
	}

	public State() {
		vp = new VerticalPanel();
		vp.setWidth("90%");
		FocusPanel fp = new FocusPanel();
		PopupPanel pop = new PopupPanel();
		pop.setModal(true);
		pop.setAutoHideEnabled(true);

		Label lblMoveUp = new Label();
		lblMoveUp.setStyleName("popUpItem");
		lblMoveUp.setText("Gor");
		lblMoveUp.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("Gor. Parent is of class: " + State.this.getParent().getClass().getName());
				if (State.this.getParent().getClass().getName().contains("VerticalContainer")) {

				}
				pop.hide(true);
			}
		});
		Label lblDelete = new Label();
		lblDelete.setStyleName("popUpItem");
		lblDelete.setText("Briši");
		lblDelete.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("Briši. Parent is of class: " + State.this.getParent().getClass().getName());
				if (State.this.getParent().getClass().getName().contains("VerticalContainer")) {
					parentVertCont.removeItem(parentVertCont.getSelectedIndex());
				}
				pop.hide(true);
			}
		});

		VerticalPanel vp1 = new VerticalPanel();
		vp1.add(lblMoveUp);
		vp1.add(lblDelete);

		pop.add(vp1);

		id = new TextBox();
		id.setHeight("12px");
		id.setWidth("90%");
		id.setStyleName("state.id");


		HorizontalPanel hp1 = new HorizontalPanel();
		Label lbl = new Label();
		lbl.setText("id");
		hp1.add(lbl);
		hp1.setCellVerticalAlignment(lbl, HasVerticalAlignment.ALIGN_MIDDLE);
		hp1.add(id);

		name = new TextBox();
		name.setStyleName("state.name");
		name.setWidth("90%");
		vp.add(name);
		vp.add(hp1);
		SimplePanel sp1 = new SimplePanel();
		sp1.setHeight("5px");
		vp.add(sp1);

		fp.add(vp);

		fp.sinkEvents(Event.ONCONTEXTMENU | Event.ONCLICK | Event.ONKEYUP | Event.ONKEYDOWN);
		fp.addHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				parentVertCont.onKeyUp(event);
				event.preventDefault();
				event.stopPropagation();
			}
		}, KeyUpEvent.getType());

		fp.addHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int button = event.getNativeEvent().getButton();
				logger.info("id: " + event.getRelativeElement().getId());
				if (button == NativeEvent.BUTTON_LEFT) {
					setIsSelected(!State.this.getIsSelected());
				}
			}
		}, ClickEvent.getType());

		fp.addHandler(new ContextMenuHandler() {
			@Override
			public void onContextMenu(ContextMenuEvent event) {
				event.preventDefault();
				event.stopPropagation();
				pop.setPopupPosition(event.getNativeEvent().getClientX(), event.getNativeEvent().getClientY());
				pop.show();
				logger.info("Click. Parent is of class: " + State.this.getParent().getClass().getName());
				if (State.this.getParent().getClass().getName().contains("ListBox")) {

				}
			}
		}, ContextMenuEvent.getType());

		id.addValueChangeHandler(new MyValueChangeHandler<String>(id) {
			@Override
			public void onValueChange(String value, String prevValue) {
				// TODO Auto-generated method stub
				if (!value.equals(prevValue))
					State.this.item.setId(id.getText());
					NewProfile.instance.changeStateId(prevValue, value);
					State.this.idChanged();
			}
		});
		
		name.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				State.this.item.setName(name.getText());
				State.this.nameChanged();
			}
		});

		initWidget(fp);
	}

	public void setIsSelected(boolean b) {
		// try to get VerticalContainer and deselect other roles if necessary
		if (parentVertCont != null) {
			for (int i = 0; i < parentVertCont.vp.getWidgetCount(); i++) {
				((State) parentVertCont.vp.getWidget(i)).isSelected = false;
				((State) parentVertCont.vp.getWidget(i)).vp.getElement().getStyle().setBackgroundColor("");
			}
		}
		this.isSelected = b;
		if (this.getIsSelected())
			this.vp.getElement().getStyle().setBackgroundColor("rgba(51, 142, 183, 0.39)");
		
		//if state is selected lets paint standard actions...
		NewProfile.instance.refreshStandardActions();
		
	}

	protected void idChanged() {
		// TODO Auto-generated method stub
	}

	protected void nameChanged() {
		// TODO Auto-generated method stub
	}
}
