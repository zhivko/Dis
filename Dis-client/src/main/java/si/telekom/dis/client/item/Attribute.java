package si.telekom.dis.client.item;

import java.util.logging.Logger;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.action.NewProfile;
import si.telekom.dis.shared.HasIdName;

public class Attribute extends IsSelected {
	private TextBox id;
	private VerticalContainer<si.telekom.dis.client.item.Attribute> parentVertCont;
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private boolean isSelected = false;
	VerticalPanel vp;

	public Attribute(String id1, VerticalContainer<Attribute> parent) {
		this();
		this.item = (HasIdName)(new si.telekom.dis.shared.Attribute(id1));
		id.setText(id1);
		this.parentVertCont = parent;
	}

	public Attribute() {
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
				logger.info("Gor. Parent is of class: " + Attribute.this.getParent().getClass().getName());
				if (Attribute.this.getParent().getClass().getName().contains("VerticalContainer")) {
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
				logger.info("Briši. Parent is of class: " + Attribute.this.getParent().getClass().getName());
				Attribute.this.parentVertCont.removeItemById(Attribute.this.getId());
				pop.hide(true);
			}
		});

		VerticalPanel vp1 = new VerticalPanel();
		vp1.add(lblMoveUp);
		vp1.add(lblDelete);

		pop.add(vp1);

		id = new TextBox();
		HorizontalPanel hp1 = new HorizontalPanel();
		Label lbl = new Label();
		lbl.setText("id");
		hp1.add(lbl);
		hp1.setCellVerticalAlignment(lbl, HasVerticalAlignment.ALIGN_MIDDLE);
		hp1.add(id);

		Button removeButton = new Button("X");
		removeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Attribute.this.parentVertCont.removeItemById(Attribute.this.getId());
			}
		});
		hp1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hp1.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hp1.add(removeButton);
		
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
					boolean prevSelected= Attribute.this.isSelected;
					logger.info("previous selected: " + Attribute.this.isSelected);
					Attribute.this.setIsSelected(!prevSelected);
					logger.info("now selected: " + Attribute.this.isSelected);
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
				logger.info("Click. Parent is of class: " + Attribute.this.getParent().getClass().getName());
				if (Attribute.this.getParent().getClass().getName().contains("ListBox")) {

				}
			}
		}, ContextMenuEvent.getType());

		initWidget(vp);
	}

	public void setIsSelected(boolean b) {
		// try to get VerticalContainer and deselect other roles if necessary
		if (parentVertCont != null) {
			for (int i = 0; i < parentVertCont.vp.getWidgetCount(); i++) {
				((Attribute) parentVertCont.vp.getWidget(i)).isSelected=false;
				((Attribute) parentVertCont.vp.getWidget(i)).vp.getElement().getStyle().setBackgroundColor("");
			}
		}
		this.isSelected = b;
		if (this.isSelected)
			this.vp.getElement().getStyle().setBackgroundColor("rgba(51, 142, 183, 0.39)");
	}


}
