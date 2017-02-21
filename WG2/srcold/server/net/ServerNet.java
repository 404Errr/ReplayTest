/*package server.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.UUID;

import server.game.ServerGame;
import shared.net.PlayerJoinMessage;
import shared.net.PlayerMessage;

public class ServerNet {
	Socket socket;
	UUID playerID;
	ObjectInputStream inStream;
	ObjectOutputStream outputStream;

	public ServerNet(Socket socket) {
		this.socket = socket;
		try {
			inStream = new ObjectInputStream(this.socket.getInputStream());
			outputStream = new ObjectOutputStream(this.socket.getOutputStream());
			PlayerJoinMessage join = (PlayerJoinMessage) inStream.readObject();
			playerID = join.getPlayerID();
			ServerGame.playerJoin(join.getUsername(), join.getPlayerID());
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't connect.");
			System.exit(0);
		}
		Thread sender = new Thread(new Sender(), "Sender");
		Thread reciever = new Thread(new Reciever(), "Reciever");
		sender.start();
		reciever.start();
	}

	private class Sender implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					for (int i = 0; i<ServerGame.getPlayers().size();i++) {
						outputStream.writeObject(new PlayerMessage(ServerGame.getPlayers().get(i)));
					}
					Thread.sleep(30);
				}
			}
			catch (SocketException e) {
				System.err.println(playerID+" Probably disconnected");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private class Reciever implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					PlayerMessage playerMessage = (PlayerMessage) inStream.readObject();
					//TODO
				}
			}
			catch (SocketException e) {
				System.err.println(playerID+" Probably disconnected");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}*/
