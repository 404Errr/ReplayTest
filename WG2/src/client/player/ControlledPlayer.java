package client.player;

import client.input.Cursor;
import shared.data.Data;
import shared.data.PlayerData;
import shared.util.Util;

public class ControlledPlayer extends Player implements PlayerData, Data {
	private boolean[] movementKeyPressed;//r,d,l,u

	public ControlledPlayer(double x, double y) {//creates at given x and y
		super(x, y);
		movementKeyPressed = new boolean[4];
	}

	@Override
	public void tick() {
		turn();//rotate the player toward cursor
		move();//move the player
		super.checkCollision();//check for collision (neccessary)
	}

	private void turn() {
		setFacing(Util.getAngle(x, y, Cursor.getXGrid(), Cursor.getYGrid()));//rotate the player toward cursor
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

		dPosition(dX, dY);//move the player by the velocity
	}

	private void dPosition(double dX, double dY) {
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

	private boolean speedLimitCheck() {//returns true if it had to enforce the limit (not in use), affects dX and dY
		boolean enforced = false;//if it enforced the limit
		if (Math.abs(dX)>PLAYER_SPEED_LIMIT) {//if over the limit
			dX = Math.signum(dX)*PLAYER_SPEED_LIMIT;//set to the limit
			enforced = true;
		}
		if (Math.abs(dY)>PLAYER_SPEED_LIMIT) {
			dY = Math.signum(dY)*PLAYER_SPEED_LIMIT;
			enforced = true;
		}
		return enforced;
	}

	public void notMovingCheck() {//affects dX and dY
		if (Math.abs(dY)<0.0001d||(!canMove(UP)&&dY<0)||(!canMove(DOWN)&&dY>0)) {//if velocity should be rounded to 0 or if cant move in the direction the velocity is set to
			dY = 0;//set velocity to 0
		}
		if (Math.abs(dX)<0.0001d||(!canMove(LEFT)&&dX<0)||(!canMove(RIGHT)&&dX>0)) {
			dX = 0;
		}
	}

	public boolean isMovementKeyPressed(int direction) {
		return movementKeyPressed[direction];
	}

	public void setMovementKeyPressed(int direction, boolean value ) {
		movementKeyPressed[direction] = value;
	}
}
