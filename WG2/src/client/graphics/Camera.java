package client.graphics;

import client.game.Game;
import data.WindowData;

public class Camera implements WindowData {
	private static double x, y, scaleRatio;

	public static void init() {
		scaleRatio = DEFAULT_SCALE_RATIO;
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

	public static double getY() {
		return y;
	}

	public static double getScaleRatio() {
		return scaleRatio;
	}

	public static void changeScaleRatio(double dScaleRatio) {//"zoom"
		scaleRatio+=dScaleRatio;
	}

}
