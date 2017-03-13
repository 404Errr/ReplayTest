package client.graphics;

import client.game.Game;
import client.input.Cursor;
import data.GraphicsData;

public class Camera implements GraphicsData {
	private static float x, y, scaleRatio;
	private static boolean zoom;
	private static float scale;

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

	public static void updateScale() {//can go below 0 (potential bug)
		scale = (int)(Math.min(Window.getFrame().getWidth(), Window.getFrame().getHeight())*Camera.getScaleRatio());
		if (scale%2==0) scale+=1;
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
//		scaleRatio+=Math.signum(direction)*ZOOM_INCREMENT;
		float dScaleRatio = (float) (-Math.signum(direction)*((0.0008f*Math.pow(scaleRatio*ZOOM_INCREMENT, 2)+Math.log(scaleRatio*ZOOM_INCREMENT))*ZOOM_INCREMENT));
		if (Math.abs(dScaleRatio)<ZOOM_INCREMENT) dScaleRatio = Math.signum(direction)*ZOOM_INCREMENT;
		scaleRatio+=dScaleRatio;
		if (scaleRatio<=0) scaleRatio = ZOOM_INCREMENT/2;
	}

	public static boolean isZoomed() {
		return zoom;
	}

	public static void setZoom(boolean cursorZoom) {
		Camera.zoom = cursorZoom;
	}
}
