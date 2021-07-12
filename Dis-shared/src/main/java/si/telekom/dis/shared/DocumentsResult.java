package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.List;

import si.telekom.dis.shared.ExtendedPermit.extPermit;

public class DocumentsResult implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {
	public int lastItemFromQuery;
	public List<Document> documents;
	public boolean done;

	public DocumentsResult() {
	}

}
