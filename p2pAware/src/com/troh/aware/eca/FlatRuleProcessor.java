/**
 * 
 */
package com.troh.aware.eca;

import java.util.List;

import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
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
	public List<String> matchEventToActions(Model event) {
		ResultSet queryResult = queryProcessor.runSelectQuery(event, queryStore.getEventTypeQuery());
		
		return null;
	}

}
