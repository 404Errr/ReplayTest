package shared.data;

import java.awt.event.KeyEvent;

public interface Controls {
	public static double ZOOM_INCREMENT = 0.001d;

	public static int ZOOM_KEY = -1;//disabled

	public static int DEBUG_LOS_LINE_KEY = KeyEvent.VK_F2;
	public static int DEBUG_TEXT_KEY = KeyEvent.VK_F3;
	public static int DEBUG_DRAW_WEAPONS_KEY = KeyEvent.VK_F5;

	public static int UP_KEY_0 = KeyEvent.VK_W;
	public static int DOWN_KEY_0 = KeyEvent.VK_S;
	public static int LEFT_KEY_0 = KeyEvent.VK_A;
	public static int RIGHT_KEY_0 = KeyEvent.VK_D;

	public static int UP_KEY_1 = KeyEvent.VK_UP;
	public static int DOWN_KEY_1 = KeyEvent.VK_DOWN;
	public static int LEFT_KEY_1 = KeyEvent.VK_LEFT;
	public static int RIGHT_KEY_1 = KeyEvent.VK_RIGHT;


}
