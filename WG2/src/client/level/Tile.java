package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import shared.data.ColorData;
import shared.data.MapData;
import shared.data.TileData;

public class Tile implements MapData {
	private short type, r, c;

	public Tile(int r, int c, int type) {
		this.type = (short)type;
		this.r = (short)r;
		this.c = (short)c;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(c, r, 1, 1);
	}

	public boolean isSolid(int solidityType) {
		return TileData.getSolid(this.type)[solidityType];
	}

	public Color getColor() {
		return ColorData.getTileColor(type);
	}
}
