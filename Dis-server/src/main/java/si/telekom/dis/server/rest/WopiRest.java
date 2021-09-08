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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.annotation.security.PermitAll;
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

import io.swagger.annotations.Api;
import si.telekom.dis.server.wopi.FileInfo;
import si.telekom.dis.server.wopi.api.NotFoundException;
import si.telekom.dis.server.wopi.api.impl.WopiApiServiceImpl;

// https://<WOPI client URL>:<port>/loleaflet/<hash>/loleaflet.html?WOPISrc=https://<WOPI host URL>/<...>/wopi/files/<id>/contents?access_token=<token>

/*

    http://admin:admin@localhost:9980/loleaflet/dist/admin/admin.html
    https://admin:admin@localhost:9980/lool/getMetrics
    http://localhost:9980/hosting/capabilities
    http://localhost:9980/hosting/discovery
    


		https://lool2.friprogramvarusyndikatet.se:9980/hosting/discovery
		
		
		http://172.17.0.1:9980/loleaflet/dist/admin/admin.html
		http://172.17.0.2:9980/hosting/discovery

 */

@Api
public class WopiRest extends WopiApiServiceImpl {

	public WopiRest() {
		super();
	}

	@Override
	public Response getCheckFileInfo(String document, String accessToken, SecurityContext securityContext) throws NotFoundException {
		// formatter:off
		File file = new File("/home/klemen/Documents/LedPaket.odt");

		FileInfo info = new FileInfo();
		
    try {
        if (document != null && document.length() > 0) {
            Logger.getLogger(this.getClass()).info("------------ get getCheckFileInfo  ------------ "+file.getName());
            if (file.exists()) {
                // 取得文件名
                info.setBaseFileName(file.getName());
                info.setSize(file.length());
                info.setOwnerId("admin");
                info.setUserId("klemen");
                info.setVersion(file.lastModified());
                info.setSha256(getHash256(file));
                info.setAllowExternalMarketplace(true);
                info.setUserCanWrite(false);
                info.setSupportsUpdate(false);
                info.setSupportsLocks(false);
                
                Date date = new Date();
                
                // 2005-01-01T12:00:00.000000+01:00
                
                SimpleDateFormat ISO8601DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", Locale.GERMANY);
                info.setLastModifiedTime(ISO8601DATEFORMAT.format(date));

                info.setUserFriendlyName("klemen");
            }
        }
//        ObjectMapper mapper = new ObjectMapper();
//        
//        objectMapper.readValue(json.getBytes(), QueryDocumentsResponse.class);
//        
//        String Json =  mapper.writev
        
        return Response.status(Response.Status.OK)
        		.header("Content-Type", "application/json")
        		.entity(info).build();
    } catch (Exception e) {
        e.printStackTrace();
    		return Response.serverError().build();
    }
	}

	@Override
	public Response getWopiDocumentContent(String document, String accessToken, SecurityContext securityContext) throws NotFoundException {
		File file = new File("/home/klemen/Documents/LedPaket.odt");
		

    InputStream fis = null;
    OutputStream toClient = null;
    try {
        // 文件的路径
        //String path = filePath + name;
        //File file = new File(path);
        // 取得文件名
        String filename = file.getName();
        // 以流的形式下载文件
        fis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        Logger.getLogger(this.getClass()).info("------------ getContentFile  ------------ "+file.getName());
        
        byte[] content = FileUtils.readFileToByteArray(file);  
//formatter:off        
        return Response.status(Response.Status.OK)
        		.header("X-WOPI-ItemVersion", 15.1321)
        		.header("Content-Length", file.length())
        		.header("Content-Disposition", "attachment; filename=" + file.getName())
        		.entity(new ByteArrayInputStream(content)).build();
//formatter:on
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }		
    return Response.serverError().build();
		
	}

	@Override
	@PermitAll
	public Response updateWopiDocumentContent(String document, String accessToken, String body, SecurityContext securityContext)
			throws NotFoundException {
		File file = new File("/home/klemen/Documents/LedPaket1.odt");
		
    FileOutputStream fop = null;
    try {
        if (!file.exists()) {
            file.createNewFile();//构建文件
        }
        fop = new FileOutputStream(file);
        fop.write(body.getBytes());
        fop.flush();
        System.out.println("------------ save file ------------ ");
        return Response.ok().build();
        
    } catch (IOException e) {
        e.printStackTrace();
        return Response.serverError().build();
    } finally {
        try {
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }		
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

//			Logger.getLogger(WopiRest.class).info("Copying config file to collabora/code container: " + containerName + "...");
//			String dockerCpCmd = "docker cp /home/klemen/git/Dis/Dis-server/src/main/resources/loolwsd.xml " + containerName + ":/etc/loolwsd/loolwsd.xml";
//
//			dockerCpCmd = "docker exec -i " + containerName
//					+ " sh -c 'cat > /etc/loolwsd/loolwsd.xml' < /home/klemen/git/Dis/Dis-server/src/main/resources/loolwsd.xml";
//
//			Logger.getLogger(WopiRest.class).info(dockerCpCmd);
//			Process p = Runtime.getRuntime().exec(dockerCpCmd.split(" (?=(([^'\\\"]*['\\\"]){2})*[^'\\\"]*$)"));
//			ByteArrayOutputStream baOsErr = new ByteArrayOutputStream();
//			PrintStream psErr = new PrintStream(baOsErr);
//			inheritIO(p.getErrorStream(), psErr);
//			p.waitFor();
//			Logger.getLogger(WopiRest.class).info(baOsErr.toString());
//			Logger.getLogger(WopiRest.class).info("Copying config file to collabora/code container: " + containerName + "...Done.");
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
	
	
	
  public static String getHash256(File file) {
    String value = "";
    // 获取hash值
    try {
        byte[] buffer = new byte[1024];
        int numRead;
        InputStream fis = new FileInputStream(file);
        //如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
        MessageDigest complete = MessageDigest.getInstance("SHA-256");
        do {
            //从文件读到buffer
            numRead = fis.read(buffer);
            if (numRead > 0) {
                //用读到的字节进行MD5的计算，第二个参数是偏移量
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);

        fis.close();
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
	
	
	
}