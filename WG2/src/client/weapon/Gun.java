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
				System.out.println(owner.getMouseControl(MOUSE1));
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
					if (RECOIL) {
						owner.recoil(type.getRecoil());
					}
					cooldown = type.getCooldown();
				}
			}
		}
	}

	private void shootRailgun() {
		Game.addEntity(new Hitscan(type.getDamage(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), Game.getPlayer().getFacing()));
//		Game.addEntity(new BouncingHitscan(type.getDamage(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), Game.getPlayer().getFacing(), 200));
		/*for (float a = 0;a<360;a+=0.5d) {
			Game.addHitscan(new Hitscan(type.getDamage(), 0.5, owner.getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), Math.toRadians(a)));
		}*/
	}

	private void shootShotgun() {
		float gunAngle = Util.getAngleSpread(Game.getPlayer().getFacing(), type.getCOF());
		for (int i = 0;i<SHOTGUN_PELLET_COUNT;i++) {
			float angle = Util.getAngleSpread(gunAngle, SHOTGUN_SPREAD), speed = Util.getSpread(type.getProjectileSpeed(), type.getSpeedOffset());
			float dX = Game.getPlayer().getdX()+Util.getXComp(angle, speed), dY = Game.getPlayer().getdY()-Util.getYComp(angle, speed);
			Game.addEntity(new Projectile(type.getDamage(), type.getProjectileSize(), owner.getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), dX, dY));
		}
	}

	private void shoot() {
		float angle = Util.getAngleSpread(Game.getPlayer().getFacing(), type.getCOF()), speed = Util.getSpread(type.getProjectileSpeed(), type.getSpeedOffset());
		float dX = Game.getPlayer().getdX()+Util.getXComp(angle, speed), dY = Game.getPlayer().getdY()-Util.getYComp(angle, speed);
		Game.addEntity(new Projectile(type.getDamage(), type.getProjectileSize(), owner.getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), dX, dY));
	}

	private boolean isActive() {
		return Game.getPlayer().getActiveGun()==this;
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
