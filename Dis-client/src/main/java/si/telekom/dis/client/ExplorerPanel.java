package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsDate;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.CustomTreeModel.MyAsyncDataProvider;
import si.telekom.dis.client.action.ClassifyDocument;
import si.telekom.dis.client.action.DocumentAddRendition;
import si.telekom.dis.client.action.DocumentAddVersionLabel;
import si.telekom.dis.client.action.DocumentAuditTrail;
import si.telekom.dis.client.action.DocumentCheckin;
import si.telekom.dis.client.action.DocumentDelete;
import si.telekom.dis.client.action.DocumentDemote;
import si.telekom.dis.client.action.DocumentPromote;
import si.telekom.dis.client.action.DocumentProperties;
import si.telekom.dis.client.action.DocumentRemoveRendition;
import si.telekom.dis.client.action.DocumentRemoveVersionLabel;
import si.telekom.dis.client.action.DocumentVersions;
import si.telekom.dis.client.action.DocumentView;
import si.telekom.dis.client.action.EditColIds;
import si.telekom.dis.client.action.FolderUseForAiTraining;
import si.telekom.dis.client.action.ImportDocument;
import si.telekom.dis.client.action.ManageUsers;
import si.telekom.dis.client.action.ShowPdfTags;
import si.telekom.dis.client.item.FormAttribute;
import si.telekom.dis.shared.Action;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.Document;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.Tab;

public class ExplorerPanel extends Composite {
	private static ExplorerPanel instance;
	public CustomTreeModel model = null;
	public HorizontalPanel hp;
	public CellTree cellTree;
	ScrollPanel scrollPanel;
	private VerticalPanel vp2;
	private VerticalPanel vp;
	private HorizontalPanel hpFolder;
	private ListBox txtFolderLb;
	private TextBox txtFolder;

	public final static ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);

	private final static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("mylogger");
	WindowBox adb = null;
	int lastSelectedPropertiesTab = 0;

	public String r_object_id;

	public HorizontalPanel hpActions;
	public FocusPanel fpProperties;
	public FocusPanel fpContent;

	public String i_chronicle_id;
	public String documentStateId;
	public Document selectedDocument;

	public CheckBox markAll;

	public ExplorerPanel() {
		hpActions = new HorizontalPanel();
		fpProperties = new FocusPanel();
		fpContent = new FocusPanel();

		hp = new HorizontalPanel();
		scrollPanel = new ScrollPanel();
		vp = new VerticalPanel();

		hpFolder = new HorizontalPanel();

		txtFolder = new TextBox();
		txtFolder.setTitle("Bakoda objekta ali mapa");

		txtFolderLb = new ListBox();
		txtFolderLb.addItem("");
		txtFolderLb.addItem("09062020031800001");
		txtFolderLb.addItem("09062019052900001");
		txtFolderLb.addItem("09382013120500002");
		txtFolderLb.addItem("/Temp");
		txtFolderLb.addItem("/MOB/Standard/Template/eForm");

		String pattern = "yyyy/MM"; /* your pattern here */
		DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
		DateTimeFormat dtf = new DateTimeFormat(pattern, info) {
		}; // <= trick here
		String yearStr = dtf.format(new Date());

		txtFolderLb.setWidth("18px");
		txtFolderLb.addItem("/MOB/PPM/EP/SP/MC/Subscriber/Current");
		txtFolderLb.addItem("/MOB/PPM/EP/SP/MC/Subscriber/" + yearStr);
		txtFolderLb.addItem("/MOB/PFK/Contract");
		txtFolderLb.addItem("/Temp/Jobs");
		// txtFolderLb.setWidth("10em");

		// markAll = new CheckBox("Označi vse");
		// markAll.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		//
		// for (MyAsyncDataProvider dp : ExplorerPanel.this.model.allDataProviders)
		// {
		// for (Document doc : dp.documents) {
		// if (markAll.getValue())
		// CustomTreeModel.selectionModel.setSelected(doc.r_object_id, true);
		// else
		// CustomTreeModel.selectionModel.setSelected(doc.r_object_id, false);
		// }
		// }
		//
		// }
		// });

		hpFolder.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		hpFolder.add(txtFolder);
		hpFolder.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hpFolder.add(txtFolderLb);
		// hpFolder.add(markAll);

		hpFolder.setWidth("100%");
		hpFolder.setCellWidth(txtFolderLb, "18px");
		hpFolder.setCellWidth(txtFolder, "100%");
		txtFolder.setWidth("100%");

		txtFolderLb.setSelectedIndex(0);
		txtFolderLb.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				// cellTree.
				txtFolder.setValue(txtFolderLb.getSelectedValue());
				refresh(txtFolderLb.getSelectedValue());
			}
		});

		txtFolder.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER)
					// TODO Auto-generated method stub
					refresh(txtFolder.getText());
			}
		});

		vp.add(hpFolder);
		// vp.setCellWidth(hpFolder, "100%");

		vp.add(scrollPanel);
		vp.setCellHeight(scrollPanel, "100%");
		vp2 = new VerticalPanel();

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				resize();
			}

		});

		vp2.add(hpActions);
		vp2.add(fpProperties);
		vp2.setCellWidth(fpProperties, "100%");
		vp2.add(fpContent);

		scrollPanel.setWidth("100%");
		scrollPanel.setAlwaysShowScrollBars(false);

		hp.add(vp);
		hp.add(vp2);
		hp.setCellHeight(vp, "80%");

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				resize();
			}
		});

		initWidget(hp);
	}

	protected void resize() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {

				String val = getStyle(ExplorerPanel.getExplorerInstance().getElement(), "font-size");
				String number = val.substring(0, val.indexOf("px"));
				int height;
				if (number.length() > 0) {
					int emSize = Integer.valueOf(number).intValue();
					height = MainPanel.getInstance().getOffsetHeight() - MainPanel.getInstance().topPanel.getOffsetHeight() - 3 * emSize;
					// MainPanel.log(String.valueOf(height));
				} else
					height = MainPanel.getInstance().getOffsetHeight() - MainPanel.getInstance().topPanel.getOffsetHeight() - 20;
				if (height > 0)
					scrollPanel.setHeight(height + "px");
			}
		});
	}

	public static ExplorerPanel getExplorerInstance() {
		if (instance == null) {
			instance = new ExplorerPanel();
		}
		return instance;
	}

	public void firstLoadCompleted() {
		TreeNode rootNode = cellTree.getRootTreeNode();
		for (int i = 0; i < rootNode.getChildCount(); i++) {
			Document doc = (Document) rootNode.getChildValue(i);
			logger.info(doc.object_name);
		}
		Document doc = (Document) rootNode.getChildValue(0);
		if (doc != null)
			selectDocument(doc);

	}

	public void refresh(String folderPath) {
		ExplorerPanel.instance.clearSelectedSet();

		this.model = new CustomTreeModel(folderPath, this);

		int size = 0;
		String className = this.getClass().getName();
		if (className.endsWith("ExplorerPanel"))
			size = MainPanel.getInstance().us.explorerReturnResultCount;
		else if (className.endsWith("SearchPanel"))
			size = MainPanel.getInstance().us.searchReturnResultCount;

		cellTree = new MyCellTree(this.model, null, r_object_id, this, size);

		// TODO: get tree size from server
		// cellTree.setDefaultNodeSize(this.model.length);

		ExplorerPanel.getExplorerInstance().sinkEvents(Event.ONCONTEXTMENU);
		cellTree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellTree.sinkEvents(Event.ONCONTEXTMENU);

		if (scrollPanel.getWidget() != null)
			scrollPanel.remove(scrollPanel.getWidget());
		scrollPanel.add(cellTree);
		// scrollPanelExplorer.setAlwaysShowScrollBars(true);

	}

	protected void runAction(String actionId) {
		// TODO Auto-generated method stub
		logger.info("Running action " + actionId + " for object: " + r_object_id);
		adb = null;
		if (actionId.equals("document.view")) {
			adb = new DocumentView(r_object_id);
		} else if (actionId.equals("document.edit")) {
			String url = DocumentView.getUrl(r_object_id, "") + "&download=true";
			String safeUriDocView = UriUtils.fromString(url).asString();
			Window.open(URL.encode(safeUriDocView), "_self", "status=0,toolbar=0,menubar=0,location=0");
		} else if (actionId.equals("document.properties")) {
			adb = new DocumentProperties(r_object_id);
		} else if (actionId.equals("document.delete")) {
			adb = new DocumentDelete(r_object_id);
		} else if (actionId.equals("document.manageUsers")) {
			adb = new ManageUsers(r_object_id);
		} else if (actionId.equals("document.versions")) {
			adb = new DocumentVersions(r_object_id);
		} else if (actionId.equals("document.audittrail")) {
			adb = new DocumentAuditTrail(r_object_id);
		} else if (actionId.equals("document.promote")) {
			adb = new DocumentPromote(r_object_id);
		} else if (actionId.equals("document.demote")) {
			adb = new DocumentDemote(r_object_id);
		} else if (actionId.equals("document.addVersionLabel")) {
			adb = new DocumentAddVersionLabel(r_object_id);
		} else if (actionId.equals("document.removeVersionLabel")) {
			adb = new DocumentRemoveVersionLabel(r_object_id);
		} else if (actionId.equals("document.checkIn")) {
			adb = new DocumentCheckin(r_object_id);
		} else if (actionId.equals("document.addRendition")) {
			adb = new DocumentAddRendition(r_object_id);
		} else if (actionId.equals("document.removeRendition")) {
			adb = new DocumentRemoveRendition(r_object_id);
		} else if (actionId.equals("document.checkOut")) {
			explorerService.checkout(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					MainPanel.log("Checkout succesfull");
					MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
				}

				@Override
				public void onFailure(Throwable caught) {
					MainPanel.log("Checkout error: " + caught.getMessage());
				}
			});
		} else if (actionId.equals("document.cancelCheckOut")) {
			explorerService.cancelCheckout(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					MainPanel.log("CancelCheckout succesfull");
					MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
				}

				@Override
				public void onFailure(Throwable caught) {
					MainPanel.log("CancelCheckout error: " + caught.getMessage());
				}
			});
		} else if (actionId.equals("document.unlock")) {
			explorerService.unlock(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					MainPanel.log("Unlock succesfull");
					MenuPanel.activeExplorerInstance.refreshLastSelectedNode();
				}

				@Override
				public void onFailure(Throwable caught) {
					MainPanel.log("Unlock error: " + caught.getMessage());
				}
			});
		} else if (actionId.equals("folder.importDocument")) {
			adb = new ImportDocument(ExplorerPanel.getExplorerInstance().r_object_id);
		} else if (actionId.equals("folder.useForAiTraining")) {
			adb = new FolderUseForAiTraining();
		} else if (actionId.equals("template.editCollIds")) {
			adb = new EditColIds(r_object_id);
		} else if (actionId.equals("document.pdfTags")) {
			adb = new ShowPdfTags(r_object_id);
		} else if (actionId.equals("template.updateBusinessNotification"))
			explorerService.updateBusinessNotification(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id,
					new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							// TODO Auto-generated method stub
							MainPanel.log("updateBusinessNotification succesfull");
						}

						@Override
						public void onFailure(Throwable caught) {
							MainPanel.log("updateBusinessNotification error: " + caught.getMessage());
						}
					});
		else if (actionId.equals("document.newRelease"))
			explorerService.newRelease(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, new AsyncCallback<Void>() {
				@Override
				public void onSuccess(Void result) {
					// TODO Auto-generated method stub
					MainPanel.log("newRelease succesfull");
				}

				@Override
				public void onFailure(Throwable caught) {
					MainPanel.log("newRelease error: " + caught.getMessage());
				}
			});
		else if (actionId.equals("document.classifyDoc")) {
			adb = new ClassifyDocument(r_object_id);
		}
		if (adb != null) {
			// <- popup content changes here
			adb.show();
			// Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			// public void execute() {
			// adb.center();
			// }
			// });
		}
	}

	public String getFolderPath() {
		return txtFolderLb.getSelectedItemText();
	}

	public void refreshLastSelectedNode() {
		// String r_object_id = model.selectionModel.getLastSelected();
		if (r_object_id != null && !r_object_id.equals(""))
			model.refreshDocument(r_object_id);
	}

	public void refreshLastSelectedNodeByChronicleId() {
		// String r_object_id = model.selectionModel.getLastSelected();
		if (i_chronicle_id != null && !i_chronicle_id.equals("")) {
			model.refreshDocument(i_chronicle_id);
		}
	}

	public void setRObjectId(Document doc) {
		if (doc != null) {
			selectedDocument = doc;
			this.r_object_id = doc.r_object_id;
			this.i_chronicle_id = doc.i_chronicle_id;
			this.documentStateId = doc.state_id;
			// MainPanel.log("r_object_id from: " +
			// ExplorerPanel.getExplorerInstance().r_object_id + " to " +
			// doc.r_object_id);
			// MainPanel.log("i_chronicle_id from: " +
			// ExplorerPanel.getExplorerInstance().i_chronicle_id + " to " +
			// doc.i_chronicle_id);
		} else {
			selectedDocument = null;
			this.r_object_id = null;
			this.i_chronicle_id = null;
			this.documentStateId = null;
			// MainPanel.log("Clear.");
			//
		}
	}

	public List<String> getCheckedObjects() {
		ArrayList<String> al = new ArrayList<String>();
		for (String checked_r_object_id : CustomTreeModel.selectionModel.getSelectedSet()) {
			al.add(checked_r_object_id);
		}
		return al;
	}

	public void clearSelectedSet() {
		CustomTreeModel.selectionModel.clear();
		setRObjectId(null);
	}

	public static native String getStyle(Element el, String styleProp) /*-{
		var camelize = function(str) {
			return str.replace(/\-(\w)/g, function(str, letter) {
				return letter.toUpperCase();
			});
		};

		if (el.currentStyle) {
			return el.currentStyle[camelize(styleProp)];
		} else if (document.defaultView
				&& document.defaultView.getComputedStyle) {
			return document.defaultView.getComputedStyle(el, null)
					.getPropertyValue(styleProp);
		} else {
			return el.style[camelize(styleProp)];
		}
	}-*/;

	/*
	 * Collects parents going up the element tree, terminated at the tree root.
	 */
	private void collectElementChain(ArrayList<Element> chain, Element hRoot, Element hElem) {
		if ((hElem == null) || (hElem == hRoot)) {
			return;
		}

		collectElementChain(chain, hRoot, hElem.getParentElement());
		chain.add(hElem);
	}

	public void selectDocument(Document toBeSelected) {

		if (selectedDocument != null) {
			MyAsyncDataProvider adp = model.getDataProviderThatHandlesDoc(selectedDocument.r_object_id, selectedDocument.i_chronicle_id);
			if (adp != null) {
				for (Document doc : adp.documents) {
					if (doc.r_object_id == selectedDocument.r_object_id || doc.i_chronicle_id == selectedDocument.i_chronicle_id) {
						doc.isHighlighted = false;
					}
				}
				adp.updateRowData(0, adp.documents);
			}
		}

		if (toBeSelected != null) {
			// refresh UI to show selected document permanently
			if (model.allDataProviders != null) {
				for (CustomTreeModel.MyAsyncDataProvider adp : model.allDataProviders) {
					if (adp.documents != null) {
						for (Document doc : adp.documents) {
							if (doc.r_object_id == toBeSelected.r_object_id || doc.i_chronicle_id == toBeSelected.i_chronicle_id) {
								doc.isHighlighted = true;
							}
						}
						adp.updateRowData(0, adp.documents);
					}
				}
				setRObjectId(toBeSelected);
				showActionsAndProperties(r_object_id);
			}
		} else {

		}
	}

	public void showActionsAndProperties(String r_object_id) {

		ExplorerPanel.this.hpActions.clear();
		explorerService.getActionsForObject(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id,
				new AsyncCallback<List<Action>>() {

					@Override
					public void onFailure(Throwable caught) {
						MainPanel.log(caught.getMessage());
					}

					@Override
					public void onSuccess(List<Action> result) {
						if (result.size() > 0) {
							for (Action action : result) {
								Button butnAction = new Button(action.getName());
								butnAction.addClickHandler(new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										// TODO Auto-generated method stub
										ExplorerPanel.this.runAction(action.getId());
									}
								});
								ExplorerPanel.this.hpActions.add(butnAction);
							}
						}
					}
				});

		if (ExplorerPanel.this.fpProperties.getWidget() != null)
			ExplorerPanel.this.fpProperties.remove(ExplorerPanel.this.fpProperties.getWidget());

		double t1 = JsDate.create().getTime();
		explorerService.getProfileAttributesAndValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id,
				new AsyncCallback<ProfileAttributesAndValues>() {

					@Override
					public void onSuccess(ProfileAttributesAndValues result) {
						TabPanel tpAtts = new TabPanel();
						tpAtts.setWidth("100%");
						tpAtts.addSelectionHandler(new SelectionHandler<Integer>() {

							@Override
							public void onSelection(SelectionEvent<Integer> event) {
								// TODO Auto-generated method stub
								lastSelectedPropertiesTab = event.getSelectedItem();
							}
						});

						List<Tab> tbs = result.profile.tabs;

						if (tbs != null && result.attributes != null && result.values.size() > 0) {
							for (Tab tab : tbs) {
								Grid g = new Grid(tab.row, tab.col);
								for (Attribute att : result.attributes) {
									if (tab.getId().equals(att.tabId)) {
										// logger.info(att.dcmtAttName);
										FormAttribute fa = new FormAttribute(att);
										List<String> values = result.values.get(att.dcmtAttName);
										if (values != null)
											fa.setValue(values);
										else
											fa.att.isReadOnly = true;
										try {
											g.setWidget(att.getRow(), att.getCol(), fa);
										} catch (Exception ex) {
											MainPanel.log("Error putting attribute: " + att.dcmtAttName + " to tab: " + tab.getId() + " row:" + att.getRow() + " col: " + att.getCol());
										}
										fa.setWidth("95%");
									}
								}
								tpAtts.add(g, tab.getParameter());

							}
							tpAtts.selectTab(lastSelectedPropertiesTab);
							ScrollPanel sp = new ScrollPanel(tpAtts);

							tpAtts.getElement().setId("tpAtts");
							if (ExplorerPanel.this.fpProperties.getWidget() != null)
								ExplorerPanel.this.fpProperties.remove(ExplorerPanel.getExplorerInstance().fpProperties.getWidget());
							ExplorerPanel.this.fpProperties.add(sp);

							double t2 = JsDate.create().getTime();

							logger.info("Show properties took: " + (t2 - t1) + " ms.");
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						MainPanel.log(caught.getMessage());
						logger.info(caught.getMessage());
					}
				});

	}

	public void hideTxtFolder() {
		vp.remove(hpFolder);
	}

}
