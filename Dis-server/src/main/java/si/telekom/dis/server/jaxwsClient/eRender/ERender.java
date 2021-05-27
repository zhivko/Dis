
package si.telekom.dis.server.jaxwsClient.eRender;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.1
 * 
 */
@WebService(name = "ERender", targetNamespace = "http://erender.telekom.si/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ERender {


    /**
     * 
     * @param sCompanyPar
     * @param iTypePar
     * @param nQuantityPar
     * @param sourceSystem
     * @param sCapturePar
     * @param sLocationPar
     * @param dateTimePar
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBarcode", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetBarcode")
    @ResponseWrapper(localName = "getBarcodeResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetBarcodeResponse")
    public List<String> getBarcode(
        @WebParam(name = "iTypePar", targetNamespace = "http://erender.telekom.si/")
        int iTypePar,
        @WebParam(name = "sCapturePar", targetNamespace = "http://erender.telekom.si/")
        String sCapturePar,
        @WebParam(name = "sCompanyPar", targetNamespace = "http://erender.telekom.si/")
        String sCompanyPar,
        @WebParam(name = "sLocationPar", targetNamespace = "http://erender.telekom.si/")
        String sLocationPar,
        @WebParam(name = "dateTimePar", targetNamespace = "http://erender.telekom.si/")
        XMLGregorianCalendar dateTimePar,
        @WebParam(name = "nQuantityPar", targetNamespace = "http://erender.telekom.si/")
        int nQuantityPar,
        @WebParam(name = "sourceSystem", targetNamespace = "http://erender.telekom.si/")
        String sourceSystem);

    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(name = "pdfContent", targetNamespace = "http://erender.telekom.si/")
    @RequestWrapper(localName = "mergeContentOfItems", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.MergeContentOfItems")
    @ResponseWrapper(localName = "mergeContentOfItemsResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.MergeContentOfItemsResponse")
    public byte[] mergeContentOfItems(
        @WebParam(name = "arg0", targetNamespace = "")
        List<MergeItem> arg0);

    /**
     * 
     * @param templateId
     * @return
     *     returns java.util.List<si.telekom.dis.server.jaxwsClient.eRender.KeyValue>
     */
    @WebMethod
    @WebResult(name = "templateField", targetNamespace = "")
    @RequestWrapper(localName = "getTemplateFields", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetTemplateFields")
    @ResponseWrapper(localName = "getTemplateFieldsResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetTemplateFieldsResponse")
    public List<KeyValue> getTemplateFields(
        @WebParam(name = "templateId", targetNamespace = "http://erender.telekom.si/")
        int templateId);

    /**
     * 
     * @param pdfContent
     * @param watermark
     * @param fontSize
     * @param opacity
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(name = "pdfWithOverlay", targetNamespace = "http://erender.telekom.si/")
    @RequestWrapper(localName = "overlayPdf", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.OverlayPdf")
    @ResponseWrapper(localName = "overlayPdfResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.OverlayPdfResponse")
    public byte[] overlayPdf(
        @WebParam(name = "pdfContent", targetNamespace = "http://erender.telekom.si/")
        byte[] pdfContent,
        @WebParam(name = "watermark", targetNamespace = "http://erender.telekom.si/")
        String watermark,
        @WebParam(name = "fontSize", targetNamespace = "http://erender.telekom.si/")
        int fontSize,
        @WebParam(name = "opacity", targetNamespace = "http://erender.telekom.si/")
        float opacity);

    /**
     * 
     * @param winwordContent
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(name = "pdfContent", targetNamespace = "")
    @RequestWrapper(localName = "winwordToPdf", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.WinwordToPdf")
    @ResponseWrapper(localName = "winwordToPdfResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.WinwordToPdfResponse")
    public byte[] winwordToPdf(
        @WebParam(name = "winwordContent", targetNamespace = "http://erender.telekom.si/")
        byte[] winwordContent);

    /**
     * 
     * @param pdfContent
     * @param barcodes
     */
    @WebMethod
    @RequestWrapper(localName = "mergeContent", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.MergeContent")
    @ResponseWrapper(localName = "mergeContentResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.MergeContentResponse")
    public void mergeContent(
        @WebParam(name = "barcodes", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.INOUT)
        Holder<List<String>> barcodes,
        @WebParam(name = "pdfContent", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.OUT)
        Holder<byte[]> pdfContent);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "jobId", targetNamespace = "http://erender.telekom.si/")
    @RequestWrapper(localName = "getJobId", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetJobId")
    @ResponseWrapper(localName = "getJobIdResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetJobIdResponse")
    public String getJobId();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "jobId", targetNamespace = "http://erender.telekom.si/")
    @RequestWrapper(localName = "getTemplateExtension", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetTemplateExtension")
    @ResponseWrapper(localName = "getTemplateExtensionResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetTemplateExtensionResponse")
    public String getTemplateExtension(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<si.telekom.dis.server.jaxwsClient.eRender.Template>
     */
    @WebMethod
    @WebResult(name = "template", targetNamespace = "")
    @RequestWrapper(localName = "getTemplates", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetTemplates")
    @ResponseWrapper(localName = "getTemplatesResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetTemplatesResponse")
    public List<Template> getTemplates();

    /**
     * 
     * @param hashMapKeyValue
     * @param roles
     * @param document
     * @param language
     * @param mimeType
     * @param templateId
     * @param barcode
     */
    @WebMethod
    @RequestWrapper(localName = "getContentForLanguage", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetContentForLanguage")
    @ResponseWrapper(localName = "getContentForLanguageResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetContentForLanguageResponse")
    public void getContentForLanguage(
        @WebParam(name = "templateId", targetNamespace = "http://erender.telekom.si/")
        int templateId,
        @WebParam(name = "language", targetNamespace = "http://erender.telekom.si/")
        String language,
        @WebParam(name = "hashMapKeyValue", targetNamespace = "http://erender.telekom.si/")
        HashMapWrapper hashMapKeyValue,
        @WebParam(name = "mimeType", targetNamespace = "http://erender.telekom.si/")
        String mimeType,
        @WebParam(name = "barcode", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.INOUT)
        Holder<String> barcode,
        @WebParam(name = "roles", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.INOUT)
        Holder<List<String>> roles,
        @WebParam(name = "document", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.OUT)
        Holder<byte[]> document);

    /**
     * 
     * @param hashMapKeyValue
     * @param roles
     * @param document
     * @param mimeType
     * @param templateId
     * @param barcode
     */
    @WebMethod
    @RequestWrapper(localName = "getContent", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetContent")
    @ResponseWrapper(localName = "getContentResponse", targetNamespace = "http://erender.telekom.si/", className = "si.telekom.dis.server.jaxwsClient.eRender.GetContentResponse")
    public void getContent(
        @WebParam(name = "templateId", targetNamespace = "http://erender.telekom.si/")
        int templateId,
        @WebParam(name = "hashMapKeyValue", targetNamespace = "http://erender.telekom.si/")
        HashMapWrapper hashMapKeyValue,
        @WebParam(name = "mimeType", targetNamespace = "http://erender.telekom.si/")
        String mimeType,
        @WebParam(name = "barcode", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.INOUT)
        Holder<String> barcode,
        @WebParam(name = "roles", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.INOUT)
        Holder<List<String>> roles,
        @WebParam(name = "document", targetNamespace = "http://erender.telekom.si/", mode = WebParam.Mode.OUT)
        Holder<byte[]> document);

}