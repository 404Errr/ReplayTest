package data;

import client.graphics.Camera;
import client.graphics.Window;

public interface GraphicsData {
	WindowType WINDOW_FORMAT = WindowType.WINDOWED_FULLSCREEN;
	enum WindowType {
		BORDERLESS_FULLSCREEN, WINDOWED_FULLSCREEN, WINDOWED
	}

	float DEFAULT_SCALE_RATIO = 0.035f;
	float ZOOM_INCREMENT = 0.001f;
	float DEFAULT_WINDOW_SCREEN_RATIO = 0.8f;

	boolean DRAW_BOUNCE_HIT = false;
	boolean DRAW_PROJECTILE_HIT = false;

	int PROJECTILE_LIFE = 400;

	int HEALTH_BAR_WIDTH = 0;

	boolean AUTO_RENDER_DISTANCE = true;
	int RENDER_DISTANCE_X = -1, RENDER_DISTANCE_Y = -1;

	static int getRenderDistanceX() {
		if (AUTO_RENDER_DISTANCE&&Camera.getScale()>0) return Window.centerX()/Camera.getScale()+1;
		return RENDER_DISTANCE_X;
	}

	static int getRenderDistanceY() {
		if (AUTO_RENDER_DISTANCE&&Camera.getScale()>0) return Window.centerY()/Camera.getScale()+1;
		return RENDER_DISTANCE_Y;
	}
}

