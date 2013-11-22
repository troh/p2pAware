/**
 * 
 */
package com.troh.aware.util;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.update.Update;
import com.hp.hpl.jena.update.UpdateAction;

/**
 * @author tom
 *
 */
public class QueryProcessorImpl implements QueryProcessor {

	@Override
	public void addDataToModel(Model model, Model addition) {
		model.enterCriticalSection(Lock.WRITE);
		try {
			model.add(addition);
		} finally {
			model.leaveCriticalSection();
		}		
	}

	@Override
	public void updateModel(Model model, Update update) {
		model.enterCriticalSection(Lock.WRITE);
		try {
			UpdateAction.execute(update, model);
		} finally {
			model.leaveCriticalSection();
		}			
	}

	@Override
	public boolean runAskQuery(Model model, Query query) {
		model.enterCriticalSection(Lock.READ);
		try {
			return createQueryExecution(model, query).execAsk();
		} finally {
			model.leaveCriticalSection();
		}
	}

	@Override
	public ResultSet runSelectQuery(Model model, Query query) {
		model.enterCriticalSection(Lock.READ);
		try {
			return createQueryExecution(model, query).execSelect();
		} finally {
			model.leaveCriticalSection();
		}		
	}

	@Override
	public Model runConstructQuery(Model model, Query query) {
		model.enterCriticalSection(Lock.READ);
		try {
			return createQueryExecution(model, query).execConstruct();
		} finally {
			model.leaveCriticalSection();
		}		
	}

	@Override
	public Model runDescribeQuery(Model model, Query query) {
		model.enterCriticalSection(Lock.READ);
		try {
			return createQueryExecution(model, query).execDescribe();
		} finally {
			model.leaveCriticalSection();
		}		
	}
	
	private QueryExecution createQueryExecution(Model model, Query query) {
		return QueryExecutionFactory.create(query,model);
	}

}
