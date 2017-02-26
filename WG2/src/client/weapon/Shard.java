package client.weapon;

import java.awt.Color;

import util.Util;

public class Shard extends Projectile {
	private float range, traveled;

	public Shard(float damage, float size, float range, Color color, float x, float y, float dX, float dY) {
		super(damage, 0, size, color, x, y, dX, dY);
		this.range = range;
	}

	@Override
	public boolean tick() {
		traveled+=Util.distance(0, 0, dX, dY);
		if (traveled>range) destroy = true;
		return super.tick();
	}
}
