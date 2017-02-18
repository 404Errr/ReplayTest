package server.net;

import java.net.Socket;

public class ServerTCPNet {
	Socket socket;

	public ServerTCPNet(Socket socket) {
		this.socket = socket;
		Thread sender = new Thread(new Sender(), "Sender");
		Thread reciever = new Thread(new Reciever(), "Reciever");
		sender.start();
		reciever.start();


	}

	private class Sender implements Runnable {
		@Override
		public void run() {
			System.out.println("Sender started");
		}

	}

	private class Reciever implements Runnable {
		@Override
		public void run() {
			System.out.println("Reciever started");
		}

	}

}
