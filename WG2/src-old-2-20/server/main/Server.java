package server.main;

import server.graphics.Window;

public class Server/* implements NetData*/ {
	public static void run(){
		System.out.println("SERVER STARTED");
		Window.init();
		//ServerGame.init();
		//ServerConnector.connectClients();
	}

}