/**
 * 
 */
package com.troh.aware.net;

/**
 * @author tom
 *
 */
public interface SocketGroup {
	void send(String recipient, String action, Object[] data);
	boolean addSocketManager(SocketManager socketManager, String name);
	boolean removeSocketManager(String name);
}
