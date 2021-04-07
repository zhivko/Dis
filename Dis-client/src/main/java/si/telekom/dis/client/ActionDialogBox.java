package si.telekom.dis.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class ActionDialogBox extends DialogBox {
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private String r_object_id;
	private VerticalPanel p;
	private DockPanel dockP;
	private HorizontalPanel commandPanel;
	private Button okButton;

	public ActionDialogBox() {
		super();
		p = new VerticalPanel();
		dockP = new DockPanel();

		commandPanel = new HorizontalPanel();

		okButton = new Button("OK");

		Button closeButton = new Button("Close");
		closeButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ActionDialogBox.this.hide();
			}
		});

		commandPanel.add(okButton);
		commandPanel.add(closeButton);

		dockP.add(p, DockPanel.NORTH);
		dockP.add(commandPanel, DockPanel.SOUTH);

		this.setModal(true);
		
		setWidget(dockP);
	}

	public ActionDialogBox(String r_object_id_) {
		this();
		this.r_object_id = r_object_id_;
	}

	public ActionDialogBox(String r_object_id_, String message) {
		this();
		this.r_object_id = r_object_id_;
		this.getCaption().setText(message);
		//p.add(new Label(message));
	}	

	public void run() {
	}

	public String getRObjectId() {
		return r_object_id;
	}

	public void activateDialog() {
		this.center();
		this.show();
	}

	public ExplorerServiceAsync getExplorerService() {
		return explorerService;
	}
	
	public static AdminServiceAsync getAdminService()
	{
		return adminService;
	}	

	public Logger getLogger() {
		return logger;
	}

	public VerticalPanel getPanel() {
		return p;
	}

	public void addClickHandler(ClickHandler clickHandler) {
		okButton.addClickHandler(clickHandler);
	}
	
	public Button getOkButton()
	{
		return okButton;
	}
	
	
	public HorizontalPanel getCommandPanel()
	{
		return commandPanel;
	}


}
