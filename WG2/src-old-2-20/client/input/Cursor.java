package client.input;

import java.awt.event.MouseEvent;

import client.graphics.Camera;
import client.graphics.Window;
import shared.data.PlayerData;

public class Cursor implements PlayerData {
	private static int x, y;//the coords of the cursor based on the top left corner of the screen
	private static double gridX, gridY;//the coords of the cursor based on the grid (probably centered on tile)
	private static double playerX, playerY;//the coords of the cursor based on the player

	public static void updateMouse(MouseEvent e) {
		x = e.getX()-3-5;//offset 3 and 5
		y = e.getY()-25-5;//offset 25 and 5
	}

	public static void tick() {
		gridX = (double)x/Window.getScale()+Camera.getX()-Window.getWindowWidth()/2d/Window.getScale();
		gridY = (double)y/Window.getScale()+Camera.getY()-Window.getWindowHeight()/2d/Window.getScale();
		playerX = (double)x/Window.getScale()-Window.getWindowWidth()/2d/Window.getScale();
		playerY = (double)y/Window.getScale()-Window.getWindowHeight()/2d/Window.getScale();
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static double getXGrid() {
		return gridX;
	}

	public static double getYGrid() {
		return gridY;
	}

	public static double getXPlayer() {
		return playerX;
	}

	public static double getYPlayer() {
		return playerY;
	}


}