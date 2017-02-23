package client.player;

import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import client.level.pathfinding.PathFinder;
import util.Util;

public class AIPlayer extends Player {
	PathFinder pathFinder;
	List<Point> currentPath;

	public AIPlayer(Color color, float x, float y) {
		super(color, x, y);
		pathFinder = new PathFinder();
		currentPath = new LinkedList<>();
	}

	@Override
	public boolean tick() {
		updatePathProgress();
		controlMovement();


		return super.tick();
	}

	@Override
	protected void turn() {
		if (Math.abs(dX)+Math.abs(dY)>0.01f) setFacing(Util.getAngle(0, 0, dX, dY));
	}

	public void controlMovement() {
		if (currentPath.size()>1) {
			Point currentTargetPoint = currentPath.get(1);//current position is already in currentPath
			setMovementControl(UP, y>currentTargetPoint.y);
			setMovementControl(DOWN, y<currentTargetPoint.y);
			setMovementControl(LEFT, x>currentTargetPoint.x);
			setMovementControl(RIGHT, x<currentTargetPoint.x);
		}
		else setAllMovementControl(false);
	}

	public void updatePathProgress() {
		if (currentPath.size()<=1||(currentPath.get(currentPath.size()-1).x==getXTile()&&currentPath.get(currentPath.size()-1).y==getYTile())) {
			currentPath.clear();
		}
		else try {
			currentPath = pathFinder.getPath(getXTile(), getYTile(), currentPath.get(currentPath.size()-1).x, currentPath.get(currentPath.size()-1).y);
		}
		catch (Exception e) {}
	}

	public void setPathTo(float x, float y) {
		 try {
			 currentPath = pathFinder.getPath(getXTile(), getYTile(), Math.round(x), Math.round(y));
		 }
		 catch (Exception e) {}
	}

	public void stopPathfinding() {
		currentPath.clear();
	}

	public PathFinder getPathFinder() {
		return pathFinder;
	}

	public List<Point> getCurrentPath() {
		return currentPath;
	}



}
