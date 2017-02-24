package client.level;

import java.awt.Point;
import java.util.List;

import client.entity.Entity;
import client.game.Game;
import client.player.Player;
import util.Util;

public class SpawnPoint {
	private Point point;
//	private Color color;//teams?

	public SpawnPoint(Point point/*, Color color*/) {
		this.point = point;
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
		return Util.distance(point.x, point.y, entity.getX(), entity.getY());
	}

	public float getX() {
		return point.x;
	}

	public float getY() {
		return point.y;
	}

	public Point getPoint() {
		return point;
	}


}
