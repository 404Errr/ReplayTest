package data;

import client.graphics.Camera;
import client.graphics.Window;

public interface GraphicsData {
	public static final float DEFAULT_SCALE_RATIO = 0.035f;
	public static final float ZOOM_INCREMENT = 0.001f;
	public static final float DEFAULT_WINDOW_SCREEN_RATIO = 0.8f;

	public static final boolean AUTO_RENDER_DISTANCE = true;
	public static final int RENDER_DISTANCE_X = -1, RENDER_DISTANCE_Y = -1;

	public static int getRenderDistanceX() {
		if (AUTO_RENDER_DISTANCE&&Camera.getScale()>0) return Window.centerX()/Camera.getScale()+1;
		return RENDER_DISTANCE_X;
	}

	public static int getRenderDistanceY() {
		if (AUTO_RENDER_DISTANCE&&Camera.getScale()>0) return Window.centerY()/Camera.getScale()+1;
		return RENDER_DISTANCE_Y;
	}
}
