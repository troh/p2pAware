/**
 * 
 */
package com.troh.aware.eca;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.troh.aware.util.TagsContainer;
import com.troh.aware.util.XMLUtility;

/**
 * @author tom
 *
 */
public class DefaultParameterExtractor implements ParameterExtracter {
	private String MAP_TYPE = "HashMap";
	private String LIST_TYPE = "ArrayList";
	private XMLUtility xmlUtility;
	private TagsContainer tagsContainer;
	
	public DefaultParameterExtractor(XMLUtility xmlUtility, TagsContainer tagsContainer) {
		this.xmlUtility = xmlUtility;
		this.tagsContainer = tagsContainer;
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
		//Find parameter here
		if (additionalData != null)
			parameters[numberOfParameters-1] = additionalData;
		return parameters;
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
