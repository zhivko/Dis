package si.telekom.dis.server;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hsmf.MAPIMessage;
import org.simplejavamail.outlookmessageparser.OutlookMessageParser;
import org.simplejavamail.outlookmessageparser.model.OutlookAttachment;
import org.simplejavamail.outlookmessageparser.model.OutlookFileAttachment;
import org.simplejavamail.outlookmessageparser.model.OutlookMessage;

import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfId;

import si.telekom.dis.shared.DocumentViewFileTypes;
import si.telekom.dis.shared.ServerException;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
// @RemoteServiceRelativePath("login")
public class ExplorerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loginName = req.getParameter("loginName");
		String loginPassword = req.getParameter("loginPassword");
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

		Logger.getLogger(this.getClass()).info("ExplorerServlet called.");
		File tmpFile = null;

		try {
			baOs = new ByteArrayOutputStream();
			
			if(downloadZip!=null)
			{
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
				
				if(f.delete())
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
			fileName = (title != null ? title : sysObj.getObjectName()) + "." + dosExtension;

			ByteArrayInputStream bacontentStreamIs = sysObj.getContentEx(rendition, 0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

			if (rendition.equals("msg")) {
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
				baOs.write(("<strong>TO:</strong>" + msg.getToName() + "(" + msg.getToEmail() + ")<br>\n").getBytes());
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
			}

			baOs.write(IOUtils.toByteArray(bacontentStreamIs));
			resp.setHeader("Expires", "0");
			resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			resp.setHeader("Pragma", "public");
			resp.setContentLength(baOs.size());
			resp.setStatus(200);

			if (DocumentViewFileTypes.couldDisplayFormats.contains(rendition) && !download) {
				resp.setContentType(mimeType);
				resp.setHeader("Content-disposition", "inline; filename=content." + dosExtension);
				resp.setHeader("fileName", "\"" + fileName + "\"");
			} else {
				resp.setContentType("application/x-download");
				resp.setHeader("Content-Disposition", "attachment; filename=" + "\"" + fileName + "\"");
			}

			ServletOutputStream out = resp.getOutputStream();
			baOs.writeTo(out);
			out.flush();

		} catch (Exception ex) {
			resp.setStatus(404);
			resp.setContentType("text/html; charset=UTF-8");
			baOs.write("Napaka pri pridobivanju vsebine.".getBytes("UTF-8"));
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
}
