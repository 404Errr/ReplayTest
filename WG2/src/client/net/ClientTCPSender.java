package client.net;

import java.net.Socket;

public class ClientTCPSender implements Runnable {
	private Socket socket;

	public ClientTCPSender(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

	}
}
