package si.telekom.dis.client.item;

import java.util.logging.Logger;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Event;
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

public class UserGroup extends IsSelected {
	private TextBox id;
	private TextBox name;
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private VerticalContainer parentVertCont;
	private VerticalPanel vp;

	public UserGroup(si.telekom.dis.shared.UserGroup userGroup_, VerticalContainer parent) {
		this();
		this.item = userGroup_;
		id.setText(this.item.getId());
		name.setText(this.item.getParameter());
		this.parentVertCont = parent;
	}

	public UserGroup() {
		vp = new VerticalPanel();
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
				logger.info("Gor. Parent is of class: " + UserGroup.this.getParent().getClass().getName());
				if (UserGroup.this.getParent().getClass().getName().contains("VerticalContainer")) {
					parentVertCont.removeItem(parentVertCont.getSelectedIndex());
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
				logger.info("Briši. Parent is of class: " + UserGroup.this.getParent().getClass().getName());
				NewProfile.instance.removeRole(parentVertCont.getSelectedIndex());

				if (UserGroup.this.getParent().getClass().getName().contains("VerticalContainer")) {

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
		id.setStyleName("role.id");
		id.setEnabled(false);


		HorizontalPanel hp1 = new HorizontalPanel();
		Label lbl = new Label();
		lbl.setText("id");
		hp1.add(lbl);
		hp1.setCellVerticalAlignment(lbl, HasVerticalAlignment.ALIGN_MIDDLE);
		hp1.add(id);

		name = new TextBox();
		name.setStyleName("role.name");
		name.setEnabled(false);
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
				if (button == NativeEvent.BUTTON_LEFT) {
					boolean prevSelected= UserGroup.this.getIsSelected();
					logger.info("previous selected: " + UserGroup.this.getIsSelected());
					UserGroup.this.setIsSelected(!prevSelected);
					logger.info("now selected: " + UserGroup.this.getIsSelected());
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
				logger.info("Click. Parent is of class: " + UserGroup.this.getParent().getClass().getName());
				if (UserGroup.this.getParent().getClass().getName().contains("ListBox")) {

				}
			}
		}, ContextMenuEvent.getType());

		id.addValueChangeHandler(new MyValueChangeHandler<String>(id) {
			@Override
			public void onValueChange(String value, String prevValue) {
				// TODO Auto-generated method stub
				if (!value.equals(prevValue))
				{
					UserGroup.this.item.setId(id.getText());
					UserGroup.this.idChanged();
					NewProfile.instance.changeRoleId(prevValue, value);
				}
			}
		});		
		
		name.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				UserGroup.this.item.setName(name.getText());
				UserGroup.this.nameChanged();
			}
		});

		
		initWidget(fp);
	}

	public void setIsSelected(boolean b) {
		// try to get VerticalContainer and deselect other roles if necessary
		if (parentVertCont != null) {
			for (int i = 0; i < parentVertCont.vp.getWidgetCount(); i++) {
				((UserGroup) parentVertCont.vp.getWidget(i)).isSelected=false;
				((UserGroup) parentVertCont.vp.getWidget(i)).vp.getElement().getStyle().setBackgroundColor("");
			}
		}
		this.isSelected = b;
		if (this.isSelected)
			this.vp.getElement().getStyle().setBackgroundColor("rgba(51, 142, 183, 0.39)");
	}

	protected void idChanged() {
		// TODO Auto-generated method stub
	}
	
	protected void nameChanged() {
		// TODO Auto-generated method stub
	}		
}
