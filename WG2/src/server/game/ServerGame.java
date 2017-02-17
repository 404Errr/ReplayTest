package server.game;

import java.util.ArrayList;

public class ServerGame {
	private static ArrayList<ServerPlayer> players;//all players
//	private static ArrayList<Projectile> projectiles;//all projectiles

	public static void init() {
		players = new ArrayList<>();
//		projectiles = new ArrayList<>();
	}

	public static ArrayList<ServerPlayer> getPlayers() {
		return players;
	}

	public static void playerJoin(String name) {

	}

}
