/**
 * 
 */
package com.troh.aware.eca;

import java.util.HashMap;
import java.util.HashSet;
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
		synchronized (ruleSet) {
			if (ruleSet.containsKey(eventType)) {
				ruleSet.get(eventType).add(action);
			} else {
				Set<String> newEventSet = new HashSet<String>();
				newEventSet.add(action);
				ruleSet.put(eventType, newEventSet);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.RuleProcessor#removeRule(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean removeRule(String eventType, String action) {
		boolean success;
		synchronized (ruleSet) {
			Set<String> actionConditions = ruleSet.get(eventType);
			if (actionConditions == null) {
				return false;
			}
			success = actionConditions.remove(action);
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.RuleProcessor#matchEventToActions(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public abstract List<String> matchEventToActions(Model event);
	
	protected Map<String, Set<String>> getRuleSet() {
		return ruleSet;
	}

}
