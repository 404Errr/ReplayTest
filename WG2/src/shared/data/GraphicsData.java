package shared.data;

import client.graphics.Window;

public interface GraphicsData {
	public static final double DEFAULT_SCALE_RATIO = 0.035d;
	public static final double DEFAULT_WINDOW_SCREEN_RATIO = 0.8d;

	public static final boolean AUTO_RENDER_DISTANCE = true;
	public static final int RENDER_DISTANCE_X = -1, RENDER_DISTANCE_Y = -1;

	public static int getRenderDistanceX() {
		if (AUTO_RENDER_DISTANCE) return Window.getWindowWidth()/Window.getScale()/2+1;
		return RENDER_DISTANCE_X;
	}

	public static int getRenderDistanceY() {
		if (AUTO_RENDER_DISTANCE) return Window.getWindowHeight()/Window.getScale()/2+1;
		return RENDER_DISTANCE_Y;
	}
}
