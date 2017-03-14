package client.weapon;

import client.player.Player;
import data.ControlData;
import data.PlayerData;
import data.WeaponData;

public abstract class Weapon implements WeaponData, ControlData, PlayerData {
	protected float length, width, cooldown, maxCooldown;
	protected Player owner;

	public Weapon(Player owner, float maxCooldown, float length, float width) {
		this.owner = owner;
		this.maxCooldown = maxCooldown;
		this.length = length;
		if (this.length==0) this.length = -HALF_PLAYER_SIZE;//counter the default gun length
		this.width = width;
	}

	float toBeFired;

	protected void tick() {
		if (maxCooldown<1) {
			if (owner.getMouseControl(SHOOT_1)) {
				while (toBeFired>=1) {
					toBeFired--;
					use();
				}
				toBeFired+=COOLDOWN_INCREMENT/maxCooldown;
			}
			else {
				toBeFired = 0;
			}
		}
		else {
			if (cooldown>0) cooldown-=COOLDOWN_INCREMENT;
			else {
				cooldown = 0;
				if (owner.getMouseControl(SHOOT_1)) {
					cooldown = maxCooldown;
					use();
				}
			}
		}
	}

	protected float getMaxCooldown() {
		return maxCooldown;
	}

	public float getCooldown() {
		return cooldown;
	}

	public void setCooldown(float cooldown) {
		this.cooldown = cooldown;
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
