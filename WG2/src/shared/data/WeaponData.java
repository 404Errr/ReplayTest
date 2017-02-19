package shared.data;

public interface WeaponData {
	public static boolean ALL_GUNS_AT_START = true;
	public static int STARTING_GUN = 2;

	public static double BASICGUN_LEGNTH = 0.4d, BASICGUN_WIDTH = 0.25d;
	public static double SHOTGUN_LEGNTH = 0.08d, SHOTGUN_WIDTH = 0.55d;
	public static double MACHINEGUN_LEGNTH = 1.2d, MACHINEGUN_WIDTH = 0.5d;
	public static double RAILGUN_LEGNTH = 2.2d, RAILGUN_WIDTH = 0.15d;

	public static double BASICGUN_SIZE = 0.15d, BASICGUN_SPEED = 1.9d, BASICGUN_COF = 2d;
	public static double SHOTGUN_SIZE = 0.15d, SHOTGUN_SPEED = 1.6d, SHOTGUN_COF = 20d;
	public static double MACHINEGUN_SIZE = 0.15d, MACHINEGUN_SPEED = 1.8d, MACHINEGUN_COF = 8d;
	public static double RAILGUN_SIZE = 0.15d, RAILGUN_SPEED = 2d, RAILGUN_COF = 0d;

	public static double BASICGUN_DAMAGE = 10d, BASICGUN_COOLDOWN = 400d;
	public static double SHOTGUN_DAMAGE = 10d, SHOTGUN_COOLDOWN = 900d;
	public static double MACHINEGUN_DAMAGE = 10d, MACHINEGUN_COOLDOWN = 1d;
	public static double RAILGUN_DAMAGE = 10d, RAILGUN_COOLDOWN = 1200d;

	public static double COOLDOWN_INCREMENT = 1d*Data.UPS;
}