package si.telekom.dis.client.action;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.Document;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentDemote extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	HTML html;

	public DocumentDemote(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Document Demote");
		setGlassEnabled(true);

		html = new HTML();
		getContentPanel().add(html);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				
				explorerService.demote(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id_, new AsyncCallback<Document>() {
					@Override
					public void onSuccess(Document result) {
						html.setHTML("Demote succesfull.");
						MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
						DocumentDemote.this.center();
					}

					@Override
					public void onFailure(Throwable caught) {
						html.setHTML("Demote unsuccesfull:<br><br>" + caught.getMessage());
						DocumentDemote.this.center();
					}
				});
			}
		});

		
		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				DocumentDemote.this.hide();
			}
		});
		
	}

}
