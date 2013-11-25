/**
 * 
 */
package com.troh.aware.net;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author tom
 *
 */
public interface InitialDataParser {
	void commitIntroData();
	String getName();
}
