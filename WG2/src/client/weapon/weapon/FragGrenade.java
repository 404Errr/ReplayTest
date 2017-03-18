package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.FragGrenadeProjectile;
import data.GameData;
import data.PlayerData;
import data.WeaponData;
import util.Util;

public class FragGrenade extends Weapon implements WeaponData, GameData, PlayerData {

	public FragGrenade(Player owner) {
		super(owner, FRAGGRENADE_GRENADE_RPM);
	}

	@Override
	protected void use() {
		float speed;
		if (owner.shiftControl()) speed = FRAGGRENADE_GRENADE_SPEED_LO;
		else speed = FRAGGRENADE_GRENADE_SPEED_HI;
		float dX = owner.getdX()+Util.getXComp(owner.getFacing(), speed), dY = owner.getdY()-Util.getYComp(owner.getFacing(), speed);
		Game.addEntity(new FragGrenadeProjectile(owner.getColor(), owner.getXCenter(), owner.getYCenter(), dX, dY));
	}

	@Override
	public String toString() {
		return "Frag Grenade";
	}
}
