package client.level.pathfinding;

import java.awt.Point;
import java.util.LinkedList;

import util.Util;

public class PreGeneratedPath extends PathFinder {

	private PointLinkedList[][][][] paths;

	public PreGeneratedPath(boolean[][] useableTiles) {
		paths = new PointLinkedList[useableTiles.length][useableTiles[0].length][useableTiles.length][useableTiles[0].length];
		PathFinder finder = new AStarPathFinder();
		LinkedList<Point> generated;

		for (int x1 = 0;x1<useableTiles[0].length;x1++) {
			for (int y1 = 0;y1<useableTiles.length;y1++) {
				if (!useableTiles[y1][x1]) continue;
				StringBuilder log = new StringBuilder();
				for (int x2 = 0;x2<useableTiles[0].length;x2++) {
					for (int y2 = 0;y2<useableTiles.length;y2++) {
						if (!useableTiles[y2][x2]) continue;
						long startTime = System.currentTimeMillis();
						generated = finder.getPath(x1, y1, x2, y2, useableTiles);
						if (generated.size()>1) {
							log.append(y1+", "+x1+"\t"+y2+", "+x2+"\t"+(System.currentTimeMillis()-startTime)/1000f+"\n");
							paths[y1][x1][y2][x2] = new PointLinkedList(generated);
						}
					}
				}
				System.out.print(log);
			}
		}
	}

	public LinkedList<Point> getPath(int x1, int y1, int x2, int y2) {
		if (Util.inArrayBounds(x1, y1, paths)&&Util.inArrayBounds(x2, y2, paths[0][0])&&paths[y1][x1][y2][x2]!=null) {
			return paths[y1][x1][y2][x2].getPoints();
		}
		else {
			return new LinkedList<>();
		}
	}

	@Override
	public LinkedList<Point> getPath(int x1, int y1, int x2, int y2, boolean[][] useableTiles) {
		return null;
	}

	public PointLinkedList[][][][] getPaths() {
		return paths;
	}
}

class PointLinkedList {
	private LinkedList<Point> points;

	public PointLinkedList(LinkedList<Point> points) {
		this.points = points;
	}

	public LinkedList<Point> getPoints() {
		return points;
	}
}

