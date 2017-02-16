package player;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import data.Data;
import data.PlayerData;
import level.Level;
import main.Main;

public class Player implements PlayerData, Data {
	private double x, y, dX, dY, ddX, ddY, facing;
	private PlayerHitbox hitbox;
	private boolean[] canMove, movementKeyPressed;//r,d,l,u

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		hitbox = new PlayerHitbox();
		canMove = new boolean[4];
		movementKeyPressed = new boolean[4];
		setAllCanMove(false);
	}

	private void checkWallCollision() {//TODO
		final int radius = 2;
		for (int r = (int)y-radius;r<y+radius;r++) {
			for (int c = (int)x-radius;c<x+radius;c++) {
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid()) {
					hitbox.checkCollision(Level.getTile(r, c));
				}
			}
		}
		for (int i = 0;i<4;i++) {
			if (hitbox.getSides()[i]) {
				setCanMove(i, false);
			}
		}
	}

	private void checkCollision() {
		setAllCanMove(true);
		hitbox.move(x, y);
		checkWallCollision();
	}

	public void tick() {
		turn();
		move();
		checkCollision();
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

	private void move(double dX, double dY) {
		double inc = 1d/UPS, remaining, sign;
		remaining = Math.abs(dX);
		sign = Math.signum(dX);
		while (remaining>0) {
			checkCollision();
			if (sign>0&&!canMove(RIGHT)) break;
			if (sign<0&&!canMove(LEFT)) break;
			if (remaining>=inc) x+=inc*sign;
			else x+=remaining*sign;
			remaining-=inc;
		}
		remaining = Math.abs(dY);
		sign = Math.signum(dY);
		while (remaining>0) {
			checkCollision();
			if (sign<0&&!canMove(UP)) break;
			if (sign>0&&!canMove(DOWN)) break;
			if (remaining>=inc) y+=inc*sign;
			else y+=remaining*sign;
			remaining-=inc;
		}
	}

	private void accelerate() {//affects ddX and ddY
		if (isMovementKeyPressed(UP)) {
			ddY-=PLAYER_ACCELERATION;
			if (ddY>0) ddY = 0;
		}
		if (isMovementKeyPressed(DOWN)) {
			ddY+=PLAYER_ACCELERATION;
			if (ddY<0) ddY = 0;
		}
		if (isMovementKeyPressed(LEFT)) {
			ddX-=PLAYER_ACCELERATION;
			if (ddX>0) ddX = 0;
		}
		if (isMovementKeyPressed(RIGHT)) {
			ddX+=PLAYER_ACCELERATION;
			if (ddX<0) ddX = 0;
		}
	}

	private void notAcceleratingCheck() {//affects ddX and ddY
		if (isMovementKeyPressed(UP)==isMovementKeyPressed(DOWN)) {
			ddY = 0;
		}
		if (isMovementKeyPressed(LEFT)==isMovementKeyPressed(RIGHT)) {
			ddX = 0;
		}
		if (!canMove(UP)&&ddY<0) ddY = 0;
		if (!canMove(DOWN)&&ddY>0) ddY = 0;
		if (!canMove(LEFT)&&ddX<0) ddX = 0;
		if (!canMove(RIGHT)&&ddX>0) ddX = 0;
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

	public boolean canMove(int direction) {
		return canMove[direction];
	}

	public boolean isMovementKeyPressed(int direction) {
		return movementKeyPressed[direction];
	}

	public void setMovementKeyPressed(int direction, boolean value ) {
		movementKeyPressed[direction] = value;
	}

	public Rectangle2D getBounds(double offsetX, double offsetY) {
		return new Rectangle((int)((x+offsetX)*Main.getScale()), (int)((y+offsetY)*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()));
	}

	public PlayerHitbox getHitbox() {
		return hitbox;
	}


}
