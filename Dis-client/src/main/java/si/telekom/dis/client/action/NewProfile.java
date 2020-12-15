package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragEnterEvent;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
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
import si.telekom.dis.client.MenuPanel;
import si.telekom.dis.client.MultiValueSelectBox;
import si.telekom.dis.client.MyListBox;
import si.telekom.dis.client.MyTxtBox;
import si.telekom.dis.client.ProgressDialog;
import si.telekom.dis.client.Spinner;
import si.telekom.dis.client.item.ActionInProfile;
import si.telekom.dis.client.item.Attribute;
import si.telekom.dis.client.item.ProfileAttribute;
import si.telekom.dis.client.item.Role;
import si.telekom.dis.client.item.StandardAction;
import si.telekom.dis.client.item.State;
import si.telekom.dis.client.item.Tab;
import si.telekom.dis.client.item.UserGroup;
import si.telekom.dis.client.item.VerticalContainer;
import si.telekom.dis.shared.Action;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.Template;
import si.telekom.dis.shared.TemplateFolder;

public class NewProfile extends ActionDialogBox implements ClickHandler {
	public final static AdminServiceAsync adminService = GWT.create(AdminService.class);
	public static NewProfile instance;

	Grid attsStatesRoles = new Grid(); // holding states and roles for atts
	ScrollPanel attsStatesRolesScroll;

	Grid actionsStatesRoles = new Grid(); // holding states and roles for actions
	ScrollPanel actionsStatesRolesScroll;

	Grid wizards = new Grid(); // holding states and roles for which
															// definition of attributes holds
	HashMap<String, Grid> gridAtts = new HashMap<String, Grid>();
	TabPanel tpAtts = new TabPanel();
	boolean selectingTabProgramatically;

	CheckBox cbImport;
	CheckBox cbNewDoc;
	CheckBox cbClassifyDoc;

	Spinner spinnerAtts;

	VerticalContainer<State> states = new VerticalContainer<State>();

	VerticalContainer<Role> roles = new VerticalContainer<Role>() {
		public void selectionChanged() {
			refreshDefaultUserGroups();
		};
	};

	VerticalContainer<Tab> tabs = new VerticalContainer<Tab>();
	VerticalContainer<Action> actions = new VerticalContainer<Action>();
	VerticalContainer<Attribute> detailAttributes = new VerticalContainer<Attribute>();

	VerticalContainer<StandardAction> standardActions = new VerticalContainer<StandardAction>();

	// used for newDocument, classify and import wizard
	VerticalContainer<UserGroup> defaultUserGroups = new VerticalContainer<UserGroup>();

	private List<AttributeRoleStateWizards> attributeRolesStatesWizards = new ArrayList<AttributeRoleStateWizards>();
	private Map<String, Map<String, List<String>>> roleStateActions;
	int attributeRolesStatesWizardsIndex = 0;

	MyTxtBox naziv;
	MyTxtBox id;
	CheckBox isDefaultForType;
	MyTxtBox objType;
	MyTxtBox addTabs;

	MyTxtBox prefix = new MyTxtBox("predpona");
	MyTxtBox sufix = new MyTxtBox("zapona");
	MyTxtBox startCounter = new MyTxtBox("začetna številka");
	MyTxtBox counterPlaces = new MyTxtBox("dolžina števca");

	MyTxtBox barcodeType = new MyTxtBox("tip barkode");

	int progress = 1;
	MyListBox addSa = null;
	private HashMap<String, ProfileAttribute> attTabPosition = new HashMap<String, ProfileAttribute>();

	ListBox lbTemplates;
	ListBox lbFolderTemplates;

	private TabPanel tp;

	public NewProfile(String dcmtType) {
		this();
		this.objType.setValue(dcmtType);
		this.objType.setIsEditable(false);
		attributeRolesStatesWizards = new ArrayList<AttributeRoleStateWizards>();
		attributeRolesStatesWizards.add(new AttributeRoleStateWizards());
	}

	public NewProfile(Profile prof) {
		this();
		naziv.setValue(prof.name);
		id.setValue(prof.id);
		objType.setValue(prof.objType);
		isDefaultForType.setValue(prof.isDefaultForObjectType);

		prefix.setValue(prof.namePolicyPrefix);
		sufix.setValue(prof.namePolicySuffix);
		startCounter.setValue(prof.namePolicyCounterStart);
		counterPlaces.setValue(prof.namePolicyCounterPlaces);

		barcodeType.setValue(String.valueOf(prof.namePolicyBarcodeType));

		for (si.telekom.dis.shared.Role role : prof.roles) {
			roles.addItem(new Role(role, roles));
		}
		for (si.telekom.dis.shared.State state : prof.states) {
			State sta = new State(state, states);
			states.addItem(sta);
		}

		for (si.telekom.dis.shared.Tab tab : prof.tabs) {
			Tab myTab = new Tab(tab, tabs) {
				@Override
				public void selectedHasChanged() {
					selectingTabProgramatically = true;
					int selectedTabInd = tabs.getSelectedIndex();
					NewProfile.this.tpAtts.getTabBar().selectTab(selectedTabInd);
					selectingTabProgramatically = false;
				};
			};

			tabs.addItem(myTab);
			// myTab.item = tab;
			// if (tab.row != null)
			// addTab(myTab, tab.row.intValue(), tab.col.intValue());
			// else
			// addTab(myTab, 6, 6);
		}
		attributeRolesStatesWizards = prof.attributeRolesStatesWizards;
		roleStateActions = prof.roleStateActions;
		refreshAttsStatesRoles();

		for (si.telekom.dis.shared.Attribute a : prof.detailAttributes) {
			Attribute att = new Attribute(a.dcmtAttName, detailAttributes);
			detailAttributes.addItem(att);
		}

		String dqlTemplates = "";
		for (si.telekom.dis.shared.Template t : prof.templates) {
			dqlTemplates = dqlTemplates + "'" + t.object_name + "',";
		}

		if (dqlTemplates.length() > 0) {
			dqlTemplates = dqlTemplates.substring(0, dqlTemplates.length() - 1);

			String dqlTemplatesFull = "select object_name, mob_short_name from mob_form_template where object_name in (" + dqlTemplates + ")";

			getExplorerService().dqlLookup(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, dqlTemplatesFull,
					new AsyncCallback<List<List<String>>>() {

						@Override
						public void onSuccess(List<List<String>> result) {
							// TODO Auto-generated method stub
							for (List<String> list : result) {
								String line = "";
								for (String attValue : list) {
									line = line + attValue + "|";
								}
								if (line.length() > 0)
									line = line.substring(0, line.length() - 1);
								lbTemplates.addItem(line);
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							MainPanel.log(caught.getMessage());
						}
					});
		}

		String dqlFolderTemplates = "";
		for (si.telekom.dis.shared.TemplateFolder t : prof.templateFolderPaths) {
			dqlFolderTemplates = dqlFolderTemplates + "'" + t.folderPath + "',";
		}

		if (dqlFolderTemplates.length() > 0) {
			dqlFolderTemplates = dqlFolderTemplates.substring(0, dqlFolderTemplates.length() - 1);

			String dqlTemplatesFull = "select r_folder_path, object_name, r_object_id from dm_folder where any r_folder_path in (" + dqlFolderTemplates
					+ ")";

			getExplorerService().dqlLookup(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, dqlTemplatesFull,
					new AsyncCallback<List<List<String>>>() {
						@Override
						public void onSuccess(List<List<String>> result) {
							// TODO Auto-generated method stub
							for (List<String> list : result) {
								lbFolderTemplates.addItem(list.get(0));
							}
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							MainPanel.log(caught.getMessage());
						}
					});
		}

		spinnerAtts.setMax(attributeRolesStatesWizards.size());

		resize();
	}

	public NewProfile() {
		super();

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				resize();
			}
		});

		setText("Profil");
		setAnimationEnabled(false);
		setGlassEnabled(true);

		VerticalPanel p = new VerticalPanel();

		lbTemplates = new ListBox();
		lbTemplates.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DELETE) {
					lbTemplates.removeItem(lbTemplates.getSelectedIndex());
				}
			}
		});

		lbFolderTemplates = new ListBox();
		lbFolderTemplates.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DELETE) {
					lbFolderTemplates.removeItem(lbFolderTemplates.getSelectedIndex());
				}
			}
		});

		naziv = new MyTxtBox("Naziv");
		id = new MyTxtBox("Id");
		objType = new MyTxtBox("Objektni tip");

		p.add(naziv);
		p.add(id);
		p.add(objType);

		isDefaultForType = new CheckBox();
		isDefaultForType.setText("Privzet profil za dokumentni tip");
		p.add(isDefaultForType);

		tp = new TabPanel();

		getWizardsConfig();

		tp.add(getNamePolicy(), "Poimenovanje");
		tp.add(getTemplatesPolicy(), "Predloge");
		tp.add(getAttDetails(), "Detajli");
		tp.add(getRolesPolicy(), "Vloge");
		tp.add(getStatesPolicy(), "Stanja");
		tp.add(getAttsPolicy(), "Atributi");
		tp.add(getActionsPolicy(), "Akcije");

		tabs.setHeight("150px");
		tp.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				// TODO Auto-generated method stub
				resize();
			}

		});

		tp.selectTab(0);

		HorizontalPanel buttonPanel = new HorizontalPanel();
		Button okButton = new Button("Shrani");
		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				saveProfile();
			}

		});
		buttonPanel.add(okButton);
		Button deleteButton = new Button("Briši");
		deleteButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.deleteProfile(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, NewProfile.this.id.getValue());
			}

		});
		buttonPanel.add(deleteButton);

		Button cancelButton = new Button("Prekini");
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NewProfile.this.hide();
			}

		});
		buttonPanel.add(cancelButton);

		p.add(tp);
		p.add(buttonPanel);

		standardActions.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
					deleteStandardActionFromSelectedState(standardActions.getSelectedIndex());
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
					standardActions.moveUp();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					standardActions.moveDown();
				}
			}
		});

		defaultUserGroups.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
					deleteUserGroupFromSelectedRole(defaultUserGroups.getSelectedIndex());
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
					defaultUserGroups.moveUp();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					defaultUserGroups.moveDown();
				}
			}

		});

		tabs.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
					removeTab(tabs.getSelectedIndex());
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
					tabs.moveUp();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					tabs.moveDown();
				}
			}
		});

		actionsStatesRoles.setBorderWidth(1);
		wizards.setBorderWidth(1);

		setWidget(p);
		instance = this;
	}

	private Widget getTemplatesPolicy() {
		VerticalPanel vp = new VerticalPanel();

		si.telekom.dis.shared.Attribute att = new si.telekom.dis.shared.Attribute();
		att.isRepeating = true;

		//@formatter:off							
				att.dqlValueListDefinition = 
					"select object_name, mob_short_name from mob_form_template where 1=1 order by object_name";
		//@formatter:on							

		MultiValueSelectBox mvsb = new MultiValueSelectBox(att, lbTemplates);
		mvsb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				MainPanel.log(event.getValue().get(mvsb.getValue().size() - 1));
			}
		});

		vp.add(mvsb);
		vp.add(new Label("Template document barcode"));
		SimplePanel vs = new SimplePanel();
		vs.setHeight("10px");
		vp.add(vs);
		vp.add(lbTemplates);

		SimplePanel vs2 = new SimplePanel();
		vs2.setHeight("10px");
		vp.add(vs2);

		si.telekom.dis.shared.Attribute attFolder = new si.telekom.dis.shared.Attribute();
//@formatter:off							
		attFolder.dqlValueListDefinition = 
			"select object_name, r_folder_path from dm_folder where 1=1 and folder('/MOB/Standard/Template/eForm') order by object_name";
//@formatter:on		
		attFolder.isRepeating = true;
		attFolder.dropDownCol = 1;
		MultiValueSelectBox mvsb2 = new MultiValueSelectBox(attFolder, lbFolderTemplates);
		mvsb.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				MainPanel.log(event.getValue().get(mvsb.getValue().size() - 1));
			}
		});

		vp.add(mvsb2);
		vp.add(new Label("Template folder path"));
		SimplePanel vs3 = new SimplePanel();
		vs3.setHeight("10px");
		vp.add(vs3);
		vp.add(lbFolderTemplates);

		return vp;
	}

	private Widget getAttDetails() {
		// TODO Auto-generated method stub
		VerticalPanel vp = new VerticalPanel();
		Label lb = new Label("Drag & drop attributes to container below.");
		vp.add(lb);

		// detailAttributes.addDomHandler(new DragOverHandler() {
		//
		// @Override
		// public void onDragOver(DragOverEvent event) {
		// // required
		// event.preventDefault();
		// for (int i = 0; i < actionsStatesRoles.getRowCount(); i++)
		// for (int j = 0; j < actionsStatesRoles.getColumnCount(); j++) {
		// Widget w = actionsStatesRoles.getWidget(i, j);
		// if (w != null) {
		// // logger.info(w.getClass().getName());
		// if (w.getClass().getName().endsWith("VerticalPanel"))
		// ((VerticalPanel) w).setBorderWidth(0);
		// }
		// }
		// detailAttributes.setBorderWidth(1);
		// }
		// }, DragOverEvent.getType());

		FocusPanel fp = new FocusPanel(detailAttributes);

		fp.addDragEnterHandler(new DragEnterHandler() {

			@Override
			public void onDragEnter(DragEnterEvent event) {
				// TODO Auto-generated method stub
				// getLogger().info("DRAG ENTER");
			}
		});

		fp.addDragOverHandler(new DragOverHandler() {

			@Override
			public void onDragOver(DragOverEvent event) {
				// getLogger().info("DRAG OVER");
			}
		});

		fp.addDropHandler(new DropHandler() {

			@Override
			public void onDrop(DropEvent event) {
				// targetting exactly the panel (e.g. leaving the panel, not one of
				// its children)
				// event.preventDefault();
				// get the data out of the event
				if (event.getData("text").split(":")[0].equals("attribute")) {
					String attributeData = event.getData("text").split(":")[1];
					String attName = attributeData.split("\\.")[1];
					Attribute att = new Attribute(attName, detailAttributes);
					detailAttributes.addItem(att);

					// logger.info("ClassName: " +
					// event.getSource().getClass().getName());
				}
			}
		});

		detailAttributes.setHeight("500px");
		detailAttributes.setWidth("400px");

		vp.add(fp);

		return vp;
	}

	private void getWizardsConfig() {
		wizards.resize(3, 2);
		wizards.setBorderWidth(1);

		cbImport = new CheckBox();
		cbImport.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
				if (cbImport.getValue())
					arsw.wizards.add("import");
				else
					arsw.wizards.remove("import");
			}
		});

		cbNewDoc = new CheckBox();
		cbNewDoc.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
				if (cbNewDoc.getValue())
					arsw.wizards.add("newdoc");
				else
					arsw.wizards.remove("newdoc");
			}
		});
		cbClassifyDoc = new CheckBox();
		cbClassifyDoc.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
				if (cbNewDoc.getValue())
					arsw.wizards.add("classify");
				else
					arsw.wizards.remove("classify");
			}
		});

		cbImport.getElement().setId("wizardImport");
		cbNewDoc.getElement().setId("wizardNewDocument");
		cbImport.getElement().setId("wizardClassifyDocument");

		wizards.setWidget(0, 0, new Label("Uvoz"));
		wizards.setWidget(0, 1, cbImport);
		wizards.setWidget(1, 0, new Label("Nov dokument"));
		wizards.setWidget(1, 1, cbNewDoc);
		wizards.setWidget(2, 0, new Label("Klasificiraj"));
		wizards.setWidget(2, 1, cbClassifyDoc);
	}

	private Widget getStatesPolicy() {
		// TODO Auto-generated method stub
		HorizontalPanel statesPolicy = new HorizontalPanel();

		VerticalPanel statesPolicy1 = new VerticalPanel();
		statesPolicy.add(statesPolicy1);

		VerticalPanel statesPolicySaDetails = new VerticalPanel();
		statesPolicy.add(statesPolicySaDetails);

		states.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
					states.removeItem(states.getSelectedIndex());
					NewProfile.this.refreshAttsStatesRoles();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
					states.moveUp();
					NewProfile.this.refreshAttsStatesRoles();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					states.moveDown();
					NewProfile.this.refreshAttsStatesRoles();
				}
			}
		});

		MyTxtBox addState = new MyTxtBox("dodaj stanje") {
			@Override
			public void enterPressed() {
				// TODO Auto-generated method stub
				State state = new State(this.getValue(), this.getValue(), states) {
					@Override
					protected void nameChanged() {
						// TODO Auto-generated method stub
						refreshAttsStatesRoles();
						super.nameChanged();
					}
				};
				states.addItem(state);
				NewProfile.this.refreshAttsStatesRoles();
				this.getTextBox().setText("");
			}
		};

		addSa = new MyListBox("dodaj standarno akcijo") {
			@Override
			public void enterPressed() {
				// TODO Auto-generated method stub
				if (states.getSelectedIndex() >= 0) {
					State state = (State) states.getItem(states.getSelectedIndex());
					StandardAction sa = new StandardAction(standardActions);
					si.telekom.dis.shared.StandardAction saItem = (si.telekom.dis.shared.StandardAction) sa.item;
					sa.setKind(addSa.getValue());

					si.telekom.dis.shared.State sta = (si.telekom.dis.shared.State) (state.item);
					sta.standardActions.add((si.telekom.dis.shared.StandardAction) sa.item);
				} else {
					MainPanel.log("Nobeno stanje ni izbrano - izberi stanje.");
				}
			}
		};
		for (si.telekom.dis.shared.StandardAction.types sa : si.telekom.dis.shared.StandardAction.types.values()) {
			addSa.getListBox().addItem(sa.type);
		}

		statesPolicy1.setWidth("100%");
		statesPolicy1.add(addState);
		statesPolicy1.add(states);
		states.setWidth("100%");

		statesPolicySaDetails.setWidth("100%");
		statesPolicySaDetails.add(addSa);
		addSa.setWidth("100%");
		statesPolicySaDetails.add(standardActions);
		standardActions.setWidth("100%");

		return statesPolicy;
	}

	private Widget getRolesPolicy() {
		HorizontalPanel rolePolicy = new HorizontalPanel();

		// roles.setVisibleItemCount(10);
		roles.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				int selectedInd = roles.getSelectedIndex();
				if (event.getNativeKeyCode() == KeyCodes.KEY_DELETE) {
					roles.removeItem(selectedInd);
					NewProfile.this.refreshAttsStatesRoles();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_UP) {
					roles.moveUp();
					NewProfile.this.refreshAttsStatesRoles();
				} else if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					roles.moveDown();
					NewProfile.this.refreshAttsStatesRoles();
				}
			}
		});

		MyTxtBox addRole = new MyTxtBox("dodaj vlogo") {
			@Override
			public void enterPressed() {
				// TODO Auto-generated method stub
				String id = split(this.getValue(), "\\|")[0];
				String name = split(this.getValue(), "\\|")[1];
				si.telekom.dis.shared.Role rol = new si.telekom.dis.shared.Role(id, name);
				Role role = new Role(rol, roles) {
					@Override
					protected void nameChanged() {
						// TODO Auto-generated method stub
						refreshAttsStatesRoles();
						super.nameChanged();
					}
				};
				roles.addItem(role);
				NewProfile.this.refreshAttsStatesRoles();
				this.getTextBox().setText("");
			}
		};

		VerticalPanel vp1 = new VerticalPanel();
		vp1.add(addRole);
		vp1.add(roles);
		rolePolicy.add(vp1);

		si.telekom.dis.shared.Attribute attUsers = new si.telekom.dis.shared.Attribute();
		attUsers.isRepeating = false;
//@formatter:off							
		attUsers.dqlValueListDefinition = 
			"select user_name, description from dm_user where 1=1 " +
			"union " +
			"select group_name, description from dm_group where 1=1 " + 
			"fixedValues(dm_world, vsi;dm_owner, lastnik; dm_group, skupina)";
//@formatter:on							
		MultiValueSelectBox addDefaultUG = new MultiValueSelectBox(attUsers, new ListBox());
		addDefaultUG.addValueChangeHandler(new ValueChangeHandler<List<String>>() {

			@Override
			public void onValueChange(ValueChangeEvent<List<String>> event) {
				if (roles.getSelectedIndex() >= 0) {
					String id = split(event.getValue().get(0), "|")[0];
					String name = split(event.getValue().get(0), "|")[1];

					si.telekom.dis.shared.UserGroup ug_item = new si.telekom.dis.shared.UserGroup(id, name);
					UserGroup ug = new UserGroup(ug_item, defaultUserGroups);
					defaultUserGroups.addItem(ug);

					Role role = (Role) roles.getItem(roles.getSelectedIndex());
					si.telekom.dis.shared.Role rol = (si.telekom.dis.shared.Role) (role.item);
					rol.defaultUserGroups.add(ug_item);
				} else {
					MainPanel.log("Nobena vloga ni izbrana. Izberi vlogo.");
				}
			}
		});

		VerticalPanel vp2 = new VerticalPanel();
		vp2.add(addDefaultUG);
		vp2.add(new Label("dodaj uporabnika/grupo"));
		SimplePanel space = new SimplePanel();
		space.setHeight("5px");
		vp2.add(space);
		vp2.add(new Label("privzeti uporabniki/grupe"));
		vp2.add(defaultUserGroups);
		rolePolicy.add(vp2);

		return rolePolicy;
	}

	private Widget getNamePolicy() {
		VerticalPanel namePolicy = new VerticalPanel();
		namePolicy.setStyleName("");
		namePolicy.add(prefix);
		namePolicy.add(sufix);
		namePolicy.add(startCounter);
		namePolicy.add(counterPlaces);

		namePolicy.add(barcodeType);

		return namePolicy;
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Button b = new Button();
		b.setWidth("100px");
		b.setHeight("30px");
		b.setText("Gumb");
		b.setStyleName("gwt-Button");
		MainPanel.getPanel().add(b, Random.nextInt(2000), Random.nextInt(2000));
	}

	public static Widget getMenuItem() {
		// TODO Auto-generated method stub
		Button b = new Button("New profile");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MainPanel.clearPanel();
				NewProfile np;
				if (!MenuPanel.selectedDcmtType.equals(""))
					np = new NewProfile(MenuPanel.selectedDcmtType);
				else
					np = new NewProfile();

				MainPanel.getPanel().add(np);
				// np.setHeight("100%");
			}
		});
		return b;
	}

	private Widget getAttsPolicy() {
		// TODO Auto-generated method stub
		attsStatesRoles.setBorderWidth(1);

		VerticalPanel attsPolicy = new VerticalPanel();

		HorizontalPanel hp1 = new HorizontalPanel();
		attsStatesRolesScroll = new ScrollPanel(attsStatesRoles);

		addTabs = new MyTxtBox("dodaj tab") {
			@Override
			public void enterPressed() {
				// TODO Auto-generated method stub
				si.telekom.dis.shared.Tab tab = new si.telekom.dis.shared.Tab(this.getTextBox().getText(), this.getTextBox().getText());
				tab.row = 3;
				tab.col = 1;
				Tab myTab = new Tab(tab, tabs) {
					@Override
					protected void nameChanged() {
						tpAtts.getTabBar().setTabText(tpAtts.getTabBar().getSelectedTab(), this.getName());
					};
				};

				addTab(myTab, 2, 2);
				this.getTextBox().setText("");
			}
		};

		VerticalPanel tabsAddVp = new VerticalPanel();
		tabsAddVp.add(addTabs);
		tabsAddVp.add(tabs);

		hp1.add(tabsAddVp);

		VerticalPanel rightSideVp = new VerticalPanel();
		rightSideVp.add(new Label("Čarovniki"));
		rightSideVp.add(wizards);
		HorizontalPanel space1 = new HorizontalPanel();
		space1.setHeight("10px");
		rightSideVp.add(space1);
		rightSideVp.add(new Label("Atributi za stanja in vloge"));
		rightSideVp.add(attsStatesRolesScroll);

		HorizontalPanel space2 = new HorizontalPanel();
		space2.setHeight("10px");
		rightSideVp.add(space2);

		spinnerAtts = new Spinner(1, 1) {
			@Override
			public void removed(int which) {
				if (which >= 2) {
					if (attributeRolesStatesWizardsIndex == which - 1)
						attributeRolesStatesWizardsIndex = which - 2;
					attributeRolesStatesWizards.remove(which - 1);

					refreshAttsStatesRoles();
				}
			};

			@Override
			public void added(int which) {
				attributeRolesStatesWizardsIndex = value - 1;

				attributeRolesStatesWizards.add(new AttributeRoleStateWizards());
				cbNewDoc.setValue(false);
				cbImport.setValue(false);
				cbClassifyDoc.setValue(false);

				refreshAttsStatesRoles();
			};

			@Override
			public void valueChanged() {
				AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
				for (si.telekom.dis.shared.Attribute a : arsw.attributes) {
					if (a != null)
						getLogger().info("\tAtt: " + a.dcmtAttName + " tab: " + a.tabId + " (r:" + a.row + ",c:" + a.col + ")");
				}
				getLogger().info("Size(" + attributeRolesStatesWizardsIndex + ")=" + arsw.attributes.size());

				attributeRolesStatesWizardsIndex = spinnerAtts.value - 1;
				arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
				for (si.telekom.dis.shared.Attribute a : arsw.attributes) {
					if (a != null)
						getLogger().info("\tAtt: " + a.dcmtAttName + " tab: " + a.tabId + " (r:" + a.row + ",c:" + a.col + ")");
				}
				getLogger().info("Size(" + attributeRolesStatesWizardsIndex + ")=" + arsw.attributes.size());

				refreshAttsStatesRoles();
			}
		};
		rightSideVp.add(spinnerAtts);

		hp1.add(rightSideVp);

		attsPolicy.add(hp1);
		HorizontalPanel space = new HorizontalPanel();
		space.setHeight("10px");
		attsPolicy.add(space);
		attsPolicy.add(tpAtts);
		tpAtts.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				// TODO Auto-generated method stub
				if (!NewProfile.this.selectingTabProgramatically) {
					getLogger().info("Selected tab: " + tpAtts.getTabBar().getSelectedTab());
					((Tab) tabs.getItem(tpAtts.getTabBar().getSelectedTab())).setIsSelected(true);
					resize();
				}
			}
		});

		return attsPolicy;
	}

	protected void addTab(Tab myTab, int row, int col) {
		tabs.addItem(myTab);
		myTab.setIsSelected(true);

		for (int i = 0; i < (spinnerAtts.max - spinnerAtts.min) + 1; i++) {
			Grid g = new Grid(row, col);
			gridAtts.put(i + "~" + myTab.getId(), g);

			VerticalPanel vp = new VerticalPanel();

			// MyTxtBox nazivTaba = new MyTxtBox("naziv") {
			// @Override
			// public void enterPressed() {
			// tpAtts.getTabBar().setTabText(tpAtts.getTabBar().getSelectedTab(),
			// this.getValue());
			// };
			// };
			// nazivTaba.getTextBox().setText(this.getValue());

			ScrollPanel spG = new ScrollPanel(g);
			vp.add(spG);

			tpAtts.add(vp, myTab.getName());
			tpAtts.selectTab(tabs.getItemCount() - 1);
		}
	}

	public void refreshAttsStatesRoles() {

		GWT.log("updateAttsStatesRoles...");

		attsStatesRoles.resize(roles.getItemCount() + 1, states.getItemCount() + 1);
		actionsStatesRoles.resize(roles.getItemCount() + 1, states.getItemCount() + 1);

		for (int i = 0; i < states.getItemCount(); i++) {
			State st = (State) states.getItem(i);

			Label lbl = new Label();
			lbl.setText(st.getName());
			lbl.getElement().setId("statesRoles~state:" + st.getId());

			Label lbl1 = new Label();
			lbl1.setText(st.getName());
			lbl1.getElement().setId("statesRoles~state:" + st.getId());

			attsStatesRoles.setWidget(0, i + 1, lbl);
			actionsStatesRoles.setWidget(0, i + 1, lbl1);
		}

		for (int i = 0; i < roles.getItemCount(); i++) {
			Role role = (Role) roles.getItem(i);

			Label lbl = new Label();
			lbl.setText(role.getName());
			lbl.getElement().setId("statesRoles~role:" + role.getId());

			Label lbl1 = new Label();
			lbl1.setText(role.getName());
			lbl1.getElement().setId("statesRoles~state:" + role.getId());

			attsStatesRoles.setWidget(i + 1, 0, lbl1);
			actionsStatesRoles.setWidget(i + 1, 0, lbl);
		}

		for (int i = 0; i < states.getItemCount(); i++) {
			State st = (State) states.getItem(i);
			for (int j = 0; j < roles.getItemCount(); j++) {
				Role rl = (Role) roles.getItem(j);

				CheckBox cb = new CheckBox();
				cb.getElement().setId("attsInStateRole:" + st.getId() + "~" + rl.getId());
				attsStatesRoles.setWidget(j + 1, i + 1, cb);
				cb.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						AttributeRoleStateWizards arsw1 = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
						String stateRole = cb.getElement().getId().split(":")[1];
						String stateId = stateRole.split("~")[0];
						String roleId = stateRole.split("~")[1];
						if (!arsw1.stateRole.containsKey(stateId))
							arsw1.stateRole.put(stateId, new ArrayList<>());
						List<String> roleIds = arsw1.stateRole.get(stateId);

						if (cb.getValue())
							roleIds.add(roleId);
						else
							roleIds.remove(roleId);

					}
				});

				// read configuration and mark wizards states roles checkboxes
				AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
				List<String> roleIds = arsw.stateRole.get(st.item.getId());
				if (roleIds != null)
					if (roleIds.contains(rl.item.getId()))
						cb.setValue(true);
					else
						cb.setValue(false);

				VerticalPanel vpActionsInStateRole = new VerticalPanel();
				FocusPanel sp = new FocusPanel();
				vpActionsInStateRole.add(sp);
				sp.setHeight("100px");
				sp.setWidth("100px");
				vpActionsInStateRole.setCellWidth(sp, "100%");
				vpActionsInStateRole.setCellHeight(sp, "100%");

				vpActionsInStateRole.getElement().setId("actionsInStateRole:" + st.getId() + "~" + rl.getId());
				actionsStatesRoles.setWidget(j + 1, i + 1, vpActionsInStateRole);

				if (roleStateActions != null && roleStateActions.get(st.getId()) != null) {
					String stateId = st.getId();
					Map<String, List<String>> actionsForRoleInState = roleStateActions.get(stateId);
					List<String> actions = actionsForRoleInState.get(rl.getId());
					if (actions != null)
						if (actions.size() > 0) {
							vpActionsInStateRole.remove(0);
							for (String actionId : actions) {
								addAction(actionId, vpActionsInStateRole);
							}
						}
				}

				vpActionsInStateRole.addDomHandler(new DragOverHandler() {

					@Override
					public void onDragOver(DragOverEvent event) {
						// required
						event.preventDefault();
						for (int i = 0; i < actionsStatesRoles.getRowCount(); i++)
							for (int j = 0; j < actionsStatesRoles.getColumnCount(); j++) {
								Widget w = actionsStatesRoles.getWidget(i, j);
								if (w != null) {
									// logger.info(w.getClass().getName());
									if (w.getClass().getName().endsWith("VerticalPanel"))
										((VerticalPanel) w).setBorderWidth(0);
								}
							}
						vpActionsInStateRole.setBorderWidth(1);
					}
				}, DragOverEvent.getType());

				vpActionsInStateRole.addDomHandler(new DropHandler() {
					@Override
					public void onDrop(DropEvent event) {
						// required
						event.preventDefault();

						// get the data out of the event
						String actionData = event.getData("text");
						getLogger().info("Dropped: " + actionData);
						if (actionData.split(":")[0].equals("action")) {

							if (!vpActionsInStateRole.getWidget(0).getClass().getName().endsWith("ActionInProfile"))
								vpActionsInStateRole.remove(0);

							String actionId = actionData.split(";")[0];
							String actionId2 = actionId.split("=")[1];
							addAction(actionId2, vpActionsInStateRole);
						}
					}
				}, DropEvent.getType());

			}
		}

		// fill atributes
		while (tpAtts.getWidgetCount() > 0)
			tpAtts.remove(0);

		gridAtts.clear();
		if (attributeRolesStatesWizards.size() > 0) {
			for (int i = 0; i < attributeRolesStatesWizards.size(); i++) {
				AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(i);

				if (i == spinnerAtts.value - 1) {
					if (arsw.wizards.contains("newdoc"))
						cbNewDoc.setValue(true);
					else
						cbNewDoc.setValue(false);

					if (arsw.wizards.contains("classify"))
						cbClassifyDoc.setValue(true);
					else
						cbClassifyDoc.setValue(false);

					if (arsw.wizards.contains("import"))
						cbImport.setValue(true);
					else
						cbImport.setValue(false);
				}

				int tabIndex = 0;
				for (Object tabObj : tabs.getArrayList()) {
					si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) tabObj;

					if (i == spinnerAtts.value - 1) {
						Grid g = new Grid(tab.row, tab.col);
						gridAtts.put(i + "~" + tab.getId(), g);

						VerticalPanel vp = new VerticalPanel();

						ScrollPanel spG = new ScrollPanel(g);
						spG.setSize("1200px", "500px");
						vp.add(spG);

						tpAtts.add(vp, tab.getParameter());
						// if (tpAtts.getTabBar().getTabCount() - 1 >=
						// tabs.getSelectedIndex())
						// tpAtts.selectTab(tabs.getSelectedIndex());

						for (int j = 0; j < g.getRowCount(); j++)
							for (int k = 0; k < g.getColumnCount(); k++) {
								Widget w = g.getWidget(j, k);
								if (w != null)
									g.remove(w);
								VerticalPanel emptyPanel = getEmptyPanel(j, k);
								g.setWidget(j, k, emptyPanel);
							}

						for (si.telekom.dis.shared.Attribute a : arsw.attributes) {
							if (a != null && a.tabId.equals(tab.getId())) {
								if (g != null && (a.row <= g.getRowCount() - 1 && a.col <= g.getColumnCount() - 1)) {
									VerticalPanel vpEmptyPanel = (VerticalPanel) g.getWidget(a.row, a.col);

									ProfileAttribute pa = new ProfileAttribute(a);

									vpEmptyPanel.remove(0);
									vpEmptyPanel.add(pa);
									String key = i + "~" + a.tabId + "~" + a.row + "~" + a.col;
									attTabPosition.put(key, pa);
								}
							}
						}
						tpAtts.selectTab(tabIndex);

					}
					tabIndex++;
				}
			}
		}
		GWT.log("updateAttsStatesRoles...Done.");

	}

	private void addAction(String actionId, VerticalPanel vpActionsInStateRole) {
		adminService.getActionById(actionId, new AsyncCallback<Action>() {
			@Override
			public void onSuccess(Action action) {
				ActionInProfile actInProf = new ActionInProfile(action);
				vpActionsInStateRole.add(actInProf);
			}

			@Override
			public void onFailure(Throwable caught) {
				GWT.log(caught.getMessage());
			}
		});
	}

	private Widget getActionsPolicy() {
		// TODO Auto-generated method stub
		VerticalPanel actionsPolicy = new VerticalPanel();

		actionsPolicy.add(new Label("Akcije za stanja in vloge"));
		actionsStatesRolesScroll = new ScrollPanel(actionsStatesRoles);
		actionsPolicy.add(actionsStatesRolesScroll);

		return actionsPolicy;
	}

	public void saveProfile() {
		Profile p = new Profile();

		p.id = this.id.getValue();
		p.name = this.naziv.getValue();
		p.objType = this.objType.getValue();
		p.isDefaultForObjectType = isDefaultForType.getValue();

		p.namePolicyPrefix = prefix.getValue();
		p.namePolicySuffix = sufix.getValue();
		p.namePolicyCounterStart = startCounter.getValue();
		p.namePolicyCounterPlaces = counterPlaces.getValue();
		if (!barcodeType.getValue().equals(""))
			p.namePolicyBarcodeType = Integer.valueOf(barcodeType.getValue());

		p.roles = roles.getArrayList();
		p.states = states.getArrayList();
		p.tabs = tabs.getArrayList();

		p.attributeRolesStatesWizards = attributeRolesStatesWizards;

		for (si.telekom.dis.shared.Attribute a : attributeRolesStatesWizards.get(0).attributes) {
			getLogger().info(a.dcmtAttName + " " + a.getType());
		}

		p.roleStateActions = new HashMap<String, Map<String, List<String>>>();
		for (int i = 1; i < actionsStatesRoles.getColumnCount(); i++) {
			si.telekom.dis.shared.State state = (si.telekom.dis.shared.State) states.getItem(i - 1).item;
			HashMap<String, List<String>> hmRoleActionsInState = new HashMap<String, List<String>>();
			for (int j = 1; j < actionsStatesRoles.getRowCount(); j++) {
				si.telekom.dis.shared.Role role = (si.telekom.dis.shared.Role) (roles.getItem(j - 1).item);
				ArrayList<String> actions = new ArrayList<String>();
				VerticalPanel vp = (VerticalPanel) actionsStatesRoles.getWidget(j, i);
				int wCount = vp.getWidgetCount();
				if (wCount >= 1) {
					for (int k = 0; k < vp.getWidgetCount(); k++) {
						Widget w = vp.getWidget(k);
						String className = w.getClass().getName();
						if (className.endsWith("ActionInProfile")) {
							ActionInProfile actInProf = (ActionInProfile) w;
							actions.add(actInProf.item.getId());
						}
					}
					hmRoleActionsInState.put(role.getId(), actions);
				}
			}
			p.roleStateActions.put(state.getId(), hmRoleActionsInState);
		}

		// Map<String, Map<String, List<String>>>

		p.detailAttributes = detailAttributes.getArrayList();

		p.templates = new ArrayList<Template>();
		for (int i = 0; i < lbTemplates.getItemCount(); i++) {

			String objectName = lbTemplates.getValue(i);
			if (objectName.contains("|"))
				objectName = objectName.split("\\|")[0];

			Template t = new Template(objectName);
			p.templates.add(t);
		}

		p.templateFolderPaths = new ArrayList<TemplateFolder>();
		for (int i = 0; i < lbFolderTemplates.getItemCount(); i++) {

			String folderPath = lbFolderTemplates.getItemText(i);
			if (folderPath.contains("|"))
				folderPath = folderPath.split("\\|")[0];

			TemplateFolder folder = new TemplateFolder(folderPath);
			p.templateFolderPaths.add(folder);
		}

		String loginName = MainPanel.getInstance().loginName;
		String loginPass = MainPanel.getInstance().loginPass;

		// ProgressDialog pd = new ProgressDialog("Saving profile...");
		// pd.showit();

		adminService.setProfile(loginName, loginPass, p, new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				// pd.hideit();
				MainPanel.log("Profile written OK.");
			}

			@Override
			public void onFailure(Throwable caught) {
				// pd.hideit();
				MainPanel.log("Error: " + caught.getMessage());
			}
		});

	}

	public static void deleteProfile(String loginName, String loginPass, String id) {

		ProgressDialog pd = new ProgressDialog("Deleting profile...");
		pd.showit();

		adminService.deleteProfile(loginName, loginPass, id, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void v) {
				// TODO Auto-generated method stub
				pd.hideit();
				MainPanel.log("Profile deleted.");
			}

			@Override
			public void onFailure(Throwable caught) {
				pd.hideit();
				MainPanel.log("Error: " + caught.getMessage());
			}
		});

	}

	public VerticalPanel getEmptyPanel(int row, int col) {
		VerticalPanel vpEmptyPanel = new VerticalPanel();

		vpEmptyPanel.addDomHandler(new DropHandler() {
			@Override
			public void onDrop(DropEvent event) {
				// required
				event.preventDefault();

				// get the data out of the event
				String dropType = event.getData("text").split(":")[0];
				if (dropType.equals("attribute")) {
					String typeAndAtt = event.getData("text").split(":")[1];

					String dcmtType = typeAndAtt.split("\\.")[0];
					String attName = typeAndAtt.split("\\.")[1];

					int selectedId = tabs.getSelectedIndex();
					Tab tab = (Tab) tabs.getItem(selectedId);

					String rowCol = vpEmptyPanel.getElement().getId();
					int row = Integer.valueOf(rowCol.split("~")[0]).intValue();
					int col = Integer.valueOf(rowCol.split("~")[1]).intValue();

					getLogger().info("Dropped " + attName + " on: tab:" + tab.getId() + " " + row + "," + col);

					vpEmptyPanel.remove(0);
					si.telekom.dis.shared.Attribute a = new si.telekom.dis.shared.Attribute();
					a.dcmtType = dcmtType;
					a.dcmtAttName = attName;
					a.tabId = tab.getId();
					a.row = row;
					a.col = col;
					a.label = attName;
					a.setType(si.telekom.dis.shared.Attribute.types.TEXTBOX.type);

					ProfileAttribute pa = new ProfileAttribute(a);
					vpEmptyPanel.add(pa);
					String key = attributeRolesStatesWizardsIndex + "~" + tab.getId() + "~" + row + "~" + col;

					AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
					for (si.telekom.dis.shared.Attribute a1 : arsw.attributes) {
						if (a1 != null && a1.dcmtAttName.equals(attName))
							arsw.attributes.remove(a1);
					}
					arsw.attributes.add(pa.attr);

					attTabPosition.put(key, pa);

					getLogger().info("ClassName: " + event.getSource().getClass().getName());
				}
			}
		}, DropEvent.getType());

		vpEmptyPanel.addDomHandler(new DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				// Do something like changing background
				VerticalPanel vp1 = (VerticalPanel) event.getSource();
				Grid g1 = (Grid) (vp1.getParent());
				for (int k = 0; k < g1.getRowCount(); k++) {
					for (int l = 0; l < g1.getCellCount(k); l++) {
						VerticalPanel vp2 = (VerticalPanel) (g1.getWidget(k, l));
						vp2.setBorderWidth(1);
					}
				}
				vp1.setBorderWidth(3);
			}
		}, DragOverEvent.getType());

		vpEmptyPanel.setBorderWidth(1);
		vpEmptyPanel.getElement().setId(row + "~" + col);

		SimplePanel sp = new SimplePanel();
		sp.setHeight("100px");
		sp.setWidth("150px");
		vpEmptyPanel.add(sp);

		return vpEmptyPanel;

	}

	public void removeProfileAttribute(ProfileAttribute profileAttribute) {
		for (si.telekom.dis.shared.Attribute att : attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex).attributes) {
			if (att != null)
				if (att.dcmtAttName.equals(profileAttribute.getName())) {
					attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex).attributes.remove(att);
					break;
				}
		}
		Widget w = profileAttribute.getParent();
		Grid g = gridAtts.get((spinnerAtts.value - 1) + "~" + profileAttribute.attr.tabId);

		profileAttribute.removeFromParent();
		g.setWidget(profileAttribute.attr.row, profileAttribute.attr.col, getEmptyPanel(profileAttribute.attr.row, profileAttribute.attr.col));
	}

	public void changeRoleId(String prevValue, String value) {
		MainPanel.log("ChangingRoles from: " + prevValue + " to " + value);
		// TODO Auto-generated method stub
		for (AttributeRoleStateWizards arsw : attributeRolesStatesWizards) {
			Iterator<String> stateIt = arsw.stateRole.keySet().iterator();
			while (stateIt.hasNext()) {
				String stateId = stateIt.next();
				List<String> roles = arsw.stateRole.get(stateId);
				if (roles.contains(prevValue)) {
					roles.remove(prevValue);
					roles.add(value);
				}
			}

		}
	}

	public void changeTabId(String prevValue, String value) {
		// TODO Auto-generated method stub
		MainPanel.log("Changing Tabs from: " + prevValue + " to " + value);
		for (AttributeRoleStateWizards arsw : attributeRolesStatesWizards) {
			Iterator<si.telekom.dis.shared.Attribute> attIt = arsw.attributes.iterator();
			while (attIt.hasNext()) {
				si.telekom.dis.shared.Attribute a = attIt.next();
				if (a.tabId.equals(prevValue))
					a.tabId = value;
			}

		}
	}

	public void changeStateId(String prevValue, String value) {
		// TODO Auto-generated method stub
		MainPanel.log("Changing States from: " + prevValue + " to " + value);
		for (AttributeRoleStateWizards arsw : attributeRolesStatesWizards) {
			Iterator<String> stateIt = arsw.stateRole.keySet().iterator();
			while (stateIt.hasNext()) {
				String stateId = stateIt.next();
				if (stateId.equals(prevValue)) {
					List<String> roles = arsw.stateRole.get(stateId);
					arsw.stateRole.remove(stateId);
					arsw.stateRole.put(value, roles);
				}
			}
		}
	}

	public void removeTab(int itemNo) {
		si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) (tabs.getItem(itemNo).item);
		for (AttributeRoleStateWizards arsw : attributeRolesStatesWizards) {
			Iterator<si.telekom.dis.shared.Attribute> attIt = arsw.attributes.iterator();
			while (attIt.hasNext()) {
				si.telekom.dis.shared.Attribute a = attIt.next();
				if (a.tabId.equals(tab.getId()))
					attIt.remove();
			}
		}
		tabs.removeItem(itemNo);
		refreshAttsStatesRoles();
	}

	public void removeRole(int itemNo) {
		// TODO Auto-generated method stub
		si.telekom.dis.shared.Role role = (si.telekom.dis.shared.Role) (roles.getItem(itemNo).item);
		String roleId = role.getId();
		for (AttributeRoleStateWizards arsw : attributeRolesStatesWizards) {
			Iterator<String> stateIt = arsw.stateRole.keySet().iterator();
			while (stateIt.hasNext()) {
				String stateId = stateIt.next();
				List<String> roles = arsw.stateRole.get(stateId);
				roles.remove(roleId);
			}
		}
		refreshAttsStatesRoles();
	}

	public void removeState(int itemNo) {
		si.telekom.dis.shared.State state = (si.telekom.dis.shared.State) (roles.getItem(itemNo).item);
		String stateId = state.getId();
		for (AttributeRoleStateWizards arsw : attributeRolesStatesWizards) {
			arsw.stateRole.remove(stateId);
		}
	}

	public void deleteStandardActionFromSelectedState(int selectedIndex) {
		State state = (State) states.getItem(states.getSelectedIndex());
		si.telekom.dis.shared.State sta = (si.telekom.dis.shared.State) (state.item);
		sta.standardActions.remove(selectedIndex);
		refreshStandardActions();
	}

	private void deleteUserGroupFromSelectedRole(int selectedIndex) {
		Role role = (Role) roles.getItem(roles.getSelectedIndex());
		si.telekom.dis.shared.Role rol = (si.telekom.dis.shared.Role) (role.item);
		rol.defaultUserGroups.remove(selectedIndex);
		refreshDefaultUserGroups();
	}

	public void refreshStandardActions() {
		GWT.log("refreshStandardActions...");
		int selectedStateIndex = states.getSelectedIndex();
		if (selectedStateIndex >= 0) {
			State state = (State) states.getItem(states.getSelectedIndex());
			si.telekom.dis.shared.State sta = (si.telekom.dis.shared.State) (state.item);
			standardActions.clear();
			GWT.log("State sa count: " + sta.standardActions.size());
			int i = 0;
			for (si.telekom.dis.shared.StandardAction sa : sta.standardActions) {
				StandardAction standActItem = new StandardAction(sa, standardActions);
				standardActions.addItem(standActItem);
				GWT.log("Added action " + i);
				i++;
			}
		}
		GWT.log("refreshStandardActions...Done.");
	}

	public void refreshDefaultUserGroups() {
		int selectedRoleIndex = roles.getSelectedIndex();
		if (selectedRoleIndex >= 0) {
			Role role = (Role) roles.getItem(roles.getSelectedIndex());
			si.telekom.dis.shared.Role rol = (si.telekom.dis.shared.Role) (role.item);
			defaultUserGroups.clear();
			for (si.telekom.dis.shared.UserGroup ug : rol.defaultUserGroups) {
				UserGroup userGroup = new UserGroup(ug, defaultUserGroups);
				defaultUserGroups.addItem(userGroup);
			}
		}
	}

	protected void resize() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {

				int calcHeight = Window.getClientHeight() - tp.getAbsoluteTop() - 30;
//				MainPanel.log(Window.getClientHeight() + "-" + (int) tp.getAbsoluteTop() + " -30");

				int y = tp.getAbsoluteTop();

				if (calcHeight > 0) {
					tp.setHeight(calcHeight + "px");
					int calcHeight2 = Window.getClientHeight() - tpAtts.getAbsoluteTop() - 70;
					for (String key : gridAtts.keySet()) {
						Grid g = gridAtts.get(key);
						ScrollPanel sp = (ScrollPanel) g.getParent();
						if (calcHeight2 > 0)
							sp.setHeight(calcHeight2 + "px");
					}
					// tpAtts.setHeight(calcHeight + "px");

					actionsStatesRolesScroll.setHeight(calcHeight + "px");
					// attsStatesRolesScroll.setHeight(calcHeight + "px");
				}

				Widget m = MainPanel.getInstance().menuPanel;
				int calcWidth = Window.getClientWidth() - (int) (MainPanel.getInstance().splitLayoutPanel.getWidgetSize(m).doubleValue()) - 50;
				// MainPanel.log(Window.getClientHeight() + "-" + tp.getAbsoluteTop() +
				// "-"
				// + (int)
				// (MainPanel.getInstance().splitLayoutPanel.getWidgetSize(m).doubleValue())
				// + "-100=" + calcWidth);
				if (calcWidth > 50) {
					tp.setWidth(calcWidth + "px");
					for (String key : gridAtts.keySet()) {
						Grid g = gridAtts.get(key);
						ScrollPanel sp = (ScrollPanel) g.getParent();
						if (calcWidth > 0)
							sp.setWidth(calcWidth + "px");
					}
					actionsStatesRolesScroll.setWidth(calcWidth + "px");
					// attsStatesRolesScroll.setWidth(calcWidth + "px");

				}

			}
		});

	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

	public void leftProfileAttribute(ProfileAttribute profileAttribute) {
		if (profileAttribute.attr.col > 0) {
			int targetRow = profileAttribute.attr.row;
			int targetCol = profileAttribute.attr.col - 1;
			swap(profileAttribute, targetRow, targetCol);
		}
	}

	public void rightProfileAttribute(ProfileAttribute profileAttribute) {
		si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) tabs.getArrayList().get(tpAtts.getTabBar().getSelectedTab());
		if (profileAttribute.attr.col < tab.col) {
			int targetRow = profileAttribute.attr.row;
			int targetCol = profileAttribute.attr.col + 1;
			swap(profileAttribute, targetRow, targetCol);
		}
	}

	public void upProfileAttribute(ProfileAttribute profileAttribute) {
		si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) tabs.getArrayList().get(tpAtts.getTabBar().getSelectedTab());
		if (profileAttribute.attr.row > 0) {
			int targetRow = profileAttribute.attr.row - 1;
			int targetCol = profileAttribute.attr.col;
			swap(profileAttribute, targetRow, targetCol);
		}
	}

	public void downProfileAttribute(ProfileAttribute profileAttribute) {
		si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) tabs.getArrayList().get(tpAtts.getTabBar().getSelectedTab());
		if (profileAttribute.attr.row < tab.row) {
			int targetRow = profileAttribute.attr.row + 1;
			int targetCol = profileAttribute.attr.col;
			swap(profileAttribute, targetRow, targetCol);
		}
	}

	private void swap(ProfileAttribute profileAttribute, int targetRow, int targetCol) {
		int row = profileAttribute.attr.row;
		int col = profileAttribute.attr.col;

		for (int i = 0; i < attributeRolesStatesWizards.size(); i++) {
			AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(i);

			for (Object tabObj : tabs.getArrayList()) {
				si.telekom.dis.shared.Tab tab = (si.telekom.dis.shared.Tab) tabObj;

				if (i == (spinnerAtts.value - 1) && tab.getId() == profileAttribute.attr.tabId) {
					Grid g = gridAtts.get(i + "~" + tab.getId());
					VerticalPanel vertPanelTarget = (VerticalPanel) g.getWidget(targetRow, targetCol);
					VerticalPanel vertPanelSource = (VerticalPanel) g.getWidget(row, col);

					Widget targetWidget = (Widget) vertPanelTarget.getWidget(0);
					ProfileAttribute paSource = (ProfileAttribute) vertPanelSource.getWidget(0);

					vertPanelSource.remove(paSource);

					String className = targetWidget.getClass().getName();
					if (className.endsWith("ProfileAttribute")) {
						ProfileAttribute paTarget = (ProfileAttribute) vertPanelTarget.getWidget(0);
						vertPanelTarget.remove(paTarget);
						paTarget.attr.col = col;
						paTarget.attr.row = row;

						paSource.attr.col = targetCol;
						paSource.attr.row = targetRow;

						String key = i + "~" + paSource.attr.tabId + "~" + paSource.attr.row + "~" + paSource.attr.col;
						attTabPosition.put(key, paSource);
						vertPanelSource.add(paTarget);
						key = i + "~" + paTarget.attr.tabId + "~" + paTarget.attr.row + "~" + paTarget.attr.col;
						attTabPosition.put(key, paTarget);
					} else {
						paSource.attr.col = targetCol;
						paSource.attr.row = targetRow;

						String key = i + "~" + paSource.attr.tabId + "~" + paSource.attr.row + "~" + paSource.attr.col;
						attTabPosition.put(key, paSource);
						key = i + "~" + paSource.attr.tabId + "~" + targetRow + "~" + targetCol;
						attTabPosition.remove(key);

						SimplePanel simplePanel = (SimplePanel) vertPanelTarget.getWidget(0);
						vertPanelSource.add(simplePanel);
						vertPanelTarget.remove(simplePanel);
					}

					vertPanelTarget.add(paSource);

					return;
				}
			}
		}
	}

	public void saveStandardActionsForState() {
		GWT.log("saveStandardActionsForState...");
		si.telekom.dis.shared.State st = (si.telekom.dis.shared.State) states.getItem(states.getSelectedIndex()).item;
		st.standardActions = standardActions.getArrayList();
		GWT.log("saveStandardActionsForState...Done.");
	}

	public void insertRow(ProfileAttribute profileAttribute) {
		String tabId = profileAttribute.attr.tabId;
		int selectedTab = tp.getTabBar().getSelectedTab();

		// get max row and max col
		int maxr = -1;
		int maxc = -1;

		for (int i = 0; i < attributeRolesStatesWizards.size(); i++) {
			AttributeRoleStateWizards arsw = attributeRolesStatesWizards.get(i);
			Tab tab = ((Tab) tabs.getItem(tpAtts.getTabBar().getSelectedTab()));

			si.telekom.dis.shared.Tab tb = (si.telekom.dis.shared.Tab) tab.item;

			attributeRolesStatesWizardsIndex = spinnerAtts.value - 1;
			arsw = attributeRolesStatesWizards.get(attributeRolesStatesWizardsIndex);
			for (si.telekom.dis.shared.Attribute a : arsw.attributes) {
				if (a.tabId == tab.getId()) {
					if (a.row == profileAttribute.attr.row) {
						MainPanel.log(a.tabId + " " + a.dcmtAttName);
						a.row = a.row + 1;
					}
				}
			}

		}

		refreshAttsStatesRoles();
		tp.selectTab(selectedTab);

	}

}
