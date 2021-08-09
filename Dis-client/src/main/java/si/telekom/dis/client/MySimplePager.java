package si.telekom.dis.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.Range;

public class MySimplePager extends SimplePager {
	public MySimplePager(TextLocation location, Resources resources, boolean showFastForwardButton, final int fastForwardRows,
			boolean showLastPageButton) {
		super(location, resources, showFastForwardButton, fastForwardRows, showLastPageButton,
				GWT.<ImageButtonsConstants> create(ImageButtonsConstants.class));
	}

	@Override
	protected String createText() {
		NumberFormat formatter = NumberFormat.getFormat("#,###");
		HasRows display = getDisplay();
		Range range = display.getVisibleRange();
		int pageStart = range.getStart() + 1;
		int pageSize = range.getLength();
		int dataSize = display.getRowCount();
		int endIndex = Math.min(dataSize, pageStart + pageSize - 1);
		endIndex = Math.max(pageStart, endIndex);
		boolean exact = display.isRowCountExact();
		if (dataSize == 0) {
			return "0 of 0";
		} else if (pageStart == endIndex) {
			return formatter.format(pageStart) + " of " + formatter.format(dataSize);
		}
		return formatter.format(pageStart) + "-" + formatter.format(endIndex) + (exact ? " of " : " of over ") + formatter.format(dataSize);
	}

//	@Override
//	public void setPageStart(int index) {
//	  if (getDisplay() != null) {
//	    Range range = getDisplay().getVisibleRange();
//	    int pageSize = range.getLength();
//
//	    // Removed the min to show fixed ranges
//	    //if (isRangeLimited && display.isRowCountExact()) {
//	    //  index = Math.min(index, display.getRowCount() - pageSize);
//	    //}
//
//	    index = Math.max(0, index);
//	    if (index != range.getStart()) {
//	      getDisplay().setVisibleRange(index, pageSize);
//	    }
//	  }
//	}
}