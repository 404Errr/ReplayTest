package client.input;

import java.awt.event.MouseEvent;

import client.graphics.Camera;
import client.graphics.Window;
import data.PlayerData;

public class Cursor implements PlayerData {
	private static int screenX, screenY;//the coords of the cursor based on the top left corner of the screen

	public static void updateMouse(MouseEvent e) {
		screenX = e.getX()-3-5;//offset 3 and 5
		screenY = e.getY()-25-5;//offset 25 and 5
	}

	public static int getScreenX() {//relative to screen 0, 0
		return screenX;
	}

	public static int getScreenY() {
		return screenY;
	}

//	playerX = (double)x/Window.getScale()-Window.getWindowWidth()/2d/Window.getScale();
	public static double getPlayerX() {//relative to player 0, 0
		if (!Camera.inZoom()) return 0;
		return (double)screenX/Camera.getScale()-(double)Window.centerX()/Camera.getScale();
	}

	public static double getPlayerY() {
		if (!Camera.inZoom()) return 0;
		return (double)screenY/Camera.getScale()-(double)Window.centerY()/Camera.getScale();
	}

//	gridX = (double)x/Window.getScale()+Camera.getX()-Window.getWindowWidth()/2d/Window.getScale();
	public static double getGridX() {//relative to grid 0, 0
		return (double)screenX/Camera.getScale()+Camera.getX()-(double)Window.centerX()/Camera.getScale();
	}

	public static double getGridY() {
		return (double)screenY/Camera.getScale()+Camera.getY()-(double)Window.centerY()/Camera.getScale();
	}
}