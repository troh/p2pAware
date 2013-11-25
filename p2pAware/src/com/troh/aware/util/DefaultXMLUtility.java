/**
 * 
 */
package com.troh.aware.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * @author tom
 *
 */
public class DefaultXMLUtility implements XMLUtility {
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder builder;
	private TransformerFactory transformerFactory = TransformerFactory.newInstance();
	private Transformer transformer;
	private static final int FIRST_ELEMENT = 0;
	
	public DefaultXMLUtility() throws ParserConfigurationException, TransformerConfigurationException {
		builder = factory.newDocumentBuilder();
		transformer = transformerFactory.newTransformer();
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.XMLUtility#transformToString(org.w3c.dom.Document)
	 */
	@Override
	public String transformToString(Document document) throws TransformerException {
		try {
			StringWriter stringWriter = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
			return stringWriter.toString();
		} finally {
			transformer.reset();
		}
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.XMLUtility#buildDocument(java.lang.String)
	 */
	@Override
	public Document buildDocument(String string) throws SAXException, IOException {
		try {
		    InputStream in = new ByteArrayInputStream(string.getBytes());
		    Document document = builder.parse(in);
		    return document;
		} finally {
		    builder.reset();
		}
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.XMLUtility#extractSingleTextElement(org.w3c.dom.Document, java.lang.String)
	 */
	@Override
	public String extractSingleTextElement(Document document, String tag) {
		Node node = document.getElementsByTagName(tag).item(FIRST_ELEMENT);
		if (node == null) {
			return null;
		} else {
			return node.getTextContent();
		}
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.XMLUtility#createElement(java.lang.String, java.lang.String, org.w3c.dom.Document)
	 */
	@Override
	public void createElement(String tag, String contents, Document document) {
		Node firstChild = document.getFirstChild();
		Node toAdd = document.createElement(tag).appendChild(document.createTextNode(contents));
		firstChild.appendChild(toAdd);
	}

}
