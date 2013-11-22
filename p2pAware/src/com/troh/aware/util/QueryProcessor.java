/**
 * 
 */
package com.troh.aware.util;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.update.Update;

/**
 * @author tom
 *
 */
public interface QueryProcessor {
	void addDataToModel(Model model, Model addition);
	void updateModel(Model model, Update update);
	boolean runAskQuery(Model model, Query query);
	ResultSet runSelectQuery(Model model, Query query);
	Model runConstructQuery(Model model, Query query);
	Model runDescribeQuery(Model model, Query query);
}
