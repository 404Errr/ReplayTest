package client.weapon;

import client.player.Player;

public abstract class Weapon {
	protected float length, width, cooldown;
	protected Player owner;

	public Weapon(Player owner, float cooldown, float length, float width) {
		this.owner = owner;
		this.cooldown = cooldown;
		this.length = length;
		this.width = width;
	}

	protected float getCooldown() {
		return cooldown;
	}

	protected Player getPlayer() {
		return owner;
	}

	protected abstract void use();

	public float getWidth() {
		return width;
	}

	public float getLength() {
		return length;
	}
}
