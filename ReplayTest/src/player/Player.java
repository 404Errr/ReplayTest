package player;

import java.awt.Point;

import data.PlayerData;

public class Player implements PlayerData {
	private double x, y, dX, dY, ddX, ddY;
	private double facing;
	private boolean movingUp, movingDown, movingLeft, movingRight;
	private Point[][] hitboxPoints = new Point[2][2];

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		for (int i = 0;i<2;i++) {
			for (int j = 0;j<2;j++) {
				hitboxPoints[i][j] = new Point();
			}
		}
		updateHitbox();
	}

	private void updateHitbox() {
//		hitboxPoints[0][0].setLocation(x, y);//FIXME
	}

	public void tick() {
//		turn();
		updateHitbox();
		move();

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
		x+=dX;
		y+=dY;
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
		if (!isMovingUp()&&!isMovingDown()) {
			ddY = 0;
		}
		if (!isMovingLeft()&&!isMovingRight()) {
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

	public double getFacing() {
		return facing;
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}

	public double getXDouble() {
		return x;
	}

	public double getYDouble() {
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


}
