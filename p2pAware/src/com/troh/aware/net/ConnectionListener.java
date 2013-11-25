/**
 * 
 */
package com.troh.aware.net;

/**
 * @author tom
 *
 */
public interface ConnectionListener extends Runnable {
	void setPort(int port);
	void shutDown();
}
