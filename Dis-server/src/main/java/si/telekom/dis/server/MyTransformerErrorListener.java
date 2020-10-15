package si.telekom.dis.server;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class MyTransformerErrorListener implements ErrorListener {

	public String msgs="";

	@Override
	public void warning(TransformerException exception) throws TransformerException {
		// TODO Auto-generated method stub
		msgs = msgs + "warning: " + exception.getLocalizedMessage();
	}

	@Override
	public void fatalError(TransformerException exception) throws TransformerException {
		msgs = msgs + "fatal error: " + exception.getLocalizedMessage();
	}

	@Override
	public void error(TransformerException exception) throws TransformerException {
		// TODO Auto-generated method stub
		msgs = msgs + "error: " + exception.getLocalizedMessage();
	}

	public String getMsgs() {
		return msgs;
	}

}
