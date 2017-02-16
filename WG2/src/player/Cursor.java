package player;

import java.awt.event.MouseEvent;

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
}