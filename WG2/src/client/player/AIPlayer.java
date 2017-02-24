package client.player;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import client.level.pathfinding.PathFinder;
import util.Util;

public class AIPlayer extends Player {
	private PathFinder pathFinder;
	private List<Point> currentPath;
	private Point currentPathGoal, currentTargetPoint;

	private boolean controlMovement;

	public AIPlayer(Color color, float x, float y) {
		super(color, x, y);
		pathFinder = new PathFinder();
		currentPath = new LinkedList<>();
		controlMovement = true;
	}

	@Override
	public boolean tick() {
		updatePathProgress();
		if (controlMovement) {
			controlMovement();
		}
		return super.tick();
	}

	@Override
	protected void turn() {
		if (Math.abs(dX)+Math.abs(dY)>0.01f) setFacing(Util.getAngle(0, 0, dX, dY));
	}

	private void controlMovement() {
		if (currentTargetPoint!=null) {
			setMovementControl(UP, y>currentTargetPoint.y);
			setMovementControl(DOWN, y<currentTargetPoint.y);
			setMovementControl(LEFT, x>currentTargetPoint.x);
			setMovementControl(RIGHT, x<currentTargetPoint.x);
		}
		else setAllMovementControl(false);
	}

	private void updatePathProgress() {
		if (currentPath.size()<=0||currentPathGoal==null||(currentPathGoal.x==getXTile()&&currentPathGoal.y==getYTile())) {
			stopPathfinding();
		}
		else {
			setPathTo(currentPathGoal.x, currentPathGoal.y);
			if (currentPath.size()>1) currentTargetPoint = currentPath.get(1);
		}
	}

	public void setPathTo(float x, float y) {
		try {
			currentPathGoal = new Point(Math.round(x), Math.round(y));
			if (pathFinder.isIdle()) currentPath = pathFinder.getPath(getXTile(), getYTile(), currentPathGoal.x, currentPathGoal.y);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopPathfinding() {
		currentPath.clear();
		pathFinder.setIdle(true);
		currentTargetPoint = null;
		currentPathGoal = null;
	}

	public PathFinder getPathFinder() {
		return pathFinder;
	}

	public List<Point> getCurrentPath() {
		return currentPath;
	}

	public Point getCurrentTargetPoint() {
		return currentTargetPoint;
	}

	public void toggleControlMovement() {
		controlMovement = !controlMovement;
	}



}
