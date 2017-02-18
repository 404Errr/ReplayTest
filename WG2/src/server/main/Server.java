package server.main;

import data.NetData;
import server.net.ServerConnector;

public class Server implements NetData {
//	private static ServerUpdateLoop updateLoop;

	public static void run(){
		System.out.println("SERVER STARTED");
		ServerConnector.connectClients();
	}

}
