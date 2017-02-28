package client.weapon;

import client.player.Player;
import data.Data;
import data.WeaponData;

public class PlayerWeapon implements Data, WeaponData {
	private Weapon type;
	private Player owner;

	public PlayerWeapon(Player owner, Weapon type) {
		this.owner = owner;
		this.type = type;
	}

	public void tick() {
		if (isActive()) {
			type.tick();
		}
	}

	private boolean isActive() {
		return owner.getActiveWeapon()==this;
	}

	public Weapon getType() {
		return type;
	}
	
	public void setCooldown(float cooldown) {
		type.setCooldown(cooldown);
	}

	public float getCooldown() {
		return type.getCooldown();
	}

	@Override
	public String toString() {
		return type.toString();
	}


}
