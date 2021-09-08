package si.telekom.dis.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	void login(String loginName, String passwordHashed, AsyncCallback<String[]> callback) throws IllegalArgumentException;

	void getUserSettings(String loginName, String passwordEncrypted, AsyncCallback<UserSettings> callback) throws IllegalArgumentException;

	void saveUserSettings(String loginName, String passwordEncrypted, UserSettings us, AsyncCallback<Void> callback) throws IllegalArgumentException;
	
	void getServerIp(AsyncCallback<String> callback) throws IllegalArgumentException;
}
