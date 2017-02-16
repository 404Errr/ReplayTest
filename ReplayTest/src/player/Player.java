package player;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import data.Data;
import data.PlayerData;
import level.Level;
import main.Main;

public class Player implements PlayerData, Data {
	private double x, y, dX, dY, ddX, ddY, facing;
	private boolean[] canMove = new boolean[4], movementKeyPressed = new boolean[4];//r,d,l,u

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		for (int i = 0;i<4;i++) {
			canMove[i] = false;
		}
	}

	private void checkWallCollision() {//TODO
		final int radius = 2;
		setAllCanMove(true);
		for (int r = (int)y-radius;r<y+radius;r++) {
			for (int c = (int)x-radius;c<x+radius;c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid()) {
					if (Level.getTile(r, c).getBounds().contains(x, y)) {

					}
				}
			}
		}


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
		if (getMovementKeyPressed(UP)) {
			ddY-=PLAYER_ACCELERATION;
			if (ddY>0) ddY = 0;
		}
		if (getMovementKeyPressed(DOWN)) {
			ddY+=PLAYER_ACCELERATION;
			if (ddY<0) ddY = 0;
		}
		if (getMovementKeyPressed(LEFT)) {
			ddX-=PLAYER_ACCELERATION;
			if (ddX>0) ddX = 0;
		}
		if (getMovementKeyPressed(RIGHT)) {
			ddX+=PLAYER_ACCELERATION;
			if (ddX<0) ddX = 0;
		}
	}

	private void notAcceleratingCheck() {//affects ddX and ddY
		if (getMovementKeyPressed(UP)==getMovementKeyPressed(DOWN)) {
			ddY = 0;
		}
		if (getMovementKeyPressed(LEFT)==getMovementKeyPressed(RIGHT)) {
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

	public boolean[] getCanMove() {
		return canMove;
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

	public boolean getMovementKeyPressed(int direction) {
		return movementKeyPressed[direction];
	}

	public void setMovementKeyPressed(int direction, boolean value ) {
		movementKeyPressed[direction] = value;
	}

	public Rectangle2D getBounds(double offsetX, double offsetY) {
		return new Rectangle((int)((x+offsetX)*Main.getScale()), (int)((y+offsetY)*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()));
	}
}
