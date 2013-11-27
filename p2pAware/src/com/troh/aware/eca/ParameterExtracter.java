/**
 * 
 */
package com.troh.aware.eca;

import org.w3c.dom.Document;

/**
 * @author tom
 *
 */
public interface ParameterExtracter {
	Object[] extractParameters(Document actionDocument, Object[] additionalData) throws MalformedActionStringException;
	Class<?>[] extractParameterTypes(Object[] parameters) throws MalformedActionStringException;
}
