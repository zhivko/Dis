package si.telekom.dis.server.rest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

import si.telekom.dis.server.jaxwsClient.catalogService.CatalogService;
import si.telekom.dis.server.jaxwsClient.catalogService.CatalogValue;
import si.telekom.dis.server.jaxwsClient.catalogService.GetCatalogRequestMsg;
import si.telekom.dis.server.jaxwsClient.catalogService.GetCatalogResponseMsg;
import si.telekom.dis.server.jaxwsClient.catalogService.ICatalogService;
import si.telekom.dis.server.jaxwsClient.catalogService.RequestMessageHeader;

//         /rest/catalogService?partOfProcessMilestoneName=

@Path("/catalogService")
public class CatalogServiceClient {

	public static void main(String[] args) {
	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> search(@Context Request request, @QueryParam("partOfProcessMilestoneName") String partOfProcMilestoneName) {
		List<String> ret = new ArrayList<String>();

		if (request.getMethod().equals("GET")) {
			int maxResultCount = 20;
			try {
				String wsdlEndpoint = "http://services.ts.telekom.si:80/services/common/base/v1/CatalogService/v/1.8.0?wsdl";
				QName qname = new QName("http://telekom.si/services/common/base/v1", "CatalogService");

				CatalogService service;
				URL serviceUrl = new URL(wsdlEndpoint);
				service = new CatalogService(serviceUrl, qname);
				ICatalogService client = service.getCatalogSoapBindingPort();

				final Binding binding = ((BindingProvider) client).getBinding();
				List<Handler> handlerList = binding.getHandlerChain();
				if (handlerList == null)
					handlerList = new ArrayList<Handler>();

				handlerList.add(new SecurityHandler("gdpr-system", "KulskoGeslo321."));
				binding.setHandlerChain(handlerList);

				RequestMessageHeader header = new RequestMessageHeader();
				header.setTransactionId("BusinessNotif-" + System.currentTimeMillis());
				GetCatalogRequestMsg msg = new GetCatalogRequestMsg();
				msg.setHeader(header);
				msg.setName("process.milestones");
				GetCatalogResponseMsg resp = client.getCatalog(msg);
				List<CatalogValue> values = resp.getCatalog().getCatalogValueCollection().getCatalogValues();

				String regExpr = ".*" + partOfProcMilestoneName + ".*";
				Pattern p = Pattern.compile(regExpr, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);// .
				for (CatalogValue catalogValue : values) {
					if (!partOfProcMilestoneName.equals("")) {
						Matcher m = p.matcher(catalogValue.getCode());
						if (m.find()) {
							String line = catalogValue.getCode();
							ret.add(line);
						}
					} else {
						String line = catalogValue.getCode();
						ret.add(line);
					}
					if (ret.size() > maxResultCount)
						break;
				}
				Logger.getLogger(this.getClass()).info("Process milestone returned: " + ret.size() + " results.");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret;
	}

	public final class SecurityHandler implements SOAPHandler<SOAPMessageContext> {

		private String user;
		private String pass;

		public SecurityHandler(String us, String pas) {
			this.user = us;
			this.pass = pas;
		}

		@Override
		public boolean handleMessage(final SOAPMessageContext msgCtx) {

			// Indicator telling us which direction this message is going in
			final Boolean outInd = (Boolean) msgCtx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

			// Handler must only add security headers to outbound messages
			if (outInd.booleanValue()) {
				try {
					// Get the SOAP Envelope
					final SOAPEnvelope envelope = msgCtx.getMessage().getSOAPPart().getEnvelope();

					// Header may or may not exist yet
					SOAPHeader header = envelope.getHeader();
					if (header == null)
						header = envelope.addHeader();

					// Add WSS Usertoken Element Tree
					final SOAPElement security = header.addChildElement("Security", "wsse",
							"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
					final SOAPElement userToken = security.addChildElement("UsernameToken", "wsse");
					userToken.addChildElement("Username", "wsse").addTextNode(this.user); // "gdpr-system");
					userToken.addChildElement("Password", "wsse").addTextNode(this.pass); // "KulskoGeslo321.");
				} catch (final Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
		}

		@Override
		public boolean handleFault(SOAPMessageContext context) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void close(MessageContext context) {
			// TODO Auto-generated method stub

		}

		@Override
		public Set<QName> getHeaders() {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
