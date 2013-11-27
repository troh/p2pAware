/**
 * 
 */
package com.troh.aware.eca;

import java.util.Date;

/**
 * @author tom
 *
 */
public interface PrimitiveObjectCreator {
	byte[] createBinary(String content);
	Date createDate(String content);
	Double createDouble(String content);
	Boolean createBoolean(String content);
	Integer createInteger(String content);
}
