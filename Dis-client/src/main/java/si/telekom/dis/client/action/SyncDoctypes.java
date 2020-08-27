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
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;

public class SyncDoctypes extends ActionDialogBox implements ClickHandler {
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	public static SyncDoctypes instance;

	public SyncDoctypes() {
		super();
		setText("SyncDoctypes");
		setAnimationEnabled(false);
		setGlassEnabled(true);
		
		getOkButton().addClickHandler(this);
		
		HTML html = new HTML();
		getPanel().add(html);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				html.setHTML("Syncing object types...");
				SyncDoctypes.this.center();
			}
		});
		
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				
				adminService.syncDoctypes(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						html.setHTML(html.getHTML() + "<br>" + "Sync object type succesfull. Refreshing doctypes...");
						MenuPanel.getInstance().refreshDocTypesAndAtts();
						html.setHTML(html.getHTML() + "<br>" + "Done.");
						SyncDoctypes.this.hide();
					}

					@Override
					public void onFailure(Throwable caught) {
						html.setHTML(html.getHTML() + "<br>" + "Sync object type unsuccesfull.<br>" + caught.getMessage());
						SyncDoctypes.this.center();
					}
				});
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
		
	}	

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("Sinhroniziraj objTypes<br>&Profiles");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				MainPanel.getPanel().add(new SyncDoctypes());
			}
		});
		return b;
	}
	
	
}
