package si.telekom.dis.client;

import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Tree;

public interface ImagesTypes extends Tree.Resources, ClientBundleWithLookup {

	@Source("/content/documentum/icons/type/t_dm_cabinet_open_16.gif")
	ImageResource t_dm_cabinet();

	@Source("/content/documentum/icons/type/t_dm_folder_open_16.gif")
	ImageResource t_dm_folder();

	@Source("/content/documentum/icons/type/t_dm_sysobject_16.gif")
	ImageResource t_dm_sysobject();
	
	@Source("/content/documentum/icons/type/t_dm_job_16.gif")
	ImageResource t_dm_job();
	
	
	
}
