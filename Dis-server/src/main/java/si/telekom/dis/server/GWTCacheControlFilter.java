package si.telekom.dis.server;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link Filter} to add cache control headers for GWT generated files to ensure
 * that the correct files get cached.
 * 
 * @author See Wah Cheng
 * @created 24 Feb 2009
 */
public class GWTCacheControlFilter implements Filter {
	private static final String ALLOWED_DOMAINS_REGEXP = ".*";

	public void destroy() {
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String requestURI = httpRequest.getRequestURI();

		HttpServletResponse resp = (HttpServletResponse) response;

		if (requestURI.contains(".nocache.")) {
			Date now = new Date();
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setDateHeader("Date", now.getTime());
			// one day old
			httpResponse.setDateHeader("Expires", now.getTime() - 86400000L);
			httpResponse.setHeader("Pragma", "no-cache");
			httpResponse.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		}

		String origin = httpRequest.getHeader("Origin");
		if (origin != null && origin.matches(ALLOWED_DOMAINS_REGEXP)) {
			resp.addHeader("Access-Control-Allow-Origin", origin);
			if ("options".equalsIgnoreCase(httpRequest.getMethod())) {
				resp.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS");
				if (origin != null) {
					String headers = httpRequest.getHeader("Access-Control-Request-Headers");
					String method = httpRequest.getHeader("Access-Control-Request-Method");
					resp.addHeader("Access-Control-Allow-Methods", method);
					resp.addHeader("Access-Control-Allow-Headers", headers);
					// optional, only needed if you want to allow cookies.
					resp.addHeader("Access-Control-Allow-Credentials", "true");
					resp.setContentType("text/x-gwt-rpc");
				}
				resp.getWriter().flush();
				return;
			}
		}

		// Fix ios6 caching post requests
		if ("post".equalsIgnoreCase(httpRequest.getMethod())) {
			resp.addHeader("Cache-Control", "no-cache");
		}

		if (filterChain != null) {
			filterChain.doFilter(httpRequest, resp);
		}

	}
}