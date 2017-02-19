package client.weapon;

import shared.data.WeaponData;

public enum GunType implements WeaponData {
	BASICGUN(BASICGUNLength, BASICGUNWidth, BASICGUNSpeed, BASICGUNSize, BASICGUNDamage, BASICGUNCooldown, BASICGUNCOF),
	SHOTGUN(SHOTGUNLength, SHOTGUNWidth, SHOTGUNSpeed, SHOTGUNSize, SHOTGUNDamage, SHOTGUNCooldown, SHOTGUNCOF),
	MACHINEGUN(MACHINEGUNLength, MACHINEGUNWidth, MACHINEGUNSpeed, MACHINEGUNSize, MACHINEGUNDamage, MACHINEGUNCooldown, MACHINEGUNCOF),
	RAILGUN(RAILGUNLength, RAILGUNWidth, RAILGUNSpeed, RAILGUNSize, RAILGUNDamage, RAILGUNCooldown, RAILGUNCOF);

	private double wangLength, wangWidth, projectileSpeed, projectileSize, damage, cooldown, COF;

	private GunType(double wangLength, double wangWidth, double projectileSpeed, double projectileSize, double damage, double cooldown, double COF) {
		this.wangLength = wangLength;
		this.wangWidth = wangWidth;
		this.projectileSpeed = projectileSpeed;
		this.projectileSize = projectileSize;
		this.damage = damage;
		this.cooldown = cooldown;
		this.COF = COF;
	}

	public double getWangLength() {
		return wangLength;
	}

	public double getWangWidth() {
		return wangWidth;
	}

	public double getProjectileSpeed() {
		return projectileSpeed;
	}

	public double getProjectileSize() {
		return projectileSize;
	}

	public double getDamage() {
		return damage;
	}

	public double getCooldown() {
		return cooldown;
	}

	public double getCOF() {
		return COF;
	}


}
