package client.net;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import net.PlayerPacket;

public class ClientUDPRecieverThread implements Runnable {
	DatagramSocket socket;
	@Override
	public void run() {
		try {
			socket = new DatagramSocket(9876);
			byte[] incomingData = new byte[1024];
//			InetAddress IPAddress = InetAddress.getByName("localhost");//TODO change ip
			while (true) {

				DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
				socket.receive(incomingPacket);
				byte[] data = incomingPacket.getData();
				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				try {
					PlayerPacket pp = (PlayerPacket) is.readObject();
					System.out.println("object received = "+pp);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
