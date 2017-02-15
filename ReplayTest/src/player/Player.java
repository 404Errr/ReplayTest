package player;

import data.PlayerData;
import level.Level;

public class Player implements PlayerData {
	private double x, y, dX, dY, ddX, ddY, facing;
	private boolean movingUp, movingDown, movingLeft, movingRight;
	private HitboxPoint[][] hitboxPoints = new HitboxPoint[2][2];

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		for (int i = 0;i<2;i++) {
			for (int j = 0;j<2;j++) {
				hitboxPoints[i][j] = new HitboxPoint();
			}
		}
	}

	private void checkWallCollision() {//TODO
		final int radius = 3;
		for (int i = 0;i<2;i++) {
			for (int j = 0;j<2;j++) {
				hitboxPoints[i][j].move(x+i*PLAYER_SIZE, y+j*PLAYER_SIZE);
				hitboxPoints[i][j].setTouching(false);
			}
		}
		for (int r = (int)(y-radius);r<y+radius;r++) {
			for (int c = (int)(x-radius);c<x+radius;c++) {
				if (Level.getTile(r, c).isSolid()&&r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()) {
					for (int i = 0;i<2;i++) {
						for (int j = 0;j<2;j++) {
							if (Level.getTile(r, c).getBounds().contains(hitboxPoints[i][j].getX(), hitboxPoints[i][j].getY())) {
								hitboxPoints[i][j].setTouching(true);
							}
						}
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

	public HitboxPoint getHitboxPoint(int x, int y) {
		return hitboxPoints[x][y];
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


}
