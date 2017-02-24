package data;

public interface PathfindingData {
	static final int BASIC_MOVEMENT_COST = 10;//10
	static final int DIAGONAL_MOVEMENT_COST = 14;//14
	static final int WALL_MOVEMENT_COST = 10;//additional if near a wall (avoid collisions)
	static final int WALL_DISTANCE = 2;//max distance to walls it checks
}
