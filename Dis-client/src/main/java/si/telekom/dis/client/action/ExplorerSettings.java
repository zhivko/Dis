package si.telekom.dis.client.action;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.Images;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyCheckBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.LoginService;
import si.telekom.dis.shared.LoginServiceAsync;

public class ExplorerSettings extends WindowBox {
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);
	static Images images = (Images) GWT.create(Images.class);

	public ExplorerSettings() {
		super();
		setText("Explorer nastavitve");

		MyTxtBox explorerResultSize = new MyTxtBox("Explorer result size");
		explorerResultSize.setValue(String.valueOf(MainPanel.getInstance().us.explorerReturnResultCount));
		
		MyTxtBox searchResultSize = new MyTxtBox("Search result size");
		searchResultSize.setValue(String.valueOf(MainPanel.getInstance().us.searchReturnResultCount));
		
		MyCheckBox checkBox = new MyCheckBox("Use callabora online for editing documents");
		checkBox.setValue(String.valueOf(MainPanel.getInstance().us.useColaboraOnlineForEdit));

		MyTxtBox auditTrailPerPageCount = new MyTxtBox("Audittrail per page count");
		auditTrailPerPageCount.setValue(String.valueOf(MainPanel.getInstance().us.auditTrailPerPageCount));
		
		getContentPanel().add(explorerResultSize);
		getContentPanel().add(searchResultSize);
		getContentPanel().add(auditTrailPerPageCount);
		getContentPanel().add(checkBox);
		
		this.setSize("400px", "400px");
		
		this.getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				MainPanel.getInstance().us.explorerReturnResultCount = Integer.valueOf(explorerResultSize.getValue());
				MainPanel.getInstance().us.searchReturnResultCount = Integer.valueOf(searchResultSize.getValue());
				MainPanel.getInstance().us.auditTrailPerPageCount = Integer.valueOf(auditTrailPerPageCount.getValue());
				MainPanel.getInstance().us.useColaboraOnlineForEdit = Boolean.valueOf(checkBox.getValue());
				try {
					loginService.saveUserSettings(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, MainPanel.getInstance().us,
							new AsyncCallback<Void>() {
								@Override
								public void onFailure(Throwable caught) {
									MainPanel.log(caught.getMessage());
								}

								@Override
								public void onSuccess(Void result) {
									ExplorerSettings.this.hide();
								}
							});
				} catch (Exception ex) {
					MainPanel.log(ex.getMessage());
				}
			}
		});

	}
	
	public static Widget getMenuItem() {
		ImageResource imageLeft = images.iconLeft();
		SafeHtmlBuilder shbuilder = new SafeHtmlBuilder();
		shbuilder.append(AbstractImagePrototype.create(imageLeft).getSafeHtml());
		
		Button b = new Button();
		b.setHTML(shbuilder.toSafeHtml());
		b.setText("Nastavitve");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ExplorerSettings es = new ExplorerSettings();
				es.show();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						es.center();
					}
				});
			}
		});
		return b;
	}
	
}
