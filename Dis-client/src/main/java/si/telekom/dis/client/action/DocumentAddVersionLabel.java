package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MyListBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentAddVersionLabel extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	MyListBox versionLabel;
	MyTxtBox customLabel = new MyTxtBox("Labela verzije");

	public DocumentAddVersionLabel(String r_object_id_) {
		this.r_object_id = r_object_id_;
		
		ArrayList<String> allChecked = new ArrayList<String>();
		for (String r_object_ids_ : ExplorerPanel.getExplorerInstance().getCheckedObjects()) {
			allChecked.add(r_object_ids_);
		}
		if(allChecked.size()==0)
			allChecked.add(this.r_object_id);
		
		setText("Standardne labele verzije");
		setGlassEnabled(true);

		// versionLabel = new MyTxtBox("Labela verzije");
		versionLabel = new MyListBox("Standardne labele verzij", true);

		versionLabel.addItem("draft");
		versionLabel.addItem("effective");
		versionLabel.addItem("archive");
		versionLabel.addItem("sb1");
		versionLabel.addItem("sb2");

		versionLabel.setSelectedIndex(-1);
		
		getContentPanel().add(customLabel);
		getContentPanel().add(new Label("ali"));
		getContentPanel().add(versionLabel);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentAddVersionLabel.this.center();
			}
		});		

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String labelVersion = null;
				if (versionLabel.getSelectedIndex() > -1)
					labelVersion = versionLabel.getValue();
				else
					labelVersion = customLabel.getValue();

				if (labelVersion != null)
					explorerService.addVersionLabel(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, allChecked, labelVersion,
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									MainPanel.log("Dodajanje labele verzije uspešno.");
									MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
									DocumentAddVersionLabel.this.hide();
								}

								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log("Napaka: " + caught.getMessage());
								}
							});

			}
		});

	}
}
