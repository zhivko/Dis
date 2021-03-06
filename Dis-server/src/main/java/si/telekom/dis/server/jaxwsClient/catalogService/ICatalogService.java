
package si.telekom.dis.server.jaxwsClient.catalogService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ICatalogService", targetNamespace = "http://telekom.si/services/common/base/v1")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ICatalogService {


    /**
     * 
     * @param findCatalogRequestMsg
     * @return
     *     returns si.telekom.dis.server.jaxwsClient.catalogService.FindCatalogResponseMsg
     * @throws CatalogServiceException_Exception
     */
    @WebMethod(action = "http://telekom.si/services/common/base/v1/findCatalog")
    @WebResult(name = "findCatalogResponseMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
    @RequestWrapper(localName = "findCatalog", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.FindCatalog")
    @ResponseWrapper(localName = "findCatalogResponse", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.FindCatalogResponse")
    public FindCatalogResponseMsg findCatalog(
        @WebParam(name = "findCatalogRequestMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
        FindCatalogRequestMsg findCatalogRequestMsg)
        throws CatalogServiceException_Exception
    ;

    /**
     * 
     * @param getCatalogRequestMsg
     * @return
     *     returns si.telekom.dis.server.jaxwsClient.catalogService.GetCatalogResponseMsg
     * @throws CatalogServiceException_Exception
     */
    @WebMethod(action = "http://telekom.si/services/common/base/v1/getCatalog")
    @WebResult(name = "getCatalogResponseMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
    @RequestWrapper(localName = "getCatalog", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.GetCatalog")
    @ResponseWrapper(localName = "getCatalogResponse", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.GetCatalogResponse")
    public GetCatalogResponseMsg getCatalog(
        @WebParam(name = "getCatalogRequestMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
        GetCatalogRequestMsg getCatalogRequestMsg)
        throws CatalogServiceException_Exception
    ;

    /**
     * 
     * @param getSystemInfoRequestMsg
     * @return
     *     returns si.telekom.dis.server.jaxwsClient.catalogService.GetSystemInfoResponseMsg
     */
    @WebMethod(action = "http://telekom.si/services/common/base/v1/getSystemInfo")
    @WebResult(name = "getSystemInfoResponseMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
    @RequestWrapper(localName = "getSystemInfo", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.GetSystemInfo")
    @ResponseWrapper(localName = "getSystemInfoResponse", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.GetSystemInfoResponse")
    public GetSystemInfoResponseMsg getSystemInfo(
        @WebParam(name = "getSystemInfoRequestMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
        GetSystemInfoRequestMsg getSystemInfoRequestMsg);

    /**
     * 
     * @param getUniversalCatalogRequestMsg
     * @return
     *     returns si.telekom.dis.server.jaxwsClient.catalogService.GetUniversalCatalogResponseMsg
     * @throws CatalogServiceException_Exception
     */
    @WebMethod(action = "http://telekom.si/services/common/base/v1/getUniversalCatalog")
    @WebResult(name = "getUniversalCatalogResponseMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
    @RequestWrapper(localName = "getUniversalCatalog", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.GetUniversalCatalog")
    @ResponseWrapper(localName = "getUniversalCatalogResponse", targetNamespace = "http://telekom.si/services/common/base/v1", className = "si.telekom.dis.server.jaxwsClient.catalogService.GetUniversalCatalogResponse")
    public GetUniversalCatalogResponseMsg getUniversalCatalog(
        @WebParam(name = "getUniversalCatalogRequestMsg", targetNamespace = "http://telekom.si/services/common/base/v1")
        GetUniversalCatalogRequestMsg getUniversalCatalogRequestMsg)
        throws CatalogServiceException_Exception
    ;

}
