/*package client.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.UUID;

import client.game.Game;
import shared.data.NetData;
import shared.net.PlayerJoinMessage;
import shared.net.PlayerMessage;

public class ClientNet implements NetData {
	Socket socket;
	UUID playerID;

	ObjectOutputStream outputStream;
	ObjectInputStream inStream;

	public ClientNet(Socket socket) {
		this.socket = socket;
		UUID id = new UUID(new Random().nextLong(), new Random().nextLong());
		System.out.println("UUID: "+id);
		playerID = id;
		try {
			outputStream = new ObjectOutputStream(this.socket.getOutputStream());
			inStream = new ObjectInputStream(this.socket.getInputStream());
			outputStream.writeObject(new PlayerJoinMessage(USERNAME, playerID));
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
					outputStream.writeObject(new PlayerMessage(Game.getPlayer(), playerID));
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
					//FIXME
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

}
*/