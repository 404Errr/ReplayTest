package client.level.pathfinding;

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import data.TileData;
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
		Util.removeRepeatsFromEnds(pathPoints);
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
			}
		}
		Util.removeRepeatsFromEnds(pathPoints);
		return RefinePath.removeLines(pathPoints);
	}

	public static LinkedList<Point> refinePath(List<Point> pathPoints, int buffer, int maxChain) {//TODO
		if (buffer<1) return refinePath(pathPoints);
		if (pathPoints.isEmpty()) return (LinkedList<Point>) pathPoints;
		for (int i = 0;i<buffer;i++) {
			pathPoints.add(0, pathPoints.get(0));
			pathPoints.add(pathPoints.get(pathPoints.size()-1));
		}
		int chainCount = 0;
		for (int i = pathPoints.size()-1;i>buffer*2-1;i--) {
			if (canWalkBetween(pathPoints.get(i), pathPoints.get(i-buffer*2))) {
				if (chainCount<=maxChain) {
					pathPoints.remove(i-buffer);
					chainCount++;
				}
				else chainCount = 0;
			}
			else chainCount = 0;
		}
		Util.removeRepeatsFromEnds(pathPoints);
		return RefinePath.removeLines(pathPoints);
	}

	public static LinkedList<Point> removeLines(List<Point> pathPoints) {//FIXME add diagonals
		if (pathPoints.isEmpty()) return (LinkedList<Point>) pathPoints;
		for (int k = 0;k<pathPoints.size();k++) {
				for (int i = pathPoints.size()-1;i>=2;i--) {
				Point p1 = pathPoints.get(i), p2 = pathPoints.get(i-1), p3 = pathPoints.get(i-2);
				if (((p1.x==p2.x&&p2.x==p3.x)||(p1.y==p2.y&&p2.y==p3.y))) {
					pathPoints.remove(p2);
				}
			}
		}
		return (LinkedList<Point>) pathPoints;
	}

	private static boolean canWalkBetween(Point p1, Point p2) {//true if there are no obstacles between p1 and p2
		if (Util.lineIsBrokenByBooleanArray(p1.x, p1.y, p2.x, p2.y, Util.negateArray(TileData.getUseable()))) return false;
		if (Util.lineIsBrokenByBooleanArray(p1.x+1, p1.y, p2.x+1, p2.y, Util.negateArray(TileData.getUseable()))) return false;
		if (Util.lineIsBrokenByBooleanArray(p1.x, p1.y+1, p2.x, p2.y+1, Util.negateArray(TileData.getUseable()))) return false;
		if (Util.lineIsBrokenByBooleanArray(p1.x+1, p1.y+1, p2.x+1, p2.y+1, Util.negateArray(TileData.getUseable()))) return false;
//		if (Util.lineIsBrokenByBooleanArray(p1.x+0.5f, p1.y+0.5f, p2.x+0.5f, p2.y+0.5f, Util.negateArray(TileData.getUseable()))) return false;
		return true;
	}
}
