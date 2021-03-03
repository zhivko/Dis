package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.CellTree.CellTreeMessages;
import com.google.gwt.user.cellview.client.CellTree.Resources;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.TreeViewModel;

import si.telekom.dis.client.CustomTreeModel.MyAsyncDataProvider;
import si.telekom.dis.shared.Action;
import si.telekom.dis.shared.Document;

public class MyCellTree<T> extends CellTree {
	private final static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("mylogger");

	String objectIdOrDql;
	VerticalPanel vp1;
	PopupPanel popupMenu;
	ExplorerPanel explorerOrSearch;
	final ImagesFormats imagesFormats = (ImagesFormats) GWT.create(ImagesFormats.class);
	
	
	public MyCellTree(TreeViewModel viewModel, T rootValue, String objectIdOrDql, ExplorerPanel explorerOrSearch_, int size) {
		super(viewModel, rootValue, GWT.create(CellTree.Resources.class), GWT.create(CellTree.CellTreeMessages.class), size);
		// super(viewModel, rootValue);
		this.setDefaultNodeSize(size);
		this.objectIdOrDql = objectIdOrDql;

		popupMenu = new PopupPanel();
		vp1 = new VerticalPanel();
		popupMenu.setModal(true);
		popupMenu.setAutoHideEnabled(true);
		popupMenu.add(vp1);
		explorerOrSearch = explorerOrSearch_;
	}

	@Override
	public void onBrowserEvent(Event event) {
		switch (DOM.eventGetType(event)) {
		case Event.ONCONTEXTMENU:
			String loginName = MainPanel.getInstance().loginName;
			String loginPass = MainPanel.getInstance().loginPass;

			vp1.clear();
			Label lblCheckAll = new Label("Označi vse");
			lblCheckAll.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					for (MyAsyncDataProvider dp : MenuPanel.activeExplorerInstance.model.allDataProviders) {
						for (Document doc : dp.documents) {
							if (!CustomTreeModel.selectionModel.getSelectedSet().contains(doc.r_object_id)) {
								CustomTreeModel.selectionModel.setSelected(doc.r_object_id, true);
							}
						}
						dp.updateRowData(0, dp.documents);
					}
				}
			});
			lblCheckAll.setStyleName("popUpItem");

			Label lblUnCheckAll = new Label("Odznači vse");
			lblUnCheckAll.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					for (MyAsyncDataProvider dp : MenuPanel.activeExplorerInstance.model.allDataProviders) {
						for (Document doc : dp.documents) {
							CustomTreeModel.selectionModel.getSelectedSet().clear();
						}
						dp.updateRowData(0, dp.documents);
					}
				}
			});
			lblUnCheckAll.setStyleName("popUpItem");

			String headerString = getMenuItemHtml("Izvozi v TSV",  imagesFormats.f_excel());
			HTML htmlExportToTSV = new HTML(headerString);
			htmlExportToTSV.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					String csvData = "";
					boolean alreadyAddedHeader = false;
					int docCount = 0;
					for (MyAsyncDataProvider dp : MenuPanel.activeExplorerInstance.model.allDataProviders) {

						for (Document doc : dp.documents) {
							if (!doc.isFolder) {
								if (!alreadyAddedHeader) {

									csvData = csvData + "typeName" + "\t";
									csvData = csvData + "objectName" + "\t";
									csvData = csvData + "releaseNo" + "\t";

									for (String attName : doc.attributes.keySet()) {
										csvData = csvData + attName + "\t";
									}
									csvData = csvData.substring(0, csvData.length() - 1);
									csvData = csvData + "\n";
									alreadyAddedHeader = true;
								}

								csvData = csvData + "\"" + doc.type_name + "\"" +"\t";
								csvData = csvData + "\"" + doc.object_name + "\"" + "\t";
								csvData = csvData + "\"" + doc.releaseNo + "\"" + "\t";
								for (String attName : doc.attributes.keySet()) {
									String values = "";
									for (String value : doc.attributes.get(attName)) {
										values = values + value + "#";
									}
									if (values.length() > 0)
										values = values.substring(0, values.length() - 1);
									csvData = csvData + "\"" + values + "\"\t";
								}
								csvData = csvData.substring(0, csvData.length() - 1);
								docCount++;
								csvData = csvData + "\n";
							}
						}
					}
					writeTextToClipboard(csvData);
					MainPanel.log("Added "+docCount+" documents data to clipboard as tab separated file.  Paste it (Ctrl+V) in Excel or Libreoffice CALC.");
					popupMenu.hide();
				}
			});
			htmlExportToTSV.setStyleName("popUpItem");

			Label lblDownloadChecked = new Label("Prenesi izbrane dokumente");
			lblDownloadChecked.setStyleName("popUpItem");
			ClickHandler massExportClickHandler = new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					List<String> list = new ArrayList<String>();
					for (String r_object_id : CustomTreeModel.selectionModel.getSelectedSet()) {
						list.add(r_object_id);
					}
					ExplorerPanel.explorerService.massDownloadContent(loginName, loginPass, list, new AsyncCallback<String>() {
						@Override
						public void onSuccess(String result) {
							String url = GWT.getHostPageBaseURL() + "WebUi2/explorerServ?loginName=" + MainPanel.getInstance().loginName + "&loginPassword="
									+ MainPanel.getInstance().loginPass + "&downloadZip=" + result;
							String safeUriDocView = UriUtils.fromString(url).asString();
							Window.open(URL.encode(safeUriDocView), "_self", "status=0,toolbar=0,menubar=0,location=0");
						}

						@Override
						public void onFailure(Throwable caught) {
							MainPanel.log("Error: " + caught.getMessage());
						}
					});
					popupMenu.hide();
				}
			};
			lblDownloadChecked.addClickHandler(massExportClickHandler);
			lblDownloadChecked.setStyleName("popUpItem");
			vp1.add(lblCheckAll);
			vp1.add(lblUnCheckAll);
			vp1.add(lblDownloadChecked);
			vp1.add(htmlExportToTSV);

			Label labelSeparator= new Label("-----------------");
			labelSeparator.setStyleName("popUpItem");
			vp1.add(labelSeparator);	
			
			ExplorerPanel.explorerService.getActionsForObject(loginName, loginPass, explorerOrSearch.r_object_id, new AsyncCallback<List<Action>>() {
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					logger.info(caught.getMessage());
				}

				@Override
				public void onSuccess(List<Action> result) {
					explorerOrSearch.hpActions.clear();
					if (result.size() > 0) {
						for (Action action : result) {
							Label lbl = new Label(action.getName());
							lbl.addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									popupMenu.hide();
									ExplorerPanel.getExplorerInstance().runAction(action.getId());
								}
							});
							lbl.setStyleName("popUpItem");
							vp1.add(lbl);

							Button butnAction = new Button(action.getName());
							butnAction.addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									ExplorerPanel.getExplorerInstance().runAction(action.getId());
								}
							});
							explorerOrSearch.hpActions.add(butnAction);
						}
						popupMenu.setPopupPosition(event.getClientX(), event.getClientY());
						popupMenu.show();
					}
				}
			});

			event.preventDefault();
			event.stopPropagation();

			break;
		default:
			break;

		}
		super.onBrowserEvent(event);

	}

	public native void writeTextToClipboard(String p_text)

	/*-{

		try

		{

			var _this = this;

			if ($wnd.navigator.clipboard)

			{

				console.log('navigator.clipboard.writeText()');

				console.log('document.hasFocus()=' + document.hasFocus());

				console.log('$doc.hasFocus()=' + $doc.hasFocus());

				var promise = $wnd.navigator.clipboard.writeText(p_text);

				var resolve = function(text) {

					console.log('navigator.clipboard.writeText ' + text);

				};

				var reject = function(reason) {

					console.log('navigator.clipboard.writeText failed: '
							+ reason);

				};

				promise["catch"](reject);

				promise.then(resolve, reject)["catch"](reject);

			}

			else

			{

				console
						.log('This browser does not support navigator.clipboard.');

			}

		}

		catch (e)

		{

			console.error(e, e.stack);

		}

	}-*/;

	public String getMenuItemHtml(String text, ImageResource image) {
		// Add the image and text to a horizontal panel
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(0);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.add(new Image(image));
		// HTML headerText = new HTML(text);
		// headerText.setStyleName("cw-StackPanelHeader");
		HorizontalPanel hp = new HorizontalPanel();
		hp.setWidth("5px");
		hPanel.add(hp);
		
		Label label = new Label(text);
		hPanel.add(label);

		// Return the HTML string for the panel
		return hPanel.getElement().getString();
	}	
	
}
