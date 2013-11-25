/**
 * 
 */
package com.troh.aware.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.troh.aware.eca.DataRepository;

/**
 * @author tom
 *
 */
public class DefaultInitialDataParser implements InitialDataParser {
	
	private String name;
	private DataInputStream dataIn;
	private String IpAddress;
	private Socket socket;
	private DataRepository repository;
	private String ipProperty = "http://www.ecaproject.org/hasIp";
	
	public DefaultInitialDataParser(DataInputStream dataIn, Socket socket, DataRepository repository) throws IOException {
		this.dataIn = dataIn;
		this.socket = socket;
		this.repository = repository;
		retrieveData();
	}

	private void retrieveData() throws IOException {
		name = dataIn.readUTF();
		IpAddress = getIpAddress();
	}

	private String getIpAddress() {
		return socket.getInetAddress().toString().substring(1);
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.net.InitialDataParser#commitIntroData(com.hp.hpl.jena.rdf.model.Model)
	 */
	@Override
	public void commitIntroData(Model eventNotification) {
		Dataset dataset = repository.getDataset();
		try {
			Model model = dataset.getDefaultModel();
			Property hasIp = model.createProperty(ipProperty);
			Literal litIp = model.createLiteral(IpAddress);
			Resource peer = model.createResource(name);
			peer.addProperty(hasIp, litIp);
			dataset.commit();
		} finally {
			dataset.end();
		}
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.net.InitialDataParser#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

}
