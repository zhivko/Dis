package si.telekom.dis.server.rest;


import javax.ws.rs.ApplicationPath;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import si.telekom.dis.server.rest.api.impl.DocumentsApiServiceImpl;

@ApplicationPath("/")
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
			// TODO Auto-generated constructor stub
			Logger.getLogger(this.getClass()).info("registering rest classes.");
			register(DocumentsApiServiceImpl.class);
			Logger.getLogger(this.getClass()).info("registering rest classes. end.");
		}

}