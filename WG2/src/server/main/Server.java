package server.main;

import server.graphics.Window;
import server.net.ServerConnector;
import shared.data.NetData;

public class Server implements NetData {
	public static void run(){
		System.out.println("SERVER STARTED");
		Window.init();
		ServerConnector.connectClients();
	}

}
