/**
 * 
 */
package com.troh.aware.eca;

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
		try {
			Node value = parameterNode.getFirstChild().getFirstChild();
			String type = value.getNodeName();
			String content = value.getTextContent();
			switch (type) {
				case "int":
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
					return createArray(value);
				case "member":
					return createStruct(value);
				default:
					throw new MalformedActionStringException("ERROR: Unknown type-element in action string.");
			}
		} catch (Exception ex) {
			throw new MalformedActionStringException();
		}
	}

}
