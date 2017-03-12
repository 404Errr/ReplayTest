package client.level.pathfinding;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import data.TileData;

public class PathFindingTester implements TileData {
	public static int x1, y1, x2, y2;
	public static List<Point> linesAStar = new ArrayList<>(), linesAStar2 = new ArrayList<>(), linesMaze = new ArrayList<>(), linesMaze2 = new ArrayList<>();

	public static void find() {
		try {
			linesMaze = new MazePathFinder().getPath(x1, y1, x2, y2);
			linesMaze2 = RefinedPathFinder.refinePath(new MazePathFinder().getPath(x1, y1, x2, y2));
			linesAStar = new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable());
			linesAStar2 = RefinedPathFinder.refinePath(new AStarPathFinder().getPath(x1, y1, x2, y2, TileData.getUseable()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void set1(int x, int y) {
		x1 = x;
		y1 = y;
	}

	public static void set2(int x, int y) {
		x2 = x;
		y2 = y;
	}
}
