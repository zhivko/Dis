package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.ExplorerPanel;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MultiValueSelectBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.Role;
import si.telekom.dis.shared.UserGroup;

public class ManageUsers extends WindowBox {
	String r_object_id;
	private final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	TabPanel tpUsers;
	HashMap<String, ListBox> rolesAndUsers;
	int defaultUserCountRespReceived = 0;

	public ManageUsers(String r_object_id_) {
		
		ArrayList<String> allChecked = new ArrayList<String>();
		for (String r_object_ids_ : ExplorerPanel.getExplorerInstance().getCheckedObjects()) {
			allChecked.add(r_object_ids_);
		}
		if(allChecked.size()==0)
			allChecked.add(this.r_object_id);
		
		setText("Vloge in uporabniki");
		setGlassEnabled(true);

		tpUsers = new TabPanel();

		ScrollPanel sp = new ScrollPanel(tpUsers);
		sp.setWidth("800px");
		sp.setHeight("600px");

		explorerService.getProfileAttributesAndValues(MainPanel.getInstance().loginName,
				MainPanel.getInstance().loginPass, r_object_id_, new AsyncCallback<ProfileAttributesAndValues>() {

					@Override
					public void onSuccess(ProfileAttributesAndValues result) {
						// generate tabs
						refreshUsersAndRoles(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						logger.info(caught.getMessage());
					}
				});

		getOkButton().setEnabled(false);
		getOkButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				HashMap<String, List<String>> roleUsersHm = new HashMap<String, List<String>>();
				for (String roleId : rolesAndUsers.keySet()) {
					ArrayList<String> userGroups = new ArrayList<String>();
					ListBox lb = rolesAndUsers.get(roleId);

					for (int i = 0; i < lb.getItemCount(); i++) {
						String ug = lb.getValue(i);
						String ugName = ug.split("\\|")[0];
						userGroups.add(ugName);
					}
					roleUsersHm.put(roleId, userGroups);
				}

				explorerService.setUsersForRoles(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						allChecked, roleUsersHm, new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								MainPanel.log("setManage users completed succesfully.");
								MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
								ManageUsers.this.hide(true);
							}
						});
			}
		});

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				ManageUsers.this.center();
			}
		});

		sp.setWidth("600px");
		sp.setHeight("600px");
		getContentPanel().add(sp);
	}

	protected void refreshUsersAndRoles(ProfileAttributesAndValues attAndValues) {
		// TODO Auto-generated method stub

		rolesAndUsers = new HashMap<String, ListBox>();
		MainPanel.log("Wait for user description to be retrieved from documentum...");

		for (Role role : attAndValues.profile.roles) {

			if (!role.getId().equalsIgnoreCase("unclassified")) {
				String dqlUsers = "";
				ArrayList<String> usersGroups = new ArrayList<String>();

				if (!MenuPanel.activeExplorerInstance.selectedDocument.isClassified) {
					// unclassified document - lets offer default values for user from profile
					Iterator<UserGroup> dugIt =  role.defaultUserGroups.iterator();
					while(dugIt.hasNext())
					{
						UserGroup ug = dugIt.next();
						String userGroupId = ug.id;
						dqlUsers = dqlUsers + "'" + userGroupId + "',";
						if (userGroupId.equals("dm_world") || userGroupId.equals("dm_group"))
							usersGroups.add(userGroupId);
					}
				} else {
					if (attAndValues.rolesAndUsers.containsKey(role.getId())) {
						for (String userGroupName : attAndValues.rolesAndUsers.get(role.getId())) {
							dqlUsers = dqlUsers + "'" + userGroupName + "',";
							if (userGroupName.equals("dm_world") || userGroupName.equals("dm_group"))
								usersGroups.add(userGroupName);
						}
					}
				}
				if (dqlUsers.length() > 0)
					dqlUsers = dqlUsers.substring(0, dqlUsers.length() - 1);
				else {
					dqlUsers = "'No users defined for object'";
				}				

				VerticalPanel vp = new VerticalPanel();
				final ListBox lb = new ListBox();
				lb.addKeyUpHandler(new KeyUpHandler() {
					@Override
					public void onKeyUp(KeyUpEvent event) {
						// TODO Auto-generated method stub
						int kc = event.getNativeEvent().getKeyCode();
						int kcDelete = KeyCodes.KEY_DELETE;
						if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DELETE) {
							lb.removeItem(lb.getSelectedIndex());
						}
					}
				});
				si.telekom.dis.shared.Attribute attUsers = new si.telekom.dis.shared.Attribute();
				attUsers.isRepeating = true;
//@formatter:off							
				attUsers.dqlValueListDefinition = "select user_name, description from dm_user where 1=1 " + "union "
						+ "select group_name, description from dm_group where 1=1 "
						+ "fixedValues(dm_world, vsi;dm_owner, lastnik; dm_group, skupina)";
//@formatter:on							
				MultiValueSelectBox addUG = new MultiValueSelectBox(attUsers, lb);

				vp.add(addUG);
				tpUsers.add(vp, role.getName());
				rolesAndUsers.put(role.getId(), lb);

				// @formatter:off
				String fullDqlUg = "select user_name, description from dm_user where 1=1 and user_name in (" + dqlUsers
						+ ")" + "union " + "select group_name, description from dm_group where 1=1 and group_name in ("
						+ dqlUsers + ")";
				// @formatter:on

				explorerService.dqlLookup(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						fullDqlUg, new AsyncCallback<List<List<String>>>() {
							@Override
							public void onSuccess(List<List<String>> result) {
								// TODO Auto-generated method stub
								Iterator<List<String>> it = result.iterator();
								while (it.hasNext()) {
									List<String> values = it.next();
									usersGroups.add(values.get(0) + "|" + values.get(1));
								}

								addUG.setValue(usersGroups);

								tpUsers.selectTab(0);
								defaultUserCountRespReceived++;
								// MainPanel.log(defaultUserCountRespReceived + " ?= " +
								// attAndValues.profile.roles.size());
								if (defaultUserCountRespReceived == attAndValues.profile.roles.size() - 1) {
									getOkButton().setEnabled(true);
									MainPanel.log("Wait for user description to be retrieved from documentum...Done.");
								}

							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}
						});
			}
		}

		// Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		// @Override
		// public void execute() {
		//
		// explorerService.deleteObject(MainPanel.getInstance().loginName,
		// MainPanel.getInstance().loginPass, r_object_id_, new
		// AsyncCallback<Void>() {
		// @Override
		// public void onSuccess(Void result) {
		// html.setHTML("Delete succesfull <b>" + r_object_id_ + "</b> deleted
		// sucesfully.");
		// DocumentDelete.this.center();
		// }
		//
		// @Override
		// public void onFailure(Throwable caught) {
		// html.setHTML("Delete unsuccesfull:<br><br>" + caught.getMessage());
		// DocumentDelete.this.center();
		// }
		// });
		// }
		// });
		//
	}

}
