package data;

import java.awt.event.KeyEvent;

public interface Controls {
	static final int ZOOM_KEY = -1;//disabled

	static final int DEBUG_LOS_LINE_KEY = KeyEvent.VK_F2;
	static final int DEBUG_TEXT_KEY = KeyEvent.VK_F3;
	static final int DEBUG_DRAW_WEAPONS_KEY = KeyEvent.VK_F1;
	static final int DEBUG_DRAW_PATHFINDING_KEY = KeyEvent.VK_F4;
	static final int DEBUG_DRAW_SIGHT_LINES_KEY = KeyEvent.VK_F5;

	static final int UP_KEY_0 = KeyEvent.VK_W;
	static final int DOWN_KEY_0 = KeyEvent.VK_S;
	static final int LEFT_KEY_0 = KeyEvent.VK_A;
	static final int RIGHT_KEY_0 = KeyEvent.VK_D;

	static final int UP_KEY_1 = KeyEvent.VK_UP;
	static final int DOWN_KEY_1 = KeyEvent.VK_DOWN;
	static final int LEFT_KEY_1 = KeyEvent.VK_LEFT;
	static final int RIGHT_KEY_1 = KeyEvent.VK_RIGHT;


}
