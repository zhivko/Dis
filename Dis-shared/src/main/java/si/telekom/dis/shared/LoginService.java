package si.telekom.dis.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	String[] login(String loginName, String passwordHashed) throws ServerException;
	UserSettings getUserSettings(String loginName, String passwordEncrypted) throws ServerException;
	Void saveUserSettings(String loginName, String passwordEncrypted, UserSettings us) throws ServerException;
}
