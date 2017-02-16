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
		if (type!=0) solid = true;
		this.bounds = new Rectangle(c, r, 1, 1);//switched r and c
	}

	public Rectangle2D getBounds() {
		return bounds;
	}

	public Rectangle2D getBounds(double xOffset, double yOffset) {
		return new Rectangle2D.Double(bounds.getX()+xOffset, bounds.getY()+yOffset, bounds.getWidth(), bounds.getHeight());//TODO FIXME
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