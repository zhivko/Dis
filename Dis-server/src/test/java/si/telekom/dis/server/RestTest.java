package si.telekom.dis.server;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.uri.UriComponent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

		HashMap<String, String> contextInitParams = new HashMap<>();

		// Make sure to end the URI with a forward slash
		// server = GrizzlyWebContainerFactory.create(baseUri, initParams);

		String path = baseUri.getPath();
		path = String.format("/%s", UriComponent.decodePath(baseUri.getPath(), true).get(1).toString());

		InitialContext ctx = new InitialContext();
		System.setProperty("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
		server = GrizzlyWebContainerFactory.create(baseUri, initParams);
		server.getServerConfiguration().addHttpHandler(new StaticHttpHandler(new File(".").getAbsolutePath() + "/web"));

		org.glassfish.grizzly.servlet.WebappContext webappContext = new org.glassfish.grizzly.servlet.WebappContext("Test Context");

		File f = new File("./target/Dis-server-1.0-SNAPSHOT/WEB-INF/web.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(f);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("/web-app/context-param");

		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			Element el = (Element) n;
			String paramName = el.getElementsByTagName("param-name").item(0).getTextContent();
			String paramValue = el.getElementsByTagName("param-value").item(0).getTextContent();
			webappContext.setInitParameter(paramName, paramValue);
		}

		webappContext.addListener(si.telekom.dis.server.WebAppListener.class);

		webappContext.deploy(server);

		System.out.println("documentum superuser domain: " + AdminServiceImpl.getInstance().superUserDomain);
		System.out.println("documentum superuser user: " + AdminServiceImpl.getInstance().superUserName);
		System.out.println("documentum superuser password: " + AdminServiceImpl.getInstance().superUserPassword);

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
			// server.shutdown();
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
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("delovodnik", "P@$$w0rd1");
		// HttpAuthenticationFeature feature =
		// HttpAuthenticationFeature.basic("dmadmin", "tb25me81");

		final Client client = ClientBuilder.newClient();
		client.register(feature);

		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);

		String X_Transaction_Id = String.valueOf(System.currentTimeMillis());

//@formatter:off	
		Response response;
		
		
		String jsonNewDocument = "{  " +
			  "\"profileId\": \"mob_subscriber_document\"," +
			  "\"folderId\": \"/temp\"," +
			  "\"stateId\": \"effective\"," +
			  "\"attributes\": " +
			  "[" +
			  "  { \"name\": \"mob_classification_id\", \"values\": [ \"398\" ] }," +
			  "  { \"name\": \"title\", \"values\": [ \"testni dokument\" ] }," +
			  "  { \"name\": \"mob_type_id\", \"values\": [ \"142\" ] }," +
			  "  { \"name\": \"mob_type\", \"values\": [ \"Pogodba\" ] }," +
			  "  { \"name\": \"mob_issue_date\", \"values\": [ \"01.01.2021 10:00:00\" ] }" +
			  "]," +
			  "\"rolesUsers\":" +
			  "[" +
			  "  { \"roleId\": \"coordinator\", \"values\": [ \"kovacevicr\", \"zivkovick\"] }," +
			  "  { \"roleId\": \"user\", \"values\": [ \"dm_world\" ] }" +
			  "]," +
			  "\"templateObjectRObjectId\":\"090001c8803f18e9\"" +
				"}";
		System.out.println(jsonNewDocument);
		
		response = client.target(baseUri.toString() + "/documents/create")
				.request(MediaType.APPLICATION_JSON)
				.header("X-Transaction-Id", X_Transaction_Id).
				post(Entity.json(jsonNewDocument));
//@formatter:on			
		String json = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		Document doc;
		try {
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			assertEquals("Import document should return status 200", 200, response.getStatus());
			assertNotNull("Should return document", doc);
			assertNotNull("Should return r_object_id for document", doc.getrObjectId());

			// *************** test destroy document ******************
//@formatter:off
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/destroy")
					.request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id)
					.post(Entity.text(""));
//@formatter:on			

			assertEquals("Should return status 200", 200, response.getStatus());

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

	@Test
	public void testDocumentsImport() {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("delovodnik", "P@$$w0rd1");
		// HttpAuthenticationFeature feature =
		// HttpAuthenticationFeature.basic("dmadmin", "tb25me81");

		final Client client = ClientBuilder.newClient();
		client.register(feature);

		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);

		String X_Transaction_Id = String.valueOf(System.currentTimeMillis());

		String documentContent = "dGVzdA==";

//@formatter:off	
		Response response;
		
		String jsonLoad = 				"{  " +
			  "\"profileId\": \"mob_subscriber_document\"," +
			  "\"folderId\": \"/temp\"," +
			  "\"stateId\": \"effective\"," +
			  "\"attributes\": " +
			  "[" +
			  "  { \"name\": \"mob_classification_id\", \"values\": [ \"398\" ] }," +
			  "  { \"name\": \"title\", \"values\": [ \"testni dokument\" ] }," +
			  "  { \"name\": \"mob_type_id\", \"values\": [ \"142\" ] }," +
			  "  { \"name\": \"mob_type\", \"values\": [ \"Pogodba\" ] }," +
			  "  { \"name\": \"mob_issue_date\", \"values\": [ \"01.01.2021 10:00:00\" ] }" +
			  "]," +
			  "\"rolesUsers\":" +
			  "[" +
			  "  { \"roleId\": \"coordinator\", \"values\": [ \"kovacevicr\", \"zivkovick\"] }," +
			  "  { \"roleId\": \"user\", \"values\": [ \"dm_world\" ] }" +
			  "]," +
			  "\"content\":" +
			  "{" +
			  "  \"format\": \"crtext\"," +
			  "  \"data\": \"" + documentContent + "\"" +
			  "}" +
				"}";

		System.out.println(jsonLoad);
		
		Entity<String> jsonImport = Entity.json(jsonLoad);
		
		response = client.target(baseUri.toString() + "/documents/import")
				.request(MediaType.APPLICATION_JSON)
				.header("X-Transaction-Id", X_Transaction_Id).
				post(jsonImport);
//@formatter:on			
		String json = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		Document doc;
		try {
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			assertEquals("Import document should return status 200", 200, response.getStatus());
			assertNotNull("Should return document", doc);
			assertNotNull("Should return r_object_id for document", doc.getrObjectId());

			// *************** test read content of document ******************
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/content").request().get();
			assertEquals("Should return status 200", 200, response.getStatus());

			String base64EncodedString = response.readEntity(String.class);
			String content = AdminServiceImpl.base64Decode(base64EncodedString);
			String originalContent = AdminServiceImpl.base64Decode(documentContent);
			assertNotNull("Content should contain something", response.getEntity().toString());
			assertEquals("Content should be equal", originalContent, content);

			// *************** test destroy document ******************
//@formatter:off
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/destroy")
					.request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id)
					.post(Entity.text(""));
//@formatter:on			

			assertEquals("Should return status 200", 200, response.getStatus());

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

	
	@Test
	public void testDocumentsDemote() {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("delovodnik", "P@$$w0rd1");
		// HttpAuthenticationFeature feature =
		// HttpAuthenticationFeature.basic("dmadmin", "tb25me81");

		final Client client = ClientBuilder.newClient();
		client.register(feature);

		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);

		String X_Transaction_Id = String.valueOf(System.currentTimeMillis());

		String documentContent = "dGVzdA==";

//@formatter:off	
		Response response;
		
		String jsonLoad = 				"{  " +
			  "\"profileId\": \"mob_subscriber_document\"," +
			  "\"folderId\": \"/temp\"," +
			  "\"stateId\": \"effective\"," +
			  "\"attributes\": " +
			  "[" +
			  "  { \"name\": \"mob_classification_id\", \"values\": [ \"398\" ] }," +
			  "  { \"name\": \"title\", \"values\": [ \"testni dokument\" ] }," +
			  "  { \"name\": \"mob_type_id\", \"values\": [ \"142\" ] }," +
			  "  { \"name\": \"mob_type\", \"values\": [ \"Pogodba\" ] }," +
			  "  { \"name\": \"mob_issue_date\", \"values\": [ \"01.01.2021 10:00:00\" ] }" +
			  "]," +
			  "\"rolesUsers\":" +
			  "[" +
			  "  { \"roleId\": \"coordinator\", \"values\": [ \"kovacevicr\", \"zivkovick\"] }," +
			  "  { \"roleId\": \"user\", \"values\": [ \"dm_world\" ] }" +
			  "]," +
			  "\"content\":" +
			  "{" +
			  "  \"format\": \"crtext\"," +
			  "  \"data\": \"" + documentContent + "\"" +
			  "}" +
				"}";

		System.out.println(jsonLoad);
		
		Entity<String> jsonImport = Entity.json(jsonLoad);
		
		response = client.target(baseUri.toString() + "/documents/import")
				.request(MediaType.APPLICATION_JSON)
				.header("X-Transaction-Id", X_Transaction_Id).
				post(jsonImport);
//@formatter:on			
		String json = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		Document doc;
		try {
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			assertEquals("Import document should return status 200", 200, response.getStatus());
			assertNotNull("Should return document", doc);
			assertNotNull("Should return r_object_id for document", doc.getrObjectId());

			// *************** test read content of document ******************
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/content").request().get();
			assertEquals("Should return status 200", 200, response.getStatus());

			String base64EncodedString = response.readEntity(String.class);
			String content = AdminServiceImpl.base64Decode(base64EncodedString);
			String originalContent = AdminServiceImpl.base64Decode(documentContent);
			assertNotNull("Content should contain something", response.getEntity().toString());
			assertEquals("Content should be equal", originalContent, content);
			
			
			
			// *************** test demote document ******************
			
			response = client.target(baseUri.toString() + "/documents/demoteDocument")
					.request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id).
					post(jsonImport);			

			// *************** test destroy document ******************
//@formatter:off
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/destroy")
					.request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id)
					.post(Entity.text(""));
//@formatter:on			

			assertEquals("Should return status 200", 200, response.getStatus());

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