package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.FragGrenadeProjectile;
import data.Data;
import data.WeaponData;
import util.Util;

public class FragGrenade extends Weapon implements WeaponData, Data {

	public FragGrenade(Player owner) {
		super(owner, FRAGGRENADE_COOLDOWN, 0, 0);
	}

	@Override
	protected void use() {
		float speed;
		if (owner.highPowerGrenade()) speed = FRAGGRENADE_GRENADE_SPEED_LO;
		else speed = FRAGGRENADE_GRENADE_SPEED_HI;
		float dX = owner.getdX()+Util.getXComp(owner.getFacing(), speed), dY = owner.getdY()-Util.getYComp(owner.getFacing(), speed);
		Game.addEntity(new FragGrenadeProjectile(owner.getColor(), owner.getXCenter()-FRAGGRENADE_GRENADE_SIZE/2, owner.getYCenter()-FRAGGRENADE_GRENADE_SIZE/2, dX, dY));
	}


}
