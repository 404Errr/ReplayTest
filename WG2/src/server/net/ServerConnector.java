package server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import shared.data.NetData;

public class ServerConnector implements NetData {
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
				System.out.println("Client connected: "+clientSocket.getInetAddress().toString().substring(1)+":"+clientSocket.getPort());
				new ServerTCPNet(clientSocket);
			}
		}
		catch (IOException e) {}
	}
}
