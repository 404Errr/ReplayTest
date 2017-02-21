package client.player;

import java.awt.Color;

import client.entity.Entity;
import data.Data;
import data.PlayerData;
import data.TileData;

public abstract class Player extends Entity implements PlayerData, Data, TileData {
	protected float facing;//facing is in radians
//	protected boolean[] canMove;//r,d,l,u
	protected Color color;

	public Player(Color color, float x, float y) {
		super(color, x, y);
		this.color = color;
		//canMove = new boolean[4];
		//setAllCanMove(true);//set all values in canMove to
	}

	public void move(float x, float y, float facing) {
		super.move(x, y);
		setFacing(facing);
	}





	/*private void checkWallCollision() {
		final int radius = 2;
		for (int r = (int)y-radius;r<y+radius;r++) {//for each row within the radius
			for (int c = (int)x-radius;c<x+radius;c++) {//for each collumn within the radius
				if (r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth()&&Level.getTile(r, c).isSolid(SOLID_WALLS)) {//bounds check and if tile is solid
					hitbox.checkCollision(Level.getTile(r, c));//feed tile to hitbox
				}
			}
		}
		for (int i = 0;i<4;i++) {//for every value in canMove
			if (hitbox.getSides()[i]) {
				setCanMove(i, false);//set canMove[i] to the corresponding touching value in the hitbox
			}
		}
	}

	protected void checkCollision() {
		setAllCanMove(true);//set all of canMove to true
		hitbox.move(x, y);//move the hitbox to the player's current location
		checkWallCollision();//check for a wall collision
	}*/
//
//	@Override
//	public void tick() {
//		//checkCollision();//check for collision (neccessary)
//	}

//	public boolean[] getCanMove() {
//		return canMove;
//	}

	public double getFacing() {
		return facing;
	}

	protected void setFacing(float facing) {
		this.facing = facing;
	}

	public Color getColor() {
		return color;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getXCenter() {
		return x+HALF_PLAYER_SIZE/2f;
	}

	public float getYCenter() {
		return y+HALF_PLAYER_SIZE/2f;
	}

	public int getXTile() {
		return Math.round(x);
	}

	public int getYTile() {
		return Math.round(y);
	}

	public float getdX() {
		return dX;
	}

	public float getdY() {
		return dY;
	}

	public float getddX() {
		return ddX;
	}

	public float getddY() {
		return ddY;
	}

	@Override
	public void tick() {
		turn();
		move();
	}

	protected abstract void move();
	protected abstract void turn();

//	protected void setAllCanMove(boolean value) {//set all values in canMove to the given value
//		for (int i = 0;i<4;i++) {
//			canMove[i] = value;
//		}
//	}
//
//	protected void setCanMove(int direction, boolean value) {
//		canMove[direction] = value;
//	}
//
//	public boolean canMove(int direction) {
//		return canMove[direction];
//	}

}
