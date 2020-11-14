package si.telekom.dis.client.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
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
import com.google.gwt.http.client.URL;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
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
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.Role;
import si.telekom.dis.shared.UserGroup;

public class ImportDocument extends WindowBox {
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	protected String classification_id = "";
	public static ImportDocument instance;

	int wizardTab = 0;
	int maxWizardTab = 3;
	Button nextB;
	Button previousB;
	TabPanel tpWizard;
	TabPanel tpUsers;

	ListBox profiles;
	ArrayList<FormAttribute> allFaAl;
	Profile prof;

	FormPanel uploadform;
	FileUpload fileUpload;
	FlowPanel uploadPanel;

	HashMap<String, ListBox> rolesAndUsers = new HashMap<String, ListBox>();
	private String r_object_id;

	ListBox possibleFormats;

	public ImportDocument(String r_object_id) {
		this();
		this.r_object_id = r_object_id;
	}

	public ImportDocument() {
		super();
		setText("Čarovnik za uvoz dokumenta");

		allFaAl = new ArrayList<FormAttribute>();

		tpWizard = new TabPanel();

		tpWizard.add(getClassify(), "Klasifikacija");
		// tpWizard.add(new VerticalPanel(), "Vsebina");
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

				wizardTab++;
				// refreshTabs();
				// tpWizard.selectTab(wizardTab);
				if (wizardTab == 1) {
					if (profiles.getSelectedIndex() != -1) {
						uploadContent();
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
				ImportDocument.this.center();
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
				centerWindowBox();
			}
		});
		enableDisableButtons();

		HorizontalPanel hpButtons = new HorizontalPanel();
		hpButtons.add(previousB);
		hpButtons.add(nextB);

		this.getPanel().setCellHorizontalAlignment(hpButtons, HasHorizontalAlignment.ALIGN_CENTER);
		this.getPanel().add(hpButtons);

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

				try {
					uploadform.setAction(calculateSafeUriDownload(attributes, roleUsersHm).asString());
					MainPanel.log("Počakaj trenutek - izdelava dokumenta v teku...");
					uploadform.submit();
				} catch (Exception ex) {
					MainPanel.log(ex.getMessage());
				}
			}
		});

		VerticalPanel vp = new VerticalPanel();
		uploadform = new FormPanel();
		fileUpload = new FileUpload();

		fileUpload.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (!fileUpload.getFilename().equals("")) {
					MainPanel.log("File selected: " + fileUpload.getFilename());
					findPossibleRenditions(ImportDocument.this, event.getNativeEvent());
				}
				enableDisableButtons();
			}
		});

		uploadform.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				// When the form submission is successfully completed, this
				// event is fired. Assuming the service returned a response
				// of type text/html, we can get the result text here
				Logger.getGlobal().info("event.getResults() " + event.getResults());
				if (event.getResults() != null) {
					MainPanel.log("Created imported object.");
					ImportDocument.this.hide();
				} else {
					MainPanel.log("Error creating object: " + event.getResults());
				}
			}
		});

		// set form to use the POST method, and multipart MIME encoding.
		uploadform.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadform.setMethod(FormPanel.METHOD_POST);
		uploadPanel = new FlowPanel();
		fileUpload.setName("uploadFile");

		possibleFormats = new ListBox();

		uploadform.add(fileUpload);

		vp.add(uploadform);
		vp.add(possibleFormats);
		tpWizard.insert(vp, "Vsebina", 1);

		centerWindowBox();

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
			"select group_name, description from dm_group where 1=1 and group_name in (" + dqlUsers +")";
		//@formatter:on							

				explorerService.getValuesFromDql(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, "", fullDqlUg,
						new AsyncCallback<List<String[]>>() {
							@Override
							public void onSuccess(List<String[]> result) {
								// TODO Auto-generated method stub
								Iterator<String[]> it = result.iterator();
								while (it.hasNext()) {
									String[] values = it.next();
									String line = values[0] + "|" + values[1];
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

		tpWizard.remove(3);
		tpWizard.add(tpUsers, "Uporabniki in vloge");
		tpWizard.selectTab(3);
	}

	protected void uploadContent() {
		String profileId = profiles.getSelectedValue();

		adminService.getProfile(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, profileId, new AsyncCallback<Profile>() {

			@Override
			public void onSuccess(Profile result) {
				prof = result;
			}

			@Override
			public void onFailure(Throwable caught) {
				MainPanel.log(caught.getMessage());
			}
		});

		tpWizard.selectTab(1);
		if (fileUpload.getFilename().equals(""))
			fileUpload.click();

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
		att.dqlValueListDefinition = 
//@formatter:off	
				"SELECT tc.\"code\", tc.\"name\" FROM dbo.T_CLASSIFICATION_PLAN tcp, dbo.T_CLASSIFICATION tc " +
				"WHERE "
					+ "tc.\"classification_plan_id\" = tcp.\"id\" AND "
					+ "tcp.\"name\" = 'KNTS' AND "
					+ "DATE(TODAY) >= tcp.valid_from AND "
				+ "( DATE(TODAY) <= tcp.valid_to OR tcp.valid_to = '') "
				+ "order by tc.\"code\"";
//@formatter:on
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
				ImportDocument.this.classification_id = event.getValue().get(0);

				adminService.getProfilesForClassSign(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						ImportDocument.this.classification_id, "import", new AsyncCallback<List<Profile>>() {
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

		if (wizardTab == 1 && !fileUpload.getFilename().equals(""))
			nextB.setEnabled(true);

	}

	public void refreshAttributes() {
		// TODO Auto-generated method stub
		TabPanel tpAtts = new TabPanel();
		tpAtts.setWidth("1000px");

		ScrollPanel sp = new ScrollPanel(tpAtts);
		sp.setHeight("600px");
		allFaAl.clear();
		for (si.telekom.dis.shared.Tab tab : prof.tabs) {
			Grid g = new Grid(tab.row, tab.col);
			for (AttributeRoleStateWizards arsw : prof.attributeRolesStatesWizards) {
				if (arsw.wizards.contains("newdoc")) {
					for (si.telekom.dis.shared.Attribute att : arsw.attributes) {
						if (tab.getId().equals(att.tabId)) {
							FormAttribute fa = new FormAttribute(att);
							g.setWidget(att.row, att.col, fa);
							fa.setWidth("90%");
							if (att.dcmtAttName.equals("mob_classification_id")) {
								ArrayList<String> values = new ArrayList<String>();
								values.add(split(classification_id, "|")[0]);
								fa.setValue(values);
							}

							if (att.defaultValue != null && att.defaultValueIsDql) {
								explorerService.getDefaultValueDql(att.defaultValue, new AsyncCallback<List<List<String>>>() {
									@Override
									public void onSuccess(List<List<String>> result) {
										ArrayList<String> al = new ArrayList<String>();
										al.add(result.get(0).get(0));
										fa.setValue(al);
									}

									@Override
									public void onFailure(Throwable caught) {
										MainPanel.log(caught.getMessage());
									}
								});
							}

							fa.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
								public void onValueChange(com.google.gwt.event.logical.shared.ValueChangeEvent<java.util.List<String>> event) {
									if (checkMandatoryAttributes())
										nextB.setEnabled(true);

									fillDependendAttributes(fa.att.dcmtAttName);
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
		Button b = new Button("Uvozi dokument");
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new ImportDocument();
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

	public String serializeHashMap(HashMap<String, List<String>> hm) throws IOException, SerializationException {
		String ret = "";
		for (String key : hm.keySet()) {
			ret = ret + key + "#";
			for (String val : hm.get(key)) {
				ret = ret + val + "|";
			}
			if (ret.endsWith("|"))
				ret = ret.substring(0, ret.length() - 1);
			ret = ret + "~";
		}
		if (ret.endsWith("~"))
			ret = ret.substring(0, ret.length() - 1);

		return URL.encodeQueryString(ret);
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

	public SafeUri calculateSafeUriDownload(HashMap<String, List<String>> attributes, HashMap<String, List<String>> roleUsersHm)
			throws IOException, SerializationException {

	//@formatter:off				
		SafeUri safeUriUploadDoc = UriUtils.fromString(GWT.getHostPageBaseURL() + "WebUi2/uploadServlet?"
				+ "loginName=" + MainPanel.getInstance().loginName
				+ "&loginPassword=" + MainPanel.getInstance().loginPass
				+ "&profileId=" + prof.id
				+ "&attributes=" + serializeHashMap(attributes)
				+ "&roleUsersHm=" + serializeHashMap(roleUsersHm)
				+ "&objectId=" + r_object_id
				+ "&format=" + possibleFormats.getSelectedItemText()
				);
//@formatter:on			

		return safeUriUploadDoc;
	}

	public void gotBase64(String base64data) {
		possibleFormats.clear();

		explorerService.recognizeFormat(base64data, new AsyncCallback<List<String>>() {

			@Override
			public void onSuccess(List<String> result) {
				for (String format : result) {
					possibleFormats.addItem(format);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				MainPanel.log(caught.getMessage());
			}
		});

	}

	public native void findPossibleRenditions(ImportDocument x, NativeEvent event) /*-{

		var image = event.target.files[0];

		// Check if file is an image
		//if (image.type.match('image.*')) {

		var reader = new FileReader();
		reader.onload = function(e) {
			// Call-back Java method when done
			//imageLoaded(e.target.result);
			reader.result; //holds base64 file content
			//$wnd.gotBase64(e.target.result);
			x.@si.telekom.dis.client.action.ImportDocument::gotBase64(Ljava/lang/String;)(e.target.result);
		}

		// Start reading the image
		reader.readAsDataURL(image);
		//}
	}-*/;

}
