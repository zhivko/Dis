package si.telekom.dis.client;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.constants.ProgressBarType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import elemental2.dom.DomGlobal;
import elemental2.dom.WebSocket;
import si.telekom.dis.client.action.DocumentViewCollabora;
import si.telekom.dis.client.action.SearchEdit;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.AdminServiceAsync;
import si.telekom.dis.shared.LoginService;
import si.telekom.dis.shared.LoginServiceAsync;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.UserSettings;

public class MainPanel extends Composite {
	public String loginName;
	public String repository;
	public String contentServerVersion;
	public String loginPass;
	public String dctmUserName;
	public String loginRole;

	public MenuPanel menuPanel;
	public HorizontalPanel topPanel = new HorizontalPanel();

	private HTMLPanel logHtml = new HTMLPanel("");
	VerticalPanel vpLoginInfo;
	private AbsolutePanel mainPanel = new AbsolutePanel();
	private static MainPanel instance;

	private final AdminServiceAsync adminService = GWT.create(AdminService.class);
	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	public SplitLayoutPanel splitLayoutPanel;

	public UserSettings us;

	public ScrollPanel spLog;

	public ProgressBar progressBar;
	Label lbUser;
	Label lbRole;
	Label lbRepository;
	Label lbContServVersion;
	Label lbServerTime;
	public String serverIp; 

	public static AbsolutePanel getPanel() {
		return instance.mainPanel;
	}

	public static void clearPanel() {
		while (instance.mainPanel.getWidgetCount() > 0)
			instance.mainPanel.getWidget(0).removeFromParent();
	}

	public static MainPanel getInstance() {
		return instance;
	}

	public MainPanel(String[] loginData) {
		loginName = (String) loginData[0];
		loginPass = (String) loginData[1];
		loginRole = (String) loginData[2];
		dctmUserName = (String) loginData[3];
		repository = (String) loginData[4];
		contentServerVersion = (String) loginData[5];

		progressBar = new ProgressBar();
		progressBar.setType(ProgressBarType.INFO);

		lbUser = new HTML("user: " + loginName);
		lbRole = new HTML("role: " + loginRole);
		lbRepository = new HTML("repository: <strong>" + repository + "</strong>");
		lbContServVersion = new HTML("<small>" + contentServerVersion + "</small>");
		lbServerTime = new HTML("server time: ");
		progressBar.setHeight("2em");

		vpLoginInfo = new VerticalPanel();
		vpLoginInfo.setWidth("12em");
		vpLoginInfo.add(lbUser);
		vpLoginInfo.add(lbRole);
		vpLoginInfo.add(lbRepository);
		vpLoginInfo.add(lbContServVersion);
		vpLoginInfo.add(lbServerTime);
		vpLoginInfo.add(progressBar);
		vpLoginInfo.setCellWidth(progressBar, "100%");

		History.addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(ValueChangeEvent<String> arg0) {
				MainPanel.log("History item added: " + arg0.getValue());
			}
		});

		logHtml.getElement().setId("LogHtml");

		if (instance == null)
			instance = this;

		splitLayoutPanel = new SplitLayoutPanel(5) {
			@Override
			public void onResize() {
				ExplorerPanel.getExplorerInstance().resize();
				SearchPanel.getSearchPanelInstance().resize();
				spLog.setHeight(splitLayoutPanel.getWidgetSize(topPanel).intValue() + "px");
			}
		};
		splitLayoutPanel.setStyleName("cw-DockPanel");

		String str1 = GWT.getHostPageBaseURL();
		String str2 = str1.replaceAll("http", "ws");

		SafeUri wsUri = UriUtils.fromTrustedString(str2 + "wsDis?loginName=" + loginName);
		// SafeUri wsUri = UriUtils.fromTrustedString(str2 + "ws");
		String str3 = wsUri.asString();

		// Window.alert(str3);
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "using websocket uri: " + str3);

		final WebSocket socket = new WebSocket(str3);
		socket.onopen = e -> {
			DomGlobal.console.log("[open] Connection established");
			// socket.send(Global.JSON.stringify(JsPropertyMap.of("message", "Hi ho
			// Silver!")));
		};
		socket.onerror = e -> DomGlobal.console.log("[error]", e);
		socket.onmessage = e -> {
			// DomGlobal.console.log("[message] Data received from server: ", e.data);
			if (e.data.asString().startsWith("time=")) {
				String timeStr = e.data.asString().split("=")[1];

				String pattern = "dd.MM.yyyy HH:mm:ss";
				DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
				DateTimeFormat dtf = new DateTimeFormat(pattern, info) {
				}; // <= trick here
				Date date = dtf.parse(timeStr);
				refreshLoginInfo(timeStr);
			} else if (e.data.asString().startsWith("progress")) {
				String progr = e.data.asString().split(" ")[1];
				double no1 = Double.valueOf(progr.split("/")[0]).doubleValue();
				double no2 = Double.valueOf(progr.split("/")[1]).doubleValue();
				double percent = no1 / no2 * 100.0;
				// MainPanel.log(progr + " no1: " + no1 + " no2: " + no2 + " percent: "
				// + percent);
				progressBar.setPercent(percent);
				progressBar.setText(progr);
			} else if (e.data.asString().startsWith("logout")) {
				logout();
				// Window.open(loginPageUrl, "_self", "");
			} else {
				MainPanel.log(e.data.asString());
			}
		};
		socket.onclose = e -> {
			if (e.wasClean) {
				DomGlobal.console.log("[close] Connection closed cleanly, code=" + e.code + " reason=" + e.reason + "");
				logout();
			} else {
				// e.g. server process killed or network down
				// event.code is usually 1006 in this case
				DomGlobal.console.log("[close] Connection died");
			}
		};

		mainPanel.addHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_UP || event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					event.preventDefault();
				}
			}
		}, KeyDownEvent.getType());

		mainPanel.addDomHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				// TODO Auto-generated method stub
				if (event.getNativeKeyCode() == KeyCodes.KEY_UP || event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
					event.preventDefault();
				}
			}
		}, KeyDownEvent.getType());

		// centerScrollable.setSize("100%", "100%");
		// centerScrollable.setAlwaysShowScrollBars(false);

		spLog = new ScrollPanel(logHtml);

		refreshLoginInfo("");
		topPanel.add(spLog);
		topPanel.add(vpLoginInfo);
		topPanel.setCellWidth(vpLoginInfo, "6em");
		topPanel.setWidth("100%");
		log("Uporabnik <strong>" + loginName + "</strong>. Vloga: <strong>" + loginRole + "</strong>");

		menuPanel = new MenuPanel(loginName);
		splitLayoutPanel.addNorth(topPanel, 50);
		splitLayoutPanel.addWest(menuPanel, 60);
		splitLayoutPanel.setWidgetMinSize(menuPanel, 300);

		splitLayoutPanel.setWidgetMinSize(topPanel, 105);
		splitLayoutPanel.add(mainPanel);

		mainPanel.getElement().setId("MainPanel");

		// fast start of newprofile edit profile action of profile epredloga
		/*
		 * Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		 * 
		 * @Override public void execute() { try {
		 * adminService.getProfile(MainPanel.getInstance().loginName,
		 * MainPanel.getInstance().loginPass, "epredloga", new
		 * AsyncCallback<Profile>() {
		 * 
		 * @Override public void onSuccess(Profile prof) { NewProfile np = new
		 * NewProfile(prof);
		 * 
		 * np.show();
		 * 
		 * }
		 * 
		 * @Override public void onFailure(Throwable arg0) {
		 * MainPanel.log(arg0.getMessage()); } }); } catch (Exception ex) {
		 * MainPanel.log(ex.getMessage()); } } });
		 * Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		 * 
		 * @Override public void execute() { np.this.center(); } });
		 */

		/*
		 * try { adminService.getParametrizedQueryByName(MainPanel.getInstance().
		 * loginName, MainPanel.getInstance().loginPass,
		 * "Varnost - dostop do dokumentov za osebo v obdobju", new
		 * AsyncCallback<MyParametrizedQuery>() {
		 * 
		 * @Override public void onSuccess(MyParametrizedQuery result) {
		 * ParametrizedQueryPanel pan = new ParametrizedQueryPanel(result);
		 * SearchEdit se = new SearchEdit(pan); se.show(); }
		 * 
		 * public void onFailure(Throwable caught) {
		 * MainPanel.log(caught.getMessage()); }; }); } catch (ServerException e) {
		 * // TODO Auto-generated catch block MainPanel.log(e.getMessage()); }
		 */

//		loginService.getServerIp(new AsyncCallback<String>() {
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void onSuccess(String result) {
//				DocumentViewCollabora dv = new DocumentViewCollabora("09000001927172ff", result);
//				dv.show();
//
//			}
//		});
		
		loginService.getServerIp(new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				MainPanel.log("Could not get server IP: " + caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				MainPanel.getInstance().serverIp = result;
			}
		});
		

		initWidget(splitLayoutPanel);

	}

	private void refreshLoginInfo(String time) {
		lbServerTime.setText(time);
	}

	public AbsolutePanel getCenterPanel() {
		return mainPanel;
	}

	public static void log(String line) {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			@Override
			public void execute() {
				String innerHtml = getInstance().logHtml.getElement().getInnerHTML();
				String safeHtml;
				if (line.contains("<strong>"))
					safeHtml = SafeHtmlUtils.fromSafeConstant(line + "<br>" + innerHtml).asString();
				else
					safeHtml = SafeHtmlUtils.fromSafeConstant(SafeHtmlUtils.htmlEscape(line) + "<br>" + innerHtml).asString();
				getInstance().logHtml.getElement().setInnerHTML((new HTML(safeHtml)).getHTML());
				Logger.getGlobal().info(line);
			}
		});
	}

	public void logout() {
		final String loginPageUrl = GWT.getHostPageBaseURL() + (GWT.getHostPageBaseURL().contains("127.0.0.1") ? "webui2/" : "");
		log("Logging out. Navigate to login page: " + loginPageUrl);
		Timer timer = new Timer() {
			@Override
			public void run() {
				Window.Location.replace(loginPageUrl);
			}
		};
		timer.schedule(4000);
	}
}
