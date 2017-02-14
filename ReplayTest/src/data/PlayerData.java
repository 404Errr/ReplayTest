package data;

import main.Main;

public interface PlayerData {
	public static final int PLAYER_SIZE = Main.getScale();
	public static final double PLAYER_ACCELERATION = 5d/Data.UPS, PLAYER_DECCELERATION = 0.8d;
	public static final double PLAYER_SPEED_LIMIT = 10d, PLAYER_ACCELERATION_LIMIT = 0.1d*Data.UPS;
}
