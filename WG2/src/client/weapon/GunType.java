package client.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shared.data.WeaponData;

public enum GunType implements WeaponData {
	BASICGUN(BASICGUN_LEGNTH, BASICGUN_WIDTH, BASICGUN_SPEED, BASICGUN_SIZE, BASICGUN_DAMAGE, BASICGUN_COOLDOWN, BASICGUN_COF, BASICGUN_SPEED_OFFSET),
	SHOTGUN(SHOTGUN_LEGNTH, SHOTGUN_WIDTH, SHOTGUN_SPEED, SHOTGUN_SIZE, SHOTGUN_DAMAGE, SHOTGUN_COOLDOWN, SHOTGUN_COF, SHOTGUN_SPEED_OFFSET),
	MACHINEGUN(MACHINEGUN_LEGNTH, MACHINEGUN_WIDTH, MACHINEGUN_SPEED, MACHINEGUN_SIZE, MACHINEGUN_DAMAGE, MACHINEGUN_COOLDOWN, MACHINEGUN_COF, MACHINEGUN_SPEED_OFFSET),
	RAILGUN(RAILGUN_LEGNTH, RAILGUN_WIDTH, 1, 0, RAILGUN_DAMAGE, RAILGUN_COOLDOWN, 0, 0);

	private static List<GunType> types;
	static {
		types = new ArrayList<>();
		types.addAll(Arrays.asList(BASICGUN,SHOTGUN,MACHINEGUN,RAILGUN));
		System.out.println(COOLDOWN_INCREMENT);
	}

	private double wangLength, wangWidth, projectileSpeed, projectileSize, damage, cooldown, COF, speedOffset;

	private GunType(double wangLength, double wangWidth, double projectileSpeed, double projectileSize, double damage, double cooldown, double COF, double speedOffset) {
		this.wangLength = wangLength;
		this.wangWidth = wangWidth;
		this.projectileSpeed = projectileSpeed;
		this.projectileSize = projectileSize;
		this.damage = damage;
		this.cooldown = cooldown;
		this.COF = COF;
		this.speedOffset = speedOffset;
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

	public double getSpeedOffset() {
		return speedOffset;
	}

	public static List<GunType> getTypes() {
		return types;
	}


}
