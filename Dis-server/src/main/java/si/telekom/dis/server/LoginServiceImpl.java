package si.telekom.dis.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfUser;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import si.telekom.dis.shared.LoginService;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.UserSettings;

/**
 * The server-side implementation of the RPC service.
 */
/**
 * @author klemen
 *
 *         if succesfull return object array with following { loginname,
 *         encryptedPassword, role, documentum user_name, repository_name} if
 *         unsuccesfull - return Exception
 *
 */
/**
 * @author klemen
 *
 */
/**
 * @author klemen
 *
 */
@SuppressWarnings("serial")
@RemoteServiceRelativePath("login")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	public static List<String> admins = Arrays
			.asList(new String[] { "zivkovick", "kovacevicr", "shvalec", "dmadmin", "sjakic", "lokart", "kastelicb" });

	/**
	 * returns objects of following ret[0] ... loginName ret[1] ... password
	 * entered by user ret[2] ... role of user ret[3] ... documentum UserName
	 * ret[4] ... documentum repository name ret[5] ... documentum content server
	 * version ret[6] ... UserSettings
	 */
	public String[] login(String loginName, String passwordEncoded) throws ServerException {
		// Verify that the input is valid.
		// if (!FieldVerifier.isValidName(loginName)) {
		// // If the input is not valid, throw an IllegalArgumentException back to
		// // the client.
		// throw new IllegalArgumentException("Name must be at least 4 characters
		// long");
		// }

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		Logger.getLogger(this.getClass()).info("Logging in: " + serverInfo + " userAgent: " + userAgent);

		// Escape data from the client to avoid cross-site script vulnerabilities.
		loginName = escapeHtml(loginName);
		userAgent = escapeHtml(userAgent);

		if (loginName.contains("\\"))
			throw new ServerException("Login name must not contain \\");

		try {
			return checkPassword(loginName, passwordEncoded);
		} catch (Exception ex) {
			throw new ServerException(ex);
		}
	}

	public String[] checkPassword(String loginName, String passwordEncoded) throws Exception {
		return checkPassword(loginName, passwordEncoded, null);
	}

	public String[] checkPassword(String loginName, String passwordEncoded, String ip) throws Exception {
		String ret[] = { "", "", "", "", "", "" };

		IDfSession adminSession = null;
		IDfSession userSess = null;

		try (final DatagramSocket socket = new DatagramSocket()) {

			if (ip == null)
				if (getThreadLocalRequest() != null)
					ip = getThreadLocalRequest().getRemoteHost();

			InetAddress addr = InetAddress.getByName(ip);
			String hostName = addr.getHostName();

			boolean tryDevelop = false;
			if (tryDevelop && passwordEncoded.equals("")
					&& (hostName.contentEquals("localhost") || ip.contentEquals("127.0.0.1") || ip.contentEquals("0:0:0:0:0:0:0:1"))) {
				// if (false) {
				WsServer.maxInactivityTimeSec = 5000;
				ret[0] = "zivkovick";
				ret[1] = Base64.getEncoder().encodeToString("sdfs".getBytes());
				ret[2] = "administrator";
				ret[3] = "ZivkovicK";
				ret[4] = AdminServiceImpl.getInstance().repositoryName;

				ret[0] = "dmadmin";
				ret[1] = Base64.getEncoder().encodeToString("tb25me81".getBytes());
				ret[2] = "administrator";
				ret[3] = "dmadmin";
				ret[4] = AdminServiceImpl.getInstance().repositoryName;

				loginName = "e-kalapcievv";
				loginName = "lokart";

				loginName = "drizvic";

				loginName = "ttaks";
				loginName = "zivkovick";
				// loginName = "ikovacic";
				loginName = "dmedos";
				loginName = "alzupan";
				loginName = "platisem";

				ret[0] = loginName;
				adminSession = AdminServiceImpl.getInstance().getAdminSession();
				IDfUser dcmtUser = (IDfUser) adminSession.getObjectByQualification("dm_user where user_login_name='" + loginName + "'");

				if (loginName.contentEquals("zivkovick"))
					ret[1] = Base64.getEncoder().encodeToString("Doitman890123".getBytes());
				else
					ret[1] = Base64.getEncoder().encodeToString(
							AdminServiceImpl.getInstance().getAdminSession().getLoginTicketForUser(AdminServiceImpl.userDomain + "\\" + ret[0]).getBytes());

				if (admins.contains(loginName))
					ret[2] = "administrator";
				else
					ret[2] = "user";

				ret[3] = dcmtUser.getUserName();
				ret[4] = AdminServiceImpl.repositoryName;

				ret[5] = AdminServiceImpl.getClientX().getLocalClient().getClientConfig().getString("primary_host") + "<br>"
						+ adminSession.getServerConfig().getString("r_server_version");

				Logger.getLogger(this.getClass()).info(String.format("Logged user: %s, role: %s, documentumUser %s", ret[0], ret[2], ret[3]));

				return ret;
			}

			// byte[] fromBase64 = Base64.getDecoder().decode(password);
			// String password = new String(fromBase64);
			// String password = Tools.decryptString(passwordHashed);

			if (passwordEncoded.equals(""))
				passwordEncoded = "blabla";

			boolean ldapCheck = false;

			if (!ldapCheck) {
				userSess = AdminServiceImpl.getSession(loginName, passwordEncoded);
				ret[5] = AdminServiceImpl.getClientX().getLocalClient().getClientConfig().getString("primary_host") + "<br>"
						+ userSess.getServerConfig().getString("r_server_version");
				IDfUser dcmtUser = (IDfUser) userSess.getObjectByQualification("dm_user where user_login_name='" + loginName + "'");

				if (dcmtUser != null) {
					ret[3] = dcmtUser.getUserName();
					ret[4] = userSess.getDocbaseName();
				} else {
					ret[3] = null;
					ret[4] = null;
				}
			} else {

				// Set up the environment for creating the initial context
				Hashtable env = new Hashtable();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				env.put(Context.PROVIDER_URL, "ldap://srdc01.ts.telekom.si:389");

				// Authenticate as S. User and password "mysecret"
				env.put(Context.SECURITY_AUTHENTICATION, "simple");
				env.put(Context.SECURITY_PRINCIPAL, "CN=" + loginName + ",OU=Users,OU=TelekomSlovenije,DC=ts,DC=telekom,DC=si");

				// env.put(Context.SECURITY_PRINCIPAL, "ts\\" + user);

				env.put(Context.SECURITY_CREDENTIALS, String.valueOf(passwordEncoded));

				// Create the initial context
				DirContext ctx = null;
				ctx = new InitialDirContext(env);
				// set administratr role to certain users
				ret[3] = null;
				ret[4] = null;
				ret[5] = "unknown";
			}

			if (admins.contains(loginName)) {
				ret[2] = "administrator";
				Logger.getLogger(this.getClass()).info("User " + loginName + " is in role administrator.");
			} else {
				ret[2] = "user";
			}

		} catch (NamingException ex) {
			// 525 user not found
			// 52e invalid credentials
			// 530 not permitted to logon at this time
			// 531 not permitted to logon at this workstation
			// 532 password expired
			// 533 account disabled
			// 701 account expired
			// 773 user must reset password
			// 775 user account locked
			HashMap errors = new HashMap<String, String>();
			errors.put("525", "user not found");
			errors.put("52e", "invalid credentials");
			errors.put("530", "not permitted to logon at this time");
			errors.put("532", "password expired");
			errors.put("533", "account disabled");
			errors.put("569", "denied access to computer via network");
			errors.put("701", "account expired");
			errors.put("773", "user must reset password");
			errors.put("775", "account locked");
			String foundStr = ", data";
			int loc = ex.getMessage().indexOf(foundStr);
			String errCode = ex.getMessage().substring(loc + foundStr.length() + 1, loc + foundStr.length() + 4);

			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(LoginServiceImpl.class).error(byteAOs.toString());

			throw new Exception("Problem with ldap while logging user: " + loginName + ", " + errors.get(errCode));
		} catch (Exception ex1) {
			throw new Exception("Documentum authentication problem:" + ex1.getMessage());
		} finally {
			if (userSess != null)
				userSess.getSessionManager().release(userSess);
			if (adminSession != null)
				adminSession.getSessionManager().release(adminSession);

		}

		ret[0] = loginName;
		ret[1] = new String(passwordEncoded);

		Logger.getLogger(this.getClass()).info(String.format("Logged user: %s, role: %s, documentumUser %s", ret[0], ret[2], ret[3]));

		return ret;

	}

	public static String queryLdap(String filter) throws NamingException {
		String ret = null;
		Hashtable env = new Hashtable();

		String sp = "com.sun.jndi.ldap.LdapCtxFactory";
		env.put(Context.INITIAL_CONTEXT_FACTORY, sp);

		String ldapUrl = "ldap://srdc01.ts.telekom.si";
		env.put(Context.PROVIDER_URL, ldapUrl);

		env.put(Context.SECURITY_PRINCIPAL, "ts\\dmadmin");
		env.put(Context.SECURITY_CREDENTIALS, "Andrej1");

		env.put(Context.REFERRAL, "follow");

		DirContext dctx = new InitialDirContext(env);

		String base = "DC=ts,DC=telekom,DC=si";

		SearchControls sc = new SearchControls();
		String[] attributeFilter = { "cn", "mail" };
		sc.setReturningAttributes(attributeFilter);
		sc.setSearchScope(SearchControls.SUBTREE_SCOPE);

		// String filter = "(&(sn=W*)(l=Criteria*))";

		NamingEnumeration results = dctx.search(base, filter, sc);
		while (results.hasMore()) {
			SearchResult sr = (SearchResult) results.next();
			Attributes attrs = sr.getAttributes();

			Attribute attr = attrs.get("cn");
			ret = attr.get().toString();
			attr = attrs.get("mail");
		}
		dctx.close();

		return ret;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *          the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public UserSettings getUserSettings(String loginName, String passwordEncrypted) throws ServerException {
		UserSettings us = null;

		IDfSession userSess = null;
		IDfSession adminSession = null;
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(UserSettings.class);

			userSess = AdminServiceImpl.getSession(loginName, passwordEncrypted);

			String currentPrivateFolder = userSess.getUser(userSess.getLoginUserName()).getDefaultFolder();
			String privateFolder = "/" + userSess.getLoginUserName();
			if (!currentPrivateFolder.equals(privateFolder)) {
				// not correct private folder change it on user!
				adminSession = AdminServiceImpl.getAdminSession();

				IDfUser user = adminSession.getUser(userSess.getLoginUserName());
				user.setDefaultFolder(privateFolder, true);
				user.save();

			}

			IDfSysObject obj = (IDfSysObject) userSess
					.getObjectByQualification("dm_document where folder('" + privateFolder + "') and object_name='disUserSettings'");

			if (obj == null) {
				obj = (IDfSysObject) userSess.newObject("dm_document");
				obj.link(privateFolder);
				obj.setObjectName("disUserSettings");

				us = new UserSettings();
				us.explorerReturnResultCount = 30;
				us.searchReturnResultCount = 30;
				us.auditTrailPerPageCount = 100;
				us.useColaboraOnlineForEdit = false;

				Marshaller m = context.createMarshaller();
				m.marshal(us, writer);

				ByteArrayOutputStream baOs = new ByteArrayOutputStream();
				baOs.write(writer.toString().getBytes("UTF-8"));
				obj.setContentType("xml");
				obj.setContent(baOs);
				obj.save();
			}

			ByteArrayInputStream baIs = obj.getContent();

			Unmarshaller m = context.createUnmarshaller();
			Reader reader = new InputStreamReader(baIs);
			us = (UserSettings) m.unmarshal(reader);

			if (us.auditTrailPerPageCount == 0)
				us.auditTrailPerPageCount = 50;

			Logger.getLogger(this.getClass()).info("AuditTrailPerPage: " + us.auditTrailPerPageCount);

		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		} finally {
			if (adminSession != null)
				AdminServiceImpl.getInstance().releaseSession(adminSession);
			if (userSess != null)
				AdminServiceImpl.getInstance().releaseSession(userSess);
		}

		return us;
	}

	@Override
	public Void saveUserSettings(String loginName, String passwordEncrypted, UserSettings us) throws ServerException {
		// TODO Auto-generated method stub
		IDfSession userSess = null;
		try {
			userSess = AdminServiceImpl.getSession(loginName, passwordEncrypted);
			String privateFolder = "/" + userSess.getLoginUserName();
			WsServer.log(loginName, "Saving user settings to xml disUserSettings in " + privateFolder + " ...");

			IDfSysObject obj = (IDfSysObject) userSess
					.getObjectByQualification("dm_document where folder('" + privateFolder + "') and object_name='disUserSettings'");

			JAXBContext context = JAXBContext.newInstance(UserSettings.class);

			StringWriter writer = new StringWriter();
			Marshaller m = context.createMarshaller();
			m.marshal(us, writer);

			ByteArrayOutputStream baOs = new ByteArrayOutputStream();
			baOs.write(writer.toString().getBytes("UTF-8"));
			obj.setContentType("xml");
			obj.setContent(baOs);
			obj.save();

			WsServer.log(loginName, "Saving user settings to xml disUserSettings in " + privateFolder + " ... Done.");

		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).error(ex.getMessage());
		} finally {
			if (userSess != null)
				userSess.getSessionManager().release(userSess);
		}
		return null;

	}

	@Override
	public String getServerIp() {
		String ret = "";
		try {
			String[] split= InetAddress.getLocalHost().toString().split("/");
			
			ret = split[split.length-1];
		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).error(ex);
		}
		return ret;
	}

}
