/**
 * 
 */
package com.troh.aware.eca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * @author tom
 *
 */
public abstract class AbstractActionCompleter implements ActionCompleter {
	private Map<String, ActionCompleterComponent> completerComponents = new HashMap<String, ActionCompleterComponent>();

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ActionCompleter#removeComponent(java.lang.String)
	 */
	@Override
	public boolean removeComponent(String name) {
		synchronized (completerComponents) {
			if (completerComponents.containsKey(name)) {
				completerComponents.remove(name);
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ActionCompleter#addComponent(com.troh.aware.eca.ActionCompleterComponent)
	 */
	@Override
	public boolean addComponent(ActionCompleterComponent component) {
		synchronized (completerComponents) {
			if (!completerComponents.containsKey(component.getName())) {
				completerComponents.put(component.getName(), component);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public abstract void completeAction(Model event, List<String> actionConditions);
	@Override
	public abstract void completeAction(Model event, Object[] data, List<String> actionConditions);

	
	protected ActionCompleterComponent getComponentForName(String name) {
		return completerComponents.get(name);
	}

}
