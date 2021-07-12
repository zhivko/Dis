/* GWT that supports jetty 9.4, that is used to run rest services is found in following reporisotories
https://github.com/mihaisdm/gwt
https://github.com/mihaisdm/tools
*/

package si.telekom.dis.client;

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
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import si.telekom.dis.client.action.SearchEdit;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.LoginService;
import si.telekom.dis.shared.LoginServiceAsync;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.UserSettings;

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
			loginService.getUserSettings(nameField.getValue(), passField.getValue(), new AsyncCallback<UserSettings>() {
				@Override
				public void onFailure(Throwable caught) {
					GWT.log(caught.getMessage());
				}

				public void onSuccess(UserSettings result) {
					RootPanel.get("loginPanel").setVisible(false);
					RootPanel.get().remove(RootPanel.get("loginPanel"));
					MainPanel mp = new MainPanel(loginData);
					mp.setVisible(true);
					mp.us = result;
					ExplorerPanel.getExplorerInstance().refresh("");
					RootPanel.get("mainPanel").clear();
					RootLayoutPanel rp = RootLayoutPanel.get();
					rp.add(mp);
					
				};
			});

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

		authenticated = false;

		loginButton.addStyleName("sendButton");

		loginButton.setEnabled(true);
		loginButton.setFocus(true);

		RootPanel.get("loginNameFieldContainer").add(nameField);
		RootPanel.get("loginPassFieldContainer").add(passField);
		RootPanel.get("sendButtonContainer").add(loginButton);

		RootPanel.get("errorLabelContainer").add(errorLabel);

		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		dialogBox.setText("Login");
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Loging to DIS server with login name:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b><br>"));
		
		ScrollPanel sp = new ScrollPanel();
		sp.add(serverResponseLabel);
		sp.setWidth("500px");
		sp.setHeight("200px");
		
		dialogVPanel.add(sp);
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
			boolean devTry;
			
			public MyHandler(boolean devTry_) {
				super();
				this.devTry = devTry_;
			}

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
				GWT.log("log 0");
				String passCrypted = b64encode(passField.getText());

				loginService.login(nameField.getText(), passCrypted, new AsyncCallback<String[]>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						GWT.log("log 1");
						if (!devTry) {
							Scheduler.get().scheduleDeferred(new ScheduledCommand() {
								@Override
								public void execute() {
									dialogBox.center();
								}
							});

							dialogBox.setText("Login failed");
							serverResponseLabel.addStyleName("serverResponseLabelError");
							serverResponseLabel.setHTML(caught.getMessage());
							closeButton.setFocus(true);
							authenticated = false;
							loginButton.setEnabled(true);
						}
					}

					public void onSuccess(String[] result) {
						GWT.log("setting login name and pass: " + (String) result[0] + " : " + (String) result[1]);
						nameField.setText((String) result[0]);
						passField.setText((String) result[1]);
						authenticated = true;
						forward(result);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler(false);
		loginButton.addClickHandler(handler);
		passField.addKeyUpHandler(handler);
		
		
		boolean devTry = false;
		if(devTry)
		{
			Timer timer = new Timer()
      {
          @Override
          public void run()
          {
          	handler.sendNameToServer(false);
          }
      };

      timer.schedule(1000);
		}			
	}

	private static native String b64encode(String a) /*-{
		return window.btoa(a);
	}-*/;

}
