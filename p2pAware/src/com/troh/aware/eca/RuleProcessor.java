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
public interface RuleProcessor {
	void addRule(String eventType, String action);
	boolean removeRule(String eventType, String action);
	List<String> matchEventToActions(Model event);
}
