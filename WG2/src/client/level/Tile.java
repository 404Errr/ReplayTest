package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import data.AIData;
import data.ColorData;
import data.MapData;
import data.TileData;

public class Tile implements MapData, AIData, TileData {

//	protected boolean usable;
	protected int cX, cY, x, y, type;//, nextToWallCost

	public Tile(int cX, int cY, int x, int y, int tileType) {//x, y, soliditity
		this.cX = cX;
		this.cY = cY;
		this.x = x;
		this.y = y;
		this.type = tileType;
//		this.usable = !solid&&getColor()!=null;
//		if (!solid) for (int i = 1;i<WALL_DISTANCE;i++) {
//			if (isNextToWall(i)) nextToWallCost+=WALL_MOVEMENT_COST;
//		}
	}

	public int getX() {
		return cX*Level.size+x;
	}

	public int getY() {
		return cY*Level.size+y;
	}

	public int getcX() {
		return cX;
	}

	public int getcY() {
		return cY;
	}

	public int getType() {
		return type;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(cX*Level.size+x, cY*Level.size+y, 1, 1);
	}

//	public boolean isSolid(int solidityType) {
//		return TileData.getSolid(Level.getLayoutType(c, r))[solidityType];
//	}

	public Color getColor() {
		return ColorData.getTileColor(type);
	}

//	public int getNextToWallCost() {
//		return nextToWallCost;
//	}
//
//	public boolean isUsable() {
//		return usable;
//	}

//	public boolean isNextToWall(int range) {
//		for (int y = r-range;y<=r+range;y++) {
//			for (int x = c-range;x<=c+range;x++) {
//				if (y>0&&x>0&&y<Level.getHeight()&&x<Level.getWidth()&&TileData.getSolid(Level.getLayoutType(x, y))[SOLID_WALLS]) {
//					return true;
//				}
//			}
//		}
//		return false;
//	}
}