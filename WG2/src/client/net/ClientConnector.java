package client.net;

import java.net.Socket;

import data.NetData;

public class ClientConnector implements NetData {
	private static Socket clientSocket;
	public static void connectToServer() {
		try {
			clientSocket = new Socket(IP, PORT);
			ClientTCPSender sender = new ClientTCPSender();
			ClientTCPReciever reciever = new ClientTCPReciever();
			sender.setSocket(clientSocket);
			reciever.setSocket(clientSocket);
			Thread senderThread = new Thread(sender, "Sender ");
			Thread recieverThread = new Thread(reciever, "Reciever");
			senderThread.start();
			recieverThread.start();
		} catch (Exception e) {
			System.err.println("Failed to connect to: "+IP+":"+PORT);
			System.exit(0);
		}
		System.out.println("Connected to: "+IP+":"+PORT);

	}
}
