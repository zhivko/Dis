package si.telekom.dis.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocumentViewFileTypes {
	static String[] couldDisplayFormatsArr = { "acroformpdf", "pdf", "odt", "ods", "msg", "html", "htm", "pub_html", "crtext", "xml" };
	static String[] viewerJSFormatsArr = { "acroformpdf", "pdf", "ods", "odt" };
	static String[] imageHtmlFormatsArr = { "png", "tiff", "jpeg", "jpg" };
	static String[] odfFormatsArr = { "odt", "ods"};
	static String[] htmlFormatsArr = { "html", "htm" };
	static String[] downloadFormatsArr = { "msw12", "excel12book", "excel8book", "msw8", "msw" , "zip"};

	public static List<String> couldDisplayFormats;
	public static List<String> viewerJSFormats;
	public static List<String> imageHtmlFormats;
	public static List<String> odfFormats;
	public static List<String> htmlFormats;
	public static List<String> downloadFormats;
	public static List<String> couldRenderAsPdf;
	
	static
	{
		couldDisplayFormats = new ArrayList<String>(Arrays.asList(couldDisplayFormatsArr));		
		couldDisplayFormats.addAll(Arrays.asList(imageHtmlFormatsArr));
		
		viewerJSFormats = Arrays.asList(viewerJSFormatsArr);
		odfFormats = Arrays.asList(odfFormatsArr);
		downloadFormats = Arrays.asList(downloadFormatsArr);		
		imageHtmlFormats = Arrays.asList(imageHtmlFormatsArr);	
		htmlFormats = Arrays.asList(htmlFormatsArr);	
		
		couldDisplayFormats.addAll(viewerJSFormats);
		couldDisplayFormats.addAll(imageHtmlFormats);
		couldDisplayFormats.addAll(htmlFormats);
		
	}
	
}
