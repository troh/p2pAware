/**
 * 
 */
package com.troh.aware.util;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author tom
 *
 */
public interface XMLUtility {
	String transformToString(Document document) throws TransformerException;
	Document buildDocument(String string) throws SAXException, IOException;
	String extractSingleTextElement(Document document, String tag);
	void createElement(String tag, String contents, Document document);
}
