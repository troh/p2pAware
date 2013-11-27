/**
 * 
 */
package com.troh.aware.eca;

import java.util.Date;

import javax.xml.bind.DatatypeConverter;

/**
 * @author tom
 *
 */
public class DefaultPrimitiveObjectCreator implements PrimitiveObjectCreator {

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.PrimitiveObjectCreator#createBinary(java.lang.String)
	 */
	@Override
	public byte[] createBinary(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.PrimitiveObjectCreator#createDate(java.lang.String)
	 */
	@Override
	public Date createDate(String content) {
		return DatatypeConverter.parseDateTime(content).getTime();
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.PrimitiveObjectCreator#createDouble(java.lang.String)
	 */
	@Override
	public Double createDouble(String content) {
		return new Double(content);
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.PrimitiveObjectCreator#createBoolean(java.lang.String)
	 */
	@Override
	public Boolean createBoolean(String content) {
		return new Boolean("1".equals(content));
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.PrimitiveObjectCreator#createInteger(java.lang.String)
	 */
	@Override
	public Integer createInteger(String content) {
		return new Integer(content);
	}

}
