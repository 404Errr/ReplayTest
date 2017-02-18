package server.game;

import java.util.ArrayList;

import server.player.ServerPlayer;

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
		players.add(new ServerPlayer(name));
	}

	public static int indexOfPlayer(String name) {//index of for usernames
		for (int i = 0;i<players.size();i++) {
			ServerPlayer player = players.get(i);
			if (player.getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

}
