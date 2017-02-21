package client.level.pathfinding;

public class TileNode {
	protected static final int BASICMOVEMENTCOST = 10;
	protected static final int DIAGONALMOVEMENTCOST = 14;

	private int x, y;
	private boolean open;

	private TileNode previous;
	private boolean diagonally;

	private int gCosts, hCosts;

	public TileNode(int x, int y, boolean open) {
		this.x = x;
		this.y = y;
		this.open = open;
	}

	public void setIsDiagonaly(boolean isDiagonaly) {
		this.diagonally = isDiagonaly;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isOpen() {
		return open;
	}

	public TileNode getPrevious() {
		return previous;
	}

	public void setPrevious(TileNode previous) {
		this.previous = previous;
	}

	public int getfCosts() {
		return gCosts + hCosts;
	}

	public int getgCosts() {
		return gCosts;
	}

	private void setgCosts(int gCosts) {
		this.gCosts = gCosts;
	}

	public void setgCosts(TileNode previousTileNode, int basicCost) {
		setgCosts(previousTileNode.getgCosts()+basicCost);
	}

	public void setgCosts(TileNode previousTileNode) {
		if (diagonally) {
			setgCosts(previousTileNode, DIAGONALMOVEMENTCOST);
		} else {
			setgCosts(previousTileNode, BASICMOVEMENTCOST);
		}
	}

	public int calculategCosts(TileNode previousTileNode) {
		if (diagonally) {
			return previousTileNode.getgCosts()+DIAGONALMOVEMENTCOST;
		} else {
			return previousTileNode.getgCosts()+BASICMOVEMENTCOST;
		}
	}

	public int calculategCosts(TileNode previousTileNode, int movementCost) {
		return (previousTileNode.getgCosts()+movementCost);
	}

	public int gethCosts() {
		return hCosts;
	}

	public void sethCosts(TileNode endTileNode) {
		this.sethCosts((Math.abs(this.getX()-endTileNode.getX())+Math.abs(this.getY()-endTileNode.getY()))*BASICMOVEMENTCOST);
	}

	protected void sethCosts(int hCosts) {
		this.hCosts = hCosts;
	}
}
