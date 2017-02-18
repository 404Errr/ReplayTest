package client.input;

import java.awt.event.MouseEvent;

import client.game.Game;
import client.graphics.Camera;
import client.graphics.Window;
import client.projectile.Projectile;

public class Cursor {
	private static int x, y;//the coords of the cursor based on the screen
	private static double gridX, gridY;//the coords of the cursor based on the grid

	public static void click(MouseEvent e, boolean down) {
		Game.addProjectile(new Projectile(Game.getPlayer().getX(), Game.getPlayer().getY(), 1, 1));//temp
	}

	public static void updateMouse(MouseEvent e) {
		x = e.getX()-3;//offset 3
		y = e.getY()-25;//offset 25
	}

	public static void tick() {
		gridX = (double)x/Window.getScale()+Camera.getX()-Window.getWindowWidth()/2d/Window.getScale();
		gridY = (double)y/Window.getScale()+Camera.getY()-Window.getWindowHeight()/2d/Window.getScale();
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static double getGridX() {
		return gridX;
	}

	public static double getGridY() {
		return gridY;
	}
}