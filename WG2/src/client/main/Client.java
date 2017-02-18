package client.main;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.level.Level;
import client.net.ClientConnector;
import data.NetData;

public class Client implements NetData {
	public static boolean RUNNING = true;
	private static ClientUpdateLoop updateLoop;

	public static void run(boolean online) {
		if (online) {//if should use server
			System.out.println("CLIENT STARTED");
			System.out.println("Username: "+USERNAME+"\t(change in data.NetData)");
			ClientConnector.connectToServer();
		}
		Level.init();
		Window.init();
		Game.init();
		Camera.init();
		updateLoop = new ClientUpdateLoop();
		Thread update = new Thread(updateLoop, "Loop");
		update.start();
	}
}
