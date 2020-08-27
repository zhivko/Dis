package si.telekom.dis.server;

/*
 *  Copyright 2007-2008, Plutext Pty Ltd.
 *   
 *  This file is part of docx4j.

    docx4j is licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 

    You may obtain a copy of the License at 

        http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

 */

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 * This sample demonstrates populating content controls from a custom xml part
 * (based on the xpaths given in the content controls)
 * 
 * In this example, the XML part is injected at runtime, and OpenDoPE extensions
 * are supported (if present).
 * 
 * So this example is like See
 * https://github.com/plutext/OpenDoPE-WAR/blob/master/webapp-simple/src/main/java/org/opendope/webapp/SubmitBoth.java
 */
public class ContentControlsMergeXML {

	public static JAXBContext context = org.docx4j.jaxb.Context.jc;

	private final static boolean DEBUG = true;
	private final static boolean SAVE = true;

	public static void main(String[] args) throws Exception {

		// the docx 'template'
		String input_DOCX = "/home/klemen/workspace/ErenderWebUi/src/main/resources/Pogodba - podatkovne storitve - 20171130-ODKLENJENA.docx";

		// the instance data
		String input_XML = "/home/klemen/workspace/ErenderWebUi/src/main/resources/item3.xml";

		// resulting docx
		String OUTPUT_DOCX = "/home/klemen/workspace/ErenderWebUi/src/main/resources/OUT_ContentControlsMergeXML.docx";

		// Load input_template.docx
		WordprocessingMLPackage wordMLPackage = Docx4J.load(new File(input_DOCX));

		// Open the xml stream
		FileInputStream xmlStream = new FileInputStream(new File(input_XML));

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
		Docx4J.bind(wordMLPackage, xmlStream, Docx4J.FLAG_NONE);

		// Save the document
		Docx4J.save(wordMLPackage, new File(OUTPUT_DOCX), Docx4J.FLAG_NONE);
		System.out.println("Saved: " + OUTPUT_DOCX);

	}
}