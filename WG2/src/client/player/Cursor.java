package client.player;

import java.awt.event.MouseEvent;

import client.game.Game;
import client.graphics.Camera;
import client.main.Main;
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
		gridX = (double)x/Main.getScale()+Camera.getX()-Main.getSCREEN_WIDTH()/2d/Main.getScale();
		gridY = (double)y/Main.getScale()+Camera.getY()-Main.getSCREEN_HEIGHT()/2d/Main.getScale();
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