package client.player.ai;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import client.game.Game;
import client.level.Level;
import client.level.SpawnPoint;
import client.level.pathfinding.WanderFinder;
import client.player.Player;
import data.AIData;
import data.TileData;
import util.Util;

public class AIPlayer extends Player implements AIData {
	private Point currentPathGoal, currentTargetPoint;
	private AIPathFinder pathFinder;
	private List<SightLine> sightLines;
	private Player currentTargetPlayer;
	private boolean control;
	private float sway, dSway = 1, weaponReactionCooldown, pathfindingReactionCooldown;
	private final float SWAY_A = 5, SWAY_B = 0.4f, SWAY_C = 0.15f, SWAY_D = 2, SWAY_E = 20;

	public AIPlayer(Color color, SpawnPoint spawnPoint) {
		super(color, (float)spawnPoint.getX(), (float)spawnPoint.getY());
		pathFinder = new AIPathFinder();
		control = true;
		selectWeapon((new Random()).nextInt(4));
	}

	@Override
	public void respawn(SpawnPoint spawnPoint) {
		super.respawn(spawnPoint);
		clearPath();
		selectWeapon((new Random()).nextInt(4));
	}

	@Override
	public boolean tick() {
		tickSightLines();
		targetPathPlayer();
		targetWeaponPlayer();
		updatePathFinding();
		pathFinder.refresh();
		if (control) {
			controlMovement();
			controlWeapons();
		}
		return super.tick();
	}

	private void controlWeapons() {
		setMouseControl(MOUSE1, currentTargetPlayer!=null);
	}

	private void targetWeaponPlayer() {
		boolean found = false;
		for (int i = 0;i<sightLines.size();i++) {
			if (!sightLines.get(i).isBroken()) {
				found = true;
				if (weaponReactionCooldown>0) {
					weaponReactionCooldown-=1000f/UPS;
				}
				else {
					currentTargetPlayer = sightLines.get(i).getTarget();
					weaponReactionCooldown = 0;
					return;
				}
			}
		}
		if (!found) {
			weaponReactionCooldown = VISION_REACTION_TIME;
			currentTargetPlayer = null;
		}
	}

	private void targetPathPlayer() {
		boolean found = false;
		for (int i = 0;i<sightLines.size();i++) {
			if (!sightLines.get(i).isBroken()) {//can see
				found = true;
				if (pathfindingReactionCooldown>0) {
					pathfindingReactionCooldown-=1000f/UPS;
				}
				else {
					pathfindingReactionCooldown = 0;
					currentPathGoal = sightLines.get(i).getTarget().getPoint();
					return;
				}
			}
		}
		if (!found) pathfindingReactionCooldown = VISION_REACTION_TIME;

	}

	private void tickSightLines() {
		for (int i = 0;i<sightLines.size();i++) {
			sightLines.get(i).tick();
		}
	}

	public void initSightLines() {
		sightLines = new ArrayList<>();
		for (int i = 0;i<Game.getEntities().size();i++) {//assumes there are only players in entities
			if (this.getColor()!=Game.getEntities().get(i).getColor()) sightLines.add(new SightLine(this, Game.getPlayer(i), TileData.getNotVisible()));
		}
		tickSightLines();
	}

	@Override
	protected void turn() {
		if (currentTargetPlayer!=null) {//has target
			getActiveWeapon();
			sway+=dSway*Util.getSpread(SWAY_B, SWAY_C);
			if (sway>SWAY_A) dSway = -1;
			if (sway<-SWAY_A) dSway = 1;
			if ((int)(Math.random()*SWAY_E)==0) dSway = -dSway;
			if ((int)(Math.random()*SWAY_D)==0) sway*=0.1f;
			setFacing((float)(Util.getAngle(x, y, currentTargetPlayer.getX(), currentTargetPlayer.getY())+Math.toRadians(sway)));
//			setFacing(Util.getAngle(x, y, currentTargetPlayer.getX(), currentTargetPlayer.getY()));
		}
		else {//doesnt have target
			if (Math.abs(dX)+Math.abs(dY)>0.1f) {
				setFacing(Util.getAngle(0, 0, dX, dY));
			}
		}
	}

	private void controlMovement() {//TODO FIXME
		if (currentTargetPoint!=null) {//has path
			setMovementControl(UP, y>currentTargetPoint.y);
			setMovementControl(DOWN, y<currentTargetPoint.y);
			setMovementControl(LEFT, x>currentTargetPoint.x);
			setMovementControl(RIGHT, x<currentTargetPoint.x);
		}
		else {//doesnt have path
			setAllMovementControl(false);
		}
	}

	private void updatePathFinding() {
		boolean wandering;
		if (currentPathGoal!=null) {
			if (currentPathGoal.x==getXTile()&&currentPathGoal.y==getYTile()) {//reached goal
				clearPath();
				wandering = true;
			}
			else wandering = false;
		}
		else wandering = true;
		if (currentTargetPlayer!=null) wandering = false;
		if (wandering) currentPathGoal = WanderFinder.getWanderLocation(getXTile(), getYTile());
		if (currentPathGoal!=null) updatePath();
	}

	public void updatePath() {
		if (currentPathGoal!=null&&Util.inArrayBounds(currentPathGoal.x, currentPathGoal.y, Level.getLayout())) try {
			pathFinder.setPath(getXTile(), getYTile(), currentPathGoal.x, currentPathGoal.y);
			if (getCurrentPath().size()>1) {
				currentTargetPoint = getCurrentPath().get(1);
			}
			System.out.println(getCurrentPath().size());
			System.out.println(getCurrentPath().get(1));

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

	public void clearPath() {
		pathFinder.clearCurrentPath();
		currentTargetPoint = null;
		currentPathGoal = null;
	}

	public List<Point> getCurrentPath() {
		return pathFinder.getCurrentPath();
	}

	public List<SightLine> getSightLines() {
		return sightLines;
	}

	public Point getCurrentTargetPoint() {
		return currentTargetPoint;
	}

	public void toggleControl() {
		control = !control;
	}



}
