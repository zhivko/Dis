package si.telekom.dis.client.item;

import com.google.gwt.user.client.ui.Composite;

import si.telekom.dis.shared.HasIdName;

public abstract class IsSelected extends Composite {
	public boolean isSelected;
	public HasIdName item;

	public IsSelected()
	{
		super();
	}
	
	public boolean getIsSelected() {
		return this.isSelected;
	}

	public void setItem(HasIdName item_) {
		this.item = item_;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return this.item.getId();
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.item.getParameter();
	}

	public void setId(String id_) {
		// TODO Auto-generated method stub
		this.item.setId(id_);
	}

	public void setName(String _name) {
		// TODO Auto-generated method stub
		this.item.setName(_name);
	}

}
