package client.weapon;

import client.game.Game;
import client.player.Player;
import data.Data;
import data.WeaponData;
import util.Util;

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
				cooldown = 0;
				if (owner.getMouseControl(MOUSE1)) {//if should shoot
					if (type==Gun.RAILGUN) {
						shootRailgun();
						cooldown = ((Gun) type).getCooldown();
						if (RECOIL) owner.recoil(owner.getFacing(), -((Gun) type).getRecoil());
					}
					else if (type==Gun.SHOTGUN) {
						shootShotgun();
						cooldown = ((Gun) type).getCooldown();
						if (RECOIL) owner.recoil(owner.getFacing(), -((Gun) type).getRecoil());
					}
					else if (type==Grenade.FRAGGRENADE) {
						useGrenade();
						cooldown = ((Grenade) type).getCooldown();
					}
					else {
						shoot();
						cooldown = ((Gun) type).getCooldown();
						if (RECOIL) owner.recoil(owner.getFacing(), -((Gun) type).getRecoil());
					}
				}
			}
		}
	}

	private void useGrenade() {
		float angle = owner.getFacing(), speed = ((Grenade) type).getGrenadeSpeed();
		float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
		Game.addEntity(new FragGrenade(owner.getColor(), owner.getXCenter()-((Grenade) type).getGrenadeSize()/2, owner.getYCenter()-((Grenade) type).getGrenadeSize()/2, dX, dY, ((Grenade) type).getGrenadeSize(), ((Grenade) type).getTimer()));
	}

	private void shootRailgun() {
		Game.addEntity(new Hitscan(((Gun) type).getDamage(), ((Gun) type).getRecoil(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing(), false));
//		Game.addEntity(new BouncingHitscan(type.getDamage(), type.getRecoil(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing(), 200));
		/*for (float a = 0;a<360;a+=0.5d) {
			Game.addEntity(new Hitscan(type.getDamage(), type.getRecoil(), 0.5f, owner.getColor(), owner.getXCenter(), owner.getYCenter(), (float)Math.toRadians(a), false));
		}*/
	}

	private void shootShotgun() {
		float gunAngle = Util.getAngleSpread(owner.getFacing(), ((Gun) type).getCOF());
		for (int i = 0;i<SHOTGUN_PELLET_COUNT;i++) {
			float angle = Util.getAngleSpread(gunAngle, SHOTGUN_SPREAD), speed = Util.getSpread(((Gun) type).getProjectileSpeed(), ((Gun) type).getSpeedOffset());
			float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
			Game.addEntity(new Projectile(((Gun) type).getDamage(), ((Gun) type).getRecoil(), ((Gun) type).getProjectileSize(), owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
		}
	}

	private void shoot() {
		float angle = Util.getAngleSpread(owner.getFacing(), ((Gun) type).getCOF()), speed = Util.getSpread(((Gun) type).getProjectileSpeed(), ((Gun) type).getSpeedOffset());
		float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
		Game.addEntity(new Projectile(((Gun) type).getDamage(), ((Gun) type).getRecoil(), ((Gun) type).getProjectileSize(), owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
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
