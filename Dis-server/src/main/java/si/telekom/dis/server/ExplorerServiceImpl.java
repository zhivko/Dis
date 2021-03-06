package si.telekom.dis.server;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.Holder;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hsmf.MAPIMessage;
import org.apache.poi.hsmf.datatypes.AttachmentChunks;
import org.apache.poi.hsmf.datatypes.Chunks;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.json.JSONArray;
import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.documentum.com.DfClientX;
import com.documentum.com.IDfClientX;
import com.documentum.fc.client.DfClient;
import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfAuditTrailManager;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfPermit;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfType;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.client.IDfUser;
import com.documentum.fc.client.IDfVirtualDocument;
import com.documentum.fc.client.impl.session.ISession;
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
import com.documentum.operations.IDfCopyOperation;
import com.documentum.operations.IDfOperationError;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.itextpdf.text.pdf.PdfReader;
import com.microsoft.sqlserver.jdbc.SQLServerResultSet;

import si.telekom.dis.server.jaxwsClient.eRender.ERender;
import si.telekom.dis.server.jaxwsClient.eRender.ERenderImplService;
import si.telekom.dis.server.jaxwsClient.eRender.HashMapWrapper;
import si.telekom.dis.server.jaxwsClient.eRender.KeyValue;
import si.telekom.dis.server.jaxwsClient.pdfGenerator.PdfGenerator;
import si.telekom.dis.server.jaxwsClient.pdfGenerator.PdfGeneratorImplService;
import si.telekom.dis.server.reports.IIncludeInReport;
import si.telekom.dis.shared.Action;
import si.telekom.dis.shared.Attribute;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.AttributeValue;
import si.telekom.dis.shared.DcmtAttribute;
import si.telekom.dis.shared.DocType;
import si.telekom.dis.shared.Document;
import si.telekom.dis.shared.DocumentsResult;
import si.telekom.dis.shared.ExplorerService;
import si.telekom.dis.shared.ExtendedPermit;
import si.telekom.dis.shared.MyParametrizedQuery;
import si.telekom.dis.shared.Permit.permit;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.ProfileAttributesAndValues;
import si.telekom.dis.shared.Role;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.State;
import si.telekom.dis.shared.UserGroup;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
// @RemoteServiceRelativePath("login")
public class ExplorerServiceImpl extends RemoteServiceServlet implements ExplorerService {

	static ExplorerServiceImpl instance;
	static Parser parser = new AutoDetectParser();
	HashMap<String, String> collaboraUrls = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	public static String collaboraUrl = "https://klemen-hp-elitebook-850-g7-notebook-pc.ts.telekom.si:9980/hosting/discovery";

	static {
		try {

			/* Start of Fix */
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}

			} };

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};
			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			/* End of the fix */

			getInstance().collaboraUrl("odt", "edit");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ExplorerServiceImpl getInstance() {
		if (instance == null) {
			instance = new ExplorerServiceImpl();
		}

		return instance;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String loginPassword = request.getParameter("loginPassword");
		String rObjectId = request.getParameter("r_object_id");
		String rendition = request.getParameter("rendition");
		String decryptedZipFile = request.getParameter("decryptedZipFile");

		IDfSession userSession = null;
		try {

			if (decryptedZipFile != null) {
				File f = new File(decryptedZipFile);
				ByteArrayInputStream bacontentStreamIs = new ByteArrayInputStream(FileUtils.readFileToByteArray(f));

				response.setHeader("Content-Type", "application/octet-stream;");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + f.getName() + "\"");
				OutputStream os = response.getOutputStream();
				BufferedInputStream buf = new BufferedInputStream(bacontentStreamIs);
				int readBytes = 0;
				while ((readBytes = buf.read()) != -1) {
					os.write(readBytes);
				}
				os.flush();
				os.close();// *important*
				return;

			} else {

				userSession = AdminServiceImpl.getSession(loginName, loginPassword);
				IDfPersistentObject persObj = userSession.getObject(new DfId(rObjectId));
				IDfSysObject dfSysObj = (IDfSysObject) persObj;
				ByteArrayInputStream bacontentStreamIs = dfSysObj.getContentEx(rendition, 0);

				if (rendition.equals("tiff") || rendition.equals("png") || rendition.equals("jpg")) {
					Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(rendition);
					ImageReader reader = (ImageReader) iterator.next();
					ImageInputStream iis = ImageIO.createImageInputStream(bacontentStreamIs);
					reader.setInput(iis, true);
					ImageReadParam param = reader.getDefaultReadParam();
					BufferedImage bi = reader.read(0, param);

					File tmpFile = File.createTempFile(dfSysObj.getObjectId().getId().toString(), rendition);

					ImageIO.write(bi, "png", tmpFile);
					bacontentStreamIs = new ByteArrayInputStream(FileUtils.readFileToByteArray(tmpFile));

					tmpFile.delete();
				} else if (rendition.equals("msg")) {

					File msgfile = File.createTempFile(dfSysObj.getObjectId().getId().toString(), rendition);
					IOUtils.copy(bacontentStreamIs, new FileOutputStream(msgfile.getAbsolutePath()));

					ByteArrayOutputStream baOs = new ByteArrayOutputStream();

					MAPIMessage msg = new MAPIMessage(msgfile);
					Chunks mainChunks = msg.getMainChunks();
					if (mainChunks != null) {
						String encoding = null;

						// Map<MAPIProperty, List<PropertyValue>> props =
						// mainChunks.messageProperties;
						// if (props != null) {
						// // First choice is a codepage property
						// for (MAPIProperty prop : new MAPIProperty[] {
						// MAPIProperty.MESSAGE_CODEPAGE, MAPIProperty.INTERNET_CPID }) {
						// List<PropertyValue> val = props.get(prop);
						// if (val != null && val.size() > 0) {
						// int codepage = ((PropertyValue.LongPropertyValue)
						// val.get(0)).getValue();
						// try {
						// encoding = CodePageUtil.codepageToEncoding(codepage, true);
						// } catch (UnsupportedEncodingException e) {
						// Logger.getLogger(this.getClass()).warn("Unsupported encoding: " +
						// e.getMessage());
						// }
						// tryToSet7BitEncoding(msg, encoding);
						// }
						// }
						// }

//@formatter:off			
          baOs.write(("<!DOCTYPE html>" + "<html>" + "<head>" + "<title>Page Title</title>"
                  + "<meta charset='" + encoding + "'>" + "</head>" + "<body>").getBytes());
//@formatter:on
						if (msg.getMainChunks() != null) {
							baOs.write(("<br><br>\n").getBytes(encoding));
							baOs.write(("<strong>DATE:</strong>" + sdf.format(msg.getMessageDate().getTime()) + "<br>\n").getBytes(encoding));
							baOs.write(("<strong>FROM:</strong>" + msg.getDisplayFrom() + "<br>\n").getBytes(encoding));
							baOs.write(("<strong>TO:</strong>" + msg.getDisplayTo() + "<br>\n").getBytes(encoding));
							baOs.write(("<br>").getBytes(encoding));
							baOs.write(("<strong>SUBJECT:</strong>" + msg.getConversationTopic() + "<br>\n").getBytes(encoding));

							// BodyContentHandler handler = new BodyContentHandler(new
							// ToXMLContentHandler());
							// AutoDetectParser parser = new AutoDetectParser();
							// Metadata metadata = new Metadata();
							// try (InputStream stream = new
							// FileInputStream(msgfile.getAbsolutePath())) {
							// parser.parse(stream, handler, metadata);
							// }
							baOs.write(("<br><br>").getBytes(encoding));
							try {
								baOs.write(("<br><br>" + msg.getHtmlBody() + "<br>\n").getBytes(encoding));
							} catch (Exception ex) {

								baOs.write(("<br><br>" + msg.getTextBody().replaceAll("\\n", "<br>") + "<br>\n").getBytes(encoding));
							}

							for (AttachmentChunks att : msg.getAttachmentFiles()) {
								if (att.getAttachmentDirectory() != null) {
									baOs.write((att.getAttachFileName() + "." + att.getAttachExtension()).getBytes("UTF-8"));
								} else {
									String filename = att.getAttachFileName().getValue();

									String encodedStr = Base64.getEncoder().withoutPadding().encodeToString(att.getAttachData().getValue());

									// height=\"1000\" width=\"800\"
									if (filename.toLowerCase().endsWith("pdf")) {
										baOs.write(("<embed src=\"data:" + att.getAttachMimeTag() + ";base64," + encodedStr + "\">").getBytes("UTF-8"));
									} else {
										baOs.write(
												("<img alt=\"" + filename + "\" src=\"data:" + att.getAttachMimeTag() + ";base64," + encodedStr + "\">").getBytes("UTF-8"));
									}
								}
								baOs.write(("<br>").getBytes(encoding));

							}
							baOs.write(("</body>").getBytes("UTF-8"));

							bacontentStreamIs = new ByteArrayInputStream(baOs.toByteArray());
						}
					}
				}

				String name = dfSysObj.getObjectName();
				response.setHeader("Content-Type", "application/octet-stream;");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + name + "\"");
				OutputStream os = response.getOutputStream();
				BufferedInputStream buf = new BufferedInputStream(bacontentStreamIs);
				int readBytes = 0;
				while ((readBytes = buf.read()) != -1) {
					os.write(readBytes);
				}
				os.flush();
				os.close();// *important*
				return;
			}
		} catch (Throwable e1) {
			throw new ServletException("Throwable: ", new IOException(e1));
		} finally {
			// TODO Auto-generated catch block
			if (userSession != null) {
				AdminServiceImpl.getInstance().releaseSession(userSession);
			}
		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		super.doPut(req, resp);
	}

	@Override
	public DocumentsResult getObjects(String source_r_object_id, boolean current, String loginName, String password, int start, int length)
			throws ServerException {

		DocumentsResult ret1 = new DocumentsResult();
		ret1.lastItemFromQuery = start;

		ArrayList<Document> ret = new ArrayList<Document>();
		ArrayList<String> retRObjectIds = new ArrayList<String>();
		IDfQuery query = new DfQuery();

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			Logger.getLogger(this.getClass()).info("getObjects for " + loginName + " for: " + source_r_object_id);
			userSession = AdminServiceImpl.getSession(loginName, password);

			boolean done = false;

			int loop = 0;
			while (!done) {
				int rangeStart = start + 1;
				int rangeEnd = start + length;

				String dql;
				if (source_r_object_id == null || source_r_object_id.equals("")) {
					dql = "select r_object_id from dm_cabinet where is_private=0 ENABLE(RETURN_RANGE " + rangeStart + " " + rangeEnd + " 'object_name')"; // is_private=0
				} else if (source_r_object_id.startsWith("/")) {
					dql = "select r_object_id from dm_sysobject where folder('" + source_r_object_id + "') ENABLE(RETURN_RANGE " + rangeStart + " " + rangeEnd
							+ " 'r_object_id DESC')";
				} else {
					IDfFolder fold = (IDfFolder) userSession.getObjectByQualification("dm_folder where r_object_id='" + source_r_object_id + "'");
					if (fold != null) {
						String path = "";
						for (int i = 0; i < fold.getFolderPathCount(); i++) {
							path = path + fold.getFolderPath(i).toString();
						}
						dql = "select r_object_id from dm_sysobject where folder('" + path + "') ENABLE(RETURN_RANGE " + rangeStart + " " + rangeEnd
								+ " 'r_object_id DESC')";
					} else {
						dql = "dm_document(all) where r_object_id = '" + source_r_object_id + "'";
						IDfPersistentObject persObj = userSession.getObjectByQualification(dql);
						if (persObj != null) {
							ArrayList<Document> al = new ArrayList<Document>();
							al.add(docFromSysObject((IDfSysObject) persObj, loginName, userSession));
							ret1.documents = al;
							ret1.lastItemFromQuery++;
							return ret1;
						} else {
							dql = "dm_document where i_chronicle_id in (select i_chronicle_id from dm_document(all) where r_object_id = '" + source_r_object_id
									+ "')";
							persObj = userSession.getObjectByQualification(dql);
							if (persObj != null) {
								ArrayList<Document> al = new ArrayList<Document>();
								al.add(docFromSysObject((IDfSysObject) persObj, loginName, userSession));
								ret1.documents = al;
								ret1.lastItemFromQuery++;
								return ret1;
							} else {
								dql = "select r_object_id from dm_sysobject where object_name='" + source_r_object_id + "'";
							}
						}
					}
				}

				query.setDQL(dql);
				Logger.getLogger(this.getClass()).info("\tStarted dql query: " + dql);
				WsServer.log(loginName, "Started dql query...");
				long milis1 = System.currentTimeMillis();
				collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);
				WsServer.log(loginName, "Started dql query...Done.");
				long milis2 = System.currentTimeMillis();
				float duration1sec = (int) ((milis2 - milis1) / 1000);
				String durationStr1 = String.format(Locale.ROOT, "%.2f", duration1sec);
				Logger.getLogger(this.getClass()).info("\tEnded in: " + durationStr1 + "s");
				int prevLogOutputDuration = 0;
				int addedToResultCount = 0;
				while (collection.next()) {
					String r_object_id = collection.getId("r_object_id").toString();

					if (!retRObjectIds.contains(r_object_id)) {
						IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));
						Document doc = docFromSysObject((IDfSysObject) persObj, loginName, userSession);
						ret.add(doc);
						retRObjectIds.add(r_object_id);
						addedToResultCount++;

						if (ret.size() == length) {
							ret1.documents = ret;
							ret1.done = false;
							ret1.lastItemFromQuery++;
							return ret1;
						}
					}

					long milis3 = System.currentTimeMillis();
					int duration = (int) ((milis3 - milis2) / 1000);
					if (duration % 1 == 0 && prevLogOutputDuration != duration) {
						String message = "still parsing, currently: " + ret.size() + "/" + length + " working for: " + duration + "s";
						Logger.getLogger(this.getClass()).info("\t" + message);
						WsServer.log(loginName, message);
						prevLogOutputDuration = duration;
					}

					ret1.lastItemFromQuery++;

				}
				collection.close();

				if (addedToResultCount == 0) {
					done = true;
				} else {
					done = false;
					start = start + length;
				}

				long milis3 = System.currentTimeMillis();

				String durationStr2 = String.format(Locale.ROOT, "%.2f", (milis3 - milis2) / 1000.0);
				float duration = Float.valueOf(durationStr2).floatValue();

				String msg = "Returned: " + ret.size() + " objects, working for: " + duration + "s";
				Logger.getLogger(this.getClass()).info(msg);
				WsServer.log(loginName, "returned: " + ret.size() + " rows, ended in: " + durationStr2 + "s");

				loop++;
			}
			ret1.documents = ret;
			ret1.done = done;
		} catch (Throwable ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(stackTrace);

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret1;
	}

	public Document docFromSysObject(IDfPersistentObject persObj, String loginName, IDfSession userSession) throws Throwable {
		Document doc = new Document();
		try {
			Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(persObj, loginName, userSession);
			Profile prof = (Profile) profileAndRolesOfUserAndState[1];
			ArrayList<String> rolesOfUser = (ArrayList<String>) profileAndRolesOfUserAndState[2];
			String stateId = (String) profileAndRolesOfUserAndState[3];

			if (prof == null) {
				prof = findProfileForObjectType(persObj);
			}

			IDfSysObject sysObj = ((IDfSysObject) persObj);
			doc.object_name = sysObj.getObjectName().length() == 0 ? sysObj.getTitle() : sysObj.getObjectName();

			doc.state_id = (stateId == null ? null : stateId);

			doc.title = sysObj.getTitle();
			doc.subject = sysObj.getSubject();
			doc.r_creation_date = Date.from(sysObj.getCreationDate().getDate().toInstant());
			doc.r_modify_date = Date.from(sysObj.getModifyDate().getDate().toInstant());
			doc.modifier = sysObj.getModifier();
			doc.owner = sysObj.getOwnerName();
			doc.lockOwner = sysObj.getLockOwner();
			doc.lockMachine = sysObj.getLockMachine();
			int realeNoInd = sysObj.findAttrIndex("mob_releaseno");
			if (realeNoInd > -1) {
				doc.releaseNo = sysObj.getInt("mob_releaseno");
			} else {
				doc.releaseNo = -1;
			}

			doc.isClassified = (stateId == null ? false : true);

			doc.i_chronicle_id = sysObj.getChronicleId().getId().toString();

			if (sysObj.getFormat() != null) {
				doc.format = sysObj.getFormat().getName();
			} else {
				doc.format = "";
			}

			HashMap<String, List<String>> roleUsers = (HashMap<String, List<String>>) profileAndRolesOfUserAndState[4];

			doc.roles = new ArrayList<Role>();
			doc.roleMembers = new ArrayList<String>();
			for (String roleId : roleUsers.keySet()) {
				for (String roleMember : roleUsers.get(roleId)) {
					doc.roles.add(prof.getRoleForId(roleId));
					doc.roleMembers.add(roleMember);
				}
			}

			doc.attributes = new HashMap<String, List<String>>();
			ArrayList<Attribute> alAtts = new ArrayList<Attribute>();
			for (int i = 0; i < persObj.getAttrCount(); i++) {
				String attName = persObj.getAttr(i).getName();
				if (!attName.equals("r_object_id")) {
					ArrayList<String> values = new ArrayList<String>();
					if (persObj.getAttr(i).isRepeating()) {
						for (int j = 0; j < persObj.getValueCount(attName); j++) {
							values.add(persObj.getRepeatingValue(attName, j).asString());
						}
						doc.attributes.put(attName, values);
					} else {
						values.add(persObj.getValue(attName).asString());
					}
					doc.attributes.put(attName, values);
				}
			}

			ArrayList<String> formats = new ArrayList<String>();
			IDfCollection collection = ((IDfSysObject) persObj).getRenditions("full_format");
			while (collection.next()) {
				// Spin through each attribute.
				for (int i = 0; i < collection.getAttrCount(); i++) {
					formats.add(collection.getString("full_format"));
				}
			}
			collection.close();
			doc.formats = formats;

			doc.details = new ArrayList<String>();
			if (prof != null) {
				if (prof.detailAttributes != null) {
					for (Attribute att : prof.detailAttributes) {
						DocType docType = AdminServiceImpl.doctypes.get(persObj.getType().getName());
						if (docType != null) {
							DcmtAttribute attr = docType.attributes.get(att.dcmtAttName);
							if (attr.attr_repeating) {
								String value = sysObj.getAllRepeatingStrings(att.dcmtAttName, ",").toString();
								if (value != "") {
									doc.details.add(value);
								}
							} else {
								String value = sysObj.getValue(att.dcmtAttName).toString();
								if (!value.equals("")) {
									doc.details.add(value);
								}
							}
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		doc.r_object_id = persObj.getId("r_object_id").toString();
		doc.type_name = persObj.getType().getName();

		if (doc.type_name.equals("dm_cabinet") || userSession.getType(doc.type_name).isSubTypeOf("dm_cabinet") || doc.type_name.equals("dm_folder")
				|| userSession.getType(doc.type_name).isSubTypeOf("dm_folder")) {
			doc.isFolder = true;
		}

		return doc;
	}

	@Override
	public List<Action> getActionsForObject(String loginName, String password, String r_object_id) throws ServerException {
		// see if object is already classified
		Logger.getLogger(ExplorerServiceImpl.class).info("Getting actions for loginName: " + loginName + " and r_object_id: " + r_object_id);
		List<Action> ret = new ArrayList<Action>();

		IDfSession userSession = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject object = userSession.getObject(new DfId(r_object_id));
			showCurrentDocumentumPermits(loginName, object);
			ret = getActionsForObject(userSession, userSession.getLoginUserName(), object);
		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Logger.getLogger(ExplorerServiceImpl.class).info("Getting actions ended returned actions count: " + ret.size());

		return ret;

	}

	private List<Action> getActionsForObject(IDfSession userSession, String userNameOrGroupName, IDfPersistentObject object) throws Throwable {
		List<Action> ret = new ArrayList<Action>();

		String r_object_id = object.getString("r_object_id");

		Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(r_object_id, userNameOrGroupName, userSession);
		IDfPersistentObject persObj = (IDfPersistentObject) profileAndRolesOfUserAndState[0];
		Profile prof = (Profile) profileAndRolesOfUserAndState[1];
		ArrayList<String> rolesOfUser = (ArrayList<String>) profileAndRolesOfUserAndState[2];
		String stateId = (String) profileAndRolesOfUserAndState[3];
		boolean isClassified = (boolean) profileAndRolesOfUserAndState[5];

		String msg = "Actions for object <strong>" + persObj.getString("object_name") + "</strong> in state: <strong>" + stateId + "</strong> for user "
				+ userNameOrGroupName + " in role <strong>";

		if (rolesOfUser.size() == 0) {

			// TO-DO
			// no roles on object - lets try get general role of user...
			// rolesOfUser.add("unclassified");
		}

		if (prof != null) {
			// get all actions for user in this state
			Map<String, List<String>> rolesActions = prof.roleStateActions.get(stateId);
			if (rolesActions != null) {
				for (String roleId : rolesOfUser) {
					msg = msg + roleId + ",";
					for (String actionId : rolesActions.get(roleId)) {
						Action act = AdminServiceImpl.getInstance().getActionById(actionId);
						if (!ret.contains(act)) {
							ret.add(act);
						}
					}
				}
			}

			if (ret.size() == 0 && !isClassified) {
				// do same for "all" state and "all" role
				rolesActions = prof.roleStateActions.get("unclassified");
				msg = msg + "unclassified,";
				if (rolesActions != null) {
					for (String roleId : rolesOfUser) {
						for (String actionId : rolesActions.get(roleId)) {
							Action act = AdminServiceImpl.getInstance().getActionById(actionId);
							if (!ret.contains(act)) {
								ret.add(act);
							}
						}

						for (String actionId : rolesActions.get("unclassified")) {
							Action act = AdminServiceImpl.getInstance().getActionById(actionId);
							if (!ret.contains(act)) {
								ret.add(act);
							}
						}

					}
				}
			}
			if (msg.endsWith(",")) {
				msg = msg.substring(0, msg.length() - 1);
			}
			msg = msg + "</strong>";
			WsServer.log(userSession.getLoginInfo().getUser(), msg);
		} else {
			// unclassified document
			// lets get actions from "default for object type" definition
			Profile prof1 = findProfileForObjectType(persObj);

			// for unclassified document we show classify action
			if (!isClassified && !persObj.getType().isInstanceOf("dm_folder")) {
				Action action = new Action("document.classifyDoc", "klasificiraj", permit.WRITE);
				ret.add(action);
			}

			if (prof1 != null) {
				Map<String, List<String>> roleActionsInAllState = prof1.roleStateActions.get("unclassified");
				if (roleActionsInAllState != null) {
					List<String> actions = roleActionsInAllState.get("unclassified");
					if (actions != null) {
						for (String actionId : actions) {
							Action action = AdminServiceImpl.getInstance().getActionById(actionId);
							if (action != null) {
								ret.add(action);
							} else {
								Logger.getLogger(this.getClass()).error("Unknown action: " + actionId);
							}
						}
					}
				}
			} else {
				Logger.getLogger(this.getClass()).error("Not found profile for objectType: " + persObj.getType().getName());
			}
		}
		return ret;
	}

	Profile findProfileForObjectType(IDfPersistentObject persObj) throws DfException {
		Profile ret = null;
		for (Profile prof1 : AdminServiceImpl.profiles.values()) {
			if (prof1.isDefaultForObjectType) {
				if (prof1.objType.equals(persObj.getType().getName())) {
					ret = prof1;
					break;
				}
			}
		}

		if (ret == null) {
			List<String> allSuperTypes = findSuperTypes(persObj.getType(), null);
			for (String typeName : allSuperTypes) {
				DocType docType = AdminServiceImpl.doctypes.get(typeName);
				if (docType.profiles != null) {
					for (Profile prof1 : docType.profiles.values()) {
						if (prof1.isDefaultForObjectType) {
							// Logger.getLogger(this.getClass())
							// .info("Searching objectType for isDefaultForObjectType on
							// supertypes.. Found porfileId: " + prof1.id + " on type: " +
							// docType.id);
							ret = prof1;
							break;
						}
					}
				}
			}
		}

		return ret;
	}

	/**
	 * returns array object consisting of persObj, profile, rolesOfUser, stateId
	 *
	 *
	 * @param persObj
	 *          ... existing object in documentum
	 * @param forUserOrGroup
	 * @param userSession
	 * @return array of { persObj, prof, rolesOfUser, stateId, roleUserGroups,
	 *         isClassified };<br>
	 *         whereOf <br>
	 *         persObj ... documentum object<br>
	 *         prof ... documnetum profile<br>
	 *         rolesOfUser ... roles of logged user<br>
	 *         stateId ... id of state of object<br>
	 *         roleUserGroups ... users and groups in each role<br>
	 * @throws Exception
	 */
	public Object[] getProfileAndUserRolesAndState(IDfPersistentObject persObj, String forUserOrGroup, IDfSession userSession) throws Throwable {
		boolean isClassified = false;
		IDfQuery query2 = new DfQuery();
		IDfQuery query = new DfQuery();
		String id = persObj.getObjectId().getId();
		if (id == null) {
			Logger.getLogger(this.getClass()).warn("Object NULL!");
		}
		String dql1 = "select profile_id, current_state_id from dm_dbo.T_DOCMAN_S where r_object_id='" + id + "'";
		Logger.getLogger(this.getClass()).debug("Dql for docman_s: " + dql1);
		query.setDQL(dql1);
		Profile prof = null;
		ArrayList<String> rolesOfUser = new ArrayList<String>();
		String stateId = null;
		HashMap<String, List<String>> roleUserGroups = new HashMap<String, List<String>>();

		IDfCollection collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);
		// check for versioned objects
		collection.next();
		if (collection.getState() == IDfCollection.DF_NO_MORE_ROWS_STATE) {
			Logger.getLogger("No profile found for persObject.getObjectId().getId() - trying search for barcode.");
			collection.close();
			query2
					.setDQL("select profile_id, current_state_id from dm_dbo.T_DOCMAN_S where object_name='" + ((IDfSysObject) persObj).getObjectName() + "'");
			collection = query2.execute(userSession, IDfQuery.DF_READ_QUERY);
			collection.next();
		}

		// IDfUser dmUser = userSession.getUser(forUserOrGroupLoginName);
		if (collection.getState() == IDfCollection.DF_READY_STATE) {
			String profileId = collection.getString("profile_id");
			prof = AdminServiceImpl.profiles.get(profileId);
			if (prof != null) {
				isClassified = true;

				stateId = collection.getString("current_state_id");
				String foundStateId = null;
				for (State sta : prof.states) {
					if (sta.getId().equals(stateId)) {
						foundStateId = sta.getId();
						break;
					}
				}
				if (foundStateId == null) {
					Logger.getLogger(this.getClass()).error("State: " + stateId + " is not defined in profile!");
				}
			}

			// get user role
			query2.setDQL("select role_id, user_group_name from dm_dbo.T_DOCMAN_R where r_object_id='" + persObj.getObjectId().getId() + "'");
			try {
				IDfCollection collection2 = query2.execute(userSession, IDfQuery.DF_READ_QUERY);

				boolean hasNext = collection2.next();
				// check for versioned objects
				if (collection2.getState() == IDfCollection.DF_NO_MORE_ROWS_STATE) {
					Logger.getLogger("No profile found for persObject.getObjectId().getId() - trying search for barcode.");
					collection2.close();
					query2
							.setDQL("select role_id, user_group_name from dm_dbo.T_DOCMAN_R where object_name='" + ((IDfSysObject) persObj).getObjectName() + "'");
					collection2 = query2.execute(userSession, IDfQuery.DF_READ_QUERY);
					hasNext = collection2.next();
				}

				if (!hasNext) {
					Logger.getLogger(this.getClass()).warn("No records in T_DOCMAN_R - but records in T_DOCMAN_S exist ... no data for users and roles...");
				}

				while (hasNext) { // (collection2.getState() ==
					// IDfCollection.DF_READY_STATE) {
					String roleId = collection2.getString("role_id");
					String userGroupNames = collection2.getString("user_group_name");
					if (!roleUserGroups.containsKey(roleId)) {
						roleUserGroups.put(roleId, new ArrayList<String>());
					}
					if (!roleUserGroups.get(roleId).contains(userGroupNames)) {
						roleUserGroups.get(roleId).add(userGroupNames);
					}

					String roleToAdd = setRolesOfUser(userGroupNames, roleId, userSession, forUserOrGroup);
					if (roleToAdd != null && !rolesOfUser.contains(roleToAdd))
						rolesOfUser.add(roleToAdd);

					hasNext = collection2.next();
				}
				collection2.close();
			} catch (Exception ex) {
				Logger.getLogger(this.getClass()).error(ex);
			}
		}
		collection.close();

		// in case of administrators - lets add role administrator
		if (LoginServiceImpl.admins.contains(forUserOrGroup.toLowerCase())) {
			if (!rolesOfUser.contains("administrator")) {
				rolesOfUser.add("administrator");
			}
		}

		// add roles from profile for default users of profile roles
		if (prof != null)
			for (int i = 0; i < prof.roles.size(); i++) {
				Role role = prof.roles.get(i);
				for (int k = 0; k < role.defaultUserGroups.size(); k++) {

					UserGroup groupOrUser = role.defaultUserGroups.get(k);
					// Logger.getLogger(this.getClass().getName()).info("Role: " +
					// role.getId() + " defaultUser: " + groupOrUser.getId());

					if (AdminServiceImpl.allUsers.containsKey(forUserOrGroup)) {
						String roleToAdd = setRolesOfUser(groupOrUser.getId(), role.getId(), userSession, forUserOrGroup);
						if (roleToAdd != null && !rolesOfUser.contains(roleToAdd))
							rolesOfUser.add(roleToAdd);
					}
				}
			}

		Object[] ret = { persObj, prof, rolesOfUser, stateId, roleUserGroups, isClassified };
		return ret;
	}

	private String setRolesOfUser(String userGroupNames, String roleId, IDfSession userSession, String forUserOrGroup) throws Throwable {
		if (userGroupNames.equals("dm_world")) {
			return roleId;
		} else {
			MyIDfGroup group = AdminServiceImpl.allGroups.get(userGroupNames);

			if (group != null) {

				/*
				 * // --- start LdapQuery for nesting lookup of user that is part of
				 * groups within groups --- String groupDn = "CN=" + userGroupNames +
				 * ",OU=FIM-Managed Groups,DC=ts,DC=telekom,DC=si"; String ldapQuery =
				 * "(&(memberOf:1.2.840.113556.1.4.1941:={0})(objectCategory=person)(objectClass=user)(sAMAccountName={1}))";
				 * ldapQuery = ldapQuery.replaceAll("\\{0\\}", groupDn); ldapQuery =
				 * ldapQuery.replaceAll("\\{1\\}", forUserOrGroup);
				 * 
				 * Logger.getLogger(this.getClass()).debug("Member ldap query: " +
				 * ldapQuery);
				 * 
				 * long t4 = System.currentTimeMillis(); boolean isMember =
				 * AdminServiceImpl.getInstance().isMember(forUserOrGroup,
				 * userGroupNames); long t5 = System.currentTimeMillis(); if (t5 - t4 >
				 * 1000) { String msg = "Warning: ldap query: " + ldapQuery + " took " +
				 * (t5 - t4) + "ms."; WsServer.log(userSession.getLoginUserName(), msg);
				 * Logger.getLogger(this.getClass()).warn(msg); } // --- end LdapQuery
				 * for nesting lookup of user that is part of groups within groups ---
				 */

				boolean isMember = false;
				if (AdminServiceImpl.allUsers.containsValue(forUserOrGroup)) {
					isMember = isPartOf(userGroupNames, forUserOrGroup);
				}
				if (isMember || forUserOrGroup.toLowerCase().equals(userGroupNames.toLowerCase())) {
					return roleId;
				}

				// move over users in group
				for (int j = 0; j < group.getUsersNamesCount(); j++) {
					String userName = group.getUsersNames(j);
					if (forUserOrGroup.toLowerCase().equals(userName.toLowerCase())) {
						return roleId;
					}
				}

			} else {
				// try user
				// IDfUser user = userSession.getUser(userGroupNames);
				MyIDfUser user = AdminServiceImpl.allUsers.get(userGroupNames);
				if (user != null) {
					// if (user.getUserLoginName().equalsIgnoreCase(forUserOrGroup)) {
					return roleId;
					// }
				}
			}
		}
		return null;
	}

	private boolean isPartOf(String groupName, String forUserOrGroup) {
		// TODO Auto-generated method stub
		MyIDfGroup group = AdminServiceImpl.allGroups.get(groupName);
		MyIDfUser user = AdminServiceImpl.allUsers.get(forUserOrGroup);
		if (user == null)
			user = AdminServiceImpl.getUserByUserName(forUserOrGroup);
		if (user == null)
			Logger.getLogger(this.getClass()).error("OOPS");
		if (group.users.contains(user.userName)) {
			return true;
		}
		for (String oneGroupName : group.groups) {
			return isPartOf(oneGroupName, forUserOrGroup);
		}
		return false;
	}

	public void checkDocmanSExist(IDfPersistentObject persObject, IDfSession userSession, Profile prof) throws Exception {
		// check if record in docman_s exist if not create it
		IDfQuery querySelect = new DfQuery(
				"select profile_id, current_state_id from dm_dbo.T_DOCMAN_S where r_object_id='" + persObject.getId("r_object_id") + "'");
		IDfCollection coll1 = querySelect.execute(userSession, IDfQuery.DF_EXEC_QUERY);
		coll1.next();
		if (coll1.getState() == IDfCollection.DF_NO_MORE_ROWS_STATE) {
			String stateId = null;
			if (prof == null) {
				prof = ExplorerServiceImpl.getInstance().findProfileForObjectType(persObject);
			}
			int indexOfDraftState = 0;
			for (State stat : prof.states) {
				if (!stat.getId().equals("unclassified")) {
					stateId = stat.getId();
					break;
				}
				indexOfDraftState++;
			}

			IDfQuery queryInsert2 = new DfQuery("insert into dm_dbo.T_DOCMAN_S (r_object_id,object_name,profile_id,current_state_id) values('"
					+ persObject.getId("r_object_id") + "','" + ((IDfSysObject) persObject).getObjectName() + "','" + prof.id + "','" + stateId + "')");
			IDfCollection coll3 = queryInsert2.execute(userSession, IDfQuery.DF_EXEC_QUERY);
			coll3.close();
			Logger.getLogger(this.getClass()).info("Inserted into T_DOCMAN_S");

			AdminServiceImpl.runStandardActions(persObject, stateId, userSession);

			// }
			// coll2.close();
		}
		coll1.close();
	}

	/**
	 * returns array object consisting of persObj, profile, rolesOfUser, stateId
	 *
	 *
	 * @param persObj
	 *          ... existing object in documentum
	 * @param forUserOrGroup
	 * @param userSession
	 * @return array of { persObj, prof, rolesOfUser, stateId, roleUserGroups,
	 *         isClassified };<br>
	 *         whereOf <br>
	 *         persObj ... documentum object<br>
	 *         prof ... documnetum profile<br>
	 *         rolesOfUser ... roles of logged user<br>
	 *         stateId ... id of state of object<br>
	 *         roleUserGroups ... users and groups in each role<br>
	 *         isClassified ... object has profile attached
	 * @throws Exception
	 */
	private Object[] getProfileAndUserRolesAndState(String r_object_id, String userNameOrGroupName, IDfSession userSession) throws Throwable {
		// IDfSession userSession = AdminServiceImpl.getAdminSession();
		IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));
		return getProfileAndUserRolesAndState(persObj, userNameOrGroupName, userSession);
	}

	private List<String> findSuperTypes(IDfType type, ArrayList<String> ret) throws DfException {
		// System.out.println(type.getName() + " " + type.getSuperName());
		if (ret == null) {
			ret = new ArrayList<String>();
		}

		if (!type.getSuperName().equals("")) {
			ret.add(type.getSuperName());
			findSuperTypes(type.getSuperType(), ret);
		}
		return ret;
	}

	@Override
	public List<String> getRenditions(String loginName, String password, String r_object_id) throws ServerException {
		ArrayList<String> ret = new ArrayList<String>();
		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject persObject = userSession.getObjectByQualification("dm_sysobject(all) where r_object_id='" + r_object_id + "'");
			if (persObject == null) {
				throw new ServerException("Document doesn't exist, or no permission for document.");
			}

			IDfSysObject dfDocument = (IDfSysObject) persObject;
			collection = dfDocument.getRenditions("full_format");

			while (collection.next()) {
				// Spin through each attribute.
				for (int i = 0; i < collection.getAttrCount(); i++) {
					ret.add(collection.getString("full_format"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public ProfileAttributesAndValues getProfileAttributesAndValues(String loginName, String password, String r_object_id) throws ServerException {
		Logger.getLogger(this.getClass()).info("getProfileAttributesAndValues started for r_object_id: " + r_object_id);
		ProfileAttributesAndValues ret = new ProfileAttributesAndValues();
		IDfSession userSession = null;
		long t1 = System.currentTimeMillis();
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			Logger.getLogger(this.getClass()).debug("getProfileAndUserRolesAndState...");

			Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(r_object_id, userSession.getLoginUserName(), userSession);
			Logger.getLogger(this.getClass()).debug("getProfileAndUserRolesAndState... DONE.");
			IDfPersistentObject persObj = (IDfPersistentObject) profileAndRolesOfUserAndState[0];
			Profile prof = (Profile) profileAndRolesOfUserAndState[1];
			ArrayList<String> rolesOfUser = (ArrayList<String>) profileAndRolesOfUserAndState[2];
			String stateId = (String) profileAndRolesOfUserAndState[3];

			HashMap<String, List<String>> rolesAndUsers = (HashMap<String, List<String>>) profileAndRolesOfUserAndState[4];

			ret.rolesAndUsers = rolesAndUsers;

			if (stateId == null) {
				stateId = "unclassified";
			}

			String roleId = "";
			if (rolesOfUser.contains("administrator") || rolesOfUser.contains("admin")) {
				roleId = "administrator";
			} else if (rolesOfUser.contains("coordinator")) {
				roleId = "coordinator";
			} else if (rolesOfUser.contains("author")) {
				roleId = "author";
			} else if (rolesOfUser.contains("reviewer")) {
				roleId = "reviewer";
			} else if (rolesOfUser.contains("approver")) {
				roleId = "approver";
			} else if (rolesOfUser.contains("archiver")) {
				roleId = "archiver";
			} else if (rolesOfUser.contains("user")) {
				roleId = "user";
			}

			if (roleId.equals("")) {
				roleId = "unclassified";
			}

			if (prof == null) {
				Logger.getLogger(this.getClass()).debug("findProfileForObjectType(" + persObj.getId("r_object_id").toString() + ")");
				WsServer.log(loginName,
						"Unclassified document - no profile on object. Getting profile on objectType: <strong>" + persObj.getType().getName() + "</strong>");
				prof = findProfileForObjectType(persObj);
			}
			if (prof != null) {
				WsServer.log(loginName, "Found profile: <strong>" + prof.name + "</strong> (" + prof.id + ")");
				Logger.getLogger(this.getClass()).info("\tProfile=" + prof.id + "\tuser in role=" + roleId);
				ret.profile = prof;
				for (AttributeRoleStateWizards arsw : prof.attributeRolesStatesWizards) {
					List<String> rolesInState = arsw.stateRole.get(stateId);
					if (rolesInState == null) {
						WsServer.log(loginName, "Attributes for role " + roleId + " in state: " + stateId + " not defined in profile: " + prof.id + "("
								+ prof.name + ") defined on type of object: " + prof.objType);
					} else {
						if (rolesInState.contains(roleId)) {

							ret.attributes = arsw.attributes;
							ret.values = new HashMap<String, List<String>>();
							String durationStr = String.format(Locale.ROOT, "%.3fs.", (System.currentTimeMillis() - t1) / 1000.0);

							WsServer.log(loginName,
									"Object <strong>" + persObj.getString("object_name") + "</strong> (" + persObj.getId("r_object_id") + ") type: <strong>"
											+ persObj.getType().getName() + "</strong> profile: " + prof.name + " (<strong>" + prof.id + "</strong>) state: <strong>"
											+ stateId + "</strong> user <strong>" + loginName + "</strong> in role: <strong>" + roleId + "</strong> in " + durationStr);

							for (Attribute attr : ret.attributes) {
								DcmtAttribute dcmtAttribute = AdminServiceImpl.getInstance().findAttribute(attr.dcmtType, attr.dcmtAttName);
								if (dcmtAttribute != null) {
									List<String> values = new ArrayList<String>();
									// Logger.getLogger(this.getClass()).info("\t\tattribute: " +
									// dcmtAttribute.attr_name);
									if (!dcmtAttribute.attr_repeating) {
										String value = null;
										if (dcmtAttribute.domain_type.equals("0")) {
											value = String.valueOf(persObj.getBoolean(attr.dcmtAttName));
										} else if (dcmtAttribute.domain_type.equals("1")) {
											value = String.valueOf(persObj.getInt(attr.dcmtAttName));
										} else if (dcmtAttribute.domain_type.equals("2")) {
											value = String.valueOf(persObj.getString(attr.dcmtAttName));
										} else if (dcmtAttribute.domain_type.equals("3")) {
											value = String.valueOf(persObj.getId(attr.dcmtAttName));
										} else if (dcmtAttribute.domain_type.equals("4")) {
											Calendar c = new GregorianCalendar();
											if (persObj.getTime(attr.dcmtAttName).getDate() != null) {
												c.setTime(persObj.getTime(attr.dcmtAttName).getDate());
												value = String.valueOf(c.getTimeInMillis());
											}
										}
										values.add(value);
									} else {
										int valueCount = persObj.getValueCount(attr.dcmtAttName);
										for (int i = 0; i < valueCount; i++) {
											String value = null;
											if (dcmtAttribute.domain_type.equals("0")) {
												value = String.valueOf(persObj.getRepeatingBoolean(attr.dcmtAttName, i));
											} else if (dcmtAttribute.domain_type.equals("1")) {
												value = String.valueOf(persObj.getRepeatingInt(attr.dcmtAttName, i));
											} else if (dcmtAttribute.domain_type.equals("2")) {
												value = String.valueOf(persObj.getRepeatingString(attr.dcmtAttName, i));
											} else if (dcmtAttribute.domain_type.equals("3")) {
												value = String.valueOf(persObj.getRepeatingId(attr.dcmtAttName, i));
											} else if (dcmtAttribute.domain_type.equals("4")) {
												if (persObj.getRepeatingTime(attr.dcmtAttName, i).getDate() != null) {
													Calendar c = new GregorianCalendar();
													c.setTime(persObj.getRepeatingTime(attr.dcmtAttName, i).getDate());
													value = String.valueOf(c.getTimeInMillis());
												}
											}
											values.add(value);
										}
									}
									// Logger.getLogger(this.getClass()).info("\t\t\tvalue: " +
									// values.toString());

									ret.values.put(attr.dcmtAttName, values);
								} else {
									WsServer.log(loginName,
											"attribute <strong>" + attr.dcmtAttName + "</strong> missing on type: <strong>" + attr.dcmtType + "</strong>");
								}
							}
							break;
						}
						// else
						// {
						// WsServer.log(loginName, "No attribute definition in profile
						// <strong>" + prof.id
						// + "</strong> for role: <strong>" + roleId + "</strong> in state
						// <strong>" + stateId + "</strong>");
						// }
					}
				}
				if (ret.values == null || ret.values.size() == 0) {
					String dumpOfObject = persObj.dump();
					dumpOfObject = dumpOfObject.replaceAll("\n", "<br>");
					String msg = "No attribute definition in profile <strong>" + prof.name + "(" + prof.id + ")</strong> for role: <strong>" + roleId
							+ "</strong> for r_object_id: " + persObj.getId("r_object_id").toString() + "<br>" + "Dump of object:<br>" + dumpOfObject;
					WsServer.log(loginName, msg);
					Logger.getLogger(this.getClass()).warn(msg);
				}

			} else {
				String durationStr = String.format(Locale.ROOT, "%.3fs.", (System.currentTimeMillis() - t1) / 1000.0);
				String message = "No profile found for r_object_id=" + r_object_id + " type=" + persObj.getType().getName() + " in " + durationStr;
				Logger.getLogger(this.getClass()).info(message);
				WsServer.log(loginName, message);
			}

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			throw new ServerException(ex);
		} finally {
			if (userSession != null) {
				AdminServiceImpl.getInstance().releaseSession(userSession);
			}
		}
		String durationStr = String.format(Locale.ROOT, "%.3fs.", (System.currentTimeMillis() - t1) / 1000.0);
		String msg = null;
		if (ret.attributes != null) {
			msg = "getProfileAttributesAndValues ended. Returned: " + ret.attributes.size() + " attributes with values for r_object_id=" + r_object_id
					+ " in " + durationStr;
		} else {
			msg = "getProfileAttributesAndValues ended. Attributes null in " + durationStr;
		}
		Logger.getLogger(this.getClass()).info(msg);

		return ret;
	}

	@Override
	public List<String[]> getValuesFromDql(String loginName, String password, String likeString, String dql) throws ServerException {
		ArrayList<String[]> ret = new ArrayList<String[]>();
		if (dql.equals("")) {
			return ret;
		}
		IDfQuery query = new DfQuery();

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			String valueField = null;

			// ordinary attribute selects
			Pattern p = Pattern.compile("select (.*) from (.*) where (.*) order by (.*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);// .

			// user group selects
//@formatter:off			
            Pattern p1 = Pattern.compile(
                    "select (.*) from (.*) where (.*) " + "union " + "select (.*) from (.*) where (.*)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
//@formatter:on			

			likeString = likeString.toLowerCase().trim();

			// represents
			// single
			// character
			Matcher m = p.matcher(dql);
			Matcher m1 = p1.matcher(dql);
			String completeDql = "";
			String from = null;
			if (m.matches()) {
				// select user_name as ug, description from dm_user where 1=1
				// union
				// select group_name as ug, description from dm_group where 1=1
				String fields = m.group(1);
				String[] field = fields.split(",");

				String idField = field[0].trim();
				valueField = field[1].trim();

				from = m.group(2);
				String where = m.group(3);
				String orderby = m.group(4);

				if (likeString != null && !likeString.equals("")) {
					completeDql = "select " + fields + " from " + from + " where " + where + " and (lower(" + valueField + ") like '%" + likeString
							+ "%' or lower(" + idField + ") like '%" + likeString + "%') order by " + orderby;
				} else {
					completeDql = "select " + fields + " from " + from + " where " + where + " order by " + orderby;
				}

			} else if (m1.matches()) {
				String select1 = m1.group(1);
				String from1 = m1.group(2);
				String where1 = m1.group(3);

				String select2 = m1.group(4);
				String from2 = m1.group(5);
				String where2 = m1.group(6);

				String[] field1 = select1.split(",");
				String idField1 = field1[0].trim();
				String valueField1 = field1[1].trim();

				String[] field2 = select2.split(",");
				String idField2 = field2[0].trim();
				String valueField2 = field2[1].trim();

				from = from1;

				if (likeString != null && !likeString.equals("")) {
//@formatter:off							
                    completeDql = "select " + select1 + " from " + from1 + " where " + where1 + " and (lower("
                            + valueField1 + ") like '%" + likeString + "%' or lower(" + idField1 + ") like '%"
                            + likeString + "%')" + " union " + "select " + select2 + " from " + from2 + " where "
                            + where2 + " and (lower(" + valueField2 + ") like '%" + likeString + "%' or lower("
                            + idField2 + ") like '%" + likeString + "%')";
//@formatter:on							
				} else {
					completeDql = dql;
				}
			} else {
				throw new ServerException(new Exception("getValuesFromDql definition must be in form: select field1,field2 from ... where ... order by ..."));
			}

			// fields should be in form id,value
			// search and remove fixedValues
			// fixedValues(dm_world, vsi;dm_owner, lastnik, dm_group, skupina)
			Pattern pFixedValues = Pattern.compile(".*(fixedValues\\((.+?)\\))", Pattern.CASE_INSENSITIVE);
			Matcher mFixedVal = pFixedValues.matcher(completeDql);
			if (mFixedVal.find()) {
				if (mFixedVal.group(1) != null) {
					String[] vals = mFixedVal.group(2).split(";");
					for (String val : vals) {
						String[] v = val.split(",");
						ret.add(v);
					}
					completeDql = completeDql.replace(mFixedVal.group(1), "");
				}
			}

			query.setDQL(completeDql);
			Logger.getLogger(this.getClass()).info("getValuesFromDql: " + completeDql);
			collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);

			int maxRowsReturned = 200;
			int recNo = 0;
			while (collection.next()) {
				ArrayList<String> al = new ArrayList<String>();
				for (int i = 0; i < collection.getAttrCount(); i++) {
					String value = collection.getValueAt(i).toString();
					al.add(value);
				}
				String[] val = (String[]) al.toArray(new String[collection.getAttrCount()]);
				ret.add(val);
				recNo++;
				if (recNo > maxRowsReturned) {
					break;
				}

			}

			Logger.getLogger(this.getClass()).info("getValuesFromDql: " + completeDql + " returned " + ret.size() + " suggestions.");

		} catch (Exception ex) {
			// ex.printStackTrace();
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * jdbc string should be in form [jdbcString]~[user:password]~[sql]:
	 * "jdbc:oracle:thin:@localhost:1521/orclpdb1~user:password~sql"
	 * jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS_LIST=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm01.ts.telekom.si)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521)))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tscrm.ts.telekom.si)))~reports:ReportsPw01~select
	 * it.topic_id, it.topic_id || ' - ' || getdelimited('select
	 * interact_topic_desc from t_interact_topic start with topic_id =
	 * '||it.topic_id||' connect by prior topic_super_id = topic_id order by level
	 * desc', ' -> ') opis from t_interact_topic it where not exists (select 1
	 * from t_interact_topic it2 where it2.topic_super_id = it.topic_id) and
	 * status = 1 order by 1
	 */
	@Override
	public List<String[]> getValuesFromSql(String loginName, String password, String likeString, String jdbcStringAndSql) throws ServerException {
		// throw new ServerException(new Exception("Not implemented"));
		ArrayList<String[]> ret = new ArrayList<String[]>();

		if (jdbcStringAndSql != null) {
			String[] splitted = jdbcStringAndSql.split("~");

			String driverClass = splitted[0];// "oracle.jdbc.OracleDriver";

			String jdbcUser = splitted[1].split(":")[0];
			String jdbcPassword = splitted[1].split(":")[1];

			String jdbcString = splitted[2];

			String sql = splitted[3];

			Properties proxoolProps = new Properties();
			proxoolProps.setProperty("user", jdbcUser);
			proxoolProps.setProperty("password", jdbcPassword);
			proxoolProps.setProperty("statistics-log-level", "DEBUG");

			// String driverClass = jdbcString.split("@")[0];
			String driverUrl = jdbcString;

			// +
			// alias
			// +
			driverUrl = splitted[2];// "jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsbeta.ts.telekom.si)))";
			String alias = DigestUtils.sha1Hex(driverUrl);
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
					driverClass = "oracle.jdbc.driver.OracleDriver";
					Class.forName(driverClass);
					String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;

					ProxoolFacade.registerConnectionPool(url, proxoolProps);
					try {
						lconn = DriverManager.getConnection("proxool." + alias);
					} catch (SQLException e) {
						StringWriter errorStringWriter = new StringWriter();
						PrintWriter pw = new PrintWriter(errorStringWriter);
						e.printStackTrace(pw);
						Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
						throw new ServerException(e);
					}
				} catch (ProxoolException | ClassNotFoundException e) {
					StringWriter errorStringWriter = new StringWriter();
					PrintWriter pw = new PrintWriter(errorStringWriter);
					e.printStackTrace(pw);
					Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
				}
			}

			Statement stmt = null;
			ResultSet rs = null;
			try {
				stmt = lconn.createStatement();
				Logger.getLogger(this.getClass()).info(sql);
				rs = stmt.executeQuery(sql);

				Pattern p = Pattern.compile(".*" + likeString + ".*", Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);// .
				// represents
				// single
				// character
				int maxResultCount = 20;

				while (rs.next()) {
					String col1 = rs.getString(1);
					String col2 = rs.getString(2);

					if (!likeString.equals("")) {
						Matcher m = p.matcher(col2);
						if (m.find()) {
							String[] values = { col1, col2 };
							ret.add(values);
						}
					} else {
						String[] values = { col1, col2 };
						ret.add(values);
					}
					if (ret.size() > maxResultCount) {
						break;
					}

				}
				Logger.getLogger(this.getClass()).info("Returned: " + ret.size() + " records.");
			} catch (SQLException e) {
				StringWriter errorStringWriter = new StringWriter();
				PrintWriter pw = new PrintWriter(errorStringWriter);
				e.printStackTrace(pw);
				Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			} finally {
				if (rs != null)
					try {
						rs.close();
						stmt.close();
						lconn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		return ret;

	}

	@Override
	public Void setAttributes(String loginName, String password, String r_object_id, List<AttributeValue> values) throws ServerException {
		Logger.getLogger(this.getClass()).info("SetAttribute started for loginName: " + loginName + " for object r_object_id: " + r_object_id);
		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));
			if (persObject == null) {
				throw new ServerException("Document doesn't exist, or no permission for document.");
			}
			Object[] ret = getProfileAndUserRolesAndState(persObject, loginName, userSession);
			Profile prof = (Profile) ret[1];

			String stateId = "unclassified";
			String roleId = "unclassified";
			if (prof != null) {
				ArrayList<String> rolesOfUser = (ArrayList<String>) ret[2];
				stateId = (String) ret[3];
				if (rolesOfUser.contains("administrator")) {
					roleId = "administrator";
				} else if (rolesOfUser.contains("coordinator")) {
					roleId = "coordinator";
				} else if (rolesOfUser.contains("author")) {
					roleId = "author";
				} else if (rolesOfUser.contains("reviewer")) {
					roleId = "reviewer";
				} else if (rolesOfUser.contains("approver")) {
					roleId = "approver";
				} else if (rolesOfUser.contains("user")) {
					roleId = "user";
				} else {
					roleId = "non standard role";
					Logger.getLogger(this.getClass()).warn("non standard role!");
				}
			} else {
				prof = findProfileForObjectType(persObject);
			}

			AttributeRoleStateWizards arsw = AdminServiceImpl.getAttributesForStateAndRole(prof, stateId, roleId);
			IDfSysObject dfDocument = (IDfSysObject) persObject;
			if (arsw != null) {
				Logger.getLogger(this.getClass()).info("\tr_object_id=" + r_object_id + " object_name=" + ((IDfSysObject) (persObject)).getObjectName());

				for (AttributeValue attrVal : values) {
					String attName = attrVal.getName();
					DcmtAttribute attr = AdminServiceImpl.getInstance().findAttribute(dfDocument.getType().getName(), attName);
					Attribute att = AdminServiceImpl.getAttFromAttributeRoleStateWizards(arsw, attName);
					if (!att.isReadOnly) {
						if (!attr.attr_repeating) {
							String attValue = null;
							if (attrVal.getValues().size() > 0) {
								attValue = attrVal.getValues().get(0);

								Logger.getLogger(this.getClass()).info("\t" + attName + " = '" + attValue + "'");
								// 0,Boolean
								// 1,Integer
								// 2,String
								// 3,ID
								// 4,Time/Date
								// 5,Double

								if (attr.domain_type.equals("0")) {
									dfDocument.setBoolean(attName, Boolean.valueOf(attValue));
								} else if (attr.domain_type.equals("1")) {
									dfDocument.setInt(attName, Integer.valueOf(attValue));
								} else if (attr.domain_type.equals("2")) {
									dfDocument.setString(attName, attValue);
								} else if (attr.domain_type.equals("3")) {
									dfDocument.setId(attName, new DfId(attValue));
								} else if (attr.domain_type.equals("4")) {
									// milliseconds since January 1, 1970, 00:00:00 GMT
									if (!attValue.equals("")) {
										Date date = new Date();
										date.setTime(Long.valueOf(attValue).longValue());
										IDfTime time = new DfTime(date);
										Logger.getLogger(this.getClass()).info("\t\t = '" + time.toString() + "'");
										dfDocument.setTime(attName, time);
									} else {
										dfDocument.setTime(attName, null);
									}
								} else if (attr.domain_type.equals("5")) {
									dfDocument.setDouble(attName, Double.valueOf(attValue));
								}
							} else {
								dfDocument.setNull(attName);
							}
						} else {
							int i = 0;
							if (attrVal.getValues().size() > 0) {
								for (String valueFromValues : attrVal.getValues()) {
									Logger.getLogger(this.getClass()).info("\t" + attName + "[" + i + "] = '" + valueFromValues + "'");

									if (attr.domain_type.equals("0")) {
										dfDocument.setRepeatingBoolean(attName, i, Boolean.valueOf(valueFromValues));
									} else if (attr.domain_type.equals("1")) {
										dfDocument.setRepeatingInt(attName, i, Integer.valueOf(valueFromValues));
									} else if (attr.domain_type.equals("2")) {
										dfDocument.setRepeatingString(attName, i, valueFromValues);
									} else if (attr.domain_type.equals("3")) {
										dfDocument.setRepeatingId(attName, i, new DfId(valueFromValues));
									} else if (attr.domain_type.equals("4")) {
										// milliseconds since January 1, 1970, 00:00:00 GMT
										Date date = new Date();
										date.setTime(Long.valueOf(valueFromValues).longValue());
										IDfTime time = new DfTime(date);
										dfDocument.setRepeatingTime(attName, i, time);
									} else if (attr.domain_type.equals("5")) {
										dfDocument.setRepeatingDouble(attName, i, Double.valueOf(valueFromValues));
									}
									i++;
								}
							}
							int howMany = dfDocument.getValueCount(attName) - i;

							if (howMany > 0) {
								for (int j = 0; j < howMany; j++) {
									dfDocument.remove(attName, i);
								}
							}
						}
					}
				}
				Logger.getLogger(this.getClass()).info("SetAttribute save object...");
				dfDocument.save();
				Logger.getLogger(this.getClass()).info("SetAttribute save object...DONE.");
			} else {
				String msg = "Definition of attributes for role: " + roleId + " in state: " + stateId + " in profile: " + prof.id;
				WsServer.log(loginName, msg);
				Logger.getLogger(this.getClass()).warn("user: " + loginName + " r_object_id: " + dfDocument.getId("r_object_id").toString() + msg);
			}
		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(ExplorerServiceImpl.class).error("Error rolling back transaction.");
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Void deleteObject(String loginName, String password, String r_object_id, boolean allVersions) throws ServerException {
		// TODO Auto-generated method stub
		IDfSession userSession = null;
		IDfCollection collection = null;
		Logger.getLogger(this.getClass()).info(String.format("[%s] deleteObject started for %s", loginName, r_object_id));

		IDfSession adminSession = null;

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			AdminServiceImpl.beginTransaction(userSession);

			IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));
			if (persObject == null) {
				throw new ServerException("Document doesn't exist, or no permission for document.");
			}

			IDfSysObject dfDocument = (IDfSysObject) persObject;
			Logger.getLogger(this.getClass()).info(String.format("object_name: [%s]", dfDocument.getObjectName()));

			WsServer.log(loginName, "Executing delete...");

			if (!allVersions) {
				IDfQuery queryDelete = new DfQuery("delete from dm_dbo.T_DOCMAN_S where r_object_id='" + persObject.getId("r_object_id") + "'");
				Logger.getLogger(this.getClass()).info("Delete dql: " + queryDelete.getDQL());
				IDfCollection coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
				if (coll.next()) {
					int rowsDeleted = coll.getInt("rows_deleted");
					Logger.getLogger(this.getClass()).info("Executing delete in DOCMAN_S..." + rowsDeleted + " rows deleted.");
					coll.close();
				}

				try {
					String dql = "delete from dm_dbo.T_dm_audittrail_s where audited_obj_id='" + persObject.getId("r_object_id") + "'";
					queryDelete = new DfQuery(dql);
					coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
					if (coll.next()) {
						int rowsDeleted = coll.getInt("rows_deleted");
						Logger.getLogger(this.getClass()).info("Executing delete in T_dm_audittrail_s..." + rowsDeleted + " rows deleted.");
						coll.close();
					}
				} catch (Exception ex) {
					Logger.getLogger(this.getClass()).debug("T_dm_audittrail_s not found (" + ex.getMessage()
							+ "). It is used only in DEV and TEST environment simulating dbo.dm_audittrail_Mobitel_all table");
				}

				IDfQuery queryDelete2 = new DfQuery("delete from dm_dbo.T_DOCMAN_R where r_object_id='" + persObject.getId("r_object_id") + "'");

				Logger.getLogger(this.getClass()).info("Delete dql: " + queryDelete2.getDQL());
				coll = queryDelete2.execute(userSession, IDfQuery.DF_EXEC_QUERY);
				if (coll.next()) {
					int rowsDeleted = coll.getInt("rows_deleted");
					Logger.getLogger(this.getClass()).info("Executing delete in DOCMAN_R..." + rowsDeleted + " rows deleted.");
				}
				coll.close();
			} else {
				IDfCollection col2 = dfDocument.getVersions("r_object_id");
				while (col2.next()) {
					String r_object_id_ = col2.getString("r_object_id");

					IDfQuery queryDelete = new DfQuery("delete from dm_dbo.T_DOCMAN_S where r_object_id='" + r_object_id_ + "'");
					Logger.getLogger(this.getClass()).info("Delete dql: " + queryDelete.getDQL());
					IDfCollection coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
					if (coll.next()) {
						int rowsDeleted = coll.getInt("rows_deleted");
						Logger.getLogger(this.getClass()).info("Executing delete in DOCMAN_S..." + rowsDeleted + " rows deleted.");
						coll.close();
					}

					try {
						String dql = "delete from dm_dbo.T_dm_audittrail_s where audited_obj_id='" + r_object_id + "'";
						queryDelete = new DfQuery(dql);
						coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
						if (coll.next()) {
							int rowsDeleted = coll.getInt("rows_deleted");
							WsServer.log(loginName, "Executing delete in T_dm_audittrail_s..." + rowsDeleted + " rows deleted.");
							coll.close();
						}
					} catch (Exception ex) {
						Logger.getLogger(this.getClass()).debug("T_dm_audittrail_s not found (" + ex.getMessage()
								+ "). It is used only in DEV and TEST environment simulating dbo.dm_audittrail_Mobitel_all table");
					}

					IDfQuery queryDelete2 = new DfQuery("delete from dm_dbo.T_DOCMAN_R where r_object_id='" + r_object_id_ + "'");

					Logger.getLogger(this.getClass()).info("Delete dql: " + queryDelete2.getDQL());
					coll = queryDelete2.execute(userSession, IDfQuery.DF_EXEC_QUERY);
					if (coll.next()) {
						int rowsDeleted = coll.getInt("rows_deleted");
						WsServer.log(loginName, "Executing delete in DOCMAN_R..." + rowsDeleted + " rows deleted.");
					}
					coll.close();
				}
				col2.close();
			}

			WsServer.log(loginName, "Executing delete...Done.");
			WsServer.log(loginName, "Destroying...");

			if (allVersions) {
				adminSession = AdminServiceImpl.getAdminSession();
				// check permissions on all versions if there is no permission add
				// permission
				IDfCollection col2 = dfDocument.getVersions("r_object_id");
				while (col2.next()) {
					String r_object_id_ = col2.getString("r_object_id");
					// IDfPersistentObject doc = adminSession.getObject(new
					// DfId(r_object_id_));
					// IDfList allPermissions = ((IDfSysObject) doc).getPermissions();
					// for (int i = 0; i < allPermissions.getCount(); i++) {
					// IDfPermit permit = (IDfPermit) allPermissions.get(i);
					// if
					// (permit.getAccessorName().contentEquals(userSession.getLoginUserName()))
					// {
					// if (permit.getPermitValueInt() < 7) {
					// WsServer.log(loginName, "Needed permit DELETE on version of object
					// with r_object_id: " + r_object_id_);
					//
					// ((IDfSysObject) doc).getACL().setDomain("dm_dbo");
					// ((IDfSysObject) doc).getACL().save();
					//
					// ((IDfSysObject) doc).setACLDomain("dm_dbo");
					// ((IDfSysObject) doc).grant(userSession.getLoginUserName(), 7,
					// null);
					// doc.save();
					// break;
					// }
					// }
					// }
					IDfPersistentObject docPersObject = userSession.getObject(new DfId(r_object_id_));
					docPersObject.destroy();
					String msg = "Destroyed version of object: " + r_object_id_;
					WsServer.log(loginName, msg);
					Logger.getLogger(this.getClass()).info(msg);
				}
				col2.close();
			} else {
				dfDocument.destroy();
			}
			WsServer.log(loginName, "Destroying...Done.");

			userSession.commitTrans();
			Logger.getLogger(this.getClass()).info(String.format("[%s] deleteObject end.", loginName));

		} catch (Exception ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			try {
				userSession.abortTrans();
			} catch (Throwable t) {
				Logger.getLogger(this.getClass()).error(t.getMessage());
			}
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (adminSession != null) {
					AdminServiceImpl.getInstance().releaseSession(adminSession);
				}
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Void deleteObjects(String loginName, String password, List<String> r_object_ids, boolean allVersions) throws ServerException {
		// TODO Auto-generated method stub
		IDfSession userSession = null;
		IDfCollection collection = null;
		Logger.getLogger(this.getClass()).info(String.format("deleteObjects started for user [%s].", loginName));
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			for (String r_object_id : r_object_ids) {
				AdminServiceImpl.beginTransaction(userSession);
				WsServer.log(loginName, "Deleting " + r_object_id + " ...");
				IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));
				if (persObject == null) {
					throw new ServerException("Document doesn't exist, or no permission for document.");
				}

				IDfSysObject dfDocument = (IDfSysObject) persObject;
				Logger.getLogger(this.getClass())
						.info(String.format("Deleting object_name: [%s] r_object_id: [%s]", dfDocument.getObjectName(), dfDocument.getId("r_object_id").getId()));

				if (!allVersions) {
					IDfQuery queryDelete = new DfQuery("delete from dm_dbo.T_DOCMAN_S where r_object_id='" + persObject.getId("r_object_id") + "'");
					IDfCollection coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
					coll.close();
					queryDelete = new DfQuery("delete from dm_dbo.T_DOCMAN_R where r_object_id='" + persObject.getId("r_object_id") + "'");
					coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
					coll.close();
				} else {
					IDfQuery queryAllVersionsOfObject = new DfQuery(
							"select r_object_id from dm_document(all) where i_chronicle_id='" + persObject.getId("i_chronicle_id") + "' order by r_object_id desc");
					IDfCollection coll2 = queryAllVersionsOfObject.execute(userSession, IDfQuery.DF_READ_QUERY);
					while (coll2.next()) {
						String r_object_id_ver = coll2.getString("r_object_id");

						IDfQuery queryDelete = new DfQuery("delete from dm_dbo.T_DOCMAN_S where r_object_id='" + r_object_id_ver + "'");
						IDfCollection coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
						coll.close();
						queryDelete = new DfQuery("delete from dm_dbo.T_DOCMAN_R where r_object_id='" + r_object_id_ver + "'");
						coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
						coll.close();
					}
					coll2.close();
				}

				if (allVersions) {
					dfDocument.destroyAllVersions();
				} else {
					dfDocument.destroy();
				}
				// WsServer.log(loginName, "Commiting transaction ...");
				userSession.commitTrans();
				// WsServer.log(loginName, "Commiting transaction ...Done.");
				String msg = "Deleting " + r_object_id + " ... DONE.";
				WsServer.log(loginName, msg);
				Logger.getLogger(this.getClass()).info(msg);

			}

			Logger.getLogger(this.getClass()).info(String.format("deleteObjects end for user [%s].", loginName));

		} catch (Exception ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<List<String>> dqlLookup(String loginName, String passwordEncrypted, String dql) throws ServerException {
		ArrayList<List<String>> ret = new ArrayList<List<String>>();
		if (dql.equals("")) {
			return ret;
		}
		IDfQuery query = new DfQuery();

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			Pattern pFixedValues = Pattern.compile(".*(fixedValues\\((.+?)\\))", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			Matcher mFixedVal = pFixedValues.matcher(dql);

			if (mFixedVal.find()) {
				if (mFixedVal.group(1) != null) {
					String[] vals = mFixedVal.group(2).split(";");
					for (String val : vals) {
						String[] v = val.split(",");
						ret.add(Arrays.asList(v));
					}
					dql = dql.replace(mFixedVal.group(1), "");
				}
			}

			if (loginName.equals(AdminServiceImpl.superUserName)) {
				userSession = AdminServiceImpl.getAdminSession();
			} else {
				userSession = AdminServiceImpl.getSession(loginName, passwordEncrypted);
			}

			query.setDQL(dql);
			collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);

			int maxRowsReturned = 200;
			int recNo = 0;
			while (collection.next()) {
				ArrayList<String> values = new ArrayList<String>();
				for (int k = 0; k < collection.getAttrCount(); k++) {
					if (collection.getAttr(k).isRepeating()) {
						values.add(collection.getAllRepeatingStrings(collection.getAttr(k).getName(), ","));
					} else {
						values.add(collection.getString(collection.getAttr(k).getName()));
					}
				}
				ret.add(values);
				recNo++;
				if (recNo > maxRowsReturned) {
					break;
				}
			}
			Logger.getLogger(this.getClass()).info("dqlLookup: " + dql + " returned " + ret.size() + " records.");

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public Void setUsersForRoles(String loginName, String password, List<String> r_object_ids, Map<String, List<String>> rolesUsers)
			throws ServerException {

		Logger.getLogger(this.getClass()).info("setUsersForRoles started by user: " + loginName);

		IDfSession userSession = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			for (String r_object_id : r_object_ids) {
				Logger.getLogger(this.getClass()).info("setUsersForRoles started for user: " + loginName + " r_object_id: " + r_object_id);

				AdminServiceImpl.beginTransaction(userSession);

				IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));

				checkDocmanSExist(persObject, userSession, null);
				setUsersForRoles(userSession, persObject, rolesUsers);

				if (userSession.isTransactionActive()) {
					userSession.commitTrans();
				}

				Logger.getLogger(this.getClass()).info("setUsersForRoles completed. objectName: " + persObject.getString("object_name") + " r_object_id: "
						+ persObject.getId("r_object_id").toString());
			}

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(ExplorerServiceImpl.class).error("Error rolling back transaction.");
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			try {
				userSession.abortTrans();
			} catch (Exception ex1) {
				ex1.printStackTrace();
			}
			throw new ServerException(ex.getMessage());
		} finally {
			// try {
			// if (collection != null)
			// collection.close();
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public void setUsersForRoles(IDfSession userSession, IDfPersistentObject persObject, Map<String, List<String>> roleUserGroups) throws Throwable {

		// persObject.save();
		// IDfSession adminSession = AdminServiceImpl.getAdminSession();
		// adminSession.beginTrans();
		IDfSession adminSess = null;
		try {
			String r_object_id = persObject.getString("r_object_id");
			persObject = userSession.getObject(new DfId(r_object_id));
			IDfSysObject sysObj = (IDfSysObject) persObject;

			String deleteDql = "delete from dm_dbo.T_DOCMAN_R where r_object_id='" + r_object_id + "'";
			IDfQuery queryDelete = new DfQuery(deleteDql);
			IDfCollection coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
			coll.close();

			// create new if needed
			adminSess = AdminServiceImpl.getAdminSession();

			String aclNameFromClassSign = findAclNameFromClassSign(persObject.getString("mob_classification_id"));
			IDfACL objAcl = null;
			if (aclNameFromClassSign == null || aclNameFromClassSign.equals("")) {
				if (sysObj.getACL() != null)
					objAcl = (IDfACL) adminSess.getACL(sysObj.getACL().getDomain(), sysObj.getACL().getObjectName());
				if (sysObj.getACL() == null || objAcl.getObjectName().startsWith("mob_") || objAcl.getObjectName().startsWith("dm_")) {// adminSess
					objAcl = (IDfACL) adminSess.newObject("dm_acl");
					objAcl.setObjectName("dis_acl_" + System.currentTimeMillis());
					objAcl.setDomain("dm_dbo");
					objAcl.save();
				}
				objAcl.grant("dm_world", 1, "");
				objAcl.grant("dm_owner", 7, "CHANGE_OWNER,CHANGE_PERMIT");
				objAcl.grant("documentum-admin", 7, "CHANGE_OWNER,CHANGE_PERMIT");

				IDfList permits = null;
				permits = objAcl.getPermissions();

				for (String roleId : roleUserGroups.keySet()) {
					for (String userGroup : roleUserGroups.get(roleId)) {
						IDfQuery queryInsert2 = new DfQuery("insert into dm_dbo.T_DOCMAN_R (r_object_id,object_name,role_id,user_group_name) values('"
								+ persObject.getId("r_object_id") + "','" + ((IDfSysObject) persObject).getObjectName() + "','" + roleId + "','" + userGroup + "')");
						IDfCollection coll2 = queryInsert2.execute(userSession, IDfQuery.DF_EXEC_QUERY);
						coll2.close();

						// remove all permit for this user
						if (permits != null) {
							for (int i = 0; i < permits.getCount(); i++) {
								IDfPermit userPermit = (IDfPermit) permits.get(i);
								if (userPermit.getAccessorName().equals(userGroup)) {
									Logger.getLogger(this.getClass().getName())
											.info("revoking permit for accessor name:" + userPermit.getAccessorName() + " permit: " + userPermit.getPermitType());
									objAcl.revokePermit(userPermit);
								}
							}
						}

						// setAclFor each user in role
						List<Action> actions = getActionsForObject(userSession, userGroup, persObject);
						// Logger.getLogger(this.getClass().getName()).info("user: " +
						// userGroup);
						int maxGrant = 0;
						String allExtPermissions = "";
						for (Action action : actions) {
							if (maxGrant < action.permit.value()) {
								maxGrant = action.permit.value();
							}

							for (ExtendedPermit.extPermit exp : action.extPermits) {
								if (!allExtPermissions.contains(exp.value())) {
									allExtPermissions = allExtPermissions + exp.value() + ",";
								}
							}
						}

						if (allExtPermissions.length() > 0) {
							allExtPermissions = allExtPermissions.substring(0, allExtPermissions.length() - 1);
						}

						IDfUser user = (IDfUser) userSession.getObjectByQualification("dm_user where lower(user_name)='" + userGroup.toLowerCase() + "'");
						if (user != null || userGroup.equals("dm_world") || userGroup.equals("dm_group") || userGroup.equals("dm_owner")) {
							if (user != null)
								objAcl.grant(user.getUserName(), maxGrant, allExtPermissions);
							else
								objAcl.grant(userGroup, maxGrant, allExtPermissions);
							Logger.getLogger(this.getClass().getName()).info("\tgranted: " + maxGrant + " extPermit: " + allExtPermissions + " for " + userGroup);
						} else {
							Logger.getLogger(this.getClass().getName()).info("No such user: " + userGroup);
						}

					}
				}
				objAcl.save();
			} else {
				objAcl = userSession.getACL("dm_dbo", aclNameFromClassSign);
				if (objAcl == null) {
					throw new Exception("Such acl: " + aclNameFromClassSign + " doesnt exist in domain dm_dbo. Not applying it to document.");
				}
				for (String roleId : roleUserGroups.keySet()) {
					for (String userGroup : roleUserGroups.get(roleId)) {
						IDfQuery queryInsert2 = new DfQuery("insert into dm_dbo.T_DOCMAN_R (r_object_id,object_name,role_id,user_group_name) values('"
								+ persObject.getId("r_object_id") + "','" + ((IDfSysObject) persObject).getObjectName() + "','" + roleId + "','" + userGroup + "')");
						IDfCollection coll2 = queryInsert2.execute(userSession, IDfQuery.DF_EXEC_QUERY);
						coll2.close();
					}
				}
			}

			sysObj.fetch(null);
			sysObj.setACLName(objAcl.getObjectName());
			sysObj.setACLDomain(objAcl.getDomain());
			sysObj.save();
			// sysObj.fetch("dm_document");
			int userPermit = sysObj.getPermitEx(userSession.getLoginUserName());
			int userExPermit = sysObj.getXPermit(userSession.getLoginUserName());
			boolean canChangePermit = false;
			if ((userExPermit & 65536) == 65536) {
				canChangePermit = true;
			}

			// if (userSession.isTransactionActive())
			// userSession.commitTrans();
			Logger.getLogger(this.getClass()).info("Acl name:   " + sysObj.getACLName());
			Logger.getLogger(this.getClass()).info("Acl domain: " + sysObj.getACLDomain());

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			WsServer.log(userSession.getLoginUserName(), ex.getMessage());
			throw new ServerException(ex.getMessage());
		} finally {
			if (adminSess != null) {
				adminSess.getSessionManager().release(adminSess);
			}
		}
	}

	private String findAclNameFromClassSign(String sClassSign) throws Exception {
		String acl_name = null;

		Connection con = null;
		PreparedStatement prep = null;
		SQLServerResultSet rs = null;
		try {
			con = BarcodeHandlerImpl.getConnection();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

//@formatter:off					
					String sqlQuery = 
"									 SELECT tc.\"id\"             AS tc_id, " + 
"						       tc.classification_plan_id, " + 
"						       tc.code, " + 
"						       tc.name             AS NAME, " + 
"						       tc.short_name, " + 
"						       tc.retention_type_id, " + 
"						       tc.retention_start_id, " +
"									 tc.acl_name,	" + 				
"						       classPlan.\"version\"       AS \"version\", " + 
"						       classPlan.valid_from, " + 
"						       classPlan.valid_to, " + 
"						       trt.calculate_month AS \"month\" " + 
"						FROM   dbo.T_CLASSIFICATION_PLAN classPlan, " + 
"						       dbo.T_CLASSIFICATION tc, " + 
"						       dbo.T_RETENTION_TYPE trt " + 
"						WHERE   " + 
"							   tc.code = '"+sClassSign+"' " + 
"						       AND tc.classification_plan_id = classPlan.\"id\" " + 
"						       AND trt.\"id\" = tc.retention_type_id " + 
"						       AND classPlan.name = 'KNTS' " + 
"						       AND Sysdatetime() >= classPlan.valid_from " + 
"						       AND ( Sysdatetime() <= classPlan.valid_to " + 
"						              OR ISNULL(classPlan.valid_to,'') = '')";  
//@formatter:on

			prep = (PreparedStatement) con.prepareStatement(sqlQuery);
			rs = (SQLServerResultSet) prep.executeQuery();

			if (rs.next()) {
				acl_name = rs.getString("acl_name");
				Logger.getLogger(this.getClass()).info("Found acl_name from T_CLASSIFICATION table. acl_name: " + acl_name);
			}
		} catch (Exception ex) {
			throw ex;

		} finally {
			try {
				// rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				prep.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return acl_name;
	}

	@Override
	public List<String[]> getValuesFromRest(String loginName, String password, String likeString, String restUrl) throws ServerException {
		// TODO Auto-generated method stub
		List<String[]> ret = new ArrayList<String[]>();
		try {
			ServletContext ctx = this.getServletContext();

			String hostname = this.getThreadLocalRequest().getLocalName();

			Client client = ClientBuilder.newClient();
			String uri = client.target(restUrl).getUri().toString();

			int port = this.getThreadLocalRequest().getLocalPort();
			String strUrl = "";
			if (restUrl.contains("http:")) {
				strUrl = restUrl + URLEncoder.encode(likeString, "UTF-8");
			} else {
				if (!restUrl.startsWith("/")) {
					restUrl = "/" + restUrl;
				}

				String prot = this.getThreadLocalRequest().isSecure() ? "https" : "http";
				String host = (hostname != null ? hostname : "127.0.0.1");
				strUrl = getServerBase(this.getThreadLocalRequest()) + restUrl + URLEncoder.encode(likeString, "UTF-8");
			}

			HttpURLConnection conn;
			Logger.getLogger(this.getClass()).info("rest url: " + strUrl);

			URL url;
			url = new URL(strUrl);
			Logger.getLogger(this.getClass()).info("Rest url: " + strUrl);
			String stUrl = URLEncoder.encode(strUrl, StandardCharsets.UTF_8.toString());
			// partOfProcessMilisetoneName
			if (this.getThreadLocalRequest().isSecure()) {

				conn = (HttpsURLConnection) url.openConnection();
				((HttpsURLConnection) conn).setHostnameVerifier(new HostnameVerifier() {

					@Override
					public boolean verify(String hostname, SSLSession session) {
						// TODO Auto-generated method stub
						return true;
					}
				});

			} else {
				conn = (HttpURLConnection) url.openConnection();
			}

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-length", "0");
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(10000);

			byte[] decodedBytes = Base64.getDecoder().decode(password.getBytes());
			String decodedPass = new String(decodedBytes);

			String auth = loginName + ":" + decodedPass;
			byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));

			String authHeaderValue = "Basic " + new String(encodedAuth);
			conn.setRequestProperty("Authorization", authHeaderValue);
			conn.connect();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				JSONArray jsonarray = new JSONArray(output);
				java.util.Iterator<Object> it = jsonarray.iterator();
				while (it.hasNext()) {
					String line = (String) it.next();
					ret.add(line.split("\\|"));
				}
			}
			conn.disconnect();

		} catch (Exception ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			throw new ServerException(ex.getMessage());
		}

		return ret;
	}

	public static String getServerBase(HttpServletRequest req) {
		String scheme = req.getScheme(); // http
		String serverName = req.getServerName(); // sub.domain.ac.uk
		int serverPort = req.getServerPort(); // 80
		String contextPath = req.getContextPath(); // /MyApp

		return scheme + "://" + serverName + ":" + serverPort + contextPath;
	}

	@Override
	public Void checkout(String loginName, String password, String r_object_id) throws ServerException {
		IDfSession userSession = null;
		IDfCollection collection = null;
		Logger.getLogger(this.getClass()).info(String.format("[%s] checkout started for %s", loginName, r_object_id));
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));
			if (persObject == null) {
				throw new ServerException("Document doesn't exist, or no permission for document.");
			}

			IDfSysObject dfDocument = (IDfSysObject) persObject;
			Logger.getLogger(this.getClass()).info(String.format("object_name: [%s]", dfDocument.getObjectName()));
			dfDocument.checkout();

			Logger.getLogger(this.getClass()).info(String.format("[%s] checkout end.", loginName));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Document> versions(String loginName, String password, String orig_r_object_id) throws ServerException {
		ArrayList<Document> ret = new ArrayList<Document>();
		IDfQuery query = new DfQuery();

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			Logger.getLogger(this.getClass()).info("versions for " + loginName + " for: " + orig_r_object_id);

			userSession = AdminServiceImpl.getSession(loginName, password);

			String dql = "select r_object_id, object_name, subject, title, r_lock_owner, r_version_label, a_content_type, r_content_size, r_modify_date,r_creator_name,r_modifier from dm_document(all) where i_chronicle_id in (select i_chronicle_id from dm_document(all)"
					+ " where r_object_id='" + orig_r_object_id + "') order by r_object_id desc";

			query.setDQL(dql);
			Logger.getLogger(this.getClass()).info("Started dql query: " + dql);
			long milis1 = System.currentTimeMillis();
			collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);
			long milis2 = System.currentTimeMillis();
			Logger.getLogger(this.getClass()).info("Ended in: " + (int) ((milis2 - milis1) / 1000) + "s");
			int prevLogOutputDuration = 0;
			while (collection.next()) {
				String r_object_id = collection.getId("r_object_id").toString();
				IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));

				Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(persObj, loginName, userSession);
				Profile prof = (Profile) profileAndRolesOfUserAndState[1];
				ArrayList<String> rolesOfUser = (ArrayList<String>) profileAndRolesOfUserAndState[2];
				String stateId = (String) profileAndRolesOfUserAndState[3];

				if (prof == null) {
					prof = findProfileForObjectType(persObj);
				}

				Document doc = new Document();
				try {
					IDfSysObject sysObj = ((IDfSysObject) persObj);
					doc.object_name = sysObj.getObjectName();

					doc.title = sysObj.getTitle();
					doc.subject = sysObj.getSubject();
					doc.r_creation_date = Date.from(sysObj.getCreationDate().getDate().toInstant());
					doc.r_modify_date = Date.from(sysObj.getModifyDate().getDate().toInstant());
					doc.creator = sysObj.getCreatorName();
					doc.modifier = sysObj.getModifier();
					doc.owner = sysObj.getOwnerName();
					doc.lockOwner = sysObj.getLockOwner();
					doc.lockMachine = sysObj.getLockMachine();
					if (sysObj.getFormat() != null) {
						doc.format = sysObj.getFormat().getName();
					} else {
						doc.format = "";
					}

					doc.details = new ArrayList<String>();
					if (prof != null) {
						if (prof.detailAttributes != null) {
							for (Attribute att : prof.detailAttributes) {
								doc.details.add(sysObj.getValue(att.dcmtAttName).toString());
							}
						}
					}

					doc.r_version_label = sysObj.getAllRepeatingStrings("r_version_label", ",");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				doc.r_object_id = persObj.getId("r_object_id").toString();
				doc.type_name = persObj.getType().getName();

				ret.add(doc);

				if (ret.size() % 10 == 0) {
					WsServer.log(loginName, "Added " + ret.size() + " version record to result.");
				}

			}

		} catch (Throwable ex) {
			// ex.printStackTrace();

			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	public void promote(IDfSession userSession, String r_object_id) throws ServerException {
		try {
			IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));

			Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(persObj, userSession.getLoginUserName(), userSession);

			// Profile dfPersObj = (Profile) profileAndRolesOfUserAndState[0];
			Profile prof = (Profile) profileAndRolesOfUserAndState[1];

			if (prof == null)
				throw new ServerException("No profile defined on object: r_object_id: " + r_object_id + " object_name: " + persObj.getString("object_name")
						+ ". Possibly unclassified object.");

			String stateId = (String) profileAndRolesOfUserAndState[3];
			Logger.getLogger(this.getClass()).info("Promote from state: " + stateId);

			int stateInd = AdminServiceImpl.getStateIndex(prof, stateId);
			String nextStateId = AdminServiceImpl.getNextStateId(prof, stateInd);
			if (nextStateId != null) {

				userSession.beginTrans();
				moveToState(userSession, r_object_id, nextStateId, profileAndRolesOfUserAndState);
				userSession.commitTrans();
			} else {
				throw new ServerException("Cannot promote, already last state.");
			}
		} catch (Throwable ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
		}

	}

	@Override
	public Document promote(String loginName, String password, String r_object_id) throws ServerException {
		Document doc = null;
		IDfSession userSession = null;
		try {
			Logger.getLogger(this.getClass()).info("Promote for " + loginName + " for: " + r_object_id);

			userSession = AdminServiceImpl.getSession(loginName, password);
			promote(userSession, r_object_id);

			IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));
			doc = docFromSysObject(persObj, userSession.getLoginUserName(), userSession);

		} catch (Throwable ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return doc;
	}

	public Void moveToState(String r_object_id, String stateId, Object[] profileAndRolesOfUserAndState, IDfSession userSession) throws ServerException {
		return moveToState(userSession, r_object_id, stateId, profileAndRolesOfUserAndState);
	}

	public Void moveToState(String loginName, String password, String r_object_id, String stateId, Object[] profileAndRolesOfUserAndState)
			throws ServerException {
		IDfSession userSession;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			return moveToState(userSession, r_object_id, stateId, profileAndRolesOfUserAndState);
		} catch (ServerException e) {
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e);
			Logger.getLogger(this.getClass()).error(e.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServerException(e.getMessage());
		}
	}

	public Void moveToState(IDfSession userSession, String r_object_id, String stateId, Object[] profileAndRolesOfUserAndState) throws ServerException {
		IDfQuery query = new DfQuery();

		IDfCollection collection = null;
		try {
			Logger.getLogger(this.getClass())
					.info("moveToState for triggered by user: " + userSession.getLoginInfo().getUser() + " for: " + r_object_id + " toState: " + stateId);

			boolean startedTransaction = false;
			if (!userSession.isTransactionActive()) {
				AdminServiceImpl.beginTransaction(userSession);
				startedTransaction = true;
			}

			IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));

			Profile prof = (Profile) profileAndRolesOfUserAndState[1];

			boolean foundState = false;
			int stateNo = 0;
			for (State state : prof.states) {
				if (state.getId().equals(stateId)) {
					foundState = true;
					break;
				}
				stateNo++;
			}

			if (!foundState) {
				throw new ServerException("No such state: " + stateId + " in profile: " + prof.id);
			}

			ArrayList<String> rolesOfUser = (ArrayList<String>) profileAndRolesOfUserAndState[2];
			String currentStateId = (String) profileAndRolesOfUserAndState[3];

			if (!currentStateId.equals(stateId)) {

				boolean shouldSupersede;
				if (stateId.equals("effective") && !currentStateId.equals("archive")) {
					shouldSupersede = true;
				} else {
					shouldSupersede = false;
				}

				int stateInd = AdminServiceImpl.getStateIndex(prof, stateId);

				// check all needed attributes are set
				String mandatoryAttNamesNotSet = "";
				boolean allMandatoryFilled = true;
				IDfSysObject dfDocument = (IDfSysObject) persObj;
				for (Role rol : prof.roles) {
					AttributeRoleStateWizards arsw = AdminServiceImpl.getAttributesForStateAndRole(prof, stateId, rol.getId());
					if (arsw != null) {
						for (Attribute att : arsw.attributes) {
							if (att.isMandatory) {
								if (dfDocument.getValue(att.dcmtAttName).asString().contentEquals("")) {
									WsServer.log(userSession.getLoginInfo().getUser(), att.dcmtAttName + " is not set.");
									allMandatoryFilled = false;
									if (!mandatoryAttNamesNotSet.contains(att.dcmtAttName)) {
										mandatoryAttNamesNotSet = mandatoryAttNamesNotSet + att.dcmtAttName + ",";
									}
								}
							}
						}
					}
				}
				if (mandatoryAttNamesNotSet.length() > 0) {
					mandatoryAttNamesNotSet = mandatoryAttNamesNotSet.substring(0, mandatoryAttNamesNotSet.length() - 1);
				}

				if (!allMandatoryFilled) {
					String object_name = persObj.getString("object_name");
					String relNo = persObj.hasAttr("mob_releaseno") ? persObj.getString("mob_releaseno") : "";
					throw new ServerException("Obvezni atributi za r_object_id: " + persObj.getId("r_object_id").toString() + " object_name: " + object_name
							+ " mob_release_no: " + relNo + " (" + mandatoryAttNamesNotSet + ") niso nastavljeni.");
				}

				String updateTDocmanS = "update dm_dbo.T_DOCMAN_S set current_state_id='" + prof.states.get(stateNo).getId() + "' where r_object_id='"
						+ r_object_id + "'";
				Logger.getLogger(this.getClass()).info("to update t_docman_s: " + updateTDocmanS);
				query.setDQL(updateTDocmanS);
				collection = query.execute(userSession, IDfQuery.DF_EXEC_QUERY);
				if (collection.next()) {
					int rowsUpdated = collection.getInt("rows_updated");
					Logger.getLogger(this.getClass())
							.info("updated " + rowsUpdated + " records - state on r_object_id: " + r_object_id + " to: " + prof.states.get(stateNo).getId());
				}

				AdminServiceImpl.runStandardActions(persObj, prof.states.get(stateNo).getId(), userSession);

				if (shouldSupersede) {
					supersede(persObj, userSession);
				}

				if (startedTransaction) {
					userSession.commitTrans();
				}
			}
		} catch (Throwable ex) {
			// ex.printStackTrace();

			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);

			try {
				if (userSession.isTransactionActive()) {
					userSession.abortTrans();
				}
			} catch (DfException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	private void supersede(IDfPersistentObject persObj, IDfSession userSession) throws Throwable {
		// IDfSession adminSess = AdminServiceImpl.getAdminSession();
		int count = persObj.getValueCount("mob_supersedes");
		for (int j = 0; j < count; j++) {
			String objectNameToSupersede = persObj.getRepeatingString("mob_supersedes", j);
			IDfSysObject objToSupersede = null;

			String objectName = null;
			int mobReleaseNo;

			if (objectNameToSupersede.contains("/")) {
				objectName = objectNameToSupersede.split("/")[0];
				mobReleaseNo = Integer.valueOf(objectNameToSupersede.split("/")[1]);
			} else {
				objectName = objectNameToSupersede.split("/")[0];
				mobReleaseNo = persObj.getInt("mob_releaseno") - 1;
			}
			objToSupersede = (IDfSysObject) userSession
					.getObjectByQualification("mob_document where object_name='" + objectName + "' and mob_releaseno=" + mobReleaseNo);
			if (objToSupersede == null) {
				objToSupersede = (IDfSysObject) userSession
						.getObjectByQualification("mob_form_template where object_name='" + objectName + "' and mob_releaseno=" + mobReleaseNo);
			}

			if (objToSupersede != null) {
				if (!objToSupersede.getId("r_object_id").equals(persObj.getId("r_object_id"))) {
					Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(objToSupersede, userSession.getLoginInfo().getUser(), userSession);
					moveToState(userSession, objToSupersede.getId("r_object_id").toString(), "archive", profileAndRolesOfUserAndState);
				} else {
					Logger.getLogger(this.getClass()).warn("Object is same.");
				}
			} else {
				Logger.getLogger(this.getClass()).info("Could not get objectNameToSupersede.");
			}
		}

	}

	@Override
	public String apiDump(String loginName, String password, String r_object_id) throws ServerException {

		String result;
		IDfSession userSession = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			Logger.getLogger(this.getClass()).info("Apidump triggered from user: " + loginName + " for object r_object_id: " + r_object_id);

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);
			userSession.beginTrans();
			IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));

			result = persObj.dump();
			result = result.replace("\n", "<br>");

			Logger.getLogger(this.getClass()).info("Apidump for " + loginName + " for: " + r_object_id + " done.");

		} catch (Throwable ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Document demote(String loginName, String password, String r_object_id) throws ServerException {

		Document doc;
		IDfSession userSession = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			Logger.getLogger(this.getClass()).info("Demote triggered rom user: " + loginName + " for object r_object_id: " + r_object_id);

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);
			userSession.beginTrans();
			IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));

			Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(persObj, loginName, userSession);
			Profile prof = (Profile) profileAndRolesOfUserAndState[1];
			String stateId = (String) profileAndRolesOfUserAndState[3];
			Logger.getLogger(this.getClass()).info("Demote from state: " + stateId);

			int stateInd = AdminServiceImpl.getStateIndex(prof, stateId);
			String prevStateId = AdminServiceImpl.getPrevStateId(prof, stateInd);
			if (prevStateId != null) {
				moveToState(userSession, r_object_id, prevStateId, profileAndRolesOfUserAndState);
			} else {
				throw new ServerException("Cannot demote, already first state.");
			}
			userSession.commitTrans();

			doc = docFromSysObject(persObj, loginName, userSession);

			Logger.getLogger(this.getClass()).info("Demote for " + loginName + " for: " + r_object_id + " done.");

		} catch (Throwable ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return doc;
	}

	@Override
	public Void cancelCheckout(String loginName, String password, String r_object_id) throws ServerException {
		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			Logger.getLogger(this.getClass()).info("CancelCheckout for " + loginName + " for: " + r_object_id);

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));
			if (((IDfSysObject) persObj).isCheckedOut()) {
				((IDfSysObject) persObj).cancelCheckout();
			}
			Logger.getLogger(this.getClass()).info("CancelCheckout for " + loginName + " for: " + r_object_id + " completed.");
		} catch (Exception ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Void unlock(String loginName, String password, String r_object_id) throws ServerException {
		IDfQuery query = new DfQuery();

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			Logger.getLogger(this.getClass()).info("Unlock for " + loginName + " for: " + r_object_id);

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);

			query.setDQL("update dm_document object set r_lock_owner='', set r_lock_machine='', set r_lock_date=date('nulldate') where r_object_id='"
					+ r_object_id + "'");
			IDfCollection col = query.execute(userSession, IDfQuery.DF_EXEC_QUERY);
			col.close();

			Logger.getLogger(this.getClass()).info("Unlock for " + loginName + " for: " + r_object_id + " completed.");
		} catch (Exception ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<List<String>> auditTrail(String loginName, String password, String orig_r_object_id, String eventFilter, int start, int end)
			throws ServerException {
		List<List<String>> ret = new ArrayList<List<String>>();
		IDfQuery query = new DfQuery();

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			String msg = "AuditTrail for " + loginName + " for: " + orig_r_object_id + " from: " + start + " to: " + end;
			Logger.getLogger(this.getClass()).info(msg);
			WsServer.log(loginName, msg);

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);

			// String dql = "select audited_obj_id, time_stamp, time_stamp_utc,
			// event_name, event_description, user_name,
			// string_1,string_2,attribute_list,attribute_list_old from
			// dm_audittrail_Mobitel_all where (audited_obj_id in (select r_object_id
			// from dm_document(all) where i_chronicle_id in "
			// + " (select i_chronicle_id from dm_document where r_object_id = '" +
			// orig_r_object_id + "'))) ENABLE(RETURN_RANGE " + start + " " + end
			// + " 'time_stamp_utc DESC, audited_obj_id DESC')";
			start = start + 1;
			end = end + 1;

			String dql;
			// if (eventFilter == null || eventFilter.equals("")) {
			// dql = "select r_object_id, audited_obj_id, time_stamp, time_stamp_utc,
			// event_name, event_description, user_name,
			// string_1,string_2,attribute_list,attribute_list_old from
			// dm_audittrail_Mobitel_all where (audited_obj_id in (select r_object_id
			// from dm_document(all) where i_chronicle_id in "
			// + " (select i_chronicle_id from dm_document where r_object_id = '" +
			// orig_r_object_id + "'))) ENABLE(RETURN_RANGE " + start + " " + end
			// + " 'r_object_id DESC')";
			// } else {
			// dql = "select r_object_id, audited_obj_id, time_stamp, time_stamp_utc,
			// event_name, event_description, user_name,
			// string_1,string_2,attribute_list,attribute_list_old from
			// dm_audittrail_Mobitel_all where "
			// + " event_name = '" + eventFilter + "' and " + " (audited_obj_id in
			// (select r_object_id from dm_document(all) where i_chronicle_id in "
			// + " (select i_chronicle_id from dm_document where r_object_id = '" +
			// orig_r_object_id + "'))) ENABLE(RETURN_RANGE " + start + " " + end
			// + " 'r_object_id DESC')";
			// }

			if (eventFilter == null || eventFilter.equals("")) {
				dql = "select r_object_id, audited_obj_id, version_label, time_stamp, time_stamp_utc, event_name, event_description, user_name, string_1,string_2,attribute_list,attribute_list_old from dm_audittrail_Mobitel_all where audited_obj_id  = '"
						+ orig_r_object_id + "' " + " ENABLE(RETURN_RANGE " + start + " " + end + " 'r_object_id DESC')";
			} else {
				dql = "select r_object_id, audited_obj_id, version_label, time_stamp, time_stamp_utc, event_name, event_description, user_name, string_1,string_2,attribute_list,attribute_list_old from dm_audittrail_Mobitel_all where audited_obj_id = '"
						+ orig_r_object_id + "' and" + " event_name = '" + eventFilter + "' ENABLE(RETURN_RANGE " + start + " " + end + " 'r_object_id DESC')";
			}

			query.setDQL(dql);
			query.setBatchSize(Integer.MAX_VALUE);
			Logger.getLogger(this.getClass()).info("Started dql query: " + dql);
			long milis1 = System.currentTimeMillis();
			collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);
			long milis2 = System.currentTimeMillis();
			Logger.getLogger(this.getClass()).info("Ended in: " + (int) ((milis2 - milis1) / 1000) + "s");
			while (collection.next()) {
				// IDfPersistentObject auditedObj = userSession
				// .getObjectByQualification("dm_document(all) where r_object_id='" +
				// collection.getValue("audited_obj_id") + "' enable (return_top 1)");
				// String rVersionLabels =
				// auditedObj.getAllRepeatingStrings("r_version_label", ",");
				// String aclName = auditedObj.getString("acl_name");
				ArrayList<String> row = new ArrayList<String>();
				for (int i = 0; i < collection.getAttrCount(); i++) {
					// if (i == 2) {
					// row.add(rVersionLabels);
					// }
					row.add(collection.getValue(collection.getAttr(i).getName()).asString());
				}
				if (ret.size() % 10 == 0) {
					WsServer.log(loginName, "Added " + ret.size() + " audittrail records to result.");
				}
				ret.add(row);
				// Logger.getLogger(ExplorerServiceImpl.class)
				// .info("added audit trail row for audited_obj_id: " +
				// collection.getValue("audited_obj_id").asString());
			}
			WsServer.log(loginName, "audittrail records returned: " + ret.size() + " - from: " + start + " to:" + end);
			Logger.getLogger(this.getClass()).info("rows: " + ret.size());
		} catch (Exception ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	private boolean tryToSet7BitEncoding(MAPIMessage msg, String charsetName) {
		if (charsetName == null) {
			return false;
		}

		if (charsetName.equalsIgnoreCase("utf-8")) {
			return false;
		}
		try {
			if (Charset.isSupported(charsetName)) {
				msg.set7BitEncoding(charsetName);
				return true;
			}
		} catch (IllegalCharsetNameException | UnsupportedCharsetException e) {
			// swallow
		}
		return false;
	}

	@Override
	public Void newFolder(String loginName, String password, String folderName, String parentFolderPath) throws ServerException {
		IDfSession userSession = null;
		try {
			Logger.getLogger(this.getClass()).info("NewFolder started for " + loginName + " for: " + parentFolderPath);

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfFolder dfFolder = (IDfFolder) userSession.newObject("dm_folder");
			dfFolder.setObjectName(folderName);
			dfFolder.link(parentFolderPath);
			dfFolder.setACLName("dcmtservice_folder");
			dfFolder.setACLDomain("dm_dbo");

			dfFolder.save();
		} catch (Exception ex) {
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public Logger getLogger() {
		return Logger.getLogger(this.getClass());
	}

	@Override
	public Void addVersionLabel(String loginName, String password, List<String> r_object_ids, String labelVersion) throws ServerException {
		getLogger().info("Starting addVersionLabel by user: " + loginName);

		IDfSession userSession = null;

		try {

			userSession = AdminServiceImpl.getSession(loginName, password);

			for (String r_object_id : r_object_ids) {
				getLogger().info("Starting addVersionLabel for user " + loginName + " r_object_id: " + r_object_id);

				IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));
				if (persObject == null) {
					throw new ServerException("Object specified by rObjectId=" + r_object_id + " doesn't exist.");
				}

				IDfDocument dfDocument = (IDfDocument) persObject;

				boolean ok = false;
				for (int i = 0; i < dfDocument.getVersionLabelCount(); i++) {
					if (dfDocument.getVersionLabel(i).equals("CURRENT")) {
						ok = true;
					}
				}

				if (ok) {
					dfDocument.mark(labelVersion);
					dfDocument.save();
				} else {
					throw new Exception("Object is not current, new version exists. Check SHOW Versions.");
				}

			}

		} catch (Exception ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);

			ex.printStackTrace(pw);
			String errorStr = "Error while doing addVersionLabel, stack trace follows:\n" + errorStringWriter.getBuffer().toString();
			getLogger().error(errorStr);
			throw new ServerException("Error while doing addVersionLabel: " + errorStr);
		} finally {
			AdminServiceImpl.getInstance().releaseSession(userSession);

		}
		getLogger().info("addVersionLabel completed.");
		return null;
	}

	@Override
	public Void removeVersionLabel(String loginName, String password, List<String> rObjectIds, String labelVersion) throws ServerException {
		// TODO Auto-generated method stub
		getLogger().info("Starting removeVersionLabel by user " + loginName);

		IDfSession userSession = null;

		try {

			userSession = AdminServiceImpl.getSession(loginName, password);

			for (String rObjectId : rObjectIds) {
				getLogger().info("Starting removeVersionLabel for user " + loginName + " r_object_id: " + rObjectId);

				IDfPersistentObject persObject = userSession.getObject(new DfId(rObjectId));
				if (persObject == null) {
					throw new ServerException("Object specified by rObjectId=" + rObjectId + " doesn't exist.");
				}

				IDfDocument dfDocument = (IDfDocument) persObject;

				boolean ok = false;
				for (int i = 0; i < dfDocument.getVersionLabelCount(); i++) {
					if (dfDocument.getVersionLabel(i).equals("CURRENT")) {
						ok = true;
					}
				}

				if (ok) {
					dfDocument.unmark(labelVersion);
					dfDocument.save();
				} else {
					throw new ServerException("Object is not current, new version exists. Check SHOW Versions.");
				}

			}

		} catch (Exception ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);

			ex.printStackTrace(pw);
			String errMsg = "Error while doing removeVersionLabel, stack trace follows:\n" + errorStringWriter.getBuffer().toString();
			getLogger().error(errMsg);
			throw new ServerException(errMsg);
		} finally {
			AdminServiceImpl.getInstance().releaseSession(userSession);

		}
		getLogger().info("removeVersionLabel completed.");
		return null;
	}

	@Override
	public synchronized Document importDocument(String loginName, String password, String folderRObjectId, String profileId, String stateId,
			Map<String, List<String>> attributes, Map<String, List<String>> rolesUsers, byte[] base64Content, String format) throws ServerException {

		Logger.getLogger(this.getClass()).info("ImportDocument started.");

		Document doc = null;
		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);
			AdminServiceImpl.beginTransaction(userSession);

			Profile prof = AdminServiceImpl.profiles.get(profileId);
			if (prof == null)
				throw new ServerException("No such profile: " + profileId);

			IDfPersistentObject persObject = userSession.newObject(prof.objType);

			// DocType docType = AdminServiceImpl.doctypes.get(prof.objType);
			HashMap<String, Attribute> wizardAttributes = AdminServiceImpl.getInstance().getWizardAttributes(prof, "import");
			if (wizardAttributes == null) {
				throw new Exception("No import wizard defined");
			}

			for (String attName : attributes.keySet()) {
				Logger.getLogger(this.getClass()).info("Updating attribute: " + attName);
				Attribute att = wizardAttributes.get(attName);

				if (att == null) {
					throw new ServerException("No such attribute: " + attName + " in profile: " + prof.id);
				}

				DcmtAttribute dcmtAttribute = AdminServiceImpl.getInstance().findAttribute(prof.objType, attName);

				if (dcmtAttribute == null) {
					throw new Exception("No such attribute: " + attName + " on type: " + prof.objType);
				}

				List<String> values = attributes.get(attName);

				if (!att.isReadOnly) {
					if (values.size() > 1) {
						int i = 0;
						for (String value : values) {
							if (att.getType().equals("dropdown")) {
								IDfValue val = new DfValue(value.split("¨")[att.dropDownCol]);
								persObject.setRepeatingValue(attName, i, val);
							} else {
								IDfValue val = new DfValue(value);
								persObject.setValue(attName, val);
							}
							i++;
						}
					} else if (values.size() == 1) {
						if (!values.get(0).equals("")) {
							if (dcmtAttribute.domain_type.equals("4")) {
								IDfTime time = null;
								if (values.get(0).contains(".")) {
									time = new DfTime(sdf.parse(values.get(0)));
								} else {
									String miliSeconds = values.get(0);
									GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
									cal.setTimeInMillis(Long.valueOf(miliSeconds));
									time = new DfTime(cal.getTime());
								}
								persObject.setTime(attName, time);
							} else {
								if (att.getType().equals("dropdown")) {
									IDfValue val = new DfValue(values.get(0).split("¨")[att.dropDownCol]);
									persObject.setValue(attName, val);
								} else {
									IDfValue val = new DfValue(values.get(0));
									persObject.setValue(attName, val);
								}
							}

						}
					}
				}

				if (att.defaultValueIsCalculatedOnServer) {
					if (att.defaultValueIsDql) {
						// att.defaultValue
						String value = ExplorerServiceImpl.getInstance().dqlLookup(loginName, password, att.defaultValue).get(0).get(0);
						IDfValue val = new DfValue(value);
						persObject.setValue(attName, val);
					}
				}

			}

			if (prof.namePolicyBarcodeType > 0) {
				GregorianCalendar gcal = new GregorianCalendar();
				gcal.setTime(new Date());

				String barcode = AdminServiceImpl.getBarcode(prof.namePolicyBarcodeType, "0", "9", "10", gcal, 1, "DisTelekom")[0];

				persObject.setString("object_name", barcode);
				if (persObject.hasAttr("mob_barcode")) {
					persObject.setString("mob_barcode", barcode);
				}
			}

			try {

				if (stateId == null) {
					for (int j = 0; j < prof.states.size(); j++) {
						if (!prof.states.get(j).getId().equals("unclassified")) {
							stateId = prof.states.get(j).getId();
							break;
						}
					}
				}
				persObject.save();
				setStateForObject(userSession, persObject, prof, stateId);
				setUsersForRoles(userSession, persObject, rolesUsers);
				persObject.fetch("dm_document");

				AdminServiceImpl.runStandardActions(persObject, stateId, userSession);
				persObject.fetch("dm_document");

				// check if passed object is folder and if it is link it to this folder
				if (folderRObjectId != null) {
					Logger.getLogger(ExplorerServiceImpl.class).info("Finding folder for r_object_id='" + folderRObjectId + "'");
					IDfFolder folder = (IDfFolder) userSession.getObjectByQualification("dm_folder where r_object_id='" + folderRObjectId + "'");
					if (folder != null) {

						// unlink all links made previously in standard actions
						IDfSysObject dfSysObject = ((IDfSysObject) persObject);
						for (int i = 0; i < dfSysObject.getFolderIdCount(); i++) {
							IDfFolder dfFold = (IDfFolder) userSession.getObject(dfSysObject.getFolderId(i));
							String folderPath = dfFold.getAllRepeatingStrings("r_folder_path", "/");
							dfSysObject.unlink(folderPath);
						}
						String folderPath = folder.getString("r_folder_path");
						Logger.getLogger(ExplorerServiceImpl.class).info("Found folder with folderPath " + folderPath + "'");

						Logger.getLogger(ExplorerServiceImpl.class).info("Linking...'");
						((IDfSysObject) persObject).link(folderPath);
						Logger.getLogger(ExplorerServiceImpl.class).info("Linking...done.'");
					}
				}
			} catch (Throwable ex) {
				Logger.getLogger(this.getClass()).error(ex);
			}

			ByteArrayOutputStream baOs = new ByteArrayOutputStream();
			baOs.write(Base64.getDecoder().decode(base64Content));

			IDfSysObject destObject = (IDfSysObject) persObject;
			destObject.setContentEx(baOs, format, 0);
			destObject.setContentType(format);

			persObject.fetch("dm_document");
			persObject.save();

			doc = docFromSysObject(persObject, userSession.getLoginUserName(), userSession);

			if (userSession.isTransactionActive()) {
				userSession.commitTrans();
			}

			String msg = "ImportDocument completed. objectName: <strong>" + persObject.getString("object_name") + "</strong> r_object_id: <strong>"
					+ destObject.getId("r_object_id") + "</strong>";
			Logger.getLogger(this.getClass()).info(msg);
			WsServer.log(loginName, msg);

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			WsServer.log(loginName, ex.getMessage());
			try {
				userSession.abortTrans();
			} catch (Exception ex1) {
				Logger.getLogger(ExplorerServiceImpl.class).error("Error rolling back transaction.");
			}

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (userSession != null) {
				AdminServiceImpl.getInstance().releaseSession(userSession);
			}
		}
		return doc;
	}

	@Override
	public Document newDocument(String loginName, String password, String profileId, Map<String, List<String>> attributes,
			Map<String, List<String>> rolesUsers, String rObjectIdOfObjectOrFolder) throws ServerException {
		Logger.getLogger(this.getClass()).info("NewDocument started for " + loginName);

		Document doc = null;
		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}
			if (rObjectIdOfObjectOrFolder.equals("") || rObjectIdOfObjectOrFolder == null) {
				throw new ServerException("Teplate not specified '" + rObjectIdOfObjectOrFolder + "'");
			}
			Profile prof = AdminServiceImpl.profiles.get(profileId);
			if (prof == null) {
				throw new ServerException("No such profile: " + profileId);
			}

			userSession = AdminServiceImpl.getSession(loginName, password);
			AdminServiceImpl.beginTransaction(userSession);

			String dqlOfObjects = "";
			if (!rObjectIdOfObjectOrFolder.startsWith("/")) {
				dqlOfObjects = "select r_object_id from dm_document(all) where r_object_id='" + rObjectIdOfObjectOrFolder + "'";
				IDfPersistentObject obj = userSession.getObjectByQualification("dm_document(all) where r_object_id='" + rObjectIdOfObjectOrFolder + "'");
				if (obj == null) {

					Logger.getLogger(getClass()).warn("Try to find object with object_name: " + rObjectIdOfObjectOrFolder);
					dqlOfObjects = "select r_object_id from dm_document where object_name='" + rObjectIdOfObjectOrFolder + "'";
					IDfPersistentObject obj1 = userSession.getObjectByQualification("dm_document where object_name='" + rObjectIdOfObjectOrFolder + "'");
					if (obj1 == null) {
						throw new Exception(
								"No such template (r_object_id='" + rObjectIdOfObjectOrFolder + "') or (object_name='" + rObjectIdOfObjectOrFolder + "')");
					}
					rObjectIdOfObjectOrFolder = obj1.getId("r_object_id").toString();

				}
			} else {
				dqlOfObjects = "select r_object_id from dm_document where folder('" + rObjectIdOfObjectOrFolder + "')";
			}

			IDfQuery query = new DfQuery();
			query.setDQL(dqlOfObjects);
			long milis1 = System.currentTimeMillis();
			collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);

			while (collection.next()) {
				long milis2 = System.currentTimeMillis();

				IDfId dfId = new DfId(rObjectIdOfObjectOrFolder);
				IDfPersistentObject objTemplate = (IDfPersistentObject) userSession.getObject(dfId);
				String msg = "Copying: " + objTemplate.getString("title");
				Logger.getLogger(this.getClass()).info("\t" + msg);
				WsServer.log(loginName, msg);

				IDfPersistentObject persObject = userSession.newObject(prof.objType);
				HashMap<String, Attribute> wizardAttributes = AdminServiceImpl.getInstance().getWizardAttributes(prof, "newdoc");
				for (String attName : attributes.keySet()) {
					Logger.getLogger(this.getClass()).info("\t\tUpdating attribute: " + attName);
					Attribute att = wizardAttributes.get(attName);
					DcmtAttribute dcmtAttribute = AdminServiceImpl.getInstance().findAttribute(prof.objType, attName);
					List<String> values = attributes.get(attName);

					if (!att.isReadOnly) {
						if (values.size() > 1) {
							int i = 0;
							for (String value : values) {
								if (!value.equals("")) {
									IDfValue val = new DfValue(value);
									persObject.setRepeatingValue(attName, i, val);
								}
							}
						} else if (values.size() == 1) {
							if (!values.get(0).equals("")) {
								if (dcmtAttribute.domain_type.equals("4")) {
									IDfTime time = null;
									if (values.get(0).contains(".")) {
										time = new DfTime(sdf.parse(values.get(0)));
									} else {
										String miliSeconds = values.get(0);
										GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
										cal.setTimeInMillis(Long.valueOf(miliSeconds));
										time = new DfTime(cal.getTime());
									}
									persObject.setTime(attName, time);
								} else {
									IDfValue val = new DfValue(values.get(0).split("\\|")[0]);
									persObject.setValue(attName, val);
								}
							}
						}
					}

					if (att.defaultValueIsCalculatedOnServer) {
						if (att.defaultValueIsDql) {
							// att.defaultValue
							String value = ExplorerServiceImpl.getInstance().dqlLookup(loginName, password, att.defaultValue).get(0).get(0);
							IDfValue val = new DfValue(value);
							persObject.setValue(attName, val);
						}
					}

				}

				// copy attribute subject and title from template
				persObject.setString("subject", objTemplate.getString("subject"));
				persObject.setString("title", objTemplate.getString("title"));

				GregorianCalendar gcal = new GregorianCalendar();
				gcal.setTime(new Date());
				String barcode = AdminServiceImpl.getBarcode(prof.namePolicyBarcodeType, "0", "9", "10", gcal, 1, "DisTelekom")[0];

				persObject.setString("object_name", barcode);
				if (persObject.hasAttr("mob_barcode")) {
					persObject.setString("mob_barcode", barcode);
				}

				IDfSysObject sysObjTemplate = (IDfSysObject) objTemplate;
				ByteArrayInputStream baIs = sysObjTemplate.getContent();
				byte[] bytes = {};
				try {
					HashMap<String, String> hm = new HashMap<>();
					hm.put("IPv4", "1");
					hm.put("IPv6", "1");
					Logger.getLogger(this.getClass()).info("\tGenerating docx from template...");
					WsServer.log(loginName, "Generating docx from template");
					// bytes = DocxGenerator.generateDocxFileFromTemplate(hm, baIs);
				} catch (Exception ex) {
					bytes = IOUtils.toByteArray(baIs);
				}

				ByteArrayOutputStream baOs = new ByteArrayOutputStream();
				baOs.write(bytes);

				IDfSysObject destObject = (IDfSysObject) persObject;
				WsServer.log(loginName, "Setting content...");
				Logger.getLogger(this.getClass()).info("\tSetting content...");

				destObject.setContentEx(baOs, ((IDfSysObject) objTemplate).getFormat().getName(), 0);
				destObject.setContentType(((IDfSysObject) objTemplate).getContentType());

				String newStateId = null;
				for (int j = 0; j < prof.states.size(); j++) {
					if (!prof.states.get(j).getId().equals("unclassified")) {
						newStateId = prof.states.get(j).getId();
						break;
					}
				}
				setStateForObject(userSession, persObject, prof, newStateId);

				setUsersForRoles(userSession, persObject, rolesUsers);
				AdminServiceImpl.runStandardActions(persObject, newStateId, userSession);
				persObject.save();

				doc = docFromSysObject(persObject, userSession.getLoginUserName(), userSession);

				long milis3 = System.currentTimeMillis();
				int timeSec = ((int) ((milis3 - milis2) / 1000));
				Logger.getLogger(this.getClass()).info("Copying: " + objTemplate.getString("title") + " done in " + timeSec + " seconds.");
				WsServer.log(loginName, "Documentum object " + barcode + "(" + persObject.getId("r_object_id") + ") created in: " + timeSec + " seconds.");
			}

			userSession.commitTrans();
			long milis4 = System.currentTimeMillis();

			Logger.getLogger(this.getClass())
					.info("NewDocument ended for " + loginName + ". Completed in " + ((int) ((milis4 - milis1) / 1000)) + " seconds.");

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			try {
				if (userSession != null && userSession.isTransactionActive()) {
					userSession.abortTrans();
				}
			} catch (Exception ex1) {
				errorStringWriter = new StringWriter();
				pw = new PrintWriter(errorStringWriter);
				ex.printStackTrace(pw);
				Logger.getLogger(ExplorerServiceImpl.class).error("Error rolling back transaction.");
				Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			}
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return doc;
	}

	public void setStateForObject(IDfSession userSession, IDfPersistentObject persObject, Profile prof, String stateId) throws Throwable {
		WsServer.log(userSession.getLoginInfo().getUser(), "Setting setStateForObject...");

		persObject.fetch("dm_document");

		String qryDelete = "delete from dm_dbo.T_DOCMAN_S where r_object_id='" + persObject.getId("r_object_id") + "'";
		IDfQuery queryDelete = new DfQuery(qryDelete);
		IDfCollection coll = queryDelete.execute(userSession, IDfQuery.DF_EXEC_QUERY);
		coll.close();

		IDfQuery queryInsert = new DfQuery("insert into dm_dbo.T_DOCMAN_S (r_object_id,object_name,profile_id,current_state_id) values('"
				+ persObject.getId("r_object_id") + "','" + ((IDfSysObject) persObject).getObjectName() + "','" + prof.id + "','" + stateId + "')");
		coll = queryInsert.execute(userSession, IDfQuery.DF_EXEC_QUERY);
		coll.close();
	}

	@Override
	public DocumentsResult runSearchQuery(String loginName, String password, String dql, MyParametrizedQuery pQuery, int start, int length)
			throws ServerException {
		dql = dql.replaceAll("\n", "");
		Logger.getLogger(this.getClass()).info("runSearchQuery for " + loginName + " for: " + dql);

		DocumentsResult ret1 = new DocumentsResult();
		ret1.lastItemFromQuery = start;

		ArrayList<Document> ret = new ArrayList<Document>();
		ArrayList<String> alRObjectIds = new ArrayList<String>();
		ArrayList<String> nonExistentRObjectIds = new ArrayList<String>();
		IDfQuery query = new DfQuery();

		// remove lines starting with #
		String[] lines = dql.split("\n");
		String resultLine = "";
		for (String line : lines) {
			if (!line.startsWith("#")) {
				resultLine = resultLine + line + "\n";
			}
		}
		dql = resultLine;
		dql = dql.replaceAll("\t", " ");

		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum
			userSession = AdminServiceImpl.getSession(loginName, password);

			boolean done = false;
			int resultCount = 0;
			long milis1 = System.currentTimeMillis();

			while (!done) {
				int rangeStart = start + 1;
				int rangeEnd = start + length;

				String regExpr = ".*(RETURN_RANGE [0-9]+ [0-9]+\\s*('.*')?)";

				Pattern p = Pattern.compile(regExpr, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);// .
				// represents
				// single
				// character
				Matcher m = p.matcher(dql);
				if (!m.find()) {
					throw new ServerException(
							"Dql must contain \"ENABLE (RETURN_RANGE 1 20 '[[att] [desc], ...]')\" hint, to limit load on Documentum content server.");
				}

				if (m.group(2) != null) {
					dql = dql.replaceAll(m.group(1), "RETURN_RANGE " + rangeStart + " " + rangeEnd + " " + m.group(2));
					// lets modify order by from MyParametrizedQuery definition
					String orderByStr = "'";
					int i = 0;
					for (String oby : pQuery.orderBys) {
						if (pQuery.orderByDirections.get(i).contentEquals("D")) {
							orderByStr = orderByStr + oby + " desc, ";
						} else {
							orderByStr = orderByStr + oby + ", ";
						}
						i++;
					}
					if (orderByStr.length() > 1) {
						orderByStr = orderByStr.substring(0, orderByStr.length() - 2) + "'";
						dql = dql.replaceAll(m.group(2).replaceAll("'", "\\'"), orderByStr);
					}
				} else {
					dql = dql.replaceAll(m.group(1), "RETURN_RANGE " + rangeStart + " " + rangeEnd);
				}

				Logger.getLogger(this.getClass()).info("dql: " + dql);

				if (loginName == null) {
					throw new Exception("LoginName should not be null");
				}
				if (password == null) {
					throw new Exception("Password should not be null");
				}

				query.setDQL(dql);
				Logger.getLogger(this.getClass()).info("\tStarted dql query: " + dql);
				WsServer.log(loginName, dql);
				WsServer.setLastGetSessionTime(loginName);
				collection = query.execute(userSession, IDfQuery.DF_READ_QUERY);
				long milis2 = System.currentTimeMillis();
				String msg = "Ended query in: " + (int) ((milis2 - milis1) / 1000) + "s";
				Logger.getLogger(this.getClass()).info("\t" + msg);
				WsServer.log(loginName, msg);
				int prevLogOutputDuration = 0;
				int addedToResultCount = 0;

				IIncludeInReport filter = null;
				if (pQuery.filterClass != null && !pQuery.filterClass.equals("")) {
					// si.telekom.dis.server.reports.FilterSapObjects
					Logger.getLogger(this.getClass()).info("filtering by filter class: '" + pQuery.filterClass + "'");

					final Class<?> aClass = WebappContext.class.getClassLoader().loadClass(pQuery.filterClass);
					final Constructor<IIncludeInReport> constr = (Constructor<IIncludeInReport>) aClass.getConstructor();
					filter = constr.newInstance();
				}

				addedToResultCount = 0;
				while (collection.next()) {
					String r_object_id;
					if (collection.hasAttr("audited_obj_id"))
						r_object_id = collection.getId("audited_obj_id").toString();
					else
						r_object_id = collection.getId("r_object_id").toString();

					if (filter != null) {
						Logger.getLogger(this.getClass()).info("filtering r_object_id: " + r_object_id);
						try {

							IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));
							if (filter.shouldInclude((IDfSysObject) persObj, userSession)) {
								if (!alRObjectIds.contains(r_object_id) && !nonExistentRObjectIds.contains(r_object_id)) {
									if (persObj.getType().getName().equals("dm_document") || persObj.getType().isSubTypeOf("dm_document")) {
										Document doc = docFromSysObject((IDfSysObject) persObj, loginName, userSession);
										ret.add(doc);
										alRObjectIds.add(r_object_id);
										addedToResultCount++;
										if (ret.size() == length) {
											ret1.documents = ret;
											ret1.done = false;
											ret1.lastItemFromQuery++;
											return ret1;
										}
									} else {
										Logger.getLogger(this.getClass()).warn("Only adding document to result of search and not: " + persObj.getType().getName());
										nonExistentRObjectIds.add(r_object_id);
									}
								}
							}
						} catch (Exception ex) {
							if (ex.getMessage().contains("DM_SYSOBJECT_E_CANT_FETCH_INVALID_ID"))
								nonExistentRObjectIds.add(r_object_id);
							else if (ex.getMessage().contains("DM_API_E_EXIST")) {
								nonExistentRObjectIds.add(r_object_id);
							}
						}

					} else {
						if (!alRObjectIds.contains(r_object_id) && !nonExistentRObjectIds.contains(r_object_id)) {
							try {
								IDfPersistentObject persObj = userSession.getObject(new DfId(r_object_id));
								// IDfPersistentObject persObj =
								// userSession.getObjectByQualification("dm_document(all) where
								// r_object_id='" + r_object_id + "'");
								if (persObj.getType().getName().equals("dm_document") || persObj.getType().isSubTypeOf("dm_document")) {
									Document doc = docFromSysObject((IDfSysObject) persObj, loginName, userSession);
									ret.add(doc);
									alRObjectIds.add(r_object_id);
									addedToResultCount++;
									if (ret.size() == length) {
										ret1.documents = ret;
										ret1.done = false;
										ret1.lastItemFromQuery++;
										return ret1;
									}
								} else {
									Logger.getLogger(this.getClass()).warn("Only adding document to result of search and not: " + persObj.getType().getName());
									nonExistentRObjectIds.add(r_object_id);
								}
							} catch (Exception ex) {
								if (ex.getMessage().contains("DM_SYSOBJECT_E_CANT_FETCH_INVALID_ID"))
									nonExistentRObjectIds.add(r_object_id);
								else if (ex.getMessage().contains("DM_API_E_EXIST")) {
									nonExistentRObjectIds.add(r_object_id);
								}

							}
						}
					}

					ret1.lastItemFromQuery++;

					long milis3 = System.currentTimeMillis();
					int duration = (int) ((milis3 - milis2) / 1000);
					if (duration % 1 == 0 && prevLogOutputDuration != duration) {
						String message = "still parsing, currently: " + ret.size() + "/" + length + " working for: " + duration + "s";
						Logger.getLogger(this.getClass()).info("\t" + message);
						WsServer.log(loginName, message);
						prevLogOutputDuration = duration;
						WsServer.setLastGetSessionTime(loginName);
					}
				}
				collection.close();

				if (addedToResultCount == 0) {
					done = true;
				}
				// else if (ret1.lastItemFromQuery < start + length) {
				// done = true;
				// }
				else {
					done = false;
					start = start + length;
				}

			}
			ret1.documents = ret;
			ret1.done = done;

			long milis3 = System.currentTimeMillis();

			String durationStr = String.format(Locale.ROOT, "%.2f", (milis3 - milis1) / 1000.0);
			float duration = Float.valueOf(durationStr).floatValue();

			String msg2 = "Returned: " + ret.size() + " objects, nonexisting documents count: " + nonExistentRObjectIds.size() + " working for: " + duration
					+ "s";
			Logger.getLogger(this.getClass()).info(msg2);
			WsServer.log(loginName, msg2);
			WsServer.setLastGetSessionTime(loginName);

		} catch (Throwable ex) {
			// ex.printStackTrace();
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(stackTrace);

			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret1;
	}

	public void showCurrentDocumentumPermits(String loginName, IDfPersistentObject persObject) throws DfException {
		IDfACL objAclExisting = ((IDfSysObject) persObject).getACL();
		String currentPermissions = "Current permission for <strong>" + persObject.getString("object_name") + "</strong> type: <strong>"
				+ persObject.getType().getName() + "</strong> owner: <strong>" + persObject.getString("owner_name") + "</strong>: ";
		if (objAclExisting != null) {
			for (int j = 0; j < objAclExisting.getAccessorCount(); j++) {
				currentPermissions = currentPermissions + objAclExisting.getAccessorName(j).toString() + " " + objAclExisting.getAccessorPermit(j) + ", ";
			}
			if (currentPermissions.endsWith(", ")) {
				currentPermissions = currentPermissions.substring(0, currentPermissions.length() - 2);
			}
			WsServer.log(loginName, currentPermissions);
		} else {
			WsServer.log(loginName, currentPermissions + "No acl on object??");
		}

	}

	public List<List<String>> showPdfTags(String loginName, String loginPassword, String r_object_id) throws ServerException {
		List<List<String>> ret = new ArrayList<List<String>>();

		IDfSession userSession = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, loginPassword);

			IDfSysObject dfSysObject = (IDfSysObject) userSession.getObject(new DfId(r_object_id));

			ByteArrayInputStream baIs = dfSysObject.getContent();
			PdfReader pdfReader = new PdfReader(baIs);
			HashMap<String, String> hmInfo = pdfReader.getInfo();
			for (String tag : hmInfo.keySet()) {

				ArrayList<String> al = new ArrayList<String>();
				al.add(tag);
				al.add(hmInfo.get(tag));

				ret.add(al);
			}

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
		} finally {
			if (userSession != null) {
				AdminServiceImpl.getInstance().releaseSession(userSession);
			}
		}

		return ret;
	}

	@Override
	public Void updateBusinessNotification(String loginName, String password, String r_object_id) throws ServerException {
		Connection lconn = null;
		Statement stmt = null;
		ResultSet rs = null;
		IDfSession userSession = null;
		String sql = "";

		try {

			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfSysObject sysObject = (IDfSysObject) userSession.getObject(new DfId(r_object_id));

			IDfAuditTrailManager mgr = userSession.getAuditTrailManager();

			InetAddress host = InetAddress.getByName(this.getThreadLocalRequest().getRemoteAddr());
			String dnsName = host.getHostName();

			String[] arg2 = { this.getThreadLocalRequest().getRemoteAddr(), dnsName, this.getThreadLocalRequest().getRemoteUser() };
			mgr.createAudit(new DfId(sysObject.getId("r_object_id").toString()), "updateBusinessNotification", arg2, null);

			String msg_template_id = sysObject.getString("mob_template_id").toString();
			String msg_template_type = sysObject.getString("bn_template_type").toString();
			int topic_id = sysObject.getInt("bn_topic_id");
			String msg_template_description = sysObject.getString("bn_template_description");
			String process_point = sysObject.getString("bn_process_point");
			String rules_conditions = sysObject.getString("bn_rules_conditions");

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

			if (msg_template_type == null) {
				throw new ServerException("BN Template type is not defined.");
			}
			if (msg_template_description == null) {
				throw new ServerException("BN Description is not defined.");
			}
			if (rules_conditions == null) {
				throw new ServerException("BN rules_conditions is not defined.");
			}

			for (String driverUrl : urls) {

				String userAndPassword = driverUrl.split("~")[0];
				String scheme = userAndPassword.split("[//]")[0];
				String jdbcUser = userAndPassword.split("[//]")[1];
				String jdbcPassword = userAndPassword.split("[//]")[2];
				String realUrl1 = driverUrl.split("~")[1];

				String alias = DigestUtils.sha1Hex(driverUrl);
				Logger.getLogger(this.getClass()).info("Updating bn with oracle user: " + jdbcUser + " alias: " + alias + " jdbcUrl: " + realUrl1);
				try {
					// DriverManager.setLogStream(System.out);
					lconn = DriverManager.getConnection("proxool." + alias);
				} catch (SQLException ex) {
					try {
						Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
						// Properties info = new Properties();
						// info.setProperty("proxool.maximum-connection-count", "10");
						// info.setProperty("proxool.house-keeping-test-sql", "select
						// CURRENT_DATE");
						// info.setProperty("user", jdbcUser);
						// info.setProperty("password", jdbcPassword);
						String driverClass = "oracle.jdbc.driver.OracleDriver";
						Class.forName(driverClass);
						String url = "proxool." + alias + ":" + driverClass + ":" + realUrl1;

						Properties proxoolProps = new Properties();
						proxoolProps.setProperty("user", jdbcUser);
						proxoolProps.setProperty("password", jdbcPassword);
						proxoolProps.setProperty("proxool.maximum-connection-count", "10");
						proxoolProps.setProperty("statistics-log-level", "DEBUG");

						ProxoolFacade.registerConnectionPool(url, proxoolProps);
						try {
							lconn = DriverManager.getConnection("proxool." + alias);
						} catch (SQLException e) {
							StringWriter errorStringWriter = new StringWriter();
							PrintWriter pw = new PrintWriter(errorStringWriter);
							e.printStackTrace(pw);
							Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
							throw new ServerException(e);
						}
					} catch (ProxoolException | ClassNotFoundException e) {
						StringWriter errorStringWriter = new StringWriter();
						PrintWriter pw = new PrintWriter(errorStringWriter);
						e.printStackTrace(pw);
						Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
					}
				}

				stmt = lconn.createStatement();
				// @formatter:off
                sql = "MERGE INTO " + scheme + ".T_MSG_TEMPLATE USING dual ON ( msg_template_id="
                        + msg_template_id + " )"
                        + "	WHEN MATCHED THEN UPDATE SET msg_template_type='" + msg_template_type + "', topic_id= " + (topic_id ==0 ? "NULL": topic_id)
                        + "	WHEN NOT MATCHED THEN "
                        + "		INSERT(msg_template_id,msg_template_type,topic_id) VALUES("
                        + msg_template_id
                        + ",'" + msg_template_type + "'," + (topic_id ==0 ? "NULL": topic_id) + ")";
                // @formatter:on
				rs = stmt.executeQuery(sql);

				// @formatter:off
                sql = "MERGE INTO " + scheme + ".T_MSG_TEMPLATE_TOPIC USING dual ON ( msg_template_id="
                        + msg_template_id + " )"
                        + "	WHEN MATCHED THEN UPDATE SET msg_template_description='" + msg_template_description + "',"
                        + "process_point=" + (process_point.equals("") ? "'NA'": "'" + process_point + "'") + ","
                        + "rules_conditions='" + rules_conditions + "'"
                        + "	WHEN NOT MATCHED THEN "
                        + "		INSERT(msg_template_id,msg_template_description,process_point,rules_conditions) VALUES("
                        + msg_template_id
                        + ",'" + msg_template_description + "'"
                        + "," + (process_point.equals("") ? "'NA'": "'" + process_point + "'") 
                        + ",'" + rules_conditions + "')";
                // @formatter:on
				rs = stmt.executeQuery(sql);

				// lets delete teplate_default for this template_id
				sql = "delete " + scheme + ".T_MSG_TEMPLATE_DEFAULT where msg_template_id=" + msg_template_id;
				stmt.execute(sql);

				int valueCount = sysObject.getValueCount("bn_template_key");
				for (int i = 0; i < valueCount; i++) {
					String sqlSeq = "select " + scheme + ".s_msg_template_default.NEXTVAL from dual";
					ResultSet rs2 = stmt.executeQuery(sqlSeq);
					if (rs2.next()) {
						int seqId = rs2.getInt(1);
						String msgTemplateKey = sysObject.getRepeatingString("bn_template_key", i);
						String msgTemplateValue = sysObject.getRepeatingString("bn_template_value", i);
						String sql2 = "INSERT into " + scheme
								+ ".T_MSG_TEMPLATE_DEFAULT (msg_template_id,msg_template_key,msg_template_value, msg_template_default_id) " + "VALUES("
								+ msg_template_id + ",'" + msgTemplateKey + "','" + msgTemplateValue + "', " + seqId + ")";
						stmt.execute(sql2);
					}
					rs2.close();
				}
				stmt.execute("commit");
				stmt.close();
				lconn.close();

				String msg = "Updated business notification on: " + realUrl1;
				WsServer.log(loginName, msg);
				Logger.getLogger(this.getClass()).info(msg);

			}
		} catch (Throwable e) {
			Logger.getLogger(this.getClass()).error("sql: " + sql);
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			e.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			WsServer.log(loginName, "ERROR: " + e.getMessage());
		} finally {
			if (userSession != null) {
				if (userSession.isConnected()) {
					userSession.getSessionManager().release(userSession);
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		IDfSession userSess;
		try {
			Class.forName("si.telekom.dis.server.AdminServiceImpl");

			// ---- DEV
			// AdminServiceImpl.repositoryName = "Mobitel";
			// AdminServiceImpl.superUserName = "zivkovick";
			// AdminServiceImpl.superUserPassword = "Doitman789012";
			// AdminServiceImpl.superUserDomain = "ts";
			// String m_docbroker = "bsw-documentum.ts.telekom.si";
			// ---- TEST
			// AdminServiceImpl.repositoryName = "Mobitel";
			// AdminServiceImpl.superUserName = "zivkovick";
			// AdminServiceImpl.superUserPassword = "Doitman789012";
			// AdminServiceImpl.superUserDomain = "ts";
			// String m_docbroker = "BTW-DOCUMENT-T.ts.telekom.si";
			// -- PROD
			AdminServiceImpl.repositoryName = "Mobitel";
			AdminServiceImpl.superUserName = "zivkovick";
			AdminServiceImpl.superUserPassword = "Doitman789012";
			AdminServiceImpl.superUserDomain = "ts";
			String m_docbroker = "bpw-dmfs-cl1.ts.telekom.si";

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

			String driverUrl = "mobile/reports/ReportsPw01~jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER=on)(ADDRESS=(PROTOCOL=TCP)(HOST=dbm02.ts.telekom.si)(PORT=1521))(CONNECT_DATA=(FAILOVER_MODE=(TYPE=select)(METHOD=basic))(SERVER=dedicated)(SERVICE_NAME=tsbeta.ts.telekom.si)))";
			String userAndPassword = driverUrl.split("~")[0];
			String scheme = userAndPassword.split("[//]")[0];
			String jdbcUser = userAndPassword.split("[//]")[1];
			String jdbcPassword = userAndPassword.split("[//]")[2];
			String realUrl1 = driverUrl.split("~")[1];

			String alias = DigestUtils.sha1Hex(realUrl1);
			Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
			String driverClass = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverClass);
			String url = "proxool." + alias + ":" + driverClass + ":" + realUrl1;

			Properties proxoolProps = new Properties();
			proxoolProps.setProperty("user", jdbcUser);
			proxoolProps.setProperty("password", jdbcPassword);
			proxoolProps.setProperty("proxool.maximum-connection-count", "10");
			proxoolProps.setProperty("statistics-log-level", "DEBUG");

			ProxoolFacade.registerConnectionPool(url, proxoolProps);
			Connection lconn = DriverManager.getConnection("proxool." + alias);

			Statement stmt = lconn.createStatement();
			Statement stmt2 = lconn.createStatement();

			IDfQuery query = new DfQuery("select r_object_id from mob_form_template where not mob_template_id is null");
			IDfCollection col = query.execute(userSess, DfQuery.DF_EXECREAD_QUERY);
			int record = 0;
			while (col.next()) {

				IDfSysObject sysObj = (IDfSysObject) userSess.getObject(col.getId("r_object_id"));

				String msg_template_id = sysObj.getString("mob_template_id");
				Logger.getLogger(ExplorerServiceImpl.class)
						.info(record + " updating " + sysObj.getString("object_name") + " for template_id=" + msg_template_id);

				String sql = "select * from " + scheme + ".T_MSG_TEMPLATE where msg_template_id=" + msg_template_id;
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					int topicId = rs.getInt("TOPIC_ID");
					String templateType = rs.getString("MSG_TEMPLATE_TYPE");

					sysObj.setString("bn_template_type", templateType);
					sysObj.setInt("bn_topic_id", topicId);

					String sql2 = "select * from " + scheme + ".T_MSG_TEMPLATE_TOPIC where msg_template_id=" + msg_template_id;
					ResultSet rs2 = stmt2.executeQuery(sql2);
					if (rs2.next()) {
						sysObj.setString("bn_template_description",
								rs2.getString("msg_template_description") == null ? "" : rs2.getString("msg_template_description"));
						sysObj.setString("bn_process_point", rs2.getString("process_point") == null ? "" : rs2.getString("process_point"));
						sysObj.setString("bn_rules_conditions", rs2.getString("rules_conditions") == null ? "" : rs2.getString("rules_conditions"));
					}
					rs2.close();

					String sql3 = "select * from " + scheme + ".T_MSG_TEMPLATE_DEFAULT where msg_template_id=" + msg_template_id;
					ResultSet rs3 = stmt2.executeQuery(sql3);
					int i = 0;
					sysObj.removeAll("bn_template_key");
					sysObj.removeAll("bn_template_value");
					if (rs3.next()) {
						sysObj.setRepeatingString("bn_template_key", i, rs3.getString("msg_template_key"));
						sysObj.setRepeatingString("bn_template_value", i, rs3.getString("msg_template_value"));
						i++;
					}
					rs3.close();
					sysObj.save();
				}
				rs.close();
				record++;
			}
			col.close();
			userSess.getSessionManager().release(userSess);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Void newRelease(String loginName, String password, String r_object_id) throws ServerException {
		IDfSession userSession = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			AdminServiceImpl.beginTransaction(userSession);

			IDfSysObject dfSysObject = (IDfSysObject) userSession.getObject(new DfId(r_object_id));

			// Instantiate a copy operation.
			IDfClientX clientx = new DfClientX();
			IDfCopyOperation copyOperation = clientx.getCopyOperation();
			IDfId destinationFolderId = dfSysObject.getFolderId(0);
			// set destination Folder id to where you want to copy to.
			copyOperation.setDestinationFolderId(destinationFolderId);
			copyOperation.setDeepFolders(true);
			copyOperation.setSession(userSession);

			getLogger().info("Copying object/virtual document: " + r_object_id);

			/**
			 * You can also add virtual document to the copy operation. In that case,
			 * all the (immediate and non-immediate) children of the virtual document
			 * will also be copied.
			 *
			 */
			if (dfSysObject.isVirtualDocument()) { // yes, it is virtual
				IDfVirtualDocument virdoc = dfSysObject.asVirtualDocument("", false);
				copyOperation.add(virdoc);
			} else {
				copyOperation.add(dfSysObject);
			}

			if (copyOperation.execute() == false) {
				getLogger().error("Copy Operation failed!");
				String allErrors = "";
				for (int j = 0; j < copyOperation.getErrors().getCount(); j++) {
					IDfOperationError errOperation = (IDfOperationError) copyOperation.getErrors().get(j);
					getLogger().error("Copy Operation failed: " + errOperation.getMessage());
					allErrors = allErrors + errOperation.getMessage() + "\n";
				}
				allErrors = allErrors.substring(0, allErrors.length() - 2);
				throw new Exception(allErrors);
			} else {
				WsServer.log(loginName, "[" + loginName + "] NewRelease: Copy Operation successful!");
				IDfSysObject copiedObject = (IDfSysObject) copyOperation.getNewObjects().get(0);
				copiedObject.save();

				Object[] profileAndRolesOfUserAndState = getProfileAndUserRolesAndState(dfSysObject, loginName, userSession);
				Profile prof = (Profile) profileAndRolesOfUserAndState[1];

				HashMap<String, List<String>> roleUserGroups = (HashMap<String, List<String>>) (profileAndRolesOfUserAndState[4]);
				if (roleUserGroups.keySet().size() == 0) {
					// in case of noclassified documents - lets pick
					// default users in profile for every role
					for (int i = 0; i < prof.roles.size(); i++) {
						for (int k = 0; k < prof.roles.get(i).defaultUserGroups.size(); k++) {
							UserGroup ug = prof.roles.get(i).defaultUserGroups.get(k);
							if (!roleUserGroups.containsKey(prof.roles.get(i).getId())) {
								roleUserGroups.put(prof.roles.get(i).getId(), new ArrayList<String>());
							}

							roleUserGroups.get(prof.roles.get(i).getId()).add(ug.getId());
						}
					}

				}

				IDfPersistentObject persObject = copiedObject;

				int releaseNo = 0;
				releaseNo = dfSysObject.getInt("mob_releaseno") + 1;
				copiedObject.setInt("mob_releaseno", releaseNo);
				if (dfSysObject.hasAttr("mob_releaseno")) {
					copiedObject.setRepeatingString("mob_supersedes", 0, dfSysObject.getObjectName() + "/" + dfSysObject.getInt("mob_releaseno"));
				} else {
					copiedObject.setRepeatingString("mob_supersedes", 0, dfSysObject.getObjectName());
				}

				try {
					copiedObject.setTime("mob_valid_from", DfTime.DF_NULLDATE);
				} catch (Exception ex) {
					String msg = "Object " + copiedObject.getObjectName() + " is of type " + copiedObject.getTypeName()
							+ " and doesn't suppor mob_valid_from attribute (" + ex.getMessage() + ")";
					WsServer.log(loginName, msg);
					Logger.getLogger(this.getClass()).info(msg);
				}

				ExplorerServiceImpl.getInstance().checkDocmanSExist(persObject, userSession, prof);
				ExplorerServiceImpl.getInstance().setUsersForRoles(userSession, persObject, roleUserGroups);

				if (userSession.isTransactionActive()) {
					userSession.commitTrans();
				}

				WsServer.log(loginName, "[" + loginName + "] NewRelease: done.");

			}

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
		} finally {
			if (userSession != null) {
				AdminServiceImpl.getInstance().releaseSession(userSession);
			}
		}

		return null;
	}

	@Override
	public List<List<String>> getDefaultValueDql(String dql) throws ServerException {
		// TODO Auto-generated method stub
		return dqlLookup(AdminServiceImpl.superUserName, AdminServiceImpl.superUserPassword, dql);
	}

	@Override
	public List<String> recognizeFormat(String base64Content) throws ServerException {
		ArrayList<String> al = new ArrayList<String>();
		Logger.getLogger(this.getClass()).info("Recognizing format...");

		IDfQuery query = new DfQuery();
		IDfCollection collection = null;
		IDfSession sess = null;

		// base64Content

		File tempFile = null;
		try {
			tempFile = File.createTempFile("recognize", ".new");

			String[] base64contentsplit = base64Content.split(",");
			byte[] data = Base64.getDecoder().decode(base64contentsplit[1]);
			try (OutputStream stream = new FileOutputStream(tempFile.getAbsolutePath())) {
				stream.write(data);
			}

			TikaInputStream tis = TikaInputStream.get(data);

			// AutoDetectParser parser = new AutoDetectParser();
			// BodyContentHandler handler = new BodyContentHandler();
			// Metadata metadata = new Metadata();
			// try (InputStream stream = new
			// FileInputStream(tempFile.getAbsolutePath())) {
			// parser.parse(stream, handler, metadata);
			// handler.toString();
			// }

			Path path = tempFile.toPath();
			String filetype = Files.probeContentType(path);

			// ClassLoader classloader =
			// org.apache.poi.poifs.filesystem.POIFSFileSystem.class.getClassLoader();
			// URL res =
			// classloader.getResource("org/apache/poi/util/XMLHelper.class");
			// String path = res.getPath();
			// System.out.println("Core POI came from " + path);
			//
			// Tika tika = new Tika();
			// String filetype = tika.detect(tis);

			if (filetype.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") || filetype.equals("application/zip")) {
				sess = AdminServiceImpl.getAdminSession();
				String mimeTypeFromClient = base64contentsplit[0].split(":")[1].split(";")[0];
				query.setDQL("select name from dm_format where mime_type in ('" + filetype + "','" + mimeTypeFromClient + "')");
				collection = query.execute(sess, DfQuery.DF_READ_QUERY);

				while (collection.next()) {
					al.add(collection.getString("name"));
					Logger.getLogger(this.getClass()).info("\t" + collection.getString("name"));
				}
				collection.close();

				Logger.getLogger(this.getClass()).info("Recognizing format...Done.");
			} else if (filetype.equals("application/vnd.oasis.opendocument.spreadsheet")) {
				al.add("ods");
			} else if (filetype.equals("application/xhtml+xml")) {
				al.add("html");
			} else if (filetype.equals("application/vnd.oasis.opendocument.text")) {
				al.add("odt");
			} // else if (filetype.equals("application/vnd.ms-excel"))
			// al.add("xls");
			else if (filetype.equals("text/html")) {
				al.add("html");
			} else {
				// String mimeType = Files.probeContentType(tempFile.toPath());
				sess = AdminServiceImpl.getAdminSession();
				String mimeTypeFromClient = base64contentsplit[0].split(":")[1].split(";")[0];
				query.setDQL("select name from dm_format where mime_type in ('" + filetype + "','" + mimeTypeFromClient + "')");
				collection = query.execute(sess, DfQuery.DF_READ_QUERY);

				while (collection.next()) {
					al.add(collection.getString("name"));
					Logger.getLogger(this.getClass()).info("\t" + collection.getString("name"));
				}
				collection.close();

				Logger.getLogger(this.getClass()).info("Recognizing format...Done.");

			}

		} catch (Throwable ex) {
			Logger.getLogger(this.getClass()).error(ex);
		} finally {
			if (sess != null) {
				sess.getSessionManager().release(sess);
			}
			if (tempFile != null) {
				tempFile.delete();
			}
		}

		al.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});

		return al;
	}

	@Override
	public Void removeRendition(String loginName, String password, String r_object_id, String format) throws ServerException {
		IDfSession userSession = null;
		try {
			Logger.getLogger(this.getClass()).info("Remove rendition started for " + loginName + " for: " + format);

			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfSysObject sysObj = (IDfSysObject) userSession.getObject(new DfId(r_object_id));
			sysObj.removeRendition(format);
			sysObj.save();
			Logger.getLogger(this.getClass()).info("Remove rendition ended.");
		} catch (Exception ex) {
			String stackTrace = org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			Logger.getLogger(this.getClass()).error(stackTrace);
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public Document classifyDocument(String loginName, String password, String r_object_id, String profileId, String stateId,
			Map<String, List<String>> attributes, Map<String, List<String>> rolesUsers) throws ServerException {

		Logger.getLogger(this.getClass()).info("ClassifyDocument started for " + loginName);
		long milis1 = System.currentTimeMillis();

		Document doc = null;
		IDfSession userSession = null;
		IDfCollection collection = null;
		try {
			// try to get profile from local path - if it doesnt exist load it from
			// documentum

			if (loginName == null) {
				throw new Exception("LoginName should not be null");
			}
			if (password == null) {
				throw new Exception("Password should not be null");
			}

			userSession = AdminServiceImpl.getSession(loginName, password);
			AdminServiceImpl.beginTransaction(userSession);

			Profile prof = AdminServiceImpl.profiles.get(profileId);

			IDfPersistentObject persObject = userSession.getObject(new DfId(r_object_id));

			if (!persObject.getType().getName().equals(prof.objType)) {
				// need to change object type
				// first to dm_document than to specific object type that is defined on
				// profile
				long t1 = System.currentTimeMillis();
				IDfSession adminSession = AdminServiceImpl.getAdminSession();

				while (!persObject.getType().getName().toString().equals("dm_document")) {
					IDfQuery q = new DfQuery("change " + persObject.getType().getName() + " object to " + persObject.getType().getSuperType().getName()
							+ " where r_object_id = '" + persObject.getObjectId().getId() + "'");
					IDfCollection coll = q.execute(adminSession, IDfQuery.DF_EXEC_QUERY);
					coll.close();
					persObject.fetch(null);
				}
				long t2 = System.currentTimeMillis();
				Logger.getLogger(this.getClass()).info("Change to dm_document type took: " + (t2 - t1) + " ms.");
				((ISession) adminSession).getDocbaseApi().sysObjFlushCache(false, null);

				persObject.fetch(null);
				IDfQuery q = new DfQuery("change " + persObject.getType().getName() + " object to " + prof.objType + " where r_object_id = '"
						+ persObject.getObjectId().getId() + "'");
				IDfCollection coll = q.execute(adminSession, IDfQuery.DF_EXEC_QUERY);
				coll.close();

				persObject.fetch(null);
				((ISession) userSession).getDocbaseApi().sysObjFlushCache(false, null);
			}

			HashMap<String, Attribute> wizardAttributes = AdminServiceImpl.getInstance().getWizardAttributes(prof, "classify");
			for (String attName : attributes.keySet()) {
				Logger.getLogger(this.getClass()).info("\t\tUpdating attribute: " + attName);
				Attribute att = wizardAttributes.get(attName);
				DcmtAttribute dcmtAttribute = AdminServiceImpl.getInstance().findAttribute(prof.objType, attName);
				if (!att.isReadOnly) {
					List<String> values = attributes.get(attName);
					if (values.size() > 1) {
						int i = 0;
						for (String value : values) {
							if (!value.equals("")) {
								IDfValue val = new DfValue(value);
								persObject.setRepeatingValue(attName, i, val);
							}
						}
					} else if (values.size() == 1) {
						if (values.get(0) != null && !values.get(0).equals("")) {
							if (dcmtAttribute.domain_type.equals("4")) {
								String miliSeconds = values.get(0);
								GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
								cal.setTimeInMillis(Long.valueOf(miliSeconds));
								IDfTime time = new DfTime(cal.getTime());
								persObject.setTime(attName, time);
							} else {
								IDfValue val = new DfValue(values.get(0).split("\\|")[0]);
								persObject.setValue(attName, val);
							}
						}
					}
				}
			}

			/*
			 * Skip object_name calculation GregorianCalendar gcal = new
			 * GregorianCalendar(); gcal.setTime(new Date()); String barcode =
			 * AdminServiceImpl.getBarcode(prof.namePolicyBarcodeType, "0", "9", "10",
			 * gcal, 1, "DisTelekom")[0]; ret = barcode + "|" +
			 * persObject.getId("r_object_id");
			 * 
			 * persObject.setString("object_name", barcode); if
			 * (persObject.hasAttr("mob_barcode")) persObject.setString("mob_barcode",
			 * barcode);
			 * 
			 */
			persObject.save();

			int stateInd = AdminServiceImpl.getStateIndex(prof, stateId);

			setStateForObject(userSession, persObject, prof, stateId);
			setUsersForRoles(userSession, persObject, rolesUsers);
			persObject.fetch("dm_document");

			AdminServiceImpl.actualizeServiceData(persObject, r_object_id, userSession);
			AdminServiceImpl.runStandardActions(persObject, stateId, userSession);
			// persObject.save();
			long milis3 = System.currentTimeMillis();
			int timeSec = ((int) ((milis3 - milis1) / 1000));
			WsServer.log(loginName, "Documentum object with barcode" + persObject.getString("object_name") + "(" + persObject.getId("r_object_id")
					+ ") classified in: " + timeSec + " seconds.");

			if (userSession.isTransactionActive()) {
				userSession.commitTrans();
			}

			doc = docFromSysObject(persObject, userSession.getLoginUserName(), userSession);

			long milis4 = System.currentTimeMillis();

			String durationStr = String.format(Locale.ROOT, "%.3fs", (milis4 - milis1) / 1000.0);

			Logger.getLogger(this.getClass()).info("ClassifyDocument for r_object_id: " + r_object_id + " (" + persObject.getId("r_object_id").toString()
					+ ") object_name: " + persObject.getString("object_name") + " ended for " + loginName + ". Completed in " + durationStr + " seconds.");

		} catch (Throwable ex) {
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());

			try {
				userSession.abortTrans();
			} catch (Exception ex1) {
				errorStringWriter = new StringWriter();
				pw = new PrintWriter(errorStringWriter);
				ex.printStackTrace(pw);
				Logger.getLogger(ExplorerServiceImpl.class).error("Error rolling back transaction.");
				Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			}
			throw new ServerException(ex.getMessage());
		} finally {
			try {
				if (collection != null) {
					collection.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (userSession != null) {
					AdminServiceImpl.getInstance().releaseSession(userSession);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return doc;
	}

	@Override
	public String massDownloadContent(String loginName, String password, List<String> r_object_ids) throws ServerException {
		String ret = null;
		IDfSession userSession = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			Map<String, String> env = new HashMap<>();
			// Create the zip file if it doesn't exist
			env.put("create", "true");

			String fileName = FileUtils.getTempDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".zip";

			URI uri = URI.create("jar:file:" + fileName);

			FileSystem zipfs = FileSystems.newFileSystem(uri, env);
			int count = 0;
			if (zipfs != null) {
				for (String r_object_id : r_object_ids) {
					IDfSysObject sysObj = (IDfSysObject) userSession.getObjectByQualification("dm_document where r_object_id='" + r_object_id + "'");

					if (sysObj != null && sysObj.getType().isSubTypeOf("dm_document")) {
						IDfAuditTrailManager mgr = userSession.getAuditTrailManager();

						InetAddress host = InetAddress.getByName(this.getThreadLocalRequest().getRemoteAddr());
						String dnsName = host.getHostName();

						String[] arg2 = { this.getThreadLocalRequest().getRemoteAddr(), dnsName, this.getThreadLocalRequest().getRemoteUser() };
						mgr.createAudit(new DfId(sysObj.getId("r_object_id").toString()), "export_content", arg2, null);

						File f2 = File.createTempFile(r_object_id + "-", "." + sysObj.getFormat().getDOSExtension());
						Logger.getLogger(this.getClass()).info(f2.getAbsolutePath());
						sysObj.getFile(f2.getAbsolutePath());

						Path externalTxtFile = Paths.get(f2.getAbsolutePath());
						Path pathInZipfile = zipfs.getPath(sysObj.getObjectName() + "." + sysObj.getFormat().getDOSExtension());

						Files.copy(externalTxtFile, pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
						if (f2.delete()) {
							Logger.getLogger(this.getClass()).info("Succesfully added in deleted temp file " + f2.getAbsolutePath() + " to zip");
						}

						WsServer.log(loginName, "progress: " + count + "/" + r_object_ids.size());
					}
					count++;
				}
				WsServer.log(loginName, "progress: 0/" + r_object_ids.size());
				zipfs.close();
				ret = fileName;
			}
			File f = new File(fileName);
			f.deleteOnExit();

		} catch (Throwable ex) {
			Logger.getLogger(this.getClass()).error(ex);
		} finally {
			if (userSession != null) {
				userSession.getSessionManager().release(userSession);
			}
		}
		return ret;
	}

	@Override
	public Void prepareAiTrainDataForFolderAndClassification(String loginName, String password, List<String> r_object_ids, String classification,
			boolean deleteDir) throws ServerException {

		// https://github.com/vasturiano/3d-force-graph
		IDfSession userSession = null;

		Thread.currentThread().setName("prepareAiTrainDataForFolderAndClassification");

		try {

			if (classification.contains("|")) {
				classification = classification.split("\\|")[0];
			}

			Path path = Paths.get("/tmp/classify/train/" + classification);
			if (!path.toFile().exists()) {
				Files.createDirectories(path);
			}

			if (deleteDir) {
				Stream<Path> list = Files.list(path);
				Iterator<Path> it = list.iterator();
				while (it.hasNext()) {
					Path pathFile = it.next();
					if (pathFile.toFile().delete()) {
						Logger.getLogger(this.getClass()).info("Delete learning file: " + pathFile.toFile().getAbsolutePath());
					}
				}
				list.close();
			}

			userSession = AdminServiceImpl.getAdminSession();

			for (String r_object_id : r_object_ids) {
				IDfFolder fold = (IDfFolder) userSession.getObjectByQualification("dm_folder where r_object_id='" + r_object_id + "'");
				String folderPath = fold.getAllRepeatingStrings("r_folder_path", "");

// @formatter:off				
                IDfQuery q = new DfQuery("select r_object_id from dm_document where folder('" + folderPath + "') and (a_content_type='pdf' or "
                        + "a_content_type='tiff' or "
                        + "a_content_type='msg' or "
                        + "a_content_type='odt' or "
                        + "a_content_type='csv' or "
                        + "a_content_type='msw12' or "
                        + "a_content_type='tiff' or "
                        + "a_content_type='tiff')"
                );
// @formatter:on				
				IDfCollection coll = q.execute(userSession, IDfQuery.DF_READ_QUERY);
				while (coll.next()) {
					String id = coll.getString("r_object_id");
					IDfDocument sysObj = (IDfDocument) userSession.getObject(new DfId(id));

					File f = new File(path.toFile().getAbsolutePath() + "/" + id + "." + sysObj.getFormat().getDOSExtension());
					sysObj.getFile(f.getAbsolutePath());

					// pdf extract images from pdf
					// Pdf2TiffConverter conv = new
					// Pdf2TiffConverter(f.getAbsolutePath());
					// ByteArrayOutputStream baOs = new ByteArrayOutputStream();
					// baOs = conv.converter2tiff(200);
					//
					// String tiffFileName = f.getAbsolutePath() + ".tiff";
					// OutputStream outputStream = new FileOutputStream(tiffFileName);
					// baOs.close();
					// baOs.flush();
					// baOs.writeTo(outputStream);
					// f.delete();
					// outputStream.close();
					// f = new File(tiffFileName);
					BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

					TesseractOCRConfig config = new TesseractOCRConfig();
					config.setLanguage("slv+eng");

					PDFParserConfig pdfConfig = new PDFParserConfig();
					pdfConfig.setExtractInlineImages(true);

					ParseContext parseContext = new ParseContext();
					parseContext.set(TesseractOCRConfig.class, config);
					parseContext.set(PDFParserConfig.class, pdfConfig);
					// need to add this to make sure recursive parsing happens!
					parseContext.set(Parser.class, parser);

					FileInputStream stream = new FileInputStream(f.getAbsolutePath());
					org.apache.tika.metadata.Metadata metadata = new org.apache.tika.metadata.Metadata();
					long t1 = System.currentTimeMillis();
					parser.parse(stream, handler, metadata, parseContext);
					long t2 = System.currentTimeMillis();
					String durationStr = String.format(Locale.ROOT, "Parsing for r_object_id: %s took %.2f s.", id, ((t2 - t1) / 1000.0f));
					Logger.getLogger(this.getClass()).info(durationStr);
					String content = handler.toString();

					String txtFileName = f.getAbsolutePath() + ".txt";
					PrintWriter out = new PrintWriter(txtFileName, "UTF-8");
					out.println(content);
					out.close();
					f.delete();
					f = new File(txtFileName);

					// if (f != null && Files.size(f.toPath()) > 0) {
					// // "curl -T testpdf.pdf http://localhost:9998/tika";
					//
					// Runtime rt = Runtime.getRuntime();s
					// String[] commands = { "curl", "-T", f.getAbsolutePath(),
					// "http://localhost:9998/tika" };
					// Process proc = rt.exec(commands);
					//
					// BufferedReader stdInput = new BufferedReader(new
					// InputStreamReader(proc.getInputStream()));
					// BufferedReader stdError = new BufferedReader(new
					// InputStreamReader(proc.getErrorStream()));
					// // Read the output from the command
					// System.out.println("Here is the standard output of the
					// command:\n");
					// String s = null;
					//
					// File fTxt = new File(f.getAbsolutePath() + "." + "txt");
					// BufferedWriter writer = new BufferedWriter(new
					// FileWriter(fTxt.getAbsolutePath()));
					// while ((s = stdInput.readLine()) != null) {
					// writer.write(s + "\n");
					// // if tika returns ocr-ed text
					// // Logger.getLogger(this.getClass()).info(s);
					// }
					// writer.close();
					// // f.delete();
					//
					// // Read any errors from the attempted command
					// // System.out.println("Here is the standard error of the command
					// (if
					// // any):\n");
					// while ((s = stdError.readLine()) != null) {
					// Logger.getLogger(this.getClass()).error(s);
					// }
					//
					// }
				}
				coll.close();
			}

		} catch (Throwable ex) {
			Logger.getLogger(this.getClass()).error(ex);
		} finally {
			if (userSession != null) {
				userSession.getSessionManager().release(userSession);
			}
		}

		return null;
	}

	@Override
	public Void syncERenderTemplate(String loginName, String password, String r_object_id) throws ServerException {

		String[] allEndpoints = { "http://10.115.4.149:8081/PdfGenerator/services?wsdl",
				"http://erender-test.ts.telekom.si:8080/PdfGenerator/services?wsdl",
				"http://erender-test.ts.telekom.si:8080/PdfGeneratorStaging/services?wsdl",
				"http://erender-test.ts.telekom.si:8080/PdfGeneratorSb1/services?wsdl",
				"http://erender-test.ts.telekom.si:9080/PdfGeneratorSb2/services?wsdl",
				"http://bpl1-kaksisa1-v.ts.telekom.si:8080/PdfGenerator/services?wsdl",
				"http://bpl2-kaksisa2-v.ts.telekom.si:8080/PdfGenerator/services?wsdl" };

		QName qname = new QName("http://templates.mobitel.com/", "PdfGeneratorImplService");

		for (String wsdlEndpoint : allEndpoints) {

			try {
				URL serviceUrl = new URL(wsdlEndpoint);
				PdfGeneratorImplService pdfGeneratorImplService = new PdfGeneratorImplService(serviceUrl, qname);
				PdfGenerator client = pdfGeneratorImplService.getPdfGeneratorImplPort();

				// final Binding binding = ((BindingProvider) client).getBinding();
				// List<Handler> handlerList = binding.getHandlerChain();
				// if (handlerList == null)
				// handlerList = new ArrayList<Handler>();
				//
				// handlerList.add(new SecurityHandler("gdpr-system",
				// "KulskoGeslo321."));
				// binding.setHandlerChain(handlerList);
				//
				// RequestMessageHeader header = new RequestMessageHeader();
				// header.setTransactionId("BusinessNotif-" +
				// System.currentTimeMillis());
				// GetCatalogRequestMsg msg = new GetCatalogRequestMsg();
				// msg.setHeader(header);
				// msg.setName("process.milestones");
				client.syncTemplate(r_object_id);

				Logger.getLogger(this.getClass()).error("Synced template on: " + wsdlEndpoint + " endpoint.");
			} catch (Exception ex) {
				String stackTrace = ExceptionUtils.getStackTrace(ex);
				Logger.getLogger(this.getClass()).error("Error calling syncTemplate for user: " + loginName);
				Logger.getLogger(this.getClass()).error(stackTrace);
			}
		}
		return null;
	}

	@Override
	public Void configureERender(String loginName, String password, String xml) throws ServerException {

		String[] allEndpoints = { "http://localhost:8081/PdfGenerator/services?wsdl",
				"http://erender-test.ts.telekom.si:8080/PdfGeneratorStaging/services?wsdl",
				"http://erender-test.ts.telekom.si:8080/PdfGeneratorSb1/services?wsdl",
				"http://erender-test.ts.telekom.si:9080/PdfGeneratorSb2/services?wsdl",
				"http://bpl1-kaksisa1-v.ts.telekom.si:8080/PdfGenerator/services?wsdl",
				"http://bpl2-kaksisa2-v.ts.telekom.si:8080/PdfGenerator/services?wsdl" };

		QName qname = new QName("http://templates.mobitel.com/", "PdfGeneratorImplService");

		for (String wsdlEndpoint : allEndpoints) {

			try {
				URL serviceUrl = new URL(wsdlEndpoint);
				PdfGeneratorImplService pdfGeneratorImplService = new PdfGeneratorImplService(serviceUrl, qname);
				PdfGenerator client = pdfGeneratorImplService.getPdfGeneratorImplPort();
				Logger.getLogger(this.getClass()).error("configureERender on: " + wsdlEndpoint + " endpoint started.");
				client.configureERender(xml);
				Logger.getLogger(this.getClass()).error("configureERender on: " + wsdlEndpoint + " endpoint completed.");
			} catch (Exception ex) {
				String stackTrace = ExceptionUtils.getStackTrace(ex);
				Logger.getLogger(this.getClass()).error("Error calling syncTemplate for user: " + loginName);
				Logger.getLogger(this.getClass()).error(stackTrace);
			}
		}
		return null;
	}

	@Override
	public List<String> decryptZip(String loginName, String password, String r_object_id, String rObjectIdOfCertWithPK) throws ServerException {
		ArrayList<String> ret = new ArrayList<String>();
		IDfSession userSession = null;

		// String openSSL = "/usr/local/ssl/bin/openssl";
		String openSSL = "openssl";

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfSysObject sysObj = (IDfSysObject) userSession.getObjectByQualification("dm_document where r_object_id='" + r_object_id + "'");

			String fileName = FileUtils.getTempDirectory().getAbsolutePath() + "/" + System.currentTimeMillis() + ".tar";
			Logger.getLogger(this.getClass()).info("Tar fileName: " + fileName);
			sysObj.getFile(fileName);
			File tarFile = new File(fileName);

			if (tarFile.length() == 0) {
				throw new ServerException("File size is 0 bytes! Possibly defective tar file.");
			}

			Path tempDirWithPrefix = Files.createTempDirectory("decryptTar-");
			tempDirWithPrefix.toFile().deleteOnExit();

			IDfSysObject sysObjPk = (IDfSysObject) userSession.getObject(new DfId(rObjectIdOfCertWithPK));
			File pkTempFile = new File(tempDirWithPrefix.toFile().getAbsolutePath() + "/keystore.pfx");
			sysObjPk.getFile(pkTempFile.getAbsolutePath());
			pkTempFile.deleteOnExit();

			KeyStore keystore = KeyStore.getInstance("PKCS12");
			// KeyStore keystore = KeyStore.getInstance("BKS", "BC");
			String p12Password = "valuid";
			keystore.load(new FileInputStream(pkTempFile), p12Password.toCharArray());

			try {
				Enumeration aliases = keystore.aliases();
				for (; aliases.hasMoreElements();) {
					String alias = (String) aliases.nextElement();
					CertificateFactory certificateFactory = certificateFactory = CertificateFactory.getInstance("X.509");
					ByteArrayInputStream bais = new ByteArrayInputStream(keystore.getCertificate(alias).getEncoded());
					X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(bais);
					Date certExpiryDate = x509Certificate.getNotAfter();

					SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					// Tue Oct 17 06:02:22 AEST 2006
					Date today = new Date();
					long dateDiff = certExpiryDate.getTime() - today.getTime();
					long expiresIn = dateDiff / (24 * 60 * 60 * 1000);
					String msg = "Certificate: " + alias + "\tExpires On: " + certExpiryDate + "\tFormated Date: " + ft.format(certExpiryDate)
							+ "\tToday's Date: " + ft.format(today) + "\tExpires In: " + expiresIn;
					WsServer.log(loginName, msg);
				}
			}

			catch (Exception e) {
				e.printStackTrace();
			}

			// export private key
			File pk = new File(tempDirWithPrefix.toFile().getAbsolutePath() + "/key.pem");

			String commandExportPK = openSSL + " pkcs12 -nodes -in " + pkTempFile.getAbsolutePath() + " -nocerts -out " + pk.getAbsolutePath()
					+ " -password pass:" + p12Password;
			Process p = Runtime.getRuntime().exec(commandExportPK.split(" (?=(([^'\\\"]*['\\\"]){2})*[^'\\\"]*$)"));
			ByteArrayOutputStream baOsErr = new ByteArrayOutputStream();
			PrintStream psErr = new PrintStream(baOsErr);
			inheritIO(p.getErrorStream(), psErr);
			p.waitFor();
			if (baOsErr.toString().length() > 0) {
				Logger.getLogger(this.getClass()).info(baOsErr.toString());
				// throw new ServerException(baOsErr.toString());
			}

			// remove pass from pk
			File pkWithoutPass = new File(tempDirWithPrefix.toFile().getAbsolutePath() + "/server.key");
			String commandremovePassFromPK = openSSL + " rsa -in " + pk.getAbsolutePath() + " -out " + pkWithoutPass.getAbsolutePath();
			p = Runtime.getRuntime().exec(commandremovePassFromPK.split(" (?=(([^'\\\"]*['\\\"]){2})*[^'\\\"]*$)"));
			baOsErr = new ByteArrayOutputStream();
			psErr = new PrintStream(baOsErr);
			inheritIO(p.getErrorStream(), psErr);
			p.waitFor();
			if (baOsErr.toString().length() > 0) {
				Logger.getLogger(this.getClass()).info(baOsErr.toString());
			}

			// export certificate
			File cert = new File(tempDirWithPrefix.toFile().getAbsolutePath() + "/cert.pem");
			String commandExportCERT = openSSL + " pkcs12 -in " + pkTempFile.getAbsolutePath() + " -nokeys -out " + cert.getAbsolutePath()
					+ " -password pass:" + p12Password;
			p = Runtime.getRuntime().exec(commandExportCERT.split(" (?=(([^'\\\"]*['\\\"]){2})*[^'\\\"]*$)"));
			baOsErr = new ByteArrayOutputStream();
			psErr = new PrintStream(baOsErr);
			inheritIO(p.getErrorStream(), psErr);
			p.waitFor();
			if (baOsErr.toString().length() > 0) {
				Logger.getLogger(this.getClass()).info(baOsErr.toString());
				// throw new ServerException(baOsErr.toString());
			}

			// env.put("create", "true");

			unTar(tarFile, tempDirWithPrefix.toFile());
			int[] count = { 0 };
			final boolean isEnded = false;

			SimpleFileVisitor<Path> myVisitor = new SimpleFileVisitor<Path>() {
				private int depth = -1;
				private long filesCount;

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					// TODO Auto-generated method stub
					depth++;
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (exc == null) {
						depth--;
						return FileVisitResult.CONTINUE;
					} else {
						throw exc;
					}
				}

				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
					// if (path.toFile().getAbsolutePath().endsWith("wav.gz")) {
					// You can do anything you want with the path here
					Path copied = Paths.get(tempDirWithPrefix.toFile().getAbsoluteFile().getAbsolutePath(), path.toFile().getName());
					// Files.copy(path, copied, StandardCopyOption.REPLACE_EXISTING);
					// copied.toFile().deleteOnExit();

					if (copied.toFile().getAbsolutePath().endsWith(".gz")) {
						// we need to decompress with gunzip
						int loca = copied.toFile().getAbsolutePath().indexOf(".gz");
						String fullPathOfDecompressed = new File(copied.toFile().getAbsolutePath().substring(0, loca)).getAbsolutePath();
						Path pathDecompresed = new File(fullPathOfDecompressed).toPath();
						decompressGzip(copied, pathDecompresed);
						CopyOption[] options = new CopyOption[] { java.nio.file.StandardCopyOption.COPY_ATTRIBUTES,
								java.nio.file.StandardCopyOption.REPLACE_EXISTING };
						copied = pathDecompresed;

						File decryptedFile = new File(
								copied.toFile().getAbsolutePath() + ".decrypted." + FilenameUtils.getExtension(copied.toFile().getAbsolutePath()));

//@formatter:off			
			    		String command = openSSL + " cms -decrypt -binary "
			    				+ "-in "+copied.toFile().getAbsolutePath()+" "
	    						+ "-inform SMIME "
			    				+	"-inkey " + pkWithoutPass.getAbsolutePath() + " "
//			    				+ "-recip " + cert.getAbsolutePath() + " "
//									+	"-recip " + pemFile.getAbsolutePath() + " "	    						
  								+ "-out " + decryptedFile.getAbsolutePath() + " " 
//  								+ "-CAfile /home/klemen/temp/Test_TS_Storitve_CA_Test_TS_Storitve_Root_.cer" + " "
  								+ "-debug_decrypt";
//@formatter:on			
						Logger.getLogger(this.getClass()).info(command);
						Process p = Runtime.getRuntime().exec(command.split(" "));
						ByteArrayOutputStream baOsErr = new ByteArrayOutputStream();
						PrintStream psErr = new PrintStream(baOsErr);
						ByteArrayOutputStream baOsOut = new ByteArrayOutputStream();
						PrintStream psOut = new PrintStream(baOsOut);
						inheritIO(p.getErrorStream(), psErr);
						inheritIO(p.getInputStream(), psOut);
						try {
							p.waitFor();
							Logger.getLogger(this.getClass()).info("OpenSSL out:" + baOsOut.toString() + " err: " + baOsErr.toString());
							if (baOsErr.toString().length() > 0) {
								String[] splittedLines = baOsErr.toString().split("\n");
								String wholeError = "";
								for (String line : splittedLines) {
									if (line.trim().length() > 0) {
										wholeError = wholeError + "\n" + line;
									}
								}
								// throw new ServerException(wholeError);
								Logger.getLogger(this.getClass()).error("OpenSSL out:" + wholeError);
								WsServer.log(loginName, wholeError);
							} else {
								filesCount++;
								ret.add(decryptedFile.getAbsolutePath());
							}

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					// }
					return FileVisitResult.CONTINUE;
				}

			};
			Files.walkFileTree(tempDirWithPrefix, myVisitor);

			WsServer.log(loginName, "Decrypted " + ret.size() + " files.");
		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).error(ex);
			WsServer.log(loginName, ex.getMessage());
		} finally {
			if (userSession != null)
				if (userSession.isConnected()) {
					userSession.getSessionManager().release(userSession);
				}
		}
		return ret;
	}

	public static void inheritIO(final InputStream src, final PrintStream dest) {
		new Thread(new Runnable() {
			public void run() {
				Scanner sc = new Scanner(src);
				while (sc.hasNextLine()) {
					dest.println(sc.nextLine() + "\n");
				}
				sc.close();
			}
		}).start();
	}

	/**
	 * Untar an input file into an output file.
	 * 
	 * The output file is created in the output folder, having the same name as
	 * the input file, minus the '.tar' extension.
	 * 
	 * @param inputFile
	 *          the input .tar file
	 * @param outputDir
	 *          the output directory file.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 * @return The {@link List} of {@link File}s with the untared content.
	 * @throws ArchiveException
	 */
	private static List<File> unTar(final File inputFile, final File outputDir) throws FileNotFoundException, IOException, ArchiveException {
		Logger.getLogger(ExplorerServiceImpl.class)
				.info(String.format("Untaring %s to dir %s.", inputFile.getAbsolutePath(), outputDir.getAbsolutePath()));

		final List<File> untaredFiles = new LinkedList<File>();
		final InputStream is = new FileInputStream(inputFile);
		final TarArchiveInputStream debInputStream = (TarArchiveInputStream) new ArchiveStreamFactory().createArchiveInputStream("tar", is);
		TarArchiveEntry entry = null;
		while ((entry = (TarArchiveEntry) debInputStream.getNextEntry()) != null) {
			final File outputFile = new File(outputDir, entry.getName());
			if (entry.isDirectory()) {
				Logger.getLogger(ExplorerServiceImpl.class).info(String.format("Attempting to write output directory %s.", outputFile.getAbsolutePath()));
				if (!outputFile.exists()) {
					Logger.getLogger(ExplorerServiceImpl.class).info(String.format("Attempting to create output directory %s.", outputFile.getAbsolutePath()));
					if (!outputFile.mkdirs()) {
						throw new IllegalStateException(String.format("Couldn't create directory %s.", outputFile.getAbsolutePath()));
					}
				}
			} else {
				Logger.getLogger(ExplorerServiceImpl.class).info(String.format("Creating output file %s.", outputFile.getAbsolutePath()));
				final OutputStream outputFileStream = new FileOutputStream(outputFile);
				IOUtils.copy(debInputStream, outputFileStream);
				outputFileStream.close();
			}
			untaredFiles.add(outputFile);
		}
		debInputStream.close();

		return untaredFiles;
	}

	private String getHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public static void decompressGzip(Path source, Path target) throws IOException {

		GZIPInputStream gis = new GZIPInputStream(new FileInputStream(source.toFile()));
		FileOutputStream fos = new FileOutputStream(target.toFile());

		// copy GZIPInputStream to FileOutputStream
		byte[] buffer = new byte[1024];
		int len;
		while ((len = gis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
	}

	@Override
	public String collaboraUrl(String format, String action) throws ServerException {
		String ret = "";
		IDfSession adminSession = null;
		try {
			adminSession = AdminServiceImpl.getAdminSession();
			String dosExtension = adminSession.getFormat(format).getDOSExtension();
			if (collaboraUrls == null) {

				collaboraUrls = new HashMap<String, String>();
				URL url = new URL(collaboraUrl);

				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				org.w3c.dom.Document doc = db.parse(url.openStream());

				NodeList nl = doc.getElementsByTagName("action");
				for (int i = 0; i < nl.getLength(); i++) {
					Element elAction = (Element) nl.item(i);
					String fmt = elAction.getAttribute("ext");
					String act = elAction.getAttribute("name");
					String uri = elAction.getAttribute("urlsrc");
					collaboraUrls.put(fmt + ":" + act, uri);
				}

				// Close the input stream
				ret = collaboraUrls.get(dosExtension + ":" + action);
				if (ret == null) {
					for (String key : collaboraUrls.keySet()) {
						if (key.startsWith(dosExtension)) {
							ret = collaboraUrls.get(key);
							break;
						}
					}
				}
			} else {
				ret = collaboraUrls.get(dosExtension + ":" + action);
				if (ret == null) {
					for (String key : collaboraUrls.keySet()) {
						if (key.startsWith(dosExtension)) {
							ret = collaboraUrls.get(key);
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			throw new ServerException(ex.getMessage());
		} finally {
			if (adminSession != null && adminSession.isConnected())
				adminSession.getSessionManager().release(adminSession);
		}

		return ret;
	}

	@Override
	public List<String> erenderTemplateFields(Integer typeId) throws ServerException {
		QName qname = new QName("http://erender.telekom.si/", "ERenderImplService");
		ArrayList<String> ret = new ArrayList<String>();
		URL serviceUrl;
		try {
			serviceUrl = new URL(AdminServiceImpl.ERENDER_WSDL_ENDPOINT);
			Logger.getLogger(this.getClass()).info("ERender webservice url endpoint: " + serviceUrl);
			ERenderImplService erenderImplService = new ERenderImplService(serviceUrl, qname);
			ERender client = erenderImplService.getERenderImplPort();
			List<KeyValue> templFields = client.getTemplateFields(typeId.intValue());
			for (KeyValue keyValue : templFields) {
				ret.add(keyValue.getKey());
			}  
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(e);
		}
		
		return ret;
	}

}
