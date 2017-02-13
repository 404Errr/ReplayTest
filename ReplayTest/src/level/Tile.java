package level;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import data.MapData;

public class Tile implements MapData {
	private int r, c, type;
	private Rectangle2D location;

	public Tile(int r, int c, int type) {
		this.r = r;
		this.c = c;
		this.type = type;
		this.location = new Rectangle(c*MAP_TILE_SIZE, r*MAP_TILE_SIZE, MAP_TILE_SIZE, MAP_TILE_SIZE);//switches r and c
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	public Rectangle2D getBounds() {
		return location;
	}

	public int getType() {
		return type;
	}
}
