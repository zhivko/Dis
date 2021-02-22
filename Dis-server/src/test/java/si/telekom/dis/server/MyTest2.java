package si.telekom.dis.server;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import si.telekom.dis.server.rest.api.DocumentsApi;
import si.telekom.dis.server.rest.api.JacksonJsonProvider;

public class MyTest2 extends JerseyTest {
	@Override
	public Application configure() {

// @formatter:off			
				String value1 = 
						"io.swagger.jaxrs.listing," + 
						"io.swagger.sample.resource," + 
						"si.telekom.dis.server.restCommon," + 
						"si.telekom.dis.server.rest," +
						"si.telekom.dis.server.rest.api";
// @formatter:on

// @formatter:off			
				String value2 = "org.glassfish.jersey.media.multipart.MultiPartFeature," + 
												"org.glassfish.jersey.filter.LoggingFilter";
// @formatter:on

		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put(ServerProperties.PROVIDER_PACKAGES, value1);
		myMap.put(ServerProperties.PROVIDER_CLASSNAMES, value2);
		myMap.put("DocumentsApi.implementation", "si.telekom.dis.server.rest.DisRest");
		
		ResourceConfig rc = new ResourceConfig();
		rc.register(DocumentsApi.class);
		rc.register(JacksonJsonProvider.class);
		
		rc.registerClasses(DocumentsApi.class);
		
		rc.setProperties(myMap);
		
		
		return rc;
	}

	@Test
	public void tesFetchAll() {
		// http://localhost:8080/Dis-server/api/documents/query?dql=select * from dm_cabinet
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("username", "password");
		
		
		final Client client = ClientBuilder.newClient();
		client.register(feature);
		
		Response response = client.target("http://localhost:9998/Dis-server/api/documents/query").queryParam("dql","select * from dm_cabinet").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		System.out.println("--- test ----");
		System.out.println("return: " + response.getEntity().toString());
		assertNotNull("Should return user list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
}