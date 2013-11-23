/**
 * 
 */
package com.troh.aware.eca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.troh.aware.util.MissingVariableBindingException;
import com.troh.aware.util.QueryProcessor;
import com.troh.aware.util.QueryStore;

/**
 * @author tom
 *
 */
public class FlatRuleProcessor extends AbstractRuleProcessor {
	private QueryProcessor queryProcessor;
	private QueryStore queryStore;
	
	public FlatRuleProcessor(QueryProcessor queryProcessor, QueryStore queryStore) {
		this.queryProcessor = queryProcessor;
		this.queryStore = queryStore;
	}
	/* (non-Javadoc)
	 * @see com.troh.aware.eca.AbstractRuleProcessor#matchEventToActions(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public List<String> matchEventToActions(Model event) throws MalformedEventNotificationException, MissingVariableBindingException {
		ResultSet queryResult = queryProcessor.runSelectQuery(event, queryStore.getEventTypeQuery());
		QuerySolution solution = ensureSingleResult(queryResult);
		String eventType = extractEventTypeURI(solution, queryStore.getEventTypeQueryVariable());
		return getActionConditionsForEventType(eventType);
	}
	
	private List<String> getActionConditionsForEventType(String eventType) {
		Map<String, Set<String>> ruleSet = getRuleSet();
		synchronized (ruleSet) {
			Set<String> actionConditions = ruleSet.get(eventType);
			return (actionConditions == null) ? new ArrayList<String>() : new ArrayList<String>(actionConditions);
		}
	}
	
	private QuerySolution ensureSingleResult(ResultSet queryResult) throws MalformedEventNotificationException {
		if (!queryResult.hasNext())
			throw new MalformedEventNotificationException("ERROR: No event types contained in event notification.");
		QuerySolution result  = queryResult.next();
		if (queryResult.hasNext())
			throw new MalformedEventNotificationException("ERROR: More than one event type contained in event notification.");
		return result;
	}
	
	private String extractEventTypeURI(QuerySolution solution, String variableBinding) throws MissingVariableBindingException {
		RDFNode node = solution.get(variableBinding);
		if (node == null)
			throw new MissingVariableBindingException("ERROR: Variable binding for event-notification not found. Check the queryStore.properties file for inconsistencies");
		return node.toString();
	}

}
