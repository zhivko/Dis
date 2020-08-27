package si.telekom.dis.client;

import java.util.Iterator;

import com.google.gwt.view.client.MultiSelectionModel;

public class MyMultiSelectionModel<String> extends MultiSelectionModel<String> {

	@Override
	public void setSelected(String myData, boolean selected) {
		super.setSelected(myData, selected);
		if (selected) {
			System.out.println("setSelected selected " + myData);
			// call now some ui handler to use the last selected myData element
		}
	}

	public String getLastSelected()
	{
		Iterator<String> it = this.getSelectedSet().iterator();
		String selectedObjectKeys=null;
		while(it.hasNext())
		{
			selectedObjectKeys = it.next();
		}
		return selectedObjectKeys;
	}
	
}
