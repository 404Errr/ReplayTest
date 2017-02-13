package game;

import java.util.ArrayList;

import player.Player;

public class Game {
	private static Player player;
	private static ArrayList<Player> players;

	public static void init() {
		players = new ArrayList<>();
		players.add(new Player(100,100));
		player = players.get(0);
	}

	public static Player getPlayer() {
		return player;
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}
}
