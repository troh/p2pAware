/**
 * 
 */
package com.troh.aware.eca;

/**
 * @author tom
 *
 */
public interface ActionExecutor {
	void execute(String action);
	void execute(String action, Object[] data);
	void addExecutionObject(String identifier, Object executor);
}
