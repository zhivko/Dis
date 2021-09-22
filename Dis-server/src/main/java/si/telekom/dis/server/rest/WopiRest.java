//https://github.com/CollaboraOnline/online/tree/master/docker

//https://sdk.collaboraonline.com/docs/installation/Docker_image.html
//https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup-1.5

// docker container logs --follow collabora

// https://www.linuxbabe.com/ubuntu/integrate-collabora-onlinenextcloud-without-docker

package si.telekom.dis.server.rest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//https://github.com/dmandalidis/docker-client/blob/master/docs/user_manual.md#get-an-archive-of-a-filesystem-resource-in-a-container
import org.mandas.docker.client.DefaultDockerClient;
import org.mandas.docker.client.DockerClient.ListContainersParam;
import org.mandas.docker.client.builder.jersey.JerseyDockerClientBuilder;
import org.mandas.docker.client.messages.Container;

import com.documentum.fc.client.DfAuthenticationException;
import com.documentum.fc.client.DfVersionPolicy;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfVersionPolicy;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.server.ExplorerServlet;
import si.telekom.dis.server.UploadServlet;
import si.telekom.dis.server.WsServer;
import si.telekom.dis.server.rest.UpdateDocumentRequest.VersionEnum;
import si.telekom.dis.server.wopi.FileInfo;
import si.telekom.dis.server.wopi.api.NotFoundException;
import si.telekom.dis.server.wopi.api.impl.WopiApiServiceImpl;
import si.telekom.dis.shared.ServerException;

// https://<WOPI client URL>:<port>/loleaflet/<hash>/loleaflet.html?WOPISrc=https://<WOPI host URL>/<...>/wopi/files/<id>/contents?access_token=<token>

/*

		https://sdk.collaboraonline.com/docs/How_to_integrate.html

    http://admin:admin@localhost:9980/loleaflet/dist/admin/admin.html
    https://admin:admin@localhost:9980/lool/getMetrics
    http://localhost:9980/hosting/capabilities
    http://localhost:9980/hosting/discovery
    
		https://test-dis-dev.apps.okd-test.ts.telekom.si/hosting/discovery
		https://test-dis-dev.apps.okd-test.ts.telekom.si/hosting/capabilities


		https://lool2.friprogramvarusyndikatet.se:9980/hosting/discovery
		https://github.com/CollaboraOnline/online/blob/master/docker/from-packages/RHEL8
		
		http://172.17.0.1:9980/loleaflet/dist/admin/admin.html
		http://10.115.4.149:9980/hosting/discovery
		
		
		https://collabora-dis-dev.apps.okd-test.ts.telekom.si/hosting/discovery
		
		
		https://erender-test:9980/hosting/capabilities
		https://localhost/hosting/discovery

		https://klemen-hp-elitebook-850-g7-notebook-pc.ts.telekom.si:9980/hosting/discovery
		
 */

@Api
public class WopiRest extends WopiApiServiceImpl {

	public WopiRest() {
		super();
	}

	/**
	 * https://www.collaboraoffice.com/libreoffice-online-api/
	 */
	@Override
	public Response getCheckFileInfo(String document, String accessToken, SecurityContext securityContext) throws NotFoundException {
		FileInfo info = new FileInfo();

		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		String action = getActionFromToken(accessToken);

		if (document.split("~").length != 2) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json").entity("Rendition not passed.")
					.build();
		}
		String r_object_id = document.split("~")[0];
		String rendition = document.split("~")[1];

		Logger.getLogger(this.getClass()).info("-----  getCheckFileInfo doc: " + r_object_id + "user:" + loginName + " action: " + action);

		IDfSession userSession = null;

		BufferedInputStream bIs;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfPersistentObject dfDocument = userSession.getObject(new DfId(r_object_id));
			IDfSysObject sysObject = (IDfSysObject) dfDocument;

			IDfCollection colRendition = sysObject.getRenditions("full_format, content_size, set_time");
			Long contentSize = null;
			Date time = null;
			while (colRendition.next()) {
				if (colRendition.getString("full_format").equals(rendition)) {
					contentSize = colRendition.getLong("content_size");
					time = colRendition.getTime("set_time").getDate();
					break;
				}
			}
			colRendition.close();

			bIs = new BufferedInputStream(sysObject.getContentEx(rendition, 0));
			byte[] buffer = new byte[bIs.available()];
			bIs.read(buffer);

			info.setBaseFileName(sysObject.getId("r_object_id").toString() + "." + sysObject.getFormat().getDOSExtension());
			info.setSize(contentSize);
			info.setOwnerId(sysObject.getOwnerName());
			info.setUserId(userSession.getLoginUserName());
			info.setVersion(String.valueOf(sysObject.getModifyDate().getDate().getTime()));
			info.setSha256(getHash256(bIs));
			info.setAllowExternalMarketplace(true);
			info.setUserCanWrite(action.equals("edit") ? true : false);
			info.setSupportsUpdate(action.equals("edit") ? true : false);
			info.setSupportsLocks(action.equals("edit") ? true : false);
			
			info.setHidePrintOption(action.equals("edit") ? true : false);
			info.setDisablePrint(action.equals("edit") ? false : true);
			info.setHideSaveOption(action.equals("edit") ? false : true);
			info.setHideExportOption(action.equals("edit") ? false : true);
			info.setDisableExport(action.equals("edit") ? false : true);
			info.setDisableCopy(false);
			info.setEnableOwnerTermination(true);
			
			info.setLastModifiedTime(formatTime(time));
			info.setUserFriendlyName(userSession.getUser(userSession.getLoginUserName()).getDescription());

			ObjectMapper mapper = new ObjectMapper();

			byte[] res = mapper.writeValueAsBytes(info);
			String result = new String(res);

			bIs.close();

			return Response.status(Response.Status.OK).header("Content-Type", "application/json").entity(result).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		} finally {
			if (userSession != null && userSession.isConnected()) {
				userSession.getSessionManager().release(userSession);
			}
		}
	}

	@Override
	public Response getWopiDocumentContent(String document, String accessToken, SecurityContext securityContext) throws NotFoundException {
		ByteArrayInputStream baIs = null;
		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		String action = getActionFromToken(accessToken);
		IDfSession userSession = null;

		Logger.getLogger(this.getClass()).info("----- getWopiDocumentContent user:" + loginName + " action: " + action + " document: " + document);

		if (document.split("~").length != 2) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json").entity("Rendition not passed.")
					.build();
		}
		String r_object_id = document.split("~")[0];
		String rendition = document.split("~")[1];

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			// IDfPersistentObject dfDocument = userSession.getObject(new
			// DfId(document));
			IDfPersistentObject dfDocument = userSession.getObjectByQualification(
					"dm_document where i_chronicle_id = (select i_chronicle_id from dm_document(all) where r_object_id='" + r_object_id + "')");
			IDfSysObject sysObject = (IDfSysObject) dfDocument;

			if (sysObject.getType().getName().equals("mob_form_template") && sysObject.hasAttr("mob_template_id")) {
				Logger.getLogger("Object is mob_form_template filling properties with col_id.");
				baIs = sysObject.getContentEx(rendition, 0);
				int id = Integer.valueOf(sysObject.getString("mob_template_id")).intValue();
				baIs = ExplorerServlet.makeSureAllFieldsExist(baIs, id);
			} else {
				baIs = sysObject.getContentEx(rendition, 0);
			}
			
			byte[] buffer = new byte[baIs.available()];
			baIs.read(buffer);
			
//@formatter:off
			return Response.status(Response.Status.OK)
					.header("X-WOPI-ItemVersion", sysObject.getModifyDate().getDate().getTime())
					.header("Content-Length", buffer.length)
					.header("Content-Type", "application/json")
					.header("Content-Disposition", "attachment; filename=" + sysObject.getId("r_object_id").toString() + "." + sysObject.getFormat().getDOSExtension())
					.entity(buffer).build();
//@formatter:on
		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).error(ex);
//@formatter:off
			return Response.status(Response.Status.fromStatusCode(500)).entity("Internal error: " + ex.getMessage())
					.header("X-WOPI-LockFailureReason", ex.getMessage())
					.build();
//@formatter:on
		} finally {
			try {
				if (baIs != null)
					baIs.close();
				if (userSession != null && userSession.isConnected())
					userSession.getSessionManager().release(userSession);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Response updateWopiDocumentContent(String document, String accessToken, Boolean xLOOLWOPIIsModifiedByUser, Boolean xLOOLWOPIIsAutosave,
			Boolean xLOOLWOPIIsExitSave, String xLOOLWOPITimestamp, File body, SecurityContext securityContext) throws NotFoundException {
		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		String action = getActionFromToken(accessToken);

		String r_object_id = document.split("~")[0];
		String rendition = document.split("~")[1];

		IDfSession userSession = null;

		Logger.getLogger(this.getClass()).info("----- updateWopiDocumentContent user:" + loginName + " action: " + action);

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			// IDfPersistentObject dfDocument = userSession.getObject(new
			// DfId(document));
			IDfPersistentObject dfDocument = userSession.getObjectByQualification(
					"dm_document where i_chronicle_id = (select i_chronicle_id from dm_document(all) where r_object_id='" + r_object_id + "')");
			IDfSysObject sysObj = (IDfSysObject) dfDocument;

			if (sysObj == null) {
				String msg = "Such document doesn't exist or you dont have permission to view or edit.";
				Logger.getLogger(this.getClass()).warn(msg);
				return Response.status(Response.Status.valueOf("404")).entity(msg).build();
			}

			String timeInDocumentum = formatTime(new Date(sysObj.getModifyDate().getDate().getTime()));
			if (xLOOLWOPITimestamp != null && !xLOOLWOPITimestamp.equals(timeInDocumentum)) {
				// document being edited is not the one that is present in the storage.
				// Hosts should not save the file to storage in such cases and respond
				// with HTTP 409 along with Collabora Online specific status code 409 in
				// json
				// 409 Conflict - Lock mismatch/locked by another interface; an
				// X-WOPI-Lock response header containing the value of the current lock
				// on the file must always be included when using this response code
				//
				// documentum offset from UTC: select r_tz_aware, r_normal_tz from
				// dm_docbase_config -> 1, 3600
				// select DATETOSTRING_LOCAL(date(now),'dd.MM.yyyy hh:mm:ss') from
				// dm_docbase_config

				// https://msroth.wordpress.com/2011/05/15/the-content-server-and-time-zones/
				Logger.getLogger(this.getClass()).warn("Document being edited                         (" + xLOOLWOPITimestamp + ")");
				Logger.getLogger(this.getClass()).warn("is not the one that is present in the storage (" + timeInDocumentum + ")");

				WsServer.log(loginName, "Document beeing edited:  " + xLOOLWOPITimestamp);
				WsServer.log(loginName, "Document beeing in dcmt: " + timeInDocumentum);

				String json = "{'LOOLStatusCode':1010}";
//@formatter:off
				return Response.status(Response.Status.fromStatusCode(409)).entity(json)
						.header("X-WOPI-Lock", sysObj.getModifier())
						.build();
//@formatter:on			
			}

			if (!xLOOLWOPIIsExitSave && !xLOOLWOPIIsAutosave && xLOOLWOPIIsModifiedByUser) {

				if (!sysObj.isCheckedOut()) {
					Logger.getLogger(this.getClass()).warn("Document is not checked out - checkout()");
					sysObj.checkout();
					Logger.getLogger(this.getClass()).warn("Document is not checked out - checkout() done.");
				}
				sysObj.setFile(body.getAbsolutePath());

				String prevVersLabels = "";
				Pattern p = Pattern.compile("^\\d+(\\.\\d+)+$");
				String allVersions = sysObj.getAllRepeatingStrings("r_version_label", ",");
				for (String ver : allVersions.split(",")) {
					Matcher m = p.matcher(ver);
					if (!ver.equals("CURRENT") && !m.find())
						prevVersLabels = prevVersLabels + ver + ",";
				}
				if (prevVersLabels.length() > 0)
					prevVersLabels = prevVersLabels.substring(0, prevVersLabels.length() - 1);

				sysObj.fetch("dm_document");
				boolean latest = ((IDfSysObject) sysObj).getLatestFlag();
				IDfVersionPolicy vp = sysObj.getVersionPolicy();
				boolean canVersionMinor = ((DfVersionPolicy) vp).canVersion(DfVersionPolicy.DF_NEXT_MINOR);
				boolean canVersionMajor = ((DfVersionPolicy) vp).canVersion(DfVersionPolicy.DF_NEXT_MAJOR);
				String msg1 = "objectname: " + sysObj.getObjectName() + " Latest? " + latest + " Immutable? " + sysObj.isImmutable() + " Frozen? "
						+ sysObj.isFrozen() + " permit: " + sysObj.getPermit() + " canVersionMinor: " + canVersionMinor + " canVersionMajor: " + canVersionMajor;
				Logger.getLogger(UploadServlet.class).info(msg1);

				if (!latest) {
					throw new ServerException("Not latest version");
				}

				String toAddVersions;
				IDfId dfnewid = null;

				VersionEnum versionToRaise = VersionEnum.MINOR;

				if (versionToRaise.equals(VersionEnum.MINOR)) {
					toAddVersions = vp.getNextMinorLabel() + ",CURRENT," + prevVersLabels;
					dfnewid = sysObj.checkin(false, toAddVersions);
				} else if (versionToRaise.equals(VersionEnum.MAJOR)) {
					toAddVersions = vp.getNextMajorLabel() + ",CURRENT," + prevVersLabels;
					dfnewid = sysObj.checkin(false, toAddVersions);
				} else {
					dfnewid = sysObj.checkin(false, "");
				}
				IDfSysObject newSysObject = (IDfSysObject) userSession.getObject(dfnewid);
				newSysObject.fetch(null);

				sysObj = newSysObject;
				WsServer.logAll("refreshDocumentWithRObjectId:" + sysObj.getId("r_object_id").toString());

			}

			if (xLOOLWOPIIsExitSave) {
				if (sysObj.isCheckedOut()) {
					sysObj.cancelCheckout();
				}
			}

			FileInfo info = new FileInfo();
			info.setBaseFileName(sysObj.getId("r_object_id").toString() + "." + sysObj.getFormat().getDOSExtension());
			info.setSize(sysObj.getContentSize());
			info.setOwnerId(sysObj.getOwnerName());
			info.setUserId(userSession.getLoginUserName());
			info.setVersion(String.valueOf(sysObj.getModifyDate().getDate().getTime()));
			info.setSha256(getHash256(body));
			info.setAllowExternalMarketplace(true);
			info.setUserCanWrite(action.equals("edit") ? true : false);
			info.setSupportsUpdate(action.equals("edit") ? true : false);
			info.setSupportsLocks(action.equals("edit") ? true : false);
			
			info.setHidePrintOption(action.equals("edit") ? true : false);
			info.setDisablePrint(action.equals("edit") ? false : true);
			info.setHideSaveOption(action.equals("edit") ? false : true);
			info.setHideExportOption(action.equals("edit") ? false : true);
			info.setDisableExport(action.equals("edit") ? false : true);
			info.setDisableCopy(false);
			info.setEnableOwnerTermination(true);			
			
			info.setLastModifiedTime(formatTime(sysObj.getModifyDate().getDate()));
			info.setUserFriendlyName(userSession.getUser(userSession.getLoginUserName()).getDescription());

			ObjectMapper mapper = new ObjectMapper();
			byte[] res = mapper.writeValueAsBytes(info);
			String result = new String(res);

//@formatter:off			
			return Response.ok()
					.header("X-WOPI-ItemVersion", sysObj.getModifyDate().getDate().getTime())
					.header("Content-Type", "application/json").entity(result).build();
//@formatter:on

		} catch (DfAuthenticationException e) {
			Logger.getLogger(this.getClass()).error(e);
			return Response.status(Response.Status.valueOf("401")).entity(e.getMessage()).build();
		} catch (Exception e) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string

			Logger.getLogger(this.getClass()).error(sStackTrace);
			return Response.serverError().build();
		} finally {
			if (userSession != null && userSession.isConnected())
				userSession.getSessionManager().release(userSession);
		}
	}

	private String formatTime(Date date) {

		SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.GERMANY);
		ISO8601DATEFORMAT.setTimeZone(TimeZone.getTimeZone("CET"));
		String formattedTime = ISO8601DATEFORMAT.format(date) + "Z";

		Logger.getLogger(this.getClass()).info("--- formatTime (" + date.toString() + "): " + formattedTime);

		return formattedTime;
	}

	public static void checkCollaboraDockerIsRunning() {

		// if (true)
		// return;

		try {
			final DefaultDockerClient docker = new JerseyDockerClientBuilder().fromEnv().build();

			// detached mode -d

//@formatter:off


/*
docker \
run -t -it \
-p 127.0.0.1:9980:9980 \
-e "domain=.*" \
-e "username=admin" \
-e "password=S3cRet" \
-e extra_params="--o:ssl.enable=false --o:storage.filesystem[@allow]=true" \
-e "LOOL_LOGLEVEL=trace" \
--restart always \
collabora/code
*/
			
			
// docker pull collabora/code:6.4.10.10			
// "--restart always " +

			
			
String dockerStartCmd = 			
"docker " +
"run -t -d " +
"-p 127.0.0.1:9980:9980 " +
"-e \"username=admin\" " +
"-e \"password=sec3et\" " +
"--privileged " +
"-e extra_params=\"--o:ssl.enable=false\" " +
"-e \"LOOL_LOGLEVEL=trace\" " +
"--name collabora "  +
"collabora/code";
			
// reset docker image: docker rmi -f $(docker images -a -q)
// execute bash on container: docker exec -it crazy_poincare bash
// -rw-r----- 1 lool lool 20196 Sep  3 08:49 loolwsd.xml

//@formatter:on

			// docker run -it -p 9980:9980 -e "LOOL_LOGLEVEL=trace" -e
			// extra_params="--o:ssl.enable=false --o:storage.filesystem[@allow]=true"
			// --restart always collabora/code
			// dockerStartCmd = "docker run -d -p 9980:9980 -e \"LOOL_LOGLEVEL=trace\"
			// -e extra_params=\"--o:ssl.enable=false
			// --o:storage.filesystem[@allow]=true\" --restart always collabora/code";
			// dockerStartCmd = "docker run -d -p 127.0.0.1:9980:9980 -e
			// extra_params=\"--o:ssl.enable=false\" --restart always collabora/code";

			ListContainersParam[] params = { new ListContainersParam("image", "collabora/code") };

			List<Container> containers = docker.listContainers(params);

			String containerName = "";
			for (Container container : containers) {
				Logger.getLogger(WopiRest.class).info("Running collabora container: " + container.names().get(0) + " command: " + container.command());
				containerName = container.names().get(0).substring(1, container.names().get(0).length());
			}
			if (containers.size() == 0) {
				// https://sdk.collaboraonline.com/docs/installation/Docker_image.html#dockerfile
				Logger.getLogger(WopiRest.class).info("Starting collabora/code container...");
				Logger.getLogger(WopiRest.class).info(dockerStartCmd);
				Process p = Runtime.getRuntime().exec(dockerStartCmd.split(" (?=(([^'\\\"]*['\\\"]){2})*[^'\\\"]*$)"));
				ByteArrayOutputStream baOsErr = new ByteArrayOutputStream();
				PrintStream psErr = new PrintStream(baOsErr);
				inheritIO(p.getErrorStream(), psErr);
				p.waitFor();
				Logger.getLogger(WopiRest.class).info(baOsErr.toString());
				Logger.getLogger(WopiRest.class).info("Starting collabora/code container...Done.");
				while (containerName.equals("")) {
					for (Container container : containers) {
						Logger.getLogger(WopiRest.class).info("Running collabora container: " + container.names().get(0) + " command: " + container.command()
								+ " ip: " + container.networkSettings().ipAddress());
						containerName = container.names().get(0).substring(1, container.names().get(0).length());
					}
					containers = docker.listContainers(params);
				}
			}

			// docker cp unruffled_turing:/etc/loolwsd/loolwsd.xml
			// /home/klemen/git/Dis/Dis-server/src/main/resources/loolwsd.xml

			// wait until cotainer is in running state:
			while (true) {
				Container container = containers.get(0);
				if (container.state().equals("running")) {
					break;
				} else {
					Logger.getLogger(WopiRest.class).info("Container: " + container.names().get(0) + " is in state: " + container.state());
					Thread.currentThread().sleep(1000);
				}

				containers = docker.listContainers(params);
			}

			// Logger.getLogger(WopiRest.class).info("Copying config file to
			// collabora/code container: " + containerName + "...");
			// String dockerCpCmd = "docker cp
			// /home/klemen/git/Dis/Dis-server/src/main/resources/loolwsd.xml " +
			// containerName + ":/etc/loolwsd/loolwsd.xml";
			//
			// dockerCpCmd = "docker exec -i " + containerName
			// + " sh -c 'cat > /etc/loolwsd/loolwsd.xml' <
			// /home/klemen/git/Dis/Dis-server/src/main/resources/loolwsd.xml";
			//
			// Logger.getLogger(WopiRest.class).info(dockerCpCmd);
			// Process p = Runtime.getRuntime().exec(dockerCpCmd.split("
			// (?=(([^'\\\"]*['\\\"]){2})*[^'\\\"]*$)"));
			// ByteArrayOutputStream baOsErr = new ByteArrayOutputStream();
			// PrintStream psErr = new PrintStream(baOsErr);
			// inheritIO(p.getErrorStream(), psErr);
			// p.waitFor();
			// Logger.getLogger(WopiRest.class).info(baOsErr.toString());
			// Logger.getLogger(WopiRest.class).info("Copying config file to
			// collabora/code container: " + containerName + "...Done.");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}

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

	@GET
	@Path("/{fileName}/contents")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response getFileContent(@PathParam("fileName") String fileName, @Context HttpServletRequest httpRequest) {

		final String filePath = "C:/wopi-docs/" + fileName;
		File file = new File(filePath);

		byte[] content = null;
		try {

			content = FileUtils.readFileToByteArray(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(new ByteArrayInputStream(content)).build();
	}

	public static String getHash256(File f) {
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			return getHash256(fs);
		} catch (Exception e) {
			Logger.getLogger(WopiRest.class).error(e);
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				Logger.getLogger(WopiRest.class).error(e);
			}
		}
		return null;
	}

	public static String getHash256(InputStream is) {
		String value = "";
		try {
			byte[] buffer = new byte[1024];
			int numRead;
			MessageDigest complete = MessageDigest.getInstance("SHA-256");
			do {
				numRead = is.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			value = new String(Base64.getEncoder().encode(complete.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public Response lockDocument(String document, String accessToken, String xWOPILock, String xWOPIOverride, String body,
			SecurityContext securityContext) throws NotFoundException {

		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		String action = getActionFromToken(accessToken);

		if (document.split("~").length != 2) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("Content-Type", "application/json").entity("Rendition not passed.")
					.build();
		}
		String r_object_id = document.split("~")[0];
		String rendition = document.split("~")[1];

		IDfSession userSession = null;

		Logger.getLogger(this.getClass()).info("----- lockDocument user:" + loginName + " action: " + action);

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			// IDfPersistentObject dfDocument = userSession.getObject(new
			// DfId(document));
			IDfPersistentObject dfDocument = userSession.getObjectByQualification(
					"dm_document where i_chronicle_id = (select i_chronicle_id from dm_document(all) where r_object_id='" + r_object_id + "')");

			IDfSysObject sysObj = (IDfSysObject) dfDocument;

			if (xWOPIOverride.equals("UNLOCK")) {
				Logger.getLogger(this.getClass()).info("unlock lockOwner:" + sysObj.getLockOwner() + " user: " + userSession.getLoginUserName());
				if (!sysObj.getLockOwner().equals("") && !sysObj.getLockOwner().equals(userSession.getLoginUserName())) {
					// try unlock
					ExplorerServiceImpl.getInstance().unlock(loginName, password, r_object_id);
				}
//@formatter:off			
				return Response.status(Response.Status.OK)
						.header("X-WOPI-ItemVersion", sysObj.getModifyDate().getDate().getTime())
						.build();
//@formatter:on
			} else {
				try {
					if (!sysObj.getLockOwner().equals(userSession.getLoginUserName()))
						sysObj.checkout();
				} catch (DfException ex1) {
//@formatter:off			
					return Response.status(Response.Status.fromStatusCode(409))
							.entity("Internal error: " + ex1.getMessage())
							.header("X-WOPI-LockFailureReason", ex1.getMessage()).build();
//@formatter:on			

				}
//@formatter:off			
				return Response.status(Response.Status.OK)
						.header("X-WOPI-Lock", sysObj.getLockOwner())
						.header("X-WOPI-ItemVersion", sysObj.getModifyDate().getDate().getTime())
						.build();
//@formatter:on
			}
		} catch (Exception e) {
//@formatter:off			
		return Response.status(Response.Status.fromStatusCode(500)).entity("Internal error: " + e.getMessage())
				.header("X-WOPI-LockFailureReason", e.getMessage())
				.build();
//@formatter:on			
		} finally {
			if (userSession != null && userSession.isConnected())
				userSession.getSessionManager().release(userSession);
		}
	}

	public String getActionFromToken(String token) {
		String decodedToken = new String(Base64.getDecoder().decode(token));
		String action = decodedToken.split(":")[2];
		return action;
	}

	public String getUserFromToken(String token) {
		String decodedToken = new String(Base64.getDecoder().decode(token));
		String user = decodedToken.split(":")[0];
		return user;
	}

	public String getPassFromToken(String token) {
		String decodedToken = new String(Base64.getDecoder().decode(token));
		String pass = decodedToken.split(":")[1];
		return pass;
	}

}