package client.weapon.entity;

import java.awt.Color;

import util.Util;

public class LimitedProjectile extends AbstractProjectile {
	private float range, traveled, opacity;

	public LimitedProjectile(float damage, float recoil, float size, float range, Color color, float x, float y, float dX, float dY, boolean fade) {
		super(damage, recoil, size, color, x, y, dX, dY);
		this.range = range;
		opacity = 1f;
	}

	@Override
	public boolean tick() {
		traveled+=Util.getDistance(0, 0, dX, dY);
		opacity = traveled/range/2+0.5f;
		if (traveled>range) destroyIn = 0;
		return super.tick();
	}

	public float getOpacity() {
		return opacity;
	}
}
