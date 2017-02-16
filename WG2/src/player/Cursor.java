package player;

import java.awt.event.MouseEvent;

import graphics.Camera;
import main.Main;

public class Cursor {
	private static int posX, posY;

	public static void click(MouseEvent e, boolean down) {

	}

	public static void updateMouse(MouseEvent e) {
		posX = e.getX()-3;//offset 3
		posY = e.getY()-25;//offset 25
	}

	public static int getX() {
		return posX;
	}

	public static int getY() {
		return posY;
	}
	
	public static double getGridX() {
		return (double)posX/Main.getScale()+Camera.getX()-Main.getSCREEN_WIDTH()/2d/Main.getScale();
	}

	public static double getGridY() {
		return (double)posY/Main.getScale()+Camera.getY()-Main.getSCREEN_HEIGHT()/2d/Main.getScale();
	}
}