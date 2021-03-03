package si.telekom.dis.server;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import si.telekom.dis.server.rest.Document;

public class RestTest extends JerseyTest {
	HttpServer server = null;

	URI baseUri = URI.create("http://localhost:9998/");

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
		server = GrizzlyWebContainerFactory.create(baseUri, initParams);
		// AdminServiceImpl.getInstance();

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

	// @Test
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

		Response response = client.target(baseUri.toString() + "/documents/query").queryParam("dql", "select * from dm_cabinet").request().get();
		String responseTxt = response.readEntity(String.class);
		// System.out.println(responseTxt);
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return user list", response.getEntity().toString());
	}

	@Test
	public void testDocumentsCreate() {
		// http://localhost:8080/Dis-server/api/documents/query?dql=select * from
		// dm_cabinet
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("delovodnik", "P@$$w0rd1");
		// HttpAuthenticationFeature feature =
		// HttpAuthenticationFeature.basic("dmadmin", "tb25me81");

		final Client client = ClientBuilder.newClient();
		client.register(feature);

		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);

	//@formatter:off	
		Response response;
		response = client.target(baseUri.toString() + "/documents/import").request(MediaType.APPLICATION_JSON).post(Entity.json(
			"{  " +
		  "\"profileId\": \"epredloga\"," +
		  "\"folderId\": \"/temp\"," +
		  "\"stateId\": \"effective\"," +
		  "\"attributes\": " +
		  "[" +
		  "  { \"name\": \"mob_classification_id\", \"values\": [ \"394\" ] }," +
		  "  { \"name\": \"subject\", \"values\": [ \"test\" ] }," +
		  "  { \"name\": \"mob_short_name\", \"values\": [ \"testna predloga čez REST\" ] }," +
		  "  { \"name\": \"title\", \"values\": [ \"testna predloga čez REST\" ] }," +
		  "  { \"name\": \"mob_template_id\", \"values\": [ \"450\" ] }" +
		  "]," +
		  "\"rolesUsers\":" +
		  "[" +
		  "  { \"roleId\": \"coordinator\", \"values\": [ \"kovacevicr\", \"zivkovick\"] }," +
		  "  { \"roleId\": \"user\", \"values\": [ \"dm_world\" ] }" +
		  "]," +
		  "\"content\":" +
		  "{" +
		  "  \"format\": \"crtext\"," +
		  "  \"data\": \"dGVzdA==\"" +
		  "}" +
			"}"));
			//@formatter:on			
		String json = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		Document doc;
		try {
			doc = objectMapper.readValue(json.getBytes(), Document.class);
			
			// System.out.println(responseTxt);
			assertEquals("should return status 200", 200, response.getStatus());
			assertNotNull("Should return user list", response.getEntity().toString());
			assertNotNull("Should return document", doc);

			assertNotNull("Should return r_object_id for document", doc.getrObjectId());			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}