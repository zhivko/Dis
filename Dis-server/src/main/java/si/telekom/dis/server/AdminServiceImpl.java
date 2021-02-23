package si.telekom.dis.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.Logger;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.DfVersionPolicy;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfGroup;
import com.documentum.fc.client.IDfPermit;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.client.IDfVersionPolicy;
import com.documentum.fc.client.content.IDfStore;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.DfLoginInfo;
import com.documentum.fc.common.DfTime;
import com.documentum.fc.common.DfValue;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfList;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.common.IDfTime;
import com.documentum.fc.common.IDfValue;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.Base64Utils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import si.telekom.dis.server.jobs.MoveMobFormTemplateToEffective;
import si.telekom.dis.shared.Action;
import si.telekom.dis.shared.AdminService;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.DcmtAttribute;
import si.telekom.dis.shared.DocType;
import si.telekom.dis.shared.ExtendedPermit.extPermit;
import si.telekom.dis.shared.HasIdName;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.Permit.permit;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.Role;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.StandardAction;
import si.telekom.dis.shared.State;
import si.telekom.dis.shared.Tab;
import si.telekom.dis.shared.Template;
import si.telekom.dis.shared.TemplateFolder;
import si.telekom.dis.shared.UserGroup;

/**
 * The server-side implementation of the RPC service.
 */
/**
 * @author klemen killing gwt embeded jetty server: kill -9 $(lsof -t -i:9876)
 *
 */
@SuppressWarnings("serial")
@RemoteServiceRelativePath("admin")
public class AdminServiceImpl extends RemoteServiceServlet implements AdminService {
	static public IDfSessionManager sessMgr = null;
	
	public static boolean started = false;
	private static DfClientX myDctmClientX;

	public static String superUserName = "dmadmin";
	public static String superUserDomain = "mobitel";
	public static String userDomain = "ts";
	public static String superUserPassword = "tb25me81";
	public static String repositoryName = "Mobitel";
	public static String configPath = "/app/render/DocMan";
	private static String configDmPath = "/System/DocMan";

	public static HashMap<String, Profile> profiles;
	public static HashMap<String, DocType> doctypes;
	private static HashMap<String, DcmtAttribute> attributes;
	private static Transformer transformer;

	private static AdminServiceImpl instance;

	private static BarcodeHandlerImpl barcodeHandler;

	public static String BARCODE_USER = "barcode";
	public static String BARCODE_PASSWORD = "barcode1234";
	public static String BARCODE_SQL_SERVER_HOST = "bpw-docsql.ts.telekom.si";
	public static String BARCODE_SQL_SERVER_PORT = "1433";
	public static String BARCODE_SQL_SERVER_DB_NAME = "DM_Mobitel_docbase";
	public static boolean MOVE_TO_EFFECTIVE_JOB_ENABLED = false;

	public static DfClientX CX;
	static Document docConfig;
	static String configPathFileName;

	static DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	static {
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	/**
	 * retentionAddUnit could be day, month or year
	 */
	protected static String retentionAddUnit;
	public static HashMap<String, IDfGroup> allGroups = new HashMap<String, IDfGroup>();

	static {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				Logger.getLogger(AdminServiceImpl.class).info("Starting...");
				// Thread.currentThread().setName("WebUi-StartThread");
				long timeMs1 = System.currentTimeMillis();

				CX = new DfClientX();

				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				try {
					transformer = transformerFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes");
					transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				} catch (TransformerConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// register needed tables
				registerTables();

				// check if ecsstore_dis exists if not create it
				checkEcsStore("ecsstore_dis", true);
				checkEcsStore("ecsstore_dis_a", false);
				checkEcsStore("ecsstore_dis_t", false);

				// deserialize doctypes
				try {
					File f = new File(configPath);
					if (!f.exists())
						f.mkdirs();
					File serFile = new File((f.getAbsolutePath() + File.separator + "doctypes.ser"));
					if (serFile.exists()) {
						InputStream file = new FileInputStream(serFile.getAbsolutePath());
						InputStream buffer = new BufferedInputStream(file);
						ObjectInput input = new ObjectInputStream(buffer);
						doctypes = (HashMap<String, DocType>) input.readObject();
						Logger.getLogger(AdminServiceImpl.class).info("Deserialized doctypes.");
						input.close();
						buffer.close();
					} else {
						syncDocTypes();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					try {
						syncDocTypes();
					} catch (Exception ex1) {
						ex1.printStackTrace();
					}
				}

				// deserializa attributes
				try {
					File f = new File(configPath);
					if (!f.exists())
						f.mkdirs();
					File serFile = new File((f.getAbsolutePath() + File.separator + "attributes.ser"));
					if (serFile.exists()) {
						InputStream file = new FileInputStream(serFile.getAbsolutePath());
						InputStream buffer = new BufferedInputStream(file);
						ObjectInput input = new ObjectInputStream(buffer);
						attributes = (HashMap<String, DcmtAttribute>) input.readObject();
						Logger.getLogger(AdminServiceImpl.class).info("Deserialized attributes.");
						input.close();
						buffer.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				for (DocType doctype : doctypes.values()) {
					doctype.profiles.clear();
				}
				// deserialize profiles
				try {
					File f = new File(configPath);
					if (!f.exists())
						f.mkdirs();
					File serFile = new File((f.getAbsolutePath() + File.separator + "profiles.ser"));
					if (serFile.exists()) {
						InputStream file = new FileInputStream(serFile.getAbsolutePath());
						InputStream buffer = new BufferedInputStream(file);
						ObjectInput input = new ObjectInputStream(buffer);
						Logger.getLogger(AdminServiceImpl.class).info("Deserializing profiles...");
						profiles = (HashMap<String, Profile>) input.readObject();

						for (Profile prof : profiles.values()) {
							DocType docType = doctypes.get(prof.objType);
							if (docType != null) {
								docType.profiles.put(prof.id, prof);
								Logger.getLogger(AdminServiceImpl.class).info(String.format("\tobjType: %s profile %s", prof.id, docType.name));
							} else {
								Logger.getLogger(AdminServiceImpl.class).info(String.format("\tobjType: '%s' on profile '%s' doesn't exist!", prof.objType, prof.id));
							}
						}
						// check if modify date in docbase is newer than from filesystem
						// than pick it up
						// from docbase

						Logger.getLogger(AdminServiceImpl.class).info("Deserialized profiles.");
						input.close();
						buffer.close();

						IDfSession adminSession = AdminServiceImpl.getAdminSession();

						Logger.getLogger(AdminServiceImpl.class)
								.info("DIS Telekom started on: " + AdminServiceImpl.getClientX().getLocalClient().getClientConfig().getString("primary_host") + " "
										+ adminSession.getServerConfig().getString("r_server_version"));

						adminSession.getSessionManager().release(adminSession);
					} else {
						syncProfiles();
						// for (Profile prof : profiles.values()) {
						// DocType docType = doctypes.get(prof.objType);
						// if (docType != null) {
						// docType.profiles.put(prof.id, prof);
						// Logger.getLogger(AdminServiceImpl.class).info(String.format("\tobjType:
						// %s profile %s", prof.id, docType.name));
						// } else {
						// Logger.getLogger(AdminServiceImpl.class).info(String.format("\tprofile
						// %s unknown objType: %s", prof.id, prof.objType));
						// }
						// }
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					syncProfiles();
				}

				// HashMap<String, DocType> doctypes2 = new HashMap<String, DocType>();
				// SortedSet<String> keys = new TreeSet<>(doctypes.keySet());
				// for (String key : keys) {
				// DocType value = doctypes.get(key);
				// doctypes2.put(key, value);
				// }
				// doctypes = doctypes2;

				getBarcodeHandlerImpl();

				long timeMs2 = System.currentTimeMillis();
				Logger.getLogger(AdminServiceImpl.class).info("Started in: " + Math.round(((timeMs2 - timeMs1) / 1000)) + "s.");

				started=true;
				// MassClassify();
				// checkPermForUser();

			}
		});
		t.setName("MyStartThread");
		t.start();

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Logger.getLogger(AdminServiceImpl.class).info("Syncing groups...");
						IDfSession adminSession = getAdminSession();
						IDfQuery qry = new DfQuery("select r_object_id, group_name from dm_group");
						IDfCollection coll = qry.execute(adminSession, IDfQuery.DF_READ_QUERY);
						while (coll.next()) {
							String groupName = coll.getString("group_name");
							IDfGroup group = adminSession.getGroup(groupName);
							allGroups.put(groupName, group);
						}
						coll.close();
						adminSession.getSessionManager().release(adminSession);
						Logger.getLogger(AdminServiceImpl.class).info("Syncing groups...end.");
						// 1 hour
						Thread.sleep(1 * 60 * 60 * 1000);
					}
				} catch (Exception e) {
					Logger.getLogger(AdminServiceImpl.class).error(e);
				}
			}
		});
		t1.setName("SyncGroups");
		//t1.start();

		if (MOVE_TO_EFFECTIVE_JOB_ENABLED) {
			Logger.getLogger(AdminServiceImpl.class).info("Starting MOVE_TO_EFFECTIVE_JOB");
			MoveMobFormTemplateToEffective job = new MoveMobFormTemplateToEffective();
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(job, 0, 60, TimeUnit.MINUTES);
		}
		// correctAcls();

	}

	static boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}

	public static void readStartupParamFromServletContext(ServletContext context) {

		try {
			// String classPath =
			// AdminServiceImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			// classPath = classPath.replace("classes", "");
			// String webxml = classPath + "web.xml";

			Context env = (Context) new InitialContext().lookup("java:comp/env");

			// ServletContext servletContext = WebappContext.getServletContext();
			AdminServiceImpl.BARCODE_SQL_SERVER_DB_NAME = context.getInitParameter("barcode.database");
			AdminServiceImpl.BARCODE_SQL_SERVER_HOST = context.getInitParameter("barcode.sqlHost");
			AdminServiceImpl.BARCODE_SQL_SERVER_PORT = context.getInitParameter("barcode.sqlPort");
			AdminServiceImpl.BARCODE_USER = context.getInitParameter("barcode.user");
			AdminServiceImpl.BARCODE_PASSWORD = context.getInitParameter("barcode.password");

			AdminServiceImpl.MOVE_TO_EFFECTIVE_JOB_ENABLED = Boolean.valueOf(context.getInitParameter("MOVE_TO_EFFECTIVE_JOB_ENABLED"));

			AdminServiceImpl.repositoryName = context.getInitParameter("documentum.docbaseName");
			AdminServiceImpl.superUserName = context.getInitParameter("documentum.superUserLogin");
			AdminServiceImpl.superUserPassword = context.getInitParameter("documentum.superUserPassword");
			AdminServiceImpl.userDomain = context.getInitParameter("documentum.userDomain");
			AdminServiceImpl.superUserDomain = context.getInitParameter("documentum.superUserDomain");

			AdminServiceImpl.retentionAddUnit = context.getInitParameter("retention.addUnit");

			AdminServiceImpl.configPath = context.getInitParameter("configPath");
		} catch (Throwable ex) {
			System.out.println(ex.getMessage());
			System.exit(-1);
		}
	}

	protected static void checkEcsStore(String ecsstoreDisName, boolean requiresRetentionDate) {
		// TODO Auto-generated method stub

		IDfSession adminSession = null;
		try {
			adminSession = getAdminSession();
			IDfStore ecsStore = (IDfStore) adminSession.getObjectByQualification("dm_ca_store where name='" + ecsstoreDisName + "'");
			if (ecsStore == null) {
				// create it.
				ecsStore = (IDfStore) adminSession.newObject("dm_ca_store");

				ecsStore.setName(ecsstoreDisName);
				List<List<String>> lookupResult = ExplorerServiceImpl.getInstance().dqlLookup(superUserName,
						Base64.getEncoder().encode(superUserPassword.getBytes()).toString(), "select r_object_id from dm_plugin where object_name='CSEC Plugin'");
				String plugin_id = lookupResult.get(0).get(0);
				ecsStore.setId("a_plugin_id", new DfId(plugin_id));

				// ecsStore.setBoolean("digital_shredding", true);
				if (requiresRetentionDate) {
					ecsStore.setBoolean("a_retention_attr_required", true);
					ecsStore.setString("a_retention_attr_name", "retention_date");
				} else {
					IDfTime defaultRetDate = new DfTime("01.12.9999 01:01:01", "dd.MM.yyyy HH:mm:ss", true);
					ecsStore.setTime("a_default_retention_date", defaultRetDate);
					ecsStore.setString("a_retention_attr_name", "retention_date");
				}

				// c:\PEA\ecs_documentum.pea

				ecsStore.setRepeatingString("a_storage_params", 0,
						"primary=10.122.225.101,10.122.225.102,10.122.225.103,10.122.225.104,secondary=10.122.225.201,10.122.225.202,10.122.225.203,10.122.225.204?path=d:\\pea\\doc-test-ecs.pea");
				ecsStore.setRepeatingString("a_storage_params", 1, "pool_option:embedded_blob:99");
				ecsStore.setRepeatingString("a_storage_params", 2, "pool_option:clip_buffer_size:250");
				ecsStore.setRepeatingString("a_storage_params", 3, "pool_option:max_connections:198");

				ecsStore.save();
				Logger.getLogger(AdminServiceImpl.class).info("ECS store " + ecsstoreDisName + " created.");
			} else {
				Logger.getLogger(AdminServiceImpl.class).info("ECS store " + ecsstoreDisName + " exists.");
			}
		} catch (Throwable ex) {
			Logger.getLogger(AdminServiceImpl.class).error(ex.getMessage());
		} finally {
			if (adminSession != null)
				adminSession.getSessionManager().release(adminSession);
		}

	}

	public static AdminServiceImpl getInstance() {
		if (instance == null)
			instance = new AdminServiceImpl();
		return instance;
	}

	@Override
	public List<Action> getActions(String loginName, String password) throws ServerException {
		// TODO Auto-generated method stub
		ArrayList<Action> ret = new ArrayList<Action>();
		ret.add(new Action("document.properties", "lastnosti", permit.BROWSE));
		ret.add(new Action("document.delete", "briši", permit.DELETE));
		ret.add(new Action("document.view", "poglej vsebino", permit.READ));
		ret.add(new Action("document.edit", "urejaj vsebino", permit.WRITE));

		ArrayList<extPermit> extPermitManageUsers = new ArrayList<>();
		extPermitManageUsers.add(extPermit.CHANGE_PERMIT);
		ret.add(new Action("document.manageUsers", "upravljaj uporabnike", permit.WRITE, extPermitManageUsers));
		ret.add(new Action("document.classifyDoc", "klasificiraj", permit.WRITE));
		ret.add(new Action("other.action1", "akcija1"));
		ret.add(new Action("other.action2", "akcija2"));
		ret.add(new Action("document.versions", "verzije", permit.READ));
		ret.add(new Action("document.audittrail", "revizijska sled", permit.READ));
		ret.add(new Action("document.checkIn", "checkin", permit.VERSION));
		ret.add(new Action("document.addRendition", "dodaj rendicijo", permit.WRITE));
		ret.add(new Action("document.removeRendition", "odstrani rendicijo", permit.WRITE));
		ret.add(new Action("document.checkOut", "checkout", permit.WRITE));
		ret.add(new Action("document.unlock", "odkleni", permit.WRITE));
		ret.add(new Action("document.addVersionLabel", "dodaj labelo verzije", permit.WRITE));
		ret.add(new Action("document.removeVersionLabel", "odstrani labelo verzije", permit.WRITE));
		ret.add(new Action("document.cancelCheckOut", "Prekliči checkout", permit.WRITE));

		ArrayList<extPermit> extPermitPromoteDemote = new ArrayList<>();
		extPermitPromoteDemote.add(extPermit.CHANGE_PERMIT);
		extPermitPromoteDemote.add(extPermit.CHANGE_LOCATION);

		ret.add(new Action("document.promote", "v naslednje stanje", permit.WRITE, extPermitPromoteDemote));
		ret.add(new Action("document.demote", "v prejšnje stanje", permit.WRITE, extPermitPromoteDemote));
		ret.add(new Action("document.pdfTags", "pdf tagi", permit.READ));
		ret.add(new Action("document.newRelease", "nova različica", permit.READ));

		ret.add(new Action("folder.importDocument", "uvozi dokument", permit.WRITE));
		ret.add(new Action("folder.useForAiTraining", "uporabi za ai učenje", permit.READ));

		ret.add(new Action("template.editCollIds", "uredi polja", permit.NONE));
		ret.add(new Action("template.updateBusinessNotification", "ažuriraj bussines<br>notification", permit.NONE));
		ret.add(new Action("template.syncTemplate", "sinhroniziraj eRender<br>predlogo", permit.NONE));

		return ret;
	}

	public Action getActionById(String actionId) throws ServerException {
		// TODO Auto-generated method stub
		List<Action> ret = getActions("", "");
		for (Action action : ret) {
			if (action.getId().equals(actionId))
				return action;
		}
		return null;
	}

	@Override
	public List<DocType> getDocTypes(String loginName, String password) throws ServerException {
		// TODO Auto-generated method stub
		DocType docType = doctypes.get("mob_form_template");
		ArrayList<DocType> doctypesAl = new ArrayList<DocType>(doctypes.values());
		return doctypesAl;
	}

	@Override
	public List<Profile> getProfiles(String loginName, String password) throws ServerException {
		// TODO Auto-generated method stub
		ArrayList<Profile> profilesAl = new ArrayList<Profile>(profiles.values());
		return profilesAl;
	}

	@Override
	public Profile getProfile(String loginName, String password, String profileId) {
		if (profiles.containsKey(profileId)) {
			Profile prof = profiles.get(profileId);

			String object_name = "profile_" + prof.id;
			File profileFile = new File(configPath + "/" + object_name + ".xml");
			IDfSysObject sysObj;
			IDfSession sess = null;
			long t1 = System.currentTimeMillis();
			try {
				sess = getAdminSession();
				sysObj = (IDfSysObject) sess
						.getObjectByQualification("dm_document where object_name='" + object_name + "' and folder('" + configDmPath + "')");
				if (pullFromDcmtIfNeeded(sysObj, prof)) {
					Date modifiedDocumentumTime = null;
					if (!sysObj.getTime("a_effective_date").isNullDate())
						modifiedDocumentumTime = sysObj.getTime("a_effective_date").getDate();
					else {
						modifiedDocumentumTime = sysObj.getModifyDate().getDate();
					}
					Date date = utcFormat.parse(utcFormat.format(modifiedDocumentumTime));
					String msg = "Modify date of profile " + prof.id + " in docbase " + utcFormat.format(date) + " is NEWER than profile modify date:"
							+ utcFormat.format(prof.modifyDateUTC);
					Logger.getLogger(this.getClass()).info(msg);
					WsServer.log(loginName, msg);
					prof = profiles.get(profileId);
				}

			} catch (DfException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (sess != null)
					sess.getSessionManager().release(sess);
			}
			long t2 = System.currentTimeMillis();
			Logger.getLogger(AdminService.class).info(String.format(String.format("Return profile with id=" + profileId + " in %d ms.", t2 - t1)));

			return prof;
		}

		Profile ret = null;

		try {
			getSessionManager();

			// try to get profile from local path - if it doesnt exist load it from
			// documentum
			String object_name = "profile_" + profileId;
			File profileFile = new File(configPath + "/" + object_name + ".xml");

			if (!profileFile.exists()) {
				getSessionManager();

				IDfSession userSession = null;
				synchronized (sessMgr) {
					IDfLoginInfo loginInfo = new DfLoginInfo();
					loginInfo.setUser(loginName);
					loginInfo.setDomain(userDomain);
					loginInfo.setPassword(password);
					sessMgr.clearIdentities();
					sessMgr.setIdentity(repositoryName, loginInfo);
					userSession = sessMgr.getSession(repositoryName);
				}

				IDfSysObject sysObj = (IDfSysObject) userSession
						.getObjectByQualification("dm_document where object_name='" + object_name + "' and folder(" + configDmPath + ")");
				if (sysObj != null) {
					sysObj.getFile(profileFile.getAbsolutePath());
				}

				Logger.getLogger(AdminServiceImpl.class).info("");
			}

			// now profileFile should exist, lets parse it
			ret = parseProfile(profileFile.getAbsolutePath());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Logger.getLogger(AdminServiceImpl.class).info("Get profile END.");
		return ret;

	}

	private static boolean pullFromDcmtIfNeeded(IDfSysObject sysObj, Profile prof)
			throws XPathExpressionException, ParserConfigurationException, SAXException, IOException, DfException, ParseException {
		Date modifiedDocumentumTime = null;
		if (!sysObj.getTime("a_effective_date").isNullDate())
			modifiedDocumentumTime = sysObj.getTime("a_effective_date").getDate();
		else {
			modifiedDocumentumTime = sysObj.getModifyDate().getDate();
		}

		Date date = modifiedDocumentumTime;

		Logger.getLogger(AdminServiceImpl.class).info(utcFormat.format(date));
		Logger.getLogger(AdminServiceImpl.class).info(utcFormat.format(prof.modifyDateUTC));

		if (utcFormat.format(date).equals(utcFormat.format(prof.modifyDateUTC))) {
			Logger.getLogger(AdminServiceImpl.class).info("Modify date of profile " + prof.id + " in docbase " + utcFormat.format(date)
					+ " is EQUAL than profile modify date:" + utcFormat.format(prof.modifyDateUTC));
			return false;
		} else if (date.compareTo(prof.modifyDateUTC) == 1) {
			Logger.getLogger(AdminServiceImpl.class).info("Modify date of profile " + prof.id + " in docbase " + utcFormat.format(date)
					+ " is NEWER than profile modify date:" + utcFormat.format(prof.modifyDateUTC));

			String object_name = "profile_" + prof.id;
			File profileFile = new File(configPath + "/" + object_name + ".xml");

			sysObj.getFile(profileFile.getAbsolutePath());
			prof = parseProfile(profileFile.getAbsolutePath());
			Logger.getLogger(AdminServiceImpl.class).info("\tCopied and parsed profile from docbase.");

			profiles.put(prof.id, prof);
			doctypes.get(prof.objType).profiles.put(prof.id, prof);
			try {
				serializeProfiles();
				serializeDocTypes();
			} catch (Exception e) {
				Logger.getLogger(AdminServiceImpl.class).error("Error serializing dcmtypes and profiles.");
			}
			return true;
		} else {
			Logger.getLogger(AdminServiceImpl.class).warn("Modify date of profile " + prof.id + " in docbase " + utcFormat.format(date)
					+ " is OLDER than profile modify date:" + utcFormat.format(prof.modifyDateUTC));

			String object_name = "profile_" + prof.id;
			File profileFile = new File(configPath + "/" + object_name + ".xml");
			prof = parseProfile(profileFile.getAbsolutePath());

			try {
				// String ret = saveProfile(prof, profileFile.getAbsolutePath(),
				// sysObj);
				profiles.put(prof.id, prof);
				doctypes.get(prof.objType).profiles.put(prof.id, prof);
				serializeProfiles();
				serializeDocTypes();
				// Logger.getLogger(AdminServiceImpl.class).info("\tSaved profile to
				// docbase.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}

	private static Profile parseProfile(String absolutePath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		// TODO Auto-generated method stub
		Logger.getLogger(AdminServiceImpl.class).info("Parse profile: " + absolutePath);
		Profile retProfile = new Profile();
		// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl",
				AdminServiceImpl.class.getClassLoader());
		DocumentBuilder builder = factory.newDocumentBuilder();

		Logger.getLogger(AdminServiceImpl.class).info("Parsing: " + absolutePath);
		Document doc = builder.parse("file:///" + absolutePath);
		XPath xPath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xPath.compile("/profile");

		// Evaluate expression result on XML document
		Element profileNode = (Element) expr.evaluate(doc, XPathConstants.NODE);

		Date modifiedTime = null;
		String modifiedTimeStr = profileNode.getAttribute("modify_date_utc");
		if (!modifiedTimeStr.equals(""))
			try {
				Logger.getLogger(AdminServiceImpl.class).info("Parsing date: " + modifiedTimeStr);

				modifiedTime = utcFormat.parse(modifiedTimeStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				DateFormat form = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
				try {
					modifiedTime = form.parse(modifiedTimeStr);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// e.printStackTrace();
				// modifiedTime = new Date((new File(absolutePath)).lastModified());
			}
		else
			modifiedTime = new Date((new File(absolutePath)).lastModified());
		retProfile.modifyDateUTC = modifiedTime;

		Field[] fields = retProfile.getClass().getDeclaredFields();
		Logger.getLogger(AdminServiceImpl.class).info(String.format("%d fields:", fields.length));
		for (Field field : fields) {
			Logger.getLogger(AdminServiceImpl.class)
					.info(String.format("\t%s %s %s", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));
			String value = profileNode.getAttribute(field.getName());
			try {
				// field.set(retProfile, value);

				if (field.getAnnotatedType().getType().getTypeName().equals("boolean")) {
					field.set(retProfile, Boolean.valueOf(value));
				} else if (field.getAnnotatedType().getType().getTypeName().equals("int")) {
					if (!value.equals(""))
						field.set(retProfile, Integer.valueOf(value));
				} else if (field.getAnnotatedType().getType().getTypeName().equals("java.lang.String")) {
					field.set(retProfile, String.valueOf(value));
				} else if (field.getAnnotatedType().getType().getTypeName().equals("ArrayList")) {
				} else {
					// field.set(a, value);
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		retProfile.templates = new ArrayList<Template>();
		expr = xPath.compile("/profile/templates/template");
		// Evaluate expression result on XML document
		NodeList profileTemplates = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < profileTemplates.getLength(); i++) {
			Element elTemplate = (Element) profileTemplates.item(i);

			Template template = new Template(elTemplate.getAttribute("object_name"));
			retProfile.templates.add(template);
		}

		retProfile.templateFolderPaths = new ArrayList<TemplateFolder>();
		expr = xPath.compile("/profile/templates/templateFolderPaths");
		// Evaluate expression result on XML document
		NodeList profileTemplateFoldPaths = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < profileTemplateFoldPaths.getLength(); i++) {
			Element elTemplateFolder = (Element) profileTemplateFoldPaths.item(i);
			TemplateFolder templateFolder = new TemplateFolder(elTemplateFolder.getAttribute("folderPath"));
			retProfile.templateFolderPaths.add(templateFolder);
		}

		expr = xPath.compile("/profile/roles/role");
		// Evaluate expression result on XML document
		NodeList profileRoles = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		ArrayList<Role> roles = new ArrayList<Role>();
		for (int i = 0; i < profileRoles.getLength(); i++) {
			Element nRole = (Element) profileRoles.item(i);
			Role role = new Role(nRole.getAttribute("id"), nRole.getAttribute("name"));

			XPathExpression expr2 = xPath.compile("./defaultUserGroup");
			NodeList nlUserGroups = (NodeList) expr2.evaluate(nRole, XPathConstants.NODESET);
			for (int j = 0; j < nlUserGroups.getLength(); j++) {
				Element ugEl = (Element) nlUserGroups.item(j);
				UserGroup ug = new UserGroup();
				ug.setId(ugEl.getAttribute("id"));
				role.defaultUserGroups.add(ug);
			}
			roles.add(role);
		}
		retProfile.roles = roles;

		expr = xPath.compile("/profile/states/state");
		// Evaluate expression result on XML document
		NodeList profileStates = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		ArrayList<State> states = new ArrayList<State>();
		for (int i = 0; i < profileStates.getLength(); i++) {
			Element nState = (Element) profileStates.item(i);
			State state = new State(nState.getAttribute("id"), nState.getAttribute("name"));
			XPathExpression expr2 = xPath.compile("./standardAction");
			NodeList sActions = (NodeList) expr2.evaluate(nState, XPathConstants.NODESET);
			for (int j = 0; j < sActions.getLength(); j++) {
				Element saEl = (Element) sActions.item(j);
				StandardAction sa = new StandardAction();
				sa.kind = saEl.getAttribute("kind");
				sa.parameter = saEl.getAttribute("parameter");
				state.standardActions.add(sa);
			}
			states.add(state);
		}
		retProfile.states = states;

		expr = xPath.compile("/profile/tabs/tab");
		// Evaluate expression result on XML document
		NodeList profileTabs = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		ArrayList<Tab> tabs = new ArrayList<Tab>();
		for (int i = 0; i < profileTabs.getLength(); i++) {
			Element nTab = (Element) profileTabs.item(i);
			Tab tab = new Tab(nTab.getAttribute("id"), nTab.getAttribute("name"));
			Logger.getLogger(AdminServiceImpl.class).info("tab id= " + tab.getId());
			String cols = nTab.getAttribute("cols");
			String rows = nTab.getAttribute("rows");
			if (cols != null && !cols.equals("") && !cols.contentEquals("null")) {
				tab.col = Integer.valueOf(cols);
				if (rows != null && !rows.equals("") && !rows.contentEquals("null")) {
					tab.row = Integer.valueOf(rows);
					tabs.add(tab);
				} else {
					Logger.getLogger(AdminServiceImpl.class).error("tab id= " + tab.getId() + " rows value: " + rows + " cannot be parsed.");
				}

			} else {
				Logger.getLogger(AdminServiceImpl.class).error("tab id= " + tab.getId() + " cols value: " + cols + " cannot be parsed.");
			}
		}
		retProfile.tabs = tabs;

		expr = xPath.compile("/profile/attributes");
		NodeList profileAttributes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		ArrayList<AttributeRoleStateWizards> arsws = new ArrayList<AttributeRoleStateWizards>();

		for (int i = 0; i < profileAttributes.getLength(); i++) {
			Element elProfileAttributes = (Element) profileAttributes.item(i);
			AttributeRoleStateWizards arsw = new AttributeRoleStateWizards();
			// attributes
			NodeList nlElAttribute = elProfileAttributes.getElementsByTagName("attribute");
			for (int j = 0; j < nlElAttribute.getLength(); j++) {
				Element nAttribute = (Element) nlElAttribute.item(j);
				// System.out.println(nAttribute.getTextContent());

				Attribute a = new Attribute();
				Field[] fieldsA = a.getClass().getDeclaredFields();
				for (Field field : fieldsA) {
					// System.out.println(field);
					String value = nAttribute.getAttribute(field.getName());
					String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length());
					int modifiers = field.getModifiers();
					if (modifiers == 1) {
						try {
							if (field.getAnnotatedType().getType().getTypeName().equals("boolean")) {
								if (!value.equals(""))
									field.set(a, Boolean.valueOf(value));
							} else if (field.getAnnotatedType().getType().getTypeName().equals("int")) {
								if (!value.equals(""))
									field.set(a, Integer.valueOf(value));
							} else if (field.getAnnotatedType().getType().getTypeName().equals("java.lang.String")) {
								if (!value.equals("") && !value.equals("null")) {
									field.set(a, String.valueOf(value));
								}

							} else if (field.getAnnotatedType().getType().getTypeName().equals("ArrayList")) {
							} else {
								// field.set(a, value);
							}
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							Method m = null;
							if (field.getAnnotatedType().getType().getTypeName().equals("boolean"))
								m = a.getClass().getMethod(methodName, boolean.class);
							else if (field.getAnnotatedType().getType().getTypeName().equals("int"))
								m = a.getClass().getMethod(methodName, int.class);
							else if (field.getAnnotatedType().getType().getTypeName().equals("java.lang.String"))
								m = a.getClass().getMethod(methodName, String.class);
							if (m != null) {
								m.invoke(a, value);
								Logger.getLogger(AdminServiceImpl.class).info("method " + methodName + " invoked.");
							} else {
								Logger.getLogger(AdminServiceImpl.class).error("unknown field type " + field.getName());
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				arsw.attributes.add(a);
			}

			// wizards
			XPathExpression expr2 = xPath.compile("wizards/wizard");
			NodeList nlWizards = (NodeList) expr2.evaluate(elProfileAttributes, XPathConstants.NODESET);
			for (int j = 0; j < nlWizards.getLength(); j++) {
				Element nWizard = (Element) nlWizards.item(j);
				arsw.wizards.add(nWizard.getAttribute("id"));
			}

			// state roles
			expr2 = xPath.compile("stateRoles/state");
			NodeList nlStates = (NodeList) expr2.evaluate(elProfileAttributes, XPathConstants.NODESET);
			for (int j = 0; j < nlStates.getLength(); j++) {
				Element nState = (Element) nlStates.item(j);
				expr2 = xPath.compile("role");
				NodeList nlRoles = (NodeList) expr2.evaluate(nState, XPathConstants.NODESET);
				ArrayList<String> stateRoles = new ArrayList<String>();
				for (int k = 0; k < nlRoles.getLength(); k++) {
					Element nRole = (Element) nlRoles.item(k);
					stateRoles.add(nRole.getAttribute("id"));
				}
				arsw.stateRole.put(nState.getAttribute("id"), stateRoles);
			}

			arsws.add(arsw);
		}
		retProfile.attributeRolesStatesWizards = arsws;

		retProfile.roleStateActions = new HashMap<String, Map<String, List<String>>>();

		// expr = xPath.compile("/profile/actions");
		Element profileActions = (Element) profileNode.getElementsByTagName("actions").item(0); // /profile/actions
		if (profileActions == null) {
			profileActions = (Element) profileNode.appendChild(doc.createElement("actions"));
		}
		// /profile/actions/state
		NodeList nlStates = profileActions.getElementsByTagName("state");
		for (int i = 0; i < nlStates.getLength(); i++) {
			Element elState = (Element) nlStates.item(i);
			String stateId = elState.getAttribute("id");
			NodeList nlRoles = elState.getElementsByTagName("role");
			HashMap<String, List<String>> rolesInStateActions = new HashMap<String, List<String>>();
			for (int j = 0; j < nlRoles.getLength(); j++) {
				Element elRole = (Element) nlRoles.item(j);
				String roleId = elRole.getAttribute("id");
				XPathExpression expr3 = xPath.compile("action");
				NodeList nlActions = (NodeList) expr3.evaluate(elRole, XPathConstants.NODESET);
				ArrayList<String> actions = new ArrayList<String>();
				for (int k = 0; k < nlActions.getLength(); k++) {
					/// profile/actions/state/role/action
					if (nlActions.item(k).getNodeType() == Node.ELEMENT_NODE) {
						Element elAction = (Element) nlActions.item(k);
						String actionId = elAction.getAttribute("id");
						actions.add(actionId);
					}
				}
				rolesInStateActions.put(roleId, actions);
			}
			retProfile.roleStateActions.put(stateId, rolesInStateActions);
		}

		expr = xPath.compile("/profile/detailAttributes/attribute");
		NodeList detailAttributes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		retProfile.detailAttributes = new ArrayList<Attribute>();

		for (int i = 0; i < detailAttributes.getLength(); i++) {
			Element elAttribute = (Element) detailAttributes.item(i);
			Attribute a = new Attribute(elAttribute.getAttribute("id"));
			retProfile.detailAttributes.add(a);
		}

		return retProfile;
	}

	public static IDfSessionManager getSessionManager() throws Exception {
		// https://community.emc.com/thread/179349?start=0&tstart=0
		if (sessMgr == null) {
			long n1 = System.currentTimeMillis();
			getClientX();
			long n2 = System.currentTimeMillis();
			Logger.getLogger(AdminService.class).info(String.format(String.format("getClientX took %d ms.", n2 - n1)));

			n1 = System.currentTimeMillis();
			Logger.getLogger(AdminService.class).info(String.format(String.format("Started myDctmClientX.getLocalClient()")));
			IDfClient myDfClient = myDctmClientX.getLocalClient();
			long n3 = System.currentTimeMillis();
			Logger.getLogger(AdminService.class).info(String.format(String.format("Started myDctmClientX.getLocalClient()...Done. took:" + (n3 - n1))));

			sessMgr = myDfClient.newSessionManager();
			Logger.getLogger(AdminService.class).info(String.format(String.format("Started myDctmClientX.getLocalClient().newSessionManager()...Done.")));
			n2 = System.currentTimeMillis();
			Logger.getLogger(AdminService.class).info(String.format(String.format("newSessionManager took %d ms.", n2 - n3)));
			try {
				n1 = System.currentTimeMillis();
				IDfClient client = new DfClient();
				n2 = System.currentTimeMillis();
				Logger.getLogger(AdminService.class).info(String.format(String.format("new DfClient() took %d ms.", n2 - n1)));
				IDfTypedObject config = client.getClientConfig();
				String docbrokerAdress = config.getString("primary_host");
				int docbrokerPort;
				docbrokerPort = config.getInt("primary_port");
				Logger.getLogger(AdminService.class).info(String.format("Docbroker host: %s port: %d", docbrokerAdress, docbrokerPort));
			} catch (DfException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sessMgr;
	}

	public static IDfClientX getClientX() {
		if (myDctmClientX == null)
			myDctmClientX = new DfClientX();
		return myDctmClientX;
	}

	@Override
	public synchronized String setProfile(String loginName, String passwordEncrypted, Profile prof) throws ServerException {
		String profileId = prof.id;

		String password = new String(Base64Utils.fromBase64(passwordEncrypted), Charset.forName("UTF-8"));

		if (profileId.equals("")) {
			throw new ServerException("profileId should not be empty: prof name:" + prof.name);
		}
		DocType docType = doctypes.get(prof.objType);
		if (docType == null)
			throw new ServerException(String.format("Such objectType (%s) doesn't exist.", prof.objType));

		String ret = null;
		IDfSysObject sysObj = null;
		IDfSession userSession = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum
			String object_name = "profile_" + profileId;
			File profileFile = new File(configPath + "/" + object_name + ".xml");

			// getSessionManager();

			userSession = getAdminSession();
			sysObj = (IDfSysObject) userSession
					.getObjectByQualification("dm_document where object_name='" + object_name + "' and folder('" + configDmPath + "')");

			if (sysObj == null) {
				sysObj = createNewFromTemplate(profileId, userSession);
			} else {
				if (!profileFile.exists()) {
					sysObj.getFile(profileFile.getAbsolutePath());
				}
			}

			// now profileFile should exist, lets parse it
			ret = saveProfile(prof, profileFile.getAbsolutePath(), sysObj, userSession);

			profiles.put(prof.id, prof);
			docType.profiles.put(prof.id, prof);
			doctypes.put(docType.id, docType);

			Profile p = doctypes.get("mob_form_template").profiles.get("epredloga");

			serializeProfiles();
			serializeDocTypes();
		} catch (Throwable e1) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			e1.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(e1.getMessage());
		} finally {
			if (userSession != null)
				userSession.getSessionManager().release(userSession);
		}

		return ret;
	}

	private static IDfSysObject createNewFromTemplate(String profileId, IDfSession userSession) throws Exception {
		String object_name = "profile_" + profileId;
		File profileFile = new File(configPath + "/" + object_name + ".xml");

		ServletContext ctxt = WebappContext.servletContext;
		String profileTemplateFilePath;
		if (!profileFile.exists()) {
			profileTemplateFilePath = ctxt.getRealPath("WEB-INF/classes/profile-template.xml");
			Files.copy(Paths.get(profileTemplateFilePath), Paths.get(profileFile.getAbsolutePath()));
		} else {
			profileTemplateFilePath = profileFile.getAbsolutePath();
		}

		IDfSysObject sysObj = (IDfSysObject) userSession.newObject("dm_document");
		getOrCreateFolder(configDmPath);
		sysObj.link(configDmPath);
		sysObj.setObjectName(object_name);
		sysObj.setContentType("xml");
		sysObj.grant("documentum-admin", 7, "");
		sysObj.setFile(profileTemplateFilePath);
		sysObj.save();

		return sysObj;
	}

	private static String saveProfile(Profile prof, String absolutePath, IDfSysObject sysObj, IDfSession userSession) throws Exception {

		Logger.getLogger(AdminServiceImpl.class).info("Saving profile: " + prof.id + " to absolutePath: " + absolutePath);
		// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance("com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl",
				AdminServiceImpl.class.getClassLoader());

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = builder.parse("file:///" + absolutePath);
		XPath xPath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xPath.compile("/profile");

		// Evaluate expression result on XML document
		Element profileNode = (Element) expr.evaluate(doc, XPathConstants.NODE);
		Logger.getLogger(AdminServiceImpl.class).info("\tSaving profile....");
		Field[] fields = prof.getClass().getDeclaredFields();
		// System.out.printf("%d fields:%n", fields.length);
		for (Field field : fields) {
			Logger.getLogger(AdminServiceImpl.class)
					.info(String.format("\t\t%s %s %s", Modifier.toString(field.getModifiers()), field.getType().getSimpleName(), field.getName()));
			if (field.getType().getSimpleName().equals("String") || field.getType().getSimpleName().equals("boolean")
					|| field.getType().getSimpleName().equals("int")) {
				String value = String.valueOf(field.get(prof));
				profileNode.setAttribute(field.getName(), value);
			}
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving templates...");
		expr = xPath.compile("/profile/templates");
		Element elTemplates = (Element) expr.evaluate(doc, XPathConstants.NODE);
		// delete all elements
		if (elTemplates == null) {
			elTemplates = (Element) profileNode.appendChild(doc.createElement("templates"));
		}
		while (elTemplates.getChildNodes().getLength() > 0) {
			elTemplates.removeChild(elTemplates.getChildNodes().item(0));
		}

		for (Template t : prof.templates) {
			Element elTemplate = (Element) elTemplates.appendChild(doc.createElement("template"));
			elTemplate.setAttribute("object_name", t.object_name);
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving template folders...");
		for (TemplateFolder t : prof.templateFolderPaths) {
			Element elTemplateFolder = (Element) elTemplates.appendChild(doc.createElement("templateFolderPaths"));
			elTemplateFolder.setAttribute("folderPath", t.folderPath);
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving roles.");
		expr = xPath.compile("/profile/roles");
		Element roles = (Element) expr.evaluate(doc, XPathConstants.NODE);
		// delete all elements
		while (roles.getChildNodes().getLength() > 0) {
			roles.removeChild(roles.getChildNodes().item(0));
		}
		for (HasIdName rol : prof.roles) {
			Role role = (Role) rol;
			Element nodRole = doc.createElement("role");
			nodRole.setAttribute("id", rol.getId());
			nodRole.setAttribute("name", rol.getParameter());

			for (UserGroup ug : role.defaultUserGroups) {
				Element nodUG = doc.createElement("defaultUserGroup");
				nodUG.setAttribute("id", ug.getId());
				nodRole.appendChild(nodUG);
			}

			roles.appendChild(nodRole);
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving states.");
		expr = xPath.compile("/profile/states");
		Element states = (Element) expr.evaluate(doc, XPathConstants.NODE);
		// delete all elements
		while (states.getChildNodes().getLength() > 0) {
			states.removeChild(states.getChildNodes().item(0));
		}
		for (HasIdName state : prof.states) {
			Element nodState = doc.createElement("state");
			nodState.setAttribute("id", state.getId());
			nodState.setAttribute("name", state.getParameter());
			State sta = (State) state;
			for (StandardAction sa : sta.standardActions) {
				Element nodStandardAction = doc.createElement("standardAction");
				nodStandardAction.setAttribute("kind", sa.kind);
				nodStandardAction.setAttribute("parameter", sa.parameter);
				nodState.appendChild(nodStandardAction);
			}
			states.appendChild(nodState);
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving tabs.");
		expr = xPath.compile("/profile/tabs");
		Element tabs = (Element) expr.evaluate(doc, XPathConstants.NODE);
		// delete all elements
		while (tabs.getChildNodes().getLength() > 0) {
			tabs.removeChild(tabs.getChildNodes().item(0));
		}
		for (Tab tab : prof.tabs) {
			Element nodTab = doc.createElement("tab");
			nodTab.setAttribute("id", tab.getId());
			nodTab.setAttribute("name", tab.getParameter());
			nodTab.setAttribute("rows", String.valueOf(tab.row));
			nodTab.setAttribute("cols", String.valueOf(tab.col));
			tabs.appendChild(nodTab);
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving attributes.");
		while (true) {
			Node nodAtts = profileNode.getElementsByTagName("attributes").item(0);
			if (nodAtts == null)
				break;
			profileNode.removeChild(nodAtts);
		}

		for (AttributeRoleStateWizards arsw : prof.attributeRolesStatesWizards) {
			Element nodAttributes = doc.createElement("attributes");

			// attributes
			for (Tab tab : prof.tabs) {
				for (Attribute attribute : arsw.attributes) {
					if (attribute.tabId.equals(tab.getId()) && attribute != null && attribute.dcmtAttName != null && !attribute.dcmtAttName.equals("")) {

						Element nodAttribute = doc.createElement("attribute");

						Field[] fieldsAtt = attribute.getClass().getDeclaredFields();
						Logger.getLogger(AdminServiceImpl.class).debug(String.format("%d fields:", fields.length));
						for (Field field : fieldsAtt) {
							int modifiers = field.getModifiers();
							String value = null;
							if (field.getType().getSimpleName().equals("String") || field.getType().getSimpleName().equals("boolean")
									|| field.getType().getSimpleName().equals("int")) {
								if (modifiers == 1) {
									value = String.valueOf(field.get(attribute));
								} else {
									String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length());
									Method m = attribute.getClass().getMethod(methodName);

									value = (String) m.invoke(attribute);
								}

								if (field.getName().startsWith("defaultValueIsConstant")) {
									if (attribute.defaultValue != null)
										System.out.println("");
								}

								Logger.getLogger(AdminServiceImpl.class).debug(String.format("\t%s %s %s value: %s", Modifier.toString(field.getModifiers()),
										field.getType().getSimpleName(), field.getName(), value));
								nodAttribute.setAttribute(field.getName(), value);
							}

							nodAttributes.appendChild(nodAttribute);
						}
					}
				}
			}

			// wizards that use this attribute definition
			Element wizards = (Element) nodAttributes.appendChild(doc.createElement("wizards"));
			// remove wizard
			// while (true) {
			// Node nodWizard = wizards.getElementsByTagName("wizard").item(0);
			// if (nodWizard == null)
			// break;
			// wizards.removeChild(nodWizard);
			// }
			// add wizard
			for (String wizard : arsw.wizards) {
				Element nodWizard = doc.createElement("wizard");
				nodWizard.setAttribute("id", wizard);
				wizards.appendChild(nodWizard);
			}
			nodAttributes.appendChild(wizards);
			//
			// state/role that use this attribute definition

			// remove stateRoles
			// while (true) {
			// Node stateRoles =
			// nodAttributes.getElementsByTagName("stateRoles").item(0);
			// if (stateRoles == null)
			// break;
			// nodAttributes.removeChild(stateRoles);
			// }
			// add stateRoles
			Element stateRoles = (Element) doc.createElement("stateRoles");
			ArrayList<String> statesToRemove = new ArrayList<String>();
			for (String stateId : arsw.stateRole.keySet()) {

				// try to find if roleid is defined in profile and it exists
				boolean ok = false;
				for (State sta : prof.states) {
					if (sta.getId().contentEquals(stateId)) {
						ok = true;
						break;
					}
				}
				if (ok) {
					Element elState = (Element) doc.createElement("state");
					elState.setAttribute("id", stateId);
					for (String roleId : arsw.stateRole.get(stateId)) {
						Element elRole = (Element) doc.createElement("role");
						elRole.setAttribute("id", roleId);
						elState.appendChild(elRole);
					}
					stateRoles.appendChild(elState);
				} else {
					statesToRemove.add(stateId);
				}
			}
			nodAttributes.appendChild(stateRoles);

			// removes orphaned states
			for (String stateId : statesToRemove) {
				arsw.stateRole.remove(stateId);
			}

			profileNode.appendChild(nodAttributes);
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving roleStateActions.");
		// actions
		// prof.roleStateActions
		Element elActions = (Element) profileNode.getElementsByTagName("actions").item(0);
		if (elActions != null) {
			while (true) {
				elActions = (Element) profileNode.getElementsByTagName("actions").item(0);
				if (elActions.getChildNodes().getLength() > 0)
					elActions.removeChild(elActions.getChildNodes().item(0));
				else
					break;
			}
		} else {
			elActions = (Element) profileNode.appendChild(doc.createElement("actions"));
		}

		for (String stateId : prof.roleStateActions.keySet()) {
			Map<String, List<String>> rolesActionsInState = prof.roleStateActions.get(stateId);
			Element stateEl = (Element) elActions.appendChild(doc.createElement("state"));
			stateEl.setAttribute("id", stateId);
			for (String roleId : rolesActionsInState.keySet()) {
				Element roleEl = (Element) stateEl.appendChild(doc.createElement("role"));
				roleEl.setAttribute("id", roleId);
				List<String> actions = rolesActionsInState.get(roleId);
				for (String actionId : actions) {
					Element actionEl = (Element) roleEl.appendChild(doc.createElement("action"));
					actionEl.setAttribute("id", actionId);
				}
			}
		}

		Logger.getLogger(AdminServiceImpl.class).info("\tSaving details.");
		// profile/detailAttributes/attribute
		if (profileNode.getElementsByTagName("detailAttributes").item(0) == null) {
			profileNode.appendChild(doc.createElement("detailAttributes"));
		}
		Element elDetailAttributes = (Element) profileNode.getElementsByTagName("detailAttributes").item(0);
		while (elDetailAttributes.getChildNodes().getLength() > 0)
			elDetailAttributes.removeChild(elDetailAttributes.getChildNodes().item(0));

		for (Attribute a : prof.detailAttributes) {
			Element elAtt = (Element) elDetailAttributes.appendChild(doc.createElement("attribute"));
			elAtt.setAttribute("id", a.dcmtAttName);
		}

		Instant instant = Instant.now();
		prof.modifyDateUTC = Date.from(instant);
		String stringModifyTime = utcFormat.format(prof.modifyDateUTC);
		profileNode.setAttribute("modify_date_utc", stringModifyTime);

		DOMSource source = new DOMSource(doc);
		FileWriter writer = new FileWriter(new File(absolutePath));
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);

		String msg = "Profile: " + prof.id + " saved to file on server.";
		WsServer.logAll(msg);
		Logger.getLogger(AdminServiceImpl.class).info(msg);

		// checkIn as new version in documentum
		boolean keepLock = false;

		String oldVersLabels = sysObj.getAllRepeatingStrings("r_version_label", ",");
		Logger.getLogger(AdminServiceImpl.class).info("Old version label: " + oldVersLabels);

		IDfVersionPolicy vp = sysObj.getVersionPolicy();
		boolean canVersionMinor = ((DfVersionPolicy) vp).canVersion(DfVersionPolicy.DF_NEXT_MINOR);

		String msg1 = "Immutable? " + sysObj.isImmutable() + " Frozen? " + sysObj.isFrozen() + " permit: " + sysObj.getPermit() + " canVersionMinor: "
				+ canVersionMinor;
		Logger.getLogger(AdminServiceImpl.class).info(msg1);

		if (!canVersionMinor) {
			String msg2 = "Could not version profile: " + prof.id + " to docbase.";
			WsServer.log("_all_", msg2);
			Logger.getLogger(AdminServiceImpl.class).error(msg2);
			sysObj.destroyAllVersions();

			sysObj = createNewFromTemplate(prof.id, userSession);
			vp = sysObj.getVersionPolicy();

		}

		// sysObj.getPermit()
		if (!sysObj.isCheckedOutBy(AdminServiceImpl.superUserName))
			sysObj.checkout();

		sysObj.setContentType("xml");
		sysObj.setFile(absolutePath); // assuming you're using

		String toAddVersions = vp.getNextMinorLabel() + ",CURRENT";
		IDfId dfnewid = sysObj.checkin(keepLock, toAddVersions);

		IDfSession adminSess = getAdminSession();

		IDfSysObject newSysObj = (IDfSysObject) adminSess.getObject(dfnewid);
		newSysObj.setTime("a_effective_date", new DfTime(prof.modifyDateUTC));
		newSysObj.fetch("dm_document");
		newSysObj.save();

		Logger.getLogger(AdminServiceImpl.class).info(utcFormat.format(newSysObj.getTime("a_effective_date").getDate()));
		Logger.getLogger(AdminServiceImpl.class).info(utcFormat.format(prof.modifyDateUTC));

		String msg2 = "Checked in profile: " + prof.id + " to documentum as new version: " + newSysObj.getAllRepeatingStrings("r_version_label", ",")
				+ " a_effective_date: " + utcFormat.format(newSysObj.getTime("a_effective_date").getDate());
		WsServer.log("_all_", msg2);
		Logger.getLogger(AdminServiceImpl.class).info(msg2);

		AdminServiceImpl.getInstance().releaseSession(adminSess);
		return "";
	}

	public static IDfId getOrCreateFolder(String folderPath) throws Exception {
		IDfId ret = null;

		ret = folderExist(folderPath);
		if (ret == null) {
			IDfSession dfSuperUserSession = getAdminSession();
			String[] folderTokens = folderPath.split("/");
			String tempPath = "";
			for (String token : folderTokens) {
				if (!token.equals("")) {
					tempPath = tempPath + "/" + token;
					ret = folderExist(tempPath);
					if (ret == null) {
						try {
							Logger.getLogger(AdminServiceImpl.class).info("Creating path: " + tempPath + "...");
							String previousFolder = tempPath.substring(0, tempPath.lastIndexOf("/"));
							IDfFolder dmFolder = null;
							if (previousFolder.equals(""))
								dmFolder = (IDfFolder) dfSuperUserSession.newObject("dm_cabinet");
							else {
								dmFolder = (IDfFolder) dfSuperUserSession.newObject("dm_folder");
								dmFolder.link(previousFolder);
							}

							String folderName = tempPath.substring(tempPath.lastIndexOf("/") + 1);
							dmFolder.setObjectName(folderName);
							dmFolder.setACLName("dcmtservice_folder");
							dmFolder.setACLDomain("dm_dbo");

							dmFolder.save();
							Logger.getLogger(AdminServiceImpl.class).info("Creating path: " + tempPath + "...DONE.");
						} catch (Exception ex2) {
							ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
							PrintWriter pw = new PrintWriter(byteAOs);
							ex2.printStackTrace(pw);
							pw.flush();
							Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
							throw new Exception("Error creating or finding folder.");
						}
					}
				}
			}
			dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
		}

		return ret;
	}

	private static IDfId folderExist(String folderPath) throws Exception {
		IDfId ret = null;
		IDfFolder objFolder = null;

		int i = 0;

		getSessionManager();

		IDfSession userSession = null;
		synchronized (sessMgr) {
			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser(superUserName);
			loginInfo.setDomain(superUserDomain);
			loginInfo.setPassword(superUserPassword);
			sessMgr.clearIdentities();
			sessMgr.setIdentity(repositoryName, loginInfo);
			userSession = sessMgr.getSession(repositoryName);
			objFolder = userSession.getFolderByPath(folderPath);
			if (objFolder != null) {
				i++;
				ret = objFolder.getId("r_object_id");
			}
			userSession.getSessionManager().release(userSession);
		}
		Logger.getLogger(AdminServiceImpl.class).info("There is " + i + " such folder.");

		return ret;
	}

	public static void registerTables() {
		IDfQuery query = new DfQuery();

		IDfSession dfSuperUserSession = null;
		IDfCollection collection = null;
		try {
			dfSuperUserSession = getAdminSession();

			Logger.getLogger(AdminServiceImpl.class).info("Setting permission start.");

			IDfId objId = dfSuperUserSession.getIdByQualification("dm_registered where object_name='T_DOCMAN_S'");
			if (!objId.isNull()) {
				String unregisterDql = "UNREGISTER dm_dbo.T_DOCMAN_S";
				Logger.getLogger(AdminServiceImpl.class).info("\t" + unregisterDql);
				query.setDQL(unregisterDql);
				collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);
				collection.close();
			}

			objId = dfSuperUserSession.getIdByQualification("dm_registered where object_name='T_DOCMAN_R'");
			if (!objId.isNull()) {
				String unregisterDql = "UNREGISTER dm_dbo.T_DOCMAN_R";
				Logger.getLogger(AdminServiceImpl.class).info("\t" + unregisterDql);
				query.setDQL(unregisterDql);
				collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);
				collection.close();
			}

//@formatter:off
			String registerDql = "REGISTER TABLE dbo.T_DOCMAN_S " + " (" + "r_object_id STRING(16), "
					+ "object_name STRING(255), " + "profile_id STRING(50)," + "current_state_id STRING(50)" + ")";
//@formatter:on
			Logger.getLogger(AdminServiceImpl.class).info("\t" + registerDql);
			query.setDQL(registerDql);
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);
			collection.close();
//@formatter:off
			registerDql = "REGISTER TABLE dbo.T_DOCMAN_R " + " (" + "r_object_id STRING(16), "
					+ "object_name STRING(255), " + "role_id STRING(50), " + "user_group_name STRING(50)" + ")";
//@formatter:on
			Logger.getLogger(AdminServiceImpl.class).info("\t" + registerDql);
			query.setDQL(registerDql);
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);
			collection.close();

//@formatter:off
			String permissionDql = "UPDATE dm_registered OBJECT " + "  set owner_table_permit=15, "
					+ "  set group_permit=7," + "  set world_permit=7, " + "  set world_table_permit=15, "
					+ "  set group_table_permit=15 " + "where object_name='T_DOCMAN_S' or object_name='T_DOCMAN_R'";
//@formatter:on
			query.setDQL(permissionDql);
			Logger.getLogger(AdminServiceImpl.class).info("\t" + permissionDql);
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);

			Logger.getLogger(AdminServiceImpl.class).info("Setting permission done.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (collection != null)
					collection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (dfSuperUserSession != null)
					dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void syncDocTypes() throws ServerException {
		syncDocTypes(null);
	}

	public static void syncDocTypes(String loginName) throws ServerException {
		IDfQuery query = new DfQuery();

		IDfSession dfSuperUserSession = null;
		IDfCollection collection = null;
		try {
			// enumerate doctypes and store them into hashmap
			dfSuperUserSession = getAdminSession();
			String additionalDoctypes_1 = "ipko_%";
			String additionalDoctypes_2 = "ts_%";

			// @formatter:off
			String attDql = "select r_object_id, type_name,attr_name,domain_type,attr_repeating, read_only,domain_length,label_text,comment_text "
					+ "from dmi_dd_attr_info ai, dm_type_r r, dm_type_s s "
					+ "where s.r_object_id = r.r_object_id and ai.type_name=s.name and r.attr_name = ai.attr_name "
					+ "and (type_name like 'mob_%' or " + "     type_name like '" + additionalDoctypes_1 + "' or type_name like '" + additionalDoctypes_2 + "' or "
					+ "     type_name = 'dm_document' or " + "     type_name = 'dm_folder' or "
					+ "     type_name = 'dm_cabinet' or " + "     type_name = 'dm_job' or "
					+ "     type_name = 'dm_sysobject') " + "order by type_name, attr_name";
			// @formatter:on
			query.setDQL(attDql);
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_READ_QUERY);
			String tempType = "";
			DocType type = null;
			doctypes = new HashMap<String, DocType>();
			attributes = new HashMap<String, DcmtAttribute>();
			while (collection.next()) {
				String type_name = collection.getString("type_name");
				String attr_name = collection.getString("attr_name");
				String domain_type = collection.getString("domain_type");
				boolean attr_repeating = collection.getInt("attr_repeating") == 1 ? true : false;
				boolean read_only = collection.getInt("read_only") == 1 ? true : false;
				int domain_length = collection.getInt("domain_length");
				String label_text = collection.getString("label_text");
				String comment_text = collection.getString("comment_text");

				if (!type_name.equals(tempType)) {
					if (type != null) {
						doctypes.put(type.id, type);
						Logger.getLogger(AdminServiceImpl.class).info("Read dm_type: " + type.id);
						WsServer.log(loginName, "Doctype: " + type_name + " attributes count: " + type.attributes.size());
					}
					type = new DocType(type_name, type_name);
					if (dfSuperUserSession.getType(type_name).getSuperType() != null)
						type.superName = dfSuperUserSession.getType(type_name).getSuperType().getName();

					// add r_object_id
					DcmtAttribute dcmtAttribute = new DcmtAttribute("r_object_id", "3", false, true, 16, "r_object_id", "ID of object");
					attributes.put(type.id + "." + dcmtAttribute.attr_name, dcmtAttribute);
					type.addAttribute(dcmtAttribute);
					//

				}

				DcmtAttribute dcmtAttribute = new DcmtAttribute(attr_name, domain_type, attr_repeating, read_only, domain_length, label_text, comment_text);
				attributes.put(type.id + "." + dcmtAttribute.attr_name, dcmtAttribute);
				type.addAttribute(dcmtAttribute);
				tempType = type_name;
			}

			WsServer.log(loginName, "Serializing doctypes.");
			serializeDocTypes();
			WsServer.log(loginName, "Serializing attributes.");
			serializeAtts();
			WsServer.log(loginName, "Serializing attributes done.");

		} catch (Exception ex) {
			ex.printStackTrace();
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex);

		} finally {
			try {
				collection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private static void serializeAtts() throws Exception {

		Path path = Paths.get(configPath + File.separator + "attributes.ser");
		try {
			Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-rw-r--");
			FileAttribute<?> permissions = PosixFilePermissions.asFileAttribute(ownerWritable);
			if (path.toFile().exists())
				path.toFile().delete();
			Files.createFile(path, permissions);
		} catch (Exception ex) {
			Logger.getLogger(AdminServiceImpl.class).warn("Unable to set posix permissions - probably not unix fs.");
		}

		File serFile = path.toFile();

		FileOutputStream fout = new FileOutputStream(serFile.getAbsolutePath());
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(attributes);
		fout.close();
		oos.close();
		Logger.getLogger(AdminServiceImpl.class).info("Serialized " + serFile.getAbsolutePath());
	}

	private static void serializeDocTypes() throws Exception {
		Path path = Paths.get(configPath + File.separator + "doctypes.ser");
		try {
			Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-rw-r--");
			FileAttribute<?> permissions = PosixFilePermissions.asFileAttribute(ownerWritable);
			if (path.toFile().exists())
				path.toFile().delete();
			Files.createFile(path, permissions);
		} catch (Exception ex) {
			Logger.getLogger(AdminServiceImpl.class).warn("Unable to set posix permissions - probably not unix fs.");
		}
		File serFile = path.toFile();
		FileOutputStream fout = new FileOutputStream(serFile.getAbsolutePath());
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(doctypes);
		fout.close();
		oos.close();
		Logger.getLogger(AdminServiceImpl.class).info("Serialized " + serFile.getAbsolutePath());
	}

	public static void syncProfiles() {
		syncProfiles(null);
	}

	public static void syncProfiles(String loginName) {
		// enumerate profiles and store them into hashmap
		IDfSession dfSuperUserSession = null;
		String dql = "select r_object_id, object_name from dm_document where folder('/System/DocMan') and object_name like 'profile_%'";
		IDfCollection collection = null;

		try {
			dfSuperUserSession = getAdminSession();

			IDfQuery query = new DfQuery();
			query.setDQL(dql);

			collection = query.execute(dfSuperUserSession, IDfQuery.DF_READ_QUERY);
			profiles = new HashMap<String, Profile>();
			while (collection.next()) {
				String object_name = collection.getString("object_name");
				File profileFile = new File(configPath + "/" + object_name + ".xml");
				if (profileFile.exists()) {
					try {
						Profile prof = parseProfile(profileFile.getAbsolutePath());

						IDfSysObject dfSysObject = (IDfSysObject) dfSuperUserSession
								.getObjectByQualification("dm_document where r_object_id='" + collection.getString("r_object_id") + "'");
						pullFromDcmtIfNeeded(dfSysObject, prof);
						Logger.getLogger(AdminServiceImpl.class).info("Read profile: " + prof.id);
						profiles.put(prof.id, prof);
						WsServer.log(loginName, "Parsed profile: " + prof.name + " (" + prof.id + ")");
					} catch (Exception ex) {
						WsServer.log(loginName, "Problem parsing profile from: " + profileFile);
					}
				}
			}

			File dir = new File(configPath);
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getAbsolutePath().endsWith(".xml")) {
						String object_name = child.getName().split("\\.")[0];
						Logger.getLogger(AdminServiceImpl.class).info("Syncing profile: " + child.getName() + " object_name: " + object_name);
						String profileId = object_name.substring(object_name.indexOf("_") + 1, object_name.length());
						Profile prof = profiles.get(profileId);
						if (prof == null) {
							prof = parseProfile(child.getAbsolutePath());
							try {
								getInstance().setProfile(superUserName, superUserPassword, prof);
							} catch (Exception ex) {
								Logger.getLogger(AdminServiceImpl.class).warn(child.getAbsolutePath() + " " + ex.getMessage());
							}
							// profiles.put(prof.id, prof);
						}
					}
				}
			} else {
				// Handle the case where dir is not really a directory.
				// Checking dir.isDirectory() above would not be sufficient
				// to avoid race conditions with another process that deletes
				// directories.
			}

			for (Profile prof : profiles.values()) {
				DocType docType = doctypes.get(prof.objType);
				if (docType != null) {
					docType.profiles.put(prof.id, prof);
					Logger.getLogger(AdminServiceImpl.class).info(String.format("\tobjType: %s profile %s", prof.id, docType.name));
				} else {
					Logger.getLogger(AdminServiceImpl.class).info(String.format("\tobjType: '%s' on profile '%s' doesn't exist!", prof.objType, prof.id));
				}
			}
			serializeProfiles();

		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			// new ServerException(e.getMessage());
		} finally {
			try {
				collection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	private synchronized static void serializeProfiles() throws IOException {
		Path path = Paths.get(configPath + File.separator + "profiles.ser");
		try {
			Set<PosixFilePermission> ownerWritable = PosixFilePermissions.fromString("rw-rw-r--");
			FileAttribute<?> permissions = PosixFilePermissions.asFileAttribute(ownerWritable);
			if (path.toFile().exists())
				path.toFile().delete();
			Files.createFile(path, permissions);

		} catch (Exception ex) {
			Logger.getLogger(AdminServiceImpl.class).warn("Problem setting posix permission - probably not unix fs.");
		}
		File serFile = path.toFile();
		FileOutputStream fout = new FileOutputStream(serFile.getAbsolutePath());
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(profiles);
		fout.close();
		oos.close();
		Logger.getLogger(AdminServiceImpl.class).info("Serialized " + serFile.getAbsolutePath());
	}

	public static IDfSession getSession(String loginName, String passwordEncrypted) throws Exception {

		if (loginName == null)
			throw new Exception("LoginName should not be null");
		if (passwordEncrypted == null)
			throw new Exception("Password should not be null");

		getSessionManager();

		// String password = Tools.decryptString(passwordEncrypted);
		String password = new String(Base64Utils.fromBase64(passwordEncrypted));

		IDfSession userSession = null;
		synchronized (sessMgr) {
			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser(loginName);
			if (loginName.equals(AdminServiceImpl.superUserName)) {
				loginInfo.setDomain(AdminServiceImpl.superUserDomain);
				System.out.println("Password equals? " + password.equals(AdminServiceImpl.superUserPassword));
			} else
				loginInfo.setDomain(AdminServiceImpl.userDomain);

			loginInfo.setPassword(password);
			loginInfo.setForceAuthentication(false);

			sessMgr.clearIdentities();
			sessMgr.setIdentity(AdminServiceImpl.repositoryName, loginInfo);

			long t1 = System.currentTimeMillis();
			String msg = "Getting session for " + loginInfo.getDomain() + "\\" + loginInfo.getUser() + "...";
			Logger.getLogger(AdminServiceImpl.class).info(msg);
			WsServer.log(loginName, msg);
			WsServer.setLastGetSessionTime(loginInfo.getUser());
			userSession = sessMgr.getSession(AdminServiceImpl.repositoryName);
			long t2 = System.currentTimeMillis();
			String durationStr = String.format(Locale.ROOT, "%.3fs", (t2 - t1) / 1000.0);
			String msg2 = msg + "Done in: " + durationStr;
			WsServer.log(loginName, msg2);
			Logger.getLogger(AdminServiceImpl.class).info(msg2);
		}

		return userSession;
	}

	public static IDfSession getAdminSession() throws Exception {
		getSessionManager();

		IDfSession userSession = null;
		synchronized (sessMgr) {
			IDfLoginInfo loginInfo = new DfLoginInfo();
			loginInfo.setUser(AdminServiceImpl.superUserName);
			loginInfo.setDomain(AdminServiceImpl.superUserDomain);
			loginInfo.setPassword(AdminServiceImpl.superUserPassword);
			loginInfo.setForceAuthentication(false);
			sessMgr.clearIdentities();
			sessMgr.setIdentity(AdminServiceImpl.repositoryName, loginInfo);
			Logger.getLogger(AdminServiceImpl.class).info("Getting admin session for: " + AdminServiceImpl.superUserDomain + "\\"
					+ AdminServiceImpl.superUserName + " pass: " + AdminServiceImpl.superUserPassword + "...");
			userSession = sessMgr.getSession(AdminServiceImpl.repositoryName);
			Logger.getLogger(AdminServiceImpl.class).info("Getting admin session for: " + AdminServiceImpl.superUserDomain + "\\"
					+ AdminServiceImpl.superUserName + " pass: " + AdminServiceImpl.superUserPassword + " ... Done");
		}

		return userSession;
	}

	@Override
	public void deleteProfile(String loginName, String password, String profileId) throws ServerException {
		IDfSession adminSession = null;
		try {

			Logger.getLogger(this.getClass()).info("deleteProfile started for profileId=" + profileId);
			adminSession = AdminServiceImpl.getAdminSession();

			String object_name = "profile_" + profileId;
			IDfSysObject sysObj = (IDfSysObject) adminSession
					.getObjectByQualification("dm_document where object_name='" + object_name + "' and folder('" + configDmPath + "')");
			if (sysObj != null) {
				sysObj.destroyAllVersions();
				Logger.getLogger(this.getClass()).info("profile object on documentum deleted.");
			}

			for (DocType docType : doctypes.values()) {
				docType.profiles.remove(profileId);
			}
			profiles.remove(profileId);
			serializeDocTypes();
			serializeProfiles();

			// rename profile xml file to .deleted
			String fileName = configPath + "/" + object_name + ".xml";
			File profileFile = new File(fileName);
			File profileFileRenamed = new File(fileName + ".deleted");
			profileFile.renameTo(profileFileRenamed);

		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		} finally {
			if (adminSession != null)
				adminSession.getSessionManager().release(adminSession);
		}
	}

	public DcmtAttribute findAttribute(String docTypeName, String attName) throws Exception {
		DocType docType = doctypes.get(docTypeName);
		if (docType == null)
			throw new Exception("No such doctype: " + docTypeName);
		while (docType.attributes.get(attName) == null) {
			docType = doctypes.get(doctypes.get(docType.id).superName);
			if (docType == null) {
				Logger.getLogger(this.getClass()).warn("Searched all supertypes for attribute: " + attName + " and it doesn't exist on any super type");
				// throw new Exception("No such doctype: " +
				// doctypes.get(docTypeName).superName);
				return null;
			}
			Logger.getLogger(this.getClass()).info("Finding attribute: " + attName + " on " + docType.name);

		}
		return doctypes.get(docTypeName).attributes.get(attName);
	}

	public List<Profile> getProfilesForClassSign(String loginName, String password, String classSign, String wizardType) {
		// profiles for classign are gathered from newdoc wizard and
		// mob_classification_id attribute

		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("nashorn");

		if (classSign.contains("|"))
			classSign = classSign.split("\\|")[0];
		List<Profile> ret = new ArrayList<Profile>();
		for (Profile prof : profiles.values()) {
			for (AttributeRoleStateWizards arsw : prof.attributeRolesStatesWizards) {
				for (String wizardId : arsw.wizards) {
					if (wizardId.equals(wizardType)) {
						for (Attribute att : arsw.attributes) {
							if (att.dcmtAttName.equals("mob_classification_id") && !att.dqlValueListDefinition.equals("")) {
								Pattern p0 = Pattern.compile(".*'" + classSign + "'.*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
								Matcher m0 = p0.matcher(att.dqlValueListDefinition);
								if (m0.find()) {
									ret.add(prof);
								} else {
									Pattern p = Pattern.compile("\\((\\bcode.*)*\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
									Matcher m = p.matcher(att.dqlValueListDefinition);
									Logger.getLogger(this.getClass()).info("trying: " + prof.id + " " + att.dqlValueListDefinition);
									if (m.find()) {
										String codeLikeExpression = m.group(1);

										Pattern p1 = Pattern.compile("code like '(.+?)'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
										Matcher m1 = p1.matcher(codeLikeExpression);
										while (m1.find()) {
											codeLikeExpression = codeLikeExpression.replaceAll(m1.group(0), "code.matches('" + m1.group(1) + "')");
											m1 = p1.matcher(codeLikeExpression);
										}
										codeLikeExpression = codeLikeExpression.replaceAll("%", "*");
										codeLikeExpression = codeLikeExpression.replaceAll("=", "==");
										codeLikeExpression = codeLikeExpression.replaceAll("or", "||");
										codeLikeExpression = "code = '" + classSign + "';" + codeLikeExpression + ";";
										try {
											Logger.getLogger(this.getClass()).info("Evaluating: " + codeLikeExpression);
											Object result = engine.eval(codeLikeExpression);
											if ((boolean) result) {
												ret.add(prof);
											}
										} catch (ScriptException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										// String[] classSigns = m.group(1).split(",");
										// for (String cs : classSigns) {
										// if (cs.replaceAll("'", "").trim().equals(classSign))
										// ret.add(prof);
										// }
									}
								}
							}
						}
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Runs standard actions for existing object in docbase as defined in profile
	 * for this object. If all standard actions are runned succesfully - state of
	 * object is set to target state
	 * 
	 * @param persObject
	 *          ... object in documentum for which standarActions are to be
	 *          executed
	 * @param targetStateNo
	 *          ... target state
	 */
	public static void runStandardActions(IDfPersistentObject persObject, String stateId, IDfSession userSess) throws Exception {
		String msg = "Runing standard actions for: " + persObject.getId("r_object_id").toString() + " to stateId: " + stateId;
		WsServer.log(userSess.getLoginInfo().getUser(), msg);
		Logger.getLogger(AdminServiceImpl.class).info(msg);
		IDfCollection collection = null;
		IDfCollection collClassification = null;
		IDfCollection collNow = null;

		String saKind = "";
		try {

			IDfQuery query = new DfQuery();
			query.setDQL(
					"select profile_id, object_name, current_state_id from dm_dbo.T_DOCMAN_S where r_object_id='" + persObject.getObjectId().getId() + "'");

			IDfSysObject dfSysObject = ((IDfSysObject) persObject);

			collection = query.execute(userSess, IDfQuery.DF_READ_QUERY);

			if (collection.getState() == IDfCollection.DF_NO_MORE_ROWS_STATE) {
				Logger.getLogger("No profile found for persObject.getObjectId().getId() - trying search for barcode.");
				collection.close();
				query.setDQL(
						"select profile_id, object_name, current_state_id from dm_dbo.T_DOCMAN_S where object_name='" + dfSysObject.getObjectName() + "'");
				collection = query.execute(userSess, IDfQuery.DF_READ_QUERY);
			}
			if (collection.next()) {
				String profileId = collection.getString("profile_id");

				Profile prof = profiles.get(profileId);
				int targetStateNo = 0;
				for (State sta : prof.states) {
					if (sta.getId().equals(stateId)) {
						break;
					}
					targetStateNo++;
				}

				if (prof.states.get(targetStateNo) == null)
					throw new Exception("No such state: " + targetStateNo + " in profile: " + prof.name + " (" + prof.id + ")");
				Logger.getLogger(AdminServiceImpl.class).info("StateName: " + prof.states.get(targetStateNo).getId());

				for (StandardAction sa : prof.states.get(targetStateNo).standardActions) {
					saKind = sa.kind;
					Logger.getLogger(AdminServiceImpl.class).info("Above to execute standard action: " + saKind);

					if (sa.kind.equalsIgnoreCase(StandardAction.types.LINK_TO_FOLDER.type)) {
						String folder = evaluateFolderExpression(sa.parameter, persObject);
						IDfId dfId = AdminServiceImpl.getOrCreateFolder(folder);
						boolean alreadyLinked = false;
						for (int k = 0; k < dfSysObject.getFolderIdCount(); k++) {
							IDfFolder dfFold = (IDfFolder) userSess.getObject(dfSysObject.getFolderId(k));
							if (dfFold.getId("r_object_id").equals(dfId)) {
								alreadyLinked = true;
								Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " already linked to folder: " + folder);
								break;
							}
						}
						if (!alreadyLinked) {
							Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " linking to folder: " + folder + "...");
							dfSysObject.link(folder);
							Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " linking to folder: " + folder + "...Done.");
							dfSysObject.save();
						}
					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.MOVE_ALL_FOLDER_LINKS.type)) {
						String folder = evaluateFolderExpression(sa.parameter, persObject);

						ArrayList<String> foldersToUnlinkFrom = new ArrayList<String>();
						for (int i = 0; i < dfSysObject.getFolderIdCount(); i++) {
							IDfFolder dfFold = (IDfFolder) userSess.getObject(dfSysObject.getFolderId(i));
							String folderPath = dfFold.getAllRepeatingStrings("r_folder_path", "/");
							if (!folderPath.equalsIgnoreCase(folder))
								foldersToUnlinkFrom.add(folderPath);
						}

						IDfId dfId = AdminServiceImpl.getOrCreateFolder(folder);
						boolean alreadyLinked = false;
						for (int k = 0; k < dfSysObject.getFolderIdCount(); k++) {
							IDfFolder dfFold = (IDfFolder) userSess.getObject(dfSysObject.getFolderId(k));
							if (dfFold.getId("r_object_id").equals(dfId)) {
								alreadyLinked = true;
								Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " already linked in folder: " + folder);
								break;
							}
						}
						if (!alreadyLinked) {
							Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " linking to folder: " + folder + "...");
							dfSysObject.link(folder);
							Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " linking to folder: " + folder + "...Done.");
						}

						// unlink
						for (String folderToUnlink : foldersToUnlinkFrom) {
							dfSysObject.unlink(folderToUnlink);
							Logger.getLogger(AdminServiceImpl.class).info(sa.kind + " unlinked from folder: " + folderToUnlink);
						}
						dfSysObject.save();

					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.UNLINK_FROM_FOLDER.type)) {
						for (int i = 0; i < dfSysObject.getFolderIdCount(); i++) {
							IDfFolder dfFold = (IDfFolder) userSess.getObject(dfSysObject.getFolderId(i));
							String folderPath = dfFold.getAllRepeatingStrings("i_folder_path", "/");
							dfSysObject.unlink(folderPath);
						}
					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.REPLACE_VERSION_LABEL.type)) {
						boolean ok = false;
						ArrayList<String> allVersionLabels = new ArrayList<String>();
						for (int i = 0; i < dfSysObject.getVersionLabelCount(); i++) {
							allVersionLabels.add(dfSysObject.getVersionLabel(i));
							if (dfSysObject.getVersionLabel(i).equals("CURRENT")) {
								ok = true;
							}
						}
						Logger.getLogger(AdminServiceImpl.class).info("REPLACE_VERSION_LABEL is OK? " + ok);
						if (ok) {
							for (String ver : allVersionLabels) {
								if (!ver.equals("CURRENT") && !isNumeric(ver)) {
									dfSysObject.unmark(ver);
									Logger.getLogger(AdminServiceImpl.class).info("REPLACE_VERSION_LABEL unmarked: " + ver);
								}
							}
							dfSysObject.mark(sa.parameter);
							Logger.getLogger(AdminServiceImpl.class).info("REPLACE_VERSION_LABEL marked: " + sa.parameter);
							dfSysObject.save();
							Logger.getLogger(AdminServiceImpl.class).info("REPLACE_VERSION_LABEL saved.");
							// sysObj.checkin (false, "");
						} else {
							throw new Exception("Object is not current, new version exists. Check SHOW Versions.");
						}
					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.ADD_VERSION_LABEL.type)) {
						boolean ok = false;
						ArrayList<String> allVersionLabels = new ArrayList<String>();
						for (int i = 0; i < dfSysObject.getVersionLabelCount(); i++) {
							allVersionLabels.add(dfSysObject.getVersionLabel(i));
							if (dfSysObject.getVersionLabel(i).equals("CURRENT")) {
								ok = true;
							}
						}
						if (ok) {
							dfSysObject.mark(sa.parameter);
							dfSysObject.save();
						} else {
							throw new Exception("Object is not current, new version exists. Check SHOW Versions.");
						}
					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.APPLY_CAS_RETENTION.type)) {

						// What to check if RPS retention is not being propagated to
						// Centera?
						// https://knowledge.opentext.com/knowledge/cs.dll/kcs/kbarticle/view/KB8802964

						// https://knowledge.opentext.com/knowledge/cs.dll/kcs/kbarticle/view/KB8672522
						// setcontentattrs,c,l,text,0,,'description="mydocument",title="test
						// document?,retention_date=DATE(01/31/2008)'

						// IDfSysObjectRetention sysObjRet =
						// (IDfSysObjectRetention)dfSysObject;
						// IDfRetainer dfRetainer =
						// (IDfRetainer)userSess.getObjectByQualification("dm_retainer where
						// ");
						// sysObjRet.applyRetention(dfRetainer.getId("r_object_id"));

						// String fromDate = "06.06.2020 8:00:00";
						SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						SimpleDateFormat outputFormat2 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

						TimeZone tz = TimeZone.getTimeZone("GMT+2:00");
						GregorianCalendar cal = new GregorianCalendar(tz);

						String dqlNow = "SELECT Date(Today) as date_today , DATETOSTRING_LOCAL(Date(Today),'dd.MM.yyyy HH:mm:ss') as date_ddmmyyyy FROM dm_docbase_config";
						IDfQuery queryNow = new DfQuery(dqlNow);
						collNow = queryNow.execute(userSess, DfQuery.DF_READ_QUERY);
						if (collNow.next()) {
							// calculate retention in years
							Date archivalDate = collNow.getTime("date_today").getDate();
							archivalDate = new Date();

							try {
								// set it only if it is not already set
								if (dfSysObject.getTime("mob_archive_start_date").isNullDate()) {
									dfSysObject.setTime("mob_archive_start_date", new DfTime(archivalDate));
									Logger.getLogger(AdminServiceImpl.class).info("Set mob_archive_start_date to: " + outputFormat2.format(archivalDate));
								}

								archivalDate = dfSysObject.getTime("mob_archive_start_date").getDate();
							} catch (Exception ex) {
								Logger.getLogger(AdminServiceImpl.class).warn("Could not set mob_archive_start_date: " + ex.getMessage());
							}

							cal.setTime(archivalDate);

							// get classification retention years

							String classId = dfSysObject.getString("mob_classification_id");
							Logger.getLogger(AdminServiceImpl.class).info("Class sign from document: " + classId);

//@formatter:off
							String dqlClass = 
							"SELECT " + 
							"tc.\"id\", tc.\"classification_plan_id\", tc.\"code\", tc.\"name\", " + 
							"tc.\"short_name\", tc.\"retention_type_id\", rt.\"name\" as rt_name, rt.\"calculate_month\" as rt_month, tc.\"retention_start_id\", " + 
							"tc.\"version\" " + 
							"FROM dbo.T_CLASSIFICATION tc, dbo.T_CLASSIFICATION_PLAN tcp, dbo.T_RETENTION_TYPE rt " + 
							"WHERE" + 
							" rt.\"id\" = tc.\"retention_type_id\" AND " +
							" tc.\"code\" = '"+classId+"' AND " + 
							"tc.\"classification_plan_id\" = tcp.\"id\" AND " + 
							"tcp.\"name\" = 'KNTS' AND " + 
							"DATE(TODAY) >= tcp.\"valid_from\" AND (" + 
							"DATE(TODAY) <= tcp.\"valid_to\" OR " + 
							"tcp.\"valid_to\" = '' )";							
//@formatter:on

							Logger.getLogger(AdminServiceImpl.class).info("ClassSignDql:\n" + dqlClass);

							IDfQuery queryClassification = new DfQuery(dqlClass);
							collClassification = queryClassification.execute(userSess, DfQuery.DF_READ_QUERY);
							if (collClassification.next()) {
								String retentionName = collClassification.getString("rt_name");
								int retentionCalcMonth = collClassification.getInt("rt_month");

								String targetStore = calculateTargetStoreFromRetention(retentionName);

								String prevStorageType = dfSysObject.getStorageType();

								if (targetStore.equals(prevStorageType)) {
									Logger.getLogger(AdminServiceImpl.class).info("Already in proper storagetype: " + targetStore);
								} else {
									if (!retentionName.toLowerCase().equals("a") && !retentionName.toLowerCase().equals("p")) {
										if (AdminServiceImpl.retentionAddUnit.equals("day"))
											// we are on dev or test system
											cal.add(Calendar.DAY_OF_YEAR, 1);
										else if (AdminServiceImpl.retentionAddUnit.equals("month"))
											cal.add(Calendar.MONTH, retentionCalcMonth);
										else if (AdminServiceImpl.retentionAddUnit.equals("year"))
											cal.add(Calendar.YEAR, retentionCalcMonth);

										Date d = cal.getTime();
										String dateStr = outputFormat.format(d);

										// dmEMC Plugin: Setting retention period to 86398
										// 1 day retention = 24*60*60 seconds 86400 seconds

										IDfCollection coll = dfSysObject.getRenditions("full_format");
										while (coll.next()) {
											IDfFormat myformat = userSess.getFormat(coll.getString("full_format"));
											// String args = myformat.getName() +
											// ",0,,'description=\"mydocument\",title=\"test
											// document\",retention_date=DATE(" + dateStr + ")'";
											String args = myformat.getName() + ",0,,'barcode=\"" + dfSysObject.getObjectName() + "\",retention_date=DATE(" + dateStr + ")'";

											Logger.getLogger(AdminServiceImpl.class).info("setcontentattrs," + args);

											dfSysObject.apiExec("setcontentattrs", args);
											dfSysObject.save();

											// IDfCollection coll2 =
											// dfSysObject.getCollectionForContent(myformat.getName(),
											// 0);
											// while (coll2.next()) {
											// // userSess.getd
											// for(int j=0;j<coll2.getAttrCount();j++)
											// {
											// Logger.getLogger(AdminServiceImpl.class).info(coll2.getAttr(j).getName());
											// }
											// IDfContent content = (IDfContent)
											// userSess.getObject(new
											// DfId(coll2.getString("r_object_id")));
											// }
											IDfTime retentionDate = dfSysObject.getTimeContentAttr("retention_date", dfSysObject.getFormat().getName(), 0, null);
											Logger.getLogger(AdminServiceImpl.class)
													.info("dfsysobject id: " + dfSysObject.getId("r_object_id").toString() + " format: " + dfSysObject.getFormat().getName()
															+ "dmr_content id: " + dfSysObject.getContentsId().getId() + " retention_date = "
															+ outputFormat2.format(retentionDate.getDate()));
										}
										dfSysObject.setTime("a_retention_date", new DfTime(d));

										dfSysObject.save();

										dfSysObject.fetch("dm_document");
									}

									Logger.getLogger(AdminServiceImpl.class).info("targetStore: " + targetStore);
									dfSysObject.setStorageType(targetStore);
									dfSysObject.save();

								}
							} else {
								String errMsg = sa.kind + ": Class sign '" + classId + "' is not valid.";
								Logger.getLogger(AdminServiceImpl.class).error(errMsg);
								throw new ServerException(errMsg);
							}
						} else {
							throw new ServerException("Unable to get documentum server time.");
						}
					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.SET_ATTRIBUTE.type)) {
						String attributeName = sa.parameter.split(",")[0];
						String value = sa.parameter.split(",")[1];
						IDfValue val;
						if (value.equals("$now"))
							val = new DfValue(new DfTime(new Date()));
						else
							val = new DfValue(value);

						dfSysObject.setValue(attributeName, val);
						dfSysObject.save();
					} else if (sa.kind.equalsIgnoreCase(StandardAction.types.CLEAR_ATTRIBUTES.type)) {
						String[] attributeNames = sa.parameter.split(",");
						for (String attName : attributeNames) {
							for (int i = 0; i < dfSysObject.getAttrCount(); i++) {
								if (dfSysObject.getAttr(i).getName().equals(attName)) {
									/*
									 * 0 Boolean 1 Integer 2 String 3 ID 4 Time, or date 5 Double
									 * 6 Undefined
									 */
									if (dfSysObject.getAttr(i).getDataType() == 0)
										dfSysObject.setBoolean(attName, false);
									else if (dfSysObject.getAttr(i).getDataType() == 1)
										dfSysObject.setInt(attName, 0);
									else if (dfSysObject.getAttr(i).getDataType() == 2)
										dfSysObject.setString(attName, null);
									else if (dfSysObject.getAttr(i).getDataType() == 3)
										dfSysObject.setId(attName, DfId.DF_NULLID);
									else if (dfSysObject.getAttr(i).getDataType() == 4)
										dfSysObject.setTime(attName, DfTime.DF_NULLDATE);
									else if (dfSysObject.getAttr(i).getDataType() == 5)
										dfSysObject.setDouble(attName, 0);
									else
										Logger.getLogger(AdminServiceImpl.class).warn("Clear attribute: Unknown type.");
								}
							}
						}
						dfSysObject.save();
					}
				}
			}
		} catch (Exception ex) {
			Logger.getLogger(AdminServiceImpl.class).error(saKind + ": Error executing standard actions: " + ex.getMessage());
			throw ex;
		} finally {
			if (collection != null)
				collection.close();
			if (collClassification != null)
				collClassification.close();
			if (collNow != null)
				collNow.close();

		}
	}

	private static String calculateTargetStoreFromRetention(String retentionName) {
		String targetStore = null;
		if (retentionName.toLowerCase().equals("a"))
			targetStore = "ecsstore_dis_a";
		else if (retentionName.toLowerCase().equals("t"))
			targetStore = "ecsstore_dis_t";
		else {
			targetStore = "ecsstore_dis";
		}
		return targetStore;
	}

	/**
	 * Evaluates folder expression for example
	 * "/mob/ppm/em/sp/mc/subscriber/[year(mob_issue_date)]" evaluates to
	 * "/mob/ppm/em/sp/mc/subscriber/2019"
	 * 
	 * @param folderExpression
	 * @return
	 * @throws DfException
	 */
	private static String evaluateFolderExpression(String folderExpression, IDfPersistentObject persObject) throws Exception {
		// TODO Auto-generated method stub
		String ret = folderExpression;

		// Pattern p = Pattern.compile("\\[(.*?)\\]", Pattern.CASE_INSENSITIVE);

		Pattern p = Pattern.compile("\\[(.*?)\\]", Pattern.CASE_INSENSITIVE);

		Matcher m = p.matcher(folderExpression);
		while (m.find()) {
			for (int i = 0; i < m.groupCount(); i++) {
				String attName = m.group(i).replaceAll("\\[", "");
				attName = attName.replaceAll("\\]", "");
				if (attName.startsWith("year(") || attName.startsWith("month(")) {
					Pattern p1 = Pattern.compile("(year|month)\\((.*?)\\)", Pattern.CASE_INSENSITIVE);
					Matcher m1 = p1.matcher(attName);
					if (m1.find()) {
						String attName2 = m1.group(2);
						Date date = persObject.getTime(attName2).getDate();
						GregorianCalendar cal = new GregorianCalendar(Locale.GERMANY);
						cal.setTime(date);
						int attValue = 0;
						if (m1.group(1).equals("year"))
							attValue = cal.get(Calendar.YEAR);
						else if (m1.group(1).equals("month"))
							attValue = cal.get(Calendar.MONTH);
						else {
							throw new Exception("unknown function: " + attName);
						}

						String toReplace = "\\[" + attName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)") + "\\]";
						folderExpression = folderExpression.replaceAll(toReplace, String.valueOf(attValue));
					} else {
						String attValue = persObject.getValue(attName).toString();
						String toReplace = "\\[" + attName + "\\]";
						folderExpression = folderExpression.replaceAll(toReplace, attValue);
					}
				} else {
					String attValue = persObject.getValue(attName).toString();
					String toReplace = "\\[" + attName + "\\]";
					folderExpression = folderExpression.replaceAll(toReplace, attValue);
				}
			}
		}

		return folderExpression;
	}

	private static BarcodeHandlerImpl getBarcodeHandlerImpl() {
		if (barcodeHandler == null)
			barcodeHandler = new BarcodeHandlerImpl();
		return barcodeHandler;

	}

	public static String[] getBarcode(int iTypePar, String sCapturePar, String sCompanyPar, String sLocationPar, GregorianCalendar dateTimePar,
			int nQuantityPar, String sourceSystem) throws Exception {
		// TODO Auto-generated method stub
		if (dateTimePar == null) {
			throw new Exception("Takšen datum ne obstaja oziroma je null.");
		}

		Logger.getLogger(AdminServiceImpl.class).info("getBarcode called for sourceSystem: " + sourceSystem + "... Using barcode db: "
				+ BarcodeHandlerImpl.barcodeSqlServerDbName + " on sql server host: " + BarcodeHandlerImpl.barcodeSqlServerHost);
		String[] ret = null;
		Calendar gc = GregorianCalendar.getInstance();
		// Logger.getLogger(this.getClass()).info("Default timezone: " +
		// TimeZone.getDefault());
		gc.setTimeZone(TimeZone.getTimeZone("CET"));
		gc.setTime(dateTimePar.getTime());
		Date myDate = gc.getTime();

		try {
			ret = getBarcodeHandlerImpl().getBarcode(iTypePar, sCapturePar, sCompanyPar, sLocationPar, myDate, nQuantityPar, sourceSystem);
		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new Exception("Napaka pri pridobivanju barkod dokumenta.", ex);
		}
		Logger.getLogger(AdminServiceImpl.class).info("getBarcode called for sourceSystem: " + sourceSystem + "...FINISHED.");
		return ret;
	}

	public HashMap<String, si.telekom.dis.shared.Attribute> getWizardAttributes(Profile prof, String kindWizard) {
		HashMap<String, si.telekom.dis.shared.Attribute> attributes = new HashMap<String, si.telekom.dis.shared.Attribute>();
		for (AttributeRoleStateWizards arsw : prof.attributeRolesStatesWizards) {
			if (arsw.wizards.contains(kindWizard)) {
				for (si.telekom.dis.shared.Attribute att : arsw.attributes) {
					attributes.put(att.dcmtAttName, att);
				}
			}
		}
		return attributes;
	}

	@Override
	public void syncDoctypes(String loginName, String password) throws ServerException {
		// TODO Auto-generated method stub
		WsServer.log(loginName, "SyncDoctypes & profiles start.");
		syncDocTypes(loginName);
		syncProfiles(loginName);
		WsServer.log(loginName, "SyncDoctypes & profiles end.");
	}

	@Override
	public void registerTable(String loginName, String password, String tableName) throws ServerException {
		IDfQuery query = new DfQuery();

		IDfSession dfSuperUserSession = null;
		IDfCollection collection = null;
		try {
			dfSuperUserSession = getAdminSession();

			Logger.getLogger(AdminServiceImpl.class).info("registerTable start - user: " + loginName + " table " + tableName);

			IDfId objId = dfSuperUserSession.getIdByQualification("dm_registered where object_name='" + tableName + "'");
			if (!objId.isNull()) {
				try {
					String unregisterDql = "UNREGISTER dm_dbo." + tableName;
					Logger.getLogger(AdminServiceImpl.class).info("\t" + unregisterDql);
					query.setDQL(unregisterDql);
					collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);
					collection.close();
				} catch (Exception ex) {
					Logger.getLogger(AdminServiceImpl.class).warn("\tunable to UNregister " + tableName);
				}
			}

			String fields = (String) getRegTableFields(tableName)[1];

			/*
			 * + "r_object_id STRING(16), " + "profile_id STRING(50)," +
			 * "current_state_id STRING(50)"
			 */

//@formatter:off
			String registerDql = "REGISTER TABLE dm_dbo." + tableName + " (" + fields + ")";
//@formatter:on
			Logger.getLogger(AdminServiceImpl.class).info("\t" + registerDql);
			query.setDQL(registerDql);
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);
			collection.close();

//@formatter:off
			String permissionDql = "UPDATE dm_registered OBJECT " + "  set owner_table_permit=15, "
					+ "  set group_permit=7," + "  set world_permit=7, " + "  set world_table_permit=15, "
					+ "  set group_table_permit=15 " + "where object_name='" + tableName + "'";
//@formatter:on

			query.setDQL(permissionDql);
			Logger.getLogger(AdminServiceImpl.class).info("\t" + permissionDql);
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_EXEC_QUERY);

			Logger.getLogger(AdminServiceImpl.class).info("Setting permission on " + tableName + " done.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (collection != null)
					collection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private Object[] getRegTableFields(String tableName) throws Exception {

		ArrayList<String> fields = new ArrayList<String>();

		// @formatter:off
		String sqlQuery = "select schema_name(tab.schema_id) as schema_name, " + "tab.name as table_name, "
				+ "col.column_id, " + "col.name as column_name, " + "t.name as data_type, " + "col.max_length, "
				+ "col.precision " + "from sys.tables as tab " + "inner join sys.columns as col "
				+ "    on tab.object_id = col.object_id " + "left join sys.types as t "
				+ "on col.user_type_id = t.user_type_id " + "where tab.name='" + tableName + "' " +
//"order by schema_name, " +
//    "table_name, " +
//    "column_id union " +

				" union " +

				"select schema_name(tab.schema_id) as schema_name, " + "tab.name as table_name, " + "col.column_id, "
				+ "col.name as column_name, " + "t.name as data_type, " + "col.max_length, " + "col.precision "
				+ "from sys.views as tab " + "inner join sys.columns as col " + "    on tab.object_id = col.object_id "
				+ "left join sys.types as t " + "on col.user_type_id = t.user_type_id " + "where tab.name='" + tableName
				+ "' ";
//	"order by schema_name, " +
//	"table_name, " +
//	"column_id union ";

//@formatter:on
		Connection conn = barcodeHandler.getConnection();
		PreparedStatement prep = (PreparedStatement) conn.prepareStatement(sqlQuery);

		Logger.getLogger(this.getClass()).info(sqlQuery);

		ResultSet rs = prep.executeQuery();
		Logger.getLogger(this.getClass()).info("sqlQuery done.");

		String fields_str_def = "";
		String fields_str = "";
		while (rs.next()) {
			String column = "\"" + rs.getString("column_name") + "\"";
			String data_type = rs.getString("data_type");
			String length = rs.getString("max_length");
			if (data_type.startsWith("nchar") && length.equals("32"))
				fields_str_def = fields_str_def + column + " INT, \n";
			else if (data_type.startsWith("nvarchar"))
				fields_str_def = fields_str_def + column + " STRING(" + length + "), \n";
			else if (data_type.startsWith("varchar"))
				fields_str_def = fields_str_def + column + " STRING(" + length + "), \n";
			else if (data_type.equals("bigint"))
				fields_str_def = fields_str_def + column + " INT, \n";
			else if (data_type.equals("int"))
				fields_str_def = fields_str_def + column + " INT, \n";
			else if (data_type.equals("datetime"))
				fields_str_def = fields_str_def + column + " TIME, \n";
			else
				Logger.getLogger(this.getClass()).warn("unknown field: " + data_type);

			fields.add(column);
			fields_str = fields_str + column + ", ";
		}
		rs.close();
		conn.close();

		if (fields_str_def.length() > 0)
			fields_str_def = fields_str_def.substring(0, fields_str_def.length() - 3);

		if (fields_str.length() > 0)
			fields_str = fields_str.substring(0, fields_str.length() - 2);

		Object[] ret = { fields, fields_str_def, fields_str };
		return ret;

	}

	@Override
	public List<List<String>> getRegTableValues(String loginName, String loginPass, String regTableId, int start, int length) throws ServerException {
		List<List<String>> ret = new ArrayList<List<String>>();
		IDfSession userSession = null;
		IDfCollection coll = null;
		Logger.getLogger(this.getClass()).info(String.format("[%s] getRegTableValues started for %s", loginName, regTableId));
		try {
			if (loginName == null)
				throw new Exception("LoginName should not be null");
			if (loginPass == null)
				throw new Exception("Password should not be null");

			userSession = AdminServiceImpl.getSession(loginName, loginPass);

			String fields = (String) getRegTableFields(regTableId)[2];

			IDfQuery queryRead = new DfQuery("select " + fields + " from dm_dbo." + regTableId);
			Logger.getLogger(this.getClass()).info(String.format("[%s] dql for regTable: %s start...", loginName, queryRead.getDQL()));
			coll = queryRead.execute(userSession, IDfQuery.DF_EXEC_QUERY);
			Logger.getLogger(this.getClass()).info(String.format("[%s] dql done..", loginName, queryRead.getDQL()));
			long j = 0;
			Logger.getLogger(this.getClass()).info(String.format("[%s] getRegTableValues for regTable: %s start...", loginName, regTableId));
			while (coll.next()) {
				if (j >= start && j < (start + length)) {
					ArrayList<String> line = new ArrayList<String>();
					for (int i = 0; i < coll.getAttrCount(); i++) {
						line.add(coll.getValueAt(i).toString());
					}
					ret.add(line);
				}
				j++;
			}
			coll.close();

			Logger.getLogger(this.getClass()).info(String.format("[%s] getRegTableValues for regTable: %s end.", loginName, regTableId));

		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (coll != null)
					coll.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null)
					userSession.getSessionManager().release(userSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public List<String> getRegTables(String loginName, String loginPass) throws ServerException {
		Logger.getLogger(AdminServiceImpl.class).info("getRegTables start - user: " + loginName);
		List<String> ret = new ArrayList<String>();
		IDfQuery query = new DfQuery();
		IDfSession dfSuperUserSession = null;
		IDfCollection collection = null;
		try {
			dfSuperUserSession = getAdminSession();
			query.setDQL(
					"select object_name from dm_registered where object_name like '%T_DOC%' or object_name like 'T_DCTME_%' or object_name = 'mob_sppserviceaction' or object_name like 'T_CLASSIFICATION%'");
			collection = query.execute(dfSuperUserSession, IDfQuery.DF_READ_QUERY);
			while (collection.next()) {
				Logger.getLogger(this.getClass()).info(collection.getString("object_name"));
				ret.add(collection.getString("object_name"));
			}
			collection.close();
		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null)
					collection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (dfSuperUserSession != null)
					dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public void updateRegTableRow(String loginName, String loginPass, String regTableId, String value, int colIndex, String pkValue)
			throws ServerException {
		Logger.getLogger(AdminServiceImpl.class).info("updateRegTableRow start - user: " + loginName + " regtable: " + regTableId);
		List<String> ret = new ArrayList<String>();
		IDfQuery query = new DfQuery();
		IDfSession userSession = null;
		IDfCollection collection = null;

		try {
			List<String> fields = (List<String>) getRegTableFields(regTableId)[0];

			userSession = AdminServiceImpl.getSession(loginName, loginPass);
			String dql = "update dm_dbo." + regTableId + " set " + fields.get(colIndex) + "='" + value + "' where " + fields.get(0) + "='" + pkValue + "'";
			query.setDQL(dql);
			collection = query.execute(userSession, IDfQuery.DF_EXEC_QUERY);
			collection.close();
		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null)
					collection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null)
					userSession.getSessionManager().release(userSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static int getStateIndex(Profile prof, String stateId) {
		int ret = -1;
		int i = 0;
		for (State state : prof.states) {
			if (state.getId().equals(stateId)) {
				ret = i;
				break;
			}

			i++;
		}

		return ret;
	}

	public static boolean isNumeric(String strNum) {
		return strNum.matches("-?\\d+(\\.\\d+)?");
	}

	@Override
	public List<String> getRegTableFieldNames(String loginName, String loginPass, String regTableName) throws ServerException {

		try {
			return (List<String>) getRegTableFields(regTableName)[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServerException(e);
		}

	}

	public static AttributeRoleStateWizards getAttributesForStateAndRole(Profile prof, String stateId, String roleId) {
		for (AttributeRoleStateWizards attrsw : prof.attributeRolesStatesWizards) {
			List<String> rolesInState = attrsw.stateRole.get(stateId);
			if (rolesInState.contains(roleId)) {
				return attrsw;
			}
		}
		return null;
	}

	public static Attribute getAttFromAttributeRoleStateWizards(AttributeRoleStateWizards arsw, String dctmAttName) {
		for (Attribute a : arsw.attributes) {
			if (a.dcmtAttName.equals(dctmAttName)) {
				return a;
			}
		}
		return null;
	}

	@Override
	public List<MyParametrizedQuery> getLazySearchQueries(String loginName, String dcmtUserName, String loginPass) throws ServerException {
		Logger.getLogger(AdminServiceImpl.class).info("getLazySearchQueries requested by " + loginName);
		return getSearchQueries(loginName, dcmtUserName, loginPass, true);
	}

	@Override
	public List<MyParametrizedQuery> getSearchQueries(String loginName, String dcmtUserName, String loginPass) throws ServerException {
		return getSearchQueries(loginName, dcmtUserName, loginPass, false);
	}

	public List<MyParametrizedQuery> getSearchQueries(String loginName, String dcmtUserName, String loginPass, boolean lazy) throws ServerException {
		Logger.getLogger(AdminServiceImpl.class).info("getSearchQueries requested by " + loginName);

		ArrayList<MyParametrizedQuery> queries = new ArrayList<MyParametrizedQuery>();

		try {
			XPathFactory xpathFac = XPathFactory.newInstance();
			XPath xpath = xpathFac.newXPath();
			// xpath.setNamespaceContext();

			// XPathExpression expr = xpath.compile("/config/docbase[@name='" +
			// docBase + "']/queries/query");
			XPathExpression expr = xpath.compile("/config/docbase[1]/queries/query");
			// NodeList nl = (NodeList) expr.evaluate( new InputSource(new
			// StringReader(getDocConfig().toString())),
			// XPathConstants.NODESET);
			NodeList nl = (NodeList) expr.evaluate(getDocConfig(), XPathConstants.NODESET);
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				String searchName = el.getAttribute("name");

				Logger.getLogger(this.getClass()).info("Shoud user get " + searchName);

				boolean userShouldGetSearch = false;
				XPathExpression expr2 = xpath.compile(".//userGroup");
				NodeList groupList = (NodeList) expr2.evaluate(el, XPathConstants.NODESET);
				for (int j = 0; j < groupList.getLength(); j++) {
					Element el1 = (Element) groupList.item(j);
					String groupName = el1.getAttribute("id");

					userShouldGetSearch = groupName.equalsIgnoreCase("dm_world");
					if (!userShouldGetSearch) {
						if (groupName.equals(dcmtUserName)) {
							userShouldGetSearch = true;
						} else {
							userShouldGetSearch = isMember(loginName, groupName);
						}
						if (userShouldGetSearch)
							break;
					}
				}

				if (userShouldGetSearch || LoginServiceImpl.admins.contains(loginName)) { // userShouldGetSearch
					WsServer.log(loginName, new String(el.getAttribute("name").getBytes(), "UTF-8"));
					MyParametrizedQuery query = getParametrizedQuery(el, xpathFac, lazy);
					queries.add(query);
					Logger.getLogger(this.getClass()).info("\tyes.");

				}

			}

		} catch (Throwable ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());

			WsServer.log(loginName, ex.getMessage());
			return queries;
			// throw new ServerException(ex.getMessage());
		}
		return queries;
	}

	private MyParametrizedQuery getParametrizedQuery(Element el, XPathFactory xpathFac, boolean lazy)
			throws XPathExpressionException, UnsupportedEncodingException {
		String dqlQuery = getFirstLevelTextContent((Node) el);
		String type = getObjecTypeFromDql(dqlQuery);
		MyParametrizedQuery ret = new MyParametrizedQuery(new String(el.getAttribute("name").getBytes(), "UTF-8"), dqlQuery);

		Logger.getLogger(AdminServiceImpl.class).info("lazy queryName: " + el.getAttribute("name"));

		if (lazy)
			return ret;

		// if (el.getAttribute("name").startsWith("ePredloge po naz")) {
		// System.out.println("oop");
		// }

		XPath xpath = xpathFac.newXPath();
		XPathExpression expr3 = xpath.compile(".//userGroup");
		NodeList groupList3 = (NodeList) expr3.evaluate(el, XPathConstants.NODESET);
		for (int k = 0; k < groupList3.getLength(); k++) {
			Element elGroup = (Element) groupList3.item(k);
			UserGroup ug = new UserGroup(elGroup.getAttribute("id"), elGroup.getAttribute("name"));
			ret.usersGroups.add(ug);
		}

		expr3 = xpath.compile(".//orderBy");
		NodeList orderbyList3 = (NodeList) expr3.evaluate(el, XPathConstants.NODESET);
		for (int k = 0; k < orderbyList3.getLength(); k++) {
			Element orderBy = (Element) orderbyList3.item(k);
			String name = orderBy.getAttribute("name");
			if (!name.equals("")) {
				ret.orderBys.add(name);
				ret.orderByDirections.add(orderBy.getAttribute("direction"));
			}
		}

		expr3 = xpath.compile(".//filterClass");
		NodeList filterByList = (NodeList) expr3.evaluate(el, XPathConstants.NODESET);
		Element filterBy = (Element) filterByList.item(0);
		if (filterBy != null)
			ret.filterClass = filterBy.getTextContent();

		String patternToCompile = "[<][0-9].*?[>]";
		Pattern p = Pattern.compile(patternToCompile, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(dqlQuery);
		while (m.find()) {
			if (!ret.arguments.contains(m.group(0))) {
				String partDql = dqlQuery.substring(0, dqlQuery.indexOf(m.group(0)) + m.group(0).length());
				if (doctypes.get(type) == null) {
					Logger.getLogger(this.getClass()).error("no such document type: " + type);
				}
				if (doctypes.get(type) != null) {
					for (String attName : doctypes.get(type).attributes.keySet()) {
						// try to parse label
						String escapedGroup0 = m.group(0).replaceAll("\\%", "[%]");
						String toCompile = ".*" + attName + " \\((.*?)\\)(.*)" + escapedGroup0;

						// Logger.getLogger(this.getClass()).info("trying: " + toCompile);

						Pattern p1 = Pattern.compile(toCompile, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
						Matcher m1 = p1.matcher(dqlQuery);

						if (m1.find()) {
							int poz1 = partDql.lastIndexOf(attName);
							String partOfDql = partDql.substring(poz1);
							String label = "";
							if (m1.group(1) != null)
								label = m1.group(1);

							// dqlQuery = dqlQuery.replaceAll("\\(" + label + "\\)", "");
							DcmtAttribute dcmtAtt = doctypes.get(type).attributes.get(attName);
							Attribute a = new Attribute();
							a.label = (label.equals("") ? attName : label);
							a.dcmtAttName = dcmtAtt.attr_name;
							if (dcmtAtt.domain_type.equals("0")) {
								a.setType("checkBox");
							} else if (dcmtAtt.domain_type.equals("1")) {
								a.setType("textbox");
							} else if (dcmtAtt.domain_type.equals("2")) {
								a.setType("textbox");
							} else if (dcmtAtt.domain_type.equals("3")) {
								a.setType("textbox");
							} else if (dcmtAtt.domain_type.equals("4")) {
								a.setType("datetime");
							}
							ret.formAttributes.add(a);
							ret.dqlParts.add(partOfDql);
							ret.labels.add("\\(" + label + "\\)");
							ret.arguments.add(m.group(0));
							break;
						}
					}
				}
			}
		}

		return ret;
	}

	public Document getDocConfig() throws Throwable {
		if (docConfig == null) {
			configPathFileName = WebappContext.getServletContext().getRealPath("/WEB-INF/classes/config.xml");

			InputStream inputStream = new FileInputStream(configPathFileName);
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");

			DocumentBuilderFactory factory;
			DocumentBuilder builder;

			factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true); // never forget this!
			builder = factory.newDocumentBuilder();

			docConfig = builder.parse(is);
			reader.close();
		}
		return docConfig;
	}

	public static String getFirstLevelTextContent(Node node) {
		NodeList list = node.getChildNodes();
		StringBuilder textContent = new StringBuilder();
		for (int i = 0; i < list.getLength(); ++i) {
			Node child = list.item(i);
			if (child.getNodeType() == Node.TEXT_NODE)
				textContent.append(child.getTextContent());
		}
		return textContent.toString();
	}

	boolean isMember(String forUserOrGroup, String groupName) throws Throwable {
		String groupDn = "CN=" + groupName + ",OU=FIM-Managed Groups,DC=ts,DC=telekom,DC=si";
		String ldapQuery = "(&(memberOf:1.2.840.113556.1.4.1941:={0})(objectCategory=person)(objectClass=user)(sAMAccountName={1}))";
		ldapQuery = ldapQuery.replaceAll("\\{0\\}", groupDn);
		ldapQuery = ldapQuery.replaceAll("\\{1\\}", forUserOrGroup);

		Logger.getLogger(this.getClass()).debug("Member ldap query: " + ldapQuery);

		boolean isMember = LoginServiceImpl.queryLdap(ldapQuery) != null ? true : false;

		return isMember;
	}

	public String getObjecTypeFromDql(String dql) {
		// TODO Auto-generated method stub
		String type = null;

		dql = dql.replaceAll("\n", " ");

		dql = dql.replaceAll("\t", " ");
		dql = dql.replaceAll("  ", " ");

		String[] words = dql.split(" ");

		if (dql.trim().toLowerCase().startsWith("select")) {

			int i = 0;
			for (String string : words) {
				if (string.toLowerCase().equals("from")) {
					break;
				}
				i++;
			}

			for (int j = i + 1; j < words.length; j++) {
				if (!words[j].trim().equals("")) {
					type = words[j];
					break;
				}
			}

		} else if (dql.trim().toLowerCase().startsWith("update")) {
			type = words[1].toLowerCase();
		}

		if (type.endsWith("(all)")) {
			type = type.substring(0, type.indexOf("(all"));
		}
		if (type.endsWith("(ALL)")) {
			type = type.substring(0, type.indexOf("(ALL"));
		}

		return type;
	}

	@Override
	public void editParametrizedQuery(String loginName, String loginPass, String oldName, String newName, String newDql, List<String> groups,
			List<String> orderBys, List<String> orderBydirections, String filterClass) throws ServerException {

		Logger.getLogger(this.getClass()).info("Saving search: " + oldName);
		ArrayList<MyParametrizedQuery> queries = new ArrayList<MyParametrizedQuery>();

		try {
			XPathFactory xpathFac = XPathFactory.newInstance();
			XPath xpath = xpathFac.newXPath();
			// xpath.setNamespaceContext();

			// XPathExpression expr = xpath.compile("/config/docbase[@name='" +
			// AdminServiceImpl. docBase + "']/queries/query");
			XPathExpression expr = xpath.compile("/config/docbase[1]/queries/query[@name='" + oldName + "']");
			// NodeList nl = (NodeList) expr.evaluate( new InputSource(new
			// StringReader(getDocConfig().toString())),
			// XPathConstants.NODESET);
			NodeList nl = (NodeList) expr.evaluate(getDocConfig(), XPathConstants.NODESET);
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);
				if (el != null) {
					Logger.getLogger(this.getClass()).info("User " + loginName + " saving search " + oldName + " with newName: " + newName);
					el.setTextContent(newDql);
					el.setAttribute("name", newName);
					Element elGroups = getDocConfig().createElement("userGroups");
					el.appendChild(elGroups);
					for (String group : groups) {
						String groupId = "";
						if (group.split("\\|").length > 1)
							groupId = group.split("\\|")[0];
						else
							groupId = group;

						String groupName = "";
						if (group.split("\\|").length > 1)
							groupName = group.split("\\|")[1];
						else
							groupName = group;

						Element elGroup = getDocConfig().createElement("userGroup");
						elGroup.setAttribute("id", groupId);
						elGroup.setAttribute("name", groupName);
						elGroups.appendChild(elGroup);
					}
					Logger.getLogger(this.getClass()).info("User " + loginName + " saving search " + oldName + " usersGroups.");

					Element elOrderBys = getDocConfig().createElement("orderBys");
					el.appendChild(elOrderBys);
					int j = 0;
					for (String oBy : orderBys) {
						Element elOrderBy = getDocConfig().createElement("orderBy");
						String str = StringUtils.stripEnd(oBy, null);
						str = StringUtils.stripStart(str, null);
						elOrderBy.setAttribute("name", str);
						elOrderBy.setAttribute("direction", orderBydirections.get(j));
						elOrderBys.appendChild(elOrderBy);
						j++;
					}
					Logger.getLogger(this.getClass()).info("User " + loginName + " saving search " + oldName + " orderBys.");

					Element elFilterClass = getDocConfig().createElement("filterClass");
					elFilterClass.setTextContent(filterClass);
					el.appendChild(elFilterClass);
					Logger.getLogger(this.getClass()).info("User " + loginName + " saving search " + oldName + " filterClass.");

					Transformer tr = TransformerFactory.newInstance().newTransformer();
					tr.setOutputProperty(OutputKeys.INDENT, "yes");
					tr.setOutputProperty(OutputKeys.METHOD, "xml");
					tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
					// tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
					tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

					// send DOM to file
					tr.transform(new DOMSource(docConfig), new StreamResult(new FileOutputStream(configPathFileName)));
					Logger.getLogger(this.getClass()).info("User " + loginName + " saved search with newName:" + newName + " to " + configPathFileName);

					// check if it is localhost save it to development environment
					final String ip = getThreadLocalRequest().getRemoteHost();
					InetAddress addr = InetAddress.getByName(ip);
					String hostName = addr.getHostName();
					if (hostName.equals("localhost") || hostName.equals("activation.cloud.techsmith.com")) {
						File devConfig;
						if (SystemUtils.IS_OS_LINUX) {
							devConfig = new File("/home/klemen/git/Dis/Dis-server/src/main/resources/config.xml");
						} else {
							devConfig = new File("c:\\git\\Dis\\Dis-server\\src\\main\\resources\\config.xml");
						}
						FileUtils.copyFile(new File(configPathFileName), devConfig);
						Logger.getLogger(this.getClass()).info("Saved search to dev config file: " + devConfig.getAbsolutePath());
					}

				}
			}
		} catch (Throwable ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex.getMessage());
		}
	}

	@Override
	public MyParametrizedQuery getParametrizedQueryByName(String loginName, String loginPass, String name) throws ServerException {
		// TODO Auto-generated method stub
		MyParametrizedQuery ret = null;

		try {
			XPathFactory xpathFac = XPathFactory.newInstance();
			XPath xpath = xpathFac.newXPath();
			// xpath.setNamespaceContext();

			// XPathExpression expr = xpath.compile("/config/docbase[@name='" +
			// docBase + "']/queries/query");
			XPathExpression expr = xpath.compile("/config/docbase/queries/query");
			// NodeList nl = (NodeList) expr.evaluate( new InputSource(new
			// StringReader(getDocConfig().toString())),
			// XPathConstants.NODESET);
			NodeList nl = (NodeList) expr.evaluate(getDocConfig(), XPathConstants.NODESET);
			for (int i = 0; i < nl.getLength(); i++) {
				Element el = (Element) nl.item(i);

				if (el.getAttribute("name").equals(name)) {
					ret = getParametrizedQuery(el, xpathFac, false);
				}

			}

		} catch (Throwable ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex.getMessage());
		}
		return ret;
	}

	public void releaseSession(IDfSession userSession) {
		long t1 = System.currentTimeMillis();
		Logger.getLogger(this.getClass()).info("releasing session...");
		try {
			String user = userSession.getLoginInfo().getUser();
			userSession.getSessionManager().release(userSession);
			long t2 = System.currentTimeMillis();
			String durationStr = String.format(Locale.ROOT, "%.3fs", (t2 - t1) / 1000.0);
			String msg = "Release session done in: " + durationStr;
			WsServer.log(user, msg);
			Logger.getLogger(this.getClass()).info(msg);
		} catch (DfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public DocType getDocType(String loginName, String password, String doctypeName) throws ServerException {
		return doctypes.get(doctypeName);
	}

	@Override
	public DocType getDocTypeFromDql(String loginName, String loginPass, String dql) throws ServerException {
		// TODO Auto-generated method stub
		String type = getObjecTypeFromDql(dql);
		return doctypes.get(type);
	}

	public List<List<String>> getColIdsForTemplate(int templateId, int rowNo, int pageSize) throws ServerException {
		Logger.getLogger(this.getClass()).info(String.format("getColIdsForTemplate started for templateId: %s", templateId));
		List<List<String>> ret = new ArrayList<List<String>>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "select col_id, col_name from mobile.t_subscription_inet_col where type_id = " + templateId + " order by col_id";

			String pagedSql = "";
			if (pageSize != -1) {
				int pageNumber = rowNo / pageSize;

				pageNumber = pageNumber + 1;

				pagedSql =
// @formatter:off
			"SELECT * FROM " +
			"( " +
			"   SELECT a.*, rownum r__ " +
			"    FROM " +
			"    ( sql ) a " +
			"    WHERE rownum < ((pageNumber * pageSize) + 1 ) " +
			") " +
			"WHERE r__ >= (((pageNumber-1) * pageSize) + 1)";
// @formatter:on

				pagedSql = pagedSql.replace("sql", sql);
				pagedSql = pagedSql.replace("pageNumber", String.valueOf(pageNumber));
				pagedSql = pagedSql.replace("pageSize", String.valueOf(pageSize));
			} else {
				pagedSql = sql;
			}

			Logger.getLogger(this.getClass()).info(String.format(" sql for retrieving fields: %s", pagedSql));

			con = getDbConnection();
			stmt = con.createStatement();

			long t1 = System.currentTimeMillis();
			rs = stmt.executeQuery(pagedSql);
			long t2 = System.currentTimeMillis();
			String durationStr = String.format(Locale.ROOT, "%.3fs", (t2 - t1) / 1000.0);
			Logger.getLogger(this.getClass()).info(String.format("sql query done in: %s", durationStr));

			int lineCount = 0;
			while (rs.next()) {
				ArrayList<String> cols = new ArrayList<String>();
				cols.add(rs.getString("col_id"));
				cols.add(rs.getString("col_name"));
				ret.add(cols);
				lineCount++;
			}
			Logger.getLogger(this.getClass()).info(String.format("getColIdsForTemplate retrieved %d lines.", lineCount));
		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	public synchronized static Connection getDbConnection() throws Exception {
		// Connection connectionn = null;
		//
		// try {
		// Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
		// Class.forName("oracle.jdbc.OracleDriver");
		// } catch (ClassNotFoundException e) {
		// Logger.getLogger(AdminServiceImpl.class).error(e.getMessage());
		// }
		//
		// String alias = "OracleDb";
		// String driverClass = "oracle.jdbc.OracleDriver";
		// String driverUrl =
		// "jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsbeta.ts.telekom.si)))";
		// String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;
		//
		//
		// Properties info = new Properties();
		// info.setProperty("proxool.maximum-connection-count", "5");
		// info.setProperty("simultaneous-build-throttle", "5");
		// info.setProperty("proxool.house-keeping-test-sql", "select * from dual");
		// info.setProperty("user", "reports");
		// info.setProperty("password", "ReportsPw01");
		// info.setProperty("proxool.driver", driverClass);
		// info.setProperty("proxool.url", url);
		//
		// connectionn = DriverManager.getConnection("proxool." + alias, info);
		//
		// Logger.getLogger(AdminServiceImpl.class).log(Level.INFO,
		// String.format("JDBC url: %s", driverUrl));
		// return connectionn;

		String jdbcUser = "reports";
		String jdbcPassword = "ReportsPw01";

		Properties proxoolProps = new Properties();
		proxoolProps.setProperty("user", jdbcUser);
		proxoolProps.setProperty("password", jdbcPassword);
		proxoolProps.setProperty("statistics-log-level", "DEBUG");

		// String driverClass = jdbcString.split("@")[0];
		String driverUrl = "jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsbeta.ts.telekom.si)))";

		// +
		// alias
		// +
		String alias = "oracleDb";
		Connection lconn = null;
		try {
			// DriverManager.setLogStream(System.out);
			lconn = DriverManager.getConnection("proxool." + alias);
		} catch (SQLException ex) {
			try {
				Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
				Properties info = new Properties();
				info.setProperty("proxool.maximum-connection-count", "10");
				info.setProperty("proxool.house-keeping-test-sql", "select CURRENT_DATE");
				info.setProperty("user", jdbcUser);
				info.setProperty("password", jdbcPassword);
				String driverClass = "oracle.jdbc.driver.OracleDriver";
				Class.forName(driverClass);
				String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;

				ProxoolFacade.registerConnectionPool(url, proxoolProps);
				try {
					lconn = DriverManager.getConnection("proxool." + alias);
				} catch (SQLException e) {
					StringWriter errorStringWriter = new StringWriter();
					PrintWriter pw = new PrintWriter(errorStringWriter);
					e.printStackTrace(pw);
					Logger.getLogger(AdminServiceImpl.class).error(errorStringWriter.getBuffer().toString());
					throw new ServerException(e);
				}
			} catch (ProxoolException | ClassNotFoundException e) {
				StringWriter errorStringWriter = new StringWriter();
				PrintWriter pw = new PrintWriter(errorStringWriter);
				e.printStackTrace(pw);
				Logger.getLogger(AdminServiceImpl.class).error(errorStringWriter.getBuffer().toString());
			}
		}
		return lconn;

	}

	public void updateCollId(String loginName, String loginPass, int templateId, String templateName, String col_id_old, String columnName,
			String value) throws ServerException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = getDbConnection();
			con.setAutoCommit(true);
			stmt = con.createStatement();

			String scheme = "mobile";

			String sqlExist = "select count(*) as cnt from " + scheme + ".t_subscription_inet_col where type_id=" + templateId + " and col_id='"
					+ col_id_old + "'";
			rs = stmt.executeQuery(sqlExist);
			if (rs.next()) {
				int count = rs.getInt("cnt");
				if (count == 1) {
					// update
					String sql = "update " + scheme + ".t_subscription_inet_col set " + columnName + " = '" + value + "' where type_id=" + templateId
							+ " and col_id = '" + col_id_old + "'";
					Logger.getLogger(this.getClass()).info("sql: " + sql);
					stmt.execute(sql);
				} else {
					// check if parent exist if not add it
// @formatter:off
					String sql = "MERGE INTO "+scheme+".t_subscription_inet_type USING dual ON ( type_id="
						+ templateId + " )"
						+ "	WHEN MATCHED THEN UPDATE SET type_name='" + templateId + "' " + "	WHEN NOT MATCHED THEN "
						+ "		INSERT(type_id,type_name) VALUES(" + templateId	+ ",'" + templateName + "')";
//@formatter:on
					Logger.getLogger(this.getClass()).info("sql: " + sql);
					stmt.execute(sql);
					Logger.getLogger(this.getClass()).info("done.");

					// insert
					String anotherCol = (columnName.equals("col_id") ? "col_name" : "col_id");
					sql = "insert into " + scheme + ".t_subscription_inet_col (type_id, " + columnName + ", " + anotherCol + ", on_main_doc) values " + "("
							+ templateId + ",'" + value + "','opis',1)";
					Logger.getLogger(this.getClass()).info("sql: " + sql);
					stmt.execute(sql);
					Logger.getLogger(this.getClass()).info("done.");

				}
			}

		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logger.getLogger(this.getClass()).error(ex.getMessage());
			}
		}
	}

	@Override
	public void deleteCollId(String loginName, String loginPass, int templateId, String col_id) throws ServerException {
		Connection con = null;
		Statement stmt = null;

		try {
			con = getDbConnection();
			con.setAutoCommit(true);
			stmt = con.createStatement();

			String scheme = "mobile";

			String sqlDelete = "delete from " + scheme + ".t_subscription_inet_col where type_id=" + templateId + " and col_id='" + col_id + "'";
			stmt.executeQuery(sqlDelete);
		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logger.getLogger(this.getClass()).error(ex.getMessage());
			}

		}
	}

	@Override
	public void transferToAllEnvironments(String loginName, String loginPass, int templateId) throws ServerException {
		String schemeOrig = "mobile";

		String[] urls = {
				// test - beta
				"mobile/reports/ReportsPw01~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsbeta.ts.telekom.si)))",
				// production
				"mobile/reports/ReportsPw01~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS_LIST=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm01.ts.telekom.si)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521)))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select) (METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tscrm.ts.telekom.si)))",
				// SB0 universe/enigma
				"universe/universe/enigma~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsalfa.ts.telekom.si)))",
				// SB1 mobilesb1_dev1/enigma
				"mobilesb1_dev1/mobilesb1_dev1/enigma~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=bssalfa.ts.telekom.si)))",
				// SB2 mobilesb2_dev1/enigma
				"mobilesb2_dev1/mobilesb2_dev1/enigma~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=bssalfa.ts.telekom.si)))",
				// SB2 mobilesb2_dev1/enigma
				"mobile/reports/ReportsPw01~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsalfa.ts.telekom.si)))" };

		Connection con = null;
		Statement stmt = null;

		try {
			con = getDbConnection();
			con.setAutoCommit(true);
			stmt = con.createStatement();
			stmt.execute("Select type_name from mobile.t_subscription_inet_type where type_id=" + templateId);
			ResultSet rslt = stmt.getResultSet();
			if (rslt.next()) {
				String typeName = rslt.getString(1);
				for (String url : urls) {
					if (!url.contains("tsbeta")) {
						String userAndPassword = url.split("~")[0];
						String scheme = userAndPassword.split("[//]")[0];
						String user = userAndPassword.split("[//]")[1];
						String password = userAndPassword.split("[//]")[2];
						String realUrl = url.split("~")[1];
						int i = 0;

						WsServer.log(loginName, "Updated: " + realUrl);

						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection connection = null;
						connection = DriverManager.getConnection(realUrl, user, password);
						Statement stmt1 = connection.createStatement();
// @formatter:off
						String sql = "MERGE INTO "+scheme+".t_subscription_inet_type USING dual ON ( type_id="
							+ templateId + " )"
							+ "	WHEN MATCHED THEN UPDATE SET type_name='" + typeName + "' " + "	WHEN NOT MATCHED THEN "
							+ "		INSERT(type_id,type_name) VALUES(" + templateId
							+ ",'" + typeName + "')";
// @formatter:on
						stmt1.execute(sql);

					// // @formatter:off
					// String sql1 =
					// "delete mobile.t_subscription_inet_col where type_id="+QuickTableFrame_t_subscription_inet_col.this.typeId;
					// //@formatter:on
						// stmt1.execute(sql1);

						Statement stmt2 = con.createStatement();
						ResultSet rs2 = stmt2.executeQuery("select col_id, col_name from " + schemeOrig + ".t_subscription_inet_col where type_id=" + templateId);

						while (rs2.next()) {
							String colId = rs2.getString("col_id");
							String colName = rs2.getString("col_name");
// @formatter:off
							String sql2 = " MERGE INTO "+scheme+".t_subscription_inet_col USING dual ON ( type_id="
									+ templateId + " and col_id='" + colId + "' )"
									+ "	WHEN MATCHED THEN UPDATE SET col_name='" + colName + "' " + "	WHEN NOT MATCHED THEN "
									+ "		INSERT(type_id,col_id,col_name,on_main_doc) VALUES("
									+ templateId + ",'" + colId + "','" + colName
									+ "',1)";
// @formatter:on
							stmt1.execute(sql2);
							i++;
						}
						rs2.close();
						stmt2.close();

						stmt1.close();
						connection.close();

						WsServer.log(loginName, "Transfered " + i + " col_ids to oracle environment: " + realUrl);
					}
				}
			}
		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (Exception ex) {
				Logger.getLogger(this.getClass()).error(ex.getMessage());
			}
		}

	}

	public static String getPrevStateId(Profile prof, int stateId) {
		for (int j = stateId - 1; j > 0; j--) {
			State state = prof.states.get(j);
			if (state != null)
				return state.getId();
		}
		return null;
	}

	public static String getNextStateId(Profile prof, int stateId) {
		for (int j = stateId + 1; j < prof.states.size(); j++) {
			State state = prof.states.get(j);
			if (state != null)
				return state.getId();
		}
		return null;
	}

	// public static int getStateIndex(Profile prof, String stateId) {
	// int i=0;
	// for (State state : prof.states) {
	// if (state.id.equals(stateId))
	// return i;
	// i++;
	// }
	// return null;
	// }

	public static void correctEffectiveDateOfProfiles() {
		// enumerate profiles and store them into hashmap
		IDfSession userSess = null;
		String dql = "select r_object_id, object_name from dm_document where folder('/System/DocMan') and object_name like 'profile_%'";
		IDfCollection collection = null;

		try {

			// ---- DEV
			AdminServiceImpl.repositoryName = "TS_Dev";
			AdminServiceImpl.superUserName = "zivkovick";
			AdminServiceImpl.superUserPassword = "Doitman789012";
			AdminServiceImpl.superUserDomain = "ts";
			String m_docbroker = "bsw-documentum.ts.telekom.si";
			configPath = "/app/render/DocMan-DEV";

			// ---- TEST
			// AdminServiceImpl.repositoryName = "Mobitel";
			// AdminServiceImpl.superUserName = "zivkovick";
			// AdminServiceImpl.superUserPassword = "Doitman789012";
			// AdminServiceImpl.superUserDomain = "ts";
			// String m_docbroker = "BTW-DOCUMENT-T.ts.telekom.si";
			// configPath = "/app/render/DocMan-TEST";

			// -- PROD
			// AdminServiceImpl.repositoryName = "Mobitel";
			// AdminServiceImpl.superUserName = "zivkovick";
			// AdminServiceImpl.superUserPassword = "Doitman789012";
			// AdminServiceImpl.superUserDomain = "ts";
			// String m_docbroker = "bpw-dmfs-cl1.ts.telekom.si";
			// configPath = "/app/render/DocMan-test";

			IDfClient dfClient = DfClient.getLocalClient();

			IDfSessionManager sessionMgr = null;
			IDfTypedObject config = dfClient.getClientConfig();
			config.setString("primary_host", m_docbroker);
			if (dfClient != null) {
				IDfLoginInfo li = new DfLoginInfo();
				li.setUser(AdminServiceImpl.superUserName);
				li.setPassword(AdminServiceImpl.superUserPassword);
				li.setDomain(AdminServiceImpl.superUserDomain);

				sessionMgr = dfClient.newSessionManager();
				for (int j = 0; j < dfClient.getDocbaseMap().getDocbaseCount(); j++) {
					sessionMgr.setIdentity(dfClient.getDocbaseMap().getDocbaseName(j), li);
				}
			}

			userSess = sessionMgr.getSession(AdminServiceImpl.repositoryName);

			userSess = getAdminSession();

			IDfQuery query = new DfQuery();
			query.setDQL(dql);

			collection = query.execute(userSess, IDfQuery.DF_READ_QUERY);
			while (collection.next()) {
				String object_name = collection.getString("object_name");
				File profileFile = new File(configPath + "/" + object_name + ".xml");
				if (profileFile.exists()) {
					Profile prof = parseProfile(profileFile.getAbsolutePath());

					IDfSysObject dfSysObject = (IDfSysObject) userSess
							.getObjectByQualification("dm_document where r_object_id='" + collection.getString("r_object_id") + "'");

					Date modifiedDocumentumTime = null;
					if (!dfSysObject.getTime("a_effective_date").isNullDate())
						modifiedDocumentumTime = dfSysObject.getTime("a_effective_date").getDate();
					else {
						modifiedDocumentumTime = dfSysObject.getModifyDate().getDate();
					}
					DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
					utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
					Date date = utcFormat.parse(utcFormat.format(modifiedDocumentumTime));

					String fromDocbaseFile = profileFile + "_fromDocBase";
					dfSysObject.getFile(fromDocbaseFile);

					Profile prof1 = parseProfile(fromDocbaseFile);

					if (!prof1.modifyDateUTC.equals(date)) {

						dfSysObject.setTime("a_effective_date", new DfTime(prof1.modifyDateUTC));
						dfSysObject.save();
						System.out.println("updated profile: " + prof1.id);
					}
				}
			}
			collection.close();
			userSess.getSessionManager().release(userSess);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void correctAcls() {
		// correctEffectiveDateOfProfiles();
		// ClassifyAsDraft();

		try {
			IDfSession sess = getAdminSession();

			File file = new File("c:\\Temp\\DcmtServiceLogs\\OUT.txt"); // creates a
																																	// new file
																																	// instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering
																									// character input stream
			StringBuffer sb = new StringBuffer(); // constructs a string buffer with
																						// no characters
			String line;
			int i = 0;
			while ((line = br.readLine()) != null) {
				String objectName = line.trim();
				IDfSysObject dfSysObject = (IDfSysObject) sess.getObjectByQualification("dm_document where object_name='" + objectName + "'");
				if (dfSysObject != null) {
					if (!dfSysObject.getLockOwner().contentEquals("")) {
						IDfQuery query = new DfQuery();
						query.setDQL(
								"update dm_document object set acl_name='mob_AllDelete', set acl_domain='dm_dbo', set r_lock_owner='', set r_lock_machine='', set r_lock_date=date('nulldate') where r_object_id='"
										+ dfSysObject.getId("r_object_id") + "'");
						query.execute(sess, IDfQuery.DF_EXEC_QUERY);
						dfSysObject.fetch("dm_document");
					}

					dfSysObject.setACLName("mob_AllDelete");
					dfSysObject.setACLDomain("dm_dbo");
					dfSysObject.save();
					System.out.println(i + "  " + objectName);
				}
				i++;
			}
			fr.close(); // closes the stream and release the resources
			sess.getSessionManager().release(sess);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void MassClassify() {
		try {
			String loginUser = "zivkovick";
			String loginPassword = Base64Utils.toBase64("Doitman789012".getBytes());
			IDfSession userSess = getSession("zivkovick", loginPassword);

			String dql = "select r_object_id,object_name,mob_template_id,mob_releaseno,mob_language,r_version_label from mob_form_template A where A.mob_releaseno in (select max(mob_releaseno) from mob_form_template where object_name = A.object_name) 				and not mob_template_number like 'PI%' and any r_version_label='effective' and A.r_object_id not in (select r_object_id from dm_dbo.T_DOCMAN_R where r_object_id=A.r_object_id and role_id='erender') order by mob_template_id, mob_releaseno";

			IDfQuery q = new DfQuery(dql);

			IDfCollection coll = q.execute(userSess, IDfQuery.DF_EXECREAD_QUERY);
			while (coll.next()) {
				IDfSysObject document = (IDfSysObject) userSess.getObject(new DfId(coll.getString("r_object_id")));
				Logger.getLogger(AdminServiceImpl.class)
						.info("Trying classify: " + document.getObjectName() + "  " + document.getAllRepeatingStrings("r_version_label", ","));
				Object[] ret = ExplorerServiceImpl.getInstance().getProfileAndUserRolesAndState(document, userSess.getLoginUserName(), userSess);
				// Profile prof = (Profile)ret[1];
				// if(prof==null)
				// {
				Profile prof = profiles.get("epredloga");
				String stateId = null;
				if (document.getAllRepeatingStrings("r_version_label", ",").contains("draft"))
					stateId = "draft";
				else if (document.getAllRepeatingStrings("r_version_label", ",").contains("effective"))
					stateId = "effective";
				else
					throw new Exception("no state");

				Map<String, List<String>> rolesUsers = new HashMap<String, List<String>>();
				for (Role role : prof.roles) {
					ArrayList<String> users = new ArrayList<String>();
					for (UserGroup ug : role.defaultUserGroups) {
						users.add(ug.getId());
					}
					rolesUsers.put(role.getId(), users);
				}
				Map<String, List<String>> values = new HashMap<String, List<String>>();

				ExplorerServiceImpl.getInstance().classifyDocument(loginUser, loginPassword, document.getId("r_object_id").toString(), prof.id, stateId,
						values, rolesUsers);

				// Logger.getLogger(AdminServiceImpl.class).info("classified: " +
				// document.getObjectName() + "(" + document.getId("r_object_id") +
				// ")");
				// }
				// else
				// {
				// Logger.getLogger(AdminServiceImpl.class).info("\talready
				// classified.");
				// }
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void checkPermForUser() {
		// kill $(lsof -ti tcp:9876)
		String dql = "select r_object_id,object_name, profile_id from dm_document c, dm_dbo.T_DOCMAN_S b where c.r_object_id=b.r_object_id and i_chronicle_id in (select i_chronicle_id from dm_document where r_object_id in (select audited_obj_id from dm_audittrail where event_name = 'dm_mark' and string_1 = 'effective'))";

		try {
			String loginUser = "zivkovick";
			String loginPassword = Base64Utils.toBase64("Doitman789012".getBytes());
			IDfSession userSess = getSession(loginUser, loginPassword);

			IDfQuery q = new DfQuery(dql);

			IDfCollection coll = q.execute(userSess, IDfQuery.DF_EXECREAD_QUERY);
			while (coll.next()) {
				IDfSysObject document = (IDfSysObject) userSess.getObject(new DfId(coll.getString("r_object_id")));

				boolean ok = false;
				IDfList listPerm = document.getPermissions();
				for (int i = 0; i < listPerm.getCount(); i++) {
					IDfPermit perm = (IDfPermit) listPerm.get(i);

					if (perm.getAccessorName().equals("dm_world") && (perm.getPermitValueString().equals("Read"))) {
						ok = true;
						break;
					}
				}

				if (!ok)
					Logger.getLogger(AdminServiceImpl.class).error(document.getObjectName() + " " + document.getAllRepeatingStrings("r_version_label", ",")
							+ " '" + document.getString("title") + "' world doesn't have read perm.");

			}
			coll.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@Override
	public String duplicateParametrizedQuery(String loginName, String loginPass, String name) throws ServerException {
		String ret = null;
		Logger.getLogger(this.getClass()).info("Duplicate search: " + name);
		ArrayList<MyParametrizedQuery> queries = new ArrayList<MyParametrizedQuery>();

		try {
			XPathFactory xpathFac = XPathFactory.newInstance();
			XPath xpath = xpathFac.newXPath();
			// xpath.setNamespaceContext();

			XPathExpression expr = xpath.compile("/config/docbase[1]/queries/query[@name='" + name + "']");
			Element el = (Element) expr.evaluate(getDocConfig(), XPathConstants.NODE);
			if (el != null) {

				Element el1 = (Element) el.cloneNode(true);
				el1.setAttribute("name", name + " (copy of)");

				el.getParentNode().appendChild(el1);

				Transformer tr = TransformerFactory.newInstance().newTransformer();
				tr.setOutputProperty(OutputKeys.INDENT, "yes");
				tr.setOutputProperty(OutputKeys.METHOD, "xml");
				tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				// tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
				tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

				// send DOM to file
				tr.transform(new DOMSource(docConfig), new StreamResult(new FileOutputStream(configPathFileName)));

				// check if it is ocalhost save it to development environment
				final String ip = getThreadLocalRequest().getRemoteHost();
				InetAddress addr = InetAddress.getByName(ip);
				String hostName = addr.getHostName();
				if (hostName.equals("localhost") || hostName.equals("activation.cloud.techsmith.com")) {
					File devConfig;
					if (SystemUtils.IS_OS_LINUX) {
						devConfig = new File("/home/klemen/git/Dis/Dis-server/src/main/resources/config.xml");
					} else {
						devConfig = new File("c:\\git\\Dis\\Dis-server\\src\\main\\resources\\config.xml");
					}
					FileUtils.copyFile(new File(configPathFileName), devConfig);
					Logger.getLogger(this.getClass()).info("Saved search to dev config file: " + devConfig.getAbsolutePath());
				}
			}
		} catch (Throwable ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			throw new ServerException(ex.getMessage());
		}

		return ret;
	}

	public static void beginTransaction(IDfSession userSession) throws Exception {
		userSession.beginTrans();
	}

	synchronized public String getLoginTicket(String userDomain, String userLoginName, String docbaseName) throws ServerException {
		String ret = null;
		String ticket = null;

		Logger.getLogger(AdminServiceImpl.class)
				.info(String.format("'getloginTicket' called for user: %s domain:%s docbase:%s", userLoginName, userDomain, docbaseName));

		IDfSession dfSuperUserSession = null;

		try {

			dfSuperUserSession = getAdminSession();

			ticket = dfSuperUserSession.getLoginTicketEx(userDomain + "\\" + userLoginName, "docbase", 0, false, null);

			// username - the supplied username
			// scope - docbase
			// timeout - no value (this defaults to the value in
			// login_ticket_timeout
			// in dm_server_config)
			// singleUse - false
			// serverName - no value

			// ticket = session.getLoginTicketForUser(userLoginName);
			ret = ticket;
			Logger.getLogger(AdminServiceImpl.class)
					.info(String.format("'getloginTicket' for user: %s domain:%s docbase:%s ... END.", userLoginName, userDomain, docbaseName));
		} catch (Exception ex) {
			ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(byteAOs);
			ex.printStackTrace(pw);
			pw.flush();
			Logger.getLogger(AdminServiceImpl.class)
					.error("Error getting authTicket for user " + userDomain + "\\" + userLoginName + ". " + ex.getMessage());
			throw new ServerException(ex);
		} finally {
			if (dfSuperUserSession != null)
				dfSuperUserSession.getSessionManager().release(dfSuperUserSession);
		}

		return ret;
	}

}
