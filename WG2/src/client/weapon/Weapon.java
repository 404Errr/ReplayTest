package client.weapon;

import client.player.Player;
import data.ControlData;
import data.GameData;
import data.PlayerData;
import data.WeaponData;

public abstract class Weapon implements WeaponData, ControlData, PlayerData, GameData {
	protected float length, width, rpf, toBeFired;
	protected boolean constantUse;
	protected Player owner;

	public Weapon(Player owner, float rpm, float length, float width) {
		this.owner = owner;
		this.rpf = rpm/60f/UPS;
		this.length = length+HALF_PLAYER_SIZE;
		this.width = width;
	}

	public Weapon(Player owner, float rpm) {
		this.owner = owner;
		this.rpf = rpm/60f/UPS;
	}

	public void tick() {
		if (owner.getActiveWeapon()==this) {
			if (owner.getMouseControl(USE_1)||constantUse) {
				while (toBeFired>=1) {
					toBeFired-=1f;
					use();
				}
				toBeFired+=rpf;
			}
			else {
				toBeFired+=rpf;
				if (toBeFired>1f) toBeFired = 1f;
			}
		}
	}

	public float getRpf() {
		return rpf;
	}

	public Player getOwner() {
		return owner;
	}

	public float getToBeFired() {
		return toBeFired;
	}

	protected Player getPlayer() {
		return owner;
	}

	public float getGirth() {
		return width;
	}

	public float getDepth() {
		return length;
	}

	protected abstract void use();
}
