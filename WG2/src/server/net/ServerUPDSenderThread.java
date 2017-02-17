package server.net;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import client.game.Game;
import net.PlayerPacket;

public class ServerUPDSenderThread implements Runnable {

	DatagramSocket socket;
	@Override
	public void run() {
		try {
			socket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");//TODO change ip
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outputStream);
			while (true) {
				PlayerPacket pp = new PlayerPacket(Game.getPlayer());//to send

				os.writeObject(pp);

				byte[] outgoingData = outputStream.toByteArray();
				DatagramPacket outgoingPacket = new DatagramPacket(outgoingData, outgoingData.length, IPAddress, 9876);
				socket.send(outgoingPacket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
