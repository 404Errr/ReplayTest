package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import util.Util;


public class MazePathFinder extends PathFinder {
	private boolean[][] looked;
	List<Point> path;
	private boolean[][] useableTiles;

	@Override
	public LinkedList<Point> getPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles) {
		this.useableTiles = useableTiles;
		path = new LinkedList<>();
		looked = new boolean[useableTiles.length][useableTiles[0].length];
		solvePath(x1, y1, x2, y2);
		path.add(new Point(x2, y2));
		return (LinkedList<Point>) path;
	}

	private boolean usable(int c, int r) {
		return Util.inArrayBounds(c, r, useableTiles)&&!looked[r][c]&&useableTiles[r][c];
	}

	private boolean solvePath(int c, int r, int endC, int endR) {
		if(!Util.inArrayBounds(c, r, useableTiles)) return false;
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
