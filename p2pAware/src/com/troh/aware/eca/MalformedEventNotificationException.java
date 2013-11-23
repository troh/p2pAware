/**
 * 
 */
package com.troh.aware.eca;

/**
 * @author tom
 *
 */
public class MalformedEventNotificationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4686038087906582444L;
	public MalformedEventNotificationException() {
		super();
	}
	public MalformedEventNotificationException(String message) {
		super(message);
	}
	public MalformedEventNotificationException(String message, Throwable cause) {
		super(message,cause);
	}
	public MalformedEventNotificationException(Throwable cause) {
		super(cause);
	}
}
