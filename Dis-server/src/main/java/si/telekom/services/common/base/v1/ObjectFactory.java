
package si.telekom.services.common.base.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.services.common.base.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PagingFilter_QNAME = new QName("http://telekom.si/services/common/base/v1", "pagingFilter");
    private final static QName _ResultFilter_QNAME = new QName("http://telekom.si/services/common/base/v1", "resultFilter");
    private final static QName _Condition_QNAME = new QName("http://telekom.si/services/common/base/v1", "condition");
    private final static QName _Filter_QNAME = new QName("http://telekom.si/services/common/base/v1", "filter");
    private final static QName _SearchFilter_QNAME = new QName("http://telekom.si/services/common/base/v1", "searchFilter");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.services.common.base.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Filter }
     * 
     */
    public Filter createFilter() {
        return new Filter();
    }

    /**
     * Create an instance of {@link Condition }
     * 
     */
    public Condition createCondition() {
        return new Condition();
    }

    /**
     * Create an instance of {@link PagingFilter }
     * 
     */
    public PagingFilter createPagingFilter() {
        return new PagingFilter();
    }

    /**
     * Create an instance of {@link GetSystemInfoResponse }
     * 
     */
    public GetSystemInfoResponse createGetSystemInfoResponse() {
        return new GetSystemInfoResponse();
    }

    /**
     * Create an instance of {@link GetSystemInfoResponseMsg }
     * 
     */
    public GetSystemInfoResponseMsg createGetSystemInfoResponseMsg() {
        return new GetSystemInfoResponseMsg();
    }

    /**
     * Create an instance of {@link GetSystemInfo }
     * 
     */
    public GetSystemInfo createGetSystemInfo() {
        return new GetSystemInfo();
    }

    /**
     * Create an instance of {@link GetSystemInfoRequestMsg }
     * 
     */
    public GetSystemInfoRequestMsg createGetSystemInfoRequestMsg() {
        return new GetSystemInfoRequestMsg();
    }

    /**
     * Create an instance of {@link SearchFilter }
     * 
     */
    public SearchFilter createSearchFilter() {
        return new SearchFilter();
    }

    /**
     * Create an instance of {@link ResultFilter }
     * 
     */
    public ResultFilter createResultFilter() {
        return new ResultFilter();
    }

    /**
     * Create an instance of {@link SearchRequest }
     * 
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest();
    }

    /**
     * Create an instance of {@link SystemInfo }
     * 
     */
    public SystemInfo createSystemInfo() {
        return new SystemInfo();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link RequestMessageHeader }
     * 
     */
    public RequestMessageHeader createRequestMessageHeader() {
        return new RequestMessageHeader();
    }

    /**
     * Create an instance of {@link ResponseMessage }
     * 
     */
    public ResponseMessage createResponseMessage() {
        return new ResponseMessage();
    }

    /**
     * Create an instance of {@link ServiceInfo }
     * 
     */
    public ServiceInfo createServiceInfo() {
        return new ServiceInfo();
    }

    /**
     * Create an instance of {@link ServiceException }
     * 
     */
    public ServiceException createServiceException() {
        return new ServiceException();
    }

    /**
     * Create an instance of {@link PagingInfo }
     * 
     */
    public PagingInfo createPagingInfo() {
        return new PagingInfo();
    }

    /**
     * Create an instance of {@link RequestMessage }
     * 
     */
    public RequestMessage createRequestMessage() {
        return new RequestMessage();
    }

    /**
     * Create an instance of {@link ResponseMessageHeader }
     * 
     */
    public ResponseMessageHeader createResponseMessageHeader() {
        return new ResponseMessageHeader();
    }

    /**
     * Create an instance of {@link ServiceInfoCollection }
     * 
     */
    public ServiceInfoCollection createServiceInfoCollection() {
        return new ServiceInfoCollection();
    }

    /**
     * Create an instance of {@link ResultEntity }
     * 
     */
    public ResultEntity createResultEntity() {
        return new ResultEntity();
    }

    /**
     * Create an instance of {@link ServiceExceptionCollection }
     * 
     */
    public ServiceExceptionCollection createServiceExceptionCollection() {
        return new ServiceExceptionCollection();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagingFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/services/common/base/v1", name = "pagingFilter")
    public JAXBElement<PagingFilter> createPagingFilter(PagingFilter value) {
        return new JAXBElement<PagingFilter>(_PagingFilter_QNAME, PagingFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/services/common/base/v1", name = "resultFilter")
    public JAXBElement<ResultFilter> createResultFilter(ResultFilter value) {
        return new JAXBElement<ResultFilter>(_ResultFilter_QNAME, ResultFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Condition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/services/common/base/v1", name = "condition")
    public JAXBElement<Condition> createCondition(Condition value) {
        return new JAXBElement<Condition>(_Condition_QNAME, Condition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Filter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/services/common/base/v1", name = "filter")
    public JAXBElement<Filter> createFilter(Filter value) {
        return new JAXBElement<Filter>(_Filter_QNAME, Filter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/services/common/base/v1", name = "searchFilter")
    public JAXBElement<SearchFilter> createSearchFilter(SearchFilter value) {
        return new JAXBElement<SearchFilter>(_SearchFilter_QNAME, SearchFilter.class, null, value);
    }

}
