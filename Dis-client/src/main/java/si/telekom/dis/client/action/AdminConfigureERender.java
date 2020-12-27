package si.telekom.dis.client.action;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyListBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class AdminConfigureERender extends WindowBox {

	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	MyListBox ALLOW_PDF_PRINTING;
	MyTxtBox customLabel = new MyTxtBox("Labela verzije");

	public AdminConfigureERender() {

		setText("Configure ERender");

		// versionLabel = new MyTxtBox("Labela verzije");
		ALLOW_PDF_PRINTING = new MyListBox("Allow pdf printing", true);

		ALLOW_PDF_PRINTING.addItem("true");
		ALLOW_PDF_PRINTING.addItem("false");

		ALLOW_PDF_PRINTING.setSelectedIndex(0);

		getContentPanel().add(ALLOW_PDF_PRINTING);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				AdminConfigureERender.this.center();
			}
		});

		getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				String xml = "<config>" + "  <ALLOW_PDF_PRINTING>" + ALLOW_PDF_PRINTING.getValue() + "</ALLOW_PDF_PRINTING>" + "</config>";

				explorerService.configureERender(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, xml, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						MainPanel.log("configureERender uspešno.");
						AdminConfigureERender.this.hide();
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
