package data;

public interface WeaponData {
	static boolean ALL_GUNS_AT_START = true, RECOIL = true;
	static int STARTING_GUN = 0;

	static float WEAPON_SWITCH_COOLDOWN = 30f;

	static float BASICGUN_LEGNTH = 0.4f;
	static float BASICGUN_WIDTH = 0.25f;
	static float BASICGUN_SIZE = 0.15f;
	static float BASICGUN_SPEED = 2.1f;
	static float BASICGUN_COF = 2f;
	static float BASICGUN_SPEED_SPREAD = 0.3f;
	static float BASICGUN_RECOIL = 0.015f;
	static float BASICGUN_COOLDOWN = 10f;
	static float BASICGUN_DAMAGE = 0.22f;

	static float SHOTGUN_LEGNTH = 0.08f;
	static float SHOTGUN_WIDTH = 0.55f;
	static float SHOTGUN_SIZE = 0.15f;
	static float SHOTGUN_SPEED = 1.9f;
	static float SHOTGUN_COF = 2f;
	static float SHOTGUN_SPEED_SPREAD = 1f;
	static float SHOTGUN_SPREAD = 15f;
	static int   SHOTGUN_PELLET_COUNT = 24;
	static float SHOTGUN_RECOIL = 2.4f/SHOTGUN_PELLET_COUNT;
	static float SHOTGUN_COOLDOWN = 35f;
	static float SHOTGUN_DAMAGE = 0.8f/SHOTGUN_PELLET_COUNT;

	static float MACHINEGUN_LEGNTH = 1.2f;
	static float MACHINEGUN_WIDTH = 0.5f;
	static float MACHINEGUN_SIZE = 0.15f;
	static float MACHINEGUN_SPEED = 1.8f;
	static float MACHINEGUN_COF = 8f;
	static float MACHINEGUN_SPEED_SPREAD = 0.8f;
	static float MACHINEGUN_RECOIL = 0.01f;
	static float MACHINEGUN_COOLDOWN = 1f;
	static float MACHINEGUN_DAMAGE = 0.06f;

	static float RAILGUN_LEGNTH = 2.3f;
	static float RAILGUN_WIDTH = 0.2f;
	static float RAILGUN_LINE_INITIAL_WIDTH = 0.18f;
	static float RAILGUN_LINE_DURATION = 0.6f*Data.UPS;
	static float RAILGUN_RECOIL = 0.35f;
	static float RAILGUN_COOLDOWN = 50f;
	static float RAILGUN_DAMAGE = 0.75f;

	static float HITSCAN_INITIAL_INCREMENT = 0.25f, HITSCAN_FINAL_INCREMENT = 0.001f;
	static float COOLDOWN_INCREMENT = 0.01667f*Data.UPS;//don't change (or do, I don't care)

}