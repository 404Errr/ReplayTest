package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.Projectile;
import data.WeaponData;
import util.Util;

public class ShotGun extends Weapon implements WeaponData {

	public ShotGun(Player owner) {
		super(owner, SHOTGUN_COOLDOWN, SHOTGUN_LEGNTH, SHOTGUN_WIDTH);
	}

	@Override
	protected void use() {
		float gunAngle = Util.getAngleSpread(owner.getFacing(), SHOTGUN_COF);
		for (int i = 0;i<SHOTGUN_PELLET_COUNT;i++) {
			float angle = Util.getAngleSpread(gunAngle, SHOTGUN_SPREAD), speed = Util.getSpread(SHOTGUN_SPEED, SHOTGUN_SPEED_SPREAD);
			float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
			Game.addEntity(new Projectile(SHOTGUN_DAMAGE, SHOTGUN_RECOIL, SHOTGUN_SIZE, owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
		}
		owner.recoil(owner.getFacing(), -SHOTGUN_RECOIL);
	}

}
