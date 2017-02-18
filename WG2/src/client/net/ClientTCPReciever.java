package client.net;

import java.net.Socket;

public class ClientTCPReciever implements Runnable {
	private Socket socket;

	public ClientTCPReciever(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

	}
}
