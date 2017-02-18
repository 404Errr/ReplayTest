package client.net;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import net.PlayerPacketsPacket;

public class ClientTCPReciever implements Runnable {
	private Socket socket;
	@Override
	public void run() {
		ObjectInputStream oiStream = null;
		try {
			InputStream iStream = this.socket.getInputStream();
			oiStream = new ObjectInputStream(iStream);
		}
		catch (Exception e) {}
		while (true) {
			try {
				PlayerPacketsPacket packet = (PlayerPacketsPacket) oiStream.readObject();
				System.out.println("Server: "+packet);
			} catch (Exception e) {}
		}

	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
