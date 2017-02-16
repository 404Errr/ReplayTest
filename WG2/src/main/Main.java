package main;

public class Main {
	private static boolean CLIENT = true;


	public static void main(String[] args) {
		if (CLIENT) {
			client.main.Client.run();
		}
		else {
			server.main.Server.run();
		}
	}
}