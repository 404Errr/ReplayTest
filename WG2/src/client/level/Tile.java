package client.level;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import client.level.pathfinding.PathFinder;
import data.ColorData;
import data.MapData;
import data.PathfindingData;
import data.TileData;
import main.Debug;
import util.Util;

public class Tile implements MapData, PathfindingData, TileData {

	private int tileType, c, r;//type of the tile, x, y
	private int totalCost, distanceCost;//f = g+h
	private boolean usable, diagonal;//, nextToWallClose, nextToWallFar;
	private int nextToWallCost;
	private Tile previous;

	public Tile(int c, int r, int tileType, boolean solid) {
		this.tileType = tileType;
		this.c = c;
		this.r = r;
		this.usable = !solid;//not solid
//		this.nextToWallClose = isNextToWall(1);
//		this.nextToWallFar = isNextToWall(2);
		for (int i = 0;i<WALL_DISTANCE;i++) {
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
		if (!isSolid(SOLID_WALLS)) switch (Debug.getTileDebugState()) {
		case COMBINED_COST:
			try{return Util.getRedGreenColorShift((float)getCombinedCosts()/(float)PathFinder.getHighestCombined().getCombinedCosts());} catch(Exception e) {}
		case TOTAL_COST:
			return Util.getRedGreenColorShift(1-(float)getTotalCost()/(float)PathFinder.getHighestTotal().getTotalCost());
		case DISTANCE_COST:
			return Util.getRedGreenColorShift((float)getDistanceCost()/(float)PathFinder.getHighestDistance().getDistanceCost());
		default:
		}
		return ColorData.getTileColor(tileType);
	}

	//pathfinding
	public void reset() {
		previous = null;
		diagonal = false;
		totalCost = 0;
		distanceCost = 0;
	}

	public void setIsDiagonal(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public boolean isUsable() {
		return usable;
	}

	public Tile getPrevious() {
		return previous;
	}

	public void setPrevious(Tile previous) {
		this.previous = previous;
	}

	public int getDistanceCost() {
		return distanceCost;
	}

	public int getCombinedCosts() {
		return totalCost+distanceCost;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Tile previousTile) {
		this.totalCost = calculateTotalCost(previousTile);
	}

	public int calculateTotalCost(Tile previousTile) {
		return previousTile.getTotalCost()+((diagonal)?DIAGONAL_MOVEMENT_COST:BASIC_MOVEMENT_COST)+nextToWallCost;
	}

	public void setDistanceCost(Tile endTile) {
		distanceCost = (int) (Util.distance(c, r, endTile.getC(), endTile.getR())*BASIC_MOVEMENT_COST);
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