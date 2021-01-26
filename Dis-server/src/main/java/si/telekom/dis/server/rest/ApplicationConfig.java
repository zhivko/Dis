package si.telekom.dis.server.rest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.logging.LoggingFeature;

import io.swagger.jaxrs.config.BeanConfig;
//https://www.nabisoft.com/tutorials/java-ee/producing-and-consuming-json-or-xml-in-java-rest-services-with-jersey-and-jackson
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
 
		public ApplicationConfig()
		{
      BeanConfig beanConfig = new BeanConfig();
      beanConfig.setVersion("1.0.2");
      beanConfig.setSchemes(new String[]{"http"});
      beanConfig.setHost("localhost:8080");
      beanConfig.setBasePath("/rest");
      beanConfig.setResourcePackage("io.swagger.resources");
      beanConfig.setScan(true);			
		}
	
    @Override
    public Set<Class<?>> getClasses() {
        
        Set<Class<?>> resources = new java.util.HashSet<>();
        
        System.out.println("REST configuration starting: getClasses()");            
        
        //features
        //this will register Jackson JSON providers
        resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
        //we could also use this:
				resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);

				resources.add(LoggingFeature.class);
        //resources.add(GsonMessageBodyHandler.class);        
        
        //instead let's do it manually:
//        resources.add(si.telekom.dis.shared.Profile.class);
				
				resources.add(io.swagger.v3.jaxrs2.integration.resources.OpenApiResource.class);
        resources.add(si.telekom.dis.server.rest.DisRest.class);

        //==> we could also choose packages, see below getProperties()
        
        System.out.println("REST configuration ended successfully.");
        
        return resources;
    }
    
    @Override
    public Set<Object> getSingletons() {
        return Collections.emptySet();
    }
    
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        
        //in Jersey WADL generation is enabled by default, but we don't 
        //want to expose too much information about our apis.
        //therefore we want to disable wadl (http://localhost:8080/service/application.wadl should return http 404)
        //see https://jersey.java.net/nonav/documentation/latest/user-guide.html#d0e9020 for details
        properties.put("jersey.config.server.wadl.disableWadl", false);
        
        //we could also use something like this instead of adding each of our resources
        //explicitly in getClasses():
        //properties.put("jersey.config.server.provider.packages", "si.telekom.dis.server.rest");
        

        
        return properties;
    }    
}