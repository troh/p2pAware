/**
 * 
 */
package com.troh.aware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;

/**
 * @author tom
 *
 */
public class DefaultQueryStore implements QueryStore {
	private String eventTypeQuery;
	private String eventTypeQueryVariable;
	
	public DefaultQueryStore(String configLocation) throws IOException {
		Properties queryStoreData = new Properties();
		queryStoreData.load(new FileInputStream(configLocation));
		initialiseFields(queryStoreData);
	}

	private void initialiseFields(Properties queryStoreData) {
		eventTypeQuery = getData(queryStoreData, "eventTypeQuery");
		eventTypeQueryVariable = getData(queryStoreData, "eventTypeQueryVariable").trim(); //deal with hanging whitespace from final entry
	}

	private String getData(Properties queryStoreData, String identifier) {
		return queryStoreData.getProperty(identifier);
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.QueryStore#getEventTypeQuery()
	 */
	@Override
	public Query getEventTypeQuery() {
		return createQuery(eventTypeQuery);
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.QueryStore#getEventTypeQueryVariable()
	 */
	@Override
	public String getEventTypeQueryVariable() {
		return eventTypeQueryVariable;
	}

	private Query createQuery(String queryStr) {
		return QueryFactory.create(queryStr);
	}
}
