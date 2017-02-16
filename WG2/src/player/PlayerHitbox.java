package player;

import java.awt.geom.Rectangle2D;

import data.Data;
import data.PlayerData;
import level.Tile;
import main.Main;

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

	public void move(double x, double y) {
		for (int i = 0;i<4;i++) {
			touching[i] = false;
		}
		sides[UP].setRect(x, y-1d/Main.getScale(), PLAYER_SIZE-1d/Main.getScale(), 1d/Main.getScale());
		sides[DOWN].setRect(x, y+PLAYER_SIZE-1d/Main.getScale(), PLAYER_SIZE-1d/Main.getScale(), 1d/Main.getScale());
		sides[LEFT].setRect(x-1d/Main.getScale(), y, 1d/Main.getScale(), PLAYER_SIZE-1d/Main.getScale());
		sides[RIGHT].setRect(x+PLAYER_SIZE-1d/Main.getScale(), y, 1d/Main.getScale(), PLAYER_SIZE-1d/Main.getScale());
	}

	public Rectangle2D getSide(int side) {
		return sides[side];
	}

	public boolean[] getSides() {
		return touching;
	}
}
