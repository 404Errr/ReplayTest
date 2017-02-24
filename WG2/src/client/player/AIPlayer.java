package client.player;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import client.game.Game;
import client.level.Level;
import client.level.SpawnPoint;
import client.level.pathfinding.PathFinder;
import client.player.ai.SightLine;
import util.Util;

public class AIPlayer extends Player {
	private PathFinder pathFinder;
	private List<Point> currentPath;
	private Point currentPathGoal, currentTargetPoint;

	private List<SightLine> sightLines;

	private boolean controlMovement;

	/*public AIPlayer(Color color, float x, float y) {
		super(color, x, y);
		pathFinder = new PathFinder();
		currentPath = new LinkedList<>();
		controlMovement = true;
	}*/

	public AIPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, spawnPoint.getX(), spawnPoint.getY());
		pathFinder = new PathFinder();
		currentPath = new LinkedList<>();
		controlMovement = true;
	}

	@Override
	public void respawn(SpawnPoint spawnPoint) {
		super.respawn(spawnPoint);
		stopPathfinding();
	}

	@Override
	public boolean tick() {
		updateSightLines();
		updatePathProgress();
		if (controlMovement) {
			controlMovement();
		}
		return super.tick();
	}

	private void updateSightLines() {
		for (int i = 0;i<sightLines.size();i++) {
			sightLines.get(i).tick();
		}
	}

	public void initSightLines() {
		sightLines = new ArrayList<>();
		for (int i = 0;i<Game.getEntities().size();i++) {//assumes there are only players in entities
			sightLines.add(new SightLine(this, Game.getPlayer(i)));
		}
		updateSightLines();
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
			if (x<0||y<0||x>=Level.getWidth()||y>=Level.getHeight()) return;
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

	public List<SightLine> getSightLines() {
		return sightLines;
	}

	public Point getCurrentTargetPoint() {
		return currentTargetPoint;
	}

	public void toggleControlMovement() {
		controlMovement = !controlMovement;
	}



}
