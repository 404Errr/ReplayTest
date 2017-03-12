package client.level.pathfinding;

import java.awt.Point;
import java.util.List;

import data.TileData;
import util.BreakableLine;
import util.Util;

public class RefinedPathFinder {

	public static List<Point> refinePath(List<Point> pathPoints) {
		for (int i = pathPoints.size()-1;i>1;i--) {
			if (canWalkBetween(pathPoints.get(i), pathPoints.get(i-2))) {
				pathPoints.remove(i-1);
			}
		}
		return pathPoints;
	}

	private static boolean canWalkBetween(Point p1, Point p2) {//true if there are no obstacles between p1 and p2
		PathLine pathLine = new PathLine(p1.x+0.5f, p1.y+0.5f, p2.x+0.5f, p2.y+0.5f);
		return !pathLine.isBroken();
	}
}

class PathLine extends BreakableLine {

	public PathLine(float x1, float y1, float x2, float y2) {
		super(x1, y1, x2, y2, Util.negateArray(TileData.getUseable()));
	}

	@Override
	public void setLocation() {}

}