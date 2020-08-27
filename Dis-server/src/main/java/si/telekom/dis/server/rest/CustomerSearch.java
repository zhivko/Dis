package si.telekom.dis.server.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.xml.ws.BindingProvider;

import org.apache.bcel.generic.RET;
import org.apache.log4j.Logger;

import si.telekom.schemas.common.customer.v1.Customer;
import si.telekom.schemas.common.party.v1.Individual;
import si.telekom.schemas.common.party.v1.Organization;
import si.telekom.services.common.base.v1.RequestMessageHeader;
import si.telekom.services.common.customer.v1.CustomerService;
import si.telekom.services.common.customer.v1.CustomerServiceException;
import si.telekom.services.common.customer.v1.FindCustomer;
import si.telekom.services.common.customer.v1.FindCustomerRequestMsg;
import si.telekom.services.common.customer.v1.FindCustomerResponseMsg;
import si.telekom.services.common.customer.v1.ICustomerService;

@Path("/customerSearch")
public class CustomerSearch {

	public static void main(String[] args) {
		// CustomerSearch cs = new CustomerSearch();
		// List<String> list = cs.search("ŽIVKOVI");
		// for (String line : list) {
		// System.out.println(line);
		// }
	}
	
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces({ MediaType.APPLICATION_JSON })
	public List<String> search(@Context Request request, @QueryParam("partOfCustName") String partOfCustName) {
		List<String> ret = new ArrayList<String>();
		
		
		if (request.getMethod().equals("GET")) {

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
				int resultCount =0;
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
					if(resultCount>maxCustomerResultCount)
						break;
				}
				Logger.getLogger(this.getClass()).info("Customer search returned: " + ret.size() + " customers.");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CustomerServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
}
