package client.level;

import client.logic.BreakableLine;
import client.player.Player;

public class SpawnPointVisibiltiyLine extends BreakableLine {
	private SpawnPoint spawnPoint;
	private Player player;

	public SpawnPointVisibiltiyLine(SpawnPoint spawnPoint, Player player) {
		this.spawnPoint = spawnPoint;
		this.player = player;
	}

	public SpawnPoint getSpawnPoint() {
		return spawnPoint;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void setLocation() {
		x1 = player.getX();
		y1 = player.getY();
		x2 = spawnPoint.x;
		y2 = spawnPoint.y;
	}
}
