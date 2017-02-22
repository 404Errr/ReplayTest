package client.level.pathfinding;

import client.level.Level;
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
		this.gCosts = calculategCosts(previousTileNode);
	}

	public int calculategCosts(TileNode previousTileNode) {
		int cost;
		if (diagonal) {
			cost = previousTileNode.getgCosts()+DIAGONAL_MOVEMENT_COST;
		} else {
			cost =  previousTileNode.getgCosts()+BASIC_MOVEMENT_COST;
		}
		if (isNextToWall()) cost+=WALL_MOVEMENT_COST;
		return cost;
	}

	public int calculategCosts(TileNode previousTileNode, int movementCost) {
		return previousTileNode.getgCosts()+movementCost;
	}

	public int gethCosts() {
		return hCosts;
	}

	public void sethCosts(TileNode endTileNode) {
		this.hCosts = (Math.abs(x-endTileNode.getX())+Math.abs(y-endTileNode.getY()))*BASIC_MOVEMENT_COST;
	}

	public boolean isNextToWall() {
		for (int r = y-1;r<=y+1;r++) {
			for (int c = x-1;c<=x+1;c++) {
				if (r>0&&c>0&&r<=Level.getNavMapHeight()&&c<=Level.getNavMapWidth()&&!Level.getNavMap().getTileNode(c, r).isUseable()) {
					return true;
				}
			}
		}
		return false;
	}
}
