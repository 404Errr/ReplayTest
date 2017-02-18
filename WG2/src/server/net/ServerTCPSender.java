package server.net;

import java.net.Socket;

public class ServerTCPSender implements Runnable {
	private Socket socket;

	public ServerTCPSender(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

	}
}
