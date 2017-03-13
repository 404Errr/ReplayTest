package client.level.pathfinding;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import data.TileData;
import util.BreakableLine;
import util.Util;

public class RefinePath {

	public static LinkedList<Point> refinePath(List<Point> pathPoints) {
		if (pathPoints.isEmpty()) return (LinkedList<Point>) pathPoints;
		if (canWalkBetween(pathPoints.get(0), pathPoints.get(pathPoints.size()-1))) return new LinkedList<>(Arrays.asList(pathPoints.get(0), pathPoints.get(pathPoints.size()-1)));
		for (int i = pathPoints.size()-1;i>1;i--) {
			if (canWalkBetween(pathPoints.get(i), pathPoints.get(i-2))) {
				pathPoints.remove(i-1);
			}
		}
		return RefinePath.removeLines(pathPoints);
	}

	public static LinkedList<Point> refinePath(List<Point> pathPoints, int buffer) {
		if (buffer<1) return refinePath(pathPoints);
		if (pathPoints.isEmpty()) return (LinkedList<Point>) pathPoints;
		for (int i = 0;i<buffer;i++) {
			pathPoints.add(0, pathPoints.get(0));
			pathPoints.add(pathPoints.get(pathPoints.size()-1));
		}
		for (int i = pathPoints.size()-1;i>buffer*2-1;i--) {
			if (canWalkBetween(pathPoints.get(i), pathPoints.get(i-buffer*2))) {
				pathPoints.remove(i-buffer);
//				pathPoints = Util.removeBetween(pathPoints, i-1, (i-buffer*2)+1);
			}
		}
		return RefinePath.removeLines(pathPoints);
	}

	public static LinkedList<Point> removeLines(List<Point> pathPoints) {
		if (pathPoints.isEmpty()) return (LinkedList<Point>) pathPoints;
		for (int i = pathPoints.size()-1;i>1;i--) {
			if (((pathPoints.get(i).x==pathPoints.get(i-1).x&&pathPoints.get(i).x==pathPoints.get(i-2).x)
				||(pathPoints.get(i).y==pathPoints.get(i-1).y&&pathPoints.get(i).y==pathPoints.get(i-2).y))

				//diagonal lines TODO

				&&canWalkBetween(pathPoints.get(i), pathPoints.get(i-2))) {
				pathPoints.remove(i-1);
			}
		}
		return (LinkedList<Point>) pathPoints;
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