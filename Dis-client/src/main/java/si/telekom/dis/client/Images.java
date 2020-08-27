package si.telekom.dis.client;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Tree;

public interface Images extends Tree.Resources {

	@Source("/content/lists/contactsgroup.gif")
	ImageResource contactsgroup();

	@Source("/content/lists/defaultContact.jpg")
	ImageResource defaultContact();

	@Source("/content/lists/drafts.gif")
	ImageResource drafts();

	@Source("/content/lists/filtersgroup.gif")
	ImageResource filtersgroup();

	@Source("/content/lists/inbox.gif")
	ImageResource inbox();

	@Source("/content/lists/mailgroup.gif")
	ImageResource mailgroup();

	@Source("/content/lists/sent.gif")
	ImageResource sent();

	@Source("/content/lists/templates.gif")
	ImageResource templates();

	@Source("/content/lists/trash.gif")
	ImageResource trash();

	/**
	 * Use noimage.png, which is a blank 1x1 image.
	 */
	@Override
	@Source("noimage.png")
	ImageResource treeLeaf();

	@Source("/content/lists/mailgroup.gif")
	ImageResource mailHeader();

	@Source("/content/webui/explorer.png")
	ImageResource explorer();

	@Source("/content/webui/search.png")
	ImageResource search();
	
	
	@Source("/content/documentum/icons/type/t_dm_type_16.gif")
	ImageResource doctype();
	
	@Source("/content/documentum/icons/type/t_attribute_16.gif")
	ImageResource docattribute();

	@Source("/content/webui/t_profile_16.png")
	ImageResource docProfile();	
	
	@Source("/content/push-icon-11.jpg")
	ImageResource action();

	@Source("content/documentum/icons/type/t_dm_cabinet_16.gif")
	ImageResource cabinetClose();
	
	@Source("content/documentum/icons/type/t_dm_cabinet_open_16.gif")
	ImageResource cabinetOpen();
	
	@Source("content/webui/iconLeft_16.png")
	ImageResource iconLeft();	
	
	@Source("content/webui/iconRight_16.png")
	ImageResource iconRight();	
	
	@Source("/content/documentum/icons/type/t_dm_registered_16.gif")
	ImageResource dmRegistered();

	@Source("/content/documentum/icons/indicator/i_locked_by_you_16.gif")
	ImageResource locked_by_you();
	
	@Source("/content/documentum/icons/indicator/i_locked_by_another_16.gif")
	ImageResource locked_by_another();
	

}
