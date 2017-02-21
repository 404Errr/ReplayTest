package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import data.ColorData;
import data.MapData;
import data.TileData;

public class Tile implements MapData {
	private final int tileType, r, c;

	public Tile(int r, int c, int tileType) {
		this.tileType = tileType;
		this.r = r;
		this.c = c;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(c, r, 1, 1);//r and c have been switched
	}

	public boolean isSolid(int solidityType) {
		return TileData.getSolid(tileType)[solidityType];
	}

	public Color getColor() {
		return ColorData.getTileColor(tileType);
	}
}
