package client.level;

import client.logic.BreakableLine;
import client.player.Player;

public class SpawnPointVisibiltiyLine extends BreakableLine {
	private SpawnPoint spawnPoint;
	private Player player;

	public SpawnPointVisibiltiyLine(SpawnPo int spawnPoint, Player player) {
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
//		x1 = spawnPoint1.x;
//		y1 = spawnPoint1.y;
//		x2 = spawnPoint2.x;
//		y2 = spawnPoint2.y;
	}
}
