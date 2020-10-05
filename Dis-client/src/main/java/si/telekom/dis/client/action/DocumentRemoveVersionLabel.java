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
import com.google.gwt.user.client.ui.HTML;

import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentRemoveVersionLabel extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);

	MyTxtBox versionLabel;

	public DocumentRemoveVersionLabel(String r_object_id_) {

		this.r_object_id = r_object_id_;
		
		
		ArrayList<String> allChecked = new ArrayList<String>();
		for (String r_object_ids_ : ExplorerPanel.getExplorerInstance().getCheckedObjects()) {
			allChecked.add(r_object_ids_);
		}
		if(allChecked.size()==0)
			allChecked.add(this.r_object_id);

		
		
		setText("Odstrani labelo verzije");
		setGlassEnabled(true);

		versionLabel = new MyTxtBox("Labela verzije");
		getContentPanel().add(versionLabel);

		
		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String labelVersion = versionLabel.getValue();
				explorerService.removeVersionLabel(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, allChecked, labelVersion,
						new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								MainPanel.log("Odstranjevanje labele verzije uspešno.");
								MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
								DocumentRemoveVersionLabel.this.hide();
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
