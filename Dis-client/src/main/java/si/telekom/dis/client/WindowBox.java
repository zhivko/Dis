package si.telekom.dis.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class WindowBox extends PopupPanel implements SupportsShow {
	private final static AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	
	
	private String r_object_id;

	VerticalPanel vp;
	HTML label;
	Button okButton;
	Button cancelButton;
	VerticalPanel contentPanel;
	HorizontalPanel hp;

	public WindowBox() {
		vp = new VerticalPanel();
		label = new HTML();

		vp.add(label);
		vp.setCellHeight(label, "30em");

		contentPanel = new VerticalPanel();
		vp.add(contentPanel);

		hp = new HorizontalPanel();
		okButton = new Button("OK");
		getContentPanel().setCellHeight(hp, "30em");

		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hp.add(okButton);
		hp.setCellHorizontalAlignment(okButton, HasHorizontalAlignment.ALIGN_CENTER);

		cancelButton = new Button("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				WindowBox.this.hide();
			}
		});
		hp.add(cancelButton);
		hp.setCellHorizontalAlignment(cancelButton, HasHorizontalAlignment.ALIGN_CENTER);
		vp.add(hp);

		this.add(vp);
		
		//History.newItem("page at" + System.currentTimeMillis());
		
		setWidget(vp);
	}

	public WindowBox(String r_object_id_) {
		this();
		this.r_object_id = r_object_id_;
	}

	public VerticalPanel getContentPanel() {
		return contentPanel;
	}

	public HorizontalPanel getButtonPanel() {
		return hp;
	}

	public VerticalPanel getPanel() {
		return this.getContentPanel();
	}

	public void setText(String title) {
		
		label.setHTML(title);
	}

	public Button getOkButton() {
		return okButton;
	}

	public void centerWindowBox() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				WindowBox.this.center();
			}
		});
	}

	protected int getMaxWidth()
	{
		return (int) Window.getClientWidth() * 3 / 4;
	}
	
	protected String getMaxWidthPx() {
		return (String.valueOf(getMaxWidth()) + "px");
	}
	protected String getMaxHeightPx() {
		return String.valueOf(((int) Window.getClientHeight() * 3 / 4)) + "px";
	}

	public ExplorerServiceAsync getExplorerService() {
		return explorerService;
	}
	
	public static AdminServiceAsync getAdminService()
	{
		return adminService;
	}	

	public static Logger getLogger()
	{
		return logger;
	}
	
}
