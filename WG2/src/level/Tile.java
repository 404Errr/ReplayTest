package level;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import data.MapData;

public class Tile implements MapData {
	private int type;
	private Rectangle2D bounds;
	private boolean solid;

	public Tile(int r, int c, int type) {
		this.type = type;
		if (type==1) solid = true;
		this.bounds = new Rectangle(c, r, 1, 1);//switched r and c
	}

	public Rectangle2D getBounds() {
		return bounds;
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
