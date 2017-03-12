package client.level.pathfinding;

import client.level.Level;
import client.level.Tile;
import data.MapData;
import data.AIData;
import data.TileData;
import util.Util;

public class OldPathfindingTile extends Tile implements MapData, AIData, TileData {
	private int totalCost, distanceCost;
	private boolean diagonal;
	private OldPathfindingTile previous;
	private CurrentList currentList;

	enum CurrentList {
		NONE, OPEN, CLOSED
	}

	public OldPathfindingTile(int c, int r) {
		super(c, r, Level.getLayoutType(c, r), TileData.getSolid(Level.getLayoutType(c, r))[SOLID_WALLS]);
		currentList = CurrentList.NONE;
	}

	public void setIsDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public OldPathfindingTile getPrevious() {
		return previous;
	}

	public void setPrevious(OldPathfindingTile previous) {
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

	public void setTotalCost(OldPathfindingTile previousTile) {
		this.totalCost = calculateTotalCost(previousTile);
	}

	public int calculateTotalCost(OldPathfindingTile previousTile) {
		return previousTile.getTotalCost()+((diagonal)?DIAGONAL_MOVEMENT_COST:BASIC_MOVEMENT_COST)+nextToWallCost;
	}

	public void setDistanceCost(OldPathfindingTile endTile) {
		distanceCost = (int)(Util.getDistance(c, r, endTile.getC(), endTile.getR())*BASIC_MOVEMENT_COST);
	}

	public CurrentList getCurrentList() {
		return currentList;
	}

	public void setCurrentList(CurrentList currentList) {
		this.currentList = currentList;
	}


}