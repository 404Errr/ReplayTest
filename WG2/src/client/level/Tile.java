package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import shared.data.ColorData;
import shared.data.MapData;
import shared.data.TileData;

public class Tile implements MapData {
	private Color color;//color
	private boolean[] solid;//if its solid (0 = walls, 1 = projectiles)
	private Rectangle2D bounds;

	public Tile(int r, int c, int type) {
		color = ColorData.getTileColor(type);
		solid = TileData.getSolid(type);
		bounds = new Rectangle2D.Double(c, r, 1, 1);
	}

	public Rectangle2D getBounds() {
		return bounds;
	}

	public boolean isSolid(int type) {
		return solid[type];
	}

	public Color getColor() {
		return color;
	}
}
