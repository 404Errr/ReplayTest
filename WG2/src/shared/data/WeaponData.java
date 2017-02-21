package shared.data;

public interface WeaponData {
	public static boolean ALL_GUNS_AT_START = true, RECOIL = true;
	public static int STARTING_GUN = 0;

	public static double BASICGUN_LEGNTH = 0.4d, 	BASICGUN_WIDTH = 0.25d;
	public static double BASICGUN_SIZE = 0.15d, 	BASICGUN_SPEED = 2.1d, 		BASICGUN_COF = 2d, 		BASICGUN_SPEED_SPREAD = 0.3d;
	public static double BASICGUN_DAMAGE = 10d, 	BASICGUN_COOLDOWN = 10d,	BASICGUN_RECOIL = 0.015d;

	public static double SHOTGUN_LEGNTH = 0.08d, 	SHOTGUN_WIDTH = 0.55d;
	public static double SHOTGUN_SIZE = 0.15d, 		SHOTGUN_SPEED = 1.9d, 		SHOTGUN_COF = 2d, 		SHOTGUN_SPEED_SPREAD = 1d,		SHOTGUN_SPREAD = 15d;
	public static int    SHOTGUN_PELLET_COUNT = 12;
	public static double SHOTGUN_DAMAGE = 10d,	 	SHOTGUN_COOLDOWN = 35d,		SHOTGUN_RECOIL = 2.4d/SHOTGUN_PELLET_COUNT;

	public static double MACHINEGUN_LEGNTH = 1.2d, 	MACHINEGUN_WIDTH = 0.5d;
	public static double MACHINEGUN_SIZE = 0.15d, 	MACHINEGUN_SPEED = 1.8d, 	MACHINEGUN_COF = 8d, 	MACHINEGUN_SPEED_SPREAD = 0.8d;
	public static double MACHINEGUN_DAMAGE = 10d, 	MACHINEGUN_COOLDOWN = 1d,	MACHINEGUN_RECOIL = 0.01d;

	public static double RAILGUN_LEGNTH = 2.3d, 	RAILGUN_WIDTH = 0.2d;
	public static double RAILGUN_DAMAGE = 10d, 		RAILGUN_COOLDOWN = 50d,		RAILGUN_RECOIL = 0.35d;
	public static double RAILGUN_LINE_INITIAL_WIDTH = 0.18d, RAILGUN_LINE_DURATION = 0.6d*Data.UPS;

	public static float HITSCAN_INITIAL_INCREMENT = 0.25f, HITSCAN_FINAL_INCREMENT = 0.001f;//, MAX = 1000, HITSCAN_MAX_DISTANCE = Math.min(MAX, Math.max(Level.getWidth(), Level.getHeight())*1.1f);

	public static double COOLDOWN_INCREMENT = 0.01667d*Data.UPS;//dont change (or do, I don't care)

}