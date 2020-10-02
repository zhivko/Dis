package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.ActionDialogBox;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MultiValueSelectBox;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.Role;
import si.telekom.dis.shared.Template;
import si.telekom.dis.shared.TemplateFolder;
import si.telekom.dis.shared.UserGroup;

public class NewDocument extends ActionDialogBox {
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	protected String classification_id = "";
	public static NewDocument instance;

	int wizardTab = 0;
	int maxWizardTab = 3;
	Button nextB;
	Button previousB;
	TabPanel tpWizard;
	TabPanel tpUsers;

	ListBox profiles;
	ArrayList<FormAttribute> allFaAl;
	Profile prof;

	HashMap<String, ListBox> rolesAndUsers = new HashMap<String, ListBox>();

	ListBox lbTemplates;
	ListBox lbFolderTemplates;

	public NewDocument() {
		super();
		setText("Čarovnik za nov dokument");

		allFaAl = new ArrayList<FormAttribute>();

		tpWizard = new TabPanel();

		tpWizard.add(getClassify(), "Klasifikacija");
		tpWizard.add(new VerticalPanel(), "Predloge");
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

		getPanel().add(tpWizard);

		nextB = new Button("Naprej");
		nextB.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				wizardTab++;
				// refreshTabs();
				// tpWizard.selectTab(wizardTab);
				if (wizardTab == 1) {
					if (profiles.getSelectedIndex() != -1) {
						refreshTemplates();
					}
				} else if (wizardTab == 2) {
					if (profiles.getSelectedIndex() != -1) {
						refreshAttributes();
					}
				} else if (wizardTab == 3) {
					// if(profiles.getSelectedIndex()==-1)
					// {
					// MainPanel.log("Profil ni izbran - izberi klasifikacijski znak");
					// return;
					// }
					// else
					// {
					if (checkMandatoryAttributes()) {
						refreshUsersAndRoles();
						getOkButton().setEnabled(true);
					}
				}

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

		lbTemplates = new ListBox();
		lbTemplates.setVisibleItemCount(10);
		lbTemplates.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (lbTemplates.getSelectedIndex() > -1)
					nextB.setEnabled(true);
			}
		});

		lbFolderTemplates = new ListBox();
		lbFolderTemplates.setVisibleItemCount(10);
		lbFolderTemplates.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (lbFolderTemplates.getSelectedIndex() > -1)
					nextB.setEnabled(true);
			}
		});

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

				String templateObjectNameOrFolder;
				if (lbTemplates.getSelectedIndex() >= 0) {
					templateObjectNameOrFolder = lbTemplates.getItemText(lbTemplates.getSelectedIndex());
					if (templateObjectNameOrFolder.contains("|")) {
						templateObjectNameOrFolder = split(templateObjectNameOrFolder, "|")[0];
					}
				} else {
					templateObjectNameOrFolder = lbFolderTemplates.getSelectedItemText();
				}

				explorerService.newDocument(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, NewDocument.this.prof.id, attributes,
						roleUsersHm, templateObjectNameOrFolder, new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								MainPanel.log(caught.getMessage());
							}

							@Override
							public void onSuccess(String result) {
								MainPanel.log("NewDocument created, object_name: " + result);
							}

						});
			}
		});

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
					dqlUsers = dqlUsers + "'" + ug.id + "',";
					if (ug.id.equals("dm_world") || ug.id.equals("dm_group"))
						usersGroups.add(ug.id);
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
								// addUG.addValueChangeHandler(new
								// ValueChangeHandler<List<String>>() {
								// @Override
								// public void onValueChange(ValueChangeEvent<List<String>>
								// event)
								// {
								// // TODO Auto-generated method stub
								// lb.addItem(event.getValue().get(0));
								// }
								// });

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

		tpWizard.remove(3);
		tpWizard.add(tpUsers, "Uporabniki in vloge");
		tpWizard.selectTab(3);
	}

	protected void refreshTemplates() {
		String profileId = profiles.getSelectedValue();
		nextB.setEnabled(false);


		adminService.getProfile(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, profileId, new AsyncCallback<Profile>() {

			@Override
			public void onSuccess(Profile result) {
				prof = result;
				VerticalPanel vp = new VerticalPanel();
				lbTemplates.clear();
				lbFolderTemplates.clear();

				vp.add(new Label("Predloga:"));
				vp.add(lbTemplates);
				SimplePanel sp = new SimplePanel();
				sp.setHeight("10px");
				vp.add(sp);
				vp.add(new Label("Mapa predlog:"));
				vp.add(lbFolderTemplates);

				for (TemplateFolder f : prof.templateFolderPaths) {
					lbFolderTemplates.addItem(f.folderPath);
				}										
				
				tpWizard.remove(1);
				tpWizard.insert(vp, "Predloge", 1);
				tpWizard.selectTab(1);
				
				
				String object_names = "";
				for (Template t : prof.templates) {
					object_names = object_names + "'" + t.object_name + "',";
				}
				if (object_names.length() > 0) {
					object_names = object_names.substring(0, object_names.length() - 1);

					String dql = "select object_name,mob_short_name from mob_form_template where object_name in (" + object_names + ")";

					try {
						explorerService.dqlLookup(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, dql,
								new AsyncCallback<List<List<String>>>() {
									@Override
									public void onSuccess(List<List<String>> result) {

										Iterator<List<String>> it = result.iterator();
										while (it.hasNext()) {
											List<String> values = it.next();
											lbTemplates.addItem(values.get(0) + "|" + values.get(1));
										}

									}

									@Override
									public void onFailure(Throwable caught) {
										MainPanel.log(caught.getMessage());
									}
								});
					} catch (Exception e) {
						MainPanel.log(e.getMessage());
					}
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				getLogger().info(caught.getMessage());
			}
		});

	}

	private void refreshTabs() {
		nextB.setEnabled(false);

	}

	private Widget getClassify() {
		VerticalPanel classifyVp = new VerticalPanel();
		classifyVp.setStyleName("");

		HorizontalPanel hp = new HorizontalPanel();

		hp.add(new Label("Izberi klasifikacijski znak:"));

		si.telekom.dis.shared.Attribute att = new si.telekom.dis.shared.Attribute();
		att.setDqlValueListDefinition(
				"SELECT tc.\"code\", tc.\"name\" FROM dbo.T_CLASSIFICATION_PLAN tcp, dbo.T_CLASSIFICATION tc WHERE tc.\"classification_plan_id\" = tcp.\"id\" AND tcp.\"name\" = 'KNTS' AND DATE(TODAY) >= valid_from AND ( DATE(TODAY) <= valid_to OR valid_to = '' ) order by tc.code enable (return_top 20)");
		att.isRepeating = false;

		profiles = new ListBox();
		profiles.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				enableDisableButtons();
			}
		});

		MultiValueSelectBox mvsb = new MultiValueSelectBox(att, new ListBox());
		mvsb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				// TODO Auto-generated method stub
				NewDocument.this.classification_id = event.getValue().get(0);

				adminService.getProfilesForClassSign(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, NewDocument.this.classification_id, "newdoc",
						new AsyncCallback<List<Profile>>() {
							@Override
							public void onSuccess(List<Profile> result) {
								// TODO Auto-generated method stub
								profiles.clear();
								for (Profile prof : result) {
									profiles.addItem(prof.name, prof.id);
								}
								enableDisableButtons();
								nextB.setEnabled(true);
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

		hp1.add(new Label("Profil:"));
		hp1.add(profiles);
		classifyVp.add(hp1);

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
		for (si.telekom.dis.shared.Tab tab : prof.tabs) {
			Grid g = new Grid(tab.row, tab.col);
			for (AttributeRoleStateWizards arsw : prof.attributeRolesStatesWizards) {
				if (arsw.wizards.contains("newdoc")) {
					for (si.telekom.dis.shared.Attribute att : arsw.attributes) {

						if (tab.getId().equals(att.tabId)) {
							getLogger().info(att.dcmtAttName);
							FormAttribute fa = new FormAttribute(att);
							fa.setWidth("90%");
							
							g.setWidget(att.row, att.col, fa);
							if (att.dcmtAttName.equals("mob_classification_id")) {
								ArrayList<String> values = new ArrayList<String>();
								values.add(classification_id);
								fa.setValue(values);
							}
							fa.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
								public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.util.List<String>> event) {
									if (checkMandatoryAttributes())
										nextB.setEnabled(true);

									fillDependendAttributes(fa.att.getDcmtAttName());
								};
							});

							allFaAl.add(fa);
						}
					}
					tpAtts.add(g, tab.getParameter());
					break;
				}
			}
		}
		tpAtts.selectTab(0);

		tpWizard.remove(2);
		tpWizard.insert(tpAtts, "Atributi", 2);
		tpWizard.selectTab(2);
		
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
				MainPanel.getPanel().add(new NewDocument());
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
