package data;

public interface GameData {
	int UPS = 60;//the updates per second
	boolean DEBUG = true;

	int MAX_ENTITIES = 750;

	boolean ALL_GUNS_AT_START = true, RECOIL = false, DAMAGE = true;
	int STARTING_GUN = 0;

	boolean BOTS_WANDER = true;
	boolean BOTS_TARGET = false;

//	int BOT_COUNT = 0;
	int BOT_COUNT = 1;
//	int BOT_COUNT = 2;
//	int BOT_COUNT = 3;
//	int BOT_COUNT = 10;
//	int BOT_COUNT = 40;

	int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;//directions (clockwise starting at right (0))
}
