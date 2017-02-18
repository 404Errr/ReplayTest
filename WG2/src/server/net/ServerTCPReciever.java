package server.net;

import java.net.Socket;

public class ServerTCPReciever implements Runnable {
	private Socket socket;

	public ServerTCPReciever(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

	}
}
