package si.telekom.dis.client;

import java.util.logging.Logger;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.CheckBox;

import si.telekom.dis.shared.Document;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;

public class CustomDocumentCell extends AbstractCell<Document> {
	final ImagesFormats imagesFormats = (ImagesFormats) GWT.create(ImagesFormats.class);
	final ImagesTypes imagesTypes = (ImagesTypes) GWT.create(ImagesTypes.class);
	Images images = (Images) GWT.create(Images.class);
	static Logger logger = java.util.logging.Logger.getLogger("mylogger");
	private final ExplorerServiceAsync explorerService = GWT.create(ExplorerService.class);
	private MyMultiSelectionModel<String> selectionModel;

	private ExplorerPanel explorerOrSearch;
	private Element prevParent;
	// private SetSelectionModel<String> selectionModel;

	static String[] events = { BrowserEvents.CLICK  };

	public CustomDocumentCell(MyMultiSelectionModel<String> selectionModel, ExplorerPanel explorerOrSearch_) {
		super(events);
		this.selectionModel = selectionModel;
		this.explorerOrSearch = explorerOrSearch_;
	}

	// @Override
	// public boolean handlesSelection() {
	// // TODO Auto-generated method stub
	// return true;
	// }

	static {
		exportStaticMethod();
	}

	@Override
	public void render(Context context, Document value, SafeHtmlBuilder sb) {
		if (value != null) {

			sb.appendHtmlConstant("<div id=\"" + value.r_object_id + "\"" + (value.isHighlighted ? "class=\"selected_tree_node\"" : "") + ">");
			CheckBox checkBox = new CheckBox();
			checkBox.setTabIndex(-1);

			String htmlStr2 = "<input type='checkbox' tabindex='-1' " + (CustomTreeModel.selectionModel.isSelected(value.r_object_id) ? "checked" : "")
					+ " onmousedown=\"checkIt('" + value.r_object_id + "')\">";
			sb.append(SafeHtmlUtils.fromTrustedString(htmlStr2));

			// CheckBox cb = new CheckBox();
			// cb.setTabIndex(-1);
			// cb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			//
			// @Override
			// public void onValueChange(ValueChangeEvent<Boolean> event) {
			// value.isChecked = true;
			// }
			// });
			// String html = cb.getElement().getInnerHTML();
			// sb.append(SafeHtmlUtils.fromTrustedString(html));

			String format = value.format;
			String lockOwner = value.lockOwner;
			if (lockOwner != null) {
				String loggedDctmUser = MainPanel.getInstance().dctmUserName;
				ImageResource resourceLock = null;
				if (lockOwner.equalsIgnoreCase(loggedDctmUser))
					resourceLock = (ImageResource) images.locked_by_you();
				else if (lockOwner.equals("")) {

				} else {
					resourceLock = (ImageResource) images.locked_by_another();
				}
				if (resourceLock != null)
					sb.append(AbstractImagePrototype.create(resourceLock).getSafeHtml());
			}

			if (format != null) {
				// String urlStr =
				// imagesFormats.f_image().getSafeUri().toString();
				ImageResource resource;
				if (!format.equals(""))
					resource = (ImageResource) imagesFormats.getResource("f_" + format);
				else {
					resource = (ImageResource) imagesTypes.getResource("t_" + value.type_name);
					if (resource == null)
						logger.info("no icon for type: " + value.type_name);
				}
				if (resource != null)
					sb.append(AbstractImagePrototype.create(resource).getSafeHtml());
			}

			if(!value.isFolder && value.releaseNo>-1)
				sb.appendEscaped(value.object_name + "/" + value.releaseNo);
			else
				sb.appendEscaped(value.object_name);

			sb.appendEscapedLines("\n");
			if (!value.isFolder)
				if (value.isClassified)
					sb.appendHtmlConstant("<strong>" + value.state_id + "</strong>");
				else
					sb.appendHtmlConstant("<strong>unclassified</strong>");

			// details
			if (value.details != null) {
				sb.appendEscaped("\n");
				String detailsLine = "";
				for (String detailsValue : value.details)
					detailsLine = detailsLine + detailsValue + ", ";

				if (detailsLine.length() > 0)
					detailsLine = detailsLine.substring(0, detailsLine.length() - 2);
				sb.appendEscapedLines(detailsLine);
			}

			sb.appendHtmlConstant("</div>");
		}
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context, Element parent, Document value, NativeEvent event,
			ValueUpdater<Document> valueUpdater) {
		super.onBrowserEvent(context, parent, value, event, valueUpdater);

		logger.info(event.getType() + ": " + value.object_name + "(" + value.r_object_id + ") type: " + value.type_name + " format: " + value.format);

		if (event.getType().equals(BrowserEvents.CHANGE)) {
			// Handle CHANGE events. (checkbox)
		} else if (event.getType() == BrowserEvents.CLICK) {
			// Handle click events.
			explorerOrSearch.selectDocument(value);
		}

	}

	/**
	 * Get the checkbox input element from the parent element that wraps our cell.
	 * 
	 * @param parent
	 *          the parent element
	 * @return the checkbox
	 */
	private InputElement getInputElement(Element parent) {
		// We need to navigate down to our input element.
		TableElement table = parent.getFirstChildElement().cast();

		NodeList<TableRowElement> nl = table.getRows();

		// TableRowElement tr = table.getRows().getItem(0);
		// TableCellElement td = tr.getCells().getItem(0);
		InputElement input = table.getFirstChildElement().cast();
		return input;
	}

	/**
	 * By convention, cells that respond to user events should handle the enter
	 * key. This provides a consistent user experience when users use keyboard
	 * navigation in the widget. Our cell will toggle the checkbox on Enter.
	 */
	@Override
	protected void onEnterKeyDown(Context context, Element parent, Document value, NativeEvent event, ValueUpdater<Document> valueUpdater) {
		// Toggle the checkbox.
		InputElement input = getInputElement(parent);
		input.setChecked(!input.isChecked());

		// Update the favorites based on the new state.
		// updateFavorites(parent, value);

		// Show the new list of favorites.
		// showCurrentFavorites();
	}

	public static void checkIt(String r_object_id) {
		CustomTreeModel.selectionModel.setSelected(r_object_id, !CustomTreeModel.selectionModel.isSelected(r_object_id));
		GWT.log("Checked objects size:" + CustomTreeModel.selectionModel.getSelectedSet().size());
		for (String r_object_id_ : ExplorerPanel.getExplorerInstance().getCheckedObjects()) {
			GWT.log(r_object_id_);
		}

	}

	public static native void exportStaticMethod() /*-{
		$wnd.checkIt = $entry(@si.telekom.dis.client.CustomDocumentCell::checkIt(Ljava/lang/String;));
	}-*/;

}
