package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;

public abstract class PathFinder {
	public abstract LinkedList<Point> getPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles);
}
