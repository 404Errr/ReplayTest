package data;

import client.graphics.Camera;
import client.graphics.Window;

public interface GraphicsData {
	public static final double DEFAULT_SCALE_RATIO = 0.03d;
	public static final double DEFAULT_WINDOW_SCREEN_RATIO = 0.8d;

	public static final boolean AUTO_RENDER_DISTANCE = true;
	public static final int RENDER_DISTANCE_X = -1, RENDER_DISTANCE_Y = -1;

	public static int getRenderDistanceX() {
		if (AUTO_RENDER_DISTANCE) return Window.centerX()/Camera.getScale()+1;
		return RENDER_DISTANCE_X;
	}

	public static int getRenderDistanceY() {
		if (AUTO_RENDER_DISTANCE) return Window.centerY()/Camera.getScale()+1;
		return RENDER_DISTANCE_Y;
	}
}
