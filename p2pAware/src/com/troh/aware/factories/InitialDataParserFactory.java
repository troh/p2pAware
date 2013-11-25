/**
 * 
 */
package com.troh.aware.factories;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.troh.aware.net.InitialDataParser;

/**
 * @author tom
 *
 */
public interface InitialDataParserFactory {
	InitialDataParser createInitialDataParser(DataInputStream dIn, Socket socket) throws IOException;
}
