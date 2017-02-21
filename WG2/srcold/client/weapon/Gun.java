package client.weapon;

import client.game.Game;
import client.input.Input;
import client.projectile.Hitscan;
import client.projectile.Projectile;
import shared.data.WeaponData;
import shared.util.Util;

public class Gun implements WeaponData {
	private GunType type;
	private double cooldown;

	public Gun(GunType type) {
		this.type = type;
	}

	public void tick() {
		if (isActive()) {
			if (cooldown>0) {
				cooldown-=COOLDOWN_INCREMENT;
			}
			else {
				cooldown = 0;
				if (Input.isMouse1Down()) {//if should shoot
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
					if (RECOIL) {
						Game.getPlayer().recoil(type.getRecoil());
					}
					cooldown = type.getCooldown();
				}
			}
		}
	}

	private void shootRailgun() {
		Game.addHitscan(new Hitscan(type.getDamage(), RAILGUN_LINE_INITIAL_WIDTH, Game.getPlayer().getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), Game.getPlayer().getFacing()));
		/*for (double a = 0;a<360;a+=0.5d) {
			Game.addHitscan(new Hitscan(type.getDamage(), 0.5, Game.getPlayer().getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), Math.toRadians(a)));
		}*/
	}

	private void shootShotgun() {
		double gunAngle = Util.getAngleSpread(Game.getPlayer().getFacing(), type.getCOF());
		for (int i = 0;i<SHOTGUN_PELLET_COUNT;i++) {
			double angle = Util.getAngleSpread(gunAngle, SHOTGUN_SPREAD), speed = Util.getSpread(type.getProjectileSpeed(), type.getSpeedOffset());
			double dX = Game.getPlayer().getdX()+Util.getXComp(angle, speed), dY = Game.getPlayer().getdY()-Util.getYComp(angle, speed);
			Game.addProjectile(new Projectile(type.getDamage(), type.getProjectileSize(), Game.getPlayer().getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), dX, dY));
		}
	}

	private void shoot() {
		double angle = Util.getAngleSpread(Game.getPlayer().getFacing(), type.getCOF()), speed = Util.getSpread(type.getProjectileSpeed(), type.getSpeedOffset());
		double dX = Game.getPlayer().getdX()+Util.getXComp(angle, speed), dY = Game.getPlayer().getdY()-Util.getYComp(angle, speed);
		Game.addProjectile(new Projectile(type.getDamage(), type.getProjectileSize(), Game.getPlayer().getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), dX, dY));
	}

	private boolean isActive() {
		return Game.getPlayer().getActiveGun()==this;
	}

	public GunType getType() {
		return type;
	}

	public double getCooldown() {
		return cooldown;
	}

	@Override
	public String toString() {
		return type.toString();
	}


}
