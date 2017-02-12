package player;

import java.awt.event.MouseEvent;

public class Cursor {
	private static int x, y;

	public static void click(MouseEvent e, boolean down) {

	}

	public static void updateMouse(MouseEvent e) {
		x = e.getX()-3;//offset 3
		y = e.getY()-25;//offset 25
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}
}