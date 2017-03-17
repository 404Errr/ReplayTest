package client.weapon.weapon;

import client.game.Game;
import client.player.Player;
import client.weapon.Weapon;
import client.weapon.entity.BouncingHitscan;
import client.weapon.entity.Hitscan;
import data.WeaponData;

public class RailGun extends Weapon implements WeaponData {

	public RailGun(Player owner) {
		super(owner, RAILGUN_RPM, RAILGUN_LEGNTH, RAILGUN_WIDTH);
	}

	@Override
	protected void use() {
		switch (RAILGUN_SHOOTING_TYPE) {
		case NORMAL:
			Game.addEntity(new Hitscan(RAILGUN_DAMAGE, RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing()));
			owner.recoil(owner.getFacing(), -RAILGUN_RECOIL);
			break;
		case BOUNCING:
			Game.addEntity(new BouncingHitscan(RAILGUN_LINE_INITIAL_WIDTH, owner.getColor(), owner.getXCenter(), owner.getYCenter(), owner.getFacing(), 200));
			break;
		case AROUND:
			for (float a = 0;a<360;a+=0.5d) {
				Game.addEntity(new Hitscan(0, 0.25f, owner.getColor(), owner.getXCenter(), owner.getYCenter(), (float)Math.toRadians(a)));
			}
			break;
		}
	}

	@Override
	public String toString() {
		return "Rail Gun";
	}
}
