package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.Projectile;
import data.WeaponData;
import util.Util;

public class MachineGun extends Weapon implements WeaponData {
	public MachineGun(Player owner) {
		super(owner, MACHINEGUN_COOLDOWN, MACHINEGUN_LEGNTH, MACHINEGUN_WIDTH);
	}

	@Override
	protected void use() {
		float angle = Util.getAngleSpread(owner.getFacing(), MACHINEGUN_COF), speed = Util.getSpread(MACHINEGUN_SPEED, MACHINEGUN_SPEED_SPREAD);
		float dX = owner.getdX()+Util.getXComp(angle, speed), dY = owner.getdY()-Util.getYComp(angle, speed);
		Game.addEntity(new Projectile(MACHINEGUN_DAMAGE, MACHINEGUN_RECOIL, MACHINEGUN_SIZE, owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
		owner.recoil(owner.getFacing(), -MACHINEGUN_RECOIL);
	}
	
}
