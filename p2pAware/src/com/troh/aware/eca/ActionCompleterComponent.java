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
public interface ActionCompleterComponent {
	String getName();
	boolean completeAction(Model event, Object[] data, Document incompleteAction);
}
