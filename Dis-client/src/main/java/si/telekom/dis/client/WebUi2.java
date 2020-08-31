/* GWT that supports jetty 9.4, that is used to run rest services is found in following reporisotories
https://github.com/mihaisdm/gwt
https://github.com/mihaisdm/tools
*/

package si.telekom.dis.client;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExplorerServiceAsync;
import si.telekom.dis.shared.LoginService;
import si.telekom.dis.shared.LoginServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebUi2 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while " + "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	static boolean authenticated = false;

	// static boolean fastForward = true;
	/**
	 * This is the entry point method.
	 */

	final DialogBox dialogBox = new DialogBox();
	final Button loginButton = new Button("Login");
	final TextBox nameField = new TextBox();
	final PasswordTextBox passField = new PasswordTextBox();
	final Label errorLabel = new Label();

	public void forward(String[] loginData) {
		if (authenticated) {
			RootPanel.get("loginPanel").setVisible(false);
			RootPanel.get().remove(RootPanel.get("loginPanel"));
			MainPanel mp = new MainPanel(loginData);

			ExplorerPanel.getExplorerInstance().refresh("");

			mp.setVisible(true);

			// Attach the LayoutPanel to the RootLayoutPanel. The latter will listen
			// for
			// resize events on the window to ensure that its children are informed of
			// possible size changes.

			RootPanel.get("mainPanel").clear();
			RootLayoutPanel rp = RootLayoutPanel.get();
			rp.add(mp);

			// RootPanel.get("mainPanel").add(mp);
		} else {
			RootPanel.get("mainPanel").setVisible(false);
			RootPanel.get("loginPanel").setVisible(true);
		}

	}

	private static void ensureNotUmbrellaError(@NotNull Throwable e) {
		for (Throwable th : ((UmbrellaException) e).getCauses()) {
			if (th instanceof UmbrellaException) {
				ensureNotUmbrellaError(th);
			} else {
				th.printStackTrace();
			}
		}
	}

	public void onModuleLoad() {

		// GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
		// @Override
		// public void onUncaughtException(@NotNull Throwable e) {
		// e.printStackTrace();
		// //ensureNotUmbrellaError(e);
		// }
		// });

		authenticated = false;
		nameField.getElement().setPropertyString("placeholder", "vnesi prijavno ime");
		passField.getElement().setPropertyString("placeholder", "vnesi geslo");

		// We can add style names to widgets
		loginButton.addStyleName("sendButton");

		loginButton.setEnabled(true);
		loginButton.setFocus(true);

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("loginNameFieldContainer").add(nameField);
		RootPanel.get("loginPassFieldContainer").add(passField);
		RootPanel.get("sendButtonContainer").add(loginButton);

		RootPanel.get("errorLabelContainer").add(errorLabel);

		// MyDateTime mdt = new MyDateTime();
		// RootPanel.get("loginPassFieldContainer").add(mdt);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b><br>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public MyHandler(boolean devTry_) {
				super();
				this.devTry = devTry_;
			}

			boolean devTry = false;

			public void onClick(ClickEvent event) {
				sendNameToServer(false);
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer(false);
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			public void sendNameToServer(boolean devTry) {
				// First, we validate the input.
				errorLabel.setText("");
				nameField.getText();
				// if (!FieldVerifier.isValidName(nameField.getText())) {
				// errorLabel.setText("Please enter at least four characters");
				// return;
				// }

				// Then, we send the input to the server.
				if (!devTry)
					loginButton.setEnabled(false);
				textToServerLabel.setText(nameField.getText());
				serverResponseLabel.setText("");
				authenticated = false;

				// String passCrypted = Tools.encryptString(passField.getText());
				// String passCrypted =
				// Base64Utils.toBase64(passField.getText().getBytes(Charset.forName("UTF-8")));
				String passCrypted = b64encode(passField.getText());

				loginService.login(nameField.getText(), passCrypted, new AsyncCallback<String[]>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						if (!devTry) {
							Scheduler.get().scheduleDeferred(new ScheduledCommand() {
								@Override
								public void execute() {
									dialogBox.setWidth("400px");
									dialogBox.setHeight("500px");
									dialogBox.center();
								}
							});

							dialogBox.setText("Remote Procedure Call - Failure");
							serverResponseLabel.addStyleName("serverResponseLabelError");
							serverResponseLabel.setHTML(caught.getMessage());
							closeButton.setFocus(true);
							authenticated = false;
							loginButton.setEnabled(true);
						}
					}

					public void onSuccess(String[] result) {
						nameField.setText(result[0]);
						passField.setText(result[1]);
						authenticated = true;
						forward(result);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler(false);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				Timer timer = new Timer() {
					@Override
					public void run() {
						handler.sendNameToServer(true);
					}
				};
				timer.schedule(300);
			}
		});
		loginButton.addClickHandler(handler);


		passField.addKeyUpHandler(handler);

	}

	private static native String b64encode(String a) /*-{
		return window.btoa(a);
	}-*/;

}