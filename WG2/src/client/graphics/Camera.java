package client.graphics;

import client.game.Game;
import client.input.Cursor;
import data.GraphicsData;

public class Camera implements GraphicsData {
	private static float x, y, scaleRatio;
	private static boolean zoom;
	private static float scale;

	public static final int IN = 1, OUT = -1;
	private static final int SMART_ZOOM_START = 40;
	
	public static void init() {
		scaleRatio = DEFAULT_SCALE_RATIO;
	}

	public static void tick() {
		if (zoom) {
			setPos(Game.getPlayer().getX()+Cursor.getPlayerX(), Game.getPlayer().getY()+Cursor.getPlayerY());//set position to "half" of the distance between the player and cursor
		}
		else {
			setPos(Game.getPlayer().getX(), Game.getPlayer().getY());//set position to player position
		}
	}

	public static void updateScale() {
		scale = Window.getFrame().getHeight()*Camera.getScaleRatio();
		if (scale%2==0) scale+=1;
		System.out.println(scale);
	}

	public static int getScale() {
		return Math.round(scale);
	}

	public static void setPos(float x, float y) {
		Camera.x = x;
		Camera.y = y;
	}
	
	public static float getX() {
		return x;
	}
	
	public static float getY() {
		return y;
	}
	
	public static int getXTile() {
		return Math.round(getX());
	}

	public static int getYTile() {
		return Math.round(getY());
	}

	public static float getScaleRatio() {
		return scaleRatio;
	}

	public static void changeScaleRatio(int direction) {//zoom
		float dScaleRatio = direction*ZOOM_INCREMENT;
		if (scale>SMART_ZOOM_START) dScaleRatio = (float) (-direction*((0.0008f*Math.pow(1/scale, 2)+Math.log(1/scale))*ZOOM_INCREMENT));
		scaleRatio+=dScaleRatio;
		System.out.println("scale "+scale+"\tscale ratio "+scaleRatio+"\tdscaleratio "+dScaleRatio);
	}

	public static boolean isZoomed() {
		return zoom;
	}

	public static void setZoom(boolean cursorZoom) {
		Camera.zoom = cursorZoom;
	}
}
