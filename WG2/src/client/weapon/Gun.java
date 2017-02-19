package client.weapon;

import client.game.Game;
import client.input.Input;
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
					shoot();
					cooldown = type.getCooldown();
				}
			}
		}
	}

	public void shoot() {
		double angle = Util.getAngleSpread(Game.getPlayer().getFacing(), type.getCOF()), speed = Util.getSpread(type.getProjectileSpeed(), type.getCOF()/10);
		double dX = Game.getPlayer().getdX()+Util.getXComp(angle, speed), dY = Game.getPlayer().getdY()-Util.getYComp(angle, speed);
		Game.addProjectile(new Projectile(type.getDamage(), type.getProjectileSize(), Game.getPlayer().getColor(), Game.getPlayer().getXCenter(), Game.getPlayer().getYCenter(), dX, dY));
	}

	private boolean isActive() {
		return Game.getPlayer().getActiveGun()==this;
	}

	public GunType getType() {
		return type;
	}



	@Override
	public String toString() {
		return type.toString();
	}


}
