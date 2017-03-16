package client.weapon.weapon;

import java.awt.geom.Rectangle2D;

import client.level.Level;
import client.player.Player;
import client.weapon.WeaponEntity;
import data.TileData;
import data.WeaponData;
import util.Util;

public class Triangle extends WeaponEntity implements WeaponData, TileData {
	private float facing, size, tX, tY;
	private int tSign;
	private boolean destroy;
	private boolean[] canMove;

	public Triangle(Player owner, float x, float y, float damage) {
		super(owner.getColor(), x, y, damage);
		size = RCTRIANGLE_SIZE;
		canMove = new boolean[4];
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
//		x+=dX;
//		y+=dY;
	}

	private void dPosition(float dX, float dY) {
		setAllCanMove(true);//sets all values in canMove to true
		float inc = 0.025f, remaining, sign;//inc - the increment between collision checks
		remaining = Math.abs(dX);//the magnitude of dX
		sign = Math.signum(dX);//the sign of dX
		while (remaining>0) {
			if (remaining>=inc) x+=inc*sign;//if remaining isnt smaller than increment, change x by increment
			else x+=remaining*sign;//if it is, change x by remaining
			if (checkWallCollision()) {//if hit something
				if (sign>0) setCanMove(RIGHT, false);//if was trying to move to the right, setcanmove right to false
				if (sign<0) setCanMove(LEFT, false);
				x = Math.round(x);//reallign to grid
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
				if (sign<0) setCanMove(UP, false);
				if (sign>0) setCanMove(DOWN, false);
				y = Math.round(y);
				break;
			}
			remaining-=inc;
		}
	}

	private boolean checkWallCollision() {
		final Rectangle2D hitbox = new Rectangle2D.Float(x, y, size, size);
		for (int r = getYTile()-1;r<getYTile()+2;r++) {//for each row within the radius
			for (int c = getXTile()-1;c<getXTile()+2;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(c, r).isSolid(SOLID_WALLS)) {//bounds check and if tile is solid
					if (Level.getTile(c, r).getBounds().intersects(hitbox)) {
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

	protected boolean canMove(int side) {
		return canMove[side];
	}

	protected void setCanMove(int side, boolean value) {
		canMove[side] = value;
	}

	protected void setAllCanMove(boolean value) {
		for (int i = 0;i<4;i++) {
			canMove[i] = value;
		}
	}
}
