package client.game;

import java.util.ArrayList;
import java.util.List;

import client.player.ControlledPlayer;
import client.projectile.Hitscan;
import client.projectile.Projectile;
import shared.data.ColorData;

public class Game implements ColorData {
	private static ControlledPlayer player;//the user's player
	private static List<Projectile> projectiles;
	private static List<Hitscan> hitscans;

	public static void init() {
		projectiles = new ArrayList<>();
		hitscans = new ArrayList<>();
		player = new ControlledPlayer(COLOR_PLAYER, 1-0.001d,1-0.001d);//the player
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static List<Projectile> getProjectiles() {
		return projectiles;
	}

	public static void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public static void addHitscan(Hitscan hitscan) {
		hitscans.add(hitscan);
	}

	public static List<Hitscan> getHitscans() {
		return hitscans;
	}
}
