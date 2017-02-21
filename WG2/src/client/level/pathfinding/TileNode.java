package client.level.pathfinding;

import data.PathfindingData;

public class TileNode implements PathfindingData {
	private int x, y, gCosts, hCosts;
	private boolean usable, diagonal;

	private TileNode previous;

	public TileNode(int x, int y, boolean useable) {
		this.x = x;
		this.y = y;
		this.usable = useable;
	}

	public void setIsDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isUseable() {
		return usable;
	}

	public TileNode getPrevious() {
		return previous;
	}

	public void setPrevious(TileNode previous) {
		this.previous = previous;
	}

	public int getfCosts() {
		return gCosts+hCosts;
	}

	public int getgCosts() {
		return gCosts;
	}

	public void setgCosts(TileNode previousTileNode, int basicCost) {
		this.gCosts = previousTileNode.getgCosts()+basicCost;
	}

	public void setgCosts(TileNode previousTileNode) {
		if (diagonal) {
			setgCosts(previousTileNode, DIAGONAL_MOVEMENT_COST);
		} else {
			setgCosts(previousTileNode, BASIC_MOVEMENT_COST);
		}
	}

	public int calculategCosts(TileNode previousTileNode) {
		if (diagonal) {
			return previousTileNode.getgCosts()+DIAGONAL_MOVEMENT_COST;
		} else {
			return previousTileNode.getgCosts()+BASIC_MOVEMENT_COST;
		}
	}

	public int calculategCosts(TileNode previousTileNode, int movementCost) {
		return previousTileNode.getgCosts()+movementCost;
	}

	public int gethCosts() {
		return hCosts;
	}

	public void sethCosts(TileNode endTileNode) {
		this.hCosts = (Math.abs(this.getX()-endTileNode.getX())+Math.abs(this.getY()-endTileNode.getY()))*BASIC_MOVEMENT_COST;
	}
}
