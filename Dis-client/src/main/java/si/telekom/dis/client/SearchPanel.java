package si.telekom.dis.client;

import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Event;

import si.telekom.dis.shared.Document;

public class SearchPanel extends ExplorerPanel {
	static SearchPanel searchPanelInstance;

	//
	public SearchPanel() {
		super();
		this.hideTxtFolder();
	}

	public void runReadDqlQuery(String dql) {
		this.model = new CustomTreeModel(dql, this);

		int size = MainPanel.getInstance().us.searchReturnResultCount;
		this.cellTree = new MyCellTree<Document>(this.model, null, dql, this, size);

		cellTree.setDefaultNodeSize(this.model.length);

		SearchPanel.getExplorerInstance().sinkEvents(Event.ONCONTEXTMENU);
		cellTree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellTree.sinkEvents(Event.ONCONTEXTMENU);

		if (scrollPanel.getWidget() != null)
			scrollPanel.remove(scrollPanel.getWidget());
		scrollPanel.add(cellTree);

	}

	public static SearchPanel getSearchPanelInstance() {
		if (searchPanelInstance == null) {
			searchPanelInstance = new SearchPanel();
		}
		return searchPanelInstance;
	}

}
