package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;

import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentNewRelease extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	CheckBox allVersions;

	public DocumentNewRelease(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Document Delete");
		setGlassEnabled(true);

		allVersions = new CheckBox();
		allVersions.setText("Vse verzije");
		getContentPanel().add(allVersions);

		String toDeleteStr = "";
		ArrayList<String> allChecked = new ArrayList<String>();
		for (String r_object_ids_ : ExplorerPanel.getExplorerInstance().getCheckedObjects()) {
			allChecked.add(r_object_ids_);
			toDeleteStr = toDeleteStr + "," + r_object_ids_;
		}

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				boolean allV = allVersions.getValue();
				if (allChecked.size() > 0)
					explorerService.deleteObjects(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, allChecked, allV,
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									MainPanel.log("Delete succesfull.");
									MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
									MenuPanel.activeExplorerInstance.clearSelectedSet();
									DocumentNewRelease.this.hide();
								}
								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log("Error deleting object: " + caught.getMessage());
								}
							});
				else
					explorerService.deleteObject(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id_, allV,
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									MainPanel.log("Delete succesfull <strong>" + r_object_id_ + "</strong> deleted sucesfully.");
									MenuPanel.activeExplorerInstance.refreshLastSelectedNodeByChronicleId();;
									DocumentNewRelease.this.hide();
								}

								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log("Error deleting object: " + caught.getMessage());
								}
							});

			}
		});
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentNewRelease.this.center();
			}
		});

	}
}
