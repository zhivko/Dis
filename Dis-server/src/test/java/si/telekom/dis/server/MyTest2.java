package si.telekom.dis.server;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

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

		Map<String, String> myMap = new HashMap<String, String>();
		myMap.put(ServerProperties.PROVIDER_PACKAGES, value1);
		myMap.put(ServerProperties.PROVIDER_CLASSNAMES, value2);
		myMap.put("DocumentsApi.implementation", "si.telekom.dis.server.rest.DisRest");


		
		ResourceConfig rc = new ResourceConfig();
		rc.register(DocumentsApi.class);
		rc.register(JacksonJsonProvider.class);
		
		
		rc.setProperties(myMap);
		
		return rc;
	}

	@Test
	public void tesFetchAll() {
		Response response = target("/documents/query").request().get();
		assertEquals("should return status 200", 200, response.getStatus());
		System.out.println("--- test ----");
		System.out.println("return: " + response.getEntity().toString());
		assertNotNull("Should return user list", response.getEntity().toString());
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
	}
}