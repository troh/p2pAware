/**
 * 
 */
package com.troh.aware.eca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Node;

import com.troh.aware.util.XMLUtility;

/**
 * @author tom
 *
 */
public class DefaultRecursiveParameterFinder implements RecursiveParameterFinder {
	private XMLUtility xmlUtility;
	private PrimitiveObjectCreator primitiveObjectCreator;
	public DefaultRecursiveParameterFinder(XMLUtility xmlUtility, PrimitiveObjectCreator primitiveObjectCreator) {
		this.xmlUtility = xmlUtility;
		this.primitiveObjectCreator = primitiveObjectCreator;
	}
	
	/* (non-Javadoc)
	 * @see com.troh.aware.eca.RecursiveParameterFinder#findParameterInNode(org.w3c.dom.Node)
	 */
	@Override
	public Object findParameterInNode(Node parameterNode) throws MalformedActionStringException {
		Node type = parameterNode.getFirstChild().getFirstChild();
		String typeName = type.getNodeName();
		String content = type.getTextContent();
		switch (typeName) {
			case "int":
				return primitiveObjectCreator.createInteger(content);
			case "i4":
				return primitiveObjectCreator.createInteger(content);
			case "string":
				return content;
			case "boolean":
				return primitiveObjectCreator.createBoolean(content);
			case "double":
				return primitiveObjectCreator.createDouble(content);
			case "dateTime.iso8601":
				return primitiveObjectCreator.createDate(content);
			case "base64":
				return primitiveObjectCreator.createBinary(content);
			case "data":
				return createArray(type);
			case "member":
				return createStruct(type);
			default:
				throw new MalformedActionStringException("ERROR: Unknown type-element in action string.");
		}
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private Object createStruct(Node currentNode) {
		Map struct = new HashMap();
		while (currentNode != null) {
			
		}
		return struct;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	private Object createArray(Node type) {
		List array = new ArrayList();
		Node currentNode = type.getFirstChild();
		while (currentNode != null) {
			
		}
		return array;
	}

}
