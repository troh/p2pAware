/**
 * 
 */
package com.troh.aware.net;

/**
 * @author tom
 *
 */
public interface SocketManager extends Runnable {
	void send(String eventNotification, Object[] data);
	void disconnect();
}
