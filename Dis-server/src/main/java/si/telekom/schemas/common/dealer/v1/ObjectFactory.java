
package si.telekom.schemas.common.dealer.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.schemas.common.dealer.v1 package. 
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

    private final static QName _Dealer_QNAME = new QName("http://telekom.si/schemas/common/dealer/v1", "dealer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.schemas.common.dealer.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Dealer }
     * 
     */
    public Dealer createDealer() {
        return new Dealer();
    }

    /**
     * Create an instance of {@link SalesOrganizationalUnit }
     * 
     */
    public SalesOrganizationalUnit createSalesOrganizationalUnit() {
        return new SalesOrganizationalUnit();
    }

    /**
     * Create an instance of {@link DealerUserSalesOU }
     * 
     */
    public DealerUserSalesOU createDealerUserSalesOU() {
        return new DealerUserSalesOU();
    }

    /**
     * Create an instance of {@link ContractCollection }
     * 
     */
    public ContractCollection createContractCollection() {
        return new ContractCollection();
    }

    /**
     * Create an instance of {@link PointOfSale }
     * 
     */
    public PointOfSale createPointOfSale() {
        return new PointOfSale();
    }

    /**
     * Create an instance of {@link PointOfSaleInvolvmentRole }
     * 
     */
    public PointOfSaleInvolvmentRole createPointOfSaleInvolvmentRole() {
        return new PointOfSaleInvolvmentRole();
    }

    /**
     * Create an instance of {@link RepresentativeCollection }
     * 
     */
    public RepresentativeCollection createRepresentativeCollection() {
        return new RepresentativeCollection();
    }

    /**
     * Create an instance of {@link Isp }
     * 
     */
    public Isp createIsp() {
        return new Isp();
    }

    /**
     * Create an instance of {@link DealerInvolvmentRole }
     * 
     */
    public DealerInvolvmentRole createDealerInvolvmentRole() {
        return new DealerInvolvmentRole();
    }

    /**
     * Create an instance of {@link WorkingHoursCollection }
     * 
     */
    public WorkingHoursCollection createWorkingHoursCollection() {
        return new WorkingHoursCollection();
    }

    /**
     * Create an instance of {@link DealerUserRoleCollection }
     * 
     */
    public DealerUserRoleCollection createDealerUserRoleCollection() {
        return new DealerUserRoleCollection();
    }

    /**
     * Create an instance of {@link WorkingHours }
     * 
     */
    public WorkingHours createWorkingHours() {
        return new WorkingHours();
    }

    /**
     * Create an instance of {@link SalesChannel }
     * 
     */
    public SalesChannel createSalesChannel() {
        return new SalesChannel();
    }

    /**
     * Create an instance of {@link SalesChannelCollection }
     * 
     */
    public SalesChannelCollection createSalesChannelCollection() {
        return new SalesChannelCollection();
    }

    /**
     * Create an instance of {@link DealerRoleCollection }
     * 
     */
    public DealerRoleCollection createDealerRoleCollection() {
        return new DealerRoleCollection();
    }

    /**
     * Create an instance of {@link DealerUserSalesOUCollection }
     * 
     */
    public DealerUserSalesOUCollection createDealerUserSalesOUCollection() {
        return new DealerUserSalesOUCollection();
    }

    /**
     * Create an instance of {@link DealerUser }
     * 
     */
    public DealerUser createDealerUser() {
        return new DealerUser();
    }

    /**
     * Create an instance of {@link Representative }
     * 
     */
    public Representative createRepresentative() {
        return new Representative();
    }

    /**
     * Create an instance of {@link SalesTypeCollection }
     * 
     */
    public SalesTypeCollection createSalesTypeCollection() {
        return new SalesTypeCollection();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContract() {
        return new Contract();
    }

    /**
     * Create an instance of {@link DealerUserCollection }
     * 
     */
    public DealerUserCollection createDealerUserCollection() {
        return new DealerUserCollection();
    }

    /**
     * Create an instance of {@link PointOfSaleCollection }
     * 
     */
    public PointOfSaleCollection createPointOfSaleCollection() {
        return new PointOfSaleCollection();
    }

    /**
     * Create an instance of {@link SalesType }
     * 
     */
    public SalesType createSalesType() {
        return new SalesType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Dealer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://telekom.si/schemas/common/dealer/v1", name = "dealer")
    public JAXBElement<Dealer> createDealer(Dealer value) {
        return new JAXBElement<Dealer>(_Dealer_QNAME, Dealer.class, null, value);
    }

}
