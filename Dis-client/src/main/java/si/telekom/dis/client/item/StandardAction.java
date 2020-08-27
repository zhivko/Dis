package si.telekom.dis.client.item;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.action.NewProfile;
import si.telekom.dis.shared.HasIdName;

public class StandardAction extends IsSelected {
	public ListBox kindList;
	private TextBox parameterTxt;
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private VerticalContainer parentVertCont;
	private VerticalPanel vp;
	public HasIdName hasIdName;

	public StandardAction(VerticalContainer parent) {
		this();
		this.item = new si.telekom.dis.shared.StandardAction();
		this.parentVertCont = parent;
		parentVertCont.addItem(this);
	}

	public StandardAction(si.telekom.dis.shared.StandardAction sa, VerticalContainer<StandardAction> parentVertCont_) {
		this();
		this.item = sa;
		this.setKind(sa.kind);
		this.parameterTxt.setText(sa.parameter);

		this.parentVertCont = parentVertCont_;
	}

	public StandardAction() {
		vp = new VerticalPanel();
		vp.setWidth("100%");
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
				logger.info("Gor. Parent is of class: " + StandardAction.this.getParent().getClass().getName());
				if (StandardAction.this.getParent().getClass().getName().contains("VerticalContainer")) {

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
				logger.info("Briši. Parent is of class: " + StandardAction.this.getParent().getClass().getName());
				logger.info("Selected index: " + parentVertCont.getSelectedIndex());
				String className = StandardAction.this.getParent().getClass().getName();
				if (className.contains("VerticalPanel")) {
					NewProfile.instance.deleteStandardActionFromSelectedState(parentVertCont.getSelectedIndex());
					parentVertCont.removeItem(parentVertCont.getSelectedIndex());
				}
				pop.hide(true);
			}
		});

		VerticalPanel vp1 = new VerticalPanel();
		vp1.add(lblMoveUp);
		vp1.add(lblDelete);

		pop.add(vp1);

		kindList = new ListBox();
		kindList.setWidth("250px");
		for (si.telekom.dis.shared.StandardAction.types type1 : si.telekom.dis.shared.StandardAction.types.values()) {
			kindList.addItem(type1.type);
		}
		// kindList.setHeight("12px");
		// kindList.setStyleName("role.id");

		vp.setCellVerticalAlignment(kindList, HasVerticalAlignment.ALIGN_MIDDLE);
		vp.add(kindList);

		parameterTxt = new TextBox();
		parameterTxt.setStyleName("role.name");
		parameterTxt.setWidth("250px");
		
		parameterTxt.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				si.telekom.dis.shared.StandardAction sa = (si.telekom.dis.shared.StandardAction) StandardAction.this.item;
				sa.parameter = parameterTxt.getValue();
			}
		});
		
		vp.add(parameterTxt);
		fp.add(vp);

		fp.sinkEvents(Event.ONCONTEXTMENU | Event.ONCLICK | Event.ONKEYUP | Event.ONKEYDOWN);
		fp.addHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				parentVertCont.onKeyUp(event);
				
				String className = StandardAction.this.getParent().getClass().getName();
				if (className.contains("VerticalPanel")) {
					//GWT.log("Saving standard actions");
					//NewProfile.instance.saveStandardActionsForState();
				}				
				
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
					setIsSelected(!StandardAction.this.getIsSelected());
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
				logger.info("Click. Parent is of class: " + StandardAction.this.getParent().getClass().getName());
				if (StandardAction.this.getParent().getClass().getName().contains("ListBox")) {

				}
			}
		}, ContextMenuEvent.getType());

		kindList.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				si.telekom.dis.shared.StandardAction sa = (si.telekom.dis.shared.StandardAction) (StandardAction.this.item);
				sa.kind = kindList.getSelectedItemText();
			}
		});

		parameterTxt.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				si.telekom.dis.shared.StandardAction sa = (si.telekom.dis.shared.StandardAction) (StandardAction.this.item);
				sa.parameter = parameterTxt.getValue();
			}
		});

		initWidget(fp);
	}

	public void setIsSelected(boolean b) {
		// try to get VerticalContainer and deselect other roles if necessary
		if (parentVertCont != null) {
			for (int i = 0; i < parentVertCont.vp.getWidgetCount(); i++) {
				((StandardAction) parentVertCont.vp.getWidget(i)).isSelected = false;
				((StandardAction) parentVertCont.vp.getWidget(i)).vp.getElement().getStyle().setBackgroundColor("");
			}
		}
		this.isSelected = b;
		if (this.getIsSelected())
			this.vp.getElement().getStyle().setBackgroundColor("rgba(51, 142, 183, 0.39)");
	}

	public String setKind(String value) {
		for (int i = 0; i < kindList.getItemCount(); i++) {
			if (kindList.getItemText(i).equals(value)) {
				kindList.setSelectedIndex(i);
				si.telekom.dis.shared.StandardAction itemSa = (si.telekom.dis.shared.StandardAction) this.item;
				itemSa.kind = value;
				break;
			}
		}
		return null;
	}
}
