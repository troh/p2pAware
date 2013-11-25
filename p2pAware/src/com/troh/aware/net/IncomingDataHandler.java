/**
 * 
 */
package com.troh.aware.net;

/**
 * @author tom
 *
 */
public interface IncomingDataHandler {
	void handle(String message, Object[] data);
}
