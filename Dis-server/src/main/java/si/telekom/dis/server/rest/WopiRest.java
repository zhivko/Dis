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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

import com.documentum.fc.client.DfVersionPolicy;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfVersionPolicy;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.server.UploadServlet;
import si.telekom.dis.server.rest.UpdateDocumentRequest.VersionEnum;
import si.telekom.dis.server.restCommon.User;
import si.telekom.dis.server.wopi.FileInfo;
import si.telekom.dis.server.wopi.api.NotFoundException;
import si.telekom.dis.server.wopi.api.impl.WopiApiServiceImpl;
import si.telekom.dis.shared.ServerException;

// https://<WOPI client URL>:<port>/loleaflet/<hash>/loleaflet.html?WOPISrc=https://<WOPI host URL>/<...>/wopi/files/<id>/contents?access_token=<token>

/*

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

 */

@Api
public class WopiRest extends WopiApiServiceImpl {

	public WopiRest() {
		super();
	}

	@Override
	public Response getCheckFileInfo(String document, String accessToken, SecurityContext securityContext) throws NotFoundException {
		FileInfo info = new FileInfo();

		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		IDfSession userSession = null;

		BufferedInputStream bIs;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);

			IDfPersistentObject dfDocument = userSession.getObject(new DfId(document));
			IDfSysObject sysObject = (IDfSysObject) dfDocument;
			Logger.getLogger(this.getClass()).info("------------ get getCheckFileInfo  ------------ " + document);

			bIs = new BufferedInputStream(sysObject.getContent());
			byte[] buffer = new byte[bIs.available()];
			bIs.read(buffer);

			info.setBaseFileName(sysObject.getId("r_object_id").toString() + "." + sysObject.getFormat().getDOSExtension());
			info.setSize(sysObject.getContentSize());
			info.setOwnerId(sysObject.getOwnerName());
			info.setUserId(loginName);
			info.setVersion(sysObject.getModifyDate().getDate().getTime());
			info.setSha256(getHash256(bIs));
			info.setAllowExternalMarketplace(true);
			info.setUserCanWrite(true);
			info.setSupportsUpdate(true);
			info.setSupportsLocks(true);
			info.setLastModifiedTime(formatTime(sysObject.getModifyDate().getDate()));
			info.setUserFriendlyName("klemen");
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
		InputStream fis = null;
		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		IDfSession userSession = null;
		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject dfDocument = userSession.getObject(new DfId(document));
			IDfSysObject sysObject = (IDfSysObject) dfDocument;
			fis = new BufferedInputStream(sysObject.getContent());
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			Logger.getLogger(this.getClass()).info("------------ getContentFile  ------------ " + document);
//@formatter:off
			return Response.status(Response.Status.OK)
					.header("X-WOPI-ItemVersion", sysObject.getModifyDate().getDate().getTime())
					.header("Content-Length", sysObject.getContentSize())
					.header("Content-Type", "application/json")
					.header("Content-Disposition", "attachment; filename=" + sysObject.getId("r_object_id").toString() + "." + sysObject.getFormat().getDOSExtension())
					.entity(new ByteArrayInputStream(buffer)).build();
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
				fis.close();
				if (userSession != null && userSession.isConnected())
					userSession.getSessionManager().release(userSession);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Response updateWopiDocumentContent(String document, String accessToken, File body, SecurityContext securityContext)
			throws NotFoundException {

		String loginName = getUserFromToken(accessToken);
		String password = getPassFromToken(accessToken);
		IDfSession userSession = null;

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject dfDocument = userSession.getObject(new DfId(document));
			IDfSysObject sysObj = (IDfSysObject) dfDocument;

			if (!sysObj.isCheckedOut())
				sysObj.checkout();
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

			// Files.copy(body.toPath(), file.toPath(),
			// StandardCopyOption.REPLACE_EXISTING);
			Logger.getLogger(this.getClass()).info("------------ save file ------------ ");

			FileInfo info = new FileInfo();
			info.setBaseFileName(newSysObject.getId("r_object_id").toString() + "." + newSysObject.getFormat().getDOSExtension());
			info.setSize(newSysObject.getContentSize());
			info.setOwnerId(newSysObject.getOwnerName());
			info.setUserId(loginName);
			info.setVersion(newSysObject.getModifyDate().getDate().getTime());
			info.setSha256(getHash256(body));
			info.setAllowExternalMarketplace(true);
			info.setUserCanWrite(true);
			info.setSupportsUpdate(true);
			info.setSupportsLocks(true);
			info.setLastModifiedTime(formatTime(newSysObject.getModifyDate().getDate()));
			info.setUserFriendlyName(loginName);

			ObjectMapper mapper = new ObjectMapper();
			byte[] res = mapper.writeValueAsBytes(info);
			String result = new String(res);

//@formatter:off			
			return Response.ok()
					.header("X-WOPI-ItemVersion", newSysObject.getModifyDate().getDate().getTime())
					.header("Content-Type", "application/json").entity(result).build();
//@formatter:on			
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).error(e);
			return Response.serverError().build();
		} finally {
			if (userSession != null && userSession.isConnected())
				userSession.getSessionManager().release(userSession);
		}
	}

	private String formatTime(Date date) {
		SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.GERMANY);
		ISO8601DATEFORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
		String formattedTime = ISO8601DATEFORMAT.format(new Date()) + "Z";
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
		IDfSession userSession = null;

		try {
			userSession = AdminServiceImpl.getSession(loginName, password);
			IDfPersistentObject dfDocument = userSession.getObject(new DfId(document));
			IDfSysObject sysObj = (IDfSysObject) dfDocument;

			if (xWOPIOverride.equals("UNLOCK")) {
				ExplorerServiceImpl.getInstance().unlock(loginName, password, document);
//@formatter:off			
				return Response.status(Response.Status.OK)
						.header("X-WOPI-Lock", loginName)
						.header("X-WOPI-ItemVersion", sysObj.getModifyDate().getDate().getTime())
						.build();
//@formatter:on
			} else {
				sysObj.checkout();
//@formatter:off			
				return Response.status(Response.Status.OK)
						.header("X-WOPI-Lock", loginName)
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

	
	public String getUserFromToken(String token)
	{
		String decodedToken=new String(Base64.getDecoder().decode(token));
		String user = decodedToken.split(":")[0];
		return user;
	}
	
	public String getPassFromToken(String token)
	{
		String decodedToken=new String(Base64.getDecoder().decode(token));
		String pass = decodedToken.split(":")[1];
		return pass;
	}	
	
}