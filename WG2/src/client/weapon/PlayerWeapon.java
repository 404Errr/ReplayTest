package client.weapon;

import client.player.Player;
import data.Data;
import data.WeaponData;

public class PlayerWeapon implements Data, WeaponData {
	private Weapon type;
	private float cooldown;
	private Player owner;

	public PlayerWeapon(Player owner, Weapon type) {
		this.owner = owner;
		this.type = type;
	}

	public void tick() {
		if (isActive()) {
			if (cooldown>0) {
				cooldown-=COOLDOWN_INCREMENT;
			}
			else {
				setCooldown(0);
				if (owner.getMouseControl(MOUSE1)) {//if should shoot
					setCooldown(type.getCooldown());
					type.use();
				}
			}
		}
	}

	private boolean isActive() {
		return owner.getActiveWeapon()==this;
	}

	public Weapon getType() {
		return type;
	}

	public float getCooldown() {
		return cooldown;
	}

	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
	}

	@Override
	public String toString() {
		return type.toString();
	}


}
