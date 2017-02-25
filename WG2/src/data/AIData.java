package data;

public interface AIData {
	//pathfinding
	int BASIC_MOVEMENT_COST = 10;//10
	int DIAGONAL_MOVEMENT_COST = 14;//14
	int WALL_MOVEMENT_COST = 10;//additional if near a wall (avoid collisions)
	int WALL_DISTANCE = 2;//max distance to walls it checks
	//ai
	int VISION_REACTION_TIME = 240;
}
