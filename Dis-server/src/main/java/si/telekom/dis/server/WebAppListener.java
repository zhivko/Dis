package si.telekom.dis.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import si.telekom.dis.server.jobs.MoveMobFormTemplateToEffective;

public final class WebAppListener implements ServletContextAttributeListener, ServletContextListener {

	// ----------------------------------------------------- Instance Variables

	/**
	 * The servlet context with which we are associated.
	 */
	@Context
	private ServletContext context = null;
	

	/**
	 * Record the fact that this web application has been destroyed.
	 * 
	 * @param event The servlet context event
	 */
	public void contextDestroyed(ServletContextEvent event) {
		/*
		 * Logger.getLogger(this.getClass()).info("contextDestroyed()"); this.context =
		 * null; WebappContext.destroy(event.getServletContext());
		 * 
		 * int count = Thread.activeCount();
		 * Logger.getLogger(this.getClass()).info("currently active threads = " +
		 * count);
		 * 
		 * Thread th[] = new Thread[count]; // returns the number of threads put into
		 * the array Thread.enumerate(th);
		 * 
		 * // prints active threads for (int i = 0; i < count; i++) {
		 * StackTraceElement[] element = th[i].getStackTrace();
		 * 
		 * int j = 0; Logger.getLogger(this.getClass()).info("Thread: " + i + "/" +
		 * count + " " + th[i].getName()); for (StackTraceElement stackTraceElement :
		 * element) { j++; char[] repeatTab = new char[j]; Arrays.fill(repeatTab,
		 * "\t".charAt(0)); String tabs = new String(repeatTab); String line = tabs +
		 * stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() +
		 * " " + stackTraceElement.getLineNumber();
		 * Logger.getLogger(this.getClass()).info(line); }
		 * 
		 * }
		 */
		WsServer.logAll("Server going down because of deploying new verion. Login again after 1 min.");
	}

	/**
	 * Record the fact that this web application has been initialized.
	 * 
	 * @param event The servlet context event
	 */
	public void contextInitialized(ServletContextEvent event) {
		Logger.getLogger(this.getClass()).info("contextInitialized");
		this.context = event.getServletContext();
		
		//WopiRest.checkCollaboraDockerIsRunning();

		AdminServiceImpl.readStartupParamFromServletContext(context);
		
		try {
			WebappContext.init(event.getServletContext());
			//SLF4JBridgeHandler.install();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (AdminServiceImpl.MOVE_TO_EFFECTIVE_JOB_ENABLED) {
			Logger.getLogger(AdminServiceImpl.class).info("Starting MOVE_TO_EFFECTIVE_JOB");
			MoveMobFormTemplateToEffective job = new MoveMobFormTemplateToEffective();
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			scheduler.scheduleAtFixedRate(job, 0, 60, TimeUnit.MINUTES);
		}
		
		
	}

	
	
	// -------------------------------------------------------- Private Methods

	/**
	 * Log a message to the servlet context application log.
	 * 
	 * @param message Message to be logged
	 */
	private void log(String message) {

		if (context != null)
			context.log("ContextListener: " + message);
		else
			System.out.println("ContextListener: " + message);

	}

	/**
	 * Log a message and associated exception to the servlet context application
	 * log.
	 * 
	 * @param message   Message to be logged
	 * @param throwable Exception to be logged
	 */
	private void log(String message, Throwable throwable) {

		if (context != null)
			context.log("ContextListener: " + message, throwable);
		else {
			Logger.getLogger(this.getClass()).error(throwable);
		}

	}

	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}

}