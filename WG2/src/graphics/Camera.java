package graphics;

import game.Game;

public class Camera {
	private static double x, y;
	private static boolean lockToPlayer;
	
	public static void init() {
		setLockToPlayer(true);
//		setLockToPlayer(false);
	}
	
	public static void tick() {
		if (lockToPlayer()) {
			setPos(Game.getPlayer().getX(), Game.getPlayer().getY());
		}
		else {
			setPos(0,0);
		}
	}
	
	public static void setPos(double x, double y) {
		Camera.x = x;
		Camera.y = y;		
	}
	
	public static boolean lockToPlayer() {
		return lockToPlayer;
	}

	public static void setLockToPlayer(boolean lockToPlayer) {
		Camera.lockToPlayer = lockToPlayer;
	}

	public static double getX() {
		return x;
	}

	public static void setX(double x) {
		Camera.x = x;
	}

	public static double getY() {
		return y;
	}

	public static void setY(double y) {
		Camera.y = y;
	}
	
	
}
