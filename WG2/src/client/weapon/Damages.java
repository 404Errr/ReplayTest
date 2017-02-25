package client.weapon;

import client.player.Player;

public interface Damages {
	default void damage(Player player, float damage) {//temp TODO
		int type = 0;
		if (damage==GunType.BASICGUN_DAMAGE) {
			type = 0;
		}
		else if (damage==GunType.SHOTGUN_DAMAGE) {
			type = 1;
		}
		else if (damage==GunType.MACHINEGUN_DAMAGE) {
			type = 2;
		}
		else if (damage==GunType.RAILGUN_DAMAGE) {
			type = 3;
		}
		player.changeHealth(-damage, type);
	}

//	default void damage(Player player, float damage) {//TODO
//		player.changeHealth(-damage);
//	}
}
