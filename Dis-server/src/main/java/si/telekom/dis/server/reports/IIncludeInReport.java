package si.telekom.dis.server.reports;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;

public interface IIncludeInReport {
	public boolean shouldInclude(IDfSysObject sysObj, IDfSession userSess) throws Exception;
}
