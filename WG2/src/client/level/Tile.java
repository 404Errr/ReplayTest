package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import data.ColorData;
import data.MapData;
import data.PathfindingData;
import data.TileData;

public class Tile implements MapData, PathfindingData, TileData {

	protected boolean usable;
	protected int tileType, c, r, nextToWallCost;

	public Tile(int c, int r, int tileType, boolean solid) {//x, y, type of the tile
		this.tileType = tileType;
		this.c = c;
		this.r = r;
		this.usable = !solid;//not solid
		if (!solid) for (int i = 1;i<WALL_DISTANCE;i++) {
			if (isNextToWall(i)) nextToWallCost+=WALL_MOVEMENT_COST;
		}
	}

	public int getC() {
		return c;
	}

	public int getR() {
		return r;
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

	public int getNextToWallCost() {
		return nextToWallCost;
	}

	public boolean isUsable() {
		return usable;
	}

	public boolean isNextToWall(int range) {
		for (int y = r-range;y<=r+range;y++) {
			for (int x = c-range;x<=c+range;x++) {
				if (y>0&&x>0&&y<Level.getHeight()&&x<Level.getWidth()&&TileData.getSolid(Level.getLayoutType(x, y))[SOLID_WALLS]) {
					return true;
				}
			}
		}
		return false;
	}
}