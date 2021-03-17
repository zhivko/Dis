package si.telekom.dis.client.action;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentApiDump extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	HTML html;
	ScrollPanel sp;

	public DocumentApiDump(String r_object_id_) {
		r_object_id = r_object_id_;
		setText("Document API dump");
		setGlassEnabled(true);

		html = new HTML();
		
		sp=new ScrollPanel();
		sp.setWidth("600px");
		sp.setHeight("800px");
		sp.add(html);
		
		
		getContentPanel().add(sp);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				
				explorerService.apiDump(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id_, new AsyncCallback<String>() {
					@Override
					public void onSuccess(String result) {
						html.setHTML(result);
						MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
						DocumentApiDump.this.center();
					}

					@Override
					public void onFailure(Throwable caught) {
						html.setHTML("Demote unsuccesfull:<br><br>" + caught.getMessage());
						DocumentApiDump.this.center();
					}
				});
			}
		});

		
		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				// TODO Auto-generated method stub
				DocumentApiDump.this.hide();
			}
		});
		
	}

}
