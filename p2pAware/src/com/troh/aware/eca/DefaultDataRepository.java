/**
 * 
 */
package com.troh.aware.eca;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 * @author tom
 *
 */
public class DefaultDataRepository implements DataRepository {
	private String tdbLocation;
	
	public DefaultDataRepository(String tdbLocation) {
		this.tdbLocation = tdbLocation;
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.eca.DataRepository#getDataset()
	 */
	@Override
	public Dataset getDataset() {
		return TDBFactory.createDataset(tdbLocation);
	}

}
