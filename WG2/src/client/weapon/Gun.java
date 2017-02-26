package client.weapon;

import data.WeaponData;

public enum Gun implements Weapon, WeaponData {
	BASICGUN(BASICGUN_LEGNTH, BASICGUN_WIDTH, BASICGUN_SPEED, BASICGUN_SIZE, BASICGUN_DAMAGE, BASICGUN_COOLDOWN, BASICGUN_COF, BASICGUN_SPEED_SPREAD, BASICGUN_RECOIL),
	SHOTGUN(SHOTGUN_LEGNTH, SHOTGUN_WIDTH, SHOTGUN_SPEED, SHOTGUN_SIZE, SHOTGUN_DAMAGE, SHOTGUN_COOLDOWN, SHOTGUN_COF, SHOTGUN_SPEED_SPREAD, SHOTGUN_RECOIL),
	MACHINEGUN(MACHINEGUN_LEGNTH, MACHINEGUN_WIDTH, MACHINEGUN_SPEED, MACHINEGUN_SIZE, MACHINEGUN_DAMAGE, MACHINEGUN_COOLDOWN, MACHINEGUN_COF, MACHINEGUN_SPEED_SPREAD, MACHINEGUN_RECOIL),
	RAILGUN(RAILGUN_LEGNTH, RAILGUN_WIDTH, 1, 0, RAILGUN_DAMAGE, RAILGUN_COOLDOWN, 0, 0, RAILGUN_RECOIL);

	private float wangLength, wangWidth, projectileSpeed, projectileSize, damage, cooldown, COF, speedOffset, recoil;

	private Gun(float wangLength, float wangWidth, float projectileSpeed, float projectileSize, float damage, float cooldown, float COF, float speedOffset, float recoil) {
		this.wangLength = wangLength;
		this.wangWidth = wangWidth;
		this.projectileSpeed = projectileSpeed;
		this.projectileSize = projectileSize;
		this.damage = damage;
		this.cooldown = cooldown;
		this.COF = COF;
		this.speedOffset = speedOffset;
		this.recoil = recoil;
	}

	public float getLength() {
		return wangLength;
	}

	public float getWidth() {
		return wangWidth;
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public float getProjectileSize() {
		return projectileSize;
	}

	public float getDamage() {
		return damage;
	}

	public float getCooldown() {
		return cooldown;
	}

	public float getCOF() {
		return COF;
	}

	public float getSpeedOffset() {
		return speedOffset;
	}

	public float getRecoil() {
		return recoil;
	}

}
