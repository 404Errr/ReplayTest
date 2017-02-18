package server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import data.NetData;

public class ServerConnector implements NetData {
	private static boolean running = true;

	private static ArrayList<Socket> sockets = new ArrayList<>();
	private static ArrayList<ServerTCPSender> tcpSenders = new ArrayList<>();
	private static ArrayList<ServerTCPReciever> tcpRecievers = new ArrayList<>();

	public static void connectClients() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
		}
		catch (Exception e) {
			System.err.println("Failed with port: "+PORT);
			System.out.println("Another server is probably already running.");
			System.exit(0);
		}
		try {
			System.out.println("Server started with port: "+PORT+"\nWaiting...");
			//while (running) {
				Socket clientSocket = serverSocket.accept();
				sockets.add(clientSocket);
				System.out.println("Client connected: "+clientSocket.getInetAddress()+":"+clientSocket.getPort());
				ServerTCPSender sender = new ServerTCPSender();
				ServerTCPReciever reciever = new ServerTCPReciever();
				sender.setSocket(clientSocket);
				reciever.setSocket(clientSocket);
				tcpSenders.add(sender);
				tcpRecievers.add(reciever);
				Thread senderThread = new Thread(sender, "Sender "+tcpSenders.indexOf(sender));
				Thread recieverThread = new Thread(reciever, "Reciever "+tcpSenders.indexOf(reciever));
				senderThread.start();
				recieverThread.start();
			//}
		}
		catch (IOException e) {}
	}
}
