package client.graphics;

import client.game.Game;
import client.input.Cursor;
import shared.data.WindowData;

public class Camera implements WindowData {
	private static double x, y, scaleRatio;
	private static boolean cursorZoom;

	public static void init() {
		scaleRatio = DEFAULT_SCALE_RATIO;
	}

	public static void tick() {
		if (cursorZoom) {
			setPos(Game.getPlayer().getX()+Cursor.getXPlayer(), Game.getPlayer().getY()+Cursor.getYPlayer());
		}
		else {
			setPos(Game.getPlayer().getX(), Game.getPlayer().getY());//set position to player position
		}
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

	public static int getXTile() {
		return (int)Math.round(x);
	}

	public static int getYTile() {
		return (int)Math.round(y);
	}


	public static double getScaleRatio() {
		return scaleRatio;
	}

	public static void changeScaleRatio(double dScaleRatio) {//zoom
		scaleRatio+=dScaleRatio;
	}

	public static boolean cursorZoom() {
		return cursorZoom;
	}

	public static void setCursorZoom(boolean cursorZoom) {
		Camera.cursorZoom = cursorZoom;
	}
}
