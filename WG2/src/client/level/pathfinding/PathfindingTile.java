package client.level.pathfinding;

import data.MapData;
import util.Util;

public class PathfindingTile implements MapData {
	private int c, r, totalCost, distanceCost, nextToWallCost;
	private boolean diagonal;
	private PathfindingTile previous;
	private CurrentList currentList;
	private boolean[][] useable;

	enum CurrentList {
		NONE, OPEN, CLOSED
	}

	public PathfindingTile(int c, int r, boolean[][] useable) {
		this.c = c;
		this.r = r;
		this.useable = useable;
		if (isUseable()) for (int i = 1;i<PathFinder.WALL_DISTANCE;i++) {
			if (isNextToWall(i)) nextToWallCost+=PathFinder.WALL_MOVEMENT_COST;
		}
		currentList = CurrentList.NONE;
	}


	public int getC() {
		return c;
	}

	public int getR() {
		return r;
	}

	public int getNextToWallCost() {
		return nextToWallCost;
	}

	public boolean isUseable() {
		return useable[r][c];
	}

	public boolean isNextToWall(int range) {
		for (int y = r-range;y<=r+range;y++) {
			for (int x = c-range;x<=c+range;x++) {
				if (Util.inArrayBounds(x, y, useable)&&!useable[y][x]) {
					return true;
				}
			}
		}
		return false;
	}

	public void setIsDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public PathfindingTile getPrevious() {
		return previous;
	}

	public void setPrevious(PathfindingTile previous) {
		this.previous = previous;
	}

	public int getDistanceCost() {
		return distanceCost;
	}

	public int getCombinedCosts() {
		return totalCost+distanceCost;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(PathfindingTile previousTile) {
		this.totalCost = calculateTotalCost(previousTile);
	}

	public int calculateTotalCost(PathfindingTile previousTile) {
		return previousTile.getTotalCost()+((diagonal)?PathFinder.DIAGONAL_MOVEMENT_COST:PathFinder.BASIC_MOVEMENT_COST)+nextToWallCost;
	}

	public void setDistanceCost(PathfindingTile endTile) {
		distanceCost = (int)(Util.getDistance(c, r, endTile.getC(), endTile.getR())*PathFinder.BASIC_MOVEMENT_COST);
	}

	public CurrentList getCurrentList() {
		return currentList;
	}

	public void setCurrentList(CurrentList currentList) {
		this.currentList = currentList;
	}
}