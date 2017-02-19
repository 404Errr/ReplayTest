package shared.data;

public interface WeaponData {
	public static double BASICGUNLength = 0.5d, BASICGUNWidth = 0.2d;
	public static double SHOTGUNLength = 0.5d, SHOTGUNWidth = 0.2d;
	public static double MACHINEGUNLength = 0.5d, MACHINEGUNWidth = 0.2d;
	public static double RAILGUNLength = 0.5d, RAILGUNWidth = 0.2d;

	public static double BASICGUNSize = 0.15d, BASICGUNSpeed = 2d, BASICGUNCOF = 2d;
	public static double SHOTGUNSize = 0.15d, SHOTGUNSpeed = 2d, SHOTGUNCOF = 2d;
	public static double MACHINEGUNSize = 0.15d, MACHINEGUNSpeed = 2d, MACHINEGUNCOF = 2d;
	public static double RAILGUNSize = 0.15d, RAILGUNSpeed = 2d, RAILGUNCOF = 2d;

	public static double BASICGUNDamage = 10d, BASICGUNCooldown = 300d;
	public static double SHOTGUNDamage = 10d, SHOTGUNCooldown = 300d;
	public static double MACHINEGUNDamage = 10d, MACHINEGUNCooldown = 300d;
	public static double RAILGUNDamage = 10d, RAILGUNCooldown = 300d;
}