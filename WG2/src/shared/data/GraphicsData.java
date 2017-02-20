package shared.data;

import client.graphics.Window;

public interface GraphicsData {
	public static final double DEFAULT_SCALE_RATIO = 0.035d;
	public static final double DEFAULT_WINDOW_SCREEN_RATIO = 0.8d;

	public static final int RENDER_DISTANCE_X = -1, RENDER_DISTANCE_Y = -1;//-1 for auto

	@SuppressWarnings("unused")
	public static int getRenderDistanceX() {
		if (RENDER_DISTANCE_X<0) {
			return Window.getWindowWidth()/Window.getScale()/2+1;
		}
		return RENDER_DISTANCE_X;
	}

	@SuppressWarnings("unused")
	public static int getRenderDistanceY() {
		if (RENDER_DISTANCE_Y<0) {
			return Window.getWindowHeight()/Window.getScale()/2+1;
		}
		return RENDER_DISTANCE_Y;
	}
}
