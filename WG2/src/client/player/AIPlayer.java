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
	private Point currentTargetPoint;
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
		if (currentPath.size()<=1||(currentPath.get(currentPath.size()-1).x==getXTile()&&currentPath.get(currentPath.size()-1).y==getYTile())) {
			stopPathfinding();
		}
		else {
			setPathTo(currentPath.get(currentPath.size()-1).x, currentPath.get(currentPath.size()-1).y);
			currentTargetPoint = currentPath.get(1);
		}
	}

	public void setPathTo(float x, float y) {
		 try {
			 currentPath = pathFinder.getPath(getXTile(), getYTile(), Math.round(x), Math.round(y));
		 }
		 catch (Exception e) {}
	}

	public void stopPathfinding() {
		currentPath.clear();
		currentTargetPoint = null;
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
