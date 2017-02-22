package data;

import java.awt.event.KeyEvent;

public interface Controls {
	public static int ZOOM_KEY = -1;//disabled

	public static int DEBUG_LOS_LINE_KEY = KeyEvent.VK_F2;
	public static int DEBUG_TEXT_KEY = KeyEvent.VK_F3;
	public static int DEBUG_DRAW_WEAPONS_KEY = KeyEvent.VK_F1;

	public static int DEBUG_TILE_PATHFINDING_NONE_KEY = KeyEvent.VK_F5;
	public static int DEBUG_TILE_PATHFINDING_COMBINED_KEY = KeyEvent.VK_F6;
	public static int DEBUG_TILE_PATHFINDING_TOTAL_KEY = KeyEvent.VK_F7;
	public static int DEBUG_TILE_PATHFINDING_DISTANCE_KEY = KeyEvent.VK_F8;

	public static int UP_KEY_0 = KeyEvent.VK_W;
	public static int DOWN_KEY_0 = KeyEvent.VK_S;
	public static int LEFT_KEY_0 = KeyEvent.VK_A;
	public static int RIGHT_KEY_0 = KeyEvent.VK_D;

	public static int UP_KEY_1 = KeyEvent.VK_UP;
	public static int DOWN_KEY_1 = KeyEvent.VK_DOWN;
	public static int LEFT_KEY_1 = KeyEvent.VK_LEFT;
	public static int RIGHT_KEY_1 = KeyEvent.VK_RIGHT;


}
