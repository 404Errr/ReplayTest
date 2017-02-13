package level;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import data.MapData;
import main.Main;

public class Tile implements MapData {
	private int r, c, type;
	private Rectangle2D bounds;

	public Tile(int r, int c, int type) {
		this.r = r;
		this.c = c;
		this.type = type;
		this.bounds = new Rectangle(c*Main.getScale(), r*Main.getScale(), Main.getScale(), Main.getScale());//switched r and c
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

	public Rectangle2D getBounds() {
		return bounds;
	}

	public int getType() {
		return type;
	}
}
