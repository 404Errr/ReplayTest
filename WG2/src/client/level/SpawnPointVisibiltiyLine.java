package client.level;

import client.ai.InterruptableLine;

public class SpawnPointVisibiltiyLine extends InterruptableLine {
	private SpawnPoint spawnPoint1, spawnPoint2;

	public SpawnPointVisibiltiyLine(SpawnPoint spawnPoint1, SpawnPoint spawnPoint2) {
		this.spawnPoint1 = spawnPoint1;
		this.spawnPoint2 = spawnPoint2;
		setLocation();
		update();
	}

	public SpawnPoint getSpawnPoint1() {
		return spawnPoint1;
	}

	public SpawnPoint getSpawnPoint2() {
		return spawnPoint2;
	}

	@Override
	public void setLocation() {
		x1 = spawnPoint1.x;
		y1 = spawnPoint1.y;
		x2 = spawnPoint2.x;
		y2 = spawnPoint2.y;
	}
}
