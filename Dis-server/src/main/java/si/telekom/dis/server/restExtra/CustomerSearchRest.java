//https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup-1.5
package si.telekom.dis.server.restExtra;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

import io.swagger.annotations.Api;
import si.telekom.dis.server.restExtra.api.CustomerSearchApiService;
import si.telekom.dis.server.restExtra.api.NotFoundException;
import si.telekom.schemas.common.customer.v1.Customer;
import si.telekom.schemas.common.party.v1.Individual;
import si.telekom.schemas.common.party.v1.Organization;
import si.telekom.services.common.base.v1.RequestMessageHeader;
import si.telekom.services.common.customer.v1.CustomerService;
import si.telekom.services.common.customer.v1.FindCustomer;
import si.telekom.services.common.customer.v1.FindCustomerRequestMsg;
import si.telekom.services.common.customer.v1.FindCustomerResponseMsg;
import si.telekom.services.common.customer.v1.ICustomerService;

// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/application.wadl?detail=true
// http://localhost:8080/Dis-server/rest/disRest/importDocument
// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/importDocument
// mvn clean package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true

// http://localhost:8080/Dis-server/api/v1/dis-dev/application.wadl
// http://localhost:8080/Dis-server/rest/application.wadl
@Api
public class CustomerSearchRest extends CustomerSearchApiService {

	@Override
	public Response getCustomersByPartOfName(String partOfCustName, SecurityContext securityContext) throws NotFoundException {
		List<String> ret = new ArrayList<String>();

		String endpointUrl = "http://services-stg.ts.telekom.si:8000/services/common/customer/v1/CustomerService/v/1.8.0";

		FindCustomer fc = new FindCustomer();

		CustomerService cs;
		try {
			cs = new CustomerService(new URL(endpointUrl));

			// WebServiceFeature[] features = {};
			ICustomerService servicePort = cs.getCustomerSoapBindingPort();

			Map<String, Object> req_ctx = ((BindingProvider) servicePort).getRequestContext();
			req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

			req_ctx.put(BindingProvider.USERNAME_PROPERTY, "dmadmin");
			req_ctx.put(BindingProvider.PASSWORD_PROPERTY, "Andrej1");

			FindCustomerRequestMsg findCustomerRequestMsg = new FindCustomerRequestMsg();

			RequestMessageHeader reqMsgHeader = new RequestMessageHeader();
			reqMsgHeader.setSource("DISTelekom");
			String sessId = String.valueOf(System.currentTimeMillis());
			reqMsgHeader.setSessionId(sessId);
			reqMsgHeader.setTransactionId(sessId);
			findCustomerRequestMsg.setHeader(reqMsgHeader);
			findCustomerRequestMsg.setSearchFor("CUSTOMER.NAME");
			findCustomerRequestMsg.setSearchParameter(partOfCustName.toUpperCase());
			fc.setFindCustomerRequestMsg(findCustomerRequestMsg);

			FindCustomerResponseMsg responseMsg = servicePort.findCustomer(findCustomerRequestMsg);

			int maxCustomerResultCount = 20;
			int resultCount = 0;
			for (Customer c : responseMsg.getCustomer()) {

				String name = "unknown-" + c.getId();
				String id = "unknown";
				String taxid = "unknown";
				if (c.getParty() instanceof Individual) {
					name = ((Individual) c.getParty()).getFormattedName();
					id = ((Individual) c.getParty()).getId();
					taxid = c.getParty().getTaxNo();
				} else if (c.getParty() instanceof Organization) {
					name = ((Organization) c.getParty()).getShortName();
					id = ((Organization) c.getParty()).getId();
					taxid = ((Organization) c.getParty()).getTaxNo();
				}

				String line = id + "|" + name + "|" + taxid;
				ret.add(line);
				resultCount++;
				if (resultCount > maxCustomerResultCount)
					break;
			}
			Logger.getLogger(this.getClass()).info("Customer search returned: " + ret.size() + " customers.");
			return Response.ok(ret.toArray()).build();
		} catch (Exception ex) {
			Logger.getLogger(this.getClass()).error("error", ex);
			return Response.serverError().build();
		}

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
