package client.net;

import java.net.Socket;

import shared.data.NetData;

public class ClientConnector implements NetData {
	private static Socket clientSocket;
	public static void connectToServer() {
		try {
			clientSocket = new Socket(IP, PORT);
			System.out.println("Connected to: "+IP+":"+PORT);
			ClientTCPSender sender = new ClientTCPSender(clientSocket);
			ClientTCPReciever reciever = new ClientTCPReciever(clientSocket);
			Thread senderThread = new Thread(sender, "Sender ");
			Thread recieverThread = new Thread(reciever, "Reciever");
			senderThread.start();
			recieverThread.start();
		}
		catch (Exception e) {
			System.err.println("Failed to connect to: "+IP+":"+PORT);
			System.exit(0);
		}
	}
}
