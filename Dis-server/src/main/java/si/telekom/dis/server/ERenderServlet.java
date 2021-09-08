package si.telekom.dis.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Holder;

import org.apache.log4j.Logger;

import si.telekom.dis.server.jaxwsClient.eRender.ERender;
import si.telekom.dis.server.jaxwsClient.eRender.ERenderImplService;
import si.telekom.dis.server.jaxwsClient.eRender.HashMapWrapper;
import si.telekom.dis.server.jaxwsClient.pdfGenerator.Exception_Exception;
import si.telekom.dis.server.jaxwsClient.pdfGenerator.PdfGenerator;
import si.telekom.dis.server.jaxwsClient.pdfGenerator.PdfGeneratorImplService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
// @RemoteServiceRelativePath("login")
public class ERenderServlet extends HttpServlet {
	String loginName;
	String loginPassword;
	String format;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loginName = req.getParameter("loginName");
		loginPassword = req.getParameter("loginPassword");
		format = req.getParameter("format");
		byte[] result;
		byte[] decodedPdf;
		try {
			if (req.getParameter("inetId") != null) {
				Integer inetId = Integer.valueOf(req.getParameter("inetId"));

				QName qname = new QName("http://templates.mobitel.com/", "PdfGeneratorImplService");

				URL serviceUrl = new URL(AdminServiceImpl.PDFGENERATOR_WSDL_ENDPOINT);
				//serviceUrl = new URL("http://erender.ts.telekom.si/PdfGenerator/services?wsdl");
				//serviceUrl = new URL("http://erender-test.ts.telekom.si:8080/PdfGeneratorStaging/services?wsdl");
				//serviceUrl = new URL("http://erender-test.ts.telekom.si:8080/PdfGeneratorSb1/services?wsdl");

				PdfGeneratorImplService pdfGeneratorImplService = new PdfGeneratorImplService(serviceUrl, qname);
				PdfGenerator client = pdfGeneratorImplService.getPdfGeneratorImplPort();

				result = client.getPdf(inetId);
				decodedPdf = java.util.Base64.getDecoder().decode(result);

			} else {
				Integer typeId = Integer.valueOf(req.getParameter("typeId"));

				QName qname = new QName("http://erender.telekom.si/", "ERenderImplService");

				URL serviceUrl = new URL(AdminServiceImpl.ERENDER_WSDL_ENDPOINT);
				Logger.getLogger(this.getClass()).info("ERender webservice url endpoint: " + serviceUrl);
				//serviceUrl = new URL("http://erender.ts.telekom.si/PdfGenerator/erenderServices?wsdl");
				//serviceUrl = new URL("http://localhost:8081/PdfGenerator/erenderServices?wsdl");
				ERenderImplService erenderImplService = new ERenderImplService(serviceUrl, qname);
				ERender client = erenderImplService.getERenderImplPort();

				HashMapWrapper hm = new HashMapWrapper();
				String mimeType = "application/pdf";

				Holder<String> barcode = new Holder<>();
				Holder<List<String>> roles = new Holder<>();
				Holder<byte[]> document = new Holder<>();

				HashMapWrapper.Parameters params = new HashMapWrapper.Parameters();
				hm.setParameters(params);

				client.getContent(typeId.intValue(), hm, mimeType, barcode, roles, document);
				decodedPdf = document.value;
			}
			if(format.equalsIgnoreCase("html"))
				resp.setContentType("text/html");
			else if(format.equalsIgnoreCase("html"))
				resp.setContentType("application/pdf");
			else if(format.equalsIgnoreCase("crtext"))
				resp.setContentType("text/plain");
			
			resp.setContentLength((int) decodedPdf.length);
			ByteArrayInputStream baIs = new ByteArrayInputStream(decodedPdf);
			OutputStream responseOutputStream = resp.getOutputStream();
			int bytes;
			while ((bytes = baIs.read()) != -1) {
				responseOutputStream.write(bytes);
			}
			Logger.getLogger(this.getClass()).info("Returned pdf. Size: " + decodedPdf.length);
			WsServer.log(loginName, "Pdf produced size: " + decodedPdf.length);
		} catch (Exception_Exception e) {
			Logger.getLogger(this.getClass()).error(e.getMessage());
			WsServer.log(loginName, e.getMessage());
		}

	}

}
