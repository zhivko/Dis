package si.telekom.dis.client.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;

import si.telekom.dis.client.MainPanel;
import si.telekom.dis.client.MySimplePager;
import si.telekom.dis.client.WindowBox;
import si.telekom.dis.shared.Document;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class DocumentVersions extends WindowBox {
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	public static DocumentVersions instance;

	CellTable<Row> cellTable;
	String r_object_id;

	List<Row> allRows = new ArrayList<Row>();

	public DocumentVersions(String r_object_id) {
		super();
		this.r_object_id = r_object_id;
		setText("Verzije");
		setAnimationEnabled(false);
		setGlassEnabled(true);

		cellTable = new CellTable<Row>();
		cellTable.setWidth("100%");
		cellTable.setTableLayoutFixed(true);
		cellTable.setPageSize(10);

		MySimplePager pager;
		MySimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new MySimplePager(TextLocation.RIGHT, pagerResources, false, 0, true);
		pager.setDisplay(cellTable);
		pager.setPageSize(20);
		pager.setWidth("100%");

		Column<Row, String> colRObjectId = new Column<Row, String>(new ClickableTextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(1);
			}

			// @Override
			// protected void render(Context context, SafeHtml value, SafeHtmlBuilder
			// sb) {
			// if (value != null) {
			// sb.appendHtmlConstant("<div class=\""+style+"\">");
			// sb.append(value);
			// sb.appendHtmlConstant("</div>");
			// }
			// }

			@Override
			public void onBrowserEvent(Context context, Element elem, Row row, NativeEvent event) {
				// TODO Auto-generated method stub
				// super.onBrowserEvent(context, elem, object, event);
				DocumentView v = new DocumentView(row.getValues().get(0));
				v.show();
			}

			@Override
			public void render(Context context, Row row, SafeHtmlBuilder sb) {
				// TODO Auto-generated method stub
				// super.render(context, object, sb);
				// sb.appendHtmlConstant("<div class=\""+style+"\">");
				// sb.append(value);
				sb.appendHtmlConstant("<div>");
				sb.appendEscaped(row.values.get(0));
				sb.appendHtmlConstant("</div>");
			}

		};
		cellTable.addColumn(colRObjectId, "r_object_id");
		cellTable.setColumnWidth(colRObjectId, "10%");

		Column<Row, String> colObjectName = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(1);
			}
		};
		cellTable.addColumn(colObjectName, "object_name");
		cellTable.setColumnWidth(colObjectName, "10%");

		Column<Row, String> colCreationDate = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(2);
			}
		};
		cellTable.addColumn(colCreationDate, "r_creation_date");
		cellTable.setColumnWidth(colCreationDate, "10%");

		Column<Row, String> colModifyDate = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(3);
			}
		};
		cellTable.addColumn(colModifyDate, "r_modify_date");
		cellTable.setColumnWidth(colModifyDate, "10%");

		Column<Row, String> colCreator = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(4);
			}
		};
		cellTable.addColumn(colCreator, "creator");
		cellTable.setColumnWidth(colCreator, "10%");
		
		Column<Row, String> colModifier = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(5);
			}
		};
		cellTable.addColumn(colModifier, "modifier");
		cellTable.setColumnWidth(colModifier, "10%");

		Column<Row, String> colSubject = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(6);
			}
		};
		cellTable.addColumn(colSubject, "subject");
		cellTable.setColumnWidth(colSubject, "10%");

		Column<Row, String> colTitle = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(7);
			}
		};
		cellTable.addColumn(colTitle, "title");
		cellTable.setColumnWidth(colTitle, "20%");

		Column<Row, String> colVersion = new Column<Row, String>(new TextCell()) {
			@Override
			public String getValue(Row row) {
				return row.values.get(8);
			}
		};
		cellTable.addColumn(colVersion, "version label");
		cellTable.setColumnWidth(colVersion, "15%");

		explorerService.versions(MainPanel.getInstance().loginName, MainPanel.getInstance().loginPass, r_object_id, new AsyncCallback<List<Document>>() {

			@Override
			public void onSuccess(List<Document> result) {
				// TODO Auto-generated method stub
				for (Document doc : result) {
					ArrayList<String> strings = new ArrayList<>();
					strings.add(doc.r_object_id);
					strings.add(doc.object_name);
					strings.add(DateTimeFormat.getFormat("yyyy.dd.MM HH:mm:ss").format(doc.r_creation_date));
					strings.add(DateTimeFormat.getFormat("yyyy.dd.MM HH:mm:ss").format(doc.r_modify_date));
					strings.add(doc.creator);
					strings.add(doc.modifier);
					strings.add(doc.subject);
					strings.add(doc.title);
					strings.add(doc.r_version_label);
					Row row = new Row(strings);
					allRows.add(row);
				}

				MyDataProvider dataProvider = new MyDataProvider();
				dataProvider.addDataDisplay(cellTable);

				// cellTable.setRowCount(allRows.size(), false);
				// cellTable.setRowData(0, allRows);

				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						DocumentVersions.this.center();
					}
				});

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

		VerticalPanel vp = new VerticalPanel();
		vp.add(cellTable);
		vp.add(pager);
		ScrollPanel sp = new ScrollPanel(vp);
		sp.setHeight(getMaxHeightPx());
		sp.setWidth(getMaxWidthPx());
		getContentPanel().add(vp);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				DocumentVersions.this.center();
			}
		});

		instance = this;
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
		public MyDataProvider() {
		}

		@Override
		protected void onRangeChanged(HasData<Row> display) {
			// Get the new range.
			final Range range = display.getVisibleRange();

			int min1 = Math.min(range.getStart() + range.getLength(), DocumentVersions.instance.allRows.size());
			if (range.getStart() < DocumentVersions.instance.allRows.size()) {
				updateRowData(range.getStart(), DocumentVersions.instance.allRows.subList(range.getStart(),
						min1));
			}
		}
	}

}
