package client.weapon;

import java.awt.Color;

import client.entity.Entity;
import client.player.Player;
import data.GameData;

public abstract class WeaponEntity extends Entity implements GameData {
	protected float damage;
	protected Player owner;

	public WeaponEntity(Player owner, Color color, float x, float y, float damage) {
		super(color, x, y);
		this.owner = owner;
		this.damage = damage;
	}

	public WeaponEntity(Color color, float x, float y, float damage) {
		super(color, x, y);
		this.damage = damage;
	}

	public WeaponEntity(Color color, float x, float y) {
		super(color, x, y);
	}

	protected void damage(Player player) {
		if (DAMAGE) player.changeHealth(-damage);
	}

	public Player getOwner() {
		return owner;
	}


}
