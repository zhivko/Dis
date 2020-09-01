package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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

	public MyCellTree(TreeViewModel viewModel, T rootValue, String objectIdOrDql, ExplorerPanel explorerOrSearch_) {
		super(viewModel, rootValue);
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

			explorerOrSearch.hpActions.clear();
			vp1.clear();
			
			Label lblCheckAll = new Label("Označi vse");
			lblCheckAll.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					for (MyAsyncDataProvider dp : MenuPanel.activeExplorerInstance.model.allDataProviders) {
						for (Document doc : dp.documents) {
							if (!CustomTreeModel.selectionModel.getSelectedSet().contains(doc.r_object_id))
							{
								CustomTreeModel.selectionModel.setSelected(doc.r_object_id,true);
							}
						}
						dp.updateRowData(0, dp.documents);
					}
				}
			});
			lblCheckAll.setStyleName("popUpItem");
			

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
							// TODO Auto-generated method stub

						}
					});

					popupMenu.hide();
				}
			};
			lblDownloadChecked.addClickHandler(massExportClickHandler);
			lblDownloadChecked.setStyleName("popUpItem");
			vp1.add(lblCheckAll);
			vp1.add(lblDownloadChecked);

			ExplorerPanel.explorerService.getActionsForObject(loginName, loginPass, explorerOrSearch.r_object_id, new AsyncCallback<List<Action>>() {
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					logger.info(caught.getMessage());
				}

				@Override
				public void onSuccess(List<Action> result) {
					if (result.size() > 0) {	
						for (Action action : result) {
							Label lbl = new Label(action.name);
							lbl.setStyleName("popUpItem");
							lbl.addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									popupMenu.hide();
									ExplorerPanel.getExplorerInstance().runAction(action.id);
								}
							});
							vp1.add(lbl);

							Button butnAction = new Button(action.name);
							butnAction.addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									ExplorerPanel.getExplorerInstance().runAction(action.id);
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

}
