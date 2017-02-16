package client.game;

import java.util.ArrayList;

import client.player.Player;
import client.projectile.Projectile;

public class Game {
	private static Player player;//the user's player
	private static ArrayList<Player> players;//all players
	private static ArrayList<Projectile> projectiles;//all projectiles

	public static void init() {
		players = new ArrayList<>();
		projectiles = new ArrayList<>();
		players.add(new Player(16,14));
		player = players.get(0);//player is the first player in players
	}

	public static Player getPlayer() {
		return player;
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public static ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public static Projectile getProjectile(int projectile) {
		return projectiles.get(projectile);
	}
}
