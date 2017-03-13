package client.weapon;

import java.awt.Color;

import client.entity.Entity;
import client.player.Player;

public abstract class WeaponEntity extends Entity {
	private float damage;

	public WeaponEntity(Color color, float x, float y, float damage) {
		super(color, x, y);
		this.damage = damage;
	}

	protected void damage(Player player) {
		player.changeHealth(-damage);
	}
}
