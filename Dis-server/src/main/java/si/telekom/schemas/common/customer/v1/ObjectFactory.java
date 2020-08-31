
package si.telekom.schemas.common.customer.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.schemas.common.customer.v1 package. 
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

    private final static QName _Customer_QNAME = new QName("http://telekom.si/schemas/common/customer/v1", "customer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.schemas.common.customer.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link CustomerCollection }
     * 
     */
    public CustomerCollection createCustomerCollection() {
        return new CustomerCollection();
    }

    /**
     * Create an instance of {@link CreditCheckParameterCodeCollection }
     * 
     */
    public CreditCheckParameterCodeCollection createCreditCheckParameterCodeCollection() {
        return new CreditCheckParameterCodeCollection();
    }

    /**
     * Create an instance of {@link CreditCheckParameterCollection }
     * 
     */
    public CreditCheckParameterCollection createCreditCheckParameterCollection() {
        return new CreditCheckParameterCollection();
    }

    /**
     * Create an instance of {@link CustomerAccountDetail }
     * 
     */
    public CustomerAccountDetail createCustomerAccountDetail() {
        return new CustomerAccountDetail();
    }

    /**
     * Create an instance of {@link CustomerAccount }
     * 
     */
    public CustomerAccount createCustomerAccount() {
        return new CustomerAccount();
    }

    /**
     * Create an instance of {@link CustomerDetail }
     * 
     */
    public CustomerDetail createCustomerDetail() {
        return new CustomerDetail();
    }

    /**
     * Create an instance of {@link CreditCheckParameterCode }
     * 
     */
    public CreditCheckParameterCode createCreditCheckParameterCode() {
        return new CreditCheckParameterCode();
    }

    /**
     * Create an instance of {@link CustomerAccountCollection }
     * 
     */
    public CustomerAccountCollection createCustomerAccountCollection() {
        return new CustomerAccountCollection();
    }

    /**
     * Create an instance of {@link CreditCheckParameter }
     * 
     */
    public CreditCheckParameter createCreditCheckParameter() {
        return new CreditCheckParameter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/customer/v1", name = "customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

}
