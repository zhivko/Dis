package si.telekom.dis.shared;

import java.util.logging.Level;
//import org.apache.commons.lang3.exception.ExceptionUtils;
import java.util.logging.Logger;

public class ServerException extends Exception implements com.google.gwt.user.client.rpc.IsSerializable, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerException() {
	}
	
	public ServerException(Exception ex) {
		super(ex);
		
		//this.printStackTrace();
		// String stacktrace = ExceptionUtils.getStackTrace(this.getCause());
		String stackTrace = "";
		for (StackTraceElement element : this.getStackTrace()) {
			stackTrace += element + "\n";
		}
		Logger.getLogger("").log(Level.SEVERE, this.getMessage() + "\n" + stackTrace);		
	}

	public ServerException(Throwable ex) {
		super(ex);
		
		//this.printStackTrace();
		// String stacktrace = ExceptionUtils.getStackTrace(this.getCause());
		String stackTrace = "";
		for (StackTraceElement element : this.getStackTrace()) {
			stackTrace += element + "\n";
		}
		Logger.getLogger("").log(Level.SEVERE, this.getMessage() + "\n" + stackTrace);		
	}	
	
	public ServerException(String message)
	{
		this(new Exception(message));
	}
	

}
