package si.telekom.dis.shared;

public class TemplateFolder implements com.google.gwt.user.client.rpc.IsSerializable,  java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String folderPath;
	
	public TemplateFolder() {
	}
	
	public TemplateFolder(String folderPath_) {
		this.folderPath = folderPath_;
	}

}
