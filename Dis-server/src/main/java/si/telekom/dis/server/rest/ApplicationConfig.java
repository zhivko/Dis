package si.telekom.dis.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.validation.JAXRSBeanValidationInInterceptor;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


@ApplicationPath("/rest")
public class ApplicationConfig extends ResourceConfig {

	public static String realPath;

	ServletConfig myServletConfig;
	private Set singletons = new HashSet(); 
	
	
	public ApplicationConfig() {
		Logger.getLogger(this.getClass()).info("registering rest classes.START.");
		
		singletons.add(new JacksonJsonProvider());
		singletons.add(new JAXRSBeanValidationInInterceptor());

		packages("si.telekom.dis.server.rest,io.swagger.jaxrs.listing,io.swagger.v3.jaxrs2.integration.resources,io.swagger.jaxrs.listing");
		register(DisRest.class);

		/*
//@formatter:off		
		String[] packgs = { 
				"si.telekom.dis.server.rest.DisRest", 
				"si.telekom.dis.server.rest.CatalogServiceRest",
				"si.telekom.dis.server.rest.CustomerSearchRest" };
//@formatter:on
		packages(packgs);
*/
		Logger.getLogger(this.getClass()).info("registering rest classes.END.");
	}

	public ApplicationConfig(@Context ServletConfig servletConfig) {
		this();
		myServletConfig = servletConfig;
	}

	// @Override
	// public Set<Object> getInstances() {
	// // TODO Auto-generated method stub
	// HashSet<Object> singleTons = new HashSet<>();
	// // Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME).info("registering
	// // rest classes.");
	// Logger.getLogger(this.getClass()).info("registering rest classes.START.");
	//
	// // singleTons.add(new
	// // LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
	// // Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));
	//
	// singleTons.add(new si.telekom.dis.server.rest.DisRest(myServletConfig));
	// singleTons.add(new
	// si.telekom.dis.server.rest.CatalogServiceRest(myServletConfig));
	// singleTons.add(new
	// si.telekom.dis.server.rest.CustomerSearchRest(myServletConfig));
	//
	// Logger.getLogger(this.getClass()).info("registering rest classes.END.");
	//
	// return singleTons;
	// }

}