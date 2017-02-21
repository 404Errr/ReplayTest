package client.player;

import java.awt.Color;

import data.Data;
import data.PlayerData;
import data.TileData;

public class Player implements PlayerData, Data, TileData {
	protected double x, y, dX, dY, ddX, ddY, facing;//facing is in radians
	protected boolean[] canMove;//r,d,l,u
	protected Color color;

	public Player(Color color, double x, double y) {
		move(x, y);
		this.color = color;
		canMove = new boolean[4];
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

	public void move(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void move(double x, double y, double facing) {
		this.x = x;
		this.y = y;
		this.facing = facing;
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

	public void tick() {
		//checkCollision();//check for collision (neccessary)
	}

	public boolean[] getCanMove() {
		return canMove;
	}

	public double getFacing() {
		return facing;
	}

	protected void setFacing(double facing) {
		this.facing = facing;
	}

	public Color getColor() {
		return color;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getXCenter() {
		return x+PLAYER_SIZE/2;
	}

	public double getYCenter() {
		return y+PLAYER_SIZE/2;
	}

	public int getXTile() {
		return (int)Math.round(x);
	}

	public int getYTile() {
		return (int)Math.round(y);
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

	protected void setAllCanMove(boolean value) {//set all values in canMove to the given value
		for (int i = 0;i<4;i++) {
			canMove[i] = value;
		}
	}

	protected void setCanMove(int direction, boolean value) {
		canMove[direction] = value;
	}

	public boolean canMove(int direction) {
		return canMove[direction];
	}
}
