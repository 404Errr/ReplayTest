package util;

import data.TileData;

public class WGUtil {
	public static final int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;

	public static int getSide(float x, float y) {//FIXME
		float[] distances = new float[4];
		distances[RIGHT] = (int)x+1-x;
		distances[DOWN] = (int)y+1-y;
		distances[LEFT] = x-(int)x;
		distances[UP] = y-(int)y;
		return Util.minInArray(distances);
	}


	public static int getSide(float x, float y, int[][] layout) {//FIXME
		float[] distances = new float[4];
		distances[RIGHT] = (int)x+1-x;
		distances[DOWN] = (int)y+1-y;
		distances[LEFT] = x-(int)x;
		distances[UP] = y-(int)y;
		if (x>=layout[0].length-1||(x<layout[0].length-1&&TileData.getSolid(layout[(int) y][(int) (x+1)])[TileData.SOLID_WALLS])) distances[RIGHT]+=1;
		if (y>=layout.length-1||(y<layout.length-1&&TileData.getSolid(layout[(int) (y+1)][(int) x])[TileData.SOLID_WALLS])) distances[DOWN]+=1;
		if (x<=0||(x>0&&TileData.getSolid(layout[(int) y][(int) (x-1)])[TileData.SOLID_WALLS])) distances[LEFT]+=1;
		if (y<=0||(y>0&&TileData.getSolid(layout[(int) (y-1)][(int) x])[TileData.SOLID_WALLS])) distances[UP]+=1;
		return Util.minInArray(distances);
	}

	public static int getSide(float x, float y, float dX, float dY, int[][] layout) {//FIXME
		float[] distances = new float[4];
		distances[RIGHT] = (int)x+1-x;
		if (dX>0) distances[RIGHT]++;
		if (x>=layout[0].length-1||(x<layout[0].length-1&&TileData.getSolid(layout[(int) y][(int) (x+1)])[TileData.SOLID_WALLS])) distances[RIGHT]+=1;
		distances[DOWN] = (int)y+1-y;
		if (dY>0) distances[DOWN]++;
		if (y>=layout.length-1||(y<layout.length-1&&TileData.getSolid(layout[(int) (y+1)][(int) x])[TileData.SOLID_WALLS])) distances[DOWN]+=1;
		distances[LEFT] = x-(int)x;
		if (dX<0) distances[LEFT]++;
		if (x<=0||(x>0&&TileData.getSolid(layout[(int) y][(int) (x-1)])[TileData.SOLID_WALLS])) distances[LEFT]+=1;
		distances[UP] = y-(int)y;
		if (dY<0) distances[UP]++;
		if (y<=0||(y>0&&TileData.getSolid(layout[(int) (y-1)][(int) x])[TileData.SOLID_WALLS])) distances[UP]+=1;
		return Util.minInArray(distances);
	}

//	public static int getSide(float x, float y, float dX, float dY) {//FIXME
//		float[] distances = new float[4];
//		distances[RIGHT] = (int)x+1-x;
//		if (dX>0) distances[RIGHT]++;
//		distances[DOWN] = (int)y+1-y;
//		if (dY>0) distances[DOWN]++;
//		distances[LEFT] = x-(int)x;
//		if (dX<0) distances[LEFT]++;
//		distances[UP] = y-(int)y;
//		if (dY<0) distances[UP]++;
//		return Util.minInArray(distances);
//	}
}
