package si.telekom.dis.server;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import si.telekom.dis.server.rest.Attribute;
import si.telekom.dis.server.rest.Document;
import si.telekom.dis.server.rest.DocumentContent;
import si.telekom.dis.server.rest.QueryDocumentsResponse;
import si.telekom.dis.server.rest.UpdateDocumentRequest;
import si.telekom.dis.server.rest.UpdateDocumentRequest.VersionEnum;

public class RestTest extends JerseyTest {
	HttpServer server = null;
	final org.glassfish.grizzly.servlet.WebappContext webappContext = new org.glassfish.grizzly.servlet.WebappContext("Test Context");

	URI baseUri = URI.create("http://localhost:9998/");

	private boolean started = false;

	static InitialContext ctx = null;

	String[] users = { "user1", "user2", "user3" };
	
	static int userIndex;


	@Before
	public void setUp() throws Exception {
		if (ctx == null && available(9998)) {
			ctx = new InitialContext();
			System.setProperty("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");

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

			server = GrizzlyWebContainerFactory.create(baseUri, initParams);
			server.getServerConfiguration().addHttpHandler(new StaticHttpHandler(new File(".").getAbsolutePath() + "/web"));

			// File f = new File("./target/Dis-server-1.0-SNAPSHOT/WEB-INF/web.xml");
			// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// javax.xml.parsers.DocumentBuilder builder =
			// factory.newDocumentBuilder();
			// org.w3c.dom.Document doc = builder.parse(f);
			// XPathFactory xPathfactory = XPathFactory.newInstance();
			// XPath xpath = xPathfactory.newXPath();
			// XPathExpression expr = xpath.compile("/web-app/context-param");
			//
			// NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			// for (int i = 0; i < nl.getLength(); i++) {
			// Node n = nl.item(i);
			// Element el = (Element) n;
			// String paramName =
			// el.getElementsByTagName("param-name").item(0).getTextContent();
			// String paramValue =
			// el.getElementsByTagName("param-value").item(0).getTextContent();
			// System.out.println("webappContext.setInitParameter(\""+paramName+"\",
			// \""+paramValue+"\");");
			// webappContext.setInitParameter(paramName, paramValue);
			// }

			File f = new File("./src/main/filters/prod.properties");
			Scanner input = new Scanner(f);
			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.split("=").length == 2)
					webappContext.setInitParameter(line.split("=")[0], line.split("=")[1]);
			}

			webappContext.addListener(si.telekom.dis.server.WebAppListener.class);

			webappContext.deploy(server);

			System.out.println("documentum superuser domain: " + AdminServiceImpl.getInstance().superUserDomain);
			System.out.println("documentum superuser user: " + AdminServiceImpl.getInstance().superUserName);
			System.out.println("documentum superuser password: " + AdminServiceImpl.getInstance().superUserPassword);

			while (!AdminServiceImpl.started)
				Thread.currentThread().sleep(5000);

		}
	}

	@Override
	public Application configure() {

		//enable(TestProperties.LOG_TRAFFIC);
		//enable(TestProperties.DUMP_ENTITY);

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

	@Test
	public void testDocumentsQuery() {
		HttpAuthenticationFeature feature = getFeature();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);
		
		client.register(feature);

		Document doc=null;
		
		Response response = client.target(baseUri.toString() + "documents/query").queryParam("dql", "select * from dm_document where folder('/Temp')").request().get();
		String json = response.readEntity(String.class);
		System.out.println(json);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			QueryDocumentsResponse queryDocsResp = objectMapper.readValue(json.getBytes(), QueryDocumentsResponse.class);
			doc = queryDocsResp.getDocuments().get(0);
			
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
		
		
		assertEquals("should return status 200", 200, response.getStatus());
		assertNotNull("Should return at least 1 document", doc);
	}

	private HttpAuthenticationFeature getFeature() {
		Logger.getLogger(this.getClass()).info("test with user with userIndex: " + userIndex);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(users[userIndex], "pwd");

		if (userIndex + 1 >= users.length)
			userIndex = 0;
		else
			userIndex++;

		return feature;
	}

	@Test
	public void testDocumentsCreate() throws InterruptedException {

		while (!AdminServiceImpl.started)
			Thread.currentThread().sleep(500);

		HttpAuthenticationFeature feature = getFeature();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);

		client.register(feature);

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
			  "\"templateObjectRObjectId\":\"09062016111100009\"" +
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
	public void testDocumentsImport() throws InterruptedException {

		while (!AdminServiceImpl.started)
			Thread.currentThread().sleep(500);

		HttpAuthenticationFeature feature = getFeature();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);
		client.register(feature);

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
	public void testDocumentDemotePromote() throws InterruptedException {

		while (!AdminServiceImpl.started)
			Thread.currentThread().sleep(500);

		HttpAuthenticationFeature feature = getFeature();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);
		client.register(feature);

		String X_Transaction_Id = String.valueOf(System.currentTimeMillis());
		String documentContent = "dGVzdA==";

//@formatter:off	
		Response response;
		
		String jsonLoad = 				"{  " +
			  "\"profileId\": \"epredloga\"," +
			  "\"folderId\": \"/temp\"," +
			  "\"stateId\": \"effective\"," +
			  "\"attributes\": " +
			  "[" +
			  "  { \"name\": \"mob_classification_id\", \"values\": [ \"394\" ] }," +
			  "  { \"name\": \"title\", \"values\": [ \"testna predloga\" ] }," +
			  "  { \"name\": \"mob_short_name\", \"values\": [ \"Kratek naziv test predloga\" ] }," +
			  "  { \"name\": \"mob_template_id\", \"values\": [ \"444555444\" ] }," +
			  "  { \"name\": \"mob_template_number\", \"values\": [ \"444455444\" ] }" +
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

		try {
			Document doc = objectMapper.readValue(json.getBytes(), Document.class);

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
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/demote").request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id).post(Entity.json(""));

			// *************** test that document is in draft state ******************
			json = response.readEntity(String.class);
			objectMapper = new ObjectMapper();
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			// response = client.target(baseUri.toString() +
			// "/documents/query").queryParam("dql", "select * from dm_document where
			// r_object_id='"+doc.getrObjectId()+"'").request().get();
			// json = response.readEntity(String.class);
			// objectMapper = new ObjectMapper();
			// QueryDocumentsResponse queryDocsResp =
			// objectMapper.readValue(json.getBytes(), QueryDocumentsResponse.class);
			// doc = queryDocsResp.getDocuments().get(0);

			assertEquals("State should be draft", "draft", doc.getState());

			// *************** test promote document ******************
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/promote").request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id).post(Entity.json(""));

			// *************** test that document is in effective state
			// ******************
			// response = client.target(baseUri.toString() +
			// "/documents/query").queryParam("dql", "select * from dm_document where
			// r_object_id='"+doc.getrObjectId()+"'").request().get();
			// json = response.readEntity(String.class);
			// objectMapper = new ObjectMapper();
			// queryDocsResp = objectMapper.readValue(json.getBytes(),
			// QueryDocumentsResponse.class);
			// doc = queryDocsResp.getDocuments().get(0);

			json = response.readEntity(String.class);
			objectMapper = new ObjectMapper();
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			assertEquals("State should be effective", "effective", doc.getState());

			// *************** test destroy document ******************
//@formatter:off
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/destroy")
					.request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id)
					.post(Entity.text(""));
//@formatter:on			

			assertEquals("Should return status 200", 200, response.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(timeout = 10000000)
	public void testDocumentUpdate() throws InterruptedException {

		while (!AdminServiceImpl.started)
			Thread.currentThread().sleep(500);

		HttpAuthenticationFeature feature = getFeature();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		client.property(ClientProperties.READ_TIMEOUT, 10000000);
		client.register(feature);

		// final List<Object> providers = new ArrayList<Object>();
		JacksonJaxbJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider();
		// providers.add( jacksonJsonProvider );

		// webClient = WebClient.create( "http://localhost:8080/api", providers );

		client.register(jacksonJsonProvider);

		// client.property(ClientProperties.CONNECT_TIMEOUT, 10000000);
		// client.property(ClientProperties.READ_TIMEOUT, 10000000);

		String X_Transaction_Id = String.valueOf(System.currentTimeMillis());

		String documentContent = "dGVzdA==";

//@formatter:off	
		Response response;
		
		String jsonLoad = 
				"{  " +
					  "\"profileId\": \"mob_subscriber_document\"," +
					  "\"folderId\": \"/temp\"," +
					  "\"stateId\": \"effective\"," +
					  "\"attributes\": " +
					  "[" +
					  "  { \"name\": \"mob_classification_id\", \"values\": [ \"398\" ] }," +
					  "  { \"name\": \"title\", \"values\": [ \"testni dokument\" ] }," +
					  "  { \"name\": \"mob_type_id\", \"values\": [ \"142\" ] }," +
					  "  { \"name\": \"mob_type\", \"values\": [ \"Pogodba\" ] }," +
					  "  { \"name\": \"mob_subscriber_number\", \"values\": [ \"360269\" ] }," +
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

		try {
			Document doc = objectMapper.readValue(json.getBytes(), Document.class);
			String first_r_object_id = doc.getrObjectId();

			String barcode = doc.getObjectName();

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

			// *************** test update document ******************

			// download from web
			//
			URL url = new URL(
					"https://web.archive.org/web/20081121022409fw_/http://testsuite.opendocumentfellowship.com/testcases/General/DocumentStructure/SingleDocumentContents/testDoc/testDoc.odt");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			DataInputStream dis = new DataInputStream(conn.getInputStream());
			ByteArrayOutputStream baOs = new ByteArrayOutputStream();
			byte buffer[] = new byte[1024];
			int offset = 0;
			int bytes;
			while ((bytes = dis.read(buffer, offset, buffer.length)) > 0) {
				baOs.write(buffer, 0, bytes);
			}
			baOs.close();

			String encodedString = Base64.getEncoder().encodeToString(baOs.toByteArray());

			UpdateDocumentRequest updateDocReq = new UpdateDocumentRequest();
			updateDocReq.addAttributesItem(new Attribute().name("title").addValuesItem("novTitle"));
			DocumentContent cont = new DocumentContent();
			cont.data(encodedString);
			cont.format("odt");
			updateDocReq.setContent(cont);
			updateDocReq.setVersion(VersionEnum.MINOR);

			Entity<UpdateDocumentRequest> jsonUpdate = Entity.json(updateDocReq);

			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId()).request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id).put(jsonUpdate);

			// *************** test that document is in effective state
			// ******************
			json = response.readEntity(String.class);
			objectMapper = new ObjectMapper();
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			// response = client.target(baseUri.toString() +
			// "/documents/query").queryParam("dql", "select * from dm_document where
			// r_object_id='"+doc.getrObjectId()+"'").request().get();
			// json = response.readEntity(String.class);
			// objectMapper = new ObjectMapper();
			// QueryDocumentsResponse queryDocsResp =
			// objectMapper.readValue(json.getBytes(), QueryDocumentsResponse.class);
			// doc = queryDocsResp.getDocuments().get(0);

			assertEquals("State should be effective", "effective", doc.getState());

			// check there are two versions
			response = client.target(baseUri.toString() + "/documents/query")
					.queryParam("dql", "select * from mob_subscriber_document(all) where object_name ='" + barcode + "'").request().get();
			json = response.readEntity(String.class);
			objectMapper = new ObjectMapper();
			QueryDocumentsResponse documentsResponse = objectMapper.readValue(json.getBytes(), QueryDocumentsResponse.class);
			assertEquals("There should be 2 versions of document", 2, documentsResponse.getDocuments().size());

			// let's work on updated document!
			doc = documentsResponse.getDocuments().get(1);

			// *************** test promote document ******************
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/promote").request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id).post(Entity.json(""));

			json = response.readEntity(String.class);
			objectMapper = new ObjectMapper();
			doc = objectMapper.readValue(json.getBytes(), Document.class);

			assertEquals("State should be archive", "archive", doc.getState());

			// *************** test destroy document ******************
//@formatter:off
			response = client.target(baseUri.toString() + "/documents/" + doc.getrObjectId() + "/destroy")
					.request(MediaType.APPLICATION_JSON)
					.header("X-Transaction-Id", X_Transaction_Id)
					.post(Entity.text(""));
//@formatter:on
			assertEquals("Should return status 200", 200, response.getStatus());

			// test that previous version is still available
			response = client.target(baseUri.toString() + "/documents/query")
					.queryParam("dql", "select * from mob_subscriber_document(all) where object_name ='" + barcode + "'").request().get();
			json = response.readEntity(String.class);
			objectMapper = new ObjectMapper();
			documentsResponse = objectMapper.readValue(json.getBytes(), QueryDocumentsResponse.class);
			assertEquals("There should be only one version of document", documentsResponse.getDocuments().size(), 1);
			Document doc2 = documentsResponse.getDocuments().get(0);
			assertEquals("Previous version r_object_id should be: " + first_r_object_id, first_r_object_id, doc2.getrObjectId().toString());
			System.out.println("Previous version r_object_id:" + first_r_object_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Checks to see if a specific port is available.
	 *
	 * @param port
	 *          the port to check for availability
	 */
	public static boolean available(int port) {
		if (port < Integer.MIN_VALUE || port > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Invalid start port: " + port);
		}

		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}

		return false;
	}

}