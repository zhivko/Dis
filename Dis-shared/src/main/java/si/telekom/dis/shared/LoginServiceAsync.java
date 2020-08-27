package si.telekom.dis.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	void login(String loginName, String passwordHashed, AsyncCallback<String[]> callback) throws IllegalArgumentException;
}
