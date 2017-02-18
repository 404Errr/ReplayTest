package client.level;

import java.awt.geom.Rectangle2D;

import data.MapData;
import data.TileData;

public class Tile implements MapData {
	private int c, r, type;//the type
	private boolean solid;//if its solid (wall collision)

	public Tile(int r, int c, int type) {
		this.type = type;
		solid = TileData.getSolid(type);
		this.c = c;
		this.r = r;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(c, r, 1, 1);
	}

	public int getC() {
		return c;
	}

	public int getR() {
		return r;
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public int getType() {
		return type;
	}


}
