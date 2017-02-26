package client.weapon.entity;

import java.awt.Color;

public class Projectile extends AbstractProjectile {
	public Projectile(float damage, float recoil, float size, Color color, float x, float y, float dX, float dY) {
		super(damage, recoil, size, color, x, y, dX, dY);
	}

}
