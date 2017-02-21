package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import shared.data.ColorData;
import shared.data.MapData;
import shared.data.TileData;

public class Tile implements MapData {
	private final short tileType, r, c;

	public Tile(int r, int c, int tileType) {
		this.tileType = (short)tileType;
		this.r = (short)r;
		this.c = (short)c;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(c, r, 1, 1);
	}

	public boolean isSolid(int solidityType) {
		return TileData.getSolid(tileType)[solidityType];
	}

	public Color getColor() {
		return ColorData.getTileColor(tileType);
	}
}
