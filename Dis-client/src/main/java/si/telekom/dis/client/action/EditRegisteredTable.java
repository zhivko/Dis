package si.telekom.dis.client.action;

import java.util.ArrayList;
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
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;

import si.telekom.dis.client.ActionDialogBox;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MySimplePager;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ServerException;

public class EditRegisteredTable extends ActionDialogBox implements ClickHandler {
	private static final AdminServiceAsync adminService = GWT.create(AdminService.class);
	public static EditRegisteredTable instance;

	CellTable<Row> cellTable;
	List<Row> rows = new ArrayList<EditRegisteredTable.Row>();
	String regTableId;

	public EditRegisteredTable(String regTableId) {
		super();
		this.regTableId = regTableId;

		setText("Urejanje vsebine registrirane tabele: " + regTableId);
		setAnimationEnabled(false);
		setGlassEnabled(true);

		getOkButton().addClickHandler(this);

		cellTable = new MyCellTable();

		createEditTableCols(true);

		MySimplePager pager;
		MySimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new MySimplePager(TextLocation.RIGHT, pagerResources, false, 0, true);
		pager.setDisplay(cellTable);
		pager.setPageSize(10);
		pager.setWidth("100%");

		MyDataProvider dataProvider = new MyDataProvider(regTableId);
		dataProvider.addDataDisplay(cellTable);

		// cellTable.setRowCount(pager.getPageSize(), true);
		// cellTable.setRowData(0, rows);
		cellTable.setWidth("700px");
		cellTable.setHeight("700px");

		getPanel().add(cellTable);
		getPanel().add(pager);

		getOkButton().addClickHandler(this);
		this.setAnimationEnabled(false);
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

	private final void createEditTableCols(boolean isSelectAll) {
		// status

		try {

			adminService.getRegTableFieldNames(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, regTableId,
					new AsyncCallback<List<String>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							MainPanel.log(caught.getMessage());
						}

						@Override
						public void onSuccess(List<String> columnNames) {
							try {

								adminService.getRegTableValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, regTableId, 0, 1,
										new AsyncCallback<List<List<String>>>() {
											@Override
											public void onSuccess(List<List<String>> lines) {
												MainPanel.log("Branje vrstic tabele: " + regTableId + " ok. Vrstic: " + lines.size());
												MainPanel.log("values read OK.");

												ArrayList<Row> rows = new ArrayList<Row>();
												for (List<String> list : lines) {
													Row row = new Row(list);
													rows.add(row);
												}

												for (Row row : rows) {
													for (int i = 0; i < row.values.size(); i++) {
														final int j = i;
														Column<Row, String> col = new Column<Row, String>(new EditTextCell()) {
															@Override
															public String getValue(Row row) {
																return row.values.get(j);
															}
														};
														cellTable.addColumn(col, columnNames.get(j));
														col.setFieldUpdater(new FieldUpdater<Row, String>() {
															@Override
															public void update(int index, Row object, String value) {
																// TODO Auto-generated method stub
																// this.update(index, object, value);
																updateRow(index, object, value, j);
															}
														});
													}
												}
												Scheduler.get().scheduleDeferred(new ScheduledCommand() {
													@Override
													public void execute() {
														EditRegisteredTable.this.center();
													}
												});
											}

											@Override
											public void onFailure(Throwable caught) {
												MainPanel.log("Error: " + caught.getMessage());
											}
										});
							} catch (ServerException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					});

		} catch (ServerException e) {
			// TODO Auto-generated catch block
			MainPanel.log(e.getMessage());
		}

	}

	/**
	 * A custom {@link AsyncDataProvider}.
	 */
	private static class MyDataProvider extends AsyncDataProvider<Row> {
		/**
		 * {@link #onRangeChanged(HasData)} is called when the table requests a new
		 * range of data. You can push data back to the displays using
		 * {@link #updateRowData(int, List)}.
		 */
		String regTableId;

		public MyDataProvider(String regTableId) {
			this.regTableId = regTableId;
		}

		@Override
		protected void onRangeChanged(HasData<Row> display) {
			// Get the new range.
			final Range range = display.getVisibleRange();

			try {
				adminService.getRegTableValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, regTableId, range.getStart(),
						range.getLength(), new AsyncCallback<List<List<String>>>() {
							@Override
							public void onSuccess(List<List<String>> lines) {
								MainPanel.log("Branje vrstic tabele: " + regTableId + " ok. Vrstic: " + lines.size());
								MainPanel.log("values read OK.");

								ArrayList<Row> rows = new ArrayList<Row>();
								for (List<String> list : lines) {
									Row row = new Row(list);
									rows.add(row);
								}
								updateRowData(range.getStart(), rows);
								Scheduler.get().scheduleDeferred(new ScheduledCommand() {
									@Override
									public void execute() {
										EditRegisteredTable.instance.center();
									}
								});
							}

							@Override
							public void onFailure(Throwable caught) {
								MainPanel.log("Error: " + caught.getMessage());
							}
						});
			} catch (ServerException e) {
				// TODO Auto-generated catch block
				MainPanel.log(e.getMessage());
			}

		}
	}

	private static class MyCellTable extends CellTable<Row> implements HasData<Row> {
		public MyCellTable() {
			super();
		}
	}

	private void updateRow(int index, Row object, String value, int columnIndex) {
		adminService.updateRegTableRow(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, regTableId, value, columnIndex,
				object.getValues().get(0), new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						MainPanel.log(caught.getMessage());
					}

					@Override
					public void onSuccess(Void result) {
						MainPanel.log("Update ok.");
					}
				});
	}
}
