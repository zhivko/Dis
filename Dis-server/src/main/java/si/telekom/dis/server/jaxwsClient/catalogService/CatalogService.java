
package si.telekom.dis.server.jaxwsClient.catalogService;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "CatalogService", targetNamespace = "http://telekom.si/services/common/base/v1", wsdlLocation = "http://services.ts.telekom.si/services/common/base/v1/CatalogService/v/1.8.0?wsdl")
public class CatalogService
    extends Service
{

    private final static URL CATALOGSERVICE_WSDL_LOCATION;
    private final static WebServiceException CATALOGSERVICE_EXCEPTION;
    private final static QName CATALOGSERVICE_QNAME = new QName("http://telekom.si/services/common/base/v1", "CatalogService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://services.ts.telekom.si/services/common/base/v1/CatalogService/v/1.8.0?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CATALOGSERVICE_WSDL_LOCATION = url;
        CATALOGSERVICE_EXCEPTION = e;
    }

    public CatalogService() {
        super(__getWsdlLocation(), CATALOGSERVICE_QNAME);
    }

    public CatalogService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns ICatalogService
     */
    @WebEndpoint(name = "CatalogSoapBindingPort180")
    public ICatalogService getCatalogSoapBindingPort180() {
        return super.getPort(new QName("http://telekom.si/services/common/base/v1", "CatalogSoapBindingPort180"), ICatalogService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICatalogService
     */
    @WebEndpoint(name = "CatalogSoapBindingPort180")
    public ICatalogService getCatalogSoapBindingPort180(WebServiceFeature... features) {
        return super.getPort(new QName("http://telekom.si/services/common/base/v1", "CatalogSoapBindingPort180"), ICatalogService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CATALOGSERVICE_EXCEPTION!= null) {
            throw CATALOGSERVICE_EXCEPTION;
        }
        return CATALOGSERVICE_WSDL_LOCATION;
    }

}