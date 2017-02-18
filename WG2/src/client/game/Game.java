package client.game;

import java.util.ArrayList;

import client.player.ControlledPlayer;
import client.player.Player;

public class Game {
	private static ControlledPlayer player;//the user's player
	private static ArrayList<Player> players;//all players

	public static void init() {
		players = new ArrayList<>();
		player = new ControlledPlayer(1,1);//the human player is the first player in players
		players.add(player);
	}

	public static void addPlayer(Player player) {
		players.add(player);
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static Player getPlayer(int player) {
		return players.get(player);
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}
}
