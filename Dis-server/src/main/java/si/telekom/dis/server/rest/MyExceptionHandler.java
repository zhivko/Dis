package si.telekom.dis.server.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionHandler implements ExceptionMapper<Exception> {

	@Context
	private HttpHeaders headers;

	public Response toResponse(Exception ex) {
		return Response.status(404).entity(ex.getMessage()).type(getAcceptType()).build();
	}

	private String getAcceptType() {
		return "Application/json";
		// List<MediaType> accepts = headers.getAcceptableMediaTypes();
		// if (accepts!=null && accepts.size() > 0) {
		// return
		// }else {
		// //return a default one like Application/json
		// }
	}
}