package si.telekom.dis.server.rest;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {

	public static String realPath;
	
	ServletConfig myServletConfig;

	public ApplicationConfig(@Context ServletConfig servletConfig) {
		// TODO Auto-generated constructor stub
		myServletConfig = servletConfig;
	}

	@Override
	public Set<Object> getSingletons() {
		// TODO Auto-generated method stub
		HashSet<Object> singleTons = new HashSet<>();
		//Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME).info("registering rest classes.");
		Logger.getLogger(this.getClass()).info("registering rest classes.START.");

		//singleTons.add(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

		singleTons.add(new si.telekom.dis.server.rest.DisRest(myServletConfig));
		singleTons.add(new si.telekom.dis.server.rest.CatalogServiceRest(myServletConfig));
		singleTons.add(new si.telekom.dis.server.rest.CustomerSearchRest(myServletConfig));
		
		
		Logger.getLogger(this.getClass()).info("registering rest classes.END.");

		return singleTons;
	}

}