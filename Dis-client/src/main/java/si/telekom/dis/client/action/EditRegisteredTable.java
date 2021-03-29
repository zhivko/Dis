package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.builder.shared.DivBuilder;
import com.google.gwt.dom.builder.shared.TableCellBuilder;
import com.google.gwt.dom.builder.shared.TableRowBuilder;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.AbstractCellTable;
import com.google.gwt.user.cellview.client.AbstractHeaderOrFooterBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;

import si.telekom.dis.client.ActionDialogBox;
import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MyListBox;
import si.telekom.dis.client.MySimplePager;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.ServerException;

public class EditRegisteredTable extends ActionDialogBox implements ClickHandler {
	private static final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private static final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	public static EditRegisteredTable instance;

	CellTable<Row> cellTable;
	List<Row> rows = new ArrayList<EditRegisteredTable.Row>();
	String regTableId;

	HorizontalPanel hpFilter = new HorizontalPanel();

	static String filters = "";

	MyDataProvider dataProvider;

	public EditRegisteredTable(String regTableId) {
		super();
		filters = "";
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

		dataProvider = new MyDataProvider(regTableId);
		dataProvider.addDataDisplay(cellTable);

		// cellTable.setRowCount(pager.getPageSize(), true);
		// cellTable.setRowData(0, rows);
		cellTable.setWidth("700px");
		cellTable.setHeight("700px");

		getPanel().add(hpFilter);
		getPanel().add(cellTable);
		getPanel().add(pager);

		getOkButton().addClickHandler(this);
		this.setAnimationEnabled(false);
		instance = this;
		this.center();
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
								adminService.getRegTableValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, regTableId, filters, 0, 1,
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
														col.setSortable(true);
														col.setDataStoreName(columnNames.get(j));

														Header<String> columnHeader = new Header<String>(new ClickableTextCell()) {
															@Override
															public String getValue() {
																return columnNames.get(j);
															}
														};

														columnHeader.setUpdater(new ValueUpdater<String>() {
															@Override
															public void update(String value) {
																VerticalPanel vp = new VerticalPanel();
																MyListBox mlb = new MyListBox("All values",true);
																String colName = columnNames.get(j).replace("\"", "");
																explorerService.dqlLookup(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass,
																		"select distinct \"" + colName + "\" from dm_dbo." + regTableId + " order by \"" + colName + "\"",
																		new AsyncCallback<List<List<String>>>() {
																			@Override
																			public void onSuccess(List<List<String>> result) {
																				for (int k = 0; k < result.size(); k++) {
																					mlb.addItem(result.get(k).get(0));
																				}
																			}
																			@Override
																			public void onFailure(Throwable caught) {
																				MainPanel.log(caught.getMessage());
																			}
																		});
																HorizontalPanel hp1 = new HorizontalPanel();
																TextBox txtBox = new TextBox();
																Label lb1 = new Label("Filter: ");
																hp1.add(lb1);
																hp1.add(mlb);
																hp1.add(txtBox);
																PopupPanel pop = new PopupPanel(true,true);
																vp.add(hp1);
																pop.add(vp);
																HorizontalPanel hp2 = new HorizontalPanel();
																Button button = new Button("ok");
																button.addClickHandler(new ClickHandler() {
																	@Override
																	public void onClick(ClickEvent event) {
																		// TODO Auto-generated method stub
																		String filter="";
																		if(!mlb.getValue().equals(""))
																			filter = "\"" + colName + "\"='" + mlb.getValue() + "'";
																		if(!txtBox.getValue().equals(""))
																			filter = "\"" + colName + "\"='" + txtBox.getValue() + "'";
																		if (filters.equals(""))
																			filters = filter;
																		else
																			filters = filters + " and " + filter;
																		dataProvider.onRangeChanged(cellTable);
																		cellTable.redraw();
																		
																		pop.hide();
																	}
																});
																hp2.add(button);
																vp.add(hp2);	
																pop.show();
																pop.center();
																
																
															}
														});

														cellTable.addColumn(col, columnHeader);
														col.setFieldUpdater(new FieldUpdater<Row, String>() {
															@Override
															public void update(int index, Row object, String value) {
																// TODO Auto-generated method stub
																// this.update(index, object, value);
																updateRow(index, object, value, j);
															}
														});
													}
													break;
												}

//												int i = 0;
//												for (String colName : columnNames) {
//													String colName1 = colName.replace("\"", "");
//													if (colName1.equals("classification_plan_id") || colName1.equals("code")) {
//														// VerticalPanel vp = new VerticalPanel();
//														MyListBox lb = new MyListBox("filter: " + colName1 + "  ", true);
//
//														Attribute regCol = new Attribute(colName1);
//														regCol.isLimitedToValueList = false;
//														regCol.setType(Attribute.types.DROPDOWN.type);
//														regCol.dqlValueListDefinition = "select distinct " + colName1 + " from dm_dbo." + regTableId + " order by " + colName1;
//														FormAttribute fa = new FormAttribute(regCol);
//														fa.addValueChangeHandler(new ValueChangeHandler<List<String>>() {
//															public void onValueChange(ValueChangeEvent<List<String>> event) {
//																filters = "";
//																for (int i = 0; i < EditRegisteredTable.this.hpFilter.getWidgetCount(); i++) {
//																	FormAttribute fa1 = (FormAttribute) EditRegisteredTable.this.hpFilter.getWidget(i);
//																	if (filters.equals(""))
//																		filters = fa1.getAtribute().getId() + "='" + fa1.getValue() + "'";
//																	else
//																		filters = filters + " and " + fa1.getAtribute().getId() + "='" + fa1.getValue() + "'";
//																}
//																if (!filters.equals(""))
//																	dataProvider.onRangeChanged(cellTable);
//															};
//														});
//
//														// vp.add(lb);
//														// TextBox tb = new TextBox();
//														// vp.add(tb);
//														EditRegisteredTable.this.hpFilter.add(lb);
//													}
//													i++;
//												}
												// cellTable.getEmptyTableWidget().getElement().appendChild(EditRegisteredTable.this.hpFilter.getElement());
												// cellTable.setHeaderBuilder(new
												// MyCustomHeaderBuilder(cellTable, false));

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

		} catch (

		ServerException e) {
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
				adminService.getRegTableValues(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, regTableId, filters, range.getStart(),
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
										Timer timer = new Timer() {
											@Override
											public void run() {
												EditRegisteredTable.instance.center();
											}
										};
										timer.schedule(1000);
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

	private class MyCustomHeaderBuilder extends AbstractHeaderOrFooterBuilder<Row> {

		public MyCustomHeaderBuilder(AbstractCellTable<Row> table, boolean isFooter) {
			super(cellTable, isFooter);
			// TODO Auto-generated constructor stub

		}

		@Override
		public boolean buildHeaderOrFooterImpl() {
			TableRowBuilder row = startRow();

			for (int i = 0; i < getTable().getColumnCount(); i++) {
				String dataStoreName = getTable().getColumn(i).getDataStoreName();
				dataStoreName = dataStoreName.replace("\"", "");

				ListBox filterLb = new ListBox();
				Label lb = new Label();
				lb.setText(dataStoreName);
				SimplePanel sp = new SimplePanel();
				VerticalPanel vp = new VerticalPanel();
				vp.add(lb);
				vp.add(filterLb);
				sp.add(vp);

				/*
				 * TableCellBuilder th = row.startTH(); DivBuilder div = th.startDiv();
				 * div.html(SafeHtmlUtils.fromTrustedString(vp.getElement().getInnerHTML
				 * ())); div.end(); th.endTH();
				 */

				TableCellBuilder tcb = row.startTH();
				DivBuilder div = tcb.startDiv();
				div.html(SafeHtmlUtils.fromTrustedString(sp.getElement().getInnerHTML()));
				div.endDiv();
				tcb.endTH();
			}

			row.endTR();

			return true;
		}
	}

}
