package si.telekom.dis.client.item;

import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
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

public class Tab extends IsSelected {
	private TextBox id;
	private TextBox name;
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private VerticalContainer parentVertCont;
	private VerticalPanel vp;

	private TextBox row;
	private TextBox col;

	public Tab(si.telekom.dis.shared.Tab tab, VerticalContainer parent) {
		this();
		this.item = tab;

		id.setText(tab.id);
		name.setText(tab.name);

		row.setText(String.valueOf(tab.row));
		col.setText(String.valueOf(tab.col));
		this.parentVertCont = parent;
	}

	public Tab() {
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
				logger.info("Gor. Parent is of class: " + parentVertCont.getClass().getName());
				if (parentVertCont.getClass().getName().endsWith("VerticalContainer")) {
					parentVertCont.moveUp();
					NewProfile.instance.refreshAttsStatesRoles();
				}
				pop.hide(true);
			}
		});

		Label lblMoveDown = new Label();
		lblMoveDown.setStyleName("popUpItem");
		lblMoveDown.setText("Dol");
		lblMoveDown.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("Gor. Parent is of class: " + Tab.this.getParent().getClass().getName());
				if (parentVertCont.getClass().getName().endsWith("VerticalContainer")) {
					parentVertCont.moveDown();
					NewProfile.instance.refreshAttsStatesRoles();
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
				logger.info("Briši. Parent is of class: " + Tab.this.getParent().getClass().getName());
				NewProfile.instance.removeTab(parentVertCont.getSelectedIndex());
				// if
				// (Tab.this.getParent().getClass().getName().contains("VerticalContainer"))
				// {
				// parentVertCont.removeItem(parentVertCont.getSelectedIndex());
				// }
				pop.hide(true);
			}
		});

		VerticalPanel vp1 = new VerticalPanel();
		vp1.add(lblMoveUp);
		vp1.add(lblMoveDown);
		vp1.add(lblDelete);

		pop.add(vp1);

		id = new TextBox();
		id.setHeight("12px");
		id.setStyleName("role.id");

		// @Override
		// public void onValueChange(ValueChangeEvent<String> event) {
		// // TODO Auto-generated method stub
		// event.
		//
		// }
		// });

		HorizontalPanel hp1 = new HorizontalPanel();
		Label lbl = new Label();
		lbl.setText("id");
		hp1.add(lbl);
		hp1.setCellVerticalAlignment(lbl, HasVerticalAlignment.ALIGN_MIDDLE);
		hp1.add(id);

		name = new TextBox();
		name.setStyleName("role.name");
		name.addValueChangeHandler(new MyValueChangeHandler<String>(id) {

			@Override
			public void onValueChange(String value, String prevValue) {
				// TODO Auto-generated method stub
				if (!value.equals(prevValue)) {
					si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) item;
					tab.name = value;
					NewProfile.instance.refreshAttsStatesRoles();
				}
			}

		});
		vp.add(name);
		vp.add(hp1);
		SimplePanel sp1 = new SimplePanel();
		sp1.setHeight("5px");
		vp.add(sp1);

		col = new TextBox();
		col.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				((si.telekom.dis.shared.Tab) Tab.this.item).col = Integer.valueOf(col.getValue());
				NewProfile.instance.refreshAttsStatesRoles();
			}
		});

		row = new TextBox();
		row.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				((si.telekom.dis.shared.Tab) Tab.this.item).row = Integer.valueOf(row.getValue());
				NewProfile.instance.refreshAttsStatesRoles();
			}
		});

		Label lblRows = new Label(" rows:");
		hp1.add(lblRows);
		hp1.add(row);
		Label lblCols = new Label(" cols:");
		hp1.add(lblCols);
		hp1.add(col);
		row.setStyleName("role.id");
		col.setStyleName("role.id");
		col.setWidth("20px");
		row.setWidth("20px");
		id.setWidth("50px");
		lblRows.setStyleName("role.id");
		lblCols.setStyleName("role.id");

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
					setIsSelected(!Tab.this.getIsSelected());
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
				logger.info("Click. Parent is of class: " + Tab.this.getParent().getClass().getName());
				if (Tab.this.getParent().getClass().getName().contains("ListBox")) {

				}
			}
		}, ContextMenuEvent.getType());

		id.addValueChangeHandler(new MyValueChangeHandler<String>(id) {

			@Override
			public void onValueChange(String value, String prevValue) {
				// TODO Auto-generated method stub
				if (!value.equals(prevValue)) {
					si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) item;
					tab.id = value;
					Tab.this.idChanged();
					NewProfile.instance.changeTabId(prevValue, value);
				}
			}

		});
		name.addValueChangeHandler(new MyValueChangeHandler<String>(id) {

			@Override
			public void onValueChange(String value, String prevValue) {
				// TODO Auto-generated method stub
				if (!value.equals(prevValue)) {
					si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) item;
					tab.name = value;
					Tab.this.nameChanged();
				}
			}

		});

		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				// Reset the search field for next time
				NewProfile.instance.refreshAttsStatesRoles();
			}
		});
		initWidget(fp);
	}

	protected void idChanged() {
		// TODO Auto-generated method stub
	}

	protected void nameChanged() {
		// TODO Auto-generated method stub
	}

	public void setIsSelected(boolean b) {
		// try to get VerticalContainer and deselect others if necessary
		if (parentVertCont != null) {
			for (int i = 0; i < parentVertCont.vp.getWidgetCount(); i++) {
				((Tab) parentVertCont.vp.getWidget(i)).isSelected = false;
				((Tab) parentVertCont.vp.getWidget(i)).vp.getElement().getStyle().setBackgroundColor("");
			}
		}
		this.isSelected = b;
		selectedHasChanged();
		if (this.isSelected)
			this.vp.getElement().getStyle().setBackgroundColor("rgba(51, 142, 183, 0.39)");
	}

	public void selectedHasChanged() {
		// TODO Auto-generated method stub

	}

}
