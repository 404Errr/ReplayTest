package server.net;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import net.PlayerPacket;
import server.game.ServerGame;

public class ServerTCPReciever implements Runnable {
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
				PlayerPacket packet = (PlayerPacket) oiStream.readObject();
				System.out.println("Server: "+packet);

				int playerIndex = ServerGame.indexOfPlayer(packet.getUserName());
				if (playerIndex!=-1) {
					ServerGame.getPlayers().get(playerIndex).update(packet.getX(), packet.getY(), packet.getdX(), packet.getdY(), packet.getFacing());
				}
				else {
					ServerGame.playerJoin(packet.getUserName());
				}




			} catch (Exception e) {}
		}

	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
