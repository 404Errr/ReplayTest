/*package client.net;

import java.net.Socket;

import shared.data.NetData;

public class ClientConnector implements NetData {
	public static void connectToServer() {
		try {
			Socket clientSocket = new Socket(IP, PORT);
			new ClientNet(clientSocket);
			System.out.println("Connected to: "+IP+":"+PORT);
		}
		catch (Exception e) {
			System.err.println("Failed to connect to: "+IP+":"+PORT);
			System.exit(0);
		}
	}
}
*/