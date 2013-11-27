package com.troh.aware.eca;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.troh.aware.util.TagsContainer;
import com.troh.aware.util.XMLUtility;

/**
 * @author tom
 *
 */
public class DefaultActionExecutor extends AbstractActionExecutor {
	private XMLUtility xmlUtility;
	private TagsContainer tagsContainer;
	private ParameterExtracter parameterExtracter;
	
	public DefaultActionExecutor(XMLUtility xmlUtility, TagsContainer tagsContainer, ParameterExtracter parameterExtracter) {
		this.tagsContainer = tagsContainer;
		this.xmlUtility = xmlUtility;
		this.parameterExtracter = parameterExtracter;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.AbstractActionExecutor#execute(java.lang.String)
	 */
	@Override
	public void execute(String action) throws MalformedActionStringException {
		execute(action, null);
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.AbstractActionExecutor#execute(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void execute(String action, Object[] data) throws MalformedActionStringException {
		Document actionDocument = null;
		try {
			actionDocument = xmlUtility.buildDocument(action);
		} catch (IOException | SAXException ex) {
			System.out.println("ERROR: Unable to parse incoming action string. Exiting execute method.");
			ex.printStackTrace();
			return;
		}
		String className = xmlUtility.extractSingleTextElement(actionDocument, tagsContainer.getClassTag());
		String methodName = xmlUtility.extractSingleTextElement(actionDocument, tagsContainer.getMethodTag());
		if (className == null || methodName == null) {
			throw new MalformedActionStringException();
		}
		Object[] parameters = parameterExtracter.extractParameters(actionDocument, data);
		Object executor = getExecutor(className);
		if (executor == null) {
			throw new MalformedActionStringException("No executor with class name as in action string found.");
		}
		executeMethodOnObjectWithParameters(methodName, parameters, executor);
	}
	
	private void executeMethodOnObjectWithParameters(String methodName, Object[] parameters, Object object) throws MalformedActionStringException {
		Class<?> aClass = object.getClass();
		Class<?>[] parameterTypes = parameterExtracter.extractParameterTypes(parameters);
		try {
			Method method = aClass.getMethod(methodName, parameterTypes); //extractParams and extractParamTypes must return null if they find nothing
			method.invoke(object, parameters);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("ERROR: Unable to execute method on class with parameters as found in incoming action string.");
			e.printStackTrace();
		}
	}
}
