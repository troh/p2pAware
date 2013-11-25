/**
 * 
 */
package com.troh.aware.net;

/**
 * @author tom
 *
 */
public interface ConnectionCreator {
	SocketManager connect(String address, int port);
}
