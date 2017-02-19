package client.game;

import java.util.ArrayList;

import client.player.ControlledPlayer;
import client.projectile.Projectile;
import shared.data.ColorData;

public class Game implements ColorData {
	private static ControlledPlayer player;//the user's player
	private static ArrayList<Projectile> projectiles;

	public static void init() {
		projectiles = new ArrayList<>();
		player = new ControlledPlayer(COLOR_PLAYER, 1-0.001d,1-0.001d);//the player
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
}
