package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MultiValueSelectBox;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.Role;
import si.telekom.dis.shared.State;
import si.telekom.dis.shared.Tab;
import si.telekom.dis.shared.UserGroup;

public class ClassifyDocument extends WindowBox {
	protected String classification_id = "";
	public static ClassifyDocument instance;
	public String r_object_id;

	int wizardTab = 0;
	int maxWizardTab = 2;
	Button nextB;
	Button previousB;
	TabPanel tpWizard;
	TabPanel tpUsers;

	ListBox profiles;
	ArrayList<FormAttribute> allFaAl;
	Profile prof;

	HashMap<String, ListBox> rolesAndUsers = new HashMap<String, ListBox>();

	ListBox lbStates;
	String state_id;

	public ClassifyDocument(String r_object_id) {
		this();
		this.r_object_id = r_object_id;
	}

	public ClassifyDocument() {
		super();
		setText("Čarovnik za klasifikacijo dokumenta");

		allFaAl = new ArrayList<FormAttribute>();

		tpWizard = new TabPanel();

		tpWizard.add(getClassify(), "Klasifikacija");
		tpWizard.add(new VerticalPanel(), "Atributi");
		tpWizard.add(new VerticalPanel(), "Vloge in uporabniki");

		tpWizard.selectTab(0);
		tpWizard.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				// TODO Auto-generated method stub
				wizardTab = event.getSelectedItem();
				enableDisableButtons();
				refreshTabs();
			}
		});

		getContentPanel().add(tpWizard);

		nextB = new Button("Naprej");
		nextB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				if (wizardTab < maxWizardTab)
					wizardTab++;
				// refreshTabs();
				// tpWizard.selectTab(wizardTab);
				if (wizardTab == 1) {
					if (profiles.getSelectedIndex() != -1) {
						refreshAttributes();
					}
				} else if (wizardTab == 2) {
					if (checkMandatoryAttributes()) {
						refreshUsersAndRoles();
						getOkButton().setEnabled(true);
					}
				}
				enableDisableButtons();
			}

		});
		previousB = new Button("Nazaj");
		previousB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				wizardTab--;
				enableDisableButtons();
				refreshTabs();
				tpWizard.selectTab(wizardTab);
			}
		});
		enableDisableButtons();

		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.add(previousB);
		hpButtons.add(nextB);

		this.getPanel().setCellHorizontalAlignment(hpButtons, HasHorizontalAlignment.ALIGN_CENTER);
		this.getPanel().add(hpButtons);

		// lbStates.setVisibleItemCount(10);
		// lbStates.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// // TODO Auto-generated method stub
		// if (lbStates.getSelectedIndex() > -1)
		// nextB.setEnabled(true);
		// }
		// });

		this.getOkButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				HashMap<String, List<String>> attributes = new HashMap<String, List<String>>();
				for (FormAttribute fa : allFaAl) {
					if (!fa.att.isReadOnly) {
						List<String> values = fa.getValues();
						attributes.put(fa.att.dcmtAttName, values);
					}
				}

				HashMap<String, List<String>> roleUsersHm = new HashMap<String, List<String>>();
				for (String roleId : rolesAndUsers.keySet()) {
					ArrayList<String> userGroups = new ArrayList<String>();
					ListBox lb = rolesAndUsers.get(roleId);

					for (int i = 0; i < lb.getItemCount(); i++) {
						String ug = lb.getItemText(i);
						if (ug.contains("|"))
							ug = split(ug, "|")[0];
						userGroups.add(ug);
					}
					roleUsersHm.put(roleId, userGroups);
				}

				getExplorerService().classifyDocument(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, ClassifyDocument.this.r_object_id,
						ClassifyDocument.this.prof.id, ClassifyDocument.this.state_id, attributes, roleUsersHm, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(Void result) {
								MainPanel.log("NewDocument created, object_name: " + result);
							}

						});
			}
		});

		this.center();

		instance = this;
	}

	protected void refreshUsersAndRoles() {
		// TODO Auto-generated method stub
		tpUsers = new TabPanel();

		rolesAndUsers = new HashMap<String, ListBox>();
		for (Role role : prof.roles) {

			if (!role.getId().equalsIgnoreCase("unclassified")) {
				final ListBox lb = new ListBox();
				String dqlUsers = "";
				ArrayList<String> usersGroups = new ArrayList<String>();

				for (UserGroup ug : role.defaultUserGroups) {
					dqlUsers = dqlUsers + "'" + ug.getId() + "',";
					if (ug.getId().equals("dm_world") || ug.getId().equals("dm_group"))
						usersGroups.add(ug.getId());
				}
				if (dqlUsers.length() > 0)
					dqlUsers = dqlUsers.substring(0, dqlUsers.length() - 1);
				else
					dqlUsers = "'no defaultusers'";

//@formatter:off							
String fullDqlUg = 
			"select user_name, description from dm_user where 1=1 and user_name in (" + dqlUsers +") " +
			"union " +
			"select group_name, description from dm_group where 1=1 and group_name in (" + dqlUsers +") ";
//@formatter:on							

				getExplorerService().dqlLookup(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, fullDqlUg,
						new AsyncCallback<List<List<String>>>() {
							@Override
							public void onSuccess(List<List<String>> result) {
								// TODO Auto-generated method stub
								Iterator<List<String>> it = result.iterator();
								while (it.hasNext()) {
									List<String> values = it.next();
									String line = values.get(0) + "|" + values.get(1);
									usersGroups.add(line);
								}

								VerticalPanel vp = new VerticalPanel();

								si.telekom.dis.shared.Attribute attUsers = new si.telekom.dis.shared.Attribute();
								attUsers.isRepeating = true;
								attUsers.isLimitedToValueList = true;
//@formatter:off							
								attUsers.dqlValueListDefinition = 
									"select user_name, description from dm_user where 1=1 " +
									"union " +
									"select group_name, description from dm_group where 1=1 " +
									"fixedValues(dm_world, vsi;dm_owner, lastnik; dm_group, skupina)";
//@formatter:on							
								MultiValueSelectBox addUG = new MultiValueSelectBox(attUsers, lb);


								addUG.setValue(usersGroups);

								vp.add(addUG);

								tpUsers.add(vp, role.getParameter());
								rolesAndUsers.put(role.getId(), lb);

								tpUsers.selectTab(0);
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}
						});

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
			}
		}

		tpWizard.remove(2);
		tpWizard.add(tpUsers, "Uporabniki in vloge");
		tpWizard.selectTab(2);
	}

	private void refreshTabs() {
		nextB.setEnabled(false);

	}

	private Widget getClassify() {
		VerticalPanel classifyVp = new VerticalPanel();
		classifyVp.setStyleName("");

		HorizontalPanel hp = new HorizontalPanel();

		hp.add(new Label("Izberi klasifikacijski znak: "));

		si.telekom.dis.shared.Attribute att = new si.telekom.dis.shared.Attribute();
		att.setDqlValueListDefinition(
				"SELECT tc.\"code\", tc.\"name\" FROM dbo.T_CLASSIFICATION_PLAN tcp, dbo.T_CLASSIFICATION tc WHERE tc.\"classification_plan_id\" = tcp.\"id\" AND tcp.\"name\" = 'KNTS' AND DATE(TODAY) >= valid_from AND ( DATE(TODAY) <= valid_to OR valid_to = '' ) order by tc.code enable (return_top 20)");
		att.isRepeating = false;

		lbStates = new ListBox();

		profiles = new ListBox();
		profiles.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String profileId = profiles.getSelectedValue();
				getAdminService().getProfile(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, profileId, new AsyncCallback<Profile>() {
					@Override
					public void onSuccess(Profile result) {
						prof = result;
						lbStates.clear();
						state_id = null;

						for (State state : prof.states) {
							if (!state.getId().equals("unclassified"))
								lbStates.addItem(state.getParameter(), state.getId());
						}
						lbStates.setSelectedIndex(0);
						nextB.setEnabled(false);
					}

					@Override
					public void onFailure(Throwable caught) {
						getLogger().info(caught.getMessage());
					}
				});

				enableDisableButtons();
			}
		});

		MultiValueSelectBox mvsb = new MultiValueSelectBox(att, new ListBox());
		mvsb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				// TODO Auto-generated method stub
				ClassifyDocument.this.classification_id = event.getValue().get(0);

				getAdminService().getProfilesForClassSign(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						ClassifyDocument.this.classification_id, "classify", new AsyncCallback<List<Profile>>() {
							@Override
							public void onSuccess(List<Profile> result) {
								// TODO Auto-generated method stub
								profiles.clear();
								for (Profile prof : result) {
									profiles.addItem(prof.name, prof.id);
								}
								lbStates.clear();
								nextB.setEnabled(false);
								profiles.setSelectedIndex(0);
							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								nextB.setEnabled(false);
							}
						});
			}
		});

		hp.add(mvsb);
		classifyVp.add(hp);

		HorizontalPanel hp1 = new HorizontalPanel();
		hp1.add(new Label("Profil: "));
		hp1.add(profiles);
		classifyVp.add(hp1);

		HorizontalPanel hp2 = new HorizontalPanel();
		hp2.add(new Label("Stanje: "));
		hp2.add(lbStates);
		lbStates.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				ClassifyDocument.this.state_id = lbStates.getSelectedValue();
				nextB.setEnabled(true);
			}
		});
		classifyVp.add(hp2);

		return classifyVp;
	}

	protected void enableDisableButtons() {
		nextB.setEnabled(false);
		previousB.setEnabled(false);

		if (wizardTab + 1 > maxWizardTab)
			nextB.setEnabled(false);

		if (wizardTab - 1 < 0)
			previousB.setEnabled(false);
		else
			previousB.setEnabled(true);

	}

	public void refreshAttributes() {
		// TODO Auto-generated method stub
		TabPanel tpAtts = new TabPanel();
		tpAtts.setWidth("800px");

		ScrollPanel sp = new ScrollPanel(tpAtts);
		sp.setWidth("1200px");
		sp.setHeight("900px");
		allFaAl.clear();

		MainPanel.log("Nalagam atribute..");
		getExplorerService().getProfileAttributesAndValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id,
				new AsyncCallback<ProfileAttributesAndValues>() {

					@Override
					public void onSuccess(ProfileAttributesAndValues result) {
						// generate tabs
						for (Tab tab : result.profile.tabs) {
							Grid g = new Grid(tab.row, tab.col);
							getOkButton().setEnabled(false);
							for (Attribute att : result.attributes) {
								if (tab.getId().equals(att.tabId)) {
									// logger.info(att.dcmtAttName);
									FormAttribute fa = new FormAttribute(att);
									fa.setWidth("90%");
									fa.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
										public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.util.List<String>> event) {
											if (checkMandatoryAttributes())
												getOkButton().setEnabled(true);

											fillDependendAttributes(fa.att.getDcmtAttName());
										};
									});
									List<String> values = result.values.get(att.dcmtAttName);
									if (values != null) {
										fa.setValue(values);
									} else {
										MainPanel.log("Attribute value for attribute <strong>" + att.dcmtAttName + "</strong> not received from server.");
										fa.att.isReadOnly = true;
									}
									g.setWidget(att.getRow(), att.getCol(), fa);
									allFaAl.add(fa);
									// logger.info("\tadded to row:" + att.row + " col:" +
									// att.col);
								}
							}
							tpAtts.add(g, tab.getParameter());
						}
						tpAtts.selectTab(0);
						if (checkMandatoryAttributes())
							nextB.setEnabled(true);

						Scheduler.get().scheduleDeferred(new ScheduledCommand() {
							@Override
							public void execute() {
								ClassifyDocument.this.center();
							}
						});
					}

					@Override
					public void onFailure(Throwable caught) {
						getLogger().info(caught.getMessage());
					}
				});

		tpWizard.remove(1);
		tpWizard.insert(tpAtts, "Atributi", 1);
		tpWizard.selectTab(1);

		this.center();

		checkMandatoryAttributes();

	}

	protected void fillDependendAttributes(String dcmtAttName) {
		MainPanel.log("Računam odvisne atributov...");
		for (FormAttribute fa : allFaAl) {
			if (fa.att.dependsOn != null && !fa.att.dependsOn.equals("")) {
				String dpds = fa.att.dependsOn;
				String dependsonAtt = split(fa.att.dependsOn, ",")[0];
				int columnIndex = Integer.valueOf(split(fa.att.dependsOn, ",")[1]);
				FormAttribute dependsOnAtt = findFormAttribute(dependsonAtt);
				String fullRow = dependsOnAtt.getFirstMultiselectRow();
				String value = split(fullRow, "|")[columnIndex];
				ArrayList<String> values = new ArrayList<String>();
				values.add(value);
				fa.setValue(values);
			}
		}
	}

	FormAttribute findFormAttribute(String dctmAttName) {
		for (FormAttribute fa : allFaAl) {
			if (fa.att.dcmtAttName.equalsIgnoreCase(dctmAttName)) {
				return fa;
			}

		}
		return null;
	}

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("Nov dokument");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				MainPanel.getPanel().add(new ClassifyDocument());
			}
		});
		return b;
	}

	public boolean checkMandatoryAttributes() {
		boolean ret = true;
		MainPanel.log("Preverjam obvezne atribute...");
		for (FormAttribute fa : allFaAl) {
			fa.mandatoryOk();
			if (fa.att.isMandatory) {
				String val = fa.getValue();
				if (val.equals("")) {
					MainPanel.log("Obvezni atribut " + fa.att.dcmtAttName + " ni izbran.");
					fa.mandatoryNeeded();
					ret = false;
				}
			}
		}
		return ret;
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

}
