package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MySimplePager;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.client.action.EditColIds.Row;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.ServerException;

public class ShowPdfTags extends WindowBox implements ClickHandler {
	private static final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private static final ExplorerServiceAsync explorerImpl = GWT.create(ExplorerService.class);
	public static ShowPdfTags instance;

	CellTable<Row> cellTable;
	List<Row> rows = new ArrayList<ShowPdfTags.Row>();
	SingleSelectionModel<Row> selectionModel;
	MySimplePager pager;

	public ShowPdfTags(String r_object_id) {
		super();
		

		explorerImpl.showPdfTags(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id,
				new AsyncCallback<List<List<String>>>() {
			public void onSuccess(List<List<String>> result) {
						ShowPdfTags.this.setText("Pdf tagi");

						setAnimationEnabled(false);
						setGlassEnabled(true);

						getOkButton().addClickHandler(ShowPdfTags.this);

						cellTable = new MyCellTable();

						createEditTableCols();

						MySimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
						pager = new MySimplePager(TextLocation.RIGHT, pagerResources, false, 0, true);
						pager.setDisplay(cellTable);
						pager.setPageSize(20);
						pager.setWidth("100%");

						rows = new ArrayList<Row>();
						for (List<String> list : result) {
							Row row = new Row(list);
							rows.add(row);
						}
						cellTable.setRowData(rows);
						
						cellTable.setWidth("700px");
						cellTable.setHeight("700px");

						selectionModel = new SingleSelectionModel<Row>(Row.KEY_PROVIDER);
						//cellTable.setSelectionModel(selectionModel, DefaultSelectionEventManager.<Row> createCheckboxManager());
						cellTable.setSelectionModel(selectionModel);
						selectionModel.addSelectionChangeHandler(new Handler() {
							
							@Override
							public void onSelectionChange(SelectionChangeEvent arg0) {
								MainPanel.log(selectionModel.getSelectedObject().values.get(0));
							}
						});
						
						
						getPanel().add(cellTable);
						getPanel().add(pager);

						getOkButton().addClickHandler(ShowPdfTags.this);
						ShowPdfTags.this.setAnimationEnabled(false);
						
						Scheduler.get().scheduleDeferred(new ScheduledCommand() {
							@Override
							public void execute() {
								ShowPdfTags.instance.center();
							}
						});

					};

					public void onFailure(Throwable caught) {
						MainPanel.log(caught.getMessage());
					}

				});

		instance = this;
	}

	public static final native String[] split(String string, String separator) /*-{
																																							return string.split(separator);
																																							}-*/;

	@Override
	public void onClick(ClickEvent event) {
		// OK pressed
	}

	/**
	 * Information about a row.
	 */
	public static class Row implements Comparable<Row> {

		/**
		 * The key provider that provides the unique ID of a contact.
		 */
		public static final ProvidesKey<Row> KEY_PROVIDER = new ProvidesKey<Row>() {
			@Override
			public Object getKey(Row item) {
				return item == null ? null : item.values.get(0);
			}
		};

		private static int nextId = 0;

		private List<String> values;

		public Row(List<String> vals) {
			this.values = vals;
		}

		@Override
		public int compareTo(Row o) {
			return (o == null || o.values == null) ? -1 : -o.values.get(0).compareTo(values.get(0));
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Row) {
				return ((Row) o).values.get(0) == ((Row) o).values.get(0);
			}
			return false;
		}

		public void update() {

		}

		public List<String> getValues() {
			// TODO Auto-generated method stub
			return values;
		}

	}
	
	private final void createEditTableCols() {
		Column<Row, String> tagName = addColumn(0, "tag name");
		Column<Row, String> tagValue = addColumn(1, "tag value");

		cellTable.setColumnWidth(tagName, "200px");
		cellTable.setColumnWidth(tagValue, "600px");
		tagName.setSortable(true);
		tagName.setDefaultSortAscending(true);
	}


	private static class MyCellTable extends CellTable<Row> implements HasData<Row> {
		public MyCellTable() {
			super();
		}
	}

	public Column<Row, String> addColumn(final int colNo, String fieldName) {
		Column<Row, String> col = new Column<Row, String>(new EditTextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(colNo);
			}
		};
		cellTable.addColumn(col, fieldName);
		return col;

	}
}
