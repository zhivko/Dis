package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MySimplePager;
import si.telekom.dis.client.MyTextArea;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentAuditTrail extends WindowBox {
	public static DocumentAuditTrail instance;

	CellTable<Row> cellTable;
	String r_object_id;
	List<Row> rows;

	public DocumentAuditTrail(String r_object_id) {
		// audited_obj_id, time_stamp, time_stamp_utc, event_name,
		// event_description, user_name,
		// string_1,string_2,attribute_list,attribute_list_old
		super();

		this.setWidth(getMaxWidthPx());
		this.setHeight(getMaxHeightPx());

		this.r_object_id = r_object_id;
		setText("Revizijska sled");
		setAnimationEnabled(false);
		setGlassEnabled(true);

		MySimplePager pager;
		MySimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new MySimplePager(TextLocation.RIGHT, pagerResources, false, 0, true);
		pager.setPageSize(MainPanel.getInstance().us.auditTrailPerPageCount);
		pager.setWidth("100%");

		int ordinaryColWidth = 150;

		cellTable = new CellTable<Row>(Row.KEY_PROVIDER);
		cellTable.addCellPreviewHandler(new CellPreviewEvent.Handler<Row>() {

			@Override
			public void onCellPreview(CellPreviewEvent<Row> event) {
				// TODO Auto-generated method stub
				if (event.getNativeEvent().getType().equals(BrowserEvents.CLICK)) {
					int column = event.getContext().getColumn();
					// if (event.getContext().getSubIndex() > 0) {
					if (column == 1) {

						String r_object_id = event.getValue().getValues().get(column);
						DocumentView view = new DocumentView(r_object_id);
						view.show();
					}
				}
			}

		});
		cellTable.setTableLayoutFixed(true);
		cellTable.setWidth("100%");
		pager.setDisplay(cellTable);
		cellTable.setPageSize(MainPanel.getInstance().us.auditTrailPerPageCount);

		Column<Row, String> r_obj_id = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(0);
			}
		};
		cellTable.addColumn(r_obj_id, "r_object_id");
		cellTable.setColumnWidth(r_obj_id, ordinaryColWidth, Unit.PX);

		Column<Row, String> col_audited_obj_id = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(1);
			}
		};
		cellTable.addColumn(col_audited_obj_id, "audited_obj_id");
		cellTable.setColumnWidth(col_audited_obj_id, ordinaryColWidth, Unit.PX);

		Column<Row, String> r_version_label = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(2);
			}
		};
		cellTable.addColumn(r_version_label, "r_version_label");
		cellTable.setColumnWidth(r_version_label, ordinaryColWidth + 30, Unit.PX);

		Column<Row, String> aclName = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(3);
			}
		};
		cellTable.addColumn(aclName, "acl_name");
		cellTable.setColumnWidth(aclName, ordinaryColWidth + 30, Unit.PX);

		Column<Row, String> col_time_stamp = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(4);
			}
		};
		cellTable.addColumn(col_time_stamp, "time_stamp");
		cellTable.setColumnWidth(col_time_stamp, ordinaryColWidth, Unit.PX);

		Column<Row, String> col_timestamp_utc = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(5);
			}
		};
		cellTable.addColumn(col_timestamp_utc, "time_stamp_utc");
		cellTable.setColumnWidth(col_timestamp_utc, ordinaryColWidth - 30, Unit.PX);

		Column<Row, String> col_event_name = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(6);
			}
		};
		cellTable.addColumn(col_event_name, "event_name");
		cellTable.setColumnWidth(col_event_name, ordinaryColWidth, Unit.PX);

		Column<Row, String> col_event_description = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(7);
			}
		};
		cellTable.addColumn(col_event_description, "event_description");
		cellTable.setColumnWidth(col_event_description, ordinaryColWidth, Unit.PX);

		Column<Row, String> col_user_name = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(8);
			}
		};
		cellTable.addColumn(col_user_name, "user_name");
		cellTable.setColumnWidth(col_user_name, ordinaryColWidth, Unit.PX);

		Column<Row, String> col_string_1 = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(9);
			}
		};
		cellTable.addColumn(col_string_1, "string_1");
		cellTable.setColumnWidth(col_string_1, 90, Unit.PX);

		Column<Row, String> col_string_2 = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(10);
			}
		};
		cellTable.addColumn(col_string_2, "string_2");
		cellTable.setColumnWidth(col_string_2, 110, Unit.PX);

		Column<Row, String> col_attribute_list = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(11);
			}
		};
		cellTable.addColumn(col_attribute_list, "attribute_list");
		col_attribute_list.setCellStyleNames("wordWrapAuditTrail");
		cellTable.setColumnWidth(col_attribute_list, 270, Unit.PX);

		Column<Row, String> col_attribute_list_old = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(12);
			}
		};
		cellTable.addColumn(col_attribute_list_old, "attribute_list_old");
		cellTable.setColumnWidth(col_attribute_list_old, 270, Unit.PX);
		col_attribute_list_old.setCellStyleNames("wordWrapAuditTrail");

		rows = new ArrayList<Row>();

		MyDataProvider dataProvider = new MyDataProvider(r_object_id, rows, MainPanel.getInstance().us.auditTrailPerPageCount);
		dataProvider.addDataDisplay(cellTable);

		ListHandler<Row> columnSortHandler = new ListHandler<Row>(rows);
		columnSortHandler.setComparator(r_obj_id, new Comparator<Row>() {
			public int compare(Row o1, Row o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.values.get(0).compareTo(o2.values.get(0)) : 0;
				}
				return -1;
			}
		});
		cellTable.addColumnSortHandler(columnSortHandler);

		cellTable.getColumnSortList().push(r_obj_id);

		DocumentAuditTrail.this.getContentPanel().setSize("900px", "600px");
		centerMyPanel();

		VerticalPanel vp = new VerticalPanel();
		vp.add(cellTable);
		vp.add(pager);
		ScrollPanel sp = new ScrollPanel(vp);
		sp.setHeight(getMaxHeightPx());
		sp.setWidth(getMaxWidthPx());

		getContentPanel().add(sp);

		Button exportButton = new Button("CSV export");
		exportButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String data = "";
				for (int j = 0; j<cellTable.getColumnCount(); j++) {
					data = data + "\"" + cellTable.getHeader(j).getValue() + "\"\t";
				}
				data = data.substring(0, data.length() - 1);
				data = data + "\n";
				for (Row row : cellTable.getVisibleItems()) {
					for (String cellData : row.values) {
						data = data + "\"" + cellData + "\"\t";
					}
					data = data.substring(0, data.length() - 1);
					data = data + "\n";
				}
				data = data.substring(0, data.length() - 1);
				Window.alert(data);
			}
		});
		this.getButtonPanel().add(exportButton);

		// Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		// @Override
		// public void execute() {
		// DocumentAuditTrail.this.center();
		// }
		// });

		instance = this;
	}

	private void centerMyPanel() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				// cellTable.setSize("1000px", "500px");
				DocumentAuditTrail.this.center();
			}
		});
	}

	public static final native String[] split(String string, String separator) /*-{
		return string.split(separator);
	}-*/;

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

	/**
	 * A custom {@link AsyncDataProvider}.
	 */
	private static class MyDataProvider extends AsyncDataProvider<Row> {
		/**
		 * {@link #onRangeChanged(HasData)} is called when the table requests a new
		 * range of data. You can push data back to the displays using
		 * {@link #updateRowData(int, List)}.
		 */
		private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
		List<Row> allRows;
		String r_object_id;
		int pageSize;

		public MyDataProvider(String r_object_id, List<Row> rows, int pageSize) {
			this.r_object_id = r_object_id;
			allRows = rows;
			this.pageSize = pageSize;
		}

		@Override
		protected void onRangeChanged(HasData<Row> display) {
			// Get the new range.
			final Range range = display.getVisibleRange();

			// int min1 = Math.min(range.getStart() + range.getLength(),
			// MyDataProvider.this.allRows.size());

			final int from = range.getStart() + 1;
			final int to = range.getStart() + 1 + pageSize;

			explorerService.auditTrail(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, from, to,
					new AsyncCallback<List<List<String>>>() {

						@Override
						public void onSuccess(List<List<String>> result) {
							// TODO Auto-generated method stub
							// allRows.clear();
							int i = 0;
							for (List<String> row1 : result) {
								Row row = new Row(row1);
								allRows.add(from + i - 1, row);
								i++;
							}

							// if (range.getStart() < MyDataProvider.this.allRows.size()) {
							updateRowData(from - 1, MyDataProvider.this.allRows.subList(from - 1, Math.min(to - 1, allRows.size())));
							DocumentAuditTrail.instance.centerMyPanel();
							// }

						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							MainPanel.log(caught.getMessage());
						}
					});

		}
	}

}
