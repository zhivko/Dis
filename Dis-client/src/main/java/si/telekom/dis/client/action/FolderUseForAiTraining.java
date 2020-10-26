package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.CustomTreeModel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MultiValueSelectBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.Profile;

public class FolderUseForAiTraining extends WindowBox {
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	public static FolderUseForAiTraining instance;

	CheckBox cbDeleteFiles;

	MyTxtBox folderName;
	MyTxtBox parentFolderPath;
	protected String classification_id;

	public FolderUseForAiTraining() {
		super();

		setText("Pripravi podatke za učenje");

		HorizontalPanel hp = new HorizontalPanel();

		hp.add(new Label("Izberi klasifikacijski znak:"));

		si.telekom.dis.shared.Attribute att = new si.telekom.dis.shared.Attribute();
		att.dqlValueListDefinition =
				"SELECT tc.\"code\", tc.\"name\" FROM dbo.T_CLASSIFICATION_PLAN tcp, dbo.T_CLASSIFICATION tc WHERE tc.\"classification_plan_id\" = tcp.\"id\" AND tcp.\"name\" = 'KNTS' AND DATE(TODAY) >= valid_from AND ( DATE(TODAY) <= valid_to OR valid_to = '' ) order by tc.code enable (return_top 20)";
		att.isRepeating = false;

		ListBox profiles = new ListBox();
		profiles.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub

			}
		});

		MultiValueSelectBox mvsb = new MultiValueSelectBox(att, new ListBox());
		mvsb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				// TODO Auto-generated method stub
				FolderUseForAiTraining.this.classification_id = event.getValue().get(0);

				adminService.getProfilesForClassSign(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						FolderUseForAiTraining.this.classification_id, "classify", new AsyncCallback<List<Profile>>() {
							@Override
							public void onSuccess(List<Profile> result) {
								// TODO Auto-generated method stub
								profiles.clear();
								for (Profile prof : result) {
									profiles.addItem(prof.name, prof.id);
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
							}
						});
			}
		});

		hp.add(mvsb);
		this.getPanel().add(hp);

		HorizontalPanel hp1 = new HorizontalPanel();

		hp1.add(new Label("Profil:"));
		hp1.add(profiles);
		this.getPanel().add(hp1);

		cbDeleteFiles = new CheckBox();
		this.getPanel().add(cbDeleteFiles);

		getOkButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				List<String> alString = new ArrayList<String>();
				for (String r_object_id : CustomTreeModel.selectionModel.getSelectedSet()) {
					alString.add(r_object_id);
				}

				getExplorerService().prepareAiTrainDataForFolderAndClassification(MainPanel.getInstance().loginName, MainPanel.getInstance().loginName,
						alString, FolderUseForAiTraining.this.classification_id, cbDeleteFiles.getValue(), new AsyncCallback<Void>() {
							@Override
							public void onSuccess(Void result) {
								MainPanel.log("folder content set for training data.");
							}

							@Override
							public void onFailure(Throwable caught) {

							}
						});
			}
		});
		
		ScheduledCommand commandCenter = new ScheduledCommand() {
			@Override
			public void execute() {
				FolderUseForAiTraining.this.center();
			}
		};
		Scheduler.get().scheduleDeferred(commandCenter);

		instance = this;
	}

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("Use for training data");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				MainPanel.getPanel().add(new FolderUseForAiTraining());
			}
		});
		return b;
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

}
