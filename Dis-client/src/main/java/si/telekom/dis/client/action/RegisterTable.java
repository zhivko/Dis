package si.telekom.dis.client.action;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.ActionDialogBox;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ServerException;

public class RegisterTable extends ActionDialogBox implements ClickHandler {
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	public static RegisterTable instance;

	HTML registerTblLog;
	MyTxtBox registerTableName;
	
	public RegisterTable() {
		super();
		setText("Registriraj tabelo");
		setAnimationEnabled(false);
		setGlassEnabled(true);

		//getOkButton().addClickHandler(this);

		registerTableName = new MyTxtBox("Ime tabele iz dokumentnega sistema (T_DOC_...)");
		getPanel().add(registerTableName);

		
		registerTblLog = new HTML();
		registerTblLog.setSize("500px", "500px");
		getPanel().add(registerTblLog);

		getOkButton().addClickHandler(this);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				RegisterTable.this.center();
			}
		});

		instance = this;
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

	@Override
	public void onClick(ClickEvent event) {
		// OK pressed
		registerTblLog.setHTML(registerTblLog.getHTML() + "<br>Start.");
		try {
			adminService.registerTable(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, registerTableName.getValue(),
					new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							registerTblLog.setHTML(registerTblLog.getHTML() + "<br>Registered succesfully.");
						}

						@Override
						public void onFailure(Throwable caught) {
							registerTblLog.setHTML(registerTblLog.getHTML() + "<br>Error:<br>" + caught.getMessage());
						}

					});
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("Registriraj tabelo");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				MainPanel.getPanel().add(new RegisterTable());
			}
		});
		return b;
	}

}
