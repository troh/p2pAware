/**
 * 
 */
package com.troh.aware.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.troh.aware.factories.InitialDataParserFactory;

/**
 * @author tom
 *
 */
public class DefaultSocketManager implements SocketManager {
	private Socket socket;
	private DataInputStream dataIn;
	private InitialDataParserFactory factory;
	private String name;
	private SocketGroup socketGroup;
	
	public DefaultSocketManager(Socket socket, InitialDataParserFactory factory, SocketGroup socketGroup) {
		try {
			this.socketGroup = socketGroup;
			this.factory = factory;
			this.socket = socket;
			this.dataIn = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			//to do: disconnect
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		if (!socket.isClosed()) {
			try {
				InitialDataParser dataParser = factory.createInitialDataParser(dataIn, socket);
				dataParser.commitIntroData();
				name = dataParser.getName();
				socketGroup.addSocketManager(this, name);
			} catch (IOException ex) {
				ex.printStackTrace();
				disconnect();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.troh.aware.net.SocketManager#send(java.lang.String, java.lang.Object[])
	 */
	@Override
	public void send(String eventNotification, Object[] data) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.troh.aware.net.SocketManager#disconnect()
	 */
	@Override
	public void disconnect() {
		// to do
	}

}
