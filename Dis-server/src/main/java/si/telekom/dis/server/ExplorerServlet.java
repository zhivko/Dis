package si.telekom.dis.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hsmf.MAPIMessage;
import org.simplejavamail.outlookmessageparser.OutlookMessageParser;
import org.simplejavamail.outlookmessageparser.model.OutlookAttachment;
import org.simplejavamail.outlookmessageparser.model.OutlookFileAttachment;
import org.simplejavamail.outlookmessageparser.model.OutlookMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfId;
import com.documentum.xml.xalan.utils.SystemIDResolver;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import si.telekom.dis.shared.DocumentViewFileTypes;
import si.telekom.dis.shared.ServerException;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
// @RemoteServiceRelativePath("login")
public class ExplorerServlet extends HttpServlet {

	String loginName;
	String loginPassword;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		loginName = req.getParameter("loginName");
		loginPassword = req.getParameter("loginPassword");
		String rObjectId = req.getParameter("r_object_id");
		String rendition = req.getParameter("rendition");
		String downloadZip = req.getParameter("downloadZip");

		boolean download = Boolean.valueOf(req.getParameter("download"));

		IDfSession userSession = null;
		ByteArrayOutputStream baOs = null;

		boolean foundContent = false;
		String mimeType = "";
		String fileName = "";
		String dosExtension = "";

		boolean transformedToHTML = false;

		Logger.getLogger(this.getClass()).info("ExplorerServlet called.");
		File tmpFile = null;

		try {
			baOs = new ByteArrayOutputStream();

			if (downloadZip != null) {
				File f = new File(downloadZip);
				FileInputStream fIS = new FileInputStream(f);

				baOs.write(IOUtils.toByteArray(fIS));
				resp.setHeader("Expires", "0");
				resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
				resp.setHeader("Pragma", "public");
				resp.setContentLength(baOs.size());
				resp.setContentType("application/x-download");
				resp.setHeader("Content-Disposition", "attachment; filename=" + "\"" + f.getName() + "\"");
				
				resp.setStatus(200);

				ServletOutputStream out = resp.getOutputStream();
				baOs.writeTo(out);
				out.flush();

				if (f.delete())
					Logger.getLogger(this.getClass()).info("Delete temp zip file succeded.");
				else
					Logger.getLogger(this.getClass()).warn("Delete temp zip file succeded.");

				return;
			}

			if (loginName == null)
				throw new Exception("LoginName should not be null");
			if (loginPassword == null)
				throw new Exception("Password should not be null");

			userSession = AdminServiceImpl.getSession(loginName, loginPassword);
			IDfPersistentObject persObj = userSession.getObject(new DfId(rObjectId));
			IDfSysObject sysObj = (IDfSysObject) persObj;
			if (rendition.equals(""))
				rendition = sysObj.getContentType();
			IDfFormat format = userSession.getFormat(rendition);
			String title = sysObj.getString("title");

			mimeType = format.getMIMEType().toString();
			dosExtension = format.getDOSExtension();
			//fileName = (title != null ? title : sysObj.getObjectName()) + "." + dosExtension;
			fileName = sysObj.getObjectName() + "." + dosExtension;

			ByteArrayInputStream bacontentStreamIs = sysObj.getContentEx(rendition, 0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

			if (rendition.equals("msg")) {
				transformedToHTML = true;
				File msgfile = File.createTempFile(sysObj.getObjectId().getId().toString(), rendition);
				IOUtils.copy(bacontentStreamIs, new FileOutputStream(msgfile.getAbsolutePath()));

				OutlookMessageParser msgp = new OutlookMessageParser();
				OutlookMessage msg = msgp.parseMsg(msgfile.getAbsolutePath());
				
				// @formatter:off
				baOs.write(("<!DOCTYPE html>" + "<html>" + "<head>" + "<title>Mail</title>" + "</head>" + "<body>")
						.getBytes());
				// @formatter:on

				baOs.write(("<strong>DATE:</strong>" + sdf.format(msg.getCreationDate()) + "<br>\n").getBytes());
				baOs.write(("<strong>FROM:</strong>" + msg.getFromName() + "(" + msg.getFromEmail() + ")<br>\n").getBytes());
				baOs.write(("<strong>TO:</strong>" + msg.getDisplayTo() + "<br>\n").getBytes());
				baOs.write(("<br>").getBytes());
				baOs.write(("<strong>SUBJECT:</strong>" + msg.getSubject() + "<br>\n").getBytes());

				baOs.write(msg.getConvertedBodyHTML().getBytes());

				Logger.getLogger(this.getClass().getName()).info(msg.getConvertedBodyHTML());

				for (OutlookAttachment att : msg.getOutlookAttachments()) {
					if (att instanceof OutlookFileAttachment) {
						OutlookFileAttachment ofa = (OutlookFileAttachment) att;
						String filename = ofa.getFilename();
						long fileSize = ofa.getSize();

						Logger.getLogger(this.getClass().getName()).info("Attachment: " + filename + " size: " + fileSize);

						String encodedStr = Base64.getEncoder().withoutPadding().encodeToString(ofa.getData());

						// height=\"1000\" width=\"800\"
						if (filename.toLowerCase().endsWith("pdf")) {
							baOs.write(("<iframe src=\"data:" + ofa.getMimeTag() + ";base64," + encodedStr
									+ "\" style=\"width:100%; height:500px;\" frameborder=\"0\"></iframe>").getBytes("UTF-8"));
						} else
							baOs.write(("<img alt=\"" + filename + "\" src=\"data:" + ofa.getMimeTag() + ";base64," + encodedStr + "\" />").getBytes("UTF-8"));
					} else if (att instanceof OutlookAttachment) {
					}
					baOs.write(("<br>").getBytes());
				}

				baOs.write(("</body>").getBytes("UTF-8"));

				bacontentStreamIs = new ByteArrayInputStream(baOs.toByteArray());

			} else if (rendition.equals("tiff") || rendition.equals("png") || rendition.equals("jpg")) {
				if (!rendition.equals("png")) {
					Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(rendition);
					ImageReader reader = (ImageReader) iterator.next();
					ImageInputStream iis = ImageIO.createImageInputStream(bacontentStreamIs);
					reader.setInput(iis, true);
					ImageReadParam param = reader.getDefaultReadParam();
					BufferedImage bi = reader.read(0, param);

					tmpFile = File.createTempFile(sysObj.getObjectId().getId().toString(), "." + rendition);
					String msg = "Transformation from: " + rendition + " to: png started...";
					Logger.getLogger(this.getClass()).info(msg);
					WsServer.log(loginName, msg);
					ImageIO.write(bi, "png", tmpFile);
					msg = "Transformation from: " + rendition + " to: png end. Image size: " + reader.getWidth(0) + "x" + reader.getHeight(0);
					WsServer.log(loginName, msg);
					Logger.getLogger(this.getClass()).info(msg);
					bacontentStreamIs = new ByteArrayInputStream(FileUtils.readFileToByteArray(tmpFile));
				} else {

				}
			} else if (rendition.equals("odt")) {
				if (sysObj.hasAttr("mob_template_id")) {
					// make transformation odt fill in all custom fields
					int id = Integer.valueOf(sysObj.getString("mob_template_id")).intValue();
					bacontentStreamIs = makeSureAllFieldsExist(bacontentStreamIs, id);
				}
			} else if (rendition.equals("xml")) {
				TransformerFactory factory = TransformerFactory.newInstance();
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = docBuilderFactory.newDocumentBuilder();
				ByteArrayInputStream baIs = sysObj.getContentEx(format.getName(), 0);
				Document document = builder.parse(baIs);
				DOMSource domSource = new DOMSource(document);
				MyTransformerErrorListener transfErrList = new MyTransformerErrorListener();
				factory.setErrorListener(transfErrList);

				try {
					Source xslSource = factory.getAssociatedStylesheet(domSource, null, null, null);
					if (xslSource != null) {
						Logger.getLogger(getClass()).info("stylesheet: " + xslSource.getSystemId());

						URIResolver uriResolver = factory.getURIResolver();

						String href = SystemIDResolver.getAbsoluteURI(xslSource.getSystemId(), null);

						URL url = new URL(href);
						URLConnection con = url.openConnection();
						InputStream in = con.getInputStream();
						String encoding = con.getContentEncoding();
						encoding = encoding == null ? "UTF-8" : encoding;
						String body = IOUtils.toString(in, encoding);
						InputSource is = new InputSource(new ByteArrayInputStream(body.getBytes()));

						// String fileHref =
						// "/home/klemen/Downloads/SkupinaTelekom_2_0_vizualizacija.xslt";

						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						Document documentXSLT = dbf.newDocumentBuilder().parse(is);

						XPathFactory xpf = XPathFactory.newInstance();
						XPath xpath = xpf.newXPath();
						XPathExpression expression = xpath.compile("//*[local-name() = 'output' and @method='html' and @version='1.0']");
						// XPathExpression expression = xpath.compile("//*[local-name() =
						// 'output']");
						Node b13Node = (Node) expression.evaluate(documentXSLT, XPathConstants.NODE);

						SAXSource source;
						if (b13Node != null) {
							b13Node.getParentNode().removeChild(b13Node);
							TransformerFactory tf = TransformerFactory.newInstance();
							Transformer t = tf.newTransformer();
							ByteArrayOutputStream baOsNewXSLT = new ByteArrayOutputStream();
							t.transform(new DOMSource(documentXSLT), new StreamResult(baOsNewXSLT));
							source = new SAXSource(new InputSource(new ByteArrayInputStream(baOsNewXSLT.toString().getBytes())));
						} else {
							source = new SAXSource(is);
						}

						Transformer transformer = factory.newTransformer(source);
						transformer.setErrorListener(new ErrorListener() {

							@Override
							public void warning(TransformerException exception) throws TransformerException {
								System.out.println("warning: " + exception.getMessage());
							}

							@Override
							public void fatalError(TransformerException exception) throws TransformerException {
								System.out.println("fatal error: " + exception.getMessage());
							}

							@Override
							public void error(TransformerException exception) throws TransformerException {
								System.out.println("error: " + exception.getMessage());
							}
						});

						if (!transfErrList.getMsgs().equals("")) {
							String msg = "Unsucesfull transforming xml with stlyesheet: " + xslSource.getSystemId() + "\n" + transfErrList.getMsgs();
							Logger.getLogger(getClass()).info(msg);
							WsServer.log(loginName, msg);
							baOs.write(transfErrList.getMsgs().getBytes());
							baOs.write("\n".getBytes());
						}

						if (transformer != null) {
							String msg = "Succesfully transformed xml with stlyesheet: " + xslSource.getSystemId();
							baOs = new ByteArrayOutputStream();
							StreamResult result = new StreamResult(baOs);
							transformer.setOutputProperty(OutputKeys.METHOD, "html");
							transformer.transform(domSource, result);
							bacontentStreamIs = new ByteArrayInputStream(baOs.toByteArray());
							transformedToHTML = true;
							WsServer.log(loginName, msg);
							Logger.getLogger(getClass()).info(msg);

						}
					} else {

						Transformer transformer = TransformerFactory.newInstance().newTransformer();
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
						// initialize StreamResult with File object to save to file
						StreamResult result = new StreamResult(new StringWriter());
						DOMSource source = new DOMSource(document);
						transformer.transform(source, result);
						String xmlString = result.getWriter().toString();

						// ByteArrayInputStream baIs2 =
						// sysObj.getContentEx(format.getName(),
						// 0);
						// byte[] bytes = IOUtils.toByteArray(baIs2);
						// String content = new String(bytes);
						// xmlString = xmlString.replace("\n", "\n<br>");
						// String newContent = escapeXml(xmlString);
						bacontentStreamIs = new ByteArrayInputStream(xmlString.getBytes());
					}
					baIs.close();
				} catch (Exception ex1) {
					String msg = "Unable to process xml: " + ex1.getMessage();
					WsServer.log(loginName, msg);
					Logger.getLogger(this.getClass()).warn(msg);
				}
			}

			baOs.write(IOUtils.toByteArray(bacontentStreamIs));
			resp.setHeader("Expires", "0");
			resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			resp.setHeader("Pragma", "public");
			resp.setContentLength(baOs.size());
			resp.setStatus(200);

			if ((DocumentViewFileTypes.couldDisplayFormats.contains(rendition)) && !download) {
				if (transformedToHTML)
					resp.setContentType("text/html");
				else
					resp.setContentType(mimeType);
				resp.setHeader("Content-disposition", "inline; filename=content." + dosExtension);
				resp.setHeader("fileName", "\"" + fileName + "." + dosExtension + "\"");
			} else {
				resp.setContentType("application/x-download");
				resp.setHeader("Content-Disposition", "attachment; filename=" + "\"" + fileName + "\"");
			}

			ServletOutputStream out = resp.getOutputStream();
			baOs.writeTo(out);
			out.flush();

		} catch (Throwable ex) {
			resp.setStatus(404);
			resp.setContentType("text/html; charset=UTF-8");
			baOs.write("Napaka pri pridobivanju vsebine.".getBytes("UTF-8"));
			Logger.getLogger(this.getClass()).error(ex.getMessage());
			String stacktrace = ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(this.getClass()).error(stacktrace);
			new ServerException(ex);
		} finally {
			try {
				if (userSession != null)
					userSession.getSessionManager().release(userSession);

				if (tmpFile != null)
					tmpFile.delete();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		Logger.getLogger(this.getClass()).info("ExplorerServlet ended.");

	}

	public byte[] read(ByteArrayInputStream bais) throws IOException {
		byte[] array = new byte[bais.available()];
		bais.read(array);

		return array;
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

	public static ByteArrayInputStream makeSureAllFieldsExist(ByteArrayInputStream baIs, int templateId) {
// @formatter:off			
			String systemFields[] = {
					"barcode", 
					"komerc_version", 
					"modify_date", 
					"object_name", 
					"object_name_first_part", 
					"object_name_second_part", 
					"r_object_id", 
					"r_version_label", 
					"subscription_inet_id", 
					"type_id", 
					"type_name" 
			};
// @formatter:on		
		byte[] buffer = new byte[2048];
		ZipEntry entry;
		ZipInputStream zis = new ZipInputStream(baIs);
		ByteArrayOutputStream output = null;
		boolean changed = false;
		HashMap<String, byte[]> files = new HashMap<String, byte[]>();

		try {
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.getName().equals("meta.xml")) {
					ArrayList<String> systemFieldsAl = new ArrayList<String>(Arrays.asList(systemFields));

					List<List<String>> fields = AdminServiceImpl.getInstance().getColIdsForTemplate(templateId, 0, -1);
					ArrayList<String> allColIds = new ArrayList<String>();
					for (List<String> list : fields) {
						String col_id = list.get(0);
						allColIds.add(col_id.toLowerCase());
					}

					output = new ByteArrayOutputStream();
					int len = 0;
					while ((len = zis.read(buffer)) > 0) {
						output.write(buffer, 0, len);
					}

					ByteArrayInputStream is = new ByteArrayInputStream(output.toByteArray());

					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					factory.setNamespaceAware(true); // never forget this!
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(is);

					XPathFactory xpathFac = XPathFactory.newInstance();
					XPath xpath = xpathFac.newXPath();
					xpath.setNamespaceContext(new OONamespaceContext());

					// lets remove nodes that are not in col_ids
					XPathExpression expr = xpath.compile("/office:document-meta/office:meta/meta:user-defined");
					NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
					Logger.getLogger(ExplorerServlet.class).info("Length: " + nodes.getLength());
					for (int i = 0; i < nodes.getLength(); i++) {
						Node n = nodes.item(i);
						String attName = n.getAttributes().getNamedItem("meta:name").getNodeValue();
						if (!(allColIds.contains(attName.toLowerCase()) || systemFieldsAl.contains(attName.toLowerCase()))) {
							Logger.getLogger(ExplorerServlet.class).info("Doesn't contain: " + attName + " removing.");
							n.getParentNode().removeChild(n);
							changed = true;
						}
					}

					// lets add nodes that are missing
					XPathExpression expr2 = xpath.compile("/office:document-meta/office:meta");
					Element userDefined = (Element) expr2.evaluate(doc, XPathConstants.NODE);

					allColIds.addAll(systemFieldsAl);

					for (String col_id : allColIds) {
						XPathExpression expr1 = xpath.compile("/office:document-meta/office:meta/meta:user-defined[@meta:name='" + col_id + "']");
						NodeList nodes1 = (NodeList) expr1.evaluate(doc, XPathConstants.NODESET);
						if (nodes1.getLength() == 0) {
							Logger.getLogger(ExplorerServlet.class).info("Doesn't contain: " + col_id + " adding field.");
							Element newNode = null;
							// if (nodes.item(0) != null) {
							// Node elNode = nodes.item(0);
							// newNode = elNode.cloneNode(true);
							// } else {
							newNode = doc.createElement("meta:user-defined");
							newNode.setAttribute("meta:name", col_id);
							newNode.setAttribute("meta:value-type", "string");
							newNode.setTextContent("value");
							// builder.parse(new ByteArrayInputStream("<meta:user-defined
							// meta:name=\"name\">value</meta:user-defined>".getBytes()))
							// .getDocumentElement();
							// }
							// newNode.getAttributes().getNamedItem("meta:name").setNodeValue(col_id);
							userDefined.appendChild(newNode);
							changed = true;
						}
					}

					if (changed) {
						ByteArrayOutputStream ret = new ByteArrayOutputStream();
						// XERCES 1 or 2 additionnal classes.
						OutputFormat of = new OutputFormat("XML", "UTF-8", false);
						of.setPreserveSpace(true);
						of.setIndent(1);
						of.setIndenting(true);

						// of.setDoctype(null,"users.dtd");
						XMLSerializer serializer = new XMLSerializer(ret, of);
						// As a DOM Serializer

						serializer.asDOMSerializer();
						serializer.serialize(doc.getDocumentElement());
						ret.close();

						entry.setMethod(ZipEntry.STORED);
						entry.setCompressedSize(ret.toByteArray().length);
						entry.setSize(ret.toByteArray().length);
						CRC32 crc = new CRC32();
						crc.update(ret.toByteArray());
						entry.setCrc(crc.getValue());

						files.put("meta.xml", ret.toByteArray());
					}

					is.close();
				}
			}

			if (changed) {
				zis.close();
				baIs.reset();
				ZipInputStream zis2 = new ZipInputStream(baIs);
				ByteArrayOutputStream baOs = saveZip2(zis2, files);
				ByteArrayInputStream is = new ByteArrayInputStream(baOs.toByteArray());
				baIs = is;
			}
			zis.close();
			baIs.close();

		} catch (Exception ex) {
			String stackTrace = ExceptionUtils.getStackTrace(ex);
			Logger.getLogger(ExplorerServlet.class).error(ex.getMessage());
			Logger.getLogger(ExplorerServlet.class).error(stackTrace);
		} finally {
		}
		baIs.reset();
		return baIs;
	}

	private static ByteArrayOutputStream saveZip2(ZipInputStream inZip, HashMap<String, byte[]> files) throws Exception {

		ByteArrayOutputStream result = null;
		ZipOutputStream outZip = null;
		try {
			result = new ByteArrayOutputStream();
			outZip = new ZipOutputStream(result);

			for (ZipEntry in; (in = inZip.getNextEntry()) != null;) {
				ZipEntry out = null;
				InputStream source = null;
				for (String fileName : files.keySet()) {
					if (in.getName().equals(fileName)) {
						byte[] contentAsBytes = files.get(fileName);
						out = new ZipEntry(in);

						out.setMethod(ZipEntry.STORED);
						out.setCompressedSize(contentAsBytes.length);
						out.setSize(contentAsBytes.length);
						CRC32 crc = new CRC32();
						crc.update(contentAsBytes);
						out.setCrc(crc.getValue());
						source = new ByteArrayInputStream(contentAsBytes);
						break;
					} else {
						Logger.getLogger(ExplorerServlet.class).info(in.getName());
					}
				}
				if (out == null) {
					out = in;
					source = inZip;
				}
				outZip.putNextEntry(out);
				IOUtils.copy(source, outZip); // Apache's Commons-IO
			}
		} catch (Exception e) {
			throw e;
		} finally {
			outZip.flush();
			inZip.close();
			outZip.close();
		}
		return result;
	}

	public String escapeXml(String s) {
		return s.replaceAll("&", "&amp;").replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\"", "&quot;").replaceAll("'", "&apos;");
	}

}
