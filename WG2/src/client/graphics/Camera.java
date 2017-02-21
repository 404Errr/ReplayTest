package client.graphics;

import client.game.Game;
import client.input.Cursor;
import data.GraphicsData;

public class Camera implements GraphicsData {
	private static double x, y, scaleRatio;
	private static boolean zoom;
	private static int scale;

	public static void init() {
		scaleRatio = DEFAULT_SCALE_RATIO;
	}

	public static void tick() {
		if (zoom) {
			setPos(Game.getPlayer().getX()+Cursor.getPlayerX(), Game.getPlayer().getY()+Cursor.getPlayerY());
		}
		else {
			setPos(Game.getPlayer().getX(), Game.getPlayer().getY());//set position to player position
		}
	}

	public static void updateScale() {//can go below 0 (potential bug)
		scale = (int)(Math.min(Window.getFrame().getWidth(), Window.getFrame().getHeight())*Camera.getScaleRatio());
	}

	public static int getScale() {
		return scale;
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

	public static boolean inZoom() {
		return zoom;
	}

	public static void setZoom(boolean cursorZoom) {
		Camera.zoom = cursorZoom;
	}
}
