
package si.telekom.services.common.customer.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import si.telekom.services.common.base.v1.ServiceException;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.services.common.customer.v1 package. 
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

    private final static QName _CustomerServiceException_QNAME = new QName("http://telekom.si/services/common/customer/v1", "customerServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.services.common.customer.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomerResponse }
     * 
     */
    public GetCustomerResponse createGetCustomerResponse() {
        return new GetCustomerResponse();
    }

    /**
     * Create an instance of {@link GetCustomerResponseMsg }
     * 
     */
    public GetCustomerResponseMsg createGetCustomerResponseMsg() {
        return new GetCustomerResponseMsg();
    }

    /**
     * Create an instance of {@link CreditCheck }
     * 
     */
    public CreditCheck createCreditCheck() {
        return new CreditCheck();
    }

    /**
     * Create an instance of {@link CreditCheckRequestMsg }
     * 
     */
    public CreditCheckRequestMsg createCreditCheckRequestMsg() {
        return new CreditCheckRequestMsg();
    }

    /**
     * Create an instance of {@link CreateCustomer }
     * 
     */
    public CreateCustomer createCreateCustomer() {
        return new CreateCustomer();
    }

    /**
     * Create an instance of {@link CreateCustomerRequestMsg }
     * 
     */
    public CreateCustomerRequestMsg createCreateCustomerRequestMsg() {
        return new CreateCustomerRequestMsg();
    }

    /**
     * Create an instance of {@link CreateCustomerResponse }
     * 
     */
    public CreateCustomerResponse createCreateCustomerResponse() {
        return new CreateCustomerResponse();
    }

    /**
     * Create an instance of {@link CreateCustomerResponseMsg }
     * 
     */
    public CreateCustomerResponseMsg createCreateCustomerResponseMsg() {
        return new CreateCustomerResponseMsg();
    }

    /**
     * Create an instance of {@link UpdateCustomer }
     * 
     */
    public UpdateCustomer createUpdateCustomer() {
        return new UpdateCustomer();
    }

    /**
     * Create an instance of {@link UpdateCustomerRequestMsg }
     * 
     */
    public UpdateCustomerRequestMsg createUpdateCustomerRequestMsg() {
        return new UpdateCustomerRequestMsg();
    }

    /**
     * Create an instance of {@link UpdateCustomerResponse }
     * 
     */
    public UpdateCustomerResponse createUpdateCustomerResponse() {
        return new UpdateCustomerResponse();
    }

    /**
     * Create an instance of {@link UpdateCustomerResponseMsg }
     * 
     */
    public UpdateCustomerResponseMsg createUpdateCustomerResponseMsg() {
        return new UpdateCustomerResponseMsg();
    }

    /**
     * Create an instance of {@link CreditCheckResponse }
     * 
     */
    public CreditCheckResponse createCreditCheckResponse() {
        return new CreditCheckResponse();
    }

    /**
     * Create an instance of {@link CreditCheckResponseMsg }
     * 
     */
    public CreditCheckResponseMsg createCreditCheckResponseMsg() {
        return new CreditCheckResponseMsg();
    }

    /**
     * Create an instance of {@link FindCustomer }
     * 
     */
    public FindCustomer createFindCustomer() {
        return new FindCustomer();
    }

    /**
     * Create an instance of {@link FindCustomerRequestMsg }
     * 
     */
    public FindCustomerRequestMsg createFindCustomerRequestMsg() {
        return new FindCustomerRequestMsg();
    }

    /**
     * Create an instance of {@link FindCustomerResponse }
     * 
     */
    public FindCustomerResponse createFindCustomerResponse() {
        return new FindCustomerResponse();
    }

    /**
     * Create an instance of {@link FindCustomerResponseMsg }
     * 
     */
    public FindCustomerResponseMsg createFindCustomerResponseMsg() {
        return new FindCustomerResponseMsg();
    }

    /**
     * Create an instance of {@link GetCustomer }
     * 
     */
    public GetCustomer createGetCustomer() {
        return new GetCustomer();
    }

    /**
     * Create an instance of {@link GetCustomerRequestMsg }
     * 
     */
    public GetCustomerRequestMsg createGetCustomerRequestMsg() {
        return new GetCustomerRequestMsg();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/services/common/customer/v1", name = "customerServiceException")
    public JAXBElement<ServiceException> createCustomerServiceException(ServiceException value) {
        return new JAXBElement<ServiceException>(_CustomerServiceException_QNAME, ServiceException.class, null, value);
    }

}
