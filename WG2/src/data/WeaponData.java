package data;

public interface WeaponData {
	public static boolean ALL_GUNS_AT_START = true, RECOIL = true;
	public static int STARTING_GUN = 0;

	public static float BASICGUN_LEGNTH = 0.4f, 	BASICGUN_WIDTH = 0.25f;
	public static float BASICGUN_SIZE = 0.15f, 		BASICGUN_SPEED = 2.1f, 		BASICGUN_COF = 2f, 		BASICGUN_SPEED_SPREAD = 0.3f;
	public static float BASICGUN_DAMAGE = 10f, 		BASICGUN_COOLDOWN = 10f,	BASICGUN_RECOIL = 0.015f;

	public static float SHOTGUN_LEGNTH = 0.08f, 	SHOTGUN_WIDTH = 0.55f;
	public static float SHOTGUN_SIZE = 0.15f, 		SHOTGUN_SPEED = 1.9f, 		SHOTGUN_COF = 2f, 		SHOTGUN_SPEED_SPREAD = 1f,		SHOTGUN_SPREAD = 15f;
	public static int    SHOTGUN_PELLET_COUNT = 12;
	public static float SHOTGUN_DAMAGE = 10f,	 	SHOTGUN_COOLDOWN = 35f,		SHOTGUN_RECOIL = 2.4f/SHOTGUN_PELLET_COUNT;

	public static float MACHINEGUN_LEGNTH = 1.2f, 	MACHINEGUN_WIDTH = 0.5f;
	public static float MACHINEGUN_SIZE = 0.15f, 	MACHINEGUN_SPEED = 1.8f, 	MACHINEGUN_COF = 8f, 	MACHINEGUN_SPEED_SPREAD = 0.8f;
	public static float MACHINEGUN_DAMAGE = 10f, 	MACHINEGUN_COOLDOWN = 1f,	MACHINEGUN_RECOIL = 0.01f;

	public static float RAILGUN_LEGNTH = 2.3f, 		RAILGUN_WIDTH = 0.2f;
	public static float RAILGUN_DAMAGE = 10f, 		RAILGUN_COOLDOWN = 50f,		RAILGUN_RECOIL = 0.35f;
	public static float RAILGUN_LINE_INITIAL_WIDTH = 0.18f, RAILGUN_LINE_DURATION = 0.6f*Data.UPS;

	public static float HITSCAN_INITIAL_INCREMENT = 0.25f, HITSCAN_FINAL_INCREMENT = 0.001f;//, MAX = 1000, HITSCAN_MAX_DISTANCE = Math.min(MAX, Math.max(Level.getWidth(), Level.getHeight())*1.1f);

	public static float COOLDOWN_INCREMENT = 0.01667f*Data.UPS;//dontt change (or do, I don't care)

}