package client.level;

import client.player.Player;
import util.BreakableLineConstant;

public class SpawnPointVisibiltiyLine extends BreakableLineConstant {
	private SpawnPoint spawnPoint;
	private Player player;

	public SpawnPointVisibiltiyLine(SpawnPoint spawnPoint, Player player, boolean[][] breaks) {
		super(breaks);
		this.player = player;
		this.spawnPoint = spawnPoint;
		x2 = spawnPoint.x+0.5f;
		y2 = spawnPoint.y+0.5f;
	}

	@Override
	public void setLocation() {
		x1 = player.getX()+HALF_PLAYER_SIZE;
		y1 = player.getY()+HALF_PLAYER_SIZE;
	}

	public SpawnPoint getSpawnPoint() {
		return spawnPoint;
	}

	public Player getPlayer() {
		return player;
	}
}
