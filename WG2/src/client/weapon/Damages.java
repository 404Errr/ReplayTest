package client.weapon;

import client.player.Player;

public interface Damages {
	default void damage(Player player, float damage) {
		player.changeHealth(-damage);
	}
}
