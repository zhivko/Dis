package si.telekom.dis.server.rest;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Jackson JSON processor could be controlled via providing a custom Jackson
 * ObjectMapper instance. This could be handy if you need to redefine the
 * default Jackson behavior and to fine-tune how your JSON data structures look
 * like (copied from Jersey web site). *
 * 
 * @see https://jersey.java.net/documentation/latest/media.html#d0e4799
 */

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class MyJacksonJsonProvider implements ContextResolver<ObjectMapper> {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.setSerializationInclusion(Include.NON_EMPTY);
		MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
	}

	public MyJacksonJsonProvider() {
    super();
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		System.out.println("MyJacksonProvider.getContext() called with type: " + type);
		return MAPPER;
	}
}