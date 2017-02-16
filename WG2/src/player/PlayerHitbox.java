package player;

import java.awt.geom.Rectangle2D;

import data.Data;
import data.PlayerData;
import level.Tile;

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
		for (int i = 0;i<4;i++) {
			if (tile.getBounds().intersects(sides[i])) {
				touching[i] = true;
			}
		}
	}

	public void move(double x, double y) {//FIXME
		for (int i = 0;i<4;i++) {
			touching[i] = false;
		}
//		sides[UP].setRect(x, y-1d/Main.getScale(), PLAYER_SIZE-2d/Main.getScale(), 1d/Main.getScale());
//		sides[DOWN].setRect(x, y+PLAYER_SIZE-1d/Main.getScale(), PLAYER_SIZE-2d/Main.getScale(), 1d/Main.getScale());
//		sides[LEFT].setRect(x-1d/Main.getScale(), y, 1d/Main.getScale(), PLAYER_SIZE-2d/Main.getScale());
//		sides[RIGHT].setRect(x+PLAYER_SIZE-1d/Main.getScale(), y, 1d/Main.getScale(), PLAYER_SIZE-2d/Main.getScale());
		final double pS = PLAYER_SIZE, s = 0.02d;//player size, width of hitbox rectangle
		sides[UP].setRect(x+s, y, pS-s, s);
		sides[DOWN].setRect(x+s, y+pS, pS-s, s);
		sides[RIGHT].setRect(x+pS, y+s, s, pS-s);
		sides[LEFT].setRect(x, y+s, s, pS-s);
	}

	public Rectangle2D getSide(int side) {
		return sides[side];
	}

	public boolean[] getSides() {
		return touching;
	}
}
