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
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentPromote extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	HTML html;

	public DocumentPromote(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Document Promote");
		setGlassEnabled(true);

		html = new HTML();
		getContentPanel().add(html);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {

				explorerService.promote(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						r_object_id_, new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								html.setHTML("Promote succesfull.");
								MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
								DocumentPromote.this.center();
							}

							@Override
							public void onFailure(Throwable caught) {
								html.setHTML("Promote unsuccesfull:<br><br>" + caught.getMessage());
								DocumentPromote.this.center();
							}
						});
			}
		});

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				DocumentPromote.this.hide();
			}
		});

	}

}
