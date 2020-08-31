package si.telekom.dis.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import net.sf.saxon.lib.NamespaceConstant;

public class WebappContext {

	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public static final String KEY = WebappContext.class.getName();

	public static ServletContext servletContext;
	

	public WebappContext(ServletContext servletContext) throws Exception {

		try {
			
			try {
//				<!-- 				<documentum.docbaseName>Mobitel</documentum.docbaseName> -->
//				<!-- 				<documentum.superUserLogin>dmadmin</documentum.superUserLogin> -->
//				<!-- 				<documentum.superUserPassword>tb25me81</documentum.superUserPassword> -->
//				<!-- 				<documentum.superUserDomain></documentum.superUserDomain> -->
//				<!-- 				<documentum.globalRepositoryPassword></documentum.globalRepositoryPassword> -->
//
//				<!-- 				<barcode.user></barcode.user> -->
//				<!-- 				<barcode.password></barcode.password> -->
//				<!-- 				<barcode.database></barcode.database> -->
//				<!-- 				<barcode.sqlHost></barcode.sqlHost> -->
//				<!-- 				<barcode.sqlPort></barcode.sqlPort> -->
				Class.forName("si.telekom.dis.server.AdminServiceImpl");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			Locale.setDefault(new Locale("sl", "SI"));
			System.setProperty("javax.xml.xpath.XPathFactory:" + NamespaceConstant.OBJECT_MODEL_SAXON,
					"net.sf.saxon.xpath.XPathFactoryImpl");

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error: " + ex.getMessage());
			StringWriter errorStringWriter = new StringWriter();
			PrintWriter pw = new PrintWriter(errorStringWriter);
			ex.printStackTrace(pw);
			if (errorStringWriter.getBuffer() != null) {
				Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
			}
			throw ex;
		}
	}

	private static void printBindings(Context rootContext, String subContextName, Logger logger) throws NamingException {
		NamingEnumeration names = rootContext.listBindings(subContextName);
		logger.info("The following bindings were found in " + subContextName + ":");
		while (names.hasMore()) {
			Binding binding = (Binding) names.next();
			String name = binding.getName();
			Object o = binding.getObject();
			logger.info(name + ": " + o);
		}
		logger.info("Context enumerated.");

	}

	protected static void init(ServletContext servletContext) throws Exception {
		WebappContext.servletContext = servletContext;
		WebappContext instance = new WebappContext(servletContext);
		// instance.setConfigurationClasses(com.google.gwt.dev.shell.jetty.JettyLauncher.DEFAULT_CONFIG_CLASSES);
		servletContext.setAttribute(KEY, instance);
		
	}

	protected static void destroy(ServletContext servletContext) {
		WebappContext instance = get(servletContext);
	}

	public static WebappContext get(ServletContext servletContext) {
		return (WebappContext) servletContext.getAttribute(KEY);
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static String getServletContextParameterValue(String parameterName) {
		return servletContext.getInitParameter(parameterName);
	}

}
