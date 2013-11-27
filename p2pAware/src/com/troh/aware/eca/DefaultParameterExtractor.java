/**
 * 
 */
package com.troh.aware.eca;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.troh.aware.util.TagsContainer;

/**
 * @author tom
 *
 */
public class DefaultParameterExtractor implements ParameterExtracter {
	private String MAP_TYPE = "HashMap";
	private String LIST_TYPE = "ArrayList";
	private TagsContainer tagsContainer;
	private RecursiveParameterFinder recursiveParameterFinder;
	
	public DefaultParameterExtractor(TagsContainer tagsContainer, RecursiveParameterFinder recursiveParameterFinder) {
		this.tagsContainer = tagsContainer;
		this.recursiveParameterFinder = recursiveParameterFinder;
	}
	
	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ParameterExtracter#extractParameters(org.w3c.dom.Document, boolean)
	 */
	@Override
	public Object[] extractParameters(Document actionDocument, Object[] additionalData) throws MalformedActionStringException {
		NodeList parameterElements = getParameterElements(actionDocument);
		int numberOfParameters = parameterElements.getLength();
		if (additionalData != null)
			numberOfParameters++;
		Object[] parameters = new Object[numberOfParameters];
		populateParameterArray(parameters, parameterElements);//Find parameter here
		if (additionalData != null)
			parameters[numberOfParameters-1] = additionalData;
		return parameters;
	}

	private void populateParameterArray(Object[] parameters, NodeList parameterElements) {
		for (int i = 0; i < parameterElements.getLength(); i++) {
			parameters[i] = recursiveParameterFinder.findParameterInNode(parameterElements.item(i));
		}
	}

	private NodeList getParameterElements(Document actionDocument) {
		return  actionDocument.getElementsByTagName(tagsContainer.getParamTag());
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.ParameterExtracter#extractParameterTypes(java.lang.Object[])
	 */
	@Override
	public Class<?>[] extractParameterTypes(Object[] parameters) throws MalformedActionStringException {
		Class<?>[] parameterTypes = new Class[parameters.length];
		for (int i = 0; i < parameters.length; i++) {
			Class<?> currentClass = parameters[i].getClass();
			if (currentClass.getSimpleName().equals(MAP_TYPE)) {
				parameterTypes[i] = Map.class;
			} else if (currentClass.getSimpleName().equals(LIST_TYPE)) {
				parameterTypes[i] = List.class;
			} else {
				parameterTypes[i] = currentClass;
			}
		}
		return parameterTypes;
	}

}
