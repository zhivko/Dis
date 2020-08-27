package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.ActionDialogBox;
import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class NewFolder extends ActionDialogBox {
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	public static NewFolder instance;

	MyTxtBox folderName;
	MyTxtBox parentFolderPath;

	public NewFolder() {
		super();

		setText("Nova mapa");
		parentFolderPath = new MyTxtBox("Pot nadrejene mape");
		parentFolderPath.setTextBoxWidth("300px");
		folderName = new MyTxtBox("Naziv mape");
		folderName.setTextBoxWidth("300px");

		getPanel().add(parentFolderPath);
		getPanel().add(folderName);

		parentFolderPath.setValue(ExplorerPanel.getExplorerInstance().getFolderPath());

		this.getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				explorerService.newFolder(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, folderName.getValue(),
						parentFolderPath.getValue(), new AsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								MainPanel.log("Mapa kreirana osveži explorer.");
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log("Napaka: " + caught.getMessage());
							}
						});
			}
		});
		
		instance = this;
	}

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("Nova mapa");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				MainPanel.getPanel().add(new NewFolder());
			}
		});
		return b;
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

}
