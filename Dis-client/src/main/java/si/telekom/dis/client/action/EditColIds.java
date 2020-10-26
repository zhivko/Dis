package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
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
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.ServerException;

public class EditColIds extends WindowBox implements ClickHandler {
	private static final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private static final ExplorerServiceAsync explorerImpl = GWT.create(ExplorerService.class);
	public static EditColIds instance;

	CellTable<Row> cellTable;
	List<Row> rows = new ArrayList<EditColIds.Row>();
	int templateId;
	String title;
	SingleSelectionModel<Row> selectionModel;
	String templateName;
	MySimplePager pager;
	MyDataProvider dataProvider;

	public EditColIds(String r_object_id) {
		super();

		explorerImpl.getProfileAttributesAndValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id,
				new AsyncCallback<ProfileAttributesAndValues>() {
					public void onSuccess(ProfileAttributesAndValues result) {
						EditColIds.this.templateId = Integer.valueOf(result.values.get("mob_template_id").get(0));
						EditColIds.this.templateName = result.values.get("mob_short_name").get(0);
						EditColIds.this.title = result.values.get("title").get(0);
						EditColIds.this.setText("Urejanje polj predloge: <strong>" + title + "</strong> (" + templateId + ")");

						setAnimationEnabled(false);
						setGlassEnabled(true);

						getOkButton().addClickHandler(EditColIds.this);

						cellTable = new MyCellTable();

						createEditTableCols(true);

						MySimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
						pager = new MySimplePager(TextLocation.RIGHT, pagerResources, false, 0, true);
						pager.setDisplay(cellTable);
						pager.setPageSize(20);
						pager.setWidth("100%");

						dataProvider = new MyDataProvider(templateId);
						dataProvider.addDataDisplay(cellTable);

						// AsyncHandler columnSortHandler = new AsyncHandler(cellTable);
						// cellTable.addColumnSortHandler(columnSortHandler);
						cellTable.setWidth("700px");
						cellTable.setHeight("700px");

						selectionModel = new SingleSelectionModel<Row>(Row.KEY_PROVIDER);
						// cellTable.setSelectionModel(selectionModel,
						// DefaultSelectionEventManager.<Row> createCheckboxManager());
						cellTable.setSelectionModel(selectionModel);
						selectionModel.addSelectionChangeHandler(new Handler() {

							@Override
							public void onSelectionChange(SelectionChangeEvent arg0) {
								MainPanel.log(selectionModel.getSelectedObject().values.get(0));
							}
						});

						getPanel().add(cellTable);

						Button addButton = new Button("Dodaj vrstico");
						addButton.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								String[] rs = { "[new]", "[new]", "[new]" };
								int pos = 0;
								if (pager.getPageStart() + pager.getPageSize() > rows.size())
									pos = rows.size();
								else
									pos = pager.getPageStart() + pager.getPageSize();
								rows.add(pos, new Row(Arrays.asList(rs)));

								dataProvider.updateRowData(pager.getPageStart(), rows);
							}
						});

						Button deleteButton = new Button("Briši vrstico");
						deleteButton.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								Row selRow = (Row) selectionModel.getSelectedObject();
								if (selRow != null) {
									String col_id = selRow.values.get(0);
									try {
										adminService.deleteCollId(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, templateId, col_id,
												new AsyncCallback<Void>() {
													@Override
													public void onFailure(Throwable caught) {
														MainPanel.log("Error: " + caught.getMessage());
													}

													@Override
													public void onSuccess(Void result) {
														MainPanel.log("delete of coll_id " + col_id + " done.");
														selectionModel.clear();
														cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(), true);

														// refreshTable();
													}
												});
									} catch (ServerException ex1) {
										MainPanel.log("Error:_" + ex1.getMessage());
									}
								}
							}
						});

						Button transferButton = new Button("Transfer to other environments");
						transferButton.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								try {
									adminService.transferToAllEnvironments(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, templateId,
											new AsyncCallback<Void>() {
												@Override
												public void onFailure(Throwable caught) {
													MainPanel.log("Error:_" + caught.getMessage());
												}

												@Override
												public void onSuccess(Void result) {
													MainPanel.log("Transfer done.");
												}
											});
								} catch (ServerException ex1) {
									MainPanel.log("Error:_" + ex1.getMessage());
								}
							}
						});

						HorizontalPanel hpButtons = new HorizontalPanel();
						hpButtons.add(addButton);
						hpButtons.add(deleteButton);
						hpButtons.add(transferButton);

						getPanel().add(pager);

						getPanel().add(hpButtons);

						getOkButton().addClickHandler(EditColIds.this);
						EditColIds.this.setAnimationEnabled(false);
					};

					public void onFailure(Throwable caught) {
						MainPanel.log(caught.getMessage());
					};
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

	public void refreshTable() {
		selectionModel.clear();
		dataProvider.removeDataDisplay(cellTable);
		dataProvider.addDataDisplay(cellTable);
		dataProvider.refresh();
	}

	private final void createEditTableCols(boolean isSelectAll) {
		Column<Row, String> col_id = addColumn(0, "col_id");
		Column<Row, String> colName = addColumn(1, "col_name");

		cellTable.setColumnWidth(col_id, "200px");
		cellTable.setColumnWidth(colName, "600px");
		col_id.setSortable(true);
		col_id.setDefaultSortAscending(true);
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
		int templateId;
		public static MyDataProvider instance;

		public MyDataProvider(int tempId) {
			this.templateId = tempId;
			instance = this;
		}

		public void refresh() {
			onRangeChanged(EditColIds.instance.cellTable);
		}

		@Override
		protected void onRangeChanged(HasData<Row> display) {
			// Get the new range.
			final Range range = display.getVisibleRange();

			try {
				adminService.getColIdsForTemplate(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, templateId, range.getStart(),
						range.getLength(), new AsyncCallback<List<List<String>>>() {
							@Override
							public void onSuccess(List<List<String>> lines) {
								MainPanel.log("Branje vrstic tabele: " + templateId + " ok. Vrstic: " + lines.size());
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
										EditColIds.instance.center();
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

	private void updateRow(int index, Row object, int colNo, String columnName, String value) {
		if (!object.getValues().get(colNo).equals(value))
			adminService.updateCollId(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, templateId, templateName, object.values.get(0),
					columnName, value, new AsyncCallback<Void>() {
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

	public Column<Row, String> addColumn(final int colNo, String fieldName) {
		Column<Row, String> col = new Column<Row, String>(new TextInputCell()) {
			@Override
			public void render(Context context, Row object, SafeHtmlBuilder sb) {
				// TODO Auto-generated method stub
				super.render(context, object, sb);
			}

			@Override
			public String getValue(Row row) {
				return row.values.get(colNo);
			}
		};
		cellTable.addColumn(col, fieldName);
		col.setFieldUpdater(new FieldUpdater<Row, String>() {
			@Override
			public void update(int index, Row object, String value) {
				updateRow(index, object, colNo, fieldName, value);
			}
		});
		return col;

	}
}
