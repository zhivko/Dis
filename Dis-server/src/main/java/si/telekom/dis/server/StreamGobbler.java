package si.telekom.dis.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class StreamGobbler implements Runnable {
	private final InputStream _inputStream;
	private String _result;

	StreamGobbler(InputStream is) {
		_inputStream = is;
	}

	public String getResult() {
		return _result;
	}

	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(_inputStream);
			br = new BufferedReader(isr);
			StringBuilder output = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				output.append(line);
				output.append(System.getProperty("line.separator"));
			}
			_result = output.toString();
		} catch (IOException e) {
			_result = e.getMessage();
		} finally {
			try {
				isr.close();
				br.close();
			} catch (Exception ex) {
				Logger.getLogger(this.getClass()).error(ex);
			}
		}
	}
}