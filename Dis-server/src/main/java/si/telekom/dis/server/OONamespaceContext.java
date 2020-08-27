package si.telekom.dis.server;

import java.util.Iterator;
import javax.xml.*;
import javax.xml.namespace.NamespaceContext;

public class OONamespaceContext implements NamespaceContext {

	public String getNamespaceURI(String prefix) {
		if (prefix == null)
			throw new NullPointerException("Null prefix");
		else if ("pre".equals(prefix))
			return "http://www.example.com/books";
		else if ("office".equals(prefix))
			return "urn:oasis:names:tc:opendocument:xmlns:office:1.0";
		else if ("text".equals(prefix))
			return "urn:oasis:names:tc:opendocument:xmlns:text:1.0";
		else if ("style".equals(prefix))
			return "urn:oasis:names:tc:opendocument:xmlns:style:1.0";
		else if ("draw".equals(prefix))
			return "urn:oasis:names:tc:opendocument:xmlns:drawing:1.0";
		else if ("table".equals(prefix))
			return "urn:oasis:names:tc:opendocument:xmlns:table:1.0";
		else if ("fo".equals(prefix))
			return "urn:oasis:names:tc:opendocument:xmlns:xsl-fo-compatible:1.0";
		else if ("xml".equals(prefix))
			return XMLConstants.XML_NS_URI;
		return XMLConstants.DEFAULT_NS_PREFIX;
	}

	// This method isn't necessary for XPath processing.
	public String getPrefix(String uri) {
		throw new UnsupportedOperationException();
	}

	// This method isn't necessary for XPath processing either.
	public Iterator getPrefixes(String uri) {
		throw new UnsupportedOperationException();
	}

}