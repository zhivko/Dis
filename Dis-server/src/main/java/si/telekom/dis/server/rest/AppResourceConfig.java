package si.telekom.dis.server.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.logging.LoggingFeature;
//Deployment of a JAX-RS application using @ApplicationPath with Servlet 3.0
//Descriptor-less deployment
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("resources")
public class AppResourceConfig extends ResourceConfig {
	public AppResourceConfig() {

		register(new LoggingFeature(java.util.logging.Logger.getGlobal(), java.util.logging.Level.ALL, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

    // Tracing properties (modification of the response HTTP headers)
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("jersey.config.server.tracing.type","ALL");
    params.put("jersey.config.server.tracing.threshold","TRACE");
    addProperties(params);
    
		packages("si.telekom.dis.server.rest");
		ResourceConfig config = new ResourceConfig(DisRest.class);
		config.register(LoggingFeature.class);
    
		
		// config.register(AuthenticationFilter.class);
	}
}