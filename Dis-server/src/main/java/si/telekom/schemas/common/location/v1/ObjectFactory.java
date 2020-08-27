
package si.telekom.schemas.common.location.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.schemas.common.location.v1 package. 
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

    private final static QName _Address_QNAME = new QName("http://telekom.si/schemas/common/location/v1", "address");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.schemas.common.location.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link Site }
     * 
     */
    public Site createSite() {
        return new Site();
    }

    /**
     * Create an instance of {@link SiteCollection }
     * 
     */
    public SiteCollection createSiteCollection() {
        return new SiteCollection();
    }

    /**
     * Create an instance of {@link LocalAddress }
     * 
     */
    public LocalAddress createLocalAddress() {
        return new LocalAddress();
    }

    /**
     * Create an instance of {@link GeographicLocation }
     * 
     */
    public GeographicLocation createGeographicLocation() {
        return new GeographicLocation();
    }

    /**
     * Create an instance of {@link PostOffice }
     * 
     */
    public PostOffice createPostOffice() {
        return new PostOffice();
    }

    /**
     * Create an instance of {@link AddressCollection }
     * 
     */
    public AddressCollection createAddressCollection() {
        return new AddressCollection();
    }

    /**
     * Create an instance of {@link AddressStructured }
     * 
     */
    public AddressStructured createAddressStructured() {
        return new AddressStructured();
    }

    /**
     * Create an instance of {@link Settlement }
     * 
     */
    public Settlement createSettlement() {
        return new Settlement();
    }

    /**
     * Create an instance of {@link Muncipality }
     * 
     */
    public Muncipality createMuncipality() {
        return new Muncipality();
    }

    /**
     * Create an instance of {@link Continent }
     * 
     */
    public Continent createContinent() {
        return new Continent();
    }

    /**
     * Create an instance of {@link Street }
     * 
     */
    public Street createStreet() {
        return new Street();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link PlaceCollection }
     * 
     */
    public PlaceCollection createPlaceCollection() {
        return new PlaceCollection();
    }

    /**
     * Create an instance of {@link Place }
     * 
     */
    public Place createPlace() {
        return new Place();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Address }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/location/v1", name = "address")
    public JAXBElement<Address> createAddress(Address value) {
        return new JAXBElement<Address>(_Address_QNAME, Address.class, null, value);
    }

}
