/**
 * 
 */
package com.troh.aware.util;

import java.lang.reflect.Field;

/**
 * @author tom
 *
 */
public class DefaultTagsContainer implements TagsContainer {
	private String classTag = "class";
	private String methodTag = "method";
	private String handlerTag = "handler";
	private String sendToTag = "sendTo";
	private String superPeerName = "super";
	private String paramTag = "param";
	private String dataTag = "data";
	private String valueTag = "value";
	private String nameTag = "name";
	private String structTag = "struct";
	private String arrayTag = "array";
	private String stringTag = "string";
	

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getParamTag()
	 */
	@Override
	public String getParamTag() {
		return paramTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getClassTag()
	 */
	@Override
	public String getClassTag() {
		return classTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getMethodTag()
	 */
	@Override
	public String getMethodTag() {
		return methodTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getHandlerTag()
	 */
	@Override
	public String getHandlerTag() {
		return handlerTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getSendToTag()
	 */
	@Override
	public String getSendToTag() {
		return sendToTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getSuperPeerName()
	 */
	@Override
	public String getSuperPeerName() {
		return superPeerName;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getDataTag()
	 */
	@Override
	public String getDataTag() {
		return dataTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getValueTag()
	 */
	@Override
	public String getValueTag() {
		return valueTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getArrayTag()
	 */
	@Override
	public String getArrayTag() {
		return arrayTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getStructTag()
	 */
	@Override
	public String getStructTag() {
		return structTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getNameTag()
	 */
	@Override
	public String getNameTag() {
		return nameTag;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.util.TagsContainer#getStringTag()
	 */
	@Override
	public String getStringTag() {
		return stringTag;
	}
	
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuilder builder = new StringBuilder();
		for (Field each: fields) {
			builder.append(each.getName());
			builder.append(" = ");
			try {
				builder.append(each.get(this));
			} catch (IllegalAccessException ex) {
				builder.append("ERROR: Unable to access field!");
				ex.printStackTrace();
			}
			builder.append("\n");
		}
		return builder.toString();
	}

}
