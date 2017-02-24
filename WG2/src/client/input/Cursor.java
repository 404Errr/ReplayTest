package client.input;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import client.graphics.Camera;
import client.graphics.Window;
import data.PlayerData;

public class Cursor implements PlayerData {
	private static int screenX, screenY;//the coords of the cursor based on the top left corner of the screen

	public static void updateMouse(MouseEvent e) {
		e = SwingUtilities.convertMouseEvent(Window.getFrame(), e, Window.getRendererer());
		screenX = e.getX();
		screenY = e.getY();
	}

	public static int getScreenX() {//relative to screen 0, 0
		return screenX;
	}

	public static int getScreenY() {
		return screenY;
	}

	public static float getPlayerX() {//relative to player 0, 0
		return (float)screenX/Camera.getScale()-(float)Window.centerX()/Camera.getScale();
	}

	public static float getPlayerY() {
		return (float)screenY/Camera.getScale()-(float)Window.centerY()/Camera.getScale();
	}

	public static float getGridX() {//relative to grid 0, 0 (camera)
		return (float)screenX/Camera.getScale()+Camera.getX()-(float)Window.centerX()/Camera.getScale();
	}

	public static float getGridY() {
		return (float)screenY/Camera.getScale()+Camera.getY()-(float)Window.centerY()/Camera.getScale();
	}
}