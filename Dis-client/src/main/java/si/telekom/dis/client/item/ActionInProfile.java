package si.telekom.dis.client.item;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.shared.Action;

public class ActionInProfile extends Composite {
	public Action item;

	public ActionInProfile(Action act) {
		this.item = act;
		HorizontalPanel hp = new HorizontalPanel();

		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		HTML lbl = new HTML(item.getName());
		lbl.addDragStartHandler(new DragStartHandler() {
			@Override
			public void onDragStart(DragStartEvent event) {
				// TODO Auto-generated method stub
				event.setData("text", "action:" + act.toString());
			}
		});
		hp.add(lbl);
		Button remove = new Button("X");
		remove.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				VerticalPanel vp = (VerticalPanel) ActionInProfile.this.getParent();
				vp.remove(ActionInProfile.this);
				
				if(vp.getWidgetCount()==0)
				{
					// we shoud add simple panel back
					FocusPanel sp = new FocusPanel();
					vp.add(sp);
					sp.setHeight("100px");
					sp.setWidth("100px");
				}
				
			}
		});
		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hp.add(remove);

		initWidget(hp);
	}
}
