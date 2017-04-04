package data;

import client.graphics.Camera;
import client.graphics.Window;

public interface GraphicsData {
	WindowType WINDOW_FORMAT = WindowType.WINDOWED;
//	WindowType WINDOW_FORMAT = WindowType.WINDOWED_FULLSCREEN;
//	WindowType WINDOW_FORMAT = WindowType.BORDERLESS_FULLSCREEN;
	
	enum WindowType {
		BORDERLESS_FULLSCREEN, WINDOWED_FULLSCREEN, WINDOWED
	}

	float DEFAULT_SCALE_RATIO = 0.035f;
	float ZOOM_INCREMENT = 0.001f;
	float DEFAULT_WINDOW_SCREEN_RATIO = 0.8f;

	boolean DRAW_TILE_COORDS = false;
	boolean DRAW_BOUNCE_HIT = true;
	boolean DRAW_PROJECTILE_HIT = false;

	int PROJECTILE_LIFE = 400;

	int HEALTH_BAR_WIDTH = 20;
	
	float INVENTORY_Y = 0.8f;
	float INVENTORY_SIZE = 0.05f;
	float INVENTORY_GAP = INVENTORY_SIZE*0.4f;
	float INVENTORY_HIGHLIGHT_SIZE = 1.5f;

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

