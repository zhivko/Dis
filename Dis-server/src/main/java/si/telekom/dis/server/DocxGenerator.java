package si.telekom.dis.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class DocxGenerator {

	public static void main(String[] args) {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("IPv4", "1");
		hm.put("IPv6", "1");

		try {
			FileInputStream fis = new FileInputStream(new File("/home/klemen/Downloads/Priloga.docx"));
			byte[] bytes = DocxGenerator.generateDocxFileFromTemplate(hm, fis);
			FileOutputStream fos = new FileOutputStream("/home/klemen/Downloads/Priloga-published.docx");
			fos.write(bytes);
			Process p = Runtime.getRuntime().exec("libreoffice6.3 /home/klemen/Downloads/Priloga-published.docx");
			p.waitFor();			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static byte[] generateDocxFileFromTemplate(HashMap<String, String> variables, InputStream templateInputStream) throws Exception {

		// the docx 'template'
		//String input_DOCX = "/home/klemen/workspace/ErenderWebUi/src/main/resources/Pogodba - podatkovne storitve - 20171130-ODKLENJENA.docx";

		// the instance data
		//String input_XML = "/home/klemen/workspace/ErenderWebUi/src/main/resources/item3.xml";

		// resulting docx
		//String OUTPUT_DOCX = "/home/klemen/workspace/ErenderWebUi/src/main/resources/OUT_ContentControlsMergeXML.docx";

		// Load input_template.docx
		//WordprocessingMLPackage wordMLPackage = Docx4J.load(new File(input_DOCX));
		WordprocessingMLPackage wordMLPackage = Docx4J.load(templateInputStream);

		// Open the xml stream
		StringBuilder sbXml = new StringBuilder();
		
		sbXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sbXml.append("<myxml>\n");
		for (String variable : variables.keySet()) {
			String value = variables.get(variable);
			sbXml.append("<"+variable+">");
			sbXml.append(value);
			sbXml.append("/<"+variable+">\n");
		}
		sbXml.append("</myxml>\n");
		
		//ByteArrayInputStream xmlStream = new ByteArrayInputStream(sb.toString().getBytes());
		//FileInputStream xmlStream = new FileInputStream(new File(input_XML));

		// Do the binding:
		// FLAG_NONE means that all the steps of the binding will be done,
		// otherwise you could pass a combination of the following flags:
		// FLAG_BIND_INSERT_XML: inject the passed XML into the document
		// FLAG_BIND_BIND_XML: bind the document and the xml (including any OpenDope
		// handling)
		// FLAG_BIND_REMOVE_SDT: remove the content controls from the document (only
		// the content remains)
		// FLAG_BIND_REMOVE_XML: remove the custom xml parts from the document

		// Docx4J.bind(wordMLPackage, xmlStream, Docx4J.FLAG_NONE);
		// If a document doesn't include the Opendope definitions, eg. the
		// XPathPart,
		// then the only thing you can do is insert the xml
		// the example document binding-simple.docx doesn't have an XPathPart....
		Docx4J.bind(wordMLPackage, sbXml.toString(), Docx4J.FLAG_NONE);

		// Save the document
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		wordMLPackage.save(outputStream);

		return outputStream.toByteArray();
	}
}
