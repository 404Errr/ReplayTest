package server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import shared.data.NetData;

public class ServerConnector implements NetData {
	private static ArrayList<Socket> sockets = new ArrayList<>();
	private static ArrayList<ServerTCPSender> tcpSenders = new ArrayList<>();
	private static ArrayList<ServerTCPReciever> tcpRecievers = new ArrayList<>();

	public static void connectClients() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server started with port: "+PORT+"\nWaiting...");
		}
		catch (Exception e) {
			System.err.println("Failed with port: "+PORT);
			System.out.println("Another server is probably already running.");
			System.exit(0);
		}
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				sockets.add(clientSocket);
				System.out.println("Client connected: "+clientSocket.getInetAddress().toString().substring(1)+":"+clientSocket.getPort());
				ServerTCPSender sender = new ServerTCPSender(clientSocket);
				ServerTCPReciever reciever = new ServerTCPReciever(clientSocket);
				tcpSenders.add(sender);
				tcpRecievers.add(reciever);
				Thread senderThread = new Thread(sender, "Sender "+tcpSenders.indexOf(sender));
				Thread recieverThread = new Thread(reciever, "Reciever "+tcpSenders.indexOf(reciever));
				senderThread.start();
				recieverThread.start();
			}
		}
		catch (IOException e) {}
	}
}
