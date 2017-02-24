package data;

import client.graphics.Camera;
import client.graphics.Window;

public interface GraphicsData {
	static final boolean FULLSCREEN = true;

	static final float DEFAULT_SCALE_RATIO = 0.035f;
	static final float ZOOM_INCREMENT = 0.001f;
	static final float DEFAULT_WINDOW_SCREEN_RATIO = 0.8f;

	static final boolean AUTO_RENDER_DISTANCE = true;
	static final int RENDER_DISTANCE_X = -1, RENDER_DISTANCE_Y = -1;

	static int getRenderDistanceX() {
		if (AUTO_RENDER_DISTANCE&&Camera.getScale()>0) return Window.centerX()/Camera.getScale()+1;
		return RENDER_DISTANCE_X;
	}

	static int getRenderDistanceY() {
		if (AUTO_RENDER_DISTANCE&&Camera.getScale()>0) return Window.centerY()/Camera.getScale()+1;
		return RENDER_DISTANCE_Y;
	}
}
