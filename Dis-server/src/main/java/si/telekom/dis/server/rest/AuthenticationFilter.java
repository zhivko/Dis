package si.telekom.dis.server.rest;

import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import jcifs.util.Base64;
import si.telekom.dis.server.LoginServiceImpl;

/**
 * This filter verify the access permissions for a user based on username and
 * password provided in request
 */

// http://localhost:8080/Dis-server/rest/disRest/newDocument


@Provider
@Priority(Priorities.AUTHENTICATION) // needs to happen before authorization
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private ResourceInfo resourceInfo;

	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME = "Basic";

	@Override
	public void filter(ContainerRequestContext requestContext) {
		Method method = resourceInfo.getResourceMethod();
		
    //We do allow wadl to be retrieve
		String path = requestContext.getUriInfo().getPath(true);
    if(method.getName().equals("getWadl") && (path.equals("application.wadl") || path.equals("application.wadl/xsd0.xsd"))){
        return;
    }
    
		// Access allowed for all
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Access blocked for all users !!").build());
				return;
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch authorization header
			final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
				return;
			}

			// Get encoded username and password
			final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

			// Decode username and password
			String usernameAndPassword = new String(Base64.decode(encodedUserPassword));

			// Split username and password tokens
			final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
			final String username = tokenizer.nextToken();
			final String password = tokenizer.nextToken();

			// Verifying Username and password
			Logger.getLogger(this.getClass().getName()).info("Authenticating user: " + username);

			/*
			 * // Verify user access if
			 * (method.isAnnotationPresent(RolesAllowed.class)) { RolesAllowed
			 * rolesAnnotation = method.getAnnotation(RolesAllowed.class); Set<String>
			 * rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
			 * 
			 * // Is user valid? if (!isUserAllowed(username, password, rolesSet)) {
			 * requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).
			 * entity("You cannot access this resource").build()); return; } }
			 */
			// Is user valid?
			if (!isUserAllowed(username, password)) {
				requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("You cannot access this resource").build());
				return;
			}
			Logger.getLogger(this.getClass().getName()).info("Authenticating user: " + username + " ... authenticated.");

      // We configure your Security Context here
      String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
      
      User us = new User();
      us.setId(username);
      us.setPassword(password);
      
      requestContext.setSecurityContext(new MyApplicationSecurityContext(us, scheme));
      
		}
	}

	private boolean isUserAllowed(final String username, final String password) {
		boolean isAllowed = false;

		try {
			LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
			String clientIp = servletRequest.getRemoteAddr();
			loginServiceImpl.checkPassword(username, password, clientIp);
			isAllowed = true;
			return isAllowed;
		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).warn("User " + username + " cannot login: " + ex.getMessage());
		}
		return false;
	}

}