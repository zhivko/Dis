package si.telekom.dis.client.action;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MyListBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentRemoveRendition extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);

	MyListBox renditions;
	MyTxtBox customLabel = new MyTxtBox("Labela verzije");

	public DocumentRemoveRendition(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Standardne labele verzije");
		setGlassEnabled(true);

		// versionLabel = new MyTxtBox("Labela verzije");
		renditions = new MyListBox("Rendicije", true);

		explorerService.getRenditions(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id_, new AsyncCallback<List<String>>() {
			@Override
			public void onFailure(Throwable caught) {
				MainPanel.log(caught.getMessage());
			}
			
			public void onSuccess(List<String> result) {
				for (String rendition : result) {
					renditions.addItem(rendition);
				}
			}; 
		});

		renditions.setSelectedIndex(-1);
		
		getContentPanel().add(renditions);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentRemoveRendition.this.center();
			}
		});		

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String labelVersion = null;
				if (renditions.getSelectedIndex() > -1)
					labelVersion = renditions.getValue();
				else
					labelVersion = customLabel.getValue();

				if (labelVersion != null)
					explorerService.removeRendition(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id_, renditions.getListBox().getSelectedItemText(),
							new AsyncCallback<Void>() {
								@Override
								public void onSuccess(Void result) {
									MainPanel.log("Brisanje rendicije uspešno.");
									MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
									DocumentRemoveRendition.this.hide();
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
