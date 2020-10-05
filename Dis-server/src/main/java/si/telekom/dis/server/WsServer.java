package si.telekom.dis.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/ws")
public class WsServer {

	static Timer timer = new Timer();

	static HashMap<String, Session> sessions = new HashMap<String, Session>();
	static HashMap<String, Instant> lastGetSessionTime = new HashMap<String, Instant>();
	static int maxInactivityTimeSec = 10 * 60;

	static {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				ArrayList<String> toremove = new ArrayList<String>();
				try {
					for (String userLoginName : sessions.keySet()) {
						Session session = sessions.get(userLoginName);
						String data = "Ping";
						if (session.isOpen()) {
							ByteBuffer payload = ByteBuffer.wrap(data.getBytes());
							session.getAsyncRemote().sendPing(payload);
							SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
							session.getAsyncRemote().sendText("time=" + sdf.format(new Date()));
							Instant now = Instant.now();

							Instant lastGetSession = lastGetSessionTime.get(userLoginName);
							if (lastGetSession != null) {
								Duration timeElapsed = Duration.between(lastGetSession, now);
								if (timeElapsed.getSeconds() > maxInactivityTimeSec) {
									try {
										session.getAsyncRemote().sendText("logout");
									} catch (Exception ex) {

									}
									Logger.getLogger(this.getClass())
											.warn(userLoginName + " inactivity time " + timeElapsed.getSeconds() + "s greater then: " + maxInactivityTimeSec + "s");
									toremove.add(userLoginName);
								}
							}
						}

					}
					// } catch (ClosedChannelException ccex) {
					// Logger.getLogger(this.getClass()).warn("Websocket mesage session "
					// + userLoginName + "closed.");
					// sessions.remove(userLoginName);
					// lastGetSessionTime.remove(userLoginName);
					// } catch (IllegalArgumentException e) {
					// Logger.getLogger(this.getClass()).error(e);
				} catch (Exception e) {
					StringWriter errors = new StringWriter();
					e.printStackTrace(new PrintWriter(errors));
					Logger.getLogger(this.getClass()).error(errors);
				}

				for (String toRemoveLoginName : toremove) {
					if (sessions.get(toRemoveLoginName).isOpen())
						try {
							sessions.get(toRemoveLoginName).close(new CloseReason(CloseCodes.NO_STATUS_CODE, "inactivity"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					sessions.remove(toRemoveLoginName);
				}
			}
		}, 5 * 1000, 1000);

	}

	@OnOpen
	public synchronized void onOpen(Session session) {
		session.setMaxIdleTimeout(0);
		Map<String, List<String>> params = session.getRequestParameterMap();
		Logger.getLogger(this.getClass()).info("WebSocket session onOpen::" + session.getId());
		String loginName = params.get("loginName").get(0).toString();
		sessions.put(loginName, session);
	}

	@OnClose
	public synchronized void onClose(Session session) {
		Map<String, List<String>> params = session.getRequestParameterMap();
		Logger.getLogger(this.getClass()).info("WebSocket session for: " + params.get("loginName").toString() + " onClose::" + session.getId());

		try {
			String loginName = params.get("loginName").toString();
			sessions.remove(loginName);
			Logger.getLogger(this.getClass()).info("removed session for user: " + loginName);

		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).info("Unable to remove session: " + ex.getMessage());
		}
		Logger.getLogger(this.getClass()).info("WebSocket session onClose::" + session.getId());

	}

	@OnMessage
	public synchronized void onMessage(String message, Session session) {
		Logger.getLogger(this.getClass()).info("Websocket sessionId: " + session.getId() + "!");
	}

	@OnError
	public synchronized void onError(Throwable t) {
		Logger.getLogger(this.getClass()).error(t);
	}

	public synchronized static void log(String toUser, String message) {
		try {
			if (toUser != null)
				if (toUser.contentEquals("_all_")) {
					for (String user : sessions.keySet()) {
						sessions.get(user).getAsyncRemote().sendText(message);
					}
				} else {
					if (sessions.get(toUser) != null)
						sessions.get(toUser).getAsyncRemote().sendText(message);
				}
		} catch (Exception ex) {
			Logger.getLogger(WsServer.class).info("could not report log");
		}
	}

	public static void logAll(String message) {
		log("_all_", message);
	}

	public static void setLastGetSessionTime(String userName) {
		Instant now = Instant.now();
		lastGetSessionTime.put(userName, now);
	}
}