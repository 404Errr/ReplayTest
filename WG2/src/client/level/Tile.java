package client.level;

import java.awt.geom.Rectangle2D;

import shared.data.MapData;
import shared.data.TileData;

public class Tile implements MapData {
	private int type;//the type
	private boolean[] solid;//if its solid (0 = walls, 1 = projectiles)
	private Rectangle2D bounds;

	public Tile(int r, int c, int type) {
		this.type = type;
		solid = TileData.getSolid(type);
		bounds = new Rectangle2D.Double(c, r, 1, 1);
	}

	public Rectangle2D getBounds() {
		return bounds;
	}

	public boolean isSolid(int type) {
		return solid[type];
	}

	public int getType() {
		return type;
	}


}
