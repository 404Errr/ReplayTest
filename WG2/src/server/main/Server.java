package server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import data.NetData;

public class Server implements NetData {
	public static boolean RUNNING = true;

//	private static ServerUpdateLoop updateLoop;

	public static void run(){
		System.out.println("SERVER STARTED");
		/*updateLoop = new ServerUpdateLoop();
		Thread update = new Thread(updateLoop, "Loop");
		update.start();*/

		try {
			ServerSocket listener = new ServerSocket(PORT);
			try {
				while (RUNNING) {
					Socket socket = listener.accept();








				}
			}
			finally {
				listener.close();
			}



		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
