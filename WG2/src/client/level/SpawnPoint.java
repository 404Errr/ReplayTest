package client.level;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import client.entity.Entity;
import client.game.Game;
import client.player.Player;
import data.MapData;
import util.Util;

@SuppressWarnings("serial")
public class SpawnPoint extends Point implements MapData {
	private List<SpawnPointVisibiltiyLine> spawnPointVisibilityLines;
	private boolean visible;

	public SpawnPoint(Point point/*, Color color*/) {
		setLocation(point.x, point.y);
	}

	public void init() {
		spawnPointVisibilityLines = new ArrayList<>();
		for (int i = 0;i<Game.getEntities().size();i++) {
			spawnPointVisibilityLines.add(new SpawnPointVisibiltiyLine(this, Game.getPlayer(i)));
		}
	}

	public float distanceToClosestPlayer(Player exclude) {
		if (Game.getEntities().isEmpty()) return 0;
		List<Entity> entities = Game.getEntities();
		float closest = distanceTo(entities.get(0)), temp;
		for (int i = 0;i<entities.size();i++) {
			if (entities.get(i) instanceof Player&&Game.getPlayer(i)!=exclude) {
				temp = distanceTo(entities.get(i));
				if (temp<closest) closest = temp;
			}
		}
		return closest;
	}

	private float distanceTo(Entity entity) {
		return Util.distance(x, y, entity.getX(), entity.getY());
	}

	public void tick() {
		visible = false;
		for (int i = 0;i<spawnPointVisibilityLines.size();i++) {
			if (!spawnPointVisibilityLines.get(i).tick()) visible = true;
		}
	}

	public List<SpawnPointVisibiltiyLine> getSpawnPointVisibilityLines() {
		return spawnPointVisibilityLines;
	}

	public boolean isVisible() {
		return visible;
	}

	public float getSafetyRating(Player exclude) {
		float rating = 0;
		List<Entity> entities = Game.getEntities();
		for (int i = 0;i<entities.size();i++) {
			if (entities.get(i) instanceof Player&&Game.getPlayer(i)!=exclude) {
				rating+=distanceTo(entities.get(i));
			}
		}
		if (isVisible()) return 0.01f*rating;
		else return rating;
	}
}
