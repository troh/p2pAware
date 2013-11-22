/**
 * 
 */
package com.troh.aware.eca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author tom
 *
 */
public abstract class AbstractRuleProcessor implements RuleProcessor {
	
	private final Map<String,Set<String>> ruleSet = new HashMap<String,Set<String>>();

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.RuleProcessor#addRule(java.lang.String, java.lang.String)
	 */
	@Override
	public void addRule(String eventType, String action) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.RuleProcessor#removeRule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean removeRule(String eventType, String action) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.RuleProcessor#matchEventToActions(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public List<String> matchEventToActions(Model event) {
		// TODO Auto-generated method stub
		return null;
	}

}
