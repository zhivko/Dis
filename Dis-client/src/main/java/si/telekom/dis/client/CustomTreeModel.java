package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.TreeViewModel;

import si.telekom.dis.shared.Document;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class CustomTreeModel implements TreeViewModel {
	public int length = 20;
	public String startFolder = "";
	Images images = (Images) GWT.create(Images.class);
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	static final Logger logger = java.util.logging.Logger.getLogger("mylogger");
	// final ListDataProvider<Document> dataProvider = new
	// ListDataProvider<Document>();
	Cell<Document> cell;
	DefaultNodeInfo dni;

	DivElement contentContainer;
	/**
	 * This selection model is shared across all leaf nodes. A selection model can
	 * also be shared across all nodes in the tree, or each set of child nodes can
	 * have its own instance. This gives you flexibility to determine how nodes
	 * are selected.
	 */
	// public static final SingleSelectionModel<String> selectionModel = new
	// SingleSelectionModel<String>();
	public static final MyMultiSelectionModel<String> selectionModel = new MyMultiSelectionModel<String>();

	public List<MyAsyncDataProvider> allDataProviders;
	private ExplorerPanel explorerOrSearch;

	public CustomTreeModel() {
		allDataProviders = new ArrayList<MyAsyncDataProvider>();
	}

	public CustomTreeModel(String startFolder_, ExplorerPanel explorerOrSearch_) {
		this();
		this.startFolder = startFolder_;
		this.explorerOrSearch = explorerOrSearch_;
		
		if(explorerOrSearch_.getClass().getName().endsWith("SearchPanel"))
			this.length = MainPanel.getInstance().us.searchReturnResultCount;

		if(explorerOrSearch_.getClass().getName().endsWith("ExplorerPanel"))
			this.length = MainPanel.getInstance().us.explorerReturnResultCount;
	}

	/**
	 * Get the {@link NodeInfo} that provides the children of the specified value.
	 */
	public <T> NodeInfo<?> getNodeInfo(T value) {

		if (value == null) {
			final MyAsyncDataProvider dataProvider = getDataProvider(null, null);
			// LEVEL 0.
			// We passed null as the root value. Return the cabinets.
			cell = new CustomDocumentCell(selectionModel, explorerOrSearch);
			dni = new DefaultNodeInfo<Document>(dataProvider, cell);
			return dni;
		} else if (value instanceof Document) {
			Document doc = (Document) value;
			final MyAsyncDataProvider dataProvider = getDataProvider(doc.r_object_id, doc.i_chronicle_id);
			cell = new CustomDocumentCell(selectionModel, explorerOrSearch);
			dni = new DefaultNodeInfo<Document>(dataProvider, cell);
			return dni;
		}

		return null;
	}

	/**
	 * Check if the specified value represents a leaf node. Leaf nodes cannot be
	 * opened.
	 */
	public boolean isLeaf(Object value) {
		if (value instanceof Document) {
			// The leaf nodes are the songs, which are Strings.
			Document doc = (Document) value;
			if (!(doc.type_name.equals("dm_cabinet") || doc.type_name.equals("dm_folder"))) {
				return true;
			}
		}
		return false;
	}

	public MyAsyncDataProvider getDataProvider(String r_object_id, String i_chronicle_id) {

		if (r_object_id == null) {
			if (allDataProviders.size() > 0) {
				Logger.getLogger(this.getClass().getName()).info("returned " + 0 + ". MyAsyncDataProvider");
				return allDataProviders.get(0);
			}
		}

		int i = 0;
		for (MyAsyncDataProvider dataProvider : allDataProviders) {
			if (dataProvider.documents.size() > 0) {
				if (dataProvider.r_object_id != null) {
					if (dataProvider.r_object_id.equals(r_object_id) || dataProvider.i_chronicle_id.equals(i_chronicle_id)) {
						return dataProvider;
					}
				}
				// for (Document doc : dataProvider.documents) {
				// if (doc.r_object_id.equals(r_object_id) ||
				// doc.i_chronicle_id.equals(i_chronicle_id)) {
				// Logger.getLogger(this.getClass().getName()).info("returned " + i + ".
				// MyAsyncDataProvider");
				// return dataProvider;
				// }
				// }
			}

			i++;
		}

		MyAsyncDataProvider dataProvider = new MyAsyncDataProvider(r_object_id, i_chronicle_id);
		allDataProviders.add(dataProvider);
		return dataProvider;
	}

	public MyAsyncDataProvider getDataProviderThatHandlesDoc(String r_object_id, String i_chronicle_id) {
		for (MyAsyncDataProvider dataProvider : allDataProviders) {
			for (Document document : dataProvider.documents) {
				if (document.r_object_id.equals(r_object_id) || document.i_chronicle_id.equals(i_chronicle_id)) {
					return dataProvider;
				}
			}
		}
		return null;
	}

	private ImageResource createImageResource(final Image image) {

		return new ImageResource() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isAnimated() {
				return true;
			}

			@Override
			public int getWidth() {
				return image.getWidth();
			}

			@Override
			public String getURL() {
				// TODO Auto-generated method stub
				return image.getUrl();
			}

			@Override
			public int getTop() {
				// TODO Auto-generated method stub
				return image.getAbsoluteTop();
			}

			@Override
			public int getLeft() {
				// TODO Auto-generated method stub
				return image.getAbsoluteLeft();
			}

			@Override
			public int getHeight() {
				// TODO Auto-generated method stub
				return image.getHeight();
			}

			@Override
			public SafeUri getSafeUri() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	public void refreshDocument(String r_object_id) {
		MainPanel.log("refreshing document...");

		if (r_object_id != null && !r_object_id.equals("")) {
			explorerService.getObjects(r_object_id, true, MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, 1, 1,
					new AsyncCallback<List<Document>>() {
						@Override
						public void onSuccess(List<Document> result) {
							// TODO Auto-generated method stub
							// pd.hideit();
							GWT.log("retrieved " + result.size() + " documents.");
							
							MyAsyncDataProvider myAsyncDataProvider = getDataProviderThatHandlesDoc(result.get(0).r_object_id, result.get(0).i_chronicle_id);

							int i = 0;
							for (Document document : myAsyncDataProvider.documents) {
								String r_object_id1 = document.r_object_id;
								String chronicleId = document.i_chronicle_id;
								String resultRobject_Id = result.get(0).r_object_id;
								String resultChronicleId = result.get(0).i_chronicle_id;

								if (document.r_object_id.equals(result.get(0).r_object_id) || document.i_chronicle_id.equals(result.get(0).i_chronicle_id)) {
									int ind = myAsyncDataProvider.documents.indexOf(document);
									myAsyncDataProvider.documents.set(ind, result.get(0));

									final int j = i;

									Scheduler.get().scheduleDeferred(new Command() {
										@Override
										public void execute() {
											List<Document> al = new ArrayList<Document>();
											al.add(result.get(0));
											myAsyncDataProvider.updateRowData(j, al);

											if (explorerOrSearch.i_chronicle_id.equals(result.get(0).i_chronicle_id)) {
												explorerOrSearch.selectDocument(result.get(0));
											}
										}
									});

									break;
								}
								i++;
							}

						}

						@Override
						public void onFailure(Throwable caught) {
							// pd.hideit();
							logger.info("Error: " + caught.getMessage());
						}
					});

		}
	}

	public class MyAsyncDataProvider extends AsyncDataProvider<Document> {
		public ArrayList<Document> documents;
		String r_object_id;
		String i_chronicle_id;

		public MyAsyncDataProvider(String r_object_id_, String i_chronicle_id_) {
			this.r_object_id = r_object_id_;
			this.i_chronicle_id = i_chronicle_id_;
			this.documents = new ArrayList<Document>();
		}

		@Override
		protected void onRangeChanged(HasData<Document> display) {

			Logger.getLogger(this.getClass().getName()).info("Range changed start for: " + r_object_id + " " + (documents.size()) + " length: " + length);

			String what = null;
			if (r_object_id == null)
				what = startFolder;
			else
				what = r_object_id;

			final String whatId = what;

			String oldCursorValue = MainPanel.getInstance().getElement().getStyle().getCursor();
			Cursor oldCursor = Cursor.valueOf(Cursor.DEFAULT.name());
			MainPanel.getInstance().getElement().getStyle().setCursor(Cursor.WAIT);

			if (!startFolder.toLowerCase().trim().startsWith("select")) {
				explorerService.getObjects(whatId, true, MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, documents.size(), length,
						new AsyncCallback<List<Document>>() {
							@Override
							public void onSuccess(List<Document> result) {
								MainPanel.getInstance().getElement().getStyle().setCursor(oldCursor);
								MainPanel.log("documents size: " + documents.size() + " received: " + result.size());
								if (documents.size() == 0) {
									documents.addAll(0, result);
									if (result.size() > 0) {
										Scheduler.get().scheduleDeferred(new Command() {
											@Override
											public void execute() {
												MenuPanel.activeExplorerInstance.selectDocument(documents.get(0));
											}
										});
									}
								} else
									documents.addAll(documents.size(), result);
								updateRowData(0, documents);

								Scheduler.get().scheduleDeferred(new Command() {
									@Override
									public void execute() {
										if (result.size() == CustomTreeModel.this.length) {
											makeShowMoreVisible(whatId, ExplorerPanel.getExplorerInstance().cellTree);
										} else
											makeShowMoreInVisible(whatId, ExplorerPanel.getExplorerInstance().cellTree);
									}
								});
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.getInstance().getElement().getStyle().setCursor(oldCursor);
								MainPanel.log("Error: " + caught.getMessage());
							}
						});
			} else {
				// search query
				explorerService.runSearchQuery(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, startFolder,
						ParametrizedQueryPanel.lastParametrizedQuery, documents.size(), length, new AsyncCallback<List<Document>>() {
							@Override
							public void onSuccess(List<Document> result) {
								MainPanel.getInstance().getElement().getStyle().setCursor(oldCursor);

								if (documents.size() == 0) {
									documents.addAll(0, result);
									if (result.size() == 0) {
										MenuPanel.activeExplorerInstance.fpProperties.clear();
										MenuPanel.activeExplorerInstance.hpActions.clear();
									}
									if (documents.size() > 0)
										MenuPanel.activeExplorerInstance.selectDocument(documents.get(0));
								} else {
									documents.addAll(documents.size(), result);
								}

								updateRowData(0, documents);

								Scheduler.get().scheduleDeferred(new Command() {
									@Override
									public void execute() {
										int resultSize = result.size();
										int ctmLength = CustomTreeModel.this.length;

										if (result.size() == CustomTreeModel.this.length) {
											makeShowMoreVisible(whatId, SearchPanel.getSearchPanelInstance().cellTree);
										} else
											makeShowMoreInVisible(whatId, SearchPanel.getSearchPanelInstance().cellTree);
									}
								});
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.getInstance().getElement().getStyle().setCursor(oldCursor);
								MainPanel.log("Error: " + caught.getMessage());
							}
						});
			}
		}

		public void update(List<Document> objs) {// Call update when you wanted to
																							// refresh
			// tree
			for (HasData<Document> disp : getDataDisplays()) {
				this.onRangeChanged(disp);
				break;
			}
		}

	}

	/**
	 * Ensure that the content container exists and return it.
	 * 
	 * @param cellTree
	 *
	 * @return the content container
	 */
	public void makeShowMoreVisible(String r_object_id, CellTree cellTree) {
		showOrHide(getShowMoreElement(r_object_id, cellTree), true);
	}

	public void makeShowMoreInVisible(String r_object_id, CellTree cellTree) {
		showOrHide(getShowMoreElement(r_object_id, cellTree), false);
	}

	public Element getShowMoreElement(String r_object_id, CellTree cellTree) {
		// ArrayList<Element> chain = new ArrayList<Element>();

		Element container = null;
		String firstChar = r_object_id.substring(0, 1);
		boolean isFolderPath = r_object_id.startsWith("/") || r_object_id == ("") || r_object_id.toLowerCase().trim().startsWith("select ");
		if (!isFolderPath) {
			Element element = com.google.gwt.dom.client.Document.get().getElementById(r_object_id);
			if (element != null) {
				// Logger.getLogger(this.getClass().getName()).info(element.getPropertyString("outerHTML"));
				Element parentEl1 = element.getParentElement().getParentElement();
				// Logger.getLogger(this.getClass().getName()).info(parentEl1.getPropertyString("outerHTML"));
				Element parentEl2 = parentEl1.getParentElement();
				// Logger.getLogger(this.getClass().getName()).info(container.getPropertyString("outerHTML"));
				container = (Element) parentEl2.getNextSiblingElement();
			} else {
				container = cellTree.getElement(); // ExplorerPanel.getExplorerInstance().cellTree.getElement();
			}
		} else {
			// first level node
			container = cellTree.getElement(); // ExplorerPanel.getExplorerInstance().cellTree.getElement();
			// Logger.getLogger(this.getClass().getName()).info(container.getPropertyString("outerHTML"));
		}

		if (container != null) {
			Element showMore = findElements(container).get(0);
			return showMore;
		}

		return null;
	}

	/***
	 * Returns a List of Element objects that have the specified CSS class name.
	 * 
	 * @param element
	 *          Element to start search from
	 * @param className
	 *          name of class to find
	 * @return
	 */
	public static List<Element> findElements(Element element) {
		ArrayList<Element> result = new ArrayList<Element>();
		findShowMore(result, element);
		return result;
	}

	private static void findShowMore(ArrayList res, Element element) {
		String c;

		if (element == null) {
			return;
		}

		if (element.getInnerText().equals("Show more")) {
			res.add(element);
		}

		for (int i = 0; i < DOM.getChildCount(element); i++) {
			Element child = DOM.getChild(element, i);
			findShowMore(res, child);
		}
	}

	/**
	 * Show or hide an element.
	 *
	 * @param element
	 *          the element to show or hide
	 * @param show
	 *          true to show, false to hide
	 */
	private static void showOrHide(Element element, boolean show) {
		if (element != null)
			if (show) {
				element.getStyle().clearDisplay();
			} else {
				element.getStyle().setDisplay(Display.NONE);
			}
	}

}
