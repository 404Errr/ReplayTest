package client.player;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import client.level.Level;
import data.Data;
import data.PlayerData;
import main.Main;
import util.Util;

public class Player implements PlayerData, Data {
	private double x, y, dX, dY, ddX, ddY, facing;//facing is in radians
	private PlayerHitbox hitbox;
	private boolean[] canMove, movementKeyPressed;//r,d,l,u

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		hitbox = new PlayerHitbox();
		canMove = new boolean[4];
		movementKeyPressed = new boolean[4];
		setAllCanMove(false);//set all values in canMove to false
	}

	public void move(double x, double y, double dX, double dY, double ddX, double ddY, double facing) {
		this.x = x;
		this.y = y;
		this.dX = dX;
		this.dY = dY;
		this.ddX = ddX;
		this.ddY = ddY;
		this.facing = facing;
	}

	private void checkWallCollision() {
		final int radius = 2;
		for (int r = (int)y-radius;r<y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid()) {//bounds check and if tile is solid
					hitbox.checkCollision(Level.getTile(r, c));//feed tile to hitbox
				}
			}
		}
		for (int i = 0;i<4;i++) {//for canMove
			if (hitbox.getSides()[i]) {
				setCanMove(i, false);//set canMove to the corresponding touching value in hitbox
			}
		}
	}

	private void checkCollision() {
		setAllCanMove(true);//set all of canMove to true
		hitbox.move(x, y);//move the hitbox to the player's current location
		checkWallCollision();//check for a wall collision
	}

	public void tick() {
		turn();//rotate the player toward cursor
		move();//move the player
	}

	private void turn() {
		setFacing(Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY()));//rotate the player toward cursor
	}

	private void move() {
		accelerate();//accelerate based on if movement keys are pressed
		acceleratingLimitCheck();//checks if acceleration is over the limit, sets it to the limit if it is
		notAcceleratingCheck();//checks if it shouldnt be accelerating
		dX+=ddX;
		dY+=ddY;
		deccelerate();//deccelerate the player
		speedLimitCheck();//checks if velocity is over the limit, sets it to the limit if it is
		notMovingCheck();//chceks if player shouldnt be able to move in a direction or if dX/dY need to be rounded to 0

		move(dX, dY);//move the player by the velocity
	}

	private void move(double dX, double dY) {
		double inc = 0.5d/UPS, remaining, sign;//inc - the increment between collision checks
		remaining = Math.abs(dX);//the magnitude of dX
		sign = Math.signum(dX);//the sign of dX
		while (remaining>0) {
			checkCollision();//check for collision
			if (sign>0&&!canMove(RIGHT)) break;//if cant move right, stop changing x
			if (sign<0&&!canMove(LEFT)) break;//if cant move left, stop changing x
			if (remaining>=inc) x+=inc*sign;//if remaining isnt smaller than increment, change x by increment
			else x+=remaining*sign;//if it is, change x by remaining
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
		if (isMovementKeyPressed(UP)) {//if the movement key is pressed
			ddY-=PLAYER_ACCELERATION;//change acceleration by PLAYER_ACCELERATION
			if (ddY>0) ddY = 0;//if acceleration is in the opposite direction of the movement key, reset it to 0
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
		if ((isMovementKeyPressed(UP)==isMovementKeyPressed(DOWN))||(!canMove(UP)&&ddY<0)||(!canMove(DOWN)&&ddY>0)) {//if opposite movement keys have the same value or if cant move in the direction acceleration is set to
			ddY = 0;//stop accelerating
		}
		if ((isMovementKeyPressed(LEFT)==isMovementKeyPressed(RIGHT))||(!canMove(LEFT)&&ddX<0)||(!canMove(RIGHT)&&ddX>0)) {
			ddX = 0;
		}
	}

	private void acceleratingLimitCheck() {//affects ddX and ddY
		if (Math.abs(ddX)>PLAYER_ACCELERATION_LIMIT) {//if over the limit
			ddX = Math.signum(ddX)*PLAYER_ACCELERATION_LIMIT;//set to the limit
		}
		if (Math.abs(ddY)>PLAYER_ACCELERATION_LIMIT) {
			ddY = Math.signum(ddY)*PLAYER_ACCELERATION_LIMIT;
		}
	}

	private void deccelerate() {//affects dX and dY
		if (Math.abs(dX)>0) {//if velocity>0
			dX*=PLAYER_DECCELERATION;//multiply velocity by PLAYER_DECCELERATION (less than 1)
		}
		if (Math.abs(dY)>0) {
			dY*=PLAYER_DECCELERATION;
		}
	}

	private void speedLimitCheck() {//affects dX and dY
		if (Math.abs(dX)>PLAYER_SPEED_LIMIT) {//if over the limit
			dX = Math.signum(dX)*PLAYER_SPEED_LIMIT;//set to the limit
		}
		if (Math.abs(dY)>PLAYER_SPEED_LIMIT) {
			dY = Math.signum(dY)*PLAYER_SPEED_LIMIT;
		}
	}

	public void notMovingCheck() {//affects dX and dY
		if (Math.abs(dY)<0.0001d||(!canMove(UP)&&dY<0)||(!canMove(DOWN)&&dY>0)) {//if velocity should be rounded to 0 or if cant move in the direction the velocity is set to
			dY = 0;//set velocity to 0
		}
		if (Math.abs(dX)<0.0001d||(!canMove(LEFT)&&dX<0)||(!canMove(RIGHT)&&dX>0)) {
			dX = 0;
		}
	}

	public boolean[] getCanMove() {
		return canMove;
	}

	public double getFacing() {
		return facing;
	}

	public void setFacing(double facing) {
		this.facing = facing;
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

	private void setAllCanMove(boolean value) {//set all values in canMove to the given value
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

	public Rectangle2D getBounds(double offsetX, double offsetY) {//get the bounds of the player offset
		return new Rectangle((int)((x+offsetX+PLAYER_SIZE/2)*Main.getScale()), (int)((y+offsetY+PLAYER_SIZE/2)*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()), (int)(PLAYER_SIZE*Main.getScale()));
	}

	public PlayerHitbox getHitbox() {
		return hitbox;
	}


}
