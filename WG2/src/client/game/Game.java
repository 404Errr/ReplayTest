package client.game;

import java.util.List;

import client.player.ControlledPlayer;
import client.player.Player;
import data.ColorData;

public class Game implements ColorData {
	private static ControlledPlayer player;//the user's player
	private static List<Player> players;
//	private static List<Projectile> projectiles;
//	private static List<Hitscan> hitscans;

	public static void init() {
//		projectiles = new ArrayList<>();
//		hitscans = new ArrayList<>();
		player = new ControlledPlayer(COLOR_PLAYER, 1, 1);//the player
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static Player getPlayer(int player) {
		return players.get(player);
	}

//	public static List<Projectile> getProjectiles() {
//		return projectiles;
//	}
//
//	public static void addProjectile(Projectile projectile) {
//		projectiles.add(projectile);
//	}
//
//	public static void addHitscan(Hitscan hitscan) {
//		hitscans.add(hitscan);
//	}
//
//	public static List<Hitscan> getHitscans() {
//		return hitscans;
//	}

	public static List<Player> getPlayers() {
		return players;
	}
}
