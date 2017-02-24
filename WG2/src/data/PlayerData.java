package data;

public interface PlayerData {
	static final float PLAYER_SIZE = 1, HALF_PLAYER_SIZE = PLAYER_SIZE/2;//size of the player relative to the size of each tile
	static final float PLAYER_ACCELERATION = 0.004f, PLAYER_ACCELERATION_LIMIT = 3f, PLAYER_DECCELERATION = 0.8f;
	static final float PLAYER_SPEED_LIMIT = 0.25f;//0.3f;
	static final float PLAYER_INITIAL_HEALTH = 1.0f;
}
