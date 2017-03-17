package client.weapon.weapon;

import client.level.Level;
import client.level.pathfinding.AStarPathFinder;
import client.player.Player;
import client.weapon.WeaponEntity;
import data.TileData;
import data.WeaponData;
import util.Util;

public class Triangle extends WeaponEntity implements WeaponData, TileData, PlayerData {
	private float facing, size, tX, tY;
	private int tSign;
	private boolean destroy;
	private AStarPathFinder pathFinder;

	public Triangle(Player owner, float x, float y, float damage) {
		super(owner.getColor(), x, y, damage);
		this.pathFinder = new AStarPathFinder();
		size = RCTRIANGLE_SIZE;
		tX = owner.getXCenter();
		tY = owner.getYCenter();
	}

	@Override
	public boolean tick() {
		move();
//		facing = Game.getPlayer().getFacing();
		turn();
		return destroy;
	}

	private void move() {
		float aAngle = Util.getAngle(x, y, tX, tY);
		dX*=0.9f;
		dY*=0.9f;
		dX+=Util.getXComp(aAngle, tSign*RCTRIANGLE_ACCELERATION);
		dY+=-Util.getYComp(aAngle, tSign*RCTRIANGLE_ACCELERATION);
		dPosition(dX, dY);
	}

	private void dPosition(float dX, float dY) {
		float inc = 0.025f, remaining, sign;//inc - the increment between collision checks
		remaining = Math.abs(dX);//the magnitude of dX
		sign = Math.signum(dX);//the sign of dX
		while (remaining>0) {
			if (remaining>=inc) x+=inc*sign;//if remaining isnt smaller than increment, change x by increment
			else x+=remaining*sign;//if it is, change x by remaining
			if (checkWallCollision()) {//if hit something
				x = Math.round(x)-(x-Math.round(x));//reallign to grid
				break;//stop checking x
			}
			remaining-=inc;
		}
		remaining = Math.abs(dY);
		sign = Math.signum(dY);
		while (remaining>0) {
			if (remaining>=inc) y+=inc*sign;
			else y+=remaining*sign;
			if (checkWallCollision()) {
				y = Math.round(y)-(y-Math.round(y));
				break;
			}
			remaining-=inc;
		}
	}

	private boolean checkWallCollision() {
		for (int r = getYTile()-1;r<getYTile()+2;r++) {//for each row within the radius
			for (int c = getXTile()-1;c<getXTile()+2;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(c, r).isSolid(SOLID_WALLS)) {//bounds check and if tile is solid
					if (Level.getTile(c, r).getBounds().contains(x, y)) {
						return true;
					}
				}
			}
		}
		return false;
	}


	private void turn() {
		facing = Util.getAngle(dX, dY);
	}

	public int gettSign() {
		return tSign;
	}

	public void settSign(int tSign) {
		this.tSign = tSign;
	}

	public float gettX() {
		return tX;
	}

	public void settX(float tX) {
		this.tX = tX;
	}

	public float gettY() {
		return tY;
	}

	public void settY(float tY) {
		this.tY = tY;
	}

	public float getFacing() {
		return facing;
	}

	public float getSize() {
		return size;
	}

	public void destroy() {
		destroy = true;
	}

	private static final float distance = 1;
	public void findOwnT(float xOffset, float yOffset) {todo//TODO
		if (Util.getDistance(x, y, owner.x, owner.y)>1||Util.BrokenByBooleanArray(/*blah*/)) {
			pathFinder.setPath(x, y, owner.x+HALF_PLAYER_SIZE, owner.y+HALF_PLAYER_SIZE);
			if (pathFinder.getCurrentPath().size()>1) {
				if (checkWallCollision()) {
					tX = pathFinder.getCurrentPoint().get(1).x;
					tY = pathFinder.getCurrentPoint().get(1).y;
				}
				else {
					tX = pathFinder.getCurrentPoint().get(1).x+XOffset;
					tY = pathFinder.getCurrentPoint().get(1).y+yOffset;
				}
				
			}
		}
	}
}
