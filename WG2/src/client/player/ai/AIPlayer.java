//package client.player.ai;
//
//import java.awt.Color;
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Random;
//
//import client.game.Game;
//import client.level.SpawnPoint;
//import client.level.pathfinding.PathFinder;
//import client.level.pathfinding.WanderFinder;
//import client.player.Player;
//import data.AIData;
//import util.Util;
//
//public class AIPlayer extends Player implements AIData {
//	private PathFinder pathFinder;
//	private List<Point> currentPath;
//	private Point currentPathGoal, currentTargetPoint;
//
//	private List<SightLine> sightLines;
//
//	private Player currentTargetPlayer;
//
//	private boolean control;
//
//	private float sway, dSway = 1, weaponReactionCooldown, pathfindingReactionCooldown;
//	private final float SWAY_A = 5, SWAY_B = 0.4f, SWAY_C = 0.15f, SWAY_D = 2, SWAY_E = 20;
//
//	public AIPlayer(Color color, SpawnPoint spawnPoint) {
//		super(color, (float)spawnPoint.getX(), (float)spawnPoint.getY());
//		pathFinder = new PathFinder();
//		currentPath = new LinkedList<>();
//		control = true;
//		selectWeapon((new Random()).nextInt(4));
//	}
//
//	@Override
//	public void respawn(SpawnPoint spawnPoint) {
//		super.respawn(spawnPoint);
//		stopPathfinding();
//		selectWeapon((new Random()).nextInt(4));
//	}
//
//	@Override
//	public boolean tick() {
//		updateSightLines();
//		targetPathPlayer();
//		targetWeaponPlayer();
//		updatePathFinding();
//		if (control) {
//			controlMovement();
//			controlWeapons();
//		}
//		return super.tick();
//	}
//
//	private void controlWeapons() {
//		setMouseControl(MOUSE1, currentTargetPlayer!=null);
//	}
//
//	private void targetWeaponPlayer() {
//		boolean found = false;
//		for (int i = 0;i<sightLines.size();i++) {
//			if (!sightLines.get(i).isBroken()) {
//				found = true;
//				if (weaponReactionCooldown>0) {
//					weaponReactionCooldown-=1000f/UPS;
//				}
//				else {
//					currentTargetPlayer = sightLines.get(i).getTarget();
//					weaponReactionCooldown = 0;
//					return;
//				}
//			}
//		}
//		if (!found) {
//			weaponReactionCooldown = VISION_REACTION_TIME;
//			currentTargetPlayer = null;
//		}
//	}
//
//	private void targetPathPlayer() {
//		boolean found = false;
//		for (int i = 0;i<sightLines.size();i++) {
//			if (!sightLines.get(i).isBroken()) {//can see
//				found = true;
//				if (pathfindingReactionCooldown>0) {
//					pathfindingReactionCooldown-=1000f/UPS;
//				}
//				else {
//					pathfindingReactionCooldown = 0;
//					currentPathGoal = sightLines.get(i).getTarget().getPoint();
//					return;
//				}
//			}
//		}
//		if (!found) pathfindingReactionCooldown = VISION_REACTION_TIME;
//
//	}
//
//	private void updateSightLines() {
//		for (int i = 0;i<sightLines.size();i++) {
//			sightLines.get(i).update();
//		}
//	}
//
//	public void initSightLines() {
//		sightLines = new ArrayList<>();
//		for (int i = 0;i<Game.getEntities().size();i++) {//assumes there are only players in entities
//			if (this.getColor()!=Game.getEntities().get(i).getColor()) sightLines.add(new SightLine(this, Game.getPlayer(i)));
//		}
//		updateSightLines();
//	}
//
//	@Override
//	protected void turn() {
//		if (currentTargetPlayer!=null) {
//			getActiveWeapon();
//			sway+=dSway*Util.getSpread(SWAY_B, SWAY_C);
//			if (sway>SWAY_A) dSway = -1;
//			if (sway<-SWAY_A) dSway = 1;
//			if ((int)(Math.random()*SWAY_E)==0) dSway*=-1;
//			if ((int)(Math.random()*SWAY_D)==0) sway*=0.1f;
//			setFacing((float)(Util.getAngle(x, y, currentTargetPlayer.getX(), currentTargetPlayer.getY())+Math.toRadians(sway)));
//
////			setFacing(Util.getAngle(x, y, currentTargetPlayer.getX(), currentTargetPlayer.getY()));
//		}
//		else if (Math.abs(dX)+Math.abs(dY)>0.1f) setFacing(Util.getAngle(0, 0, dX, dY));
//	}
//
//	private void controlMovement() {
//		if (currentTargetPoint!=null) {
//			setMovementControl(UP, y>currentTargetPoint.y);
//			setMovementControl(DOWN, y<currentTargetPoint.y);
//			setMovementControl(LEFT, x>currentTargetPoint.x);
//			setMovementControl(RIGHT, x<currentTargetPoint.x);
//		}
//		else setAllMovementControl(false);
//	}
//
//	private void updatePathFinding() {
//		boolean wandering;
//		if (currentPathGoal!=null) {
//			if (currentPathGoal.x==getXTile()&&currentPathGoal.y==getYTile()) {//reached goal
//				stopPathfinding();
//				wandering = true;
//			}
//			else wandering = false;
//
//		}
//		else wandering = true;
//		if (currentTargetPlayer!=null) wandering = false;
//		if (wandering) currentPathGoal = WanderFinder.getWanderLocation(getXTile(), getYTile());
//		if (currentPathGoal!=null) updatePath();
//	}
//
//	public void updatePath() {
//		if (currentPathGoal!=null) try {
//			currentPath = pathFinder.getPath(getXTile(), getYTile(), currentPathGoal.x, currentPathGoal.y);
//			if (currentPath.size()>1) currentTargetPoint = currentPath.get(1);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setPathTo(float x, float y) {
//		currentPathGoal = new Point(Math.round(x), Math.round(y));
//	}
//
//	public void setPathTo(Point point) {
//		currentPathGoal = point;
//	}
//
//	public void stopPathfinding() {
//		currentPath.clear();
//		currentTargetPoint = null;
//		currentPathGoal = null;
//	}
//
//	public PathFinder getPathFinder() {
//		return pathFinder;
//	}
//
//	public List<Point> getCurrentPath() {
//		return currentPath;
//	}
//
//	public List<SightLine> getSightLines() {
//		return sightLines;
//	}
//
//	public Point getCurrentTargetPoint() {
//		return currentTargetPoint;
//	}
//
//	public void toggleControl() {
//		control = !control;
//	}
//
//
//
//}
