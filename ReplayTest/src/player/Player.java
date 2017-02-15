package player;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import data.Data;
import data.PlayerData;
import level.Level;
import main.Main;

public class Player implements PlayerData, Data {
	private final int UP = 3, DOWN = 1, LEFT = 2, RIGHT = 0;

	private double x, y, dX, dY, ddX, ddY, facing;
	private boolean movingUp, movingDown, movingLeft, movingRight;
	private HitboxPoint[][] hitboxPoints = new HitboxPoint[2][2];
	private HitboxBar[] hitboxBars = new HitboxBar[4];
	private boolean[] canMove = new boolean[4];//r,d,l,u

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		for (int i = 0;i<4;i++) {
			canMove[i] = false;
		}
		for (int i = 0;i<2;i++) {
			for (int j = 0;j<2;j++) {
				hitboxPoints[i][j] = new HitboxPoint();
			}
		}
		for (int i = 0;i<4;i++) {
			hitboxBars[i] = new HitboxBar();
		}
	}

	private void checkWallCollision() {//TODO
		final int radius = 2;
		setAllCanMove(true);
		for (int i = 0;i<2;i++) {
			for (int j = 0;j<2;j++) {
				getHitboxPoint(i, j).move(x+i*PLAYER_SIZE, y+j*PLAYER_SIZE);
				getHitboxPoint(i, j).setTouching(false);
			}
		}
		for (int i = 0;i<4;i++) {
			getHitboxBar(i).setTouching(false);
		}

		getHitboxBar(UP).move(x-(1d/Main.getScale()), y-(1d/Main.getScale()), x+PLAYER_SIZE+(1d/Main.getScale()), y-(1d/Main.getScale()));
		getHitboxBar(DOWN).move(x-(1d/Main.getScale()), y+PLAYER_SIZE+(1d/Main.getScale()), x+PLAYER_SIZE+(1d/Main.getScale()), y+PLAYER_SIZE+(1d/Main.getScale()));
		getHitboxBar(LEFT).move(x-(1d/Main.getScale()), y-(1d/Main.getScale()), x-(1d/Main.getScale()), y+PLAYER_SIZE+(1d/Main.getScale()));
		getHitboxBar(RIGHT).move(x+PLAYER_SIZE+(1d/Main.getScale()), y-(1d/Main.getScale()), x+PLAYER_SIZE+(1d/Main.getScale()), y+PLAYER_SIZE+(1d/Main.getScale()));

		for (int r = (int)(y-radius);r<y+radius;r++) {
			for (int c = (int)(x-radius);c<x+radius;c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid()) {
					/*for (int i = 0;i<2;i++) {
						for (int j = 0;j<2;j++) {
							if (Level.getTile(r, c).getBounds().contains(getHitboxPoint(i, j).getX(), getHitboxPoint(i, j).getY())) {
								getHitboxPoint(i, j).setTouching(true);
							}
						}
					}*/
					for (int i = 0;i<4;i++) {
						if (Level.getTile(r, c).getBounds().intersectsLine(getHitboxBar(i).getBar())) {
							getHitboxBar(i).setTouching(true);
						}
					}
				}
			}
		}
		/*if (getHitboxPoint(0, 0).isTouching()&&getHitboxPoint(1, 0).isTouching()) setCanMove(UP, false);
		if (getHitboxPoint(0, 1).isTouching()&&getHitboxPoint(1, 1).isTouching()) setCanMove(DOWN, false);
		if (getHitboxPoint(0, 0).isTouching()&&getHitboxPoint(0, 1).isTouching()) setCanMove(LEFT, false);
		if (getHitboxPoint(1, 0).isTouching()&&getHitboxPoint(1, 1).isTouching()) setCanMove(RIGHT, false);*/

		if (getHitboxBar(UP).isTouching()) setCanMove(UP, false);
		if (getHitboxBar(DOWN).isTouching()) setCanMove(DOWN, false);
		if (getHitboxBar(LEFT).isTouching()) setCanMove(LEFT, false);
		if (getHitboxBar(RIGHT).isTouching()) setCanMove(RIGHT, false);


	}

	public void tick() {
		turn();
		move();
		checkWallCollision();

	}

	private void turn() {
		//TODO
	}

	private void move() {
		accelerate();
		acceleratingLimitCheck();
		notAcceleratingCheck();
		dX+=ddX;
		dY+=ddY;
		deccelerate();
		speedLimitCheck();
		notMovingCheck();

		move(dX, dY);
	}

	private void move(double dX, double dY) {//TODO
		double inc = 1d/UPS, remaining, sign;
		remaining = Math.abs(dX);
		sign = Math.signum(dX);
		while (remaining>0) {
			checkWallCollision();
			if (sign>0&&!getCanMove(RIGHT)) break;
			if (sign<0&&!getCanMove(LEFT)) break;
			if (remaining>=inc) x+=inc*sign;
			else x+=remaining*sign;
			remaining-=inc;
		}
		remaining = Math.abs(dY);
		sign = Math.signum(dY);
		while (remaining>0) {
			checkWallCollision();
			if (sign<0&&!getCanMove(UP)) break;
			if (sign>0&&!getCanMove(DOWN)) break;
			if (remaining>=inc) y+=inc*sign;
			else y+=remaining*sign;
			remaining-=inc;
		}
	}

	private void accelerate() {//affects ddX and ddY
		if (isMovingUp()) {
			ddY-=PLAYER_ACCELERATION;
			if (ddY>0) ddY = 0;
		}
		if (isMovingDown()) {
			ddY+=PLAYER_ACCELERATION;
			if (ddY<0) ddY = 0;
		}
		if (isMovingLeft()) {
			ddX-=PLAYER_ACCELERATION;
			if (ddX>0) ddX = 0;
		}
		if (isMovingRight()) {
			ddX+=PLAYER_ACCELERATION;
			if (ddX<0) ddX = 0;
		}
	}

	private void notAcceleratingCheck() {//affects ddX and ddY
		if (isMovingUp()==isMovingDown()) {
			ddY = 0;
		}
		if (isMovingLeft()==isMovingRight()) {
			ddX = 0;
		}
	}

	private void acceleratingLimitCheck() {//affects ddX and ddY
		if (Math.abs(ddX)>PLAYER_ACCELERATION_LIMIT) {
			ddX = Math.signum(ddX)*PLAYER_ACCELERATION_LIMIT;
		}
		if (Math.abs(ddY)>PLAYER_ACCELERATION_LIMIT) {
			ddY = Math.signum(ddY)*PLAYER_ACCELERATION_LIMIT;
		}
	}

	private void deccelerate() {//affects dX and dY
		if (Math.abs(dX)>0) {
			dX*=PLAYER_DECCELERATION;
		}
		if (Math.abs(dY)>0) {
			dY*=PLAYER_DECCELERATION;
		}
	}

	private void speedLimitCheck() {//affects dX and dY
		if (Math.abs(dX)>PLAYER_SPEED_LIMIT) {
			dX = Math.signum(dX)*PLAYER_SPEED_LIMIT;
		}
		if (Math.abs(dY)>PLAYER_SPEED_LIMIT) {
			dY = Math.signum(dY)*PLAYER_SPEED_LIMIT;
		}
	}

	public void notMovingCheck() {//affects dX and dY
		if (Math.abs(dX)<0.0001d) {
			dX = 0;
		}
		if (Math.abs(dY)<0.0001d) {
			dY = 0;
		}
	}

	public HitboxBar[] getHitboxBars() {
		return hitboxBars;
	}

	public boolean[] getCanMove() {
		return canMove;
	}

	public HitboxPoint getHitboxPoint(int x, int y) {
		return hitboxPoints[x][y];
	}

	public HitboxBar getHitboxBar(int side) {
		return hitboxBars[side];
	}

	public double getFacing() {
		return facing;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getdX() {
		return dX;
	}

	public double getdY() {
		return dY;
	}

	public double getddX() {
		return ddX;
	}

	public double getddY() {
		return ddY;
	}

	private void setAllCanMove(boolean value) {
		for (int i = 0;i<4;i++) {
			canMove[i] = value;
		}
	}

	private void setCanMove(int direction, boolean value) {
		canMove[direction] = value;
	}

	public boolean getCanMove(int direction) {
		return canMove[direction];
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public HitboxPoint[][] getHitboxPoints() {
		// TODO Auto-generated method stub
		return hitboxPoints;
	}

	public Rectangle2D getBounds(double offsetX, double offsetY) {
		return new Rectangle((int)((x+offsetX)*Main.getScale()), (int)((y+offsetY)*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()));
	}
}
