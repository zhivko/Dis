package si.telekom.dis.client.item;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.shared.HasIdName;

public class VerticalContainer<T> extends Composite implements KeyUpHandler {
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	VerticalPanel vp;
	ArrayList<KeyUpHandler> keyUpHandlers;
	ArrayList<HasIdName> arrayList;
	private boolean singleSelection = true;

	public VerticalContainer() {
		keyUpHandlers = new ArrayList<KeyUpHandler>();
		arrayList = new ArrayList<HasIdName>();
		vp = new VerticalPanel();
		vp.getElement().setId("vpIdVertCont");
		vp.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

		vp.setWidth("100%");
		ScrollPanel sp = new ScrollPanel(vp);
		// sp.setHeight("300px");
		// sp.setWidth("200px");

		initWidget(sp);
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		for (KeyUpHandler keyUpHandler : keyUpHandlers) {
			keyUpHandler.onKeyUp(event);
		}
	}

	public void addKeyUpHandler(KeyUpHandler keyUpHandler) {
		// TODO Auto-generated method stub
		this.keyUpHandlers.add(keyUpHandler);
	}

	public void addItem(IsSelected widget) {
		// TODO Auto-generated method stub
		this.vp.add(widget);
		arrayList.add(widget.item);
	}

	public int getItemCount() {
		// TODO Auto-generated method stub
		return vp.getWidgetCount();
	}

	public int getSelectedIndex() {
		// TODO Auto-generated method stub
		for (int i = 0; i < vp.getWidgetCount(); i++) {
			{
				IsSelected w = (IsSelected) vp.getWidget(i);
				if (w.getIsSelected())
					return i;
			}
		}
		return -1;
	}

	public void removeItem(int itemNo) {
		vp.remove(itemNo);
		arrayList.remove(itemNo);
	}

	public void removeItemById(String id) {
		for (int i = 0; i < getItemCount(); i++) {
			if (this.getItem(i).getId().equals(id)) {
				this.removeItem(i);
				break;
			}
		}
	}

	public void setSingleSelection(boolean single) {
		this.singleSelection = single;
	}

	public IsSelected getItem(int num) {
		return (IsSelected) vp.getWidget(num);
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		int selInd = getSelectedIndex();
		if (getSelectedIndex() > 0) {
			HasIdName item = arrayList.remove(selInd);
			arrayList.add(selInd - 1, item);

			logger.info("UP SelectedIndex:" + selInd);
			Widget w = vp.getWidget(selInd);
			vp.insert(w, selInd - 1);

		}
	}

	public void moveDown() {
		// TODO Auto-generated method stub
		int selInd = getSelectedIndex();
		if (selInd < vp.getWidgetCount() - 1) {
			HasIdName item = arrayList.remove(selInd + 1);
			arrayList.add(selInd, item);

			logger.info("DOWN SelectedIndex:" + selInd);
			Widget w = vp.getWidget(selInd + 1);
			vp.insert(w, selInd);
		}
	}

	// public ArrayList<HasIdName> getArrayList() {
	// return arrayList;
	// }

	public ArrayList getArrayList() {
		// ArrayList<HasIdName> ret = new ArrayList();
		//
		// for (HasIdName obj : arrayList) {
		// ret.add(obj);
		// }
		//
		// return ret;

		return arrayList;
	}

	public Widget getVerticalPanel() {
		// TODO Auto-generated method stub
		return vp;
	}

	public void clear() {
		vp.clear();
		arrayList.clear();
	}

	public void selectionChanged() {

	}

}
