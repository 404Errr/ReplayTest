package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.Hitscan;
import data.WeaponData;

public class RailGun extends Weapon implements WeaponData {

	public RailGun(Player owner) {
		super(owner, RAILGUN_COOLDOWN, RAILGUN_LEGNTH, RAILGUN_WIDTH);
	}

	@Override
	protected void use() {
		Game.addEntity(new Hitscan(RAILGUN_DAMAGE, RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing(), false));
//		Game.addEntity(new BouncingHitscan(type.getDamage(), type.getRecoil(), RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing(), 200));
		/*for (float a = 0;a<360;a+=0.5d) {
			Game.addEntity(new Hitscan(type.getDamage(), type.getRecoil(), 0.5f, owner.getColor(), owner.getXCenter(), owner.getYCenter(), (float)Math.toRadians(a), false));
		}*/
	}

}
