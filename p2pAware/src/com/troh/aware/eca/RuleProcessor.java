/**
 * 
 */
package com.troh.aware.eca;

import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.troh.aware.util.MissingVariableBindingException;

/**
 * @author tom
 *
 */
public interface RuleProcessor {
	void addRule(String eventType, String action);
	boolean removeRule(String eventType, String action);
	List<String> matchEventToActions(Model event) throws MalformedEventNotificationException, MissingVariableBindingException;
}
