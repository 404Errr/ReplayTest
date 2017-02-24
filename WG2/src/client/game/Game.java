package client.game;

import java.util.ArrayList;
import java.util.List;

import client.entity.Entity;
import client.level.Level;
import client.player.AIPlayer;
import client.player.ControlledPlayer;
import client.player.Player;
import data.ColorData;
import data.TileData;

public class Game implements ColorData, TileData {
	private static ControlledPlayer player;//the player
	private static List<Entity> entities;

	public static void init() {
		entities = new ArrayList<>();
		player = new ControlledPlayer(COLOR_PLAYER, Level.getSafestSpawnPoint());//the player
		entities.add(player);
		entities.add(new AIPlayer(COLOR_PLAYER_1, Level.getSafestSpawnPoint()));
		entities.add(new AIPlayer(COLOR_PLAYER_2, Level.getSafestSpawnPoint()));
		entities.add(new AIPlayer(COLOR_PLAYER_3, Level.getSafestSpawnPoint()));
		for (Entity entity:entities) if (entity instanceof AIPlayer) ((AIPlayer)entity).initSightLines();
	}

	public static ControlledPlayer getPlayer() {
		return player;
	}

	public static Player getPlayer(int player) {
		return (Player)entities.get(player);
	}

	public static Entity getEntity(int entity) {
		return entities.get(entity);
	}

	public static void addEntity(Entity entity) {
		entities.add(entity);
	}

	public static List<Entity> getEntities() {
		return entities;
	}
}
