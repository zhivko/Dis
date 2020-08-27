package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import si.telekom.dis.client.action.EditRegisteredTable;
import si.telekom.dis.client.action.ImportDocument;
import si.telekom.dis.client.action.NewDocument;
import si.telekom.dis.client.action.NewFolder;
import si.telekom.dis.client.action.NewProfile;
import si.telekom.dis.client.action.RegisterTable;
import si.telekom.dis.client.action.SyncDoctypes;
import si.telekom.dis.shared.Action;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.DcmtAttribute;
import si.telekom.dis.shared.DcmtAttribute.Kind;
import si.telekom.dis.shared.DocType;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.ServerException;

public class MenuPanel extends Composite {
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");

	public static HashMap<String, Action> hmActions = new HashMap<String, Action>();

	/**
	 * Specifies the images that will be bundled for this example.
	 *
	 * We will override the leaf image used in the tree. Instead of using a blank
	 * 16x16 image, we will use a blank 1x1 image so it does not take up any
	 * space. Each TreeItem will use its own custom image.
	 */
	Images images; // = (Images) GWT.create(Images.class);
	public static String selectedDcmtType = "";
	VerticalPanel adminPanel = new VerticalPanel();
	ScrollPanel spDocTypes = null;
	ScrollPanel spActions = null;
	ScrollPanel spRegisteredTables = null;
	Tree actionsTree = null;
	TreeItem treeItemDocTypesAndAtts;
	TreeItem treeItemActions;
	VerticalPanel vpSearchButtons;

	DecoratedStackPanel stackPanel;

	static MenuPanel instance;

	public static ExplorerPanel activeExplorerInstance;

	ScrollPanel spSearchItems;

	public static MenuPanel getInstance() {
		return instance;
	}

	public MenuPanel(String loginName) {
		images = (Images) GWT.create(Images.class);
		spRegisteredTables = new ScrollPanel();

		// Create a new stack panel
		stackPanel = new DecoratedStackPanel() {
			@Override
			public void showStack(int index) {
				// TODO Auto-generated method stub
				super.showStack(index);

				if (index == 0) {
					// explorer
					CustomTreeModel.selectionModel.clear();
					MainPanel.clearPanel();
					activeExplorerInstance = ExplorerPanel.getExplorerInstance();
					MainPanel.getPanel().add(activeExplorerInstance);
				} else if (index == 1) {
					CustomTreeModel.selectionModel.clear();
					createSearchItems(images, spSearchItems);
					MainPanel.clearPanel();
					SearchPanel sp = SearchPanel.getSearchPanelInstance();
					activeExplorerInstance = sp;
					sp.getElement().setId("SearchPanel");
					MainPanel.getPanel().add(sp);
					// search
				} else if (index == 3) {
					if (MainPanel.getInstance().loginRole.equalsIgnoreCase("administrator")) {
						MainPanel.log("Loading doctypes...");
						if (spDocTypes == null) {
							MainPanel.log("First time creating doctypes...");
							spDocTypes = new ScrollPanel();
							adminPanel.add(refreshDocTypesAndAtts());
						}
						if (actionsTree == null) {
							actionsTree = new Tree(images){
								@Override
								public void setFocus(boolean focus) {
									// https://bugs.chromium.org/p/chromium/issues/detail?id=681382#c27
									// do nothing
								}
							};
							
							spActions = new ScrollPanel();
							adminPanel.add(createActions());
						}
					}
				}

			}
		};

		stackPanel.getElement().setId("MenuPanel");
		stackPanel.setWidth("100%");

		// Add the Explorer folders
		String explorerHeader = getHeaderString("Raziskovalec", images.explorer());
		stackPanel.add(createExplorerItems(images), explorerHeader, true);

		String searchHeader = getHeaderString("Iskalnik", images.search());
		spSearchItems = new ScrollPanel(vpSearchButtons);
		spSearchItems.setHeight("500px");
		stackPanel.add(spSearchItems, searchHeader, true);

		// Add a list of regTables
		String regTableHeader = getHeaderString("Šifranti", images.contactsgroup());
		stackPanel.add(createRegTables(images), regTableHeader, true);

		// // Add a list of contacts
		// String contactsHeader = getHeaderString("Contacts",
		// images.contactsgroup());
		// stackPanel.add(createContactsItem(images), contactsHeader, true);

		// Return the stack panel
		stackPanel.ensureDebugId("cwStackPanel");

		MainPanel mpInstance = MainPanel.getInstance();
		String loginRole = mpInstance.loginRole;
		if (mpInstance.loginRole.equalsIgnoreCase("administrator")) {
			String adminHeader = getHeaderString("Admin", images.filtersgroup());
			stackPanel.add(createAdminItems(), adminHeader, true);
		}

		instance = this;

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				resize();
			}
		});

		initWidget(stackPanel);
	}

	/**
	 * Get a string representation of the header that includes an image and some
	 * text.
	 *
	 * @param text
	 *          the header text
	 * @param image
	 *          the {@link ImageResource} to add next to the header
	 * @return the header as a string
	 */
	private String getHeaderString(String text, ImageResource image) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(new Image(image));
		// HTML headerText = new HTML(text);
		// headerText.setStyleName("cw-StackPanelHeader");
		Anchor a = new Anchor(text);
		hPanel.add(a);

		// Return the HTML string for the panel
		return hPanel.getElement().getString();
	}

	/**
	 * Create the list of Contacts.
	 *
	 * @param images
	 *          the {@link Images} used in the Contacts
	 * @return the list of contacts
	 */
	private ScrollPanel createRegTables(Images images) {
		return refreshRegisteredTables();
	}

	/**
	 * Create the list of filters for the Filters item.
	 *
	 * @return the list of filters
	 */
	private VerticalPanel createAdminItems() {
		String[] filters = { "Novi profil", "Kopiraj profil", "Brisi profil" };
		adminPanel.setSpacing(4);
		adminPanel.setWidth("100%");
		adminPanel.add(NewProfile.getMenuItem());
		adminPanel.add(SyncDoctypes.getMenuItem());
		adminPanel.add(RegisterTable.getMenuItem());		
		
		//adminPanel.add(classifyButton);

		return adminPanel;
	}

	public ScrollPanel refreshDocTypesAndAtts() {
		// images = (Images) GWT.create(Images.class);
		MainPanel.log("Creating doctypes...");

		Tree docTypesAndAttsTree = new Tree(images) {
			@Override
			public void setFocus(boolean focus) {
				// https://bugs.chromium.org/p/chromium/issues/detail?id=681382#c27
				// do nothing
			}
		};


		// docTypesAndAttsTree.addCloseHandler(new CloseHandler<TreeItem>() {
		// @Override
		// public void onClose(CloseEvent<TreeItem> event) {
		// if (event.getTarget().equals(treeItemDocTypesAndAtts))
		// spDocTypes.setHeight("");
		// }
		// });
		//
		// docTypesAndAttsTree.addOpenHandler(new OpenHandler<TreeItem>() {
		// @Override
		// public void onOpen(OpenEvent<TreeItem> event) {
		// if (event.getTarget().equals(treeItemDocTypesAndAtts))
		// spDocTypes.setHeight("400px");
		// }
		// });

		docTypesAndAttsTree.setWidth("100%");
		spDocTypes.setWidth("100%");
		if (spDocTypes.getWidget() != null) {
			spDocTypes.remove(spDocTypes.getWidget());
		}

		spDocTypes.add(docTypesAndAttsTree);
		// docTypesAndAttsTree.addSelectionHandler(new SelectionHandler<TreeItem>()
		// {
		//
		// @Override
		// public void onSelection(SelectionEvent<TreeItem> event) {
		// // TODO Auto-generated method stub
		//
		// TreeItem item = event.getSelectedItem();
		// String id = item.getElement().getId();
		// if (id.length() > 0) {
		// selectedDcmtType = id.split(":")[1];
		// }
		// }
		// });
		treeItemDocTypesAndAtts = docTypesAndAttsTree.addTextItem("Document types");

		MainPanel.log("Loading doctypes...");

		adminService.getDocTypes(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, new AsyncCallback<List<DocType>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MainPanel.log("Error: " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<DocType> result) {
				MainPanel.log("Loading doctypes SUCCESS...");
				TreeItem docType = null;
				for (DocType doctype : result) {
					// MainPanel.log("doctype: " + doctype.id);
					ImageResource image = images.doctype();
					SafeHtmlBuilder itemHtml = new SafeHtmlBuilder();
					itemHtml.append(AbstractImagePrototype.create(image).getSafeHtml());
					itemHtml.appendHtmlConstant("  ").appendEscaped(doctype.id);
					Label lblType = new Label(doctype.name);
					lblType.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							selectedDcmtType = doctype.id;
						}
					});
					docType = treeItemDocTypesAndAtts.addItem(lblType);
					// docType.getElement().setId("dcmtType:" + doctype.id);

					SafeHtmlBuilder itemProfiles = new SafeHtmlBuilder();
					itemProfiles.appendHtmlConstant("  ").appendEscaped("profiles");
					TreeItem profiles = docType.addItem(itemProfiles.toSafeHtml());

					SafeHtmlBuilder itemInternalAtts = new SafeHtmlBuilder();
					itemInternalAtts.appendHtmlConstant("  ").appendEscaped("internal");
					TreeItem internalAtts = docType.addItem(itemInternalAtts.toSafeHtml());

					SafeHtmlBuilder itemSystemAtts = new SafeHtmlBuilder();
					itemSystemAtts.appendHtmlConstant("  ").appendEscaped("system");
					TreeItem systemAtts = docType.addItem(itemSystemAtts.toSafeHtml());

					SafeHtmlBuilder itemCustomAtts = new SafeHtmlBuilder();
					itemCustomAtts.appendHtmlConstant("  ").appendEscaped("custom");
					TreeItem customAtts = docType.addItem(itemCustomAtts.toSafeHtml());

					for (DcmtAttribute att : doctype.attributes.values()) {
						// ImageResource image1 = images.docattribute();
						// SafeHtmlBuilder itemHtml1 = new SafeHtmlBuilder();
						// itemHtml1.append(AbstractImagePrototype.create(image1).getSafeHtml());
						// itemHtml1.appendHtmlConstant(" ").appendEscaped(att.attr_name);

						HorizontalPanel hpAtt = new HorizontalPanel();
						hpAtt.add(new Image(image));
						Label lbl = new Label(att.attr_name);
						lbl.setStyleName("myLabel");

						SimplePanel spSpace = new SimpleLayoutPanel();
						spSpace.setWidth("10px");
						hpAtt.add(spSpace);
						hpAtt.add(lbl);
						lbl.addDragStartHandler(new DragStartHandler() {
							@Override
							public void onDragStart(DragStartEvent event) {
								// TODO Auto-generated method stub
								event.setData("text", doctype.id + "." + att.attr_name);
							}
						});

						FocusPanel fp2 = new FocusPanel(hpAtt);
						fp2.addDragStartHandler(new DragStartHandler() {
							@Override
							public void onDragStart(DragStartEvent event) {
								// TODO Auto-generated method stub
								event.setData("text", "attribute:" + doctype.id + "." + att.attr_name);
							}
						});
						TreeItem docAtt;
						if (att.kind == Kind.CUSTOM)
							docAtt = customAtts.addItem(fp2);
						else if (att.kind == Kind.SYSTEM)
							docAtt = systemAtts.addItem(fp2);
						else if (att.kind == Kind.INTERNAL)
							docAtt = internalAtts.addItem(fp2);

					}

					for (Profile prof : doctype.profiles.values()) {
						MainPanel.log("profile: " + prof.id);

						FocusPanel fp = new FocusPanel();
						fp.getElement().addClassName("gwt-TreeNode");
						TreeItem profileItem = profiles.addItem(fp);
						profileItem.getElement().addClassName("gwt-TreeNode");

						Button removeProfile = new Button("X");
						removeProfile.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent arg0) {
								ActionDialogBox db = new ActionDialogBox("","Really delete profile?");
								db.getOkButton().addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										// TODO Auto-generated method stub
										
										String id = prof.id;
										String loginName = MainPanel.getInstance().loginName;
										String loginPass = MainPanel.getInstance().loginPass;
										NewProfile.deleteProfile(loginName, loginPass, id);

										profiles.removeItem(profileItem);
										
										
										db.hide();
									}
								});

								db.setModal(true);
								db.show();
								db.center();
							}
						});

						Label lbProfile = new Label(prof.name);
						lbProfile.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent arg0) {
								MainPanel.log("Loading profile...");
								adminService.getProfile(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, prof.id, new AsyncCallback<Profile>() {
									@Override
									public void onSuccess(Profile prof) {
										// TODO Auto-generated method stub
										MainPanel.log("Profile read OK.");
										MainPanel.clearPanel();
										NewProfile newProfile = new NewProfile(prof);
										MainPanel.getPanel().add(newProfile);
									}

									@Override
									public void onFailure(Throwable caught) {
										MainPanel.log("Error: " + caught.getMessage());
									}
								});

							}
						});
						HorizontalPanel hp = new HorizontalPanel();
						fp.add(hp);
						ImageResource imageProf = images.docProfile();
						hp.add(new HTML(AbstractImagePrototype.create(imageProf).getSafeHtml()));
						SimplePanel sp = new SimplePanel();
						sp.setWidth("5px");
						hp.add(sp);
						hp.add(lbProfile);
						SimplePanel sp1 = new SimplePanel();
						sp1.setWidth("5px");
						hp.add(sp1);
						hp.add(removeProfile);

						// TreeItem profileItem =
						// profiles.addItem(itemProfile.toSafeHtml());

						// profileItem.getElement().setId("profile:" + prof.id);

						docType.setState(true);
					}

				}
				treeItemDocTypesAndAtts.setState(true);

				resize();

			}
		});
		return spDocTypes;
	}

	/**
	 * Create the {@link Tree} of Mail options.
	 *
	 * @param images
	 *          the {@link Images} used in the Mail options
	 * @return the {@link Tree} of mail options
	 */
	private VerticalPanel createExplorerItems(Images images) {
		VerticalPanel vp = new VerticalPanel();
		vp.add(NewDocument.getMenuItem());
		vp.add(ImportDocument.getMenuItem());
		vp.add(NewFolder.getMenuItem());
		return vp;
	}

	private void createSearchItems(Images images, ScrollPanel spSearchItems) {
		if (vpSearchButtons == null) {
			vpSearchButtons = new VerticalPanel();
			spSearchItems.add(vpSearchButtons);
			try {
				adminService.getLazySearchQueries(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
						new AsyncCallback<List<MyParametrizedQuery>>() {
							@Override
							public void onSuccess(List<MyParametrizedQuery> result) {
								for (MyParametrizedQuery query : result) {
									ParametrizedQueryPanel panel = new ParametrizedQueryPanel(query);
									vpSearchButtons.add(panel.getButtonPanel());
								}
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log(caught.getMessage());
							}
						});
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				MainPanel.log(e.getMessage());
			}
		}
	}

	private void addItem(TreeItem root, ImageResource image, String label) {
		SafeHtmlBuilder itemHtml = new SafeHtmlBuilder();
		itemHtml.append(AbstractImagePrototype.create(image).getSafeHtml());
		itemHtml.appendHtmlConstant(" ").appendEscaped(label);
		root.addItem(itemHtml.toSafeHtml());
	}

	private ScrollPanel createActions() {

		treeItemActions = actionsTree.addTextItem("Akcije");
		actionsTree.addCloseHandler(new CloseHandler<TreeItem>() {
			@Override
			public void onClose(CloseEvent<TreeItem> event) {
				// TODO Auto-generated method stub
				if (event.getTarget().equals(treeItemActions))
					spActions.setHeight("");
			}
		});
		actionsTree.addOpenHandler(new OpenHandler<TreeItem>() {
			@Override
			public void onOpen(OpenEvent<TreeItem> event) {
				if (event.getTarget().equals(treeItemActions))
					spActions.setHeight("400px");
			}
		});

		spActions.setWidth("100%");
		if (spActions.getWidget() != null) {
			spActions.remove(spActions.getWidget());
		}
		spActions.add(actionsTree);

		adminService.getActions(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, new AsyncCallback<List<Action>>() {

			@Override
			public void onSuccess(List<Action> result) {
				// @formatter:off
//				String[] actionsDef = 
//					{ 
//							"document.properties",
//							"document.checkIn",
//							"document.checkOut",
//							"document.delete",
//							"document.view",
//							"document.edit",
//							"other.action1",
//							"other.action2"
//					};
						// @formatter:on

				String previousActionGroup = "";
				TreeItem actionGroupItem = null;
				for (Action action : result) {
					hmActions.put(action.id, action);
					String actionGroup = action.getId().split("\\.")[0];
					if (!previousActionGroup.equals(actionGroup)) {
						SafeHtmlBuilder itemHtml = new SafeHtmlBuilder();
						itemHtml.appendHtmlConstant("").appendEscaped(actionGroup);
						actionGroupItem = treeItemActions.addItem(itemHtml.toSafeHtml());
						actionGroupItem.getElement().setId("actionGroup:" + actionGroup);
					}

					HorizontalPanel hpAction = new HorizontalPanel();
					ImageResource imageResource = images.action();
					Image image = new Image(imageResource);
					image.setPixelSize(16, image.getHeight() * 16 / imageResource.getWidth());
					hpAction.add(image);
					HTML lbl = new HTML(action.name);
					lbl.getElement().setId(action.toString());
					lbl.setStyleName("myLabel");

					SimplePanel spSpace = new SimpleLayoutPanel();
					spSpace.setWidth("10px");
					hpAction.add(spSpace);
					hpAction.add(lbl);
					lbl.addDragStartHandler(new DragStartHandler() {
						@Override
						public void onDragStart(DragStartEvent event) {
							// TODO Auto-generated method stub
							event.setData("text", "action:" + action.toString());
						}
					});

					FocusPanel fp = new FocusPanel(hpAction);
					fp.addDragStartHandler(new DragStartHandler() {
						@Override
						public void onDragStart(DragStartEvent event) {
							// TODO Auto-generated method stub
							event.setData("text", "action:" + action.toString());
						}
					});

					TreeItem actionItem = actionGroupItem.addItem(fp);
					actionGroupItem.setState(true);

					previousActionGroup = actionGroup;
				}

				treeItemActions.setState(true);
				resize();
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				MainPanel.log("Error getting actions: " + caught.getMessage());
			}
		});

		return spActions;
	}

	public ScrollPanel refreshRegisteredTables() {
		// images = (Images) GWT.create(Images.class);

		MainPanel.log("Creating registered tables...");

		spRegisteredTables.setHeight("500px");
		spRegisteredTables.setWidth("100%");

		Tree RegisteredTablesTree = new Tree(images);
		RegisteredTablesTree.setWidth("100%");
		if (spRegisteredTables.getWidget() != null) {
			spRegisteredTables.remove(spRegisteredTables.getWidget());
		}
		spRegisteredTables.add(RegisteredTablesTree);
		RegisteredTablesTree.addSelectionHandler(new SelectionHandler<TreeItem>() {

			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem item = event.getSelectedItem();
				String id = item.getElement().getId();
				if (id.length() > 0) {
					String[] splittedId = id.split(":");
					if (splittedId[0].equals("regtable")) {
						String regTableId = splittedId[1];
						MainPanel.clearPanel();
						EditRegisteredTable editTable = new EditRegisteredTable(regTableId);
						MainPanel.getPanel().add(editTable);
						MainPanel.log("Loading regtable: " + regTableId);
					}
					selectedDcmtType = id.split(":")[1];
				}
			}
		});
		TreeItem regTables = RegisteredTablesTree.addTextItem("Registrirane tabele");
		MainPanel.log("Loading registered tables...");

		try {
			adminService.getRegTables(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, new AsyncCallback<List<String>>() {
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					MainPanel.log("Error: " + caught.getMessage());
				}

				@Override
				public void onSuccess(List<String> result) {
					MainPanel.log("Loading regtables DONE.");

					TreeItem regTable = null;
					for (String regtable : result) {
						ImageResource image = images.dmRegistered();
						SafeHtmlBuilder itemHtml = new SafeHtmlBuilder();
						itemHtml.append(AbstractImagePrototype.create(image).getSafeHtml());
						itemHtml.appendHtmlConstant("  ").appendEscaped(regtable);

						regTable = regTables.addItem(itemHtml.toSafeHtml());
						regTable.getElement().setId("regtable:" + regtable);
					}
					regTables.setState(true);
				}
			});
		} catch (ServerException e) {
			// TODO Auto-generated catch block
			MainPanel.log(e.getMessage());
		}
		return spRegisteredTables;
	}

	protected void resize() {
		if (MainPanel.getInstance() != null && spDocTypes != null && spActions != null)
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {

				@Override
				public void execute() {
					Logger.getGlobal()
							.info(MainPanel.getInstance().getOffsetHeight() + "-" + spDocTypes.getAbsoluteTop() + "-" + spActions.getOffsetHeight() + "-" + 10);
					// int calcHeight = MainPanel.getInstance().getOffsetHeight() -
					// spDocTypes.getAbsoluteTop()
					// - spActions.getOffsetHeight() - 10;
					// if (spDocTypes.getMinimumVerticalScrollPosition() < calcHeight)
					// spDocTypes.setHeight(calcHeight + "px");
					// else
					spDocTypes.setHeight("300px");
				}
			});
	}

	public void addAdminPanel() {
		// Add a list of admin actions
	}

	public void resetSearchButtons() {
		for (int i = 0; i < vpSearchButtons.getWidgetCount(); i++) {
			HorizontalPanel hp = ((HorizontalPanel) vpSearchButtons.getWidget(i));
			((ToggleButton) hp.getWidget(0)).setDown(false);
		}
	}

}
