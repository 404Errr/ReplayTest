package client.level;

import client.logic.BreakableLine;
import client.player.Player;

public class SpawnPointVisibiltiyLine extends BreakableLine {
	private SpawnPoint spawnPoint;
	private Player player;

	public SpawnPointVisibiltiyLine(SpawnPoint spawnPoint, Player player) {
		this.player = player;
		this.spawnPoint = spawnPoint;
		x2 = spawnPoint.x+0.5f;
		y2 = spawnPoint.y+0.5f;
	}

	public SpawnPoint getSpawnPoint() {
		return spawnPoint;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void setLocation() {
		x1 = player.getX()+HALF_PLAYER_SIZE;
		y1 = player.getY()+HALF_PLAYER_SIZE;
	}
}
