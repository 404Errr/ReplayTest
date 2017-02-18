package client.graphics;

import client.game.Game;

public class Camera {
	private static double x, y;

	public static void init() {
		//not used
	}

	public static void tick() {
		setPos(Game.getPlayer().getX(), Game.getPlayer().getY());//set position to player position
	}

	public static void setPos(double x, double y) {
		Camera.x = x;
		Camera.y = y;
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
