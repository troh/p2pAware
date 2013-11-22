/**
 * 
 */
package com.troh.aware.util;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.update.Update;

/**
 * @author tom
 *
 */
public interface QueryProcessor {
	void addDataToModel(Model model, Model addition);
	void updateModel(Model model, Update update);
	void executeQuery(Model model, Query query);
}
