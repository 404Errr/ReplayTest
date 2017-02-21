package client.player;

import java.awt.Color;

import client.input.Cursor;
import data.Data;
import data.PlayerData;
import data.WeaponData;
import util.Util;

public class ControlledPlayer extends Player implements PlayerData, Data, WeaponData {
	private boolean[] movementKeyPressed;//r,d,l,u (see Data)


	public ControlledPlayer(Color color, float x, float y) {//creates at given x and y with given color
		super(color, x, y);
		movementKeyPressed = new boolean[4];
	}

	@Override
	public boolean tick() {
		super.tick();
		return false;
	}

	@Override
	protected void turn() {
		setFacing(Util.getAngle(x, y, Cursor.getGridX(), Cursor.getGridY()));//rotate the player toward cursor
	}

	@Override
	protected void move() {
		accelerate();//accelerate based on if movement keys are pressed
		accelerationLimitCheck();//checks if acceleration is over the limit, sets it to the limit if it is
		notAcceleratingCheck();//checks if it shouldnt be accelerating
		dX+=ddX;
		dY+=ddY;
		deccelerate();//deccelerate the player
		speedLimitCheck();//checks if velocity is over the limit, sets it to the limit if it is
		notMovingCheck();//chceks if player shouldnt be able to move in a direction or if dX/dY need to be rounded to 0
		dPosition(dX, dY);//move the player by the velocity while doing collision checks
	}

	@Override
	protected void accelerate() {//affects ddX and ddY
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

	@Override
	protected void notAcceleratingCheck() {//affects ddX and ddY
		super.notAcceleratingCheck();
		if ((isMovementKeyPressed(UP)==isMovementKeyPressed(DOWN))) {//if opposite movement keys have the same value or if cant move in the direction acceleration is set to
			ddY = 0;//stop accelerating
		}
		if ((isMovementKeyPressed(LEFT)==isMovementKeyPressed(RIGHT))) {
			ddX = 0;
		}
	}

	public boolean isMovementKeyPressed(int direction) {
		return movementKeyPressed[direction];
	}

	public void setMovementKeyPressed(int direction, boolean value ) {
		movementKeyPressed[direction] = value;
	}
}
