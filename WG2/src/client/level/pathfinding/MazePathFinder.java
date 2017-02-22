package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import client.level.Level;
import data.TileData;


public class MazePathFinder {
	private boolean[][] looked;
	List<Point> path;

	List<Point> getPath(int x1, int y1, int x2, int y2) {
		path = new LinkedList<>();
		looked = new boolean[Level.getHeight()][Level.getWidth()];
		solvePath(x1, y1, x2, y2);
		path.add(new Point(x2, y2));
		return path;
	}

	private boolean usable(int c, int r) {
		return !looked[r][c]&&!Level.getTile(c, r).isSolid(TileData.SOLID_WALLS);
	}

	private boolean solvePath(int c, int r, int endC, int endR) {
		System.out.println(c+", "+r+"\t"+endC+", "+endR+"\t"+(r==endR)+" "+(c==endC));
		if(!(r>=0&&c>=0&&r<Level.getHeight()&&c<Level.getWidth())) return false;
		if (r==endR&&c==endC) return true;
		looked[r][c] = true;
		Point point = new Point(c, r);
		path.add(point);
		boolean solved = false;
		if (!solved&&usable(c+1, r)&&solvePath(c+1, r, endC, endR)) return true;//R
		if (!solved&&usable(c, r+1)&&solvePath(c, r+1, endC, endR)) return true;//D
		if (!solved&&usable(c-1, r)&&solvePath(c-1, r, endC, endR)) return true;//L
		if (!solved&&usable(c, r-1)&&solvePath(c, r-1, endC, endR)) return true;//U
		path.remove(point);
		return false;
	}
}
