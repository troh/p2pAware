/**
 * 
 */
package com.troh.aware.eca;

import java.util.HashMap;
import java.util.Map;
/**
 * @author tom
 *
 */
public abstract class AbstractActionExecutor implements ActionExecutor {
	private Map<String,Object> preparedExecutionObjects = new HashMap<>(); 

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ActionExecutor#execute(java.lang.String)
	 */
	@Override
	public abstract void execute(String action) throws MalformedActionStringException;

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ActionExecutor#execute(java.lang.String, java.lang.Object[])
	 */
	@Override
	public abstract void execute(String action, Object[] data) throws MalformedActionStringException;

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ActionExecutor#addExecutionObject(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addExecutionObject(String identifier, Object executor) {
		synchronized (preparedExecutionObjects) {
			preparedExecutionObjects.put(identifier, executor);
		}
	}
	
	protected Object getExecutor(String identifier) {
		synchronized (preparedExecutionObjects) {
			return preparedExecutionObjects.get(identifier);
		}
	}

}
