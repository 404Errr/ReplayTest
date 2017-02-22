package data;

public interface PathfindingData {
	public static final int BASIC_MOVEMENT_COST = 10;//10
	public static final int DIAGONAL_MOVEMENT_COST = 14;//14
	public static final int WALL_MOVEMENT_COST = 25;//additional if near a wall (avoid collisions)
	public static final int WALL_DISTANCE = 2;//max distance to walls it checks
}
