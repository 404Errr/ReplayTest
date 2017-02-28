package data;

import java.awt.event.KeyEvent;

public interface ControlData {
	int MOUSE1 = 0, MOUSE2 = 1, MOUSE3 = 2;//left, middle, right (mouse)
	
	int ZOOM_KEY = -1;//disabled

	int SHOOT_1 = MOUSE1;
	
	int DEBUG_LOS_LINE_KEY = KeyEvent.VK_F2;
	int DEBUG_TEXT_KEY = KeyEvent.VK_F3;
	int DEBUG_DRAW_WEAPONS_KEY = KeyEvent.VK_F1;
	int DEBUG_DRAW_PATHFINDING_KEY = KeyEvent.VK_F4;
	int DEBUG_DRAW_SIGHT_LINES_KEY = KeyEvent.VK_F5;

	int UP_KEY_0 = KeyEvent.VK_W;
	int DOWN_KEY_0 = KeyEvent.VK_S;
	int LEFT_KEY_0 = KeyEvent.VK_A;
	int RIGHT_KEY_0 = KeyEvent.VK_D;

	int UP_KEY_1 = KeyEvent.VK_UP;
	int DOWN_KEY_1 = KeyEvent.VK_DOWN;
	int LEFT_KEY_1 = KeyEvent.VK_LEFT;
	int RIGHT_KEY_1 = KeyEvent.VK_RIGHT;


}
