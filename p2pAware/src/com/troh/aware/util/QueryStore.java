/**
 * 
 */
package com.troh.aware.util;

import com.hp.hpl.jena.query.Query;

/**
 * @author tom
 *
 */
public interface QueryStore {
	Query getEventTypeQuery();
	String getEventTypeQueryVariable();
}
