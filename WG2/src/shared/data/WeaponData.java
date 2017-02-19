package shared.data;

import client.level.Level;

public interface WeaponData {
	public static boolean ALL_GUNS_AT_START = true;
	public static int STARTING_GUN = 2;

	public static double BASICGUN_LEGNTH = 0.4d, 	BASICGUN_WIDTH = 0.25d;
	public static double BASICGUN_SIZE = 0.15d, 	BASICGUN_SPEED = 2.5d, 		BASICGUN_COF = 2d, 		BASICGUN_SPEED_OFFSET = 1.5d;
	public static double BASICGUN_DAMAGE = 10d, 	BASICGUN_COOLDOWN = 10d;

	public static double SHOTGUN_LEGNTH = 0.08d, 	SHOTGUN_WIDTH = 0.55d;
	public static double SHOTGUN_SIZE = 0.15d, 		SHOTGUN_SPEED = 1.9d, 		SHOTGUN_COF = 2d, 		SHOTGUN_SPEED_OFFSET = 1d;
	public static double SHOTGUN_DAMAGE = 10d,	 	SHOTGUN_COOLDOWN = 35d;
	public static double SHOTGUN_SPREAD = 15d;
	public static int SHOTGUN_PELLET_COUNT = 10;

	public static double MACHINEGUN_LEGNTH = 1.2d, 	MACHINEGUN_WIDTH = 0.5d;
	public static double MACHINEGUN_SIZE = 0.15d, 	MACHINEGUN_SPEED = 2.2d, 	MACHINEGUN_COF = 8d, 	MACHINEGUN_SPEED_OFFSET = 2d;
	public static double MACHINEGUN_DAMAGE = 10d, 	MACHINEGUN_COOLDOWN = 1d;

	public static double RAILGUN_LEGNTH = 2.3d, 	RAILGUN_WIDTH = 0.2d;
	public static double RAILGUN_DAMAGE = 10d, 		RAILGUN_COOLDOWN = 10d;//50d;
	public static double RAILGUN_INITIAL_WIDTH = 0.18d, RAILGUN_LINE_DURATION = 0.6d*Data.UPS;

	public static double HITSCAN_INCREMENT = 0.005d, HITSCAN_MAX_DISTANCE = Math.max(Level.getWidth(), Level.getHeight())*3d;

	public static double COOLDOWN_INCREMENT = 0.01667d*Data.UPS;//dont change (or do, I don't care)

}