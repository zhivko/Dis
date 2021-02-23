package si.telekom.dis.server;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.server.Server;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.ByteSource;

public class RestTest extends JerseyTest {
	HttpServer server = null;

	@Before
	public void setUp() throws Exception {

	// @formatter:off			
		String value1 = 
				"io.swagger.jaxrs.listing," + 
				"io.swagger.sample.resource," + 
				"si.telekom.dis.server.restCommon," + 
				"si.telekom.dis.server.rest," +
				"si.telekom.dis.server.rest.api";
//@formatter:on

//@formatter:off			
		String value2 = "org.glassfish.jersey.media.multipart.MultiPartFeature," + 
										"org.glassfish.jersey.filter.LoggingFilter";
//@formatter:on

		HashMap<String, String> initParams = new HashMap<>();

		// ServerProperties.PROVIDER_PACKAGES is equal to
		// "jersey.config.server.provider.packages"
		initParams.put(ServerProperties.PROVIDER_PACKAGES, value1);
		initParams.put(ServerProperties.PROVIDER_CLASSNAMES, value2);
		initParams.put("DocumentsApi.implementation", "si.telekom.dis.server.rest.DisRest");

		// Make sure to end the URI with a forward slash
		server = GrizzlyWebContainerFactory.create("http://localhost:8080/", initParams);
		//AdminServiceImpl.getInstance();

		while (!AdminServiceImpl.started)
			Thread.currentThread().sleep(500);
	}

	@Override
	public Application configure() {

		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		ResourceConfig rc = new ResourceConfig();

		return rc;

	}

	@After
	public void after() {
		try {
			server.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDocumentsQuery() {
		// http://localhost:8080/Dis-server/api/documents/query?dql=select * from
		// dm_cabinet
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("delovodnik", "P@$$w0rd1");
		// HttpAuthenticationFeature feature =
		// HttpAuthenticationFeature.basic("dmadmin", "tb25me81");

		final Client client = ClientBuilder.newClient();
		client.register(feature);

		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);

		Response response = client.target("http://localhost:8080/documents/query").queryParam("dql", "select * from dm_cabinet").request().get();
		String responseTxt = response.readEntity(String.class);
		//System.out.println(responseTxt);
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return user list", response.getEntity().toString());
	}
}