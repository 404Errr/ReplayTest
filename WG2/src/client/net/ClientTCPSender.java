package client.net;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import client.game.Game;
import net.PlayerPacket;

public class ClientTCPSender implements Runnable {

	private Socket socket;
	@Override
	public void run() {
		ObjectOutputStream objOutStream = null;
		try {
			OutputStream outStream = socket.getOutputStream();
			objOutStream = new ObjectOutputStream(outStream);
		}
		catch (Exception e) {}
		while (true) {
			try {
				if (Game.getPlayer()!=null) {
					objOutStream.writeObject(new PlayerPacket(Game.getPlayer()));
				}
				Thread.sleep(16);
			}
			catch (SocketException e) {
				System.err.println("Disconnected");
				System.exit(0);
			}
			catch (Exception e) {}
		}
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


}
