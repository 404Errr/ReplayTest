package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.Projectile;
import data.WeaponData;
import util.Util;

public class ShotGun extends Weapon implements WeaponData {

	public ShotGun(Player owner) {
		super(owner, SHOTGUN_RPM, SHOTGUN_LEGNTH, SHOTGUN_WIDTH);
	}

	@Override
	protected void use() {//FIXME
		float gunAngle = Util.getAngleSpread(owner.getFacing(), SHOTGUN_COF);
		for (int i = 0;i<SHOTGUN_PELLET_COUNT;i++) {
			float angle = Util.getAngleSpread(gunAngle, SHOTGUN_PELLET_COF), speed = Util.getSpread(SHOTGUN_PELLET_SPEED, SHOTGUN_PELLET_SPEED_SPREAD);
			float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
			float startingOffset = Util.getSpread(PLAYER_SIZE);
			float x = owner.getXCenter()+Util.getXComp(angle, startingOffset), y = owner.getYCenter()-Util.getYComp(angle, startingOffset);
			Game.addEntity(new Projectile(SHOTGUN_PELLET_DAMAGE, SHOTGUN_RECOIL, SHOTGUN_PELLET_SIZE, owner.getColor(), x, y, dX, dY));
		}
		owner.recoil(owner.getFacing(), -SHOTGUN_RECOIL);
	}

	@Override
	public String toString() {
		return "Shot Gun";
	}
}
