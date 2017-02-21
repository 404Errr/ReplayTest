package client.ai.pathfinding;

public class MapNode extends AbstractNode {

	public MapNode(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}

	@Override
	public void sethCosts(AbstractNode endNode) {
		this.sethCosts((Math.abs(this.getXPosition()-endNode.getXPosition())+Math.abs(this.getYPosition()-endNode.getYPosition()))*BASICMOVEMENTCOST);
	}

}

abstract class AbstractNode {
	protected static final int BASICMOVEMENTCOST = 10;
	protected static final int DIAGONALMOVEMENTCOST = 14;

	private int xPosition;
	private int yPosition;
	private boolean walkable;

	private AbstractNode previous;
	private boolean diagonally;
	private int movementPenalty;

	private int gCosts;

	private int hCosts;

	public AbstractNode(int xPosition, int yPosition) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.walkable = true;
		this.movementPenalty = 0;
	}

	public boolean isDiagonaly() {
		return diagonally;
	}

	public void setIsDiagonaly(boolean isDiagonaly) {
		this.diagonally = isDiagonaly;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getYPosition() {
		return yPosition;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public AbstractNode getPrevious() {
		return previous;
	}

	public void setPrevious(AbstractNode previous) {
		this.previous = previous;
	}

	public int getfCosts() {
		return gCosts + hCosts;
	}

	public int getgCosts() {
		return gCosts;
	}

	private void setgCosts(int gCosts) {
		this.gCosts = gCosts + movementPenalty;
	}

	public void setgCosts(AbstractNode previousAbstractNode, int basicCost) {
		setgCosts(previousAbstractNode.getgCosts() + basicCost);
	}

	public void setgCosts(AbstractNode previousAbstractNode) {
		if (diagonally) {
			setgCosts(previousAbstractNode, DIAGONALMOVEMENTCOST);
		} else {
			setgCosts(previousAbstractNode, BASICMOVEMENTCOST);
		}
	}

	public int calculategCosts(AbstractNode previousAbstractNode) {
		if (diagonally) {
			return (previousAbstractNode.getgCosts()+DIAGONALMOVEMENTCOST+movementPenalty);
		} else {
			return (previousAbstractNode.getgCosts()+BASICMOVEMENTCOST+movementPenalty);
		}
	}

	 public int calculategCosts(AbstractNode previousAbstractNode, int movementCost) {
		return (previousAbstractNode.getgCosts() + movementCost + movementPenalty);
	 }

	 public int gethCosts() {
		 return hCosts;
	 }

	 protected void sethCosts(int hCosts) {
		 this.hCosts = hCosts;
	 }

	 public abstract void sethCosts(AbstractNode endAbstractNode);

	 @SuppressWarnings("unused")
	 private int getMovementPanelty() {
		 return movementPenalty;
	 }

	 @Override
	 public boolean equals(Object obj) {
		 if (obj == null||getClass()!=obj.getClass()||this.xPosition!=((AbstractNode) obj).xPosition||this.yPosition!=((AbstractNode) obj).yPosition) {
			 return false;
		 }
		 return true;
	 }

	 @Override
	 public int hashCode() {
		 int hash = 3;
		 hash = 17*hash+this.xPosition;
		 hash = 17*hash+this.yPosition;
		 return hash;
	 }
}
