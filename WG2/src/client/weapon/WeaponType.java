package client.weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WeaponType {
	GUN, GRENADE;

	private static List<Weapon> weaponTypes;

	static {
		weaponTypes = new ArrayList<>();
		weaponTypes.addAll(Arrays.asList(Gun.BASICGUN, Gun.SHOTGUN, Gun.MACHINEGUN, Gun.RAILGUN, Grenade.FRAGGRENADE));
	}

	public static List<Weapon> getTypes() {
		return weaponTypes;
	}
}
