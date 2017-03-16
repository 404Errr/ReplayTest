package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.FragGrenadeProjectile;
import data.Data;
import data.PlayerData;
import data.WeaponData;
import util.Util;

public class FragGrenade extends Weapon implements WeaponData, Data, PlayerData {

	public FragGrenade(Player owner) {
		super(owner, FRAGGRENADE_GRENADE_RPM, 0, 0);
	}

	@Override
	protected void use() {
		float speed;
		if (owner.highPowerGrenade()) speed = FRAGGRENADE_GRENADE_SPEED_LO;
		else speed = FRAGGRENADE_GRENADE_SPEED_HI;
		float dX = owner.getdX()+Util.getXComp(owner.getFacing(), speed), dY = owner.getdY()-Util.getYComp(owner.getFacing(), speed);
		Game.addEntity(new FragGrenadeProjectile(owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
	}

	@Override
	public String toString() {
		return "Frag Grenade";
	}
}
