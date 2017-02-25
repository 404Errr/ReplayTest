package client.weapon;

import data.WeaponData;

public enum Grenade implements WeaponData {
	FRAGGRENADE(FRAGGRENADE_GRENADE_SPEED, FRAGGRENADE_SPEED, FRAGGRENADE_GRENADE_SIZE, FRAGGRENADE_SIZE, FRAGGRENADE_DAMAGE, FRAGGRENADE_COOLDOWN, FRAGGRENADE_RANGE, FRAGGRENADE_TIMER, FRAGGRENADE_SHARD_SPEED_SPREAD, FRAGGRENADE_SHARD_COUNT);

	private float grenadeSpeed;
	private float shardSpeed;
	private float grenadeSize;
	private float shardSize;
	private float damage;
	private float cooldown;
	private float range;
	private int timer;
	private float shardSpeedSpread;
	private int shardCount;

	private Grenade(float grenadeSpeed, float shardSpeed, float grenadeSize, float shardSize, float damage, float cooldown, float range, int timer, float shardSpeedSpread, int shardCount) {
		this.grenadeSpeed = grenadeSpeed;
		this.shardSpeed = shardSpeed;
		this.grenadeSize = grenadeSize;
		this.shardSize = shardSize;
		this.damage = damage;
		this.cooldown = cooldown;
		this.range = range;
		this.timer = timer;
		this.shardSpeedSpread = shardSpeedSpread;
		this.shardCount = shardCount;

	}

	public float getGrenadeSpeed() {
		return grenadeSpeed;
	}

	public float getShardSpeed() {
		return shardSpeed;
	}

	public float getGrenadeSize() {
		return grenadeSize;
	}

	public float getShardSize() {
		return shardSize;
	}

	public float getDamage() {
		return damage;
	}

	public float getCooldown() {
		return cooldown;
	}

	public float getRange() {
		return range;
	}

	public int getTimer() {
		return timer;
	}

	public float getShardSpeedSpread() {
		return shardSpeedSpread;
	}

	public int getShardCount() {
		return shardCount;
	}

}
