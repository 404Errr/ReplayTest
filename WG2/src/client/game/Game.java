package client.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import client.edit.Edit;
import client.entity.Entity;
import client.level.Level;
import client.player.Player;
import client.player.ai.AIPlayer;
import client.player.controlled.ControlledPlayer;
import data.ColorData;
import data.TileData;

public class Game implements ColorData, TileData {
	private static ControlledPlayer player;//the player
	private static List<Entity> entities;

	public static void init() {
		entities = new ArrayList<>();
		player = new ControlledPlayer(COLOR_PLAYER, Level.getRandomSpawnPoint());//the player
		entities.add(player);
		if (Edit.editMode) return;
		entities.add(new AIPlayer(COLOR_PLAYER_1, Level.getSafestSpawnPoint(null)));
//		entities.add(new AIPlayer(COLOR_PLAYER_2, Level.getSafestSpawnPoint(null)));
//		entities.add(new AIPlayer(COLOR_PLAYER_3, Level.getSafestSpawnPoint(null)));

//		for (int i = 0;i<13;i++) entities.add(new AIPlayer(getRandomColor(), Level.getSafestSpawnPoint(null)));

		for (Entity entity:entities) if (entity instanceof AIPlayer) ((AIPlayer)entity).initSightLines();//assumes there are only players in entities
	}

	public static Color getRandomColor() {
		return new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1f);
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
