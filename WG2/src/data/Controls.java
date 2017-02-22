package data;

import java.awt.event.KeyEvent;

public interface Controls {
	public static final int ZOOM_KEY = -1;//disabled

	public static final int DEBUG_LOS_LINE_KEY = KeyEvent.VK_F2;
	public static final int DEBUG_TEXT_KEY = KeyEvent.VK_F3;
	public static final int DEBUG_DRAW_WEAPONS_KEY = KeyEvent.VK_F1;

	public static final int DEBUG_DRAW_PATHFINDING_KEY = KeyEvent.VK_F4;
	public static final int DEBUG_TILE_PATHFINDING_NONE_KEY = KeyEvent.VK_F5;
	public static final int DEBUG_TILE_PATHFINDING_COMBINED_KEY = KeyEvent.VK_F6;
	public static final int DEBUG_TILE_PATHFINDING_TOTAL_KEY = KeyEvent.VK_F7;
	public static final int DEBUG_TILE_PATHFINDING_DISTANCE_KEY = KeyEvent.VK_F8;

	public static final int UP_KEY_0 = KeyEvent.VK_W;
	public static final int DOWN_KEY_0 = KeyEvent.VK_S;
	public static final int LEFT_KEY_0 = KeyEvent.VK_A;
	public static final int RIGHT_KEY_0 = KeyEvent.VK_D;

	public static final int UP_KEY_1 = KeyEvent.VK_UP;
	public static final int DOWN_KEY_1 = KeyEvent.VK_DOWN;
	public static final int LEFT_KEY_1 = KeyEvent.VK_LEFT;
	public static final int RIGHT_KEY_1 = KeyEvent.VK_RIGHT;


}
