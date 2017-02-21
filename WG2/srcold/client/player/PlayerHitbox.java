package client.player;

import java.awt.geom.Rectangle2D;

import client.level.Tile;
import shared.data.Data;
import shared.data.PlayerData;

public class PlayerHitbox implements Data, PlayerData {

	private Rectangle2D[] sides;
	private boolean[] touching;

	public PlayerHitbox() {
		sides = new Rectangle2D.Double[4];
		touching = new boolean[4];
		for (int i = 0;i<4;i++) {
			sides[i] = new Rectangle2D.Double();
		}
	}

	public void checkCollision(Tile tile) {
		for (int i = 0;i<4;i++) {//for each side
			if (tile.getBounds().intersects(sides[i])) {//if side is touching the given tile
				touching[i] = true;//set touching on that side to true
			}
		}
	}

	public void move(double x, double y) {//move the hitbox to given x and y
		setAllTouching(false);//set all values in touching to false
		final double playerSize = PLAYER_SIZE, barWidth = PLAYER_HITBOX_WIDTH;//player size, width of hitbox rectangle
		sides[UP].setRect(x+barWidth, y, playerSize-barWidth, barWidth);//move the bars
		sides[DOWN].setRect(x+barWidth, y+playerSize, playerSize-barWidth, barWidth);
		sides[RIGHT].setRect(x+playerSize, y+barWidth, barWidth, playerSize-barWidth);
		sides[LEFT].setRect(x, y+barWidth, barWidth, playerSize-barWidth);
	}

	private void setAllTouching(boolean value) {//set all values in touching to the given value
		for (int i = 0;i<4;i++) {
			touching[i] = value;
		}
	}

	public Rectangle2D getSide(int side) {
		return sides[side];
	}

	public boolean[] getSides() {
		return touching;
	}
}
