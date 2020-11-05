package si.telekom.dis.server.rest;

import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.internal.InternalProperties;
import org.glassfish.jersey.internal.util.PropertiesHelper;

import com.fasterxml.jackson.jaxrs.base.JsonMappingExceptionMapper;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class MarshalingFeature implements Feature {

	private final static String JSON_FEATURE = MarshalingFeature.class.getSimpleName();

	@Override
	public boolean configure(FeatureContext context) {

		context.register(JsonParseExceptionMapper.class);
		context.register(JsonMappingExceptionMapper.class);
		context.register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

		final Configuration config = context.getConfiguration();
		// Disables discoverability of org.glassfish.jersey.jackson.JacksonFeature
		context.property(PropertiesHelper.getPropertyNameForRuntime(InternalProperties.JSON_FEATURE, config.getRuntimeType()), JSON_FEATURE);

		return true;
	}
}