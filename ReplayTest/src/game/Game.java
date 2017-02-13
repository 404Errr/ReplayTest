package game;

import java.util.ArrayList;

import player.Player;

public class Game {
	private static Player Player;
	private static ArrayList<Player> players;

	public static void init() {
		players = new ArrayList<>();
		players.add(new Player(100,100));

	}
}
