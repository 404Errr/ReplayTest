package client.weapon;

import client.game.Game;
import client.player.Player;
import data.Data;
import data.WeaponData;
import util.Util;

public class Gun implements Data, WeaponData {
	private GunType type;
	private float cooldown;
	private Player owner;

	public Gun(Player owner, GunType type) {
		this.owner = owner;
		this.type = type;
	}

	public void tick() {
		if (isActive()) {
			if (cooldown>0) {
				cooldown-=COOLDOWN_INCREMENT;
			}
			else {
				cooldown = 0;
				if (owner.getMouseControl(MOUSE1)) {//if should shoot
					switch (type) {
					case RAILGUN:
						shootRailgun();
						break;
					case SHOTGUN:
						shootShotgun();
						break;
					default:
						shoot();
						break;
					}
					cooldown = type.getCooldown();
				}
			}
		}
	}

	private void shootRailgun() {
		Game.addEntity(new Hitscan(type.getDamage(), type.getRecoil(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing()));
//		Game.addEntity(new BouncingHitscan(type.getDamage(), type.getRecoil(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing(), 200));
		/*for (float a = 0;a<360;a+=0.5d) {
			Game.addEntity(new Hitscan(type.getDamage(), type.getRecoil(), 0.5f, owner.getColor(), owner.getXCenter(), owner.getYCenter(), (float)Math.toRadians(a)));
		}*/
		if (RECOIL) owner.recoil(owner.getFacing(), -type.getRecoil());
	}

	private void shootShotgun() {
		float gunAngle = Util.getAngleSpread(owner.getFacing(), type.getCOF());
		for (int i = 0;i<SHOTGUN_PELLET_COUNT;i++) {
			float angle = Util.getAngleSpread(gunAngle, SHOTGUN_SPREAD), speed = Util.getSpread(type.getProjectileSpeed(), type.getSpeedOffset());
			float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
			Game.addEntity(new Projectile(type.getDamage(), type.getRecoil(), type.getProjectileSize(), owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
			if (RECOIL) owner.recoil(owner.getFacing(), -type.getRecoil());
		}
	}

	private void shoot() {
		float angle = Util.getAngleSpread(owner.getFacing(), type.getCOF()), speed = Util.getSpread(type.getProjectileSpeed(), type.getSpeedOffset());
		float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
		Game.addEntity(new Projectile(type.getDamage(), type.getRecoil(), type.getProjectileSize(), owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
		if (RECOIL) owner.recoil(owner.getFacing(), -type.getRecoil());
	}

	private boolean isActive() {
		return owner.getActiveGun()==this;
	}

	public GunType getType() {
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
