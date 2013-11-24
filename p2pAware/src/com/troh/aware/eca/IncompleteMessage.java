/**
 * 
 */
package com.troh.aware.eca;

import org.w3c.dom.Document;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author tom
 *
 */
public interface IncompleteMessage {
	Document getActionConditions();
	Model getEventNotification();
	Object[] getData();
}
