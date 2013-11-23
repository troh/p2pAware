/**
 * 
 */
package com.troh.aware.util;

/**
 * @author tom
 *
 */
public class MissingVariableBindingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6653702562714657891L;
	public MissingVariableBindingException() {
		super();
	}
	public MissingVariableBindingException(String message) {
		super(message);
	}
	public MissingVariableBindingException(String message, Throwable cause) {
		super(message,cause);
	}
	public MissingVariableBindingException(Throwable cause) {
		super(cause);
	}
}
