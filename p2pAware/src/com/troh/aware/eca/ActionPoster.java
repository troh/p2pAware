/**
 * 
 */
package com.troh.aware.eca;

import org.w3c.dom.Document;

/**
 * @author tom
 *
 */
public interface ActionPoster {
	void post(Document document, Object[] data);
}
