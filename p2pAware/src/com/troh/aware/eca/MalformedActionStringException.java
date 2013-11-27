/**
 * 
 */
package com.troh.aware.eca;

/**
 * @author tom
 *
 */
public class MalformedActionStringException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6653702562714657891L;
	public MalformedActionStringException() {
		super();
	}
	public MalformedActionStringException(String message) {
		super(message);
	}
	public MalformedActionStringException(String message, Throwable cause) {
		super(message,cause);
	}
	public MalformedActionStringException(Throwable cause) {
		super(cause);
	}
}
