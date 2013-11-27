/**
 * 
 */
package com.troh.aware.eca;

import java.io.IOException;

import org.xml.sax.SAXException;

/**
 * @author tom
 *
 */
public interface ActionExecutor {
	void execute(String action) throws MalformedActionStringException;
	void execute(String action, Object[] data) throws MalformedActionStringException;
	void addExecutionObject(String identifier, Object executor);
}
