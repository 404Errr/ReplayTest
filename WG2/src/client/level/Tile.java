package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import data.AIData;
import data.ColorData;
import data.MapData;
import data.TileData;

public class Tile implements MapData, AIData, TileData {

	protected boolean usable;
	protected int c, r, nextToWallCost;

	public Tile(int c, int r, int tileType, boolean solid) {//x, y, soliditity
		this.c = c;
		this.r = r;
		this.usable = !solid&&getColor()!=null;
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
		return TileData.getSolid(Level.getLayoutType(c, r))[solidityType];
	}

	public Color getColor() {
		return ColorData.getTileColor(Level.getLayoutType(c, r));
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