package client.game;

import java.util.ArrayList;
import java.util.List;

import client.entity.Entity;
import client.player.ControlledPlayer;
import data.ColorData;

public class Game implements ColorData {
	private static ControlledPlayer player;//the user's player
	private static List<Entity> entities;

	public static void init() {
		player = new ControlledPlayer(COLOR_PLAYER, 1f, 1f);//the player
		entities = new ArrayList<>();
		entities.add(player);
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static Entity getEntity(int entity) {
		return entities.get(entity);
	}

//	public static void addProjectile(Projectile projectile) {
//		projectiles.add(projectile);
//	}
//
//	public static void addHitscan(Hitscan hitscan) {
//		hitscans.add(hitscan);
//	}

	public static List<Entity> getEntities() {
		return entities;
	}
}
