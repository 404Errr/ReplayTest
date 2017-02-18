package client.game;

import java.util.ArrayList;

import client.player.ControlledPlayer;
import client.projectile.Projectile;

public class Game {
	private static ControlledPlayer player;//the user's player
	private static ArrayList<ControlledPlayer> players;//all players
	private static ArrayList<Projectile> projectiles;//all projectiles

	public static void init() {
		players = new ArrayList<>();
		projectiles = new ArrayList<>();
		players.add(new ControlledPlayer(1,1));
		player = players.get(0);//player is the first player in players
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static ArrayList<ControlledPlayer> getPlayers() {
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
