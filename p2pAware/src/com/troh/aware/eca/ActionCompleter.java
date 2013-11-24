/**
 * 
 */
package com.troh.aware.eca;

import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author tom
 *
 */
public interface ActionCompleter {
	boolean removeComponent(String name);
	boolean addComponent(ActionCompleterComponent component);
	void completeAction(IncompleteMessage message);
}
