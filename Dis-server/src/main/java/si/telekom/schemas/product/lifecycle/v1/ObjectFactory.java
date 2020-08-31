
package si.telekom.schemas.product.lifecycle.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.schemas.product.lifecycle.v1 package. 
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

    private final static QName _ProductOffering_QNAME = new QName("http://telekom.si/schemas/product/lifecycle/v1", "productOffering");
    private final static QName _ProductSpecification_QNAME = new QName("http://telekom.si/schemas/product/lifecycle/v1", "productSpecification");
    private final static QName _ProductOfferingPrice_QNAME = new QName("http://telekom.si/schemas/product/lifecycle/v1", "productOfferingPrice");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.schemas.product.lifecycle.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProductSpecification }
     * 
     */
    public ProductSpecification createProductSpecification() {
        return new ProductSpecification();
    }

    /**
     * Create an instance of {@link ProductOffering }
     * 
     */
    public ProductOffering createProductOffering() {
        return new ProductOffering();
    }

    /**
     * Create an instance of {@link ProductOfferingPrice }
     * 
     */
    public ProductOfferingPrice createProductOfferingPrice() {
        return new ProductOfferingPrice();
    }

    /**
     * Create an instance of {@link ProductSpecificationCollection }
     * 
     */
    public ProductSpecificationCollection createProductSpecificationCollection() {
        return new ProductSpecificationCollection();
    }

    /**
     * Create an instance of {@link PLMEntity }
     * 
     */
    public PLMEntity createPLMEntity() {
        return new PLMEntity();
    }

    /**
     * Create an instance of {@link ProductOfferingAndSpecificationCollection }
     * 
     */
    public ProductOfferingAndSpecificationCollection createProductOfferingAndSpecificationCollection() {
        return new ProductOfferingAndSpecificationCollection();
    }

    /**
     * Create an instance of {@link ProductOfferingCollection }
     * 
     */
    public ProductOfferingCollection createProductOfferingCollection() {
        return new ProductOfferingCollection();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductOffering }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/product/lifecycle/v1", name = "productOffering")
    public JAXBElement<ProductOffering> createProductOffering(ProductOffering value) {
        return new JAXBElement<ProductOffering>(_ProductOffering_QNAME, ProductOffering.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductSpecification }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/product/lifecycle/v1", name = "productSpecification")
    public JAXBElement<ProductSpecification> createProductSpecification(ProductSpecification value) {
        return new JAXBElement<ProductSpecification>(_ProductSpecification_QNAME, ProductSpecification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductOfferingPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/product/lifecycle/v1", name = "productOfferingPrice")
    public JAXBElement<ProductOfferingPrice> createProductOfferingPrice(ProductOfferingPrice value) {
        return new JAXBElement<ProductOfferingPrice>(_ProductOfferingPrice_QNAME, ProductOfferingPrice.class, null, value);
    }

}
