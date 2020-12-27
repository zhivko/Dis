
package si.telekom.dis.server.jaxwsClient.eRender;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the si.telekom.dis.server.jaxwsClient.eRender package. 
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

    private final static QName _Domain_QNAME = new QName("http://templates.mobitel.com", "domain");
    private final static QName _Barcodes_QNAME = new QName("http://erender.telekom.com", "barcodes");
    private final static QName _Inetids_QNAME = new QName("http://erender.telekom.com", "inetids");
    private final static QName _Password_QNAME = new QName("http://templates.mobitel.com", "password");
    private final static QName _OsName_QNAME = new QName("http://templates.mobitel.com", "osName");
    private final static QName _CreateDctmObjectContent_QNAME = new QName("http://templates.mobitel.com/", "content");
    private final static QName _GetTemplateByObjectNameResponsePdfBase64Encoded_QNAME = new QName("", "pdfBase64Encoded");
    private final static QName _GetOOPdfResponseReturn_QNAME = new QName("http://templates.mobitel.com/", "return");
    private final static QName _AddRenditionData_QNAME = new QName("http://templates.mobitel.com/", "data");
    private final static QName _GetContentResponseResult_QNAME = new QName("http://templates.mobitel.com/", "result");
    private final static QName _GetOpenOfficePdfForHashMapResponseReturn_QNAME = new QName("", "return");
    private final static QName _GetPdfFromOOTemplateArg0_QNAME = new QName("", "arg0");
    private final static QName _ConcatTwoPdfsArg1_QNAME = new QName("", "arg1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: si.telekom.dis.server.jaxwsClient.eRender
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HashMapWrapper }
     * 
     */
    public HashMapWrapper createHashMapWrapper() {
        return new HashMapWrapper();
    }

    /**
     * Create an instance of {@link HashMapWrapper.Parameters }
     * 
     */
    public HashMapWrapper.Parameters createHashMapWrapperParameters() {
        return new HashMapWrapper.Parameters();
    }

    /**
     * Create an instance of {@link SyncAttsResponse }
     * 
     */
    public SyncAttsResponse createSyncAttsResponse() {
        return new SyncAttsResponse();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link Checkin }
     * 
     */
    public Checkin createCheckin() {
        return new Checkin();
    }

    /**
     * Create an instance of {@link GetRenditions }
     * 
     */
    public GetRenditions createGetRenditions() {
        return new GetRenditions();
    }

    /**
     * Create an instance of {@link GetAtts }
     * 
     */
    public GetAtts createGetAtts() {
        return new GetAtts();
    }

    /**
     * Create an instance of {@link ConfigureERender }
     * 
     */
    public ConfigureERender createConfigureERender() {
        return new ConfigureERender();
    }

    /**
     * Create an instance of {@link Lock }
     * 
     */
    public Lock createLock() {
        return new Lock();
    }

    /**
     * Create an instance of {@link CreateDctmObjectMTOM }
     * 
     */
    public CreateDctmObjectMTOM createCreateDctmObjectMTOM() {
        return new CreateDctmObjectMTOM();
    }

    /**
     * Create an instance of {@link GetUserDomain }
     * 
     */
    public GetUserDomain createGetUserDomain() {
        return new GetUserDomain();
    }

    /**
     * Create an instance of {@link LockResponse }
     * 
     */
    public LockResponse createLockResponse() {
        return new LockResponse();
    }

    /**
     * Create an instance of {@link GetRenditionsResponse }
     * 
     */
    public GetRenditionsResponse createGetRenditionsResponse() {
        return new GetRenditionsResponse();
    }

    /**
     * Create an instance of {@link Format }
     * 
     */
    public Format createFormat() {
        return new Format();
    }

    /**
     * Create an instance of {@link RevokeResponse }
     * 
     */
    public RevokeResponse createRevokeResponse() {
        return new RevokeResponse();
    }

    /**
     * Create an instance of {@link EvaluateSql }
     * 
     */
    public EvaluateSql createEvaluateSql() {
        return new EvaluateSql();
    }

    /**
     * Create an instance of {@link ConfigureERenderResponse }
     * 
     */
    public ConfigureERenderResponse createConfigureERenderResponse() {
        return new ConfigureERenderResponse();
    }

    /**
     * Create an instance of {@link GetOpenOfficePdfForHashMapResponse }
     * 
     */
    public GetOpenOfficePdfForHashMapResponse createGetOpenOfficePdfForHashMapResponse() {
        return new GetOpenOfficePdfForHashMapResponse();
    }

    /**
     * Create an instance of {@link GetContentResponse }
     * 
     */
    public GetContentResponse createGetContentResponse() {
        return new GetContentResponse();
    }

    /**
     * Create an instance of {@link GetPdfNewFromKeyValues }
     * 
     */
    public GetPdfNewFromKeyValues createGetPdfNewFromKeyValues() {
        return new GetPdfNewFromKeyValues();
    }

    /**
     * Create an instance of {@link KeyValue }
     * 
     */
    public KeyValue createKeyValue() {
        return new KeyValue();
    }

    /**
     * Create an instance of {@link GetTemplateByObjectNameResponse }
     * 
     */
    public GetTemplateByObjectNameResponse createGetTemplateByObjectNameResponse() {
        return new GetTemplateByObjectNameResponse();
    }

    /**
     * Create an instance of {@link IsUserMember }
     * 
     */
    public IsUserMember createIsUserMember() {
        return new IsUserMember();
    }

    /**
     * Create an instance of {@link RunSmartListResponse }
     * 
     */
    public RunSmartListResponse createRunSmartListResponse() {
        return new RunSmartListResponse();
    }

    /**
     * Create an instance of {@link QueryResult }
     * 
     */
    public QueryResult createQueryResult() {
        return new QueryResult();
    }

    /**
     * Create an instance of {@link CheckinMinor }
     * 
     */
    public CheckinMinor createCheckinMinor() {
        return new CheckinMinor();
    }

    /**
     * Create an instance of {@link CheckinTemplateResponse }
     * 
     */
    public CheckinTemplateResponse createCheckinTemplateResponse() {
        return new CheckinTemplateResponse();
    }

    /**
     * Create an instance of {@link GetPdf }
     * 
     */
    public GetPdf createGetPdf() {
        return new GetPdf();
    }

    /**
     * Create an instance of {@link GetDctmAttributeResponse }
     * 
     */
    public GetDctmAttributeResponse createGetDctmAttributeResponse() {
        return new GetDctmAttributeResponse();
    }

    /**
     * Create an instance of {@link DctmAttribute }
     * 
     */
    public DctmAttribute createDctmAttribute() {
        return new DctmAttribute();
    }

    /**
     * Create an instance of {@link SetAttributeToResponse }
     * 
     */
    public SetAttributeToResponse createSetAttributeToResponse() {
        return new SetAttributeToResponse();
    }

    /**
     * Create an instance of {@link SyncTemplate }
     * 
     */
    public SyncTemplate createSyncTemplate() {
        return new SyncTemplate();
    }

    /**
     * Create an instance of {@link RunSmartList }
     * 
     */
    public RunSmartList createRunSmartList() {
        return new RunSmartList();
    }

    /**
     * Create an instance of {@link GetPdfNewResponse }
     * 
     */
    public GetPdfNewResponse createGetPdfNewResponse() {
        return new GetPdfNewResponse();
    }

    /**
     * Create an instance of {@link GetOOPdfResponse }
     * 
     */
    public GetOOPdfResponse createGetOOPdfResponse() {
        return new GetOOPdfResponse();
    }

    /**
     * Create an instance of {@link SetContent }
     * 
     */
    public SetContent createSetContent() {
        return new SetContent();
    }

    /**
     * Create an instance of {@link GetPdfMultiple }
     * 
     */
    public GetPdfMultiple createGetPdfMultiple() {
        return new GetPdfMultiple();
    }

    /**
     * Create an instance of {@link GeneratePdfAndRaiseMinorVersionResponse }
     * 
     */
    public GeneratePdfAndRaiseMinorVersionResponse createGeneratePdfAndRaiseMinorVersionResponse() {
        return new GeneratePdfAndRaiseMinorVersionResponse();
    }

    /**
     * Create an instance of {@link RemoveVersionLabelResponse }
     * 
     */
    public RemoveVersionLabelResponse createRemoveVersionLabelResponse() {
        return new RemoveVersionLabelResponse();
    }

    /**
     * Create an instance of {@link CheckinTemplateByTemplateIdResponse }
     * 
     */
    public CheckinTemplateByTemplateIdResponse createCheckinTemplateByTemplateIdResponse() {
        return new CheckinTemplateByTemplateIdResponse();
    }

    /**
     * Create an instance of {@link ConcatTwoPdfs }
     * 
     */
    public ConcatTwoPdfs createConcatTwoPdfs() {
        return new ConcatTwoPdfs();
    }

    /**
     * Create an instance of {@link CheckinTemplate }
     * 
     */
    public CheckinTemplate createCheckinTemplate() {
        return new CheckinTemplate();
    }

    /**
     * Create an instance of {@link TransferDctmObjectsToDev }
     * 
     */
    public TransferDctmObjectsToDev createTransferDctmObjectsToDev() {
        return new TransferDctmObjectsToDev();
    }

    /**
     * Create an instance of {@link CheckinMinorResponse }
     * 
     */
    public CheckinMinorResponse createCheckinMinorResponse() {
        return new CheckinMinorResponse();
    }

    /**
     * Create an instance of {@link GetTemplateByTemplateIdResponse }
     * 
     */
    public GetTemplateByTemplateIdResponse createGetTemplateByTemplateIdResponse() {
        return new GetTemplateByTemplateIdResponse();
    }

    /**
     * Create an instance of {@link Template }
     * 
     */
    public Template createTemplate() {
        return new Template();
    }

    /**
     * Create an instance of {@link DeleteDctmObject }
     * 
     */
    public DeleteDctmObject createDeleteDctmObject() {
        return new DeleteDctmObject();
    }

    /**
     * Create an instance of {@link TransferDctmObjectsToDevResponse }
     * 
     */
    public TransferDctmObjectsToDevResponse createTransferDctmObjectsToDevResponse() {
        return new TransferDctmObjectsToDevResponse();
    }

    /**
     * Create an instance of {@link CreateDctmObjectResponse }
     * 
     */
    public CreateDctmObjectResponse createCreateDctmObjectResponse() {
        return new CreateDctmObjectResponse();
    }

    /**
     * Create an instance of {@link GeneratePdfAndRaiseMinorVersion }
     * 
     */
    public GeneratePdfAndRaiseMinorVersion createGeneratePdfAndRaiseMinorVersion() {
        return new GeneratePdfAndRaiseMinorVersion();
    }

    /**
     * Create an instance of {@link GetDctmQueries }
     * 
     */
    public GetDctmQueries createGetDctmQueries() {
        return new GetDctmQueries();
    }

    /**
     * Create an instance of {@link RemoveVersionLabel }
     * 
     */
    public RemoveVersionLabel createRemoveVersionLabel() {
        return new RemoveVersionLabel();
    }

    /**
     * Create an instance of {@link GetTemplateFields }
     * 
     */
    public GetTemplateFields createGetTemplateFields() {
        return new GetTemplateFields();
    }

    /**
     * Create an instance of {@link ConcatTwoPdfsResponse }
     * 
     */
    public ConcatTwoPdfsResponse createConcatTwoPdfsResponse() {
        return new ConcatTwoPdfsResponse();
    }

    /**
     * Create an instance of {@link GetPdfNewFromKeyValuesResponse }
     * 
     */
    public GetPdfNewFromKeyValuesResponse createGetPdfNewFromKeyValuesResponse() {
        return new GetPdfNewFromKeyValuesResponse();
    }

    /**
     * Create an instance of {@link CreateDctmObjectMTOMResponse }
     * 
     */
    public CreateDctmObjectMTOMResponse createCreateDctmObjectMTOMResponse() {
        return new CreateDctmObjectMTOMResponse();
    }

    /**
     * Create an instance of {@link GetFormatsFromDosExtension }
     * 
     */
    public GetFormatsFromDosExtension createGetFormatsFromDosExtension() {
        return new GetFormatsFromDosExtension();
    }

    /**
     * Create an instance of {@link GetTemplateList }
     * 
     */
    public GetTemplateList createGetTemplateList() {
        return new GetTemplateList();
    }

    /**
     * Create an instance of {@link SyncAtts }
     * 
     */
    public SyncAtts createSyncAtts() {
        return new SyncAtts();
    }

    /**
     * Create an instance of {@link GetUserDomainResponse }
     * 
     */
    public GetUserDomainResponse createGetUserDomainResponse() {
        return new GetUserDomainResponse();
    }

    /**
     * Create an instance of {@link AddRendition }
     * 
     */
    public AddRendition createAddRendition() {
        return new AddRendition();
    }

    /**
     * Create an instance of {@link GetOpenOfficePdfResponse }
     * 
     */
    public GetOpenOfficePdfResponse createGetOpenOfficePdfResponse() {
        return new GetOpenOfficePdfResponse();
    }

    /**
     * Create an instance of {@link StringByRef }
     * 
     */
    public StringByRef createStringByRef() {
        return new StringByRef();
    }

    /**
     * Create an instance of {@link GetPdfResponse }
     * 
     */
    public GetPdfResponse createGetPdfResponse() {
        return new GetPdfResponse();
    }

    /**
     * Create an instance of {@link GetOpenOfficePdfForHashMap }
     * 
     */
    public GetOpenOfficePdfForHashMap createGetOpenOfficePdfForHashMap() {
        return new GetOpenOfficePdfForHashMap();
    }

    /**
     * Create an instance of {@link DeleteDctmObjectsResponse }
     * 
     */
    public DeleteDctmObjectsResponse createDeleteDctmObjectsResponse() {
        return new DeleteDctmObjectsResponse();
    }

    /**
     * Create an instance of {@link SetContentResponse }
     * 
     */
    public SetContentResponse createSetContentResponse() {
        return new SetContentResponse();
    }

    /**
     * Create an instance of {@link GetOOPdf }
     * 
     */
    public GetOOPdf createGetOOPdf() {
        return new GetOOPdf();
    }

    /**
     * Create an instance of {@link GetPdfMultipleNewWithBarcodes }
     * 
     */
    public GetPdfMultipleNewWithBarcodes createGetPdfMultipleNewWithBarcodes() {
        return new GetPdfMultipleNewWithBarcodes();
    }

    /**
     * Create an instance of {@link CheckinResponse }
     * 
     */
    public CheckinResponse createCheckinResponse() {
        return new CheckinResponse();
    }

    /**
     * Create an instance of {@link TransferDctmObjectsToTestResponse }
     * 
     */
    public TransferDctmObjectsToTestResponse createTransferDctmObjectsToTestResponse() {
        return new TransferDctmObjectsToTestResponse();
    }

    /**
     * Create an instance of {@link AddVersionLabelResponse }
     * 
     */
    public AddVersionLabelResponse createAddVersionLabelResponse() {
        return new AddVersionLabelResponse();
    }

    /**
     * Create an instance of {@link CheckinMinorAndSetVersion }
     * 
     */
    public CheckinMinorAndSetVersion createCheckinMinorAndSetVersion() {
        return new CheckinMinorAndSetVersion();
    }

    /**
     * Create an instance of {@link GetPdfMultipleNew }
     * 
     */
    public GetPdfMultipleNew createGetPdfMultipleNew() {
        return new GetPdfMultipleNew();
    }

    /**
     * Create an instance of {@link GetTemplateFieldsResponse }
     * 
     */
    public GetTemplateFieldsResponse createGetTemplateFieldsResponse() {
        return new GetTemplateFieldsResponse();
    }

    /**
     * Create an instance of {@link GetContents }
     * 
     */
    public GetContents createGetContents() {
        return new GetContents();
    }

    /**
     * Create an instance of {@link GetPdfFromOOTemplateResponse }
     * 
     */
    public GetPdfFromOOTemplateResponse createGetPdfFromOOTemplateResponse() {
        return new GetPdfFromOOTemplateResponse();
    }

    /**
     * Create an instance of {@link GetPdfNew }
     * 
     */
    public GetPdfNew createGetPdfNew() {
        return new GetPdfNew();
    }

    /**
     * Create an instance of {@link AddRenditionResponse }
     * 
     */
    public AddRenditionResponse createAddRenditionResponse() {
        return new AddRenditionResponse();
    }

    /**
     * Create an instance of {@link DuplicateDctmObject }
     * 
     */
    public DuplicateDctmObject createDuplicateDctmObject() {
        return new DuplicateDctmObject();
    }

    /**
     * Create an instance of {@link GetOpenOfficePdf }
     * 
     */
    public GetOpenOfficePdf createGetOpenOfficePdf() {
        return new GetOpenOfficePdf();
    }

    /**
     * Create an instance of {@link Query }
     * 
     */
    public Query createQuery() {
        return new Query();
    }

    /**
     * Create an instance of {@link SyncTemplateResponse }
     * 
     */
    public SyncTemplateResponse createSyncTemplateResponse() {
        return new SyncTemplateResponse();
    }

    /**
     * Create an instance of {@link ReplaceVersionLabel }
     * 
     */
    public ReplaceVersionLabel createReplaceVersionLabel() {
        return new ReplaceVersionLabel();
    }

    /**
     * Create an instance of {@link GetPdfFromOOTemplate }
     * 
     */
    public GetPdfFromOOTemplate createGetPdfFromOOTemplate() {
        return new GetPdfFromOOTemplate();
    }

    /**
     * Create an instance of {@link GetAttsResponse }
     * 
     */
    public GetAttsResponse createGetAttsResponse() {
        return new GetAttsResponse();
    }

    /**
     * Create an instance of {@link SetTemplateDataResponse }
     * 
     */
    public SetTemplateDataResponse createSetTemplateDataResponse() {
        return new SetTemplateDataResponse();
    }

    /**
     * Create an instance of {@link AddVersionLabel }
     * 
     */
    public AddVersionLabel createAddVersionLabel() {
        return new AddVersionLabel();
    }

    /**
     * Create an instance of {@link SetAttributeTo }
     * 
     */
    public SetAttributeTo createSetAttributeTo() {
        return new SetAttributeTo();
    }

    /**
     * Create an instance of {@link GetPdfMultipleResponse }
     * 
     */
    public GetPdfMultipleResponse createGetPdfMultipleResponse() {
        return new GetPdfMultipleResponse();
    }

    /**
     * Create an instance of {@link UnLock }
     * 
     */
    public UnLock createUnLock() {
        return new UnLock();
    }

    /**
     * Create an instance of {@link GetTemplateByObjectName }
     * 
     */
    public GetTemplateByObjectName createGetTemplateByObjectName() {
        return new GetTemplateByObjectName();
    }

    /**
     * Create an instance of {@link SetTemplateData }
     * 
     */
    public SetTemplateData createSetTemplateData() {
        return new SetTemplateData();
    }

    /**
     * Create an instance of {@link GetContent }
     * 
     */
    public GetContent createGetContent() {
        return new GetContent();
    }

    /**
     * Create an instance of {@link Revoke }
     * 
     */
    public Revoke createRevoke() {
        return new Revoke();
    }

    /**
     * Create an instance of {@link GetTemplateListResponse }
     * 
     */
    public GetTemplateListResponse createGetTemplateListResponse() {
        return new GetTemplateListResponse();
    }

    /**
     * Create an instance of {@link DuplicateDctmObjectResponse }
     * 
     */
    public DuplicateDctmObjectResponse createDuplicateDctmObjectResponse() {
        return new DuplicateDctmObjectResponse();
    }

    /**
     * Create an instance of {@link GetDctmQueriesResponse }
     * 
     */
    public GetDctmQueriesResponse createGetDctmQueriesResponse() {
        return new GetDctmQueriesResponse();
    }

    /**
     * Create an instance of {@link DctmQuery }
     * 
     */
    public DctmQuery createDctmQuery() {
        return new DctmQuery();
    }

    /**
     * Create an instance of {@link DeleteDctmObjectResponse }
     * 
     */
    public DeleteDctmObjectResponse createDeleteDctmObjectResponse() {
        return new DeleteDctmObjectResponse();
    }

    /**
     * Create an instance of {@link CheckinMinorAndSetVersionResponse }
     * 
     */
    public CheckinMinorAndSetVersionResponse createCheckinMinorAndSetVersionResponse() {
        return new CheckinMinorAndSetVersionResponse();
    }

    /**
     * Create an instance of {@link DeleteDctmObjects }
     * 
     */
    public DeleteDctmObjects createDeleteDctmObjects() {
        return new DeleteDctmObjects();
    }

    /**
     * Create an instance of {@link GetPdfMultipleNewResponse }
     * 
     */
    public GetPdfMultipleNewResponse createGetPdfMultipleNewResponse() {
        return new GetPdfMultipleNewResponse();
    }

    /**
     * Create an instance of {@link CreateDctmObject }
     * 
     */
    public CreateDctmObject createCreateDctmObject() {
        return new CreateDctmObject();
    }

    /**
     * Create an instance of {@link GetFormatsFromDosExtensionResponse }
     * 
     */
    public GetFormatsFromDosExtensionResponse createGetFormatsFromDosExtensionResponse() {
        return new GetFormatsFromDosExtensionResponse();
    }

    /**
     * Create an instance of {@link GrantResponse }
     * 
     */
    public GrantResponse createGrantResponse() {
        return new GrantResponse();
    }

    /**
     * Create an instance of {@link TransferDctmObjectsToTest }
     * 
     */
    public TransferDctmObjectsToTest createTransferDctmObjectsToTest() {
        return new TransferDctmObjectsToTest();
    }

    /**
     * Create an instance of {@link UnLockResponse }
     * 
     */
    public UnLockResponse createUnLockResponse() {
        return new UnLockResponse();
    }

    /**
     * Create an instance of {@link GetTemplateByTemplateId }
     * 
     */
    public GetTemplateByTemplateId createGetTemplateByTemplateId() {
        return new GetTemplateByTemplateId();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link GetDctmAttribute }
     * 
     */
    public GetDctmAttribute createGetDctmAttribute() {
        return new GetDctmAttribute();
    }

    /**
     * Create an instance of {@link GetContentsResponse }
     * 
     */
    public GetContentsResponse createGetContentsResponse() {
        return new GetContentsResponse();
    }

    /**
     * Create an instance of {@link EvaluateSqlResponse }
     * 
     */
    public EvaluateSqlResponse createEvaluateSqlResponse() {
        return new EvaluateSqlResponse();
    }

    /**
     * Create an instance of {@link QueryResponse }
     * 
     */
    public QueryResponse createQueryResponse() {
        return new QueryResponse();
    }

    /**
     * Create an instance of {@link ReplaceVersionLabelResponse }
     * 
     */
    public ReplaceVersionLabelResponse createReplaceVersionLabelResponse() {
        return new ReplaceVersionLabelResponse();
    }

    /**
     * Create an instance of {@link IsUserMemberResponse }
     * 
     */
    public IsUserMemberResponse createIsUserMemberResponse() {
        return new IsUserMemberResponse();
    }

    /**
     * Create an instance of {@link CheckinTemplateByTemplateId }
     * 
     */
    public CheckinTemplateByTemplateId createCheckinTemplateByTemplateId() {
        return new CheckinTemplateByTemplateId();
    }

    /**
     * Create an instance of {@link GetPdfMultipleNewWithBarcodesResponse }
     * 
     */
    public GetPdfMultipleNewWithBarcodesResponse createGetPdfMultipleNewWithBarcodesResponse() {
        return new GetPdfMultipleNewWithBarcodesResponse();
    }

    /**
     * Create an instance of {@link Grant }
     * 
     */
    public Grant createGrant() {
        return new Grant();
    }

    /**
     * Create an instance of {@link MyHashMap }
     * 
     */
    public MyHashMap createMyHashMap() {
        return new MyHashMap();
    }

    /**
     * Create an instance of {@link MyHashMapArray }
     * 
     */
    public MyHashMapArray createMyHashMapArray() {
        return new MyHashMapArray();
    }

    /**
     * Create an instance of {@link Base64Binary }
     * 
     */
    public Base64Binary createBase64Binary() {
        return new Base64Binary();
    }

    /**
     * Create an instance of {@link HexBinary }
     * 
     */
    public HexBinary createHexBinary() {
        return new HexBinary();
    }

    /**
     * Create an instance of {@link HashMapWrapper.Parameters.Entry }
     * 
     */
    public HashMapWrapper.Parameters.Entry createHashMapWrapperParametersEntry() {
        return new HashMapWrapper.Parameters.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com", name = "domain")
    public JAXBElement<String> createDomain(String value) {
        return new JAXBElement<String>(_Domain_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.com", name = "barcodes")
    public JAXBElement<String> createBarcodes(String value) {
        return new JAXBElement<String>(_Barcodes_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.com", name = "inetids")
    public JAXBElement<String> createInetids(String value) {
        return new JAXBElement<String>(_Inetids_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com", name = "password")
    public JAXBElement<String> createPassword(String value) {
        return new JAXBElement<String>(_Password_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com", name = "osName")
    public JAXBElement<String> createOsName(String value) {
        return new JAXBElement<String>(_OsName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "content", scope = CreateDctmObject.class)
    public JAXBElement<byte[]> createCreateDctmObjectContent(byte[] value) {
        return new JAXBElement<byte[]>(_CreateDctmObjectContent_QNAME, byte[].class, CreateDctmObject.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pdfBase64Encoded", scope = GetTemplateByObjectNameResponse.class)
    public JAXBElement<byte[]> createGetTemplateByObjectNameResponsePdfBase64Encoded(byte[] value) {
        return new JAXBElement<byte[]>(_GetTemplateByObjectNameResponsePdfBase64Encoded_QNAME, byte[].class, GetTemplateByObjectNameResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "return", scope = GetOOPdfResponse.class)
    public JAXBElement<byte[]> createGetOOPdfResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOOPdfResponseReturn_QNAME, byte[].class, GetOOPdfResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "data", scope = AddRendition.class)
    public JAXBElement<byte[]> createAddRenditionData(byte[] value) {
        return new JAXBElement<byte[]>(_AddRenditionData_QNAME, byte[].class, AddRendition.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "data", scope = Checkin.class)
    public JAXBElement<byte[]> createCheckinData(byte[] value) {
        return new JAXBElement<byte[]>(_AddRenditionData_QNAME, byte[].class, Checkin.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "result", scope = GetContentResponse.class)
    public JAXBElement<byte[]> createGetContentResponseResult(byte[] value) {
        return new JAXBElement<byte[]>(_GetContentResponseResult_QNAME, byte[].class, GetContentResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetOpenOfficePdfForHashMapResponse.class)
    public JAXBElement<byte[]> createGetOpenOfficePdfForHashMapResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOpenOfficePdfForHashMapResponseReturn_QNAME, byte[].class, GetOpenOfficePdfForHashMapResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = ConcatTwoPdfsResponse.class)
    public JAXBElement<byte[]> createConcatTwoPdfsResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOpenOfficePdfForHashMapResponseReturn_QNAME, byte[].class, ConcatTwoPdfsResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "data", scope = CheckinMinorAndSetVersion.class)
    public JAXBElement<byte[]> createCheckinMinorAndSetVersionData(byte[] value) {
        return new JAXBElement<byte[]>(_AddRenditionData_QNAME, byte[].class, CheckinMinorAndSetVersion.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetPdfNewResponse.class)
    public JAXBElement<byte[]> createGetPdfNewResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOpenOfficePdfForHashMapResponseReturn_QNAME, byte[].class, GetPdfNewResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "data", scope = CheckinMinor.class)
    public JAXBElement<byte[]> createCheckinMinorData(byte[] value) {
        return new JAXBElement<byte[]>(_AddRenditionData_QNAME, byte[].class, CheckinMinor.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = GetPdfFromOOTemplate.class)
    public JAXBElement<byte[]> createGetPdfFromOOTemplateArg0(byte[] value) {
        return new JAXBElement<byte[]>(_GetPdfFromOOTemplateArg0_QNAME, byte[].class, GetPdfFromOOTemplate.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "data", scope = CheckinTemplateByTemplateId.class)
    public JAXBElement<byte[]> createCheckinTemplateByTemplateIdData(byte[] value) {
        return new JAXBElement<byte[]>(_AddRenditionData_QNAME, byte[].class, CheckinTemplateByTemplateId.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg1", scope = ConcatTwoPdfs.class)
    public JAXBElement<byte[]> createConcatTwoPdfsArg1(byte[] value) {
        return new JAXBElement<byte[]>(_ConcatTwoPdfsArg1_QNAME, byte[].class, ConcatTwoPdfs.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "arg0", scope = ConcatTwoPdfs.class)
    public JAXBElement<byte[]> createConcatTwoPdfsArg0(byte[] value) {
        return new JAXBElement<byte[]>(_GetPdfFromOOTemplateArg0_QNAME, byte[].class, ConcatTwoPdfs.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetPdfNewFromKeyValuesResponse.class)
    public JAXBElement<byte[]> createGetPdfNewFromKeyValuesResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOpenOfficePdfForHashMapResponseReturn_QNAME, byte[].class, GetPdfNewFromKeyValuesResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "content", scope = SetContent.class)
    public JAXBElement<byte[]> createSetContentContent(byte[] value) {
        return new JAXBElement<byte[]>(_CreateDctmObjectContent_QNAME, byte[].class, SetContent.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "data", scope = CheckinTemplate.class)
    public JAXBElement<byte[]> createCheckinTemplateData(byte[] value) {
        return new JAXBElement<byte[]>(_AddRenditionData_QNAME, byte[].class, CheckinTemplate.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://templates.mobitel.com/", name = "result", scope = GetOpenOfficePdfResponse.class)
    public JAXBElement<byte[]> createGetOpenOfficePdfResponseResult(byte[] value) {
        return new JAXBElement<byte[]>(_GetContentResponseResult_QNAME, byte[].class, GetOpenOfficePdfResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetPdfResponse.class)
    public JAXBElement<byte[]> createGetPdfResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOpenOfficePdfForHashMapResponseReturn_QNAME, byte[].class, GetPdfResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = GetPdfFromOOTemplateResponse.class)
    public JAXBElement<byte[]> createGetPdfFromOOTemplateResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_GetOpenOfficePdfForHashMapResponseReturn_QNAME, byte[].class, GetPdfFromOOTemplateResponse.class, ((byte[]) value));
    }

}
