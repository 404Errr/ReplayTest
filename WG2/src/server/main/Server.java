package server.main;

import server.net.ServerConnector;
import shared.data.NetData;

public class Server implements NetData {
//	private static ServerUpdateLoop updateLoop;

	public static void run(){
		System.out.println("SERVER STARTED");
		ServerConnector.connectClients();
	}

}
