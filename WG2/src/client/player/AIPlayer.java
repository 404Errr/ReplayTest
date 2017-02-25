package client.player;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import client.game.Game;
import client.level.SpawnPoint;
import client.level.pathfinding.PathFinder;
import client.player.ai.SightLine;
import util.Util;

public class AIPlayer extends Player {
	private PathFinder pathFinder;
	private List<Point> currentPath;
	private Point currentPathGoal, currentTargetPoint;

	private List<SightLine> sightLines;

	private Player currentTargetPlayer;

	private boolean control;

	public AIPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, spawnPoint.getX(), spawnPoint.getY());
		pathFinder = new PathFinder();
		currentPath = new LinkedList<>();
		control = true;
	}

	@Override
	public void respawn(SpawnPoint spawnPoint) {
		super.respawn(spawnPoint);
		stopPathfinding();
	}

	@Override
	public boolean tick() {
		updateSightLines();
		targetPathPlayer();
		targetWeaponPlayer();
		updatePathFinding();
		if (control) {
			controlMovement();
			controlWeapons();
		}
		return super.tick();
	}

	private void controlWeapons() {
		setMouseControl(MOUSE1, currentTargetPlayer!=null);
	}

	private boolean targetWeaponPlayer() {
		for (int i = 0;i<sightLines.size();i++) {
			if (sightLines.get(i).getCanSee()) {
				currentTargetPlayer = sightLines.get(i).getTarget();
				return true;
			}
		}
		currentTargetPlayer = null;
		return false;
	}

	private boolean targetPathPlayer() {//true if found target
		for (int i = 0;i<sightLines.size();i++) {
			if (sightLines.get(i).getCanSee()&&Util.distance(x, y, sightLines.get(i).getTarget().getX(), sightLines.get(i).getTarget().getY())>2f) {
				currentPathGoal = sightLines.get(i).getTarget().getPoint();
				return true;
			}
		}
		return false;
	}

	private void updateSightLines() {
		for (int i = 0;i<sightLines.size();i++) {
			sightLines.get(i).tick();
		}
	}

	public void initSightLines() {
		sightLines = new ArrayList<>();
		for (int i = 0;i<Game.getEntities().size();i++) {//assumes there are only players in entities
			if (Game.getPlayer(i)!=this) sightLines.add(new SightLine(this, Game.getPlayer(i)));
		}
		updateSightLines();
	}

	@Override
	protected void turn() {
		if (currentTargetPlayer!=null) {
			setFacing(Util.getAngle(x, y, currentTargetPlayer.getX(), currentTargetPlayer.getY()));
		}
		else if (Math.abs(dX)+Math.abs(dY)>0.01f) setFacing(Util.getAngle(0, 0, dX, dY));
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

	private void updatePathFinding() {
		if (currentPathGoal!=null) {
			if (currentPathGoal.x==getXTile()&&currentPathGoal.y==getYTile()) {//reached goal
				stopPathfinding();
			}
			updatePath();
		}
	}

	public void updatePath() {
		if (currentPathGoal!=null) try {
			currentPath = pathFinder.getPath(getXTile(), getYTile(), currentPathGoal.x, currentPathGoal.y);
			if (currentPath.size()>1) currentTargetPoint = currentPath.get(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPathTo(float x, float y) {
		currentPathGoal = new Point(Math.round(x), Math.round(y));
	}

	public void setPathTo(Point point) {
		currentPathGoal = point;
	}

	public void stopPathfinding() {
		currentPath.clear();
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
		control = !control;
	}



}
