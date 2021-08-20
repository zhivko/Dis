package si.telekom.dis.client.action;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.ActionDialogBox;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyTextArea;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.ServerException;

public class NewProfileFromXml extends ActionDialogBox implements ClickHandler {
	public final static AdminServiceAsync adminService = GWT.create(AdminService.class);
	public static NewProfileFromXml instance;
	public MyTextArea profileXML;

	public NewProfileFromXml() {
		super();
		VerticalPanel p = new VerticalPanel();
		profileXML = new MyTextArea("profile xml");
		profileXML.getTextBox().setWidth("500px");
		profileXML.getTextBox().setHeight("500px");
		getPanel().add(profileXML);
		setAnimationEnabled(false);
		setGlassEnabled(true);
		setText("Load profile from xml");
		getOkButton().addClickHandler(this);
		instance = this;
	}

	@Override
	public void onClick(ClickEvent event) {
		try {
			adminService.parseProfileFromXml(profileXML.getValue(), new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					MainPanel.log("Loaded profile.");
				}

				@Override
				public void onFailure(Throwable caught) {
					MainPanel.log("Error loading profile from xml: " + caught.getMessage());
				}
			});
		} catch (ServerException e) {
			MainPanel.log(e.getMessage());
		}
	}

	public static Widget getMenuItem() {
		Button b = new Button("Load profile from xml");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				NewProfileFromXml np = new NewProfileFromXml();
				np.setModal(true);
				np.show();
				np.center();
			}
		});
		return b;
	}

}
