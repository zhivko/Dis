
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

    private final static QName _OverlayPdfPdfContent_QNAME = new QName("http://erender.telekom.si/", "pdfContent");
    private final static QName _GetContentForLanguageResponseDocument_QNAME = new QName("http://erender.telekom.si/", "document");
    private final static QName _WinwordToPdfWinwordContent_QNAME = new QName("http://erender.telekom.si/", "winwordContent");
    private final static QName _WinwordToPdfResponsePdfContent_QNAME = new QName("", "pdfContent");
    private final static QName _OverlayPdfResponsePdfWithOverlay_QNAME = new QName("http://erender.telekom.si/", "pdfWithOverlay");

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
     * Create an instance of {@link GetTemplateExtensionResponse }
     * 
     */
    public GetTemplateExtensionResponse createGetTemplateExtensionResponse() {
        return new GetTemplateExtensionResponse();
    }

    /**
     * Create an instance of {@link MergeContent }
     * 
     */
    public MergeContent createMergeContent() {
        return new MergeContent();
    }

    /**
     * Create an instance of {@link MergeContentOfItemsResponse }
     * 
     */
    public MergeContentOfItemsResponse createMergeContentOfItemsResponse() {
        return new MergeContentOfItemsResponse();
    }

    /**
     * Create an instance of {@link KeyValue }
     * 
     */
    public KeyValue createKeyValue() {
        return new KeyValue();
    }

    /**
     * Create an instance of {@link GetContent }
     * 
     */
    public GetContent createGetContent() {
        return new GetContent();
    }

    /**
     * Create an instance of {@link WinwordToPdfResponse }
     * 
     */
    public WinwordToPdfResponse createWinwordToPdfResponse() {
        return new WinwordToPdfResponse();
    }

    /**
     * Create an instance of {@link GetTemplatesResponse }
     * 
     */
    public GetTemplatesResponse createGetTemplatesResponse() {
        return new GetTemplatesResponse();
    }

    /**
     * Create an instance of {@link Template }
     * 
     */
    public Template createTemplate() {
        return new Template();
    }

    /**
     * Create an instance of {@link GetBarcodeResponse }
     * 
     */
    public GetBarcodeResponse createGetBarcodeResponse() {
        return new GetBarcodeResponse();
    }

    /**
     * Create an instance of {@link GetTemplates }
     * 
     */
    public GetTemplates createGetTemplates() {
        return new GetTemplates();
    }

    /**
     * Create an instance of {@link WinwordToPdf }
     * 
     */
    public WinwordToPdf createWinwordToPdf() {
        return new WinwordToPdf();
    }

    /**
     * Create an instance of {@link GetJobIdResponse }
     * 
     */
    public GetJobIdResponse createGetJobIdResponse() {
        return new GetJobIdResponse();
    }

    /**
     * Create an instance of {@link GetTemplateFieldsResponse }
     * 
     */
    public GetTemplateFieldsResponse createGetTemplateFieldsResponse() {
        return new GetTemplateFieldsResponse();
    }

    /**
     * Create an instance of {@link GetContentForLanguageResponse }
     * 
     */
    public GetContentForLanguageResponse createGetContentForLanguageResponse() {
        return new GetContentForLanguageResponse();
    }

    /**
     * Create an instance of {@link MergeContentOfItems }
     * 
     */
    public MergeContentOfItems createMergeContentOfItems() {
        return new MergeContentOfItems();
    }

    /**
     * Create an instance of {@link MergeItem }
     * 
     */
    public MergeItem createMergeItem() {
        return new MergeItem();
    }

    /**
     * Create an instance of {@link OverlayPdf }
     * 
     */
    public OverlayPdf createOverlayPdf() {
        return new OverlayPdf();
    }

    /**
     * Create an instance of {@link OverlayPdfResponse }
     * 
     */
    public OverlayPdfResponse createOverlayPdfResponse() {
        return new OverlayPdfResponse();
    }

    /**
     * Create an instance of {@link GetBarcode }
     * 
     */
    public GetBarcode createGetBarcode() {
        return new GetBarcode();
    }

    /**
     * Create an instance of {@link GetTemplateExtension }
     * 
     */
    public GetTemplateExtension createGetTemplateExtension() {
        return new GetTemplateExtension();
    }

    /**
     * Create an instance of {@link GetContentForLanguage }
     * 
     */
    public GetContentForLanguage createGetContentForLanguage() {
        return new GetContentForLanguage();
    }

    /**
     * Create an instance of {@link GetJobId }
     * 
     */
    public GetJobId createGetJobId() {
        return new GetJobId();
    }

    /**
     * Create an instance of {@link GetTemplateFields }
     * 
     */
    public GetTemplateFields createGetTemplateFields() {
        return new GetTemplateFields();
    }

    /**
     * Create an instance of {@link GetContentResponse }
     * 
     */
    public GetContentResponse createGetContentResponse() {
        return new GetContentResponse();
    }

    /**
     * Create an instance of {@link MergeContentResponse }
     * 
     */
    public MergeContentResponse createMergeContentResponse() {
        return new MergeContentResponse();
    }

    /**
     * Create an instance of {@link HashMapWrapper.Parameters.Entry }
     * 
     */
    public HashMapWrapper.Parameters.Entry createHashMapWrapperParametersEntry() {
        return new HashMapWrapper.Parameters.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "pdfContent", scope = OverlayPdf.class)
    public JAXBElement<byte[]> createOverlayPdfPdfContent(byte[] value) {
        return new JAXBElement<byte[]>(_OverlayPdfPdfContent_QNAME, byte[].class, OverlayPdf.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "document", scope = GetContentForLanguageResponse.class)
    public JAXBElement<byte[]> createGetContentForLanguageResponseDocument(byte[] value) {
        return new JAXBElement<byte[]>(_GetContentForLanguageResponseDocument_QNAME, byte[].class, GetContentForLanguageResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "winwordContent", scope = WinwordToPdf.class)
    public JAXBElement<byte[]> createWinwordToPdfWinwordContent(byte[] value) {
        return new JAXBElement<byte[]>(_WinwordToPdfWinwordContent_QNAME, byte[].class, WinwordToPdf.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "document", scope = GetContentResponse.class)
    public JAXBElement<byte[]> createGetContentResponseDocument(byte[] value) {
        return new JAXBElement<byte[]>(_GetContentForLanguageResponseDocument_QNAME, byte[].class, GetContentResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "pdfContent", scope = MergeContentOfItemsResponse.class)
    public JAXBElement<byte[]> createMergeContentOfItemsResponsePdfContent(byte[] value) {
        return new JAXBElement<byte[]>(_OverlayPdfPdfContent_QNAME, byte[].class, MergeContentOfItemsResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "pdfContent", scope = MergeContentResponse.class)
    public JAXBElement<byte[]> createMergeContentResponsePdfContent(byte[] value) {
        return new JAXBElement<byte[]>(_OverlayPdfPdfContent_QNAME, byte[].class, MergeContentResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "pdfContent", scope = WinwordToPdfResponse.class)
    public JAXBElement<byte[]> createWinwordToPdfResponsePdfContent(byte[] value) {
        return new JAXBElement<byte[]>(_WinwordToPdfResponsePdfContent_QNAME, byte[].class, WinwordToPdfResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://erender.telekom.si/", name = "pdfWithOverlay", scope = OverlayPdfResponse.class)
    public JAXBElement<byte[]> createOverlayPdfResponsePdfWithOverlay(byte[] value) {
        return new JAXBElement<byte[]>(_OverlayPdfResponsePdfWithOverlay_QNAME, byte[].class, OverlayPdfResponse.class, ((byte[]) value));
    }

}
